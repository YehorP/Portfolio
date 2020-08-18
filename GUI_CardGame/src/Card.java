import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;

public class Card extends JPanel {
    StringBuilder definedImage = new StringBuilder();
    ImageIcon defaultBackgroundPath;
    ImageIcon cardImage;
    ImageIcon background;
    boolean state = false;
    boolean clicked = false;

    public Card(String cardImage) {
        definedImage.append(cardImage);
       background = new ImageIcon(System.getProperty("user.dir")+"/photos/background.jpg");
       defaultBackgroundPath=new ImageIcon(background.getImage().getScaledInstance(80,100,Image.SCALE_SMOOTH));
       background = new ImageIcon(cardImage);
       this.cardImage= new ImageIcon(background.getImage().getScaledInstance(80,100,Image.SCALE_SMOOTH));
       background=defaultBackgroundPath;
    }

    public Card(String cardImage, MouseAdapter ms) {
       this(cardImage);
       this.addMouseListener(ms);
    }

    @Override


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.removeAll();
        background.paintIcon(this,g,0,0);
        this.repaint();
    }

    public void changeImage(){
        background = (state && !clicked)? defaultBackgroundPath : cardImage;
        state = !state;
        clicked=true;
    }

}
