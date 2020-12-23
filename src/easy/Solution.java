package easy;

import java.util.Arrays;

import static sun.swing.MenuItemLayoutHelper.max;

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


    public static void main(String[] args){

        Solution s = new Solution();
        int[] price = {2,7,9,3,1};
        System.out.println(s.countSegments(""));
        //System.out.println();
    }
}