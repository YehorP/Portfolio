import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Reader {
    public Map<String,String> generatePerceptronsData(){
       File[] subDirectorisPaths=new File("data").listFiles(File::isDirectory);
       HashMap<String,String> result=new HashMap();
       for (File dir:subDirectorisPaths){
           File trainingData=new File(dir.toString()+"/TrainingData.txt");
           StringBuilder stringBuilder=new StringBuilder();
           try {
               BufferedReader br=new BufferedReader(new FileReader(trainingData));
               String line;
               while ((line=br.readLine())!=null){
                   stringBuilder.append(line+"\n");
               }
               br.close();
           }catch (Exception ex){
               System.out.println("Reading exception");
           }
           result.put(dir.getName(),stringBuilder.toString());
       }
       return result;
    }
}
