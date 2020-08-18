package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.awt.desktop.AppForegroundListener;
import java.beans.EventHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;


public class GameWindow extends Group {
    int currentPosition=0;
    double sceneWidth,sceneHeight;
    ImageView [] chickens=new ImageView[4];
    ImageView [] eggs = new ImageView[4];
    ImageView woolfs[] = new ImageView[5];
    Line lines[] = new Line[8];
    ArrayList<Integer>avaible=new ArrayList<>();
    Label eggResult=new Label();
    File writeResults;
    VBox results;
    Timer timer;
    Label loseAttempts;
    int counter=0;
    boolean endGame=false;
    int hp=3;
    int eggsCatched=0;
    String username;
    PathTransition ptr;
    public GameWindow(String username, double sceneWidth, double sceneHeight) {
        this.sceneHeight=sceneHeight;
        this.sceneWidth=sceneWidth;
        try {
            writeResults = new File(System.getProperty("user.dir")+"/result.txt");
            if (!writeResults.exists()) {
                try {
                    writeResults.createNewFile();
                } catch (Exception e) {

                }
            }
            this.username=username;
            hp=3;
            eggsCatched=0;
            avaible.add(0);
            avaible.add(1);
            avaible.add(2);
            avaible.add(3);

            chickens[0] = new ImageView(new Image(new FileInputStream(System.getProperty("user.dir") + "/images/ChickenLeft.png")));//right top chicken
            chickens[1] = new ImageView(new Image(new FileInputStream(System.getProperty("user.dir") + "/images/ChickenRight.png")));//left top chicken
            chickens[2] = new ImageView(new Image(new FileInputStream(System.getProperty("user.dir") + "/images/ChickenLeft.png")));//left bottom chicken
            chickens[3] = new ImageView(new Image(new FileInputStream(System.getProperty("user.dir") + "/images/ChickenRight.png")));//right bottom chicken
            woolfs[0] = new ImageView(new Image(new FileInputStream(System.getProperty("user.dir") + "/images/woolfRightBottom-removebg.png")));//default woolf
            woolfs[1] = new ImageView(new Image(new FileInputStream(System.getProperty("user.dir") + "/images/woolfRightTop-removebg.png")));// right top
            woolfs[2] = new ImageView(new Image(new FileInputStream(System.getProperty("user.dir") + "/images/woolfLeftTop__2_-removebg.png")));//left top
            woolfs[4] = new ImageView(new Image(new FileInputStream(System.getProperty("user.dir") + "/images/woolfLeftBottom-removebg.png")));//left bottom
            woolfs[3] = new ImageView(new Image(new FileInputStream(System.getProperty("user.dir")
                    + "/images/woolfRightBottom-removebg.png")));//right bottom
            ImageView background = new ImageView(new Image(new FileInputStream(System.getProperty("user.dir") + "/images/background.jpg")));
            background.setFitHeight(sceneHeight);
            background.setFitWidth(sceneWidth);
            this.getChildren().add(background);

            for (int i = 0; i < eggs.length; i++) {
                eggs[i] = new ImageView(new Image(new FileInputStream(System.getProperty("user.dir") + "/images/egg.png")));
                eggs[i].setFitWidth(sceneWidth / 16);
                eggs[i].setFitHeight(sceneHeight / 20);
                eggs[i].setVisible(false);
            }
            for (ImageView chicken : chickens) {
                chicken.setFitHeight(sceneHeight / 6);
                chicken.setFitWidth(sceneWidth / 6);
            }
            for (ImageView woolf : woolfs) {
                woolf.setFitWidth(sceneWidth / 5);
                woolf.setFitHeight(sceneHeight / 5);
                woolf.setVisible(false);
            }


            woolfs[0].setVisible(true);
            woolfs[0].setLayoutX(sceneWidth / 2 - (woolfs[0].getFitHeight() / 3));
            woolfs[0].setLayoutY(sceneHeight - woolfs[0].getFitHeight());

            chickens[0].setLayoutY(sceneHeight / 10);
            chickens[0].setLayoutX(sceneWidth - chickens[0].getFitWidth());

            lines[0] = new Line(chickens[0].getLayoutX(), chickens[0].getLayoutY() + chickens[0].getFitHeight(), chickens[0].getLayoutX() + chickens[0].getFitWidth(), chickens[0].getLayoutY() + chickens[0].getFitHeight());

            lines[1] = new Line(lines[0].getEndX(), lines[0].getEndY(), lines[0].getStartX(), chickens[0].getLayoutY() + chickens[0].getFitHeight() * 1.75);


            eggs[0].setLayoutX(chickens[0].getLayoutX() + chickens[0].getFitWidth() / 2);
            eggs[0].setLayoutY(chickens[0].getLayoutY() + chickens[0].getFitHeight() * 0.95);


            chickens[1].setLayoutY(sceneHeight / 10);
            chickens[1].setLayoutX(-1);
            eggs[1].setLayoutX(chickens[1].getLayoutX() + chickens[1].getFitWidth() / 9);
            eggs[1].setLayoutY(chickens[1].getLayoutY() + chickens[1].getFitHeight() * 0.95);

            lines[2] = new Line(chickens[1].getLayoutX(), chickens[1].getLayoutY() + chickens[1].getFitHeight(), chickens[1].getLayoutX() + chickens[1].getFitWidth(), chickens[1].getLayoutY() + chickens[1].getFitHeight());

            lines[3] = new Line(lines[2].getStartX(), lines[2].getStartY(), lines[2].getEndX(), chickens[1].getLayoutY() + chickens[1].getFitHeight() * 1.75);


            chickens[2].setLayoutY(sceneHeight / 2.2);
            chickens[2].setLayoutX(sceneWidth - chickens[2].getFitWidth());
            eggs[2].setLayoutX(chickens[2].getLayoutX() + chickens[2].getFitWidth() / 2);
            eggs[2].setLayoutY(chickens[2].getLayoutY() + chickens[2].getFitHeight() * 0.95);
            lines[4] = new Line(chickens[2].getLayoutX(), chickens[2].getLayoutY() + chickens[2].getFitHeight(), chickens[2].getLayoutX() + chickens[2].getFitWidth(), chickens[2].getLayoutY() + chickens[2].getFitHeight());

            lines[5] = new Line(lines[4].getEndX(), lines[4].getEndY(), lines[4].getStartX(), chickens[2].getLayoutY() + chickens[2].getFitHeight() * 1.75);


            chickens[3].setLayoutY(sceneHeight / 2.2);
            chickens[3].setLayoutX(-1);
            eggs[3].setLayoutX(chickens[3].getLayoutX() + chickens[3].getFitWidth() / 9);
            eggs[3].setLayoutY(chickens[3].getLayoutY() + chickens[3].getFitHeight() * 0.95);

            lines[6] = new Line(chickens[3].getLayoutX(), chickens[3].getLayoutY() + chickens[3].getFitHeight(), chickens[3].getLayoutX() + chickens[3].getFitWidth(), chickens[3].getLayoutY() + chickens[3].getFitHeight());

            lines[7] = new Line(lines[6].getStartX(), lines[6].getStartY(), lines[6].getEndX(), chickens[3].getLayoutY() + chickens[3].getFitHeight() * 1.75);

            woolfs[1].setLayoutY(chickens[0].getLayoutY() + chickens[0].getFitHeight() * 1.25);
            woolfs[1].setLayoutX(chickens[0].getLayoutX() - chickens[0].getFitWidth() * 1.25);

            woolfs[2].setLayoutY(chickens[1].getLayoutY() + chickens[1].getFitHeight() * 1.25);
            woolfs[2].setLayoutX(chickens[1].getLayoutX() + chickens[1].getFitWidth() * 1);

            woolfs[3].setLayoutY(chickens[2].getLayoutY() + chickens[2].getFitHeight() * 1);
            woolfs[3].setLayoutX(chickens[2].getLayoutX() - chickens[2].getFitWidth() * 1.25);

            woolfs[4].setLayoutY(chickens[3].getLayoutY() + chickens[3].getFitHeight() * 1);
            woolfs[4].setLayoutX(chickens[3].getLayoutX() + chickens[3].getFitWidth() * 1);

            for (int i = 0; i < lines.length; i++) {
                lines[i].setStroke(Color.BROWN);
                lines[i].setStrokeWidth(2);
                this.getChildren().add(lines[i]);
            }

            this.getChildren().addAll(eggs[0], eggs[1], eggs[2], eggs[3], chickens[0], chickens[1],chickens[2], chickens[3], woolfs[0], woolfs[1], woolfs[2], woolfs[3], woolfs[4]);
            results=new VBox();
            results.getChildren().add(new Label("Your Score is"));
            eggResult=new Label("");
            results.getChildren().add(eggResult);
            eggResult.setTextAlignment(TextAlignment.CENTER);
            results.getChildren().add(new Label("Press Ctrl+Z to exit"));
            results.setSpacing(5);
           results.setLayoutY(Math.round(sceneHeight/3));
           results.setLayoutX(Math.round(sceneWidth/2.5));
           results.setStyle("-fx-background-color: white");
           results.setPadding(new Insets(20,20,20,20));
            loseAttempts = new Label("Attempts:3");
            loseAttempts.setLayoutX(sceneWidth/2);
            loseAttempts.setFont(new Font(14));
            loseAttempts.setStyle("-fx-background-color:white;");
            this.getChildren().add(loseAttempts);
            this.getChildren().add(results);
            results.setVisible(false);
            Collections.shuffle(avaible);
            timer=new Timer();
            timer.start();
          new Thread(()->{
              while (!endGame) {
                  if (avaible.size() != 0) {
                      if (counter == 3) {
                          Collections.shuffle(avaible);
                          counter = 0;
                      }
                      spownEgg(avaible.get(0));
                      avaible.remove(0);
                      counter++;
                      }
                  try {
                      Thread.sleep(2000);
                  } catch (Exception e) {

                  }
              }
          }).start();

        }
        catch (Exception e) {
            System.out.println("kek");
        }
    }
    private void hideWoolfs(){
        for (ImageView woolf:woolfs){
            woolf.setVisible(false);
        }
    }
    private void hideEggs(){
        for (ImageView egg:eggs){
            egg.setVisible(false);
        }
    }
    public void spownEgg(int index) {
        RotateTransition rt1;
        TranslateTransition trtr;
        ParallelTransition prt;

        double time=(Math.random()*8000)+3000;
        if (index%2==0){
            eggs[index].setVisible(true);
            rt1 = new RotateTransition(Duration.millis(time),eggs[index]);
            rt1.setByAngle(-360);
            rt1.setAutoReverse(true);
            trtr = new TranslateTransition(Duration.millis(time),eggs[index]);
            trtr.setToY(woolfs[1].getLayoutY() - woolfs[1].getFitHeight());
            trtr.setToX(-(lines[1].getEndX() / 6));
            trtr.setAutoReverse(true);
            prt = new ParallelTransition(eggs[index], rt1, trtr);
        }
        else {
                 eggs[index].setVisible(true);
                rt1 = new RotateTransition(Duration.millis(time), eggs[index]);
                rt1.setByAngle(360);
                rt1.setAutoReverse(true);
                trtr = new TranslateTransition(Duration.millis(time), eggs[index]);
                trtr.setToY(woolfs[1].getLayoutY() - woolfs[1].getFitHeight());
                trtr.setToX(+(lines[1].getStartX() / 7));
                trtr.setAutoReverse(true);
                prt = new ParallelTransition(eggs[index], rt1, trtr);
        }
        prt.setCycleCount(2);
        prt.setAutoReverse(true);
        prt.setOnFinished(event->{
            avaible.add(index);
        });
        if (!endGame) {
            prt.play();
            new Thread(() -> {
                try {
                    Thread.sleep((long) (rt1.getDuration().toSeconds() * 1000)-700);
                    eggs[index].setVisible(false);
                    if (currentPosition != 0 && currentPosition - 1 == index) {
                       eggsCatched+=1;
                    } else {
                        hp -= 1;
                        Platform.runLater(() -> {
                            loseAttempts.setText("Attempts:"+hp);
                        });
                        if (hp == 0) {
                            endGame = true;
                                timer.changeIngame();
                                hideAll();
                                loseAttempts.setVisible(false);
                                Platform.runLater(() -> {
                                    eggResult.setText(eggsCatched + " in " + timer.getTime());
                                });
                                results.setVisible(true);
                        }
                    }


                } catch (Exception e) {
                }
            }).start();
        }
    }

    private void hideAll(){
        for (Line line:lines){
            line.setVisible(false);
        }
        for (int i=0;i<eggs.length;i++){
            eggs[i].setVisible(false);
            chickens[i].setVisible(false);
        }
        woolfs[currentPosition].setVisible(false);
    }
    public void getKey(KeyCode key) {
        if (!endGame) {
            hideWoolfs();
            switch (key.getChar()) {
                case "Q":
                    woolfs[2].setVisible(true);
                    currentPosition = 2;
                    break;
                case "E":
                    woolfs[1].setVisible(true);
                    currentPosition = 1;
                    break;
                case "A":
                    woolfs[4].setVisible(true);
                    currentPosition = 4;
                    break;
                case "D":
                    woolfs[3].setVisible(true);
                    currentPosition = 3;
                    break;
                case "S":
                    woolfs[0].setVisible(true);
                    currentPosition = 0;
                    break;
                default:
                    woolfs[currentPosition].setVisible(true);
            }
        }
    }
    public void writeData(){
        try {
            timer.changeIngame();
            endGame=true;
            FileWriter fw = new FileWriter(writeResults,true);
            fw.write("username: "+username+" ,score: "+eggsCatched+" ,time: "+timer.getTime()+System.lineSeparator());
            fw.flush();
            fw.close();
        }catch (Exception e){

        }
    }

}
