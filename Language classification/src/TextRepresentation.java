import java.util.*;

public class TextRepresentation {
    private static String alphabet="abcdefghijklmnopqrstuvwxyz";
    List<Double> lettersCount;
    String text;
    public TextRepresentation(String text ,List<Double> lettersCount) {
        this.lettersCount = lettersCount;
        this.text=text;
    }

    public static TextRepresentation createSingleTextRepresentationElement(String singleElemtText){
        String currentText=singleElemtText.toLowerCase();
        List<Double> tmpLettersCount=new ArrayList();
        double allLetters=currentText.length()-currentText.replaceAll("[a-z]","").length();
        for (int i = 0; i < alphabet.length(); i++) {
            String letter = alphabet.charAt(i) + "";
            tmpLettersCount.add((currentText.length()-currentText.replaceAll(letter,"").length())/allLetters);
        }
        return new TextRepresentation(singleElemtText,tmpLettersCount);

    }
    public static List<TextRepresentation> createMultiplyTextRepresentationElement(String fileText){
        String[] data=fileText.split("\n\n");
        ArrayList<TextRepresentation> resultList=new ArrayList<>();
        for (int a=0;a<data.length;a++) {
            resultList.add(createSingleTextRepresentationElement(data[a]));
        }
        return resultList;
    }

    /*public double countProbs(){
        double res=0;
        for (Double el:lettersCount){
            res+=el;
        }
        return res;
    }*/

    @Override
    public boolean equals(Object o) {
        if (o instanceof TextRepresentation){
            return this.text.equals(((TextRepresentation)o).text);
        }
       return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "TextRepresentation{" +
                "lettersCount=" + lettersCount +"\n"+
                ", text='" + text + "\n"+
                '}';
    }
}
