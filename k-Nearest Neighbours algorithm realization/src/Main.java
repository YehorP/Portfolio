public class Main {

    public static void main(String[] args) {
	// write your code here
        KNN knnElement=new KNN(System.getProperty("user.dir")+"\\data\\iris_training.txt",System.getProperty("user.dir")+"\\data\\iris_test.txt");
        knnElement.runAlghoritm();
    }
}
