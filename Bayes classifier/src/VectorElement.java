import java.util.*;

public class VectorElement {
    List<Double> values;
    String type;
    public static List<String> decisionAttribute=new ArrayList<>();

    public VectorElement(String dataLine,boolean isTrainingData) {
        String[] separatedLines=dataLine.split("\\t");
        values=new ArrayList<>();
        for (int i=0;i<separatedLines.length-1;i++){
            values.add(Double.parseDouble(separatedLines[i].trim().replaceAll(",",".")));
            }
        type=separatedLines[separatedLines.length-1].trim();
        if (isTrainingData && !decisionAttribute.contains(type))
            decisionAttribute.add(type);
    }
    public  VectorElement(List<Double> values){
        this.values=values;
    }

    @Override
    public String toString() {
        return values.toString()+" "+(type==null?"":type);
    }
}