import java.util.Arrays;

class Solution {
    public int solution(int x, int y, int n) {
        int[] dp = new int[1000001];
        Arrays.fill(dp, 9999999);
        dp[x] = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == 9999999) continue;
            else {
                if (i + n < dp.length) dp[i + n] = Math.min(dp[i] + 1, dp[i + n]);
                if (2 * i < dp.length) dp[2 * i] = Math.min(dp[i] + 1, dp[2 * i]);
                if (3 * i < dp.length) dp[3 * i] = Math.min(dp[i] + 1, dp[3 * i]);
            }
        }
        return dp[y] == 9999999 ? -1 : dp[y];
    }
}