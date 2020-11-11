package com.training.strings;

/**
 * https://www.geeksforgeeks.org/naive-algorithm-for-pattern-searching/
 *
 * Created by JohnPaul.Manohar on 10/30/2020.
 */
public class NaiveStringSearch {
    public static void main(String... args) {
        String text = "AABAACAADAABAAABAA";
        String pattern = "AABA";

        patternSearch(text, pattern);
    }

    static void patternSearch(String text, String pattern) {

        int m = pattern.length();
        int n = text.length();

        for (int i = 0; i <= (n - m); i++) {
            int j = 0;
            for (j = 0; j < m; j++) {
                if(text.charAt(i+j) != pattern.charAt(j)) break;
            }

            if(j == m) {
                System.out.println("Pattern found at index : " + i);
            }
        }

    }
}
