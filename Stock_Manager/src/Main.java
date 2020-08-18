import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here


Osoba os = new Osoba("John","White","1234567","1234567890112","Aleje Jerozalimskie 48","28.01.1981");
Osoba os2 = new Osoba("Alex","Lol","467890","1234567890112","Koszykowa 48","28.01.1991");
Osoba os3 = new Osoba("Daniel","Swim","346789","2872376237993","Koszykowa 34","29.08.1995");
Osoba os4 = new Osoba("Tonny","Gansales","89342","878989012993","Rondo ONZ","29.08.1963");
Osoba os5 = new Osoba("Daniel","Swim","11002344","517632756217","Sasanki","29.08.1974");
for (int i=0;i<15;i++){
    new Pomieszczenie(Math.round(Math.random()*200)+20);
}


Magazyn.programmeStart();
    }
}
