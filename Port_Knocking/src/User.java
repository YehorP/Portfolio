import java.net.InetAddress;
import java.util.ArrayList;

public class User {
    ArrayList<String> confirmations=new ArrayList<>();
    InetAddress userIp;
    int port;
    int allowedSize;
    int timer=0;
    boolean isOld=false;


    public User(int size, InetAddress userIp,int port) {
        this.userIp = userIp;
        this.port=port;
        allowedSize=size;
        new Thread(()->{
            while (!isOld) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Time ex");
                }
                timer++;
                if (timer == 180) {
                    isOld = true;
                }
            }
        }).start();
    }
    public boolean canBeChecked(){
        return confirmations.size()==allowedSize;
    }
    public void stop(){
        this.isOld=true;
    }

    public boolean checkConSequence(String []right_sequence) {
            for (int i=0;i<right_sequence.length;i++){
                if (!right_sequence[i].trim().equals(confirmations.get(i).trim())) {
                    return false;
                }
        }
        return true;
    }


}
