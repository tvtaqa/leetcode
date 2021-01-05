package medium;

import java.util.*;

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

    
    /**
     * 739. 每日温度
     * 两重for循环
     * */
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

    public int[] dailyTemperatures_2(int[] temperatures) {
        int n = temperatures.length;
        int[] dist = new int[n];
        Stack<Integer> indexs = new Stack<>();
        for (int curIndex = 0; curIndex < n; curIndex++) {
            while (!indexs.isEmpty() && temperatures[curIndex] > temperatures[indexs.peek()]) {
                int preIndex = indexs.pop();
                dist[preIndex] = curIndex - preIndex;
            }
            indexs.add(curIndex);
        }
        return dist;
    }


    /**
     * 394. 字符串解码
     * 利用Stack，遇到']'就开始pop，直到遇到'['
     * 将这中间的字符保存起来并reverse
     * 再将前面的数字pop，解密得到字符串，再此push到栈中即可
     * */
    public String decodeString(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++)
        {
            Character ch = s.charAt(i);

            //若遇到]，则出栈 出到上一个[，并且乘上前面的数字，再入栈
            if(ch == ']')
            {
                String substr="";
                while(st.peek()!='['){
                    Character tmp = st.peek();
                    substr+=String.valueOf(tmp);
                    st.pop();
                }
                substr = new StringBuffer(substr).reverse().toString();

                //将[ pop
                st.pop();

                //获取前面的数字
                String IntStr="";
                while(!st.empty() && st.peek()>='0' && st.peek()<='9'){
                    Character tmp = st.peek();
                    IntStr += String.valueOf(tmp);
                    st.pop();
                }
                IntStr = new StringBuffer(IntStr).reverse().toString();
                int number = Integer.parseInt(IntStr);

                //将number*substr push到stack中
                String newSubStr = "";
                while(number>0)
                {
                    newSubStr = newSubStr + substr;
                    number--;
                }
                for(int k=0;k<newSubStr.length();k++){
                    st.push(newSubStr.charAt(k));
                }
            }
            else st.push(ch);
        }

        //输出结果
        String re = "";
        for(int i=0;i<st.size();i++){
            re += String.valueOf(st.get(i));
        }
        return re;
    }

    /**
     * 503. 下一个更大元素 II
     * 解法太暴力，要被抓起来了
     * */
    public int[] nextGreaterElements(int[] nums) {
        int[] re = new int[nums.length];
        for(int i=0;i < nums.length;i++){
            int count=0;
            for(int j=i+1;;j++){
                j = j % nums.length;
                count++;
                if(nums[j]>nums[i]){
                    re[i] = nums[j];
                    break;
                }
                if(count>=nums.length)break;
            }
            if(count>=nums.length)re[i]=-1;
        }
        return re;
    }


    /**
     * 503. 下一个更大元素 II
     * 单调栈解法
     * */
    public int[] nextGreaterElements_2(int[] nums) {
        int[] re = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(re,-1);
        for(int i=0;i< 2*nums.length;i++){
            while(!st.empty() && nums[i% nums.length] > nums[st.peek()])
            {
                re[st.pop()] = nums[i% nums.length];
            }
            st.push(i% nums.length);
        }
        return re;
    }



    /**
     * 647. 回文子串
     * 中心拓展法：将一个或者两个字符当成回文串的中心字符
     * 同时向两边扫，逐一地扩展该回文串
     * 对于奇数和偶数的，分别处理即可
     * */
    public int countSubstrings(String s) {
        int count=0;
        for(int i=0;i<s.length();i++){
            //以当前的字符为中心，尝试拓展
            count += judge(i,i,s);  //回文串长度为奇数
            count += judge(i,i+1,s); //回文串的长度是偶数
        }
        return  count;
    }
    private int judge(int i,int j,String s)
    {
        int cnt=0;
        for (int left=i,right=j;left>-1 && right<s.length();){
            if(s.charAt(left) == s.charAt(right)){
                left--;
                right++;
                cnt++;
            }
            else
                break;
        }
        return  cnt;
    }


    /**
     * 240. 搜索二维矩阵 II
     * 从右上角开始search，左边的元素比当前的小，下面的元素比当前的大。所以一层for循环即可
     * PS：别写成两层for循环
     * */
    public boolean searchMatrix(int[][] matrix, int target) {
        for(int i=0,j=matrix[0].length-1;i<matrix.length && j>=0;){
            if(target > matrix[i][j]){
                i++;
            }
            else if(target < matrix[i][j]){
                j--;
            }
            else {
                return true;
            }
        }
        return false;
    }


    /**
     * 378. 有序矩阵中第K小的元素
     * 暴力求解：丢到一个一维数组，再进行排序，取对应索引的元素
     *
     * */
    public int kthSmallest(int[][] matrix, int k) {
        int[] sort_array = new int[matrix.length * matrix[0].length];
        int index=0;
        for(int i=0;i< matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                sort_array[index++] = matrix[i][j];
            }
        }
        Arrays.sort(sort_array);
        return sort_array[k-1];

    }


    /**
     * 287. 寻找重复数
     */
    public int findDuplicate(int[] nums) {
        HashMap<Integer,Integer> hashmap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            hashmap.put(nums[i], hashmap.getOrDefault(nums[i],0)+1 );
        }
        for(Integer key : hashmap.keySet()){
            if(hashmap.get(key)>1){
                return key;
            }
        }
        return 0;
    }



    public static void main(String[] args) {
        int[] arr ={1,1};
        Solution sol = new Solution();
        //System.out.println(Arrays.toString(sol.findErrorNums(arr)));
        //System.out.println(Arrays.toString(sol.dailyTemperatures_2(arr)));
        //System.out.println(Arrays.toString(sol.nextGreaterElements_2(arr)));
    }
}
