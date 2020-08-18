import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainNetwork extends Application {
    List<Perceptron> mainPerceptronLayer;
    List<TextRepresentation>trainingSet;
    List<String>resultList;
    double trainingSpeed;
    Reader reader;

    public MainNetwork(){}
    public MainNetwork(double trainingSpeed) {
        this.mainPerceptronLayer=new ArrayList<>();
        this.trainingSet=new ArrayList<>();
        this.resultList=new ArrayList<>();
        this.reader=new Reader();
        this.trainingSpeed=trainingSpeed;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainNetwork network=new MainNetwork(0.1);
        network.startNetwork();

        TextArea mainFlow=new TextArea();
        mainFlow.setPrefSize(400,400);
        Label resultLbl=new Label("result:");
        resultLbl.setPrefSize(120,50);
        resultLbl.setTextAlignment(TextAlignment.CENTER);
        resultLbl.setFont(new Font(14));
        Button classify=new Button("classify");
        Button clean=new Button("clean");
        HBox hb=new HBox();
        hb.setSpacing(15);
        classify.setPrefSize(80,40);
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(mainFlow,resultLbl,classify);
        classify.addEventHandler(MouseEvent.MOUSE_CLICKED,(el)->{
            resultLbl.setText("result:"+network.classifyCurrentElement(mainFlow.getText()));
        });

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    private void createNetwork(){
        Map<String,String> dataForPerceptrons=reader.generatePerceptronsData();
        for (String key:dataForPerceptrons.keySet()){
            List<TextRepresentation> rightClassificationsSet=TextRepresentation.createMultiplyTextRepresentationElement(dataForPerceptrons.get(key));
            trainingSet.addAll(rightClassificationsSet);
            mainPerceptronLayer.add(new Perceptron(key,rightClassificationsSet,trainingSpeed));
        }
    }

    public String classifyCurrentElement(String element){
       TextRepresentation currentRepresentation=TextRepresentation.createSingleTextRepresentationElement(element);
       resultList.clear();
       for(Perceptron perceptron:mainPerceptronLayer){
           if (perceptron.classifyElement(currentRepresentation)==1)
               resultList.add(perceptron.language);
       }
        System.out.println(resultList);
       return resultList.size()==0?"Some other Language":resultList.get(0);
    }
    private void trainNetwork(){
        for (Perceptron perceptron:mainPerceptronLayer)
            perceptron.trainPerceptron(trainingSet);
    }

    public void startNetwork(){
        createNetwork();
        trainNetwork();
    }


}
