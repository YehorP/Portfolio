import java.util.Arrays;

public class VectorElement {
    double[]values;
    public static double maxValue=0;
    String type;
    public VectorElement(String dataLine) {
        String[] separatedLines=dataLine.split("\\t");
        values=new double[separatedLines.length-1];
        for (int i=0;i<separatedLines.length-1;i++){
                values[i]=Double.parseDouble(separatedLines[i].trim().replaceAll(",","."));
                maxValue=Math.max(maxValue,values[i]);
            }
        type=separatedLines[separatedLines.length-1].trim();
    }

    public VectorElement(double[] values){
        this.values=values;
        this.type="";
    }

    public int getTypeTransformedToValue(){
        return type.equals("Iris-setosa")?1:0;
    }
    @Override
    public String toString() {
        return Arrays.toString(values)+" "+type;
    }
}