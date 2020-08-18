import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerThread implements Runnable {
    DatagramSocket serverSocket;
    int index;
    int socketListSize;
    File resFile;
    String []sequence;

    public ServerThread( String[] sequence,DatagramSocket serverSocket,int index,int socketListSize) {
        this.sequence = sequence;
        this.serverSocket=serverSocket;
        this.index=index;
        this.socketListSize=socketListSize;
    }
    public ServerThread( String[] sequence,DatagramSocket tmpSocket,int index,int socketListSize,File file){
        this(sequence,tmpSocket,index,socketListSize);
        this.resFile=file;
    }

    @Override
    public void run() {
        System.out.println("listening to "+serverSocket.getLocalPort());
        while (true) {
            DatagramPacket tmpPacket;
            try {
                tmpPacket = new DatagramPacket(new byte[256], 256);
                serverSocket.receive(tmpPacket);
                if (new String(tmpPacket.getData()).startsWith("CON")) {
                    DatagramSocket tmpSocket = new DatagramSocket();
                    new Thread(new OverloadConnectionThread( sequence, tmpSocket, tmpPacket,serverSocket.getLocalPort(), resFile)).start();
                }
            }
            catch (Exception ex){
                System.out.println("Server port exception");
            }
        }
    }
}
