import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class MainApplication {
    Model model;
    View view;
    Card cards[] = new Card[2];
    int counter = 0;
    int pairs = 0;
    String playerName;
    String sizeOfGrid;
    boolean disabled=false;
    Timer timer;
    File results;

    public MainApplication() {
        model=new Model(this,18);
        view= new View(this);
        results = new File(System.getProperty("user.dir")+"/result.txt");
        if (!results.exists()) {
            try {
                results.createNewFile();
            } catch (Exception e) {

            }
        }
    }



    public void gameStart(GridSize size){
    sizeOfGrid=size.toString();
        MouseAdapter ms = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Card crd = (Card) (e.getSource());
                if (!disabled) {
                    chooseCard(crd);
                }
            }
        };
        counter=0;
        pairs=(size.elementAmount())/2;
        String [] images = model.getCards(size.elementAmount()/2);
        Card cardList [] = new Card[size.elementAmount()];
        int imgId=0;
        Card tmp;
        int random=0;

        if (size.elementAmount()<=8){
                for (int i=0;i<cardList.length;i+=2){
                    cardList[i] = new Card(images[imgId], ms);
                    cardList[i + 1] = new Card(images[imgId], ms);
                    imgId++;
                }
            for (int i=1;i<cardList.length;i++){
                random = (int)(Math.random() * (i - 1));
                tmp = cardList[i];
                cardList[i] = cardList[random];
                cardList[random]=tmp;
            }
        }else {
            for (int i = 0; i < cardList.length; i += 2) {
                cardList[i] = new Card(images[imgId], ms);
                cardList[i + 1] = new Card(images[imgId], ms);
                random = i != 0 ? (int) (Math.random() * (i - 1)) : (int) (Math.random() * (i));
                tmp = cardList[i];
                cardList[i] = cardList[random];
                cardList[random]=tmp;
                imgId++;
            }
        }

        JPanel jp = new JPanel();
        GridLayout gridLayout = new GridLayout(size.rows,size.columns);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        jp.setLayout(gridLayout);
        jp.setPreferredSize(new Dimension(size.columns*(80+(5*size.columns)),size.rows*(100+size.rows*5)));

        for (int i=0;i<cardList.length;i++){
            jp.add(cardList[i]);
        }
        JLabel timerLabel = new JLabel("00:00:00");
        timer=new Timer(timerLabel);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        view.gameWindow.add(timerLabel,BorderLayout.PAGE_START);
        view.gameWindow.add(jp,BorderLayout.CENTER);

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.changeIngame();
                view.closeWindows();
                view.gameWindow=null;
                view.mainWindow.setVisible(true);
            }
        };
        KeyStroke keyStroke = KeyStroke.getKeyStroke("ctrl Z");
        JRootPane jrt = view.gameWindow.getRootPane();
        jrt.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke,"exitToMainMenu");
        jrt.getActionMap().put("exitToMainMenu",action);
        view.gameWindow.pack();
    }


    public void showResults(){
        view.closeWindows();
        view.resultsWindow=new JFrame();
        view.resultsWindow.setLocationRelativeTo(null);
        view.resultsWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        view.resultsWindow.setLayout(new BorderLayout());
        view.resultsWindow.getContentPane().add(createExit(),BorderLayout.PAGE_START);

        if (results.exists()&& results.length()!=0){
            ResultsModel resultsModel = new ResultsModel();
            JList resList = new JList();
            try {
                Scanner scanner = new Scanner(results);
                while (scanner.hasNextLine()){
                    resultsModel.add(scanner.nextLine());
                }
                scanner.close();
            }catch (Exception e){

            }
            resList.setModel(resultsModel);
            JScrollPane scrl =new JScrollPane(resList);
            scrl.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            view.resultsWindow.add(scrl,BorderLayout.CENTER);
        }
        else {
            JLabel jl = new JLabel("No results to show");
            jl.setFont(new Font("Font2",Font.BOLD,16));
            view.resultsWindow.add(jl,BorderLayout.CENTER);
        }
        view.resultsWindow.pack();
        view.resultsWindow.setVisible(true);
    }



    public void chooseCard(Card card){

            if (cards[0] == null) {
                card.changeImage();
                cards[0] = card;

            } else if (cards[1] == null && cards[0].hashCode() != card.hashCode()) {
                card.changeImage();
                cards[1] = card;
                if (cards[0].definedImage.toString().equals(cards[1].definedImage.toString())) {
                    counter++;
                    if (counter == pairs) {
                        timer.changeIngame();
                        try {
                            FileWriter fw = new FileWriter(results,true);
                            fw.write("player name: "+playerName+" "+" size: "+sizeOfGrid+" time: "+timer.getTime()+System.lineSeparator());
                            fw.close();
                        }catch (Exception e){

                        }

                        SwingUtilities.invokeLater(()->{
                            view.closeWindows();
                            view.mainWindow.setVisible(true);
                        });

                    }
                    cards = new Card[2];

                }
                else {
                    ChangeCardsThred th = new ChangeCardsThred(this);
                    th.start();
                }
            }
        }


    private  JPanel createExit() {
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        Button exit = new Button("<- Return");
        exit.addActionListener((e)->{
            view.closeWindows();
            view.mainWindow.setVisible(true);
        });
        jp.add(exit,BorderLayout.LINE_START);
        return  jp;
    }
}
