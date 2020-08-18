public class Przedmiot extends Byt implements Comparable<Przedmiot>{
    //tutaj dziedziczy rozmiar
    private String name;
    private boolean unpackable = false; // czy możemy rozwożyć przedmiot
    private boolean state = false; //odpowiada za stan zwożony rozwożony false zwożony true rozwożony
    private boolean located = false; // odpowiada czy znajduje przedmiot w pomieszczeniu czy nie false:nie znajdue , true znajduje
    private double unpackedSize;
    //private double sizeDifference;
    public Przedmiot(String name,double size){
            super(size);
            this.name = name;
            this.unpackedSize = 0;
    }
    public Przedmiot(String name,double size,double unpackedSize){
        this(name,size);
        this.unpackedSize=unpackedSize;
        //this.sizeDifference=this.unpackedSize-this.size;
    }
    public Przedmiot(String name,double length, double width, double height){
        this(name,length*width*height);
    }
    public Przedmiot(String name,double length, double width, double height,double unpackedSize){
        this(name,length*width*height,unpackedSize);
    }


    public boolean unpack(){
        if (unpackable && !state) {
            //size += sizeDifference;
            double tmp = size;
            size=unpackedSize;
            unpackedSize=tmp;
            state = true;
            return true;
        }
        else {
            System.out.println("Przedmiot już rozwożony albo nie może być rozwożony");
            return false;
        }
    }
     public boolean packBack(){
        if (unpackable && state){
            //size-= sizeDifference;
            double tmp = size;
            size=unpackedSize;
            unpackedSize=tmp;
            state = false;
            return true;
        }
        else {
            System.out.println("Ten przedmiot nie można rozwożyć albo złożyć");
            return false;
        }
     }

    public String getName() {
        return name;
    }

    public boolean isUnpackable() {
        return unpackable;
    }

    public boolean getState() {
        return state;
    }

    public boolean getLocated() {
        return located;
    }

    public double getUnpackedSize() {
        return unpackedSize;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setLocated() {
        this.located = !this.located;
    }

    @Override
    public int compareTo(Przedmiot o) {
        if (this.state)
            this.packBack();
        else if (o.getState())
            o.packBack();

        if ((this.size-o.size)>0)
            return 1;
        else if((this.size-o.size)<0)
            return -1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return " Nazwa = " +name + " size=" + size +" właściwości = " + (unpackable?" rozkłada":" nie rozkłada");
    }
}
