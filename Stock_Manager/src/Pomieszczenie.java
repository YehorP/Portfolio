import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Pomieszczenie {
    private static int autonumeration = 0;
    private int id;
    private double size;
    private double occupatedSize=0;//zajęte miejsce
    private int daysAmount;// illiść dni wynajmu
    private  boolean rentable = true;// czy możemy wynajmować
    private Osoba osoba = null;
    private LocalDate rentDate = null;
    private boolean containsVechicle = false;
    private ArrayList <Byt> elements= new ArrayList<Byt>();

    public Pomieszczenie(double size) {
        this.id = autonumeration++;
        this.size = size;
        Magazyn.roomsRegister.add(this);
    }

    public Pomieszczenie(double length ,double width, double height ) {
       this(length*width*height);
    }

    public void changeStatus(){
        rentable = !rentable;
    }

    public boolean rentThisRoom(Osoba osoba,int daysAmount){
        if(rentable){
            this.rentDate = LocalDate.now();
            this.osoba = osoba;
            this.daysAmount = daysAmount;
            changeStatus();
            try {
                osoba.getFirsRentDate();
            }catch (NeverRentException e){
                osoba.setFirsRentDate(rentDate);
            }
            return true;
        }
        else {
            return false;
        }
    }

    public void checkoutCheck(){
        if(this.daysAmount==0&& this.rentDate!=null){
            this.rentDate = null;
            osoba.getInRent().remove(this);
            if (osoba.getInRent().size()!=0){
                for (Byt e:elements){
                    for (Pomieszczenie b:osoba.getInRent()){
                        try {
                            if(b.add(e)){
                                break;
                            }
                        }catch (TooManyThingsException n){}
                    }
                }
            }
                this.osoba=null;
                elements.clear();
        }
    }

    public boolean add(Byt przedmiot)throws TooManyThingsException{
        if (occupatedSize+przedmiot.size<=size){
            occupatedSize+=przedmiot.size;
            elements.add(przedmiot);
            return  true;
        }
        else {
            throw new TooManyThingsException();
        }
    }

    public boolean remove(Byt przedmiot){
        if(elements.contains(przedmiot)){
            occupatedSize-=przedmiot.size;
            elements.remove(przedmiot);
            return true;
        }
        else {
            System.out.println("Room doesn't contain such item");
            return false;
        }
    }

    public boolean remove(int id){
        if((elements.size()-1)>=id){
            occupatedSize-=elements.get(id).size;
            elements.remove(id);
            return true;
        }
        else {
            System.out.println("Room doesn't contain such item");
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public double getSize() {
        return size;
    }

    public int getDaysAmount() {
        return daysAmount;
    }

    public boolean isRentable() {
        return rentable;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public double getOccupatedSize() {
        return occupatedSize;
    }

    public ArrayList<Byt> getElements() {
        return elements;
    }

    public void setDaysAmount(int daysAmount) {
        this.daysAmount = daysAmount;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public boolean isContainsVechicle() {
        return containsVechicle;
    }

    public void changeVechicleContainStatus(){
        containsVechicle=!containsVechicle;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }
    public void nextDay(){
        daysAmount--;
        checkoutCheck();
    }
    public String showElements(){
        if (elements.size()!=0) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < elements.size(); i++) {
                str.append((i+1) + " " + elements.get(i).toString() + System.lineSeparator());
            }
            return str.toString();
        }
        return "";
    }


    @Override
    public String toString(){
               return "id: " + id + ((!rentable?" , status:wyłończone z użytku/wynajęte":" , status:wolne")) + " , rozmiar: " + size +", miesca zajęto: "+occupatedSize+((osoba==null?"":" , najemca: "+osoba.getName()+" , z: "+rentDate+" , na: "+daysAmount+" dni" +System.lineSeparator()+ ((elements.size()>0?"\nelementy:\n"+showElements():" "))));

    }
}
