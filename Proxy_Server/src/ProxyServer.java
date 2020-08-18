import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProxyServer {
    int port;
    public ProxyServer(String port) {
        try {
            int tmp=Integer.parseInt(port);
            this.port=tmp>1023 && tmp<65536?tmp:8080;
        }catch (NumberFormatException ex){
            this.port=8080;
        }
    }
    public ProxyServer(){
        this("8080");
    }

    public void listenSocket() {
        ServerSocket server = null;
        Socket client = null;
        Executor ex= Executors.newFixedThreadPool(30);
        try {
            server = new ServerSocket(port);
        }
        catch (IOException e) {
            System.out.println("Could not listen");
            System.exit(-1);
        }
        System.out.println("Server listens on port: " + server.getLocalPort());

        while(true) {
            try {
                client = server.accept();
            }
            catch (IOException e) {
                System.out.println("Accept failed");
                System.exit(-1);
            }
            ex.execute(new ProxyThread(client));
        }
    }

    public static void main(String[] args) {
        ProxyServer server = args.length>0?new ProxyServer(args[0]):new ProxyServer();
        server.listenSocket();
    }
}
