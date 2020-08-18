import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OverloadConnectionThread implements Runnable {
    public static Map<String,User> usersList=new HashMap<>();
    public static final Object mutex=new Object();
    public static Thread usersListCleaner;
    DatagramSocket tmpSocket;
    byte [] ACK="ACK".getBytes();
    byte[]NORETR="NORETR".getBytes();
    byte[]END="END".getBytes();
    InetAddress userIp;
    String identifier;
    File resFile;
    String []sequence;
    int fromPort;
    int userPort;

    static {
        usersListCleaner=new Thread(()->{
            while (true) {
                synchronized (mutex) {
                    usersList = usersList.entrySet().stream().filter(key -> !key.getValue().isOld).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                }
                try {
                    Thread.sleep(15000);
                }catch (Exception ex){
                    System.out.println("Cleaner Exception");
                }
            }
        });
        usersListCleaner.setDaemon(true);
        usersListCleaner.start();
    }

    public OverloadConnectionThread( String[] sequence,DatagramSocket tmpSocket,DatagramPacket userPacket,int fromPort,File file) {
        this.sequence = sequence;
        this.tmpSocket=tmpSocket;
        this.userIp=userPacket.getAddress();
        this.fromPort=fromPort;
        this.userPort=userPacket.getPort();
        identifier=userIp.toString()+userPort;
        this.resFile=file;
    }


    public void absoluteSend(DatagramSocket socket, DatagramPacket packet) throws Exception{
        int i=0;
        boolean failed=true;
        DatagramPacket recPacket=new DatagramPacket(new byte[256],256);
        while (i<3){
            try {
                socket.send(packet);
                socket.setSoTimeout(4000);
                socket.receive(recPacket);
                if (!recPacket.getAddress().equals(packet.getAddress()) || !new String(recPacket.getData()).startsWith("ACK")) {
                    break;
                }
                socket.send(new DatagramPacket(NORETR,NORETR.length,packet.getAddress(),packet.getPort()));
                failed=false;
                break;
            }catch (Exception ex){
                i++;
                System.out.println("Absolute Transmite Exception");
            }
        }
        if (failed)
            throw new Exception("Sending Failed");
    }

    public void exchange(){
        try {
            boolean flag=true;
            boolean share=false;
            absoluteSend(tmpSocket,new DatagramPacket(ACK,ACK.length,userIp,userPort));

            synchronized (mutex) {
                if (usersList.keySet().contains(identifier)){
                    usersList.get(identifier).confirmations.add(fromPort+"");
                }else {
                    usersList.put(identifier, new User(sequence.length, userIp, userPort));
                    usersList.get(identifier).confirmations.add(fromPort + "");
                }

                if (usersList.get(identifier).canBeChecked()){
                    if (usersList.get(identifier).checkConSequence(sequence)){
                        share=true;
                    }else
                        flag=false;
                    System.out.println(userIp+" "+userPort+" "+usersList.get(identifier).confirmations);
                    usersList.get(identifier).stop();
                    usersList.remove(identifier);
                }
            }

            if (flag)
                absoluteSend(tmpSocket,new DatagramPacket(ACK,ACK.length,userIp,userPort));
            if (share){
                DatagramSocket dt=new DatagramSocket();
                shareFile(dt);
            }
            tmpSocket.close();
        }
        catch (Exception ex){
            System.out.println("Exchanging Exception");
        }
    }

    public void shareFile(DatagramSocket dataSocket){
        try {
            String fileData=resFile.getName()+" "+resFile.length();
            absoluteSend(dataSocket,new DatagramPacket(fileData.getBytes(),fileData.getBytes().length,userIp,userPort));
            byte []buffer=new byte[4096];
            BufferedInputStream bfs=new BufferedInputStream(new FileInputStream(resFile));
            int transmitValue;
            while ((transmitValue=bfs.read(buffer))!=-1){
                absoluteSend(dataSocket,new DatagramPacket(buffer,buffer.length,userIp,userPort));
                buffer=new byte[4096];
            }
            absoluteSend(dataSocket,new DatagramPacket(END,END.length,userIp,userPort));
            System.out.println("Send for "+userIp+" "+userPort);
            bfs.close();
            dataSocket.close();
        }catch (Exception ex){
            System.out.println("SENDING FILE ERROR");
        }
    }

    @Override
    public void run() {
        try {
            exchange();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}