import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Map {
    int rows;
    int cols;
    Node start,end;
    int prev[];
    boolean flag=false;
    ArrayList<Node> res=new ArrayList<>();
    LinkedList<Node> map=new LinkedList<>();
    boolean visited[];

    public Map( int cols,int rows) {
        this.rows = rows;
        this.cols = cols;
        createMap();

    }
    private  void createMap(){
        int col=0,row=0;
        for (int i=0;i<cols*rows;i++){
            if (col-cols==0){
                col=0;
                row++;
            }
            map.add(new Node(row,col));
            col++;
        }
        int endIndex=(int)(Math.random()*map.size()),startIndex=(int)(Math.random()*map.size());
        while (endIndex==startIndex){
            endIndex=(int)(Math.random()*map.size());
            startIndex=(int)(Math.random()*map.size());
        }
        start=map.get(startIndex);
        end=map.get(endIndex);
        start.makeStart();
        end.makeEnd();

        int values[]={1,-1,cols,-cols};
        for (int i=0;i<map.size();i++) {
            if (map.get(i).val != 0) {
                for (int a = 0; a < values.length; a++) {
                    if (!((i % cols == 0 && values[a] == -1) || (i + 1) % cols == 0 && values[a] == 1)){
                        if(values[a]>0?i + values[a] < map.size():i + values[a] >= 0){
                            map.get(i).connect(map.get(i + values[a]));
                        }
                    }
                }
            }
        }
    }
    public void find(){
        System.out.println("Start:"+start+", End "+end);
            Node tmp;
            visited = new boolean[map.size()];
            visited[map.indexOf(start)] = true;
            prev = new int[map.size()];
            LinkedList<Node> queue = new LinkedList<Node>();
            queue.add(start);
            while (queue.size() != 0 && !flag) {
                tmp = queue.poll();
                Iterator<Node> i = tmp.connections.listIterator();
                while (i.hasNext()) {
                    Node n = i.next();
                    if (!visited[map.indexOf(n)]) {
                        prev[map.indexOf(n)] = map.indexOf(tmp);
                        visited[map.indexOf(n)] = true;
                        queue.add(n);
                    }
                    if (n.row == end.row && n.col == end.col) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) {
                int index = map.indexOf(end);
                while (index != (map.indexOf(start))) {
                    res.add(0, map.get(index));
                    index = prev[index];
                }
                res.add(0, map.get(index));
                System.out.println(res);
            }else {
                System.out.println("no way");
            }
    }
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<map.size();i++) {

            sb.append(map.get(i).name.isEmpty()?map.get(i).val+" ":map.get(i).name+" ");
            if ((i + 1) % cols == 0) {
                sb.append("\n");
            }
        }
//        for (int i=0;i<map.size();i++){
//            if (map.get(i).val!=0)
//            sb.append(map.get(i).show());
//        }
        return sb.toString();
    }
}