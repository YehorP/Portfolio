
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    String path;
    List<List<BackpackElement>> dataSets;
    long dataFileModification = -1;
    File dataFile;
    int maxCapacity = 0;

    public Reader(String path) {
        this.dataFile = new File(path);
    }

    private void readData(){
        if (dataFileModification == -1 || dataFileModification != dataFile.lastModified()) {
            dataSets = new ArrayList<>();
            dataFileModification = dataFile.lastModified();
            try (BufferedReader bf = new BufferedReader(new FileReader(dataFile))) {
                Pattern capacity = Pattern.compile("capacity\\s([1-9]\\d*)");
                Pattern valuesAndSizes = Pattern.compile("(sizes\\s*=\\s*[{][\\s*[1-9]\\d[,]?]*[}])\\s*(vals\\s*=\\s*[{][\\s*[1-9]\\d[,]?]*[}])");
                Pattern insideValues = Pattern.compile("[1-9]\\d*");
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bf.readLine()) != null) {
                    sb.append(line);
                }

                Matcher patternMatcher = capacity.matcher(sb.toString());
                if (patternMatcher.find()){
                    maxCapacity = Integer.parseInt(patternMatcher.group(1));
                }
                else {
                    throw new Exception("Wrong file data");
                }

                patternMatcher = valuesAndSizes.matcher(sb.toString());
                while (patternMatcher.find()){
                    Matcher sizesMatcher = insideValues.matcher(patternMatcher.group(1));
                    Matcher valsMatcher = insideValues.matcher(patternMatcher.group(2));
                    List<BackpackElement> elements = new ArrayList<>();
                   while (sizesMatcher.find() && valsMatcher.find()){
                       elements.add(new BackpackElement(Integer.parseInt(sizesMatcher.group(0)), Integer.parseInt(valsMatcher.group(0))));
                   }
                   dataSets.add(elements);
                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                dataSets = null;
            }
        }
    }

    public List<BackpackElement> getRandomSet() throws Exception{
        readData();
        if (dataSets == null)
            throw new Exception("No data was readed please change source file");
        return dataSets.get((int)(Math.random()*dataSets.size()));
    }
}
