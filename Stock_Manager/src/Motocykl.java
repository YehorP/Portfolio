public class Motocykl extends Pojazd {
    private boolean hamologacja;
    public Motocykl(String name, double size,boolean hamologacja) {
        super(name, size);
        this.hamologacja =hamologacja;
    }

    public Motocykl(String name, double length, double width, double height,boolean hamologacja) {
        this(name,length*width*height,hamologacja);
    }

    public boolean isHamologacja() {
        return hamologacja;
    }


    @Override
    public String toString() {
        return " Motocykl " + name +" , rozmiar = "+ size + (hamologacja?" , hamologacja = tak":" , hamologacja = nie");
    }
}
