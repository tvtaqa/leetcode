package easy;

import sun.util.resources.cldr.zh.CalendarData_zh_Hans_HK;

import javax.naming.InitialContext;
import java.util.*;

import static sun.swing.MenuItemLayoutHelper.max;


/**
 * 155. 最小栈
 * 由于需要记录当前栈中最小的元素，所以push的时候，将当前值和最小值都push进去
 * pop的时候，pop两次
 * 栈顶元素是倒数第二个元素
 * 栈中最小值就是栈顶的值
 * */
class MinStack {
    Stack<Integer> st =  null;
    /** initialize your data structure here. */
    public MinStack() {
        st = new Stack<>();
    }

    public void push(int x) {
        int top; //当前最小值
        if(!st.empty()){
            top  = st.peek();
            if(top < x)
            {
                st.push(x);
                st.push(top);
            }
            else {
                st.push(x);
                st.push(x);
            }
        }
        else
        {
            st.push(x);
            st.push(x);
        }
    }

    public void pop() {
        st.pop();
        st.pop();
    }

    public int top() {
        return st.get(st.size()-2);
    }

    public int getMin() {
        return st.peek();

    }
}


/**
 * 232. 用栈实现队列
 * 需要用两个栈来模拟队列
 * */
class MyQueue {
    Stack<Integer> stackin;
    Stack<Integer> stackout;

    /** Initialize your data structure here. */
    public MyQueue() {
    stackin = new Stack<>();
    stackout = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stackin.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        in2out();
        return stackout.pop();

    }

    /** Get the front element. */
    public int peek() {
        in2out();
        return stackout.peek();
    }

    public void in2out() {
        if(stackout.empty()){
            while(!stackin.empty()){
                stackout.push(stackin.pop());
            }
        }

    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackin.empty() && stackout.empty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

/**
 * 225. 用队列实现栈
 * 每一次将新的元素插入队列后，将前面的元素全部出队，再入队，这样就能模拟Stack的后进先出
 * */
class MyStack {
    Queue<Integer> queue;
    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();

    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        //将除了刚进队的元素，一一出队，再入队
        int len = queue.size()-1;
        while(len-- > 0){
            queue.add(queue.poll());
        }

    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
            return queue.remove();
    }

    /** Get the top element. */
    public int top() {
            return queue.peek() ;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();

    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */




class Solution {
    /*
    首先遍历一轮，两个两个比较，如果相同，将前面那个数置为MAX_VALUE
    第二轮 调整位置，将非MAX_VALUE的值往前挪即可。
     */
    public int removeDuplicates(int[] nums) {
        for(int i=0;i< nums.length-1;i++)
        {
            int j=i+1;
            if(nums[j] == nums[i])
                nums[i] = Integer.MAX_VALUE;
        }
        int count=0;
        for(int i=0;i< nums.length;i++)
        {
            if(nums[i] != Integer.MAX_VALUE)
            {
                nums[count] = nums[i];
                count++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return count ;
    }


    /*
    与上题一致
    *****/
    public int removeElement(int[] nums, int val) {
        for (int i=0;i<nums.length;i++)
        {
            if(nums[i] == val)
            {
                nums[i]=Integer.MAX_VALUE;
            }
        }
        int count=0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]!=Integer.MAX_VALUE)
            {
                nums[count]=nums[i];
                count++;
            }
        }
        //System.out.println(Arrays.toString(nums));
        return count;

    }
    /*
    * 应该用KMP算法
    * */
    public int strStr(String haystack, String needle) {

        return haystack.indexOf(needle);
    }

/**
 * 因为是有序的数组
 * 1.找到相等的返回下标
 * 2.第一个大于target的，就说明没有该值，插入到这个位置即可，返回下标
 * 3.如果能遍历完整个数组，target比数组中所有的值都大，插末尾，返回数组大小
 */
    public int searchInsert(int[] nums, int target) {
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]==target)
                return i;
            else if(nums[i]>target)
                return i;
        }
        return nums.length;
    }



    /*
    * 外观数列
    * */
    public String countAndSay(int n) {
        String str="1";
        for(int i=1;i<=n;i++)
        {
            for(int j=0;j<str.length();j++)
            {

            }

        }
        return "";
    }


    /**
     * easy
     * 58. 最后一个单词的长度
     * 从后往前count一下即可，遇到空格停止。
     * 注意：最后一个单词后面可能有空格，需要跳过那些空格
     */
    public int lengthOfLastWord(String s) {
        int count=0;
        boolean lastWordStart=false;
        for(int i=s.length()-1;i>=0;i--)
        {
            if(s.charAt(i)!=' '){
                lastWordStart = true;
                count++;
            }
            else if(s.charAt(i)==' ' && lastWordStart){
                break;
            }
        }
    return count;
    }


    /**
     * easy
     * 66. 加一
     * 末位+1，需要判断是否有进位
     * 注意：99+1=100，原来的数组就不能完整保存结果了
     * 如果走完整个for循环，就说明是上述的这样的情况，原数组中全是0，需要在数组的第一位上加一个0
     *
     */
    public int[] plusOne(int[] digits) {
        int i= digits.length-1;
        for(;i>=0;i--)
        {
            if(digits[i]!=9)
            {
                digits[i]++;
                break;
            }
            else
            {
                digits[i]=0;
            }
        }
        if(i==-1)
        {
            int[] newarrary = new int[digits.length+1];
            newarrary[0]=1;
            for(int j=1;j<newarrary.length;j++)
            {
                newarrary[j] = 0;
            }
            return  newarrary;
        }

        return  digits;
    }


    /**
     * 67. 二进制求和
     *
     */
    public String addBinary(String a, String b) {

        return "";
    }

    /**
     * 69. x 的平方根
     * */
    public int mySqrt(int x) {
        double result = Math.floor(Math.sqrt(x));
        return (int)result;
    }

    /**
     * 70. 爬楼梯
     * 应该是动态规划的题目。。。用递归做（效率低）
     * 如果要通过，只能用斐波那契数列
     *
     * */

    public int climbStairs(int n) {
        if(n<=2)
            return n;

        int i1=1;
        int i2=2;
        int result=0;
        for(int i=3;i<=n;i++){
            result = i1+i2;
            i1=i2;
            i2=result;
        }
        return result;
    }


    /**
     * 83. 删除排序链表中的重复元素
     * 一个个比较，如果当前和下一个节点的val相同，则cur.next = cur.next.next，直到找到val不同的节点。
     * 重复以上的步骤即可
     * 吐了。。注意空指针就行了
     * */

    public static class ListNode {
             int val;
             ListNode next;
             ListNode(int x) { val = x; }
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cursor = head;
        while (cursor!=null)
        {
            if(cursor.next == null)
                break;
            while(cursor.val == cursor.next.val){
                cursor.next = cursor.next.next;
                if(cursor.next == null) break;
            }
            cursor = cursor.next;
        }
//        while(true){
//            System.out.print(head.val+"->");
//            if(head.next != null)
//                head = head.next;
//            else
//                break;
//        }
        return  head;
    }


    /**
     * 88. 合并两个有序数组（归并排序）
     * 外层循环是第二个数组，从中每拿一个数，insert第一个数组中
     * insert的逻辑：因为是有序的，找到第一个小于的位置，然后往后挪，腾出一个位置，插入即可
     *              如果找不到，就插在最后
     *
     * */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i2 = 0;i2 < nums2.length ; i2++)
        {
            int waitForInsert = nums2[i2];
            int i1;
            for(i1 = 0;i1 < m ;i1++)
            {
                if(waitForInsert < nums1[i1]){
                    //从最后一个开始，往后挪一个位置
                    int t;
                    for(t = m-1;t>=i1;t--){
                        nums1[t+1]=nums1[t];
                    }
                    //插入该位置
                    nums1[i1]=waitForInsert;
                    m++;
                    break;
                }
            }
            if(i1>=m){
                nums1[i1] = waitForInsert;
                m++;
            }
        }
//        for(int i=0;i<m;i++)
//            System.out.println(nums1[i]+" ");
    }




//      public class TreeNode {
//          int val;
//          TreeNode left;
//          TreeNode right;
//          TreeNode() {}
//          TreeNode(int val) { this.val = val; }
//          TreeNode(int val, TreeNode left, TreeNode right) {
//              this.val = val;
//              this.left = left;
//              this.right = right;
//          }
//      }

//    public boolean isSameTree(TreeNode p, TreeNode q) {
//        boolean flag = true;
//        while(true){
//            if(p.val != q.val )
//            {
//                flag = false;
//            }
//        }
//        return true;
//    }


    /***
     * 112. 路径总和
     *
     */
//    public class TreeNode {
//          int val;
//          TreeNode left;
//          TreeNode right;
//          TreeNode(int x) { val = x; }
//      }
//
//    public boolean hasPathSum(TreeNode root, int sum) {
//        if(root == null)
//        if(sum>0)
//        {
//            hasPathSum(root.left, sum - root.val);
//            hasPathSum(root.right, sum - root.val);
//        }
//        else if(sum<0){
//            return false;
//        }
//        return true;
//    }

    /**
     * 125. 验证回文串
     * 转小写，去除非字母和数字
     * */

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("[^0-9a-zA-Z]","");
//        System.out.println("length:"+s.length());
//        System.out.println("s="+s);
        boolean flag = true;
        for(int i=0;i<s.length()/2;i++)
        {
            char chFront = s.charAt(i);
            char chEnd = s.charAt(s.length()-i-1);
            if(chFront != chEnd){
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * easy 121. 买卖股票的最佳时机
     * 一维数组的动态规划题
     * dp[i]的含义：i天的最大收益
     * 状态转移方程：dp[i]=max(dp[i-1] , 第i天 - 第i天前面最小的)
     */

//    public int maxProfit(int[] prices) {
//        if(prices.length==0)return 0;
//        int[] dp = new int[prices.length+1];
//        //初始化
//        dp[0]=-prices[0];
//        Integer minPrice = prices[0];
//        for(int i = 1 ;i < prices.length ; i++)
//        {
//            dp[i] = max(dp[i-1] , prices[i]-minPrice);
//            if(prices[i] < minPrice)
//                minPrice = prices[i];
//        }
//        if (dp[prices.length-1]<=0)
//            return 0;
//        else
//            return dp[prices.length-1];
//
//    }

    /***
     * easy 53. 最大子序和
     * 动态规划
     * dp[i]表示 以第i个数为结尾的 子数列的最大和
     * dp[i] = max(nums[i] , dp[i-1] + nums[i])
     * 同时 还需要一个变量保存最大和
     */
    int max(int i,int j){
        return (i>j?i:j);
    }
    public int maxSubArray(int[] nums) {
        if(nums.length==0)return  0;
        int[] dp = new int[nums.length];
        dp[0]=nums[0];
        int maxCount=dp[0];
        for(int i = 1;i < nums.length;i++){
            dp[i] = max( dp[i-1] + nums[i],nums[i]);
            if(maxCount < dp[i])maxCount = dp[i];

        }
        return maxCount;
    }

    /**
     *  198. 打家劫舍
     *  动态规划
     *  dp[i] 第i间房为止，最大的盗窃金额
     *  状态转移方程
     *  dp[i] = max(dp[i-1] , dp[i-2] + nums[i])
     * */
    public int rob(int[] nums) {
        if(nums.length==0)
            return 0;
        else if (nums.length==1)
            return  nums[0];
        //初始化
        int[] dp = new int[nums.length];
        dp[0]=nums[0];
        dp[1]=max(nums[0],nums[1]);
        for(int i=2;i < nums.length;i++){
            dp[i] = max(dp[i-1] , dp[i-2] + nums[i]);
        }
        return dp[nums.length-1];
    }

    /**
     * 面试题 08.01. 三步问题
     * 动态规划
     * dp[i]表示 i阶楼梯有多少种上楼的方式
     * dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
     * 需要注意 结果溢出
     * */

    public int waysToStep(int n) {
        if(n==0)return 0;
        else if(n==1)return 1;
        else if(n==2)return 2;
        //初始化
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4;i <= n;i++){
            dp[i] = ( (dp[i-1] + dp[i-2])%1000000007 + dp[i-3])%1000000007;
        }
        return dp[n];
    }

    /**
     * 面试题 17.16. 按摩师
     * 和上面的小偷是一样的
     * */

    /**
     * 300. 最长递增子序列
     * 动态规划
     * dp[i] 表示 到i为止，递增的子序列最大长度
     * dp[i] = dp
     * */

    /**
     * 1155. 掷骰子的N种方法
     * dp[d][target] 有d个骰子，和正好为target，共有多少种方法
     * 状态转移方程
     * dp[d][target] = (dp[d-1][target-j] + dp[1][j]) /2
     * */
    public int numRollsToTarget(int d, int f, int target) {
        //初始化
        int[][] dp = new int[d+1][target+1];
        for(int i=1;i<=target;i++)
        {
            if(i>f)
                dp[1][i] = 0;
            else
                dp[1][i] = 1;
        }

        for(int i=2;i<=d;i++)
        {
            for(int j=1;j<=target;j++)
            {
                for(int k=1;k<=j;k++)
                {
                    dp[i][j] += (dp[i-1][j-k] + dp[1][k]);
                }
                dp[i][j] = dp[i][j]/2;
            }
        }
        return dp[d][target];
    }


    /**
     * 434. 字符串中的单词数
     * 当前字符不为空，后一个字符为空，则认为单词数+1
     * 如果最后一个字符不是空格，则需要在结束前再加1
     * */
    public int countSegments(String s) {
        if(s.length()==0)return 0;
        int count=0;
        for(int i=0;i<s.length()-1;i++)
        {
            char cFront = s.charAt(i);
            char cLater = s.charAt(i+1);
            if(cFront!=' ' && cLater==' ')
            {
                count++;
            }
        }
        if(s.charAt(s.length()-1)!=' ')count++;
        return count;

    }

    /**
     * 20. 有效的括号
     *  熟悉一下Java的Stack类
     * */
    public boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        for(int i=0;i<s.length();i++)
        {
            String str = String.valueOf(s.charAt(i));
            if(str.equals("(")  || str.equals("{") || str.equals("[") )
            {
                stack.push(str);
            }
            else if(str.equals(")")){
                if(stack.empty() || !stack.pop().equals("("))
                    return false;
            }
            else if(str.equals("}")){
                if(stack.empty() || !stack.pop().equals("{"))
                    return false;
            }
            else if( str.equals("]")){
                if(stack.empty() || !stack.pop().equals("["))
                    return false;
            }
        }
        if (stack.empty())
            return true;
        else
            return false;
    }


    /**
     * 1544. 整理字符串
     * 思路：
     * 从前面往后扫一遍，这样替换地不完全，所以外层再套一层循环，扫完一遍再从头开始扫，直到没有替换了，再跳出循环
     * 注：
     * 1.Java中 字符串 replace函数返回一个新串，而不是在原有的基础上修改
     *
     * */
    public String makeGood(String s) {
        String newstr = s;
        while(true){
            boolean change = false;
            for(int i=0;i<newstr.length()-1;i++){
                char cFront = newstr.charAt(i);
                char cLater = newstr.charAt(i+1);
                String sub = newstr.substring(i,i+2);
                if(Math.abs(cFront-cLater) == 'a' -'A'){
                    newstr = newstr.replaceFirst(sub,"");
                    change = true;
                }
            }
            if (!change)
                break;
        }
        return  newstr;
    }

    /**
     * 1544. 整理字符串 解法2
     * 思路：
     * 使用栈，遍历字符串的时候，如果当前的字符和stack的栈顶元素是大小写关系，则pop，否则push.
     * 看栈顶的时候，需要判断是否为空
     * 最后返回结果的时候，一个一个pop。但是这样就逆序了，需要reverse。
     *
     * */
    public String makeGood_2(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            Character cur = s.charAt(i);
            if(!st.empty()) {
                Character stTop = st.peek();
                if( Math.abs(stTop-cur) == 'a' - 'A') {
                    st.pop();
                }
                else {
                    st.push(cur);
                }
            }
            else {
                st.push(cur);
            }
        }
        String re = "";
        while(!st.empty()){
            String tmp = Character.toString(st.peek());
            re = re+tmp;
            st.pop();
        }
        return  new StringBuffer(re).reverse().toString();
    }

    /**
     * 844. 比较含退格的字符串
     * 没啥值得说的。。。
     * */
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> st_1 = new Stack<>();
        Stack<Character> st_2 = new Stack<>();
        for(int i=0;i<S.length();i++)
        {
            Character cur = S.charAt(i);
            if(cur == '#' && !st_1.empty()) {
                st_1.pop();
            }
            else if (cur == '#' && st_1.empty()) ;
            else st_1.push(cur);


        }

        for(int i=0;i<T.length();i++)
        {
            Character cur = T.charAt(i);
            if(cur == '#' && !st_2.empty()) {
                st_2.pop();
            }
            else if (cur == '#' && st_2.empty());
            else st_2.push(cur);
        }

        //比较两个stack,进入循环的条件，两个stack都是非空
        while(!st_1.empty() && !st_2.empty())
        {
            if(st_1.peek() != st_2.peek())
                return false;
            else {
                st_1.pop();
                st_2.pop();
            }
        }

        //如果有一个非空，说明不等
        if(!st_1.empty() || !st_2.empty())
            return false;
        else
            return true;
    }

    /**
     * 496. 下一个更大元素 I
     * 两层遍历即可
     * */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] re = new int[nums1.length];
        for (int i=0;i<nums1.length;i++)
        {
            boolean loc = false;
            int j;
            for(j=0;j<nums2.length;j++)
            {
                if (!loc && nums2[j]==nums1[i])
                {
                    loc = true;
                    continue;
                }
                if (nums2[j] > nums1[i] && loc)
                {
                    re[i] = nums2[j];
                    break;
                }
            }
            if (j>=nums2.length)
            {
                re[i] = -1;
            }
        }
        return re;
    }

    /**
     *  1047. 删除字符串中的所有相邻重复项
     *
     *  和上面的有一题类似 (1544. 整理字符串 解法2）
     * */
    public String removeDuplicates(String S) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<S.length();i++){
            Character cur = S.charAt(i);
            if(!st.empty()) {
                Character stTop = st.peek();
                if( stTop == cur) {
                    st.pop();
                }
                else {
                    st.push(cur);
                }
            }
            else {
                st.push(cur);
            }
        }
        String re = "";
        while(!st.empty()){
            String tmp = Character.toString(st.peek());
            re = re+tmp;
            st.pop();
        }
        return  new StringBuffer(re).reverse().toString();
    }


    /**
     * 682. 棒球比赛
     * 本地运行没问题，但是leetcode上运行出错
     *
     * */
    public int calPoints(String[] ops) {
        Stack<String> st = new Stack<>();
        for (int i=0;i< ops.length;i++)
        {
            String str = ops[i];
            if(str == "C")
            {
                st.pop();
            }
            else if(str == "D"){
                String tmp= st.peek();
                int num = Integer.parseInt(tmp)*2;
                st.push(String.valueOf(num));

            }
            else if(str == "+")
            {
                String tmp1= st.get(st.size()-1);
                int front = Integer.parseInt(tmp1);
                String tmp2= st.get(st.size()-2);
                int later = Integer.parseInt(tmp2);
                st.push(String.valueOf(front+later));
            }
            else
            {
                st.push(str);
            }
        }
        int sum=0;
        while(!st.empty()){
            int num = Integer.parseInt(st.peek());
            sum += num;
            st.pop();
        }
        return sum;
    }

    /**
     * 1021. 删除最外层的括号
     * 只要进到第二层，就是要输出的内容
     * 所以可以用一个变量来记录当前的层数
     * */
    public String removeOuterParentheses(String S) {
        int count=0;
        String re="";
        for(int i=0;i<S.length();i++){
            Character ch = S.charAt(i);
            if(ch=='(')count++;
            if(count>=2)re+=String.valueOf(ch);
            if(ch==')')count--;

//            if(ch=='(')count++;
        }
        return re;

    }

    /**
     * 1. 两数之和
     * 题目中描述到 每种输入只会对应一个答案，所以数组中不会有重复的数字
     * 因此可以用hash表将之前的结果索引起来。等于一次for循环就能找到答案。
     * 空间换时间
     *
     * */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(hm.containsKey(target-nums[i])){
                return new int[]{hm.get(target-nums[i]),i};
            }
            else{
                hm.put(nums[i],i);
            }
        }
        return null;
    }


    /**
     * 217. 存在重复元素
     * 使用HashSet,自动去重
     * 如果set的大小小于数组的大小，则有重复的元素
     * */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        if(set.size()<nums.length)return true;
        else
            return  false;
    }

    /**
     * 594. 最长和谐子序列
     * 题目种不要求连续的子序列（离散的即可）
     * 第一轮for循环 对所有的数字进行计数
     * 第二轮for循环 遍历hashset中的key
     * 自身.计数 + 自身+1.计数 和 longest变量做比较
     *
     * * */
    public int findLHS(int[] nums) {
        int longest = 0;
        HashMap<Integer,Integer> hm= new HashMap<>();
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        for(int num : hm.keySet()){
            if(hm.containsKey(num+1)){
                longest  = max(longest,(hm.get(num)+hm.get(num+1)));
            }
        }
        return longest;
    }


    /**
     * 242. 有效的字母异位词
     * 如果字符串的长度不同，返回false
     * 如果字符串的长度相同，用一个26位的数组来count字母个数
     * */
    public boolean isAnagram(String s, String t) {
        if (s.length()!=t.length())
            return false;
        int[] charray_s = new int[26];
        int[] charray_t = new int[26];
        for (int i=0;i<s.length();i++){
            Character ch_s = s.charAt(i);
            Character ch_t = t.charAt(i);
            charray_s[ch_s-'a']++;
            charray_t[ch_t-'a']++;
        }
        for(int i=0;i<26;i++){
            if(charray_s[i]!=charray_t[i])
                return false;
        }
        return true;
    }

    /**
     * 409. 最长回文串
     * AAAababCCC 这样的字符串 AACC 还是能用来构成回文串的
     * 并不是奇数个的字符就不可以
     * 最后如果longest小于原字符串的长度，就可以再加一个单独的字符
     *
     * */
    public int longestPalindrome(String s) {
        int longest=0;
        int[] charray = new int[58];
        for(Character ch : s.toCharArray()){
            charray[ch-'A']++;
        }
        for(int i=0;i<58;i++){
            if(charray[i]%2==0)
                longest+=charray[i];
            else {
                longest+=(charray[i]/2)*2;
            }
        }
        if(longest<s.length())
            longest++;
        return longest;
    }

    /**
     * 205. 同构字符串
     * 由于字符串的长度是相同的，从前面开始扫一遍
     * 如果发现当前的字符之前出现过，就看一下之前出现的索引
     * 另一个字符串的当前字符也应该出现过，且索引相同
     *
     * 所以用Hashmap存字符和上一次出现的索引
     * */
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Integer> smap = new HashMap<>();
        Map<Character,Integer> tmap = new HashMap<>();
        for(int i=0;i<s.length();i++){
            Character ch_s = s.charAt(i);
            Character ch_t = t.charAt(i);
            //前一个字符串中出现和之前相同的字符
            if(smap.containsKey(ch_s)){
                int index_s = smap.get(ch_s);
                if(!tmap.containsKey(ch_t) || tmap.get(ch_t)!=index_s)
                    return false;
            }
            if(tmap.containsKey(ch_t)){
                int index_t = tmap.get(ch_t);
                if(!smap.containsKey(ch_s) || smap.get(ch_s)!=index_t)
                    return false;
            }
            smap.put(ch_s,i);
            tmap.put(ch_t,i);
        }
        return  true;
    }

    /**
     * 9. 回文数
     * */
    public boolean isPalindrome(int x) {
        String str = Integer.toString(x);
        String reverseStr = new StringBuffer(str).reverse().toString();
        if(reverseStr.equals(str) )
            return true;
        else
            return  false;
    }


    /**
     * 283. 移动零
     *
     * */
    public void moveZeroes(int[] nums) {
        int index = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[index++] = nums[i];
            }
        }
        for(int i=nums.length-1;i>=index;i--){
            nums[i] = 0;
        }
    }


    /**
     * 566. 重塑矩阵
     *
     * */

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(nums[0].length*nums.length != r*c)
        {
            return nums;
        }
        else{
            //System.out.println(nums[0].length);
            int[][] re = new int[r][c];
            int old_r,old_c,count;
            old_r=old_c=count=0;
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    re[i][j]=nums[old_r][old_c];
                    old_c++;
                    count++;
                    if(count%nums[0].length==0){
                        old_r++;
                        old_c=0;
                    }

                }
            }
            return  re;
        }
    }


    /**
     * 485. 最大连续1的个数
     * */
    public int findMaxConsecutiveOnes(int[] nums) {
        int count=0;
        int maxcount=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==1){
                count++;
                if(count>maxcount) {
                    maxcount = count;
                }
            }
            else{
                count=0;
            }
        }
        return maxcount;
    }




    /**
     * 645. 错误的集合
     * 使用数组桶，专治花里胡哨
     * */
    public int[] findErrorNums(int[] nums) {
        int[] bucket = new int[nums.length+1];
        int[] re = new int[2];
        for(int i :nums){
            bucket[i]++;
        }
        for(int i=1;i<nums.length+1;i++){
           if(bucket[i] == 2)
               re[0] = i;
           else if(bucket[i] == 0)
               re[1] = i;
        }

        return re;
    }

    /**
     * 697. 数组的度
     * 使用三个hashmap，分别存出现的次数，最后出现的索引，首次出现的索引
     * */
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer,Integer> maxcount = new HashMap<>();
        HashMap<Integer,Integer> lastIndex = new HashMap<>();
        HashMap<Integer,Integer> firstIndex = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            maxcount.put(nums[i],maxcount.getOrDefault(nums[i],0)+1);
            lastIndex.put(nums[i],i);
            if(!firstIndex.containsKey(nums[i])){
                firstIndex.put(nums[i],i);
            }
        }

        //确定度
        int degree = -1;
        for(int key : maxcount.keySet()){
            degree = Math.max(degree,maxcount.get(key));
        }
        int re = Integer.MAX_VALUE;
        for(int key:maxcount.keySet()){
            if(degree == maxcount.get(key))
                re = Math.min(re,lastIndex.get(key) - firstIndex.get(key) + 1);
        }
        return re;

    }

    /**
     * 766. 托普利茨矩阵
     * 首行 第i个元素斜着往下扫
     * 首列 第i个元素斜着往下扫
     * */
    public boolean isToeplitzMatrix(int[][] matrix) {
        //首行往下扫
        for(int i=0;i<matrix[0].length;i++){
            int row,col,tmp;
            row=0;
            col=i;
            tmp=matrix[row][col];
            while(row < matrix.length && col< matrix[0].length){
                if(tmp != matrix[row][col])
                    return false;
                row++;
                col++;
            }
        }

        //首列往下扫
        for(int i=1;i<matrix.length;i++){
            int row,col,tmp;
            row=i;
            col=0;
            tmp=matrix[row][col];
            while(row < matrix.length && col< matrix[0].length){
                if(tmp != matrix[row][col])
                    return false;
                row++;
                col++;
            }
        }
        return true;
    }



    public static void main(String[] args){

        Solution s = new Solution();
        int[] nums = {1,2,2,3,1,4,2};
        String[] str = {"5","2","C","D","+"};
        System.out.println(s.findShortestSubArray(nums));
        String str_1="abccccdd";
        String str_2="car";
        //System.out.println(s.searchMatrix(num,5));
        //String str= "abbaca";
        //System.out.println(s.("/.."));
        //System.out.println();
    }
}