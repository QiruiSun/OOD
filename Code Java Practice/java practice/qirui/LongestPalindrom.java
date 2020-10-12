// Needs attention!!!!!!!!!!!!!!!!
package qirui;

public class LongestPalindrom {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        String res = "";
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (s.charAt(j) == s.charAt(i)) {
                    if (i - j == 1) {
                        dp[j][i] = true;
                    } else if (i - j >= 2){
                        dp[j][i] = dp[j + 1][i - 1];
                    }
                }
                
                if (dp[j][i]) {
                    if (res.length() < (i - j + 1)) {
                        res = s.substring(j, i + 1);
                    }
                }
            }
        }
        return res;
    }
}