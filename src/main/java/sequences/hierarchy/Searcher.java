package sequences.hierarchy;

import sequences.karprabin.data.ResultSet;
import sequences.karprabin.impl.KarpRabinAlgorithm;

public class Searcher extends Thread {

    private Displayer displayer;
    private Boolean searchingRunning;
    private int minPatternSize;
    private String text;
    private String pattern = "";
    private ResultSet matches;
    private ResultSet searchResult;
    private KarpRabinAlgorithm searcherImpl = new KarpRabinAlgorithm();

    public Searcher(Displayer displayer, int minPatternSize, String text, String name) {
        super(name);
        this.displayer = displayer;
        this.minPatternSize = minPatternSize;
        this.text = text;
    }

    public void setText(String text) {
        synchronized (this) {
            this.text = text;
            notifyAll();
        }
    }

    public Boolean getSearchingRunning() {
        return searchingRunning;
    }

    private ResultSet checkAllPatterns(int patternSize) {
        ResultSet lastMatches = new ResultSet();
        for (int i = 0; i < text.length() - pattern.length() - 1; i++) {
            pattern = text.substring(i, i + patternSize);
            lastMatches = searcherImpl.find(text, pattern);
            if (lastMatches.found())
                return lastMatches;
        }
        return lastMatches;
    }

    @Override
    public void run() {
        while (true) {
            searchingRunning = true;
            while (pattern.length() < text.length() / 2) {
                for (int i = minPatternSize; i <= text.length() / 2; i++) {
                    searchResult = checkAllPatterns(i);
                    if (searchResult.found()) {
                        matches = searchResult;
                        displayer.setResultSet(matches);
                    }
                }
            }
            searchingRunning = false;
            displayer.setResultSet(matches);
            pattern = "";
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
