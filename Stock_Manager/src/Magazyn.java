import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Magazyn {
    static ArrayList<Pomieszczenie> roomsRegister = new ArrayList<>();
    static LinkedHashMap<String,Osoba> registerOsob = new LinkedHashMap<>();
    static Scanner programmeInput = new Scanner(System.in);
    static boolean isLoginned = false;
    static private Osoba currentUserid;
    static private String adminPanelKeyword = "admin1111";
    static public String showUsers(){
        if (registerOsob.size()!=0) {
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (String e : registerOsob.keySet()) {
                sb.append(i+" "+registerOsob.get(e).toString());
                sb.append(System.lineSeparator());
                i++;
            }
            return sb.toString();
        }
        return "";
    }

   static public String showRooms(boolean switcher){
        if (!switcher) {
            if (roomsRegister.size() != 0) {
                StringBuilder sb = new StringBuilder();
                for (Pomieszczenie e : roomsRegister) {
                    sb.append(e.toString());
                    sb.append(System.lineSeparator());
                }
                return sb.toString();
            }
            return "";
        }
        else if(switcher){
            if (roomsRegister.size() != 0) {
                StringBuilder sb = new StringBuilder();
                for (Pomieszczenie e : roomsRegister) {
                    if (e.isRentable()) {//pokoje nie wynajęte
                        sb.append(e.toString());
                        sb.append(System.lineSeparator());
                    }
                }
                return sb.toString();
            }
                return "";
        }
       return "";
    }

    static public void programmeStart(){
            int input=0;
            System.out.println("Welcome User choose one option below:");
            while (true) {
                System.out.println(
                        "0.Exit \n1.Login \n2.Create account \n3.Admin mode "
                );
                input = programmeInput.nextInt();
                if (input == 0) {
                    System.exit(0);
                } else if (input == 1) {
                    if (!isLoginned && login()){
                        programmeStart(0);
                    }

                } else if (input == 2) {
                    createAccount();
                }
                else if (input==3){
                    programmeStart(1);
                }
               else {
                    System.out.println("wrong input");
                }
            }
    }

    public static void writeSummary(){
        try {
            FileWriter fw = new FileWriter(new File(System.getProperty("user.dir")+".txt"));
            StringBuilder sb = new StringBuilder();
            sb.append("Users List:"+System.lineSeparator()+showUsers()+System.lineSeparator());
            sb.append("Rooms and items:"+System.lineSeparator()+showRooms(true)+System.lineSeparator());
            fw.write(sb.toString());
            fw.flush();
            fw.close();
        }catch (IOException e){
            System.out.println("Failed to create summary");
        }
    }
    static public int programmeStart(int code){
        if (code==0){
            int input=0;
            while (true) {
                System.out.println("0.Logout \n1.Show My Rooms \n2.Rent a room \n3.Add item \n4.Remove item \n5.Your profile");
                input=programmeInput.nextInt();
                if (input==0){
                    isLoginned=false;
                    currentUserid=null;
                    return 0;
                }
                else if(input == 1){
                    String rooms = currentUserid.getRooms();
                    if (!rooms.equals(""))
                    System.out.println(rooms);
                    else
                        System.out.println("you don't rent any rooms");
                }
                else if (input==2){
                    String rooms = showRooms(true);
                    if(!rooms.equals("")){
                        System.out.println(rooms);
                    System.out.println("Choose room to rent (enter id)");
                    int id = programmeInput.nextInt();
                    while(id<0 || id>roomsRegister.size()-1){
                        System.out.println("Enter another id");
                        id = programmeInput.nextInt();
                    }
                    System.out.println("Days amount");
                    int days = programmeInput.nextInt();
                    while (days<1){
                        System.out.println("you can't rent a room less than 1 day");
                        days=programmeInput.nextInt();
                    }
                    currentUserid.rent(roomsRegister.get(id),days);
                    }else {
                        System.out.println("No rooms for rent");
                    }
                }
                else if (input==3){
                    if (!currentUserid.getRooms().equals("")) {
                        System.out.println("which type of item you want to add");
                        System.out.println("0.Exit to user menu \n1.Ordinary item \n2.Car \n3.Bike \n4.Bicycle");
                        int chooser = programmeInput.nextInt();
                        if (chooser == 0) {

                        }
                        else if(chooser==1){
                            System.out.println("enter an item name");
                            String name=getName();
                            System.out.println("in which system you want to pass size \n1.Ordinzry size \n2. Length*Width*Height");
                            int sizetype=programmeInput.nextInt();
                            if (sizetype==1){
                                System.out.println("enter size");
                                double size=getDoubleValue();
                                System.out.println("If you can unpack item enter 1 else 2");
                                int type =programmeInput.nextInt();
                                if (type==1){
                                    System.out.println("enter unpacked item size");
                                    double unpackedSize = programmeInput.nextDouble();
                                    while (unpackedSize < size) {
                                        System.out.println("unpacked size can't be less than item size");
                                        unpackedSize = programmeInput.nextDouble();
                                        }
                                    Przedmiot createditem = new  Przedmiot(name,size,unpackedSize);
                                    addItem(createditem);
                                }
                                else if (type==2){
                                    Przedmiot createditem = new  Przedmiot(name,size);
                                    addItem(createditem);
                                }
                                else {
                                    System.out.println("wrong input");
                                }
                            }
                            else if (sizetype==2){
                                double [] LWH = getLWH();
                                System.out.println("If you can unpack item enter 1 else 2");
                                int type=programmeInput.nextInt();
                                if (type==1){
                                    System.out.println("enter unpacked item size");
                                    double  unpackedSize = programmeInput.nextDouble();
                                    while (unpackedSize < (LWH[0] * LWH[1] * LWH[2])) {
                                        System.out.println("unpacked size can't be less than item size");
                                        unpackedSize = programmeInput.nextDouble();
                                    }
                                    Przedmiot createditem = new Przedmiot(name,LWH[0],LWH[1],LWH[2],unpackedSize);
                                    addItem(createditem);
                                }
                                else if(type==2){
                                    Przedmiot createditem = new Przedmiot(name,LWH[0],LWH[1],LWH[2]);
                                    addItem(createditem);
                                }
                                else {
                                    System.out.println("Wrong input");
                                }
                            }
                            else {
                                System.out.println("wrong input try again next time");
                            }
                        }

                        else if (chooser==2){
                            System.out.println("Enter a car name or model");
                            String name = getName();
                            System.out.println("enter a car type \n1.Gas (enter g)\n2.Petrol (enter b)\n3.Hibryd (enter h)");
                            String carType = programmeInput.next();
                            while (!carType.equals("g") && !carType.equals("b") && !carType.equals("h")){
                                System.out.println("you entered wrong value , try again");
                                carType=programmeInput.next();
                            }
                            System.out.println("enter engine capacity");
                            int engineCapacity = programmeInput.nextInt();
                            while (engineCapacity<1){
                                System.out.println("engineCapacity can't be less than 1");
                                engineCapacity=programmeInput.nextInt();
                            }
                            System.out.println("in which system you want to pass size \n1.Ordinzry size \n2. Length*Width*Height");
                            int sizetype=programmeInput.nextInt();
                            if (sizetype==1){
                                System.out.println("enter size");
                                double size=getDoubleValue();
                                Samochod createdVechicle= new Samochod(name,size,engineCapacity,carType);
                                addItem(createdVechicle);
                            }
                            else if (sizetype==2){
                                double [] LWH = getLWH();
                                Samochod createdVechicle = new Samochod(name,LWH[0],LWH[1],LWH[2],engineCapacity,carType);
                            }
                            else {
                                System.out.println("wrong input try again");
                            }
                        }
                        else if (chooser==3){
                            System.out.println("Enter a bike name or model");
                            String name = getName();
                            System.out.println("Enter a gear number");
                            int gearNumber = programmeInput.nextInt();
                            while (gearNumber<1){
                                System.out.println("gear number can't be less than 1");
                                gearNumber=programmeInput.nextInt();
                            }
                            System.out.println("Enter a damper number");
                            int damperNumber = programmeInput.nextInt();
                            while (damperNumber<0){
                                System.out.println("damper number can't be less than 0");
                                damperNumber=programmeInput.nextInt();
                            }
                            System.out.println("which brake type does your bike has \n type true if brake caliper and false if disc brake");
                            boolean brakeType =getBooleanValue();
                            System.out.println("in which system you want to pass size \n1.Ordinzry size \n2. Length*Width*Height");
                            int sizetype=programmeInput.nextInt();
                            if (sizetype==1){
                                System.out.println("enter size");
                                double size=getDoubleValue();
                                Rower createdVechicle = new Rower(name,size,gearNumber,brakeType,damperNumber);
                                addItem(createdVechicle);
                            }
                            else if(sizetype==2){
                                double [] LWH = getLWH();
                                Rower createdVechicle = new Rower(name,LWH[0],LWH[1],LWH[2],gearNumber,brakeType,damperNumber);
                                addItem(createdVechicle);
                            }
                            else {
                                System.out.println("wrong input try again next time");
                            }

                        }
                        else if (chooser==4){
                            System.out.println("enter bicycle name or model");
                            String name = getName();
                            System.out.println(" enter true if your bicycle has homologacja or false?");
                            boolean homologacja = getBooleanValue();
                            System.out.println("in which system you want to pass size \n1.Ordinzry size \n2. Length*Width*Height");
                            int sizetype=programmeInput.nextInt();
                            if (sizetype == 1){
                                System.out.println("enter size");
                                double size=getDoubleValue();
                                Motocykl createdVechicle = new Motocykl(name,size,homologacja);
                                addItem(createdVechicle);
                            }
                            else if (sizetype==2){
                                double [] LWH = getLWH();
                                Motocykl createdVechicle = new Motocykl(name,LWH[0],LWH[1],LWH[2],homologacja);
                                addItem(createdVechicle);
                            }
                            else {
                                System.out.println("wrong input");
                            }
                        }
                        else {
                            System.out.println("wrong input try again");
                        }
                    }
                    else {
                        System.out.println("You don't rent any rooms");
                    }
                }
                else if (input==4){
                    String text = currentUserid.getRooms();
                    if (!text.equals("")) {
                        System.out.println(text);
                        System.out.println("type number(not id) of room");
                        int roomid = programmeInput.nextInt();
                        System.out.println("type number(not id) of item");
                        int itemid = programmeInput.nextInt();
                        if (roomid>0 && currentUserid.getInRent().size()>=roomid){
                           if (itemid>0 && currentUserid.getInRent().get(roomid-1).getElements().size()>=itemid){
                                currentUserid.removeItem(currentUserid.getInRent().get(roomid-1).getElements().get(itemid-1),currentUserid.getInRent().get(roomid-1));
                           }else {
                               System.out.println("wrong item number");
                           }
                        }
                        else {
                            System.out.println("Wrong room number ");
                        }
                    }
                    else {
                        System.out.println("you don't rent any rooms");
                    }
                }
                else if(input==5){
                    System.out.println(currentUserid.toString());
                }
                else {
                    System.out.println("wrong input");
                }
            }
        }
        else if (code==1){
            System.out.println("enter admin panel password");
            String pass = programmeInput.next();
            if (!pass.equals(adminPanelKeyword)){
                System.out.println("wrong password");
                return 0;
            }
            System.out.println("Welcome to admin panel");
            int input = 0;
            while (true){
                System.out.println("Choose any option \n0.Exit \n1.Show all users \n2.Show all rooms \n3.Write down summary \n4.Choose user");
                input=programmeInput.nextInt();
                if (input==0){
                    return 0;
                }
                else if (input==1){
                    System.out.println(showUsers());
                }
                else if (input==2){
                    System.out.println(showRooms(false));
                }
                else if (input == 3) {
                    writeSummary();
                }
                else if (input ==4){
                    System.out.println(showUsers());
                    System.out.println("enter user number");
                    int userNumber = programmeInput.nextInt();
                    if (userNumber<0 || userNumber>registerOsob.size()){
                        System.out.println("wrong user number");
                    }else {
                        int i = 1;
                        for (String key:registerOsob.keySet()){
                            if (i==userNumber){
                                currentUserid=registerOsob.get(key);
                                isLoginned=true;
                                programmeStart(0);
                                break;
                            }
                            i++;
                        }
                    }
                }
                else {
                    System.out.println("Wrong input");
                }
            }
        }
        return 0;
    }
    static public void addItem(Przedmiot createditem){
        System.out.println("where you wan't to place this item , enter room number(not id)");
        System.out.println(currentUserid.getRooms());
        int roomnumber = programmeInput.nextInt();
        while (roomnumber!=0){
            if (roomnumber>currentUserid.getInRent().size() || roomnumber<1){
                System.out.println("write correct number or choose another or ennter 0 to end");
                roomnumber=programmeInput.nextInt();
            }
            else {
                if(currentUserid.locateItem(createditem,currentUserid.getInRent().get(roomnumber-1)))
                    roomnumber = 0;
                else {
                    System.out.println("choose another room");
                    roomnumber=programmeInput.nextInt();
                }
            }
        }
    }

    static public void addItem(Pojazd createditem){
        System.out.println("where you wan't to place this item , enter room number(not id)");
        System.out.println(currentUserid.getRooms());
        int roomnumber = programmeInput.nextInt();
        while (roomnumber!=0){
            if (roomnumber>currentUserid.getInRent().size() || roomnumber<1){
                System.out.println("write correct number or choose another one or enter 0 to end");
                roomnumber=programmeInput.nextInt();
            }
            else if (currentUserid.getInRent().get(roomnumber-1).isContainsVechicle()){
                System.out.println("that room is already contains vechicle choose another one or ennter 0 to end");
                roomnumber=programmeInput.nextInt();
            }
            else {
                if(currentUserid.locateItem(createditem,currentUserid.getInRent().get(roomnumber-1)))
                        roomnumber = 0;
                else {
                    System.out.println("choose another room");
                    roomnumber=programmeInput.nextInt();
                }
            }
        }
    }

    static public void createAccount(){
        String data[]=new String[6];
        System.out.println("type your name");
        data[0]=getName();
        System.out.println("type your surname");
        data[1]=getName();
        System.out.println("type your password");
        data[2]=programmeInput.next();
        while (data[2].equals("")|| registerOsob.containsKey(data[2]) || data[2].equals(adminPanelKeyword)){
            System.out.println("you can't pass empty password or that password is already taken");
            data[2]=programmeInput.next();
        }
        System.out.println("type your PESEL");
        data[3]=programmeInput.next();
        System.out.println("type your adress");
        data[4]=programmeInput.next();
        System.out.println("type your birth date dd.mm.yyyy");
        data[5]=programmeInput.next();
        new Osoba(data[0],data[1],data[2],data[3],data[4],data[5]);
        System.out.println("You have succesfuly created account");
    }

    static public boolean login(){
        String logininfo[] = new String[3];
        System.out.println("enter your name");
        logininfo[0]=programmeInput.next();
        System.out.println("enter your surname");
        logininfo[1]=programmeInput.next();
        System.out.println("enter your password");
        logininfo[2]=programmeInput.next();

        if (registerOsob.containsKey(logininfo[2]) && !adminPanelKeyword.equals(logininfo[2])){
            if (registerOsob.get(logininfo[2]).getName().equals(logininfo[0]) && registerOsob.get(logininfo[2]).getSurname().equals(logininfo[1])){
                isLoginned=true;
                currentUserid=registerOsob.get(logininfo[2]);
                return true;
            }
        }
        System.out.println("Failed to login , try again");
        return false;
    }
    private static double[] getLWH(){
        double [] LWH = new double[3];
        System.out.println("enter length");
        LWH[0]=getDoubleValue();
        System.out.println("enter width");
        LWH[1]=getDoubleValue();
        System.out.println("enter height");
        LWH[2]=getDoubleValue();
        return LWH;
    }

    private static String getName(){
        String name = programmeInput.next();
        while(name.equals("")){
            System.out.println("field name can't be empty");
            name=programmeInput.next();
        }
        return name;
    }

    private  static double getDoubleValue(){
        double value = programmeInput.nextDouble();
        while (value<1){
            System.out.println("that field can't be less than 1");
            value=programmeInput.nextDouble();
        }
        return value;
    }

    private static  boolean getBooleanValue(){
        String value = programmeInput.next();
        while (!value.equals("true")&&!value.equals("false")){
            System.out.println("enter true or false");
            value=programmeInput.next();
        }
        boolean res = value.equals("true")?true:false;
        return  res;
    }

    public static void nextDay(){
        for (Pomieszczenie p:roomsRegister){
            p.nextDay();
        }
    }


}
