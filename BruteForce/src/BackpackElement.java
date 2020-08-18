public class BackpackElement {
    int size;
    int value;

    public BackpackElement(int size, int value) {
        this.size = size;
        this.value = value;
    }

    @Override
    public String toString() {
        return "size: " + size + " value: " + value;
    }
}
