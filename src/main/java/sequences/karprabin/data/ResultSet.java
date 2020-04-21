package sequences.karprabin.data;

import java.util.ArrayList;

public class ResultSet {

    public boolean found() { return results.size() > 1; }

    private ArrayList<Result> results = new ArrayList<>();
    public ArrayList<Result> results() { return results; }

    public void add(Result result) { this.results.add(result); }
}
