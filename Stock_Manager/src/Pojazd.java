public abstract class Pojazd extends Byt {
    String name;
    boolean isLocated = false;
    public Pojazd(String name,double size) {
        super(size);
        this.name=name;
    }

    public boolean isLocated() {
        return isLocated;
    }

    public void setLocated() {
        isLocated = !isLocated;
    }

    @Override
    public abstract String  toString();
}
