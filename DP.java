package algorithmtest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
		//dp_sum(num);
		dp_editdistance("apple","a");
		System.out.println(recur_scramble("great","rgtae"));
	}

}