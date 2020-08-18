import java.time.LocalDate;
import java.util.ArrayList;

public class Osoba {
    private String name;
    private String surname;
    private String PESEL;
    private String adres;
    private String birthDate;
    private LocalDate firsRentDate = null;
    private ArrayList<Pomieszczenie> inRent = new ArrayList<>();

    public Osoba(String name, String surname, String pass, String PESEL, String adres, String birthDate) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.adres = adres;
        this.birthDate = birthDate;
        Magazyn.registerOsob.put(pass,this);
    }
    public void rent(Pomieszczenie p,int daysAmount){

        if (p.rentThisRoom(this,daysAmount)){
            inRent.add(p);
            System.out.println("Success");
        }else {
            System.out.println("Failed to rent");
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getAdres() {
        return adres;
    }

    public void setFirsRentDate(LocalDate firsRentDate) {
        this.firsRentDate = firsRentDate;
    }

    public ArrayList<Pomieszczenie> getInRent() {
        return inRent;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getFirsRentDate() throws NeverRentException {
        if (this.firsRentDate==null) {
            throw new NeverRentException();
        }
        else
        return firsRentDate;
    }

    public boolean locateItem(Przedmiot item,Pomieszczenie room){
        if (inRent.contains(room) && !item.getLocated() ){
            try {
                room.add(item);
                item.setLocated();
                return true;
            }catch (TooManyThingsException e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        System.out.println("Ten przedmiot już gdzieś znajduje albo pomieszczenie przepełnione");
        return false;
    }

    public boolean locateItem(Pojazd vechicle,Pomieszczenie room){
        if (!room.isContainsVechicle() && inRent.contains(room) && !vechicle.isLocated()){
            try {
                room.add(vechicle);
                room.changeVechicleContainStatus();
                vechicle.setLocated();
                return true;
            }
            catch (TooManyThingsException e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        System.out.println("w tym pomieszczeniu już znajduje samochód");
        return false;
    }

    public boolean removeItem(Przedmiot item,Pomieszczenie room){
        if (inRent.contains(room) && item.getLocated() && room.remove(item)){
            item.setLocated();
            return true;
        }
        return false;
    }
    public boolean removeItem(Pojazd vechicle,Pomieszczenie room){
        if (inRent.contains(room) && vechicle.isLocated() && room.remove(vechicle)){
            vechicle.setLocated();
            room.changeVechicleContainStatus();
            return true;
        }
        return false;
    }
     public void removeItem(Byt p,Pomieszczenie room){
        if (p instanceof Przedmiot){
            removeItem((Przedmiot)p,room);
        }
        else if (p instanceof Pojazd){
            removeItem((Pojazd)p,room);
        }
        else {
            System.out.println("Wrong parameters");
        }
     }


    public void Unpackitem(Przedmiot item,Pomieszczenie room){
        if (room.getOccupatedSize()+item.getUnpackedSize()<room.getSize()){
            if(item.unpack()) {
                System.out.println("Przedmiot rozpakowany");
            }
        }
        else {
            System.out.println("nie uda się tego zrobić");
        }
    }

    public void PackItem(Przedmiot item,Pomieszczenie room){
        if(room.getElements().contains(item)){
            if (item.packBack()){
                System.out.println("przedmiot złożony");
            }
        }
        else {
            System.out.println("Nie posiada takiego przedmiotu");
        }
    }

    public String getRooms(){
        if (inRent.size()!=0) {
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<inRent.size();i++){
                sb.append((i+1)+". "+inRent.get(i).toString()+System.lineSeparator());
            }
            return sb.toString();
        }
        return "";
    }
    @Override
    public String toString() {
        String sb = getRooms();
        return "Imie = " + name + " " +
                ", Nazwisko = " + surname + " " +
                ", PESEL = " + PESEL + " " +
                ", Adres = " + adres + " " +
                ", Data Urodzenia = " + birthDate + " " +
                ", Pierwszy Wynajem = " + (firsRentDate == null ? "Jeście Nie wynajmował/a" : firsRentDate.toString())+
                (!sb.equals("")?System.lineSeparator()+"Pomieszczenia w wynajmie:"+System.lineSeparator()+sb:"");
    }
}
