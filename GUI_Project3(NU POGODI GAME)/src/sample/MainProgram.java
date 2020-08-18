package sample;
import java.io.File;
import java.io.FileInputStream;
import java.security.spec.ECField;
import java.util.*;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.xml.transform.Result;

public class MainProgram extends Application {
   Scene menu=createMenu();
    String username;
    int hp;
     GameWindow gm;
     Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;
        primaryStage.setScene(menu);
        primaryStage.show();
    }

    public  Scene createMenu(){

        VBox vb = new VBox();
        try {
            ImageView background=new  ImageView(new Image(new FileInputStream(System.getProperty("user.dir") + "/images/game_name.png")));
            background.setFitWidth(200);
            background.setFitHeight(100);
            vb.getChildren().add(background);

        }catch (Exception e){}
        Label lb1=new Label("s18776");
        lb1.setFont(new Font("abc",15));
        Button exit=new Button("Exit");
        Button newGame=new Button("New Game");
        Button scores=new Button("Scores");
        exit.setMaxSize(100,40);
        newGame.setMaxSize(100,40);
        scores.setMaxSize(100,40);
        vb.getChildren().addAll(lb1,exit,newGame,scores);
        vb.setSpacing(15);
        vb.setAlignment(Pos.CENTER);
        exit.setOnAction(event->{
            System.exit(0);
        });
        newGame.setOnAction(event->{
           Dialog<Results> dialog=new Dialog<>();
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            TextField username=new TextField();
            TextField width = new TextField();
            TextField height = new TextField();
            VBox dvp = new VBox();

            dvp.getChildren().addAll(new Label("username"),username,new Label("width"),width,new Label("height"),height);
            dialogPane.setContent(dvp);
            dialog.setResultConverter((ButtonType button)->{
                if (button==ButtonType.OK){
                    return new Results(username.getText(),width.getText(),height.getText());
                }
                    return null;
            });
            Alert alert;
                Optional res=dialog.showAndWait();

                if (!res.isEmpty()){
                    try {
                        Results data = (Results)res.get();
                        double windowWidth= Double.parseDouble(data.width);
                        double windowHeight = Double.parseDouble(data.height);
                        if (windowWidth>=300 && windowHeight>=300 && !data.name.isEmpty()) {

                            window.setScene(createGame(((Results) res.get()).name, windowWidth, windowHeight));

                        }
                        else {
                            alert=new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("width and height have to be more than 299 and name hasn't to be empty");
                            alert.showAndWait();
                        }
                    }catch (Exception e){
                        alert=new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("You entered wrong size ");
                        alert.showAndWait();
                    }
                }

        }
        );
        scores.setOnAction(event->{
           File results= new File(System.getProperty("user.dir")+"/result.txt");
            if (!results.exists() || results.length()==0) {
                Label lb = new Label("no data to show");
                lb.setAlignment(Pos.CENTER);
                Scene sc = new Scene(lb,300,300);
                KeyCombination ctrlZ = KeyCodeCombination.keyCombination("Ctrl+Z");
                sc.setOnKeyPressed(event2->{
                    if (ctrlZ.match(event2)){
                        window.setScene(menu);
                    }
                });
                window.setScene(sc);
                }
            else {
                try {
                    Scanner scanner=new Scanner(results);
                    ObservableList<Row> data= FXCollections.observableArrayList();
                    while (scanner.hasNextLine()){
                        data.add(new Row(scanner.nextLine()));
                    }
                    scanner.close();
                    FXCollections.sort(data);
                    ListView<Row> list=new ListView<>();

                    list.setItems(data);
                    Scene sc = new Scene(list,300,300);
                    KeyCombination ctrlZ = KeyCodeCombination.keyCombination("Ctrl+Z");
                    sc.setOnKeyPressed(event2->{
                        if (ctrlZ.match(event2)){
                            window.setScene(menu);
                        }
                    });
                    window.setScene(sc);
                }catch (Exception e){

                }

            }
        });
        return new Scene(vb,400,420);

    };
    private  Scene createGame(String username,double width,double height){
        gm=new GameWindow(username,width,height);
        Scene res=new Scene(gm,width,height);
        KeyCombination ctrlZ = KeyCodeCombination.keyCombination("Ctrl+Z");
        res.setOnKeyPressed(event->{
            if (!ctrlZ.match(event))
            ((GameWindow)res.getRoot()).getKey(event.getCode());
            else {
                ((GameWindow) res.getRoot()).writeData();
                window.setScene(menu);
            }
        });
        return res;
    };
    private Scene createResults(){
        return null;
    };



    public static void main(String[] args) {
        launch(args);
    }
}
