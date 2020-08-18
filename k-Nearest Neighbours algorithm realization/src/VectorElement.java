import java.util.Arrays;

public class VectorElement {
    double[]values;
    String type;
    public VectorElement(String dataLine) {
        String[] separatedLines=dataLine.split("\\t");
        values=new double[separatedLines.length-1];
        for (int i=0;i<separatedLines.length-1;i++){
                values[i]=Double.parseDouble(separatedLines[i].trim().replaceAll(",","."));
            }
        type=separatedLines[separatedLines.length-1].trim();
    }

    public VectorElement(double[] values){
        this.values=values;
        this.type="";
    }

    public double countDistance(VectorElement sourceElement){
        double res=0;
        for (int i=0;i<values.length;i++){
            res+=Math.pow((sourceElement.values[i]-values[i]),2);
        }
        return Math.sqrt(res);
    }

    @Override
    public String toString() {
        return Arrays.toString(values)+" "+type;
    }
}