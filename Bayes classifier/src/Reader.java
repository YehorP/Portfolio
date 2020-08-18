import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Reader {
    File trainingFile;
    File testFile;
    List<VectorElement> trainingDataList;
    List<VectorElement> checkDataList;
    long traningDataFileModification=-1;
    long testDataFileModification=-1;
    public Reader(String trainingPath,String testPath) {
        this.trainingFile=new File(trainingPath);
        this.testFile=new File(testPath);
    }

    public List<VectorElement> generateTrainingData(){
        if (traningDataFileModification==-1 || traningDataFileModification!=trainingFile.lastModified()) {
            trainingDataList=new ArrayList<>();
            traningDataFileModification=trainingFile.lastModified();
            try (BufferedReader bf = new BufferedReader(new FileReader(trainingFile))) {
                String line;
                while ((line = bf.readLine()) != null) {
                    trainingDataList.add(new VectorElement(line,true));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("exception while opening or processing training file");
            }
        }
        return trainingDataList;
    }

    public List<VectorElement> generateCheckData(){
        if (testDataFileModification==-1 || testDataFileModification!=testFile.lastModified()) {
            checkDataList=new ArrayList<>();
            testDataFileModification=testFile.lastModified();
            try (BufferedReader bf = new BufferedReader(new FileReader(testFile))) {
                String line;
                while ((line = bf.readLine()) != null) {
                    checkDataList.add(new VectorElement(line,false));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("exception while opening or processing test file");
            }
        }
        return checkDataList;
    }
}
