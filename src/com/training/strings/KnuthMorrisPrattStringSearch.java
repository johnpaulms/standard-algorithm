package com.training.strings;

/**
 * https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
 *
 * Created by JohnPaul.Manohar on 10/30/2020.
 */
public class KnuthMorrisPrattStringSearch {
    public static void main(String... args) {
        String text = "AABAACAADAABAAABAA";
        String pattern = "AABA";

        patternSearch(text, pattern);
    }

    static void patternSearch(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();

        int[] lps = new int[m];
        int j = 0;

        computeLPS(pattern, m, lps);

        int i = 0;

        while (i < n) {
            if(pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }

            if(j == m) {
                System.out.println("Pattern found at index " + (i - j));

                j = lps[j - 1];
            }
            else if(i < n && pattern.charAt(j) != text.charAt(i)) {
                if(j != 0) j = lps[j - 1];
                else i = i + 1;
            }
        }
    }

    static void computeLPS(String pattern, int patternLength, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < patternLength) {
            if(pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else {
                if(len != 0) {
                    len = lps[len - 1];
                }
                else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
}
