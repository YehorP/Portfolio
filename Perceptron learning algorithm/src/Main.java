public class Main {

    public static void main(String[] args) {
	// write your code here
        Perceptron perceptron=new Perceptron(System.getProperty("user.dir")+"\\data\\iris_training.txt",System.getProperty("user.dir")+"\\data\\iris_test.txt",0.1);
        perceptron.runClassificationProccess();
    }
}
