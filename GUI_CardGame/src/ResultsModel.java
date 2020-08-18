import javax.swing.*;
import java.util.ArrayList;

public class ResultsModel extends AbstractListModel<String> {

    private ArrayList<String> results=new ArrayList<>();
    public void add(String record){
        results.add(record.replace("\n",""));
    }
    @Override
    public int getSize() {
        return results.size();
    }

    @Override
    public String getElementAt(int index) {
        return results.get(index);
    }
}
