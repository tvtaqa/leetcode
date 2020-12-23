package medium;

public class Solution {

    /**
     * 面试题 08.11. 硬币
     * 动态规划
     * dp[i] 表示币值为i有多少种分法
     * 状态转移方程如下：
     * dp[i] = dp[i-1] + dp[i-5] + dp[i-10] + dp[i-25]
     * */
    private final int mod = 1000000007;
    private final int[] coins = {25,10,5,1};

    public int waysToChange(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        for(int coin : coins){
            for(int i = coin;i <= n;i++){
                res[i] = (res[i] + res[i - coin]) % mod;
            }
        }
        return res[n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.waysToChange(10));
    }
}
