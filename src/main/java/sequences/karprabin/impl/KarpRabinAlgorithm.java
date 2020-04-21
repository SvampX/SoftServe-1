package sequences.karprabin.impl;

import sequences.karprabin.data.Result;
import sequences.karprabin.data.ResultSet;

public class KarpRabinAlgorithm {

    private static final int COEFFICIENT = 2;
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    public ResultSet find(String from, String target) {

        ResultSet set = new ResultSet();

        final int tLength = target.length();
        final int tHash = hash(target, 0, tLength);

        for (int i = 0; i < from.length() - target.length() + 1; i++) {

            int sHash = hash(from, i, tLength);

            if (tHash == sHash)
                set.add(new Result(i, i + tLength - 1));
        }

        return set;
    }

    private int hash(String str, int i, int m) {

        return i == 0 ? hashInitial(str, i, m) : hashOthers(str, i, m);
    }

    private int hashInitial(String str, int i, int m) {

        int hash = 0;

        for (int x = i; x < m; x++)
            hash += (int) (str.charAt(x) * Math.pow(COEFFICIENT, m - x - 1));

        return hash % MAX_VALUE;
    }

    private int hashOthers(String str, int i, int m) {

        return (int) (2 * (hash(str, i - 1, m) - str.charAt(i - 1) * Math.pow(COEFFICIENT, m - 1)) + str.charAt(i + m - 1));
    }
}