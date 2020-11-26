import java.util.*;
import java.io.*;

public class catchingCheaters {
    static int[][] dp;

    //To find similarity score of all substrings of string a and b
    static int lcs(String a, String b, int al, int bl) {
        dp = new int[al+1][bl+1];// used to store similarity score
        int max = 0;// to keep track of max similarity score
        for (int i = 0; i < al; i++) {
            dp[i][0] = -i;
        }
        for (int j = 0; j < bl; j++) {
            dp[0][j] = -j;
        }
        for (int i = 1; i <= al; i++) 
            for (int j = 1; j <= bl; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    //if Ai and Bj both same letter, 
                    //concatenating Ai to Ai-1 and Bj to Bj-1
                    //will increment dp[i-1][j-1] by 2
                    dp[i][j] = Math.max(2, dp[i-1][j-1] + 2);
                    if (dp[i][j] > max) max = dp[i][j];
                } else {
                    //if Ai and Bj not the same letter, then
                    //concatenating Ai to Ai-1 will increase dp[i-1][j] by 1
                    //like wise conc Bj to Bj-1 will increase dp[i][j-1] by 1
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]) - 1;
                    if (dp[i][j] > max) max = dp[i][j];
                }
            }
        return max; 
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(System.out)));
        String[] fl = br.readLine().split(" ");
        int al = Integer.parseInt(fl[0]);
        int bl = Integer.parseInt(fl[1]);
        String a = br.readLine();
        String b = br.readLine();
        int out = lcs(a, b, al, bl);
        pw.print(out);
        pw.close();
    }
}
