import java.util.LinkedList;

public class Node {
    int col,row,val;
    String name="";
    LinkedList<Node>connections=new LinkedList<>();
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        val=Math.random()*1>0.5?1:0;
    }
    public void connect(Node n){
        if (n.val==1)
        connections.add(n);
    }
    public void makeStart(){
        this.name="S";
        val=1;
    }
    public void makeEnd(){
        this.name="K";
        val=1;
    }


    @Override
    public String toString() {
        return "("+row+","+col+")";
    }

    public String show(){
        StringBuilder stringB=new StringBuilder();

        stringB.append("\n");
        stringB.append("element: row:"+row+" col:"+col);
        stringB.append("\n");
        for (Node n:connections){
            stringB.append(n.toString());
            stringB.append("\n");
        }
        return stringB.toString();
    }
}
