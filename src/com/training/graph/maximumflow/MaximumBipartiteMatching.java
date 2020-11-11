package com.training.graph.maximumflow;

/**
 * https://www.geeksforgeeks.org/maximum-bipartite-matching/
 * 
 * Created by JohnPaul.Manohar on 10/30/2020.
 */
public class MaximumBipartiteMatching {

    static int applicant = 6;
    static int jobs = 6;

    public static void main(String... args) {

        boolean[][] bpGraph = new boolean[][] {
                {false, true, true, false, false, false},
                {true, false, false, true, false, false},
                {false, false, true, false, false, false},
                {false, false, true, true, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, false, true}};

        System.out.println("Maximum number of applicants that can get the job : " + maxBPM(bpGraph));
    }

    static int maxBPM(boolean[][] bpGraph) {
        int result = 0;

        int[] jobsarr = new int[jobs];

        for (int i = 0; i < jobs; i++) jobsarr[i] = -1;

        for (int i = 0; i < applicant; i++) {
            boolean[] visited = new boolean[jobs];

            for (int j = 0; j < jobs; j++) visited[j] = false;

            if(bpm(bpGraph, i, visited, jobsarr)) result++;
        }

        return result;
    }

    static boolean bpm(boolean[][] bpGraph, int app, boolean[] visited, int[] jobsarr) {

        for(int i = 0; i < jobs; i++) {

            if(bpGraph[app][i] && !visited[i]) {
                visited[i] = true;

                if(jobsarr[i] < 0 || bpm(bpGraph, jobsarr[i], visited, jobsarr)) {
                    jobsarr[i] = app;
                    return true;
                }
            }
        }

        return false;
    }
}
