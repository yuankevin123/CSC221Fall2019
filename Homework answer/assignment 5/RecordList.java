package application;

import java.util.ArrayList;
import java.util.List;

public class RecordList {

    private List<Record> lst = new ArrayList<>();

    public List<Record> getLst() {

        return lst;
    }

    public void createNew() {

        lst.add(new Record());
    }

    public void setLst(List<Record> lst) {

        this.lst = lst;
    }
}
