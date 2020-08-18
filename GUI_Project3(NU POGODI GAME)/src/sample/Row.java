package sample;

public class Row implements Comparable{
    int score;
    int time;
    String row;

    public Row (String row) {
        this.row=row;
        String [] data=row.split(" ");
        score=Integer.decode(data[3]);
        String [] timeText=data[5].split(":");
        int sec=timeText[2].charAt(0)=='0'?Character.getNumericValue(timeText[2].charAt(0)):Integer.decode(timeText[2]);
        int min = (timeText[1].charAt(0)=='0'?Character.getNumericValue(timeText[1].charAt(0)):Integer.decode(timeText[2]))*60;
        int hours  = (timeText[0].charAt(0)=='0'?Character.getNumericValue(timeText[0].charAt(0)):Integer.decode(timeText[0]))*3600;
        time=sec+min+hours;
    }

    @Override
    public int compareTo(Object o) {
        if (score==((Row) o).score) {
           return this.time-((Row) o).time;
        }
        return ((Row) o).score - this.score;

    }

    @Override
    public String toString() {
        return row;
    }
}
