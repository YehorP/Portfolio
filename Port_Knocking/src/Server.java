import java.io.File;
import java.net.DatagramSocket;
import java.util.*;

public class Server {
   ArrayList<DatagramSocket> socketsList=new ArrayList<>();
  File resFile;
  String []sequence;

    public Server(String[]ports) {
        sequence=ports;
        try {
            boolean flag;
           for (int i=0;i<ports.length;++i){
               flag=false;
               for (int a=i-1;a>-1;a--){
                   if (ports[i].equals(ports[a]))
                       flag=true;
               }
               if (!flag)
               socketsList.add(new DatagramSocket(Integer.parseInt(ports[i].trim())));
           }
           resFile=new File(System.getProperty("user.dir")+"\\film.mpg");
        }catch (Exception ex){
            System.out.println("Wrong Arguments");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            for(DatagramSocket socket : socketsList)
                socket.close();
        }));
    }

    public void listenPorts(){
        for(int i=0;i<socketsList.size()-1;i++){
            new Thread(new ServerThread(sequence,socketsList.get(i),i,socketsList.size())).start();
        }
        new Thread(new ServerThread(sequence,socketsList.get(socketsList.size()-1),socketsList.size()-1,socketsList.size(),resFile)).start();
    }

    public static void main(String[] args) {
       Server server= new Server(args);
       server.listenPorts();
    }
}
