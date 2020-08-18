import java.util.*;

public class KNN {
    List<VectorElement> trainingDataList;
    List<VectorElement> testDataList;
    int rightDefined;
    int neighboursAmount;
    Scanner console =new Scanner(System.in);
    Reader reader;
    public KNN(String trainingFileDestination,String testFileDestination) {
       this.reader=new Reader(trainingFileDestination,testFileDestination);
    }

    public void runAlghoritm(){
        String command;
        while (true) {
            try {
                System.out.println("enter K number or type exit");
                neighboursAmount=console.nextInt();
                this.testDataList = reader.generateCheckData();
                this.trainingDataList = reader.generateTrainingData();
                while (neighboursAmount == 0  || neighboursAmount > trainingDataList.size()) {
                    System.out.println("wrong k number try again");
                    neighboursAmount = console.nextInt();
                }
                rightDefined = 0;
                for (VectorElement element : testDataList) {
                    rightDefined += element.type.equals(classifyElement(nClosestVectors(element))) ? 1 : 0;
                }
                System.out.println("right classified:" + rightDefined);
                System.out.println("accuracy:" + ((double) (rightDefined) / (double) (testDataList.size()) * 100) + "%");
                
                console.nextLine();
                System.out.println("Now you can enter your vector values separated by \\t (tab on your keyboard) example:3.2 2.5 3.1 0.2 extra values will be ignored lack of values won't give you a result or you can type exit to exit");
                while (true){
                    System.out.println("enter your vector values or type exit:");
                    command=console.nextLine();
                    String[] tmpStringData=command.split("\\t");
                    double[] tmpDouble;
                    if (tmpStringData.length==trainingDataList.get(0).values.length){
                        try {
                            tmpDouble=new double[tmpStringData.length];
                            for (int i=0;i<tmpStringData.length;i++){
                                tmpDouble[i]=Double.parseDouble(tmpStringData[i].trim().replaceAll(",","."));
                            }
                            System.out.println("result type is:"+classifyElement(nClosestVectors(new VectorElement(tmpDouble))));
                        }
                        catch (Exception ex){
                            System.out.println("wrong data was passed");
                        }
                    }else {
                        if (command.toLowerCase().equals("exit"))
                            break;
                        else
                            System.out.println("wrong data was passed");
                    }
                }
            }catch (Exception ex){
                System.out.println("exiting");
                break;
            }
        }
    }

    public List<VectorElement> nClosestVectors(VectorElement current){
        List<VectorElement> resultNeighboursList=new ArrayList<>();
        List<VectorElement> trainingDataCopy= new ArrayList<>(trainingDataList);
        VectorElement tmp;
        for (int i=0;i<neighboursAmount;i++){
            tmp=trainingDataCopy.get(0);
            double minDistance=tmp.countDistance(current);
            for (int a=0;a<trainingDataCopy.size();a++){
                if (minDistance>trainingDataCopy.get(a).countDistance(current)){
                    tmp=trainingDataCopy.get(a);
                    minDistance=trainingDataCopy.get(a).countDistance(current);
                }
            }
            resultNeighboursList.add(tmp);
            trainingDataCopy.remove(tmp);
        }
        return resultNeighboursList;
    }

    public String classifyElement(List<VectorElement> neigboursList){
        Map<String,Integer> typesCount=new HashMap<>();
        for (VectorElement neighbour:neigboursList){
           if (typesCount.get(neighbour.type)!=null)
                typesCount.put(neighbour.type,typesCount.get(neighbour.type)+1);
            else
                typesCount.put(neighbour.type,1);
        }
        Integer maxVal=Collections.max(typesCount.values());
        String result="";
        for (String key:typesCount.keySet()){
            if (typesCount.get(key).equals(maxVal)){
                result=key;
                break;
            }
        }
        return result;
    }

}