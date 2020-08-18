import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class BruteForceImplementation {
    Reader reader;
    Scanner scanner = new Scanner(System.in);
    public BruteForceImplementation(Reader reader) {
        this.reader = reader;
    }

    public void  run(){
        while (true){
            try {
                System.out.println("type start to start the programme or type exit to stop the programme");
                String command = scanner.nextLine();
                if (command.equals("exit"))
                    break;
                else if (command.equals("start"))
                    System.out.println(bruteForce());
                else
                    System.out.println("wrong command");
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                break;
            }
        }
    }
    private String bruteForce() throws Exception{
        List<BackpackElement> generatedSet = reader.getRandomSet();
        System.out.println(generatedSet);
        StringBuilder bestSequence = new StringBuilder();
        StringBuilder currentSequence = new StringBuilder();
        StringBuilder end = new StringBuilder();
        StringBuilder start = new StringBuilder();
        int optimalSize = reader.maxCapacity;
        int optimalTotalValue = 0;
        for (int i=0;i<generatedSet.size();i++){
            bestSequence.append("0");
            currentSequence.append("0");
            start.append("0");
            end.append("1");
        }
        Instant startTime = Instant.now();
        while (!currentSequence.toString().equals(end.toString())){
            int tmpValue = 0;
            int tmpSize = 0;
            boolean flag = true;
            for (int i=0;i<currentSequence.length();i++){
                if (currentSequence.charAt(i) == '0' && flag) {
                    currentSequence.setCharAt(i, '1');
                    flag = false;
                }
                else {
                    if (flag) {
                        currentSequence.setCharAt(i, '0');
                    }
                }

                tmpSize += currentSequence.charAt(i) == '1'?generatedSet.get(i).size:0;
                tmpValue += currentSequence.charAt(i) == '1'?generatedSet.get(i).value:0;
            }

            if (tmpValue >= optimalTotalValue && tmpSize<reader.maxCapacity){
                if (tmpValue == optimalTotalValue){
                    if (tmpSize<optimalTotalValue){
                        optimalSize = tmpSize;
                        bestSequence.setLength(0);
                        bestSequence.append(currentSequence.toString());
                    }
                }else {
                    optimalSize = tmpSize;
                    optimalTotalValue = tmpValue;
                    bestSequence.setLength(0);
                    bestSequence.append(currentSequence.toString());
                }
            }
        }

        Instant endTime = Instant.now();
        StringBuilder res = new StringBuilder();
        res.append("==========================\n");
        int counter = 0;
        for(int i=0;i<bestSequence.length();i++){
            if (bestSequence.charAt(i) == '1') {
                res.append("id: ");
                res.append(counter + 1);
                res.append(" ");
                res.append(generatedSet.get(i).toString());
                res.append("\n");
                counter++;
            }
        }
        res.append("code: ");
        res.append(bestSequence.toString());
        res.append("\ntotal size: ");
        res.append(bestSequence.toString().equals(start.toString())?0:optimalSize);
        res.append(" |total value: ");
        res.append(optimalTotalValue);
        res.append(" |time: ");
        res.append(Duration.between(startTime,endTime).toMillis());
        res.append(" milliseconds");
        return  res.toString();
    }
}