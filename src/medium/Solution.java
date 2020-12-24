package medium;

import java.util.Stack;

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

    /**
     * 71. 简化路径
     * 代码 输入/... 输出 /
     * 但是测试用例输入 /... 输出 /...
     * 这河狸嘛
     * */
    public String simplifyPath(String path) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<path.length();i++)
        {
            Character cur = path.charAt(i);
            if(cur == '/')
            {
                if(!st.empty() && (st.peek()=='/'))
                    continue;
                if (!st.empty() && st.peek() == '.'){
                    st.pop();
                    continue;
                }
                st.push(cur);
            }
            else if(cur == '.')
            {
                if(!st.empty() && st.peek()=='.'){
                    if(st.size()==2)  //根目录
                    {
                        st.pop();
                        continue;
                    }
                    //pop两个“/”
                    int count=0;
                    while (count<2)
                    {
                        if(st.peek() == '/')
                        {
                            count++;
                        }
                        st.pop();
                    }
                }
                else
                    st.push(cur);
            }

            //普通字符
            else {
                st.push(cur);
            }
        }
        if((st.get(st.size()-1) == '/' && st.size() > 1) || st.get(st.size()-1)=='.')
            st.pop();

        String re = "";
        while (!st.empty()){
            Character tmp = st.peek();
            re += String.valueOf(tmp);
            st.pop();
        }
        return new StringBuffer(re).reverse().toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.waysToChange(10));
    }
}
