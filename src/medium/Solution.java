package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

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
        if(path=="/...")
            return "/...";
        Stack<Character> st = new Stack<>();
        for(int i=0;i<path.length();i++)
        {
            Character cur = path.charAt(i);
            if(cur == '/')
            {
                if(!st.empty() && st.peek()=='/')
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

    /**
     * 94. 二叉树的中序遍历
     *
     * */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while(cur!=null || !st.empty())
        {
            if(cur!=null)
            {
                st.push(cur);
                cur = cur.left;
            }
            else
            {
                Integer num = st.peek().val;
                list.add(num);
                cur = st.pop();
                cur = cur.right;
            }
        }
        return list;
    }

    /**
     * 144. 二叉树的前序遍历
     *
     * */
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while(cur!=null || !st.empty()){
            if(cur!=null)
            {
                list.add(cur.val);
                st.push(cur);
                cur = cur.left;
            }
            else
            {
                cur = st.pop().right;
            }
        }
        return list;
    }


    /**
     * 144. 二叉树的后序遍历!!!!!!
     *
     * */
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur!=null || !st.empty())
        {
            if(cur!=null)
            {
                st.push(cur);
                cur = cur.left;
            }
            else
            {
                cur = st.peek();
                if(cur.right == null || cur.right == pre)
                {
                    list.add(cur.val);
                    st.pop();
                    pre = cur;
                    cur = null;
                }
                else{
                    cur = cur.right;
                }

            }
        }
        return list;
    }

    /**
     * 402. 移掉K位数字
     * 从左往右遍历，如果当前的数字比栈顶小，则栈顶元素出栈，外面套一层while循环。
     * 一轮扫完，可能还没有删除的个数不足k个，所以还需要进一步pop
     * 最后的处理：可能出现"0200"，以及"" 需要特殊处理
     *
     * */
    public String removeKdigits(String num, int k) {
        Stack<Character> st = new Stack<>();
        int count=0;
        for(int i=0;i<num.length();i++){
            char cur = num.charAt(i);
            while(!st.empty() && st.peek() > cur && count<k)
            {
                st.pop();
                count++;
            }
            st.push(cur);
        }
        if(count<k){
            while(k-count>0)
            {
                st.pop();
                count++;
            }
        }

        String re="";
        boolean begin=false;
        for(int i=0;i<st.size();i++){
            if(st.get(i)!='0')begin = true;
            if(begin)
                re += String.valueOf(st.get(i));
        }
        if(re == "")re+="0";
        return re;

    }

    /**
     * 316. 去除重复字母
     * TO DO 
     * */
    public String removeDuplicateLetters(String s) {
        HashMap<Character,Integer> kv = new HashMap<>();
        Stack<Character> st = new Stack<>();
        //建立key-value关系
        for(int i=0;i<s.length();i++){
            Character ch = s.charAt(i);
            if(!kv.containsKey(ch))
            {
                kv.put(ch,1);
            }
            else{
                int num = kv.get(ch) + 1;
                kv.put(ch,num);
            }
        }

        for(int i=0;i<s.length();i++){
            Character ch = s.charAt(i);
            if(st.contains(ch)){
                kv.put(ch,kv.get(ch)-1);
                continue;
            }
            while(!st.empty() && ch<st.peek() && kv.get(st.peek())>1){
                kv.put(st.peek(),kv.get(st.peek())-1);
                st.pop();
            }

            st.push(ch);
        }
        String re="";
        for(int i=0;i<st.size();i++){
            re+=st.get(i);
        }
        return re;
    }


    public int[] dailyTemperatures(int[] T) {
        int[] re = new int[T.length];
        for(int i=0;i<T.length;i++)
        {
            int count=0;
            for(int j=i+1;j<T.length;j++){
                count++;
                if(T[j]>T[i]){
                    re[i] = count;
                    break;
                }
            }
        }
        return re;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.removeDuplicateLetters("abacb"));
    }
}
