package com.training.strings;

/**
 * https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
 *
 * Created by JohnPaul.Manohar on 10/30/2020.
 */
public class RabinKarpStringSearch {

    static int d = 256;

    public static void main(String... args) {

        String text = "AABAACAADAABAAABAA";
        String pattern = "AABA";

        int q = 101;

        patternSearch(text, pattern, q);
    }

    static void patternSearch(String text, String pattern, int q) {
        int m = pattern.length();
        int n = text.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;

        for (i = 0; i < m - 1; i++)
            h = (h * d) % q;

        for (i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + pattern.charAt(i)) % q;
        }

        for (i = 0; i <= (n - m); i++) {
            if(p == t) {
                for(j = 0; j < m; j++) {
                    if(text.charAt(i + j) != pattern.charAt(j)) break;
                }

                if(j == m) System.out.println("Pattern found in index : " + i);
            }

            if(i < (n-m)) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;

                if(t < 0) t = (t + q);
            }
        }
    }
}
