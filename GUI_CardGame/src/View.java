import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import java.awt.*;

public class View {
    JFrame mainWindow = new JFrame();
    JFrame gameWindow;
    JFrame resultsWindow = new JFrame();
    MainApplication mp;


    public View(MainApplication mp) {
        this.mp=mp;
        mainWindow.setDefaultCloseOperation(3);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setSize(300,240);
        mainWindow.setLayout(new BorderLayout());




        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header,BoxLayout.Y_AXIS));
        JLabel main = new JLabel("Memory The game");
        JLabel sNumber = new JLabel("s18776",SwingConstants.CENTER);


        main.setFont(new Font("Custom",Font.BOLD,16));
        sNumber.setFont(new Font("sNumber",Font.PLAIN,15));
        main.setMaximumSize(new Dimension(140,60));
        sNumber.setMaximumSize(new Dimension(60,40));
        main.setAlignmentX(Component.CENTER_ALIGNMENT);
        sNumber.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(main);
        header.add(Box.createRigidArea(new Dimension(0,10)));
        header.add(sNumber);
        header.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));

        Button exit = new Button("Exit");

        Button startGame = new Button("New Game");

        Button results = new Button("Results");

        exit.setMaximumSize(new Dimension(70,25));
        startGame.setMaximumSize(new Dimension(70,25));
        results.setMaximumSize(new Dimension(70,25));
        exit.addActionListener((e)->{
            System.exit(0);
        });

        startGame.addActionListener((e)->{
            getGameData();
        });
        results.addActionListener((e)->{
            mp.showResults();
        });
        jp.add(exit);
        jp.add(Box.createRigidArea(new Dimension(0,10)));
        jp.add(startGame);
        jp.add(Box.createRigidArea(new Dimension(0,10)));
        jp.add(results);
        jp.add(Box.createRigidArea(new Dimension(0,10)));

        mainWindow.getContentPane().add(header,BorderLayout.PAGE_START);
        mainWindow.getContentPane().add(jp,BorderLayout.CENTER);
        mainWindow.setVisible(true);

    }
    private void getGameData() {

        mp.playerName = (String) JOptionPane.showInputDialog(mainWindow, "What's your name?");
        if (mp.playerName != null && !mp.playerName.equals("")) {
            SpinnerNumberModel spnm1 = new SpinnerNumberModel(2,2,6,1);
            SpinnerNumberModel spnm2 = new SpinnerNumberModel(2,2,6,1);
            JDialog jd = new JDialog(mainWindow,true);
            jd.setLayout(new BorderLayout());
            JLabel jl1 = new JLabel("rows ");
            JSpinner js = new JSpinner(spnm1);
            JLabel jl2 = new JLabel("columns ");
            JSpinner js2= new JSpinner(spnm2);
            JComponent jc[]={jl1,js,jl2,js2};
           int res= JOptionPane.showConfirmDialog(mainWindow, jc, "My custom dialog", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null);
            if (res != JOptionPane.CANCEL_OPTION &&(int)(js.getValue())*(int)(js2.getValue())%2==0) {
                gameWindow = new JFrame();
                gameWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                gameWindow.setLocationRelativeTo(null);
                closeWindows();
                mp.gameStart(new GridSize((int)(js.getValue()),(int)(js2.getValue())));
                gameWindow.setVisible(true);
                mp.timer.start();
            }
            else if(res!=JOptionPane.CANCEL_OPTION ){
                JOptionPane.showMessageDialog(mainWindow,
                        "Grid size has to be even",
                        "Grid size error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (mp.playerName!=null){
            JOptionPane.showMessageDialog(mainWindow,
                    "Please enter your name",
                    "Name error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }



    public void closeWindows(){
        this.mainWindow.setVisible(false);
        if (gameWindow!=null) {
            this.gameWindow.setVisible(false);
        }
        if (resultsWindow!=null) {
            this.resultsWindow.setVisible(false);
        }
    }
}
