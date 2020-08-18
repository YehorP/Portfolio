import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Perceptron {
    Reader reader;
    double trainingSpeed;
    VectorElement weightsVector;
    List<VectorElement> trainingList;
    List<VectorElement> testList;
    Scanner console =new Scanner(System.in);
    public Perceptron(String trainingDataPath,String testDataPath,double trainingSpeed) {
        this.trainingSpeed=trainingSpeed;
        this.reader=new Reader(trainingDataPath,testDataPath);
    }

    public void train(){
        this.trainingList=reader.generateTrainingData();
        this.testList=reader.generateTestData();

        double[] tmpTab=new double[trainingList.get(0).values.length+1];
        for (int i=0;i<tmpTab.length-1;i++){
            tmpTab[i]=Math.random()*VectorElement.maxValue;
        }
        tmpTab[tmpTab.length-1]=1.0;
        weightsVector=new VectorElement(tmpTab);
        boolean flag=false;
        int classifiedVal=0;
        double[] trainingElementValues;
        while(!flag) {
            flag=true;
            Collections.shuffle(this.trainingList);
            for (int i = 0; i < trainingList.size(); i++) {
                trainingElementValues = trainingList.get(i).values;
                while(true) {
//                    for (int a = 0; a < trainingElementValues.length; a++) {
//                        sum += trainingElementValues[a] * weightsVector.values[a];
//                    }
//                    sum -= weightsVector.values[weightsVector.values.length - 1];
//                    classifiedVal = sum >= 0 ? 1 : 0;
                    classifiedVal = classifyElement(trainingList.get(i));
                    if (classifiedVal != trainingList.get(i).getTypeTransformedToValue()) {
                        flag=false;
                        for (int a = 0; a < trainingElementValues.length; a++) {
                            weightsVector.values[a] = weightsVector.values[a] + (trainingList.get(i).getTypeTransformedToValue() - classifiedVal) * trainingSpeed * trainingElementValues[a];
                        }
                        weightsVector.values[weightsVector.values.length - 1] = weightsVector.values[weightsVector.values.length - 1] + (trainingList.get(i).getTypeTransformedToValue() - classifiedVal) * trainingSpeed*(-1);
                    } else
                        break;
                }
            }
        }
    }

    public void runClassificationProccess(){
        boolean isExit=false;
        while (!isExit) {
            train();
            int rightClassified = 0;
            int classifiedVal;
            String command;
            for (VectorElement element : testList) {
                classifiedVal = classifyElement(element);
                rightClassified += classifiedVal == element.getTypeTransformedToValue() ? 1 : 0;
                //System.out.println(element+" result:"+(classifiedVal==1?"Iris-setosa":"any other"));
            }
            System.out.println("right classified:" + rightClassified);
            System.out.println("accuracy:" + ((double) (rightClassified) / (double) (testList.size())) * 100 + "%");
            System.out.println("Now you can enter your vector values separated by \\t (tab on your keyboard) example:3.2 2.5 3.1 0.2 extra values will be ignored lack of values won't give you a result or you can type exit to exit");
            while (true){
                System.out.println("enter your vector values or type exit:");
                command=console.nextLine();
                String[] tmpStringData=command.split("\\t");
                double[] tmpDouble;
                if (tmpStringData.length==trainingList.get(0).values.length){
                    try {
                        tmpDouble=new double[tmpStringData.length];
                        for (int i=0;i<tmpStringData.length;i++){
                            tmpDouble[i]=Double.parseDouble(tmpStringData[i].trim().replaceAll(",","."));
                        }
                        System.out.println("result type is:"+decodeClassification(classifyElement(new VectorElement(tmpDouble))));

                    }
                    catch (Exception ex){
                        System.out.println("wrong data was passed");
                    }
                }else {
                    if (command.toLowerCase().equals("exit")) {
                        isExit = true;
                        break;
                    }
                    else
                        System.out.println("wrong data was passed");
                }
            }
        }

    }

    public int classifyElement(VectorElement element){
        double sum=0;
        for (int i=0;i<element.values.length;i++){
            sum+=element.values[i]*weightsVector.values[i];
        }
        sum-=weightsVector.values[weightsVector.values.length-1];
        return sum>=0?1:0;
    }

    public String decodeClassification(int classificationValue){
        return classificationValue==1?"Iris-setosa":"something else";
    }
}
