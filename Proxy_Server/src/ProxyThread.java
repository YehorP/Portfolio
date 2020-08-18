import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
public class ProxyThread implements Runnable{
    Socket clientSocket;
    BufferedReader br;
    BufferedWriter bw;
    boolean isHttps=false;
    public ProxyThread(Socket clientSocket) {
        this.clientSocket=clientSocket;
    }

    @Override
    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            String urlAdress="";
            StringBuilder req=new StringBuilder(),tmp=new StringBuilder();
            int port=0;
            tmp.append(br.readLine());
            String tab[];

            while (!tmp.toString().isEmpty()) {
                if(tmp.toString().startsWith("CONNECT")){
                    isHttps=true;
                }
                else if (tmp.toString().startsWith("Host:") ){
                    tab=tmp.toString().split(":");
                    urlAdress=tab[1].trim();
                    port=tab.length==3?Integer.decode(tab[2].trim()):isHttps?443:80;
                }
                else if(tmp.toString().startsWith("Proxy-Connection:")){
                    tmp.replace(0,6,"");
                }
                req.append(tmp.toString()+"\r\n");
                tmp.setLength(0);
                tmp.append(br.readLine());
            }
            req.append("\r\n");
            if (!isHttps) {
                Socket siteSocket = new Socket(urlAdress, port);
                siteSocket.getOutputStream().write(req.toString().getBytes());
                siteSocket.getOutputStream().flush();
               read_data(siteSocket,clientSocket);


                siteSocket.close();
            }
            else {
                Socket siteSocket = new Socket(urlAdress, port);
                bw.write("HTTP/1.0 200 Connection established\r\n" +
                        "Proxy-Agent: ProxyServer/1.0\r\n" +
                        "\r\n");
                bw.flush();
                siteSocket.setSoTimeout(5000);
                new Thread(()->{
                    try {
                        read_data(clientSocket, siteSocket);

                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                }).start();

                read_data(siteSocket, clientSocket);
                if (siteSocket!=null)
                siteSocket.close();
                log("Finished");
            }
            br.close();
            bw.close();
            clientSocket.close();

        }catch (SocketTimeoutException e) {
            String line = "HTTP/1.0 504 Timeout Occured after 5s\r\n" +
                    "User-Agent: ProxyServer/1.0\r\n" +
                    "\r\n";
            try{
                bw.write(line);
                bw.flush();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }  catch (IOException ex){
            System.out.println("IOException");
            ex.printStackTrace();

        }
    }

    private void read_data(Socket siteSocket, Socket clientSocket) throws IOException {
        byte[] buffer = new byte[4096];
        int read;
        do {
            read = siteSocket.getInputStream().read(buffer);
            if (read > 0) {
                clientSocket.getOutputStream().write(buffer, 0, read);
                if (siteSocket.getInputStream().available() < 1) {
                    clientSocket.getOutputStream().flush();
                }
            }
        } while (read >= 0);
    }

    public static void log(String message) {
        System.out.println("[S]: "+message);//-server message [s] means server
    }
}