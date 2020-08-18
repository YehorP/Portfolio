package sample;

public class Timer extends Thread {
    int hours=0;
    int minutes=0;
    int seconds=0;

    boolean inGame=true;


    public void changeIngame(){
        inGame=false;
    }
    @Override
    public void run() {
        while(inGame){
            increment();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }


    private void increment(){
        seconds++;
        if (seconds==60){
            seconds=0;
            minutes++;
            if (minutes==60){
                minutes=0;
                hours++;
            }
        }
    }

    public String getTime(){
        return (hours-10>9?hours:("0"+hours))+":"+(minutes-10>9?minutes:("0"+minutes))+":"+(seconds-10>-1?seconds:("0"+seconds));
    }
}
