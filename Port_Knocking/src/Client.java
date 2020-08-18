import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Client {
    InetAddress serverAdress;
    File resultFile;
    byte[]ACK="ACK".getBytes();
    byte[]CON="CON".getBytes();
    String[] ports;
    DatagramSocket socket;
    public Client(String[]ports){
        try {
            if (ports[0].equals("localhost") || ports[0].equals("127.0.0.1"))
                this.serverAdress=InetAddress.getLocalHost();
            else
                this.serverAdress=InetAddress.getByName(ports[0]);

            this.socket=new DatagramSocket();
            String[] sequenceTmp=new String[ports.length-1];
            for (int i=1;i<ports.length;i++){
                sequenceTmp[i-1]=ports[i];
            }
            this.ports=sequenceTmp;
        } catch (Exception ex){
            System.out.println("Client constructor exception");
        }
    }

    public void completeLogin(){
        try {
            DatagramPacket dpReceive;
            String answer;
            int tmp;
            boolean flag=true;
            for (int i=0;i<ports.length && flag;i++){
                tmp=0;
                while(tmp<3) {
                    try {
                        socket.send(new DatagramPacket(CON, CON.length, serverAdress, Integer.parseInt(ports[i])));
                        dpReceive = new DatagramPacket(new byte[256], 256);
                        absoluteReceive(socket, dpReceive);
                        dpReceive=new DatagramPacket(new byte[256], 256);
                        absoluteReceive(socket, dpReceive);
                        answer = new String(dpReceive.getData());
                        if (i == ports.length - 1) {
                            getFile();
                        }
                        break;
                    } catch (Exception ex) {
                        tmp++;
                        if (tmp == 3)
                            flag = false;
                    }
                }
            }
        }catch (Exception ex){
            System.out.println("Failed to connect");
        }
        finally {
            socket.close();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
                socket.close();
        }));
    }

    public void getFile(){
        DatagramPacket tmpPacket=new DatagramPacket(new byte[256],256);
        try {
            System.out.println("waiting for file");
            absoluteReceive(socket,tmpPacket);
            String[] answer=new String(tmpPacket.getData()).split(" ");
            String fullFileName=answer[0].trim();
            String adress = serverAdress.equals(InetAddress.getLocalHost())?"127.0.0.1":InetAddress.getLocalHost().toString().split("/")[1].replaceAll("[.]", "_");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            Date dt = new Date(System.currentTimeMillis());
            String fileName=adress+"_"+socket.getLocalPort()+"_"+formatter.format(dt)+"_"+fullFileName;
            resultFile=new File(System.getProperty("user.dir")+"\\odebrane\\"+fileName);
            BufferedOutputStream fo=new BufferedOutputStream(new FileOutputStream(resultFile));
            resultFile.createNewFile();
            tmpPacket=new DatagramPacket(new byte[4096],4096);
            absoluteReceive(socket,tmpPacket);
            byte[]prev=new byte[1];
            while (!new String(tmpPacket.getData()).startsWith("END")){
                if (prev.length==1){
                    prev=tmpPacket.getData();
                }else {
                    fo.write(prev);
                    fo.flush();
                    prev=tmpPacket.getData();
                }
                absoluteReceive(socket,tmpPacket);
            }
            prev=trim(prev);
            fo.write(prev);

            System.out.println("Transmission Complete");
            fo.flush();
            fo.close();
        }catch (Exception ex){
            System.out.println("Creating and transfering file exception");
            resultFile.delete();
        }
    }

    public byte[] trim(byte[] array){
        int i = array.length - 1;
        while (i >= 0 && array[i] == 0)
        {
            --i;
        }
        return Arrays.copyOf(array, i + 1);
    }

    public void absoluteReceive(DatagramSocket socket,DatagramPacket dt) throws Exception{
        int flag=0;
        int retr=0;
        boolean failed=true;
        while (flag<3){
            try {
                socket.setSoTimeout(8000);
                socket.receive(dt);
                socket.send(new DatagramPacket(ACK,ACK.length,dt.getAddress(),dt.getPort()));
                DatagramPacket postRec=new DatagramPacket(new byte[256],256);
                while (retr<3) {
                    try {
                        retr++;
                        socket.setSoTimeout(8000);
                        socket.receive(postRec);
                        if (new String(postRec.getData()).startsWith("NORETR")) {
                            break;
                        }
                        socket.send(new DatagramPacket(ACK, ACK.length, dt.getAddress(), dt.getPort()));
                    }catch (Exception ex2){
                        System.out.println("Retransmition not occured");
                        break;
                    }
                }
                flag=3;
                failed=false;
            }catch (Exception ex){
                flag++;
                System.out.println("Absolute Receive Exception");
            }
        }
        if (failed)
            throw new Exception("Receive failed");
    }

    public static void main(String[] args) {
        try {
            Client cl = new Client(args);
            cl.completeLogin();
        }
        catch (Exception ex){
            System.out.println("Program execution exception");
        }
    }
}