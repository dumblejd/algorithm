package algorithmtest;

import java.lang.reflect.Array; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class DP {

	public static void dfs_uniquepath(int row,int col,int x,int y,Map<String,Integer>m,String temp)
	{
		if(x==col-1&&y==row-1)
		{
			m.put(new String(temp), 1);

			return;
		}
		while(y<(row-1)||x<(col-1))
		{
			if(x<col-1)
			{
				dfs_uniquepath(row, col, x+1, y, m, temp+"right!");
			}
			if(y<row-1)
			{
				dfs_uniquepath(row, col, x, y+1, m, temp+"down!");
			}
				return;
		}
	}
	public static void dfs_uniquepath_two(int [][]b,int x, int y,Map<String,Integer>m,String temp)
	{
		if(b[x][y]==1)
		{
			return;
		}
		if(x==b.length-1&&y==b[0].length-1)
		{
			m.put(new String(temp), 1);

			return;
		}
		while(y<(b[0].length-1)||x<(b.length-1))
		{
			if(x<b.length-1)
			{
				dfs_uniquepath_two(b, x+1, y, m, temp+"right!");
			}
			if(y<b[0].length-1)
			{
				dfs_uniquepath_two(b, x, y+1, m, temp+"down!");
			}
				return;
		}
	}
	public int dp_uniquePaths(int m, int n) {
        if (m==0 || n==0) return 0;
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++){
            dp[i][0] = 1;
            for(int j=1;j<n;j++){
                if (i==0) dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
	public static void dfs_minsum(int [][]b,int x,int y,int sum,int min)
	{
		if(x==b.length-1&&y==b[0].length-1)
		{
			if(sum<min)
			{
				min=sum;
				System.out.println(min);
			}

			return;
		}
		while(y<(b[0].length-1)||x<(b.length-1))
		{
			if(x<b.length-1)
			{
				dfs_minsum(b, x+1, y, sum+b[x][y],min);
			}
			if(y<b[0].length-1)
			{
				dfs_minsum(b, x, y+1,sum+b[x][y],min);
			}
				return;
		}
	}
//	public static void dp_minsum(int [][]b)  发现下面写过了 就直接注释了
//	{
//		int [][]dp = new int[b.length][b[0].length];
//		for(int i=0;i<b.length;i++)
//		{
//			for(int j=0;j<b[0].length;j++)
//			{
//				dp[i][j] = b[i][j]+Math.min(dp[i-1][j], dp[i][j-1]);
//			}
//		}
//	}
	public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m==0||n==0) return 0;
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++){
                if (i==0 && j==0) dp[0][0] = grid[0][0];
                else dp[i][j] = grid[i][j] + Math.min((i>0?dp[i-1][j]:Integer.MAX_VALUE),(j>0?dp[i][j-1]:Integer.MAX_VALUE));
            }
        return dp[m-1][n-1];       
        }
	public static void dp_climbstair(int n)
	{
		int[]dp = new int[n+1];
		for(int i=1;i<n;i++)
		{
			if(i==1||i==2||i==0)
			{
				dp[i]=i;
			}
			else
			{
				dp[i]=dp[i-2]+dp[i-1];
			}
		}
	}
	public static void dp_triangle(int [][]c)
	{
		int [][] dp = new int [c.length][c.length];
		dp[0][0] = c[0][0];
		for(int i=1;i<c.length;i++)
		{
			for(int j=0;j<c[i].length;j++)
			{
				dp[i][j] = c[i][j] + Math.min((i!=j?dp[i-1][j]:Integer.MAX_VALUE),(j>0?dp[i-1][j-1]:Integer.MAX_VALUE));
			}
		}
		printints(dp);
	}
	public static void printints(int[][] b)
	{
		for(int j = 0;j<b.length;j++)
		{
			for(int k=0;k<b[j].length;k++)
			{
				System.out.print(b[j][k]+" ");
			}
			System.out.println();
		}
	}
	public static void dp_sum(int[]num)
	{
		int []result= new int[num.length];
		result[0] = num [0];
		for(int i=1;i<num.length;i++)
		{
			if(result[i-1]>0)
			{
				result[i] = num[i] + result[i-1];
			}
			else
			{
				result[i] = num[i];
			}
		}
	}
	
//	如果  word1[i] = word2[j]   则   dp[i][j]   =   dp[i-1][j-1]
//
//			如果  word1[i] != word2[j]    则   dp[i][j] =   min   { dp[i-1][j]  ,  dp[i][j-1],    dp[i-1][j-1] }  + 1
	public static void dp_editdistance(String sample, String target)  
	{
		int [][]dp = new int[sample.length()+1][target.length()+1];
		for(int i=0;i<sample.length();i++)
		{
			for(int j=0;j<target.length();j++)
			{
				if(j==0||i==0)
				{
					dp[i][j]=i==0?j:0+j==0?i:0;
				}
				else if(sample.charAt(i)==target.charAt(j))
				{
					dp[i][j] = dp[i-1][j-1];
				}
				else
				{
					 int add_delete = Math.min(dp[i-1][j],dp[i][j-1]);
					 dp[i][j] = Math.min(add_delete, dp[i-1][j-1]+1);
				}
			}
		}
		System.out.println(dp[sample.length()-1][target.length()-1]);
	}
	public static boolean recur_scramble(String sample,String target)
	{
		if(sample.length()!=target.length())
		{
			return false;
		}
		char []t1 = sample.toCharArray();
		char []t2 = target.toCharArray();
		Arrays.sort(t1);
		Arrays.sort(t2);
		if(!String.valueOf(t1).equals(String.valueOf(t2)))
		{
			return false;
		}
		if(sample.equals(target))
		{
			return true;
		}
		for(int i=1;i<sample.length();i++)
		{
			if(recur_scramble(sample.substring(0, i),target.substring(0, i))
					&&recur_scramble(sample.substring(i),target.substring(i))
					)
			{
				return true;
			}
			else if(recur_scramble(sample.substring(i),target.substring(0,sample.length()-i))
					&&recur_scramble(sample.substring(0,i),target.substring(sample.length()-i))
					)
			{
				return true;
			}
			
		}
		return false;
	}
	public static boolean dp_scramble(String s1,String s2)//没完成···边界值比较复杂的  
	{
		boolean dp[][][] = new boolean [s1.length()][s2.length()][s1.length()];
		for(int len=2;len<s1.length();len++)   
		{
			for(int i=0;i<s1.length()-len;i++)
			{
				for(int j=0;j<s2.length()-len;j++)
				{
					for(int k=1;k<len;k++)//k代表每个间隔都切成两部分来比较  i 和 j 开始的len长度的字符串   分成所有可能的两部分 记录
					{
						dp[i][j][len]|=(dp[i][j][k]&&dp[i+k][j+k][len-k])||(dp[i][j+len-k][k]&&dp[i+k][j][len-k]);  //和递归的逻辑是一样的 思维不一样 仔细想想
					}
				}
			}
		}
		return false;
	}
	//416. Partition Equal Subset Sum
	 public boolean canPartition(int[] nums) {
		 int sum=0;
	        for(int i:nums) {
	        	sum+=i;
	        }
	        if(sum%2==1)
	        {
	        	return false;
	        }
	     int half=sum/2;
	     boolean [][]dp=new boolean[nums.length+1][sum+1];
	     dp[0][0]=true;
	     // 第一列
	        for (int i = 1;i<=nums.length; i++) {
	            dp[i][0] = true;
	        }
	        
	        // 第一行
	        for (int j = 1; j<=sum; j++) {
	            dp[0][j] = false;
	        }
	     for(int i=1;i<=nums.length;i++)
	     {
	    	 for(int j=1;j<=sum;j++)
	    	 {
	    		if(nums[i-1]<=j)
	    		{
	    			dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i-1]];
	    		}
	    		else
	    		{
	    			dp[i][j] = dp[i - 1][j];
	    		}
	    	 }
	     }
	     return dp[nums.length][half];
	    }
	 /** https://www.lintcode.com/problem/high-capacity-backpack/description
	     * @param s: The capacity of backpack
	     * @param v: The value of goods 
	     * @param c: The capacity of goods
	     * @return: The answer
	     */
	 public long getMaxValue(int s, int[] v, int[] c) {
	        // Write your code here
	    	//first try with 1-d array  failed
	    	//second try with 2-d array 
	    	int [][]dp=new int [v.length+1][s+1];
	    	//means first i item with capacity j can have most value
	    	
	    	Arrays.fill(dp[0], 0);
	    	
	    	for(int i=1;i<=v.length;i++)
	    	{
	    		for(int j=0;j<=s;j++)
	    		{
	    			if(j==0)
	    			{
	    				dp[i][j]=0;
	    				continue;
	    			}
	    			dp[i][j]=dp[i-1][j];
	    			if(c[i-1]<=j)
	    			{
	    				dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-c[i-1]]+v[i-1]);
	    			}
	    		}
	    	}
	    	return dp[v.length][s];
	    }
	 public long getMaxValue_o_nSpace(int C, int[] v, int[] c) { //one dimension 
		 int []dp=new int[C+1];
		 Arrays.fill(dp, 0);
		 for(int i=1;i<=v.length;i++)
		 {
			 for(int j=C;j>0;j--)
			 {
				 if(c[i-1]<=j)
				 {
					dp[j]=Math.max(dp[j],dp[j-c[i-1]]+v[i-1]);
				 }
				 else
				 {
					dp[j]=dp[j];
				 }
			 }
		 }
		 return dp[C];
	 }
	 //718 Maximum Length of Repeated Subarray
	 //without dp
	 public int findLength(int[] A, int[] B) {
	       // for(int i=0;i<A.length;)
		 return 0;
	    }
	//416. Partition Equal Subset Sum
		 public static boolean tcanPartition(int[] nums) {
			 int sum=0;
		        for(int i:nums) {
		        	sum+=i;
		        }
		        if(sum%2==1)
		        {
		        	return false;
		        }
		     int half=sum/2;
		     boolean [][]dp=new boolean[nums.length+1][half+1];
		     dp[0][0]=true;
		     // 第一列
		        for (int i = 1;i<=nums.length; i++) {
		            dp[i][0] = true;
		        }
		        
		        // 第一行
		        for (int j = 1; j<=half; j++) {
		            dp[0][j] = false;
		        }
		        
		     for(int i=1;i<=nums.length;i++)
		     {
		    	 for(int j=1;j<=half;j++)
		    	 {
	                 if(j>=nums[i-1])
	                 {
		    		    dp[i][j]=dp[i-1][j-nums[i-1]];
	                 }
	                 else
	                 {
	                     dp[i][j] = dp[i - 1][j];
	                 }
		    	 }
		     }
		     return dp[nums.length][half];
		    }
		 //#494
		//dp
		    public int findTargetSumWays(int[] nums, int S) {
		        int sum=0;
		        for(int i:nums)
		        {
		            sum+=i;
		        }
		        if(S>sum||S<-1*sum)
		        {
		            return 0;
		        }
		        //represent ways of previous i items to sum to j
		        //because it's -sum to sum  we should have sum*2+1
		        int[][]dp= new int[nums.length+1][sum*2+1];
		        //minus dp[i][j]+=dp[i-1][j+nums[i]];
		        //add  dp[i][j]+=dp[i-1][j-nums[i]]
		        dp[0][sum]=1;
		        for(int i=1;i<=nums.length;i++)
		        {
		            for(int j=0;j<2*sum+1;j++)
		            {
		                if(j+nums[i-1]<2*sum+1)
		                {
		                    dp[i][j]+=dp[i-1][j+nums[i-1]];
		                }
		                if(j-nums[i-1]>=0)
		                {
		                    dp[i][j]+=dp[i-1][j-nums[i-1]];
		                }
		            }
		        }
		        return dp[nums.length][sum+S];
		            
		    }
		    
//		    518 coin change
//		    应该理解成 前i-1个coin形成的数字n  在 第i个coin加入后 可以形成
//		    num[n]+num[n-coin[i]] 这个视频说的听清楚的
//		    https://www.youtube.com/watch?v=jaNZ83Q3QGc
		    
//		    377 combination sum IV   !!!结合coin change2  看！！！
//		    可重复组合 target.  挺难理解的
//		    理解成  如果要4  过程就是  有一个1打头的话数量就是 和为3的方法数   有一个2打头的话就是和为2的方法数，有一个3打头的话  就是和为1的方法数

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,Integer> m = new HashMap<String,Integer>();
		int [][]b = {{1,2,3},{4,1,2},{7,5,9}};
		
		//dfs_uniquepath(3,7,0,0,m,"");
		//dfs_uniquepath_two(b, 0, 0, m, "");
		int min=9999;
		//dfs_minsum(b, 0, 0, 0, min);
//		int [][]t = {{2},
//			    {3,4},
//			    {6,5,7},
//			   {4,1,8,3}};
//		dp_triangle(t);
		//System.out.println(min);
		int []num = {-2,1,-3,4,-1,2,1,-5,4};
		 int []t= {18,40,62,33,83,64,10,92,67,31,42,51,10,97,41,7,82,71,80,81,41,38,88,25,38,89,24,89,90,12,97,21,22,85,11,59,83,6,51,47,32,82,83,100,29,78,36,32,1,31,36,19,35,3,36,21,24,93,42,33,10,26,2,39,36,62,85,24,41,5,66,53,7,1,59,53,40,59,41,95,7,67,20,29,80,59,49,49,51,90,13,52,6,90,5,6,31,81,95,26};
		 Queue<Integer> q =new LinkedList<Integer>();
		 tcanPartition(t);
		//dp_sum(num);
		dp_editdistance("apple","a");
		System.out.println(recur_scramble("great","rgtae"));
	}

}
