package sequences.hierarchy;

public class Threads {
    public static void main(String args[]) {
        Thread displayer = new Displayer("Sequences Displayer");
        displayer.start();
    }
}


