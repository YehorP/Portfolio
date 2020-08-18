public class Rower extends Pojazd {
    int gearNumber;//liczba przerzutek
    boolean brakeType;// typ hamulców tarczowe:false zaciskowe:true
    int damperNumber = 0;// liczba amortyzatorów
    public Rower(String name, double size,int gearNumber,boolean brakeType) {
        super(name, size);
        this.gearNumber=gearNumber;
        this.brakeType=brakeType;
    }

    public Rower(String name, double length, double width, double height,int gearNumber,boolean brakeType) {
        this(name,length*width*height,gearNumber,brakeType);
    }

    public Rower(String name, double size,int gearNumber,boolean brakeType,int damperNumber){
        this(name,size,gearNumber,brakeType);
        this.damperNumber=damperNumber;
    }

    public Rower(String name, double length, double width, double height ,int gearNumber,boolean brakeType,int damperNumber) {
        this(name,length*width*height,gearNumber,brakeType,damperNumber);
    }
    @Override
    public String toString() {
        return " Rower "+name+" , rozmiar = "+size + " , liczba przerzutek = "+gearNumber+" , typ hamulców = "+(brakeType?"zaciskowe":"tarczowe")+(damperNumber>0?" , amortyzacja = tak"+" , liczba amortyzatory = "+damperNumber:" , amortyzacja = nie");
    }
}
