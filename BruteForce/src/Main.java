public class Main {

    public static void main(String[] args) {
	Reader reader = new Reader(System.getProperty("user.dir")+"\\data\\plecak.txt");
	BruteForceImplementation bruteForce = new BruteForceImplementation(reader);
	bruteForce.run();
    }
}
