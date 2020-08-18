import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Perceptron {
    String language;
    List<TextRepresentation> positiveClassificationSet;
    double trainingSpeed;
    double[] weightsVektor;

    public Perceptron(String language, List<TextRepresentation> positiveClassificationSet,double trainingSpeed) {
        this.language = language;
        this.positiveClassificationSet = positiveClassificationSet;
        this.weightsVektor=new double[positiveClassificationSet.get(0).lettersCount.size()+1];
        this.trainingSpeed=trainingSpeed;
        for (int a=0;a<weightsVektor.length-1;a++){
            weightsVektor[a]=Math.random()*1;
        }
        weightsVektor[weightsVektor.length-1]=1.0;
    }

    public void trainPerceptron(List<TextRepresentation> trainingSet){
        ArrayList<TextRepresentation> trainingSetCopy=new ArrayList<>(trainingSet);
        boolean continueTraining=true;
        while (continueTraining){
            continueTraining=false;
            Collections.shuffle(trainingSetCopy);
            for (TextRepresentation element:trainingSetCopy){
                while (true) {
                    double classify = classifyElement(element);
                    if (classify != isPositive(element)) {
                        for (int a = 0; a < element.lettersCount.size(); a++) {
                            weightsVektor[a] = weightsVektor[a] + (isPositive(element) - classify) * trainingSpeed * element.lettersCount.get(a);
                        }
                        weightsVektor[weightsVektor.length - 1] = weightsVektor[weightsVektor.length - 1] + (isPositive(element) - classify) * trainingSpeed*(-1);
                        continueTraining = true;
                    }else
                        break;
                }
            }
        }
    }

    public double classifyElement(TextRepresentation element){
        double sum=0.0;
        for (int a=0;a<weightsVektor.length-1;a++){
            sum+=weightsVektor[a]*element.lettersCount.get(a);
        }
        sum-=weightsVektor[weightsVektor.length-1];
        return sum>=0?1:0;
    }

    public double isPositive(TextRepresentation element){
        if (positiveClassificationSet.contains(element))
            return 1;
        return  0;
    }
}
