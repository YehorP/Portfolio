
public class Main {

    public static void main(String[] args) {
	// write your code here
        BayesClassifierRealization alghorithm=new BayesClassifierRealization(System.getProperty("user.dir")+"\\data\\iris_training.txt",System.getProperty("user.dir")+"\\data\\iris_test.txt");
        alghorithm.run(0);
    }
}
