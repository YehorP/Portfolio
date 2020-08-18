import java.util.*;
import java.util.stream.Collectors;


public class BayesClassifierRealization {
    Reader reader;
    Scanner console=new Scanner(System.in);
    public BayesClassifierRealization(String trainingFilePath,String testFilePath) {
        this.reader=new Reader(trainingFilePath,testFilePath);
    }

    public void run(int justificationArgumentNumber){
        Map<Integer,List<Double>> allAttr=new HashMap<>();
        Map<String,List<List<Double>>> trainingDataMap=new HashMap<>();
        List<VectorElement> testList=reader.generateCheckData();
        List<VectorElement> trainingList=reader.generateTrainingData();
        boolean justify=false;
        //checking if we have to justify
        for (int i=0;i<trainingList.get(0).values.size();i++)
            allAttr.put(i,new ArrayList<>());

        for (String decisionAttribute:VectorElement.decisionAttribute) {
            trainingDataMap.put(decisionAttribute, new ArrayList<>());
            for (int i=0;i<testList.get(0).values.size();i++)
                trainingDataMap.get(decisionAttribute).add(new ArrayList<>());
        }
        for (VectorElement element:trainingList){
            for (int i=0;i<element.values.size();i++){
                    allAttr.get(i).add(element.values.get(i));
                    trainingDataMap.get(element.type).get(i).add(element.values.get(i));
            }
        }
        for (VectorElement element:testList){
            for (int i=0;i<element.values.size();i++) {
                for (String key:trainingDataMap.keySet()){
                    if (!trainingDataMap.get(key).get(i).contains(element.values.get(i))){
                        justify=true;
                        break;
                    }
                }
                if (justify)
                    break;
            }
            if (justify)
                break;
        }
        //System.out.println(trainingDataMap);
        List<String> classifiedTypeList=classifyData(allAttr,trainingList,testList,trainingDataMap,justify,justificationArgumentNumber);
        classificationOutput(testList,classifiedTypeList,trainingDataMap);

        System.out.println("\nnow you can enter your vector");
        outerLoop:
        while (true){
            boolean flag=true;
            VectorElement userVector=null;
            int column=0;
            while (flag){
                System.out.println("example 3.2 3.1 2.5 4.1 each value separated by space");
                String value=console.nextLine();
                if (value.equals("exit"))
                    break outerLoop;
                if (value.split(" ").length==trainingList.get(0).values.size()){
                    List<Double> values=new ArrayList<>();
                    try {
                        for(String val:value.split(" ")){
                            values.add(Double.parseDouble(val));
                        }
                        flag=false;
                        userVector=new VectorElement(values);
                    }catch (Exception ex){
                        System.out.println("wrong data was passed");
                    }
                }else {
                    System.out.println("wrong data was passed");
                }
            }
            flag=true;
            while (flag){
                System.out.println("enter column(start from 0) which you want to justify or type -1");
                try {
                    String value=console.nextLine();
                    if (value.equals("exit"))
                        break outerLoop;
                    column=Integer.parseInt(value);
                    if (column<trainingList.get(0).values.size()+1)
                        flag=false;
                    else
                        System.out.println("column does not exist");
                }catch (Exception ex){
                    System.out.println("wrong data was passed");
                }
            }

            boolean justifyUserInput=false;
            for (int i=0;i<userVector.values.size();i++) {
                for (String key:trainingDataMap.keySet()){
                    if (!trainingDataMap.get(key).get(i).contains(userVector.values.get(i))){
                        justifyUserInput=true;
                        break;
                    }
                }
                if (justifyUserInput)
                    break;
            }
            System.out.println(userVector.toString()+"is "+classifyData(allAttr,userVector,trainingList,trainingDataMap,justifyUserInput,column));
        }
    }
    private void classificationOutput(List<VectorElement> testData,List<String> classifiedTypeList,Map<String,List<List<Double>>> trainingDataMap){
        StringBuilder res=new StringBuilder();
        res.append("\t\t\t\t");
        char code='a';

        for (String key:trainingDataMap.keySet()){
            res.append("\t"+code);
            code++;
        }
        double accuracy=0;
        res.append("\n");
        List<Map<String,Integer>> counted=new ArrayList<>();
        code='a';
        for (String currentKey:trainingDataMap.keySet()){
            Map<String,Integer> row=new HashMap<>();
            for (String keyElement:trainingDataMap.keySet()){
                row.put(keyElement,0);
            }
            for (int i=0;i<classifiedTypeList.size();i++){
                if (testData.get(i).type.equals(currentKey)){
                 if (classifiedTypeList.get(i).equals(testData.get(i).type)){
                     accuracy++;
                     row.put(currentKey,row.get(currentKey)+1);
                 }  else {
                     row.put(classifiedTypeList.get(i),row.get(classifiedTypeList.get(i))+1);
                 }
                }
            }
            counted.add(row);
            String rowString=currentKey+"="+code+" "+(currentKey.equals("Iris-setosa")?"\t":"");
            for (String keyElement:row.keySet()){
                rowString+="\t"+row.get(keyElement);
            }
            rowString+="\n";
            res.append(rowString);
            code++;


        }
//        double correctlyPredicted=0;
//        double allPredicted=0;
//        int rightGroup=0;
//        for (Map<String,Integer> row:counted){
//            int currentGroup=0;
//            for (String key:row.keySet()){
//                if (currentGroup==rightGroup)
//                    correctlyPredicted+=row.get(key);
//                allPredicted+=row.get(key);
//                currentGroup++;
//            }
//            rightGroup++;
//        }

//        correctlyPredicted=0;
//        double actualData=0;
//        rightGroup=0;
        System.out.println(res.toString());
        System.out.println("Accuracy:"+((int)(accuracy/testData.size()*100))+"%");
//        System.out.println("Precision:"+((int)(correctlyPredicted/allPredicted*100))+"%");
        System.out.println();
        HashMap<String,Double> recallMap=new HashMap<>();
        int rightGroup=0;
        for (Map<String,Integer> row:counted) {
            int currentGroup=0;
            double correctlyPredicted=0;
            double allPredicted=0;
            String show="";
            String keyValue="";
            for (String key:row.keySet()){
                if (currentGroup==rightGroup){
                    correctlyPredicted+=row.get(key);
                    show=key+" Recall:";
                    keyValue=key;
                }
                allPredicted+=row.get(key);
                currentGroup++;
            }
            recallMap.put(keyValue,correctlyPredicted/allPredicted);
            show+=((int)((correctlyPredicted/allPredicted)*100))+"%";
            System.out.println(show);
            rightGroup++;
        }
        System.out.println();
        HashMap<String,Double> precisionMap=new HashMap<>();
        List<Iterator<String>> rowIterators=new ArrayList<>();
        for (Map<String,Integer> row:counted){
            rowIterators.add(row.keySet().iterator());
        }
        int groups=0;
        for (String key:trainingDataMap.keySet()){
            List<Integer> values=new ArrayList<>();
            int currentGroup=0;
            for (Iterator<String> iter:rowIterators) {
                values.add(counted.get(currentGroup).get(iter.next()));
                currentGroup++;
            }

            int sum=values.stream().mapToInt(Integer::intValue).sum();
            precisionMap.put(key,(double)values.get(groups)/(double)sum);
            System.out.println(key+" Precision:"+(int)(((double)values.get(groups)/(double)sum)*100)+"%");
            groups++;
        }
        
        System.out.println();
        for (String key:recallMap.keySet()){
            System.out.println(key+" F measure:"+(int)(((2*recallMap.get(key)*precisionMap.get(key))/(recallMap.get(key)+precisionMap.get(key)))*100)+"%");
        }
    }

    private List<String> classifyData(Map<Integer,List<Double>> allAttr,List<VectorElement> trainingList,List<VectorElement> testList,Map<String,List<List<Double>>> trainingDataMap,boolean justify,int justificationArgumentNumber){
        List<String> classifiedTypeList=new ArrayList<>();
        for (VectorElement testElement:testList){
        HashMap <String,Double> typeProb=new HashMap<>();
        for (String key:trainingDataMap.keySet()){
            double multiply=1;
            for (int i=0;i<trainingDataMap.get(key).size();i++){
                List<Double> currentColumn=trainingDataMap.get(key).get(i);
                multiply*= justify?(double)(Collections.frequency(currentColumn,testElement.values.get(i))+1)/(double)(currentColumn.size()+allAttr.get(i).stream().distinct().count()):justificationArgumentNumber!=-1 && justificationArgumentNumber<trainingDataMap.get(key).size()?i==justificationArgumentNumber?(double)(Collections.frequency(currentColumn,testElement.values.get(i))+1)/(double)(currentColumn.size()+allAttr.get(i).stream().distinct().count()):(double)(Collections.frequency(currentColumn,testElement.values.get(i)))/(double)(currentColumn.size()):(double)(Collections.frequency(currentColumn,testElement.values.get(i)))/(double)(currentColumn.size());
            }
            multiply*=justify?(double)(trainingDataMap.get(key).get(0).size()+1)/(double)trainingList.size()+VectorElement.decisionAttribute.size():justificationArgumentNumber!=-1 && justificationArgumentNumber==trainingDataMap.get(key).size()?trainingDataMap.get(key).size()==justificationArgumentNumber?(double)(trainingDataMap.get(key).get(0).size()+1)/(double)trainingList.size()+VectorElement.decisionAttribute.size():(double)trainingDataMap.get(key).get(0).size()/(double)trainingList.size():(double)trainingDataMap.get(key).get(0).size()/(double)trainingList.size();
            typeProb.put(key,multiply);
        }
        String type="";
        double max=0;
        boolean isFirst=true;
        for (String key:typeProb.keySet()){
            if (isFirst){
                isFirst=false;
                max=typeProb.get(key);
                type=key;
            }else {
                if (max<typeProb.get(key)){
                    max=typeProb.get(key);
                    type=key;
                }
            }
        }
        classifiedTypeList.add(type);
        //System.out.println(testElement+" classified type:"+typeProb);
    }
    return classifiedTypeList;
    }

    private String classifyData(Map<Integer,List<Double>> allAttr,VectorElement testElement,List<VectorElement> trainingList,Map<String,List<List<Double>>> trainingDataMap,boolean justify,int justificationArgumentNumber){
        HashMap <String,Double> typeProb=new HashMap<>();
        for (String key:trainingDataMap.keySet()){
            double multiply=1;
            for (int i=0;i<trainingDataMap.get(key).size();i++){
                List<Double> currentColumn=trainingDataMap.get(key).get(i);
                multiply*= justify?(double)(Collections.frequency(currentColumn,testElement.values.get(i))+1)/(double)(currentColumn.size()+allAttr.get(i).stream().distinct().count()):justificationArgumentNumber!=-1 && justificationArgumentNumber<trainingDataMap.get(key).size()?i==justificationArgumentNumber?(double)(Collections.frequency(currentColumn,testElement.values.get(i))+1)/(double)(currentColumn.size()+allAttr.get(i).stream().distinct().count()):(double)(Collections.frequency(currentColumn,testElement.values.get(i)))/(double)(currentColumn.size()):(double)(Collections.frequency(currentColumn,testElement.values.get(i)))/(double)(currentColumn.size());
            }
            multiply*=justify?(double)(trainingDataMap.get(key).get(0).size()+1)/(double)trainingList.size()+VectorElement.decisionAttribute.size():justificationArgumentNumber!=-1 && justificationArgumentNumber==trainingDataMap.get(key).size()?trainingDataMap.get(key).size()==justificationArgumentNumber?(double)(trainingDataMap.get(key).get(0).size()+1)/(double)trainingList.size()+VectorElement.decisionAttribute.size():(double)trainingDataMap.get(key).get(0).size()/(double)trainingList.size():(double)trainingDataMap.get(key).get(0).size()/(double)trainingList.size();
            typeProb.put(key,multiply);
        }

        String type="";
        double max=0;
        boolean isFirst=true;
        for (String key:typeProb.keySet()){
            if (isFirst){
                isFirst=false;
                max=typeProb.get(key);
                type=key;
            }else {
                if (max<typeProb.get(key)){
                    max=typeProb.get(key);
                    type=key;
                }
            }
        }
        return type;
    }

}
