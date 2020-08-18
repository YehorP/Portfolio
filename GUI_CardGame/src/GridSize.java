public class GridSize {
    int rows;
    int columns;

    public GridSize(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public GridSize(int size) {
        this(size,size);
    }

    public int elementAmount(){
        return rows*columns;
    }

    @Override
    public String toString() {
        return rows+"x"+columns;
    }
}
