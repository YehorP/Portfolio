import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Model {
    ArrayList<String> photos = new ArrayList<>();
    MainApplication mp;

    public Model(MainApplication mp,int photonum) {
        this.mp=mp;
        StringBuilder sb = new StringBuilder();
        for (int i=1;i<=photonum;i++){
            sb.setLength(0);
            sb.append(System.getProperty("user.dir"));
            sb.append("/photos/photo");
            sb.append(i);
            sb.append(".jpg");
            photos.add(sb.toString());
            shuffle();
        }
    }

    public String[] getCards(int size){
        shuffle();
        String res[] = new String[size];
        for (int i=0;i<res.length;i++){
            res[i]=photos.get(i);
        }


        return  res;
    }

    public void shuffle(){
        for (int i=1;i<photos.size();i++){
            Collections.swap(photos,(int)(Math.random()*(i-1)),i);
        }
    }
}

