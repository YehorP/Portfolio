public class Samochod extends Pojazd {
    private int engineCapacity; //pojemność silnika
    private PojazdTyp type;
    public Samochod(String name, double size, int engineCapacity , String type) {
        super(name, size);
        this.engineCapacity=engineCapacity;
        switch (type){
            case "g":
                this.type=PojazdTyp.gaz;
                break;
            case  "h":
                this.type=PojazdTyp.hybryd;
                break;
            case "b":
                this.type=PojazdTyp.benzyn;
        }
    }

    public Samochod(String name, double length, double width, double height,int engineCapacity ,String type) {
        this(name, length*width*height,engineCapacity,type);
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public PojazdTyp getType() {
        return type;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void setType(PojazdTyp type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return " Samochód  " + name +" , rozmiar = " + size+" , typ = " + type +
                " , pojemność śilnika = "+ engineCapacity;
    }
}
