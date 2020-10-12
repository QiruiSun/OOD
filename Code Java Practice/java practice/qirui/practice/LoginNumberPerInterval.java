package qirui.practice;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

// There should be a better way of doing this!!!
public class LoginNumberPerInterval {
    public int[][] sessionNum(int[][] logins) {
        // Write your solution here
        // sort logins 
        Arrays.sort(logins, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o2[0] == o1[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        int[] merged = {};
        for (int i=0; i<logins.length; i++) {
            merged = merge(merged, logins[i]);
        }
        List<int[]> intervals = new ArrayList<>();
        int index = 0, prev = merged[0];
        while (index < merged.length) {
            while (index < merged.length && merged[index] == prev) {
            index++;
            }
            if (index < merged.length) {
                int[] session = {prev, merged[index], 0};
                intervals.add(session);
                prev = merged[index++];
            }
        }

        
        // System.out.println(Arrays.toString(intervals));    

        for (int i=0; i<logins.length; i++) {
            int left = logins[i][0], right = logins[i][1];
            for (int[] session: intervals) {
                if (session[0] >=left && session[1] <= right) {
                    session[2]++;
                }
                else if (session[0] > right) {
                    break;
                }
            }
        }

  

        List<int[]> preOutput = new ArrayList<>();    
        int[] pre = intervals.get(0);
        for (int i=0;i<intervals.size(); i++) {
            if (intervals.get(i)[2] > 0) {
                int[] session = intervals.get(i);
                if (session[0] == pre[1] && session[2] == pre[2]) {
                    int[] mergedSession = {pre[0], session[1], session[2]};
                    // output[i-1] = mergedSession;
                    preOutput.set(preOutput.size() - 1, mergedSession);
                    pre = mergedSession;
                }
                else {
                    // output[i] = session;
                    preOutput.add(session);
                    pre = session;
                }
                
            }
        }        

        int[][] output = new int[preOutput.size()][];

        for (int i=0;i<preOutput.size(); i++) {
            output[i] = preOutput.get(i);
        }

        for (int i = 0; i < output.length; i++) {
            System.out.println(Arrays.toString(output[i]));
        }

        return output;
    }

    private int[] merge(int[] a1, int[] a2) {
        int[] merged= new int[a1.length + a2.length];
        int left = 0, right = 0;
        for (int i = 0; i < merged.length; i++) {
            if (left == a1.length) {
                merged[i] = a2[right++];
            }
            else if (right == a2.length) {
                merged[i] = a1[left++];
            }
            else if (a1[left] > a2[right]) {
                merged[i] = a2[right++];
            }
            else {
                merged[i] = a1[left++];
            }
        }
        return merged;
    }

    
}