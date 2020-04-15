package sequences.hierarchy;

import sequences.karprabin.data.ResultSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Displayer extends Thread {
    private ResultSet resultSet = new ResultSet();
    private int minPatternSize = 3;
    private Boolean searchingRunning = false;
    private Scanner scanner = new Scanner(System.in);
    private String fileName;
    private String text = "";
    private Searcher searcher;

    public Displayer(String name) {
        super(name);

    }

    private String readFromFile(String fileName) {
        String textFromFile = "";
        try {
            textFromFile = Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textFromFile;
    }

    public void setResultSet(ResultSet resultSet) {
        synchronized (this) {
            this.resultSet = resultSet;
            notifyAll();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (!searchingRunning) {
                System.out.println("Enter file name");
                fileName = scanner.nextLine();
                text = readFromFile(fileName);
                if (searcher == null) {
                    searcher = new Searcher(this, minPatternSize, text, "Sequences Searcher");
                    searcher.start();
                } else {
                    searcher.setText(text);
                }
            }
            try {
                synchronized (this) {
                    wait();
                    searchingRunning = searcher.getSearchingRunning();
                    if (searchingRunning) {
                        System.out.println("For pattern length " + (resultSet.results().get(0).end - resultSet.results().get(0).start + 1) + " there are:");
                        System.out.println("matches = " + resultSet.results());
                    } else {
                        System.out.println("\nThe longest sequences of " + (resultSet.results().get(0).end - resultSet.results().get(0).start + 1) + " symbols located:");
                        System.out.println("over here >>> " + resultSet.results());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}