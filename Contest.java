package algorithmtest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
public class Contest {
	//997 find the town judge
public int findJudge(int N, int[][] trust) {
	if(trust.length==0)
	{
		return 1;
	}
        Map <Integer,Integer> m = new HashMap<Integer,Integer>();
        for(int i=0;i<trust.length;i++)
        {
        	if(!m.containsKey(trust[i][0]))
        	{
        		m.put(trust[i][0], 0);
        	}
        	if(!m.containsKey(trust[i][1]))
        	{
        		m.put(trust[i][1],0);
        	}
        	m.put(trust[i][1],m.get(trust[i][1])+1);
        }
        for(int k:m.keySet())
        {
        	if(m.get(k)==m.size()-1)
        	{
        		for(int i=0;i<trust.length;i++)
        		{
        			if(trust[i][0]==k)
        			{
        				return -1;
        			}
        			if(i==trust.length-1)
        			{
        				return k;
        			}
        		}
        	}
        }
        return -1;
    }
//999 available caputre for book
static int num999=0;
public int numRookCaptures(char[][] board) {
   for(int i=0;i<board.length;i++)
   {
	   for(int j=0;j<board[i].length;j++)
	   {
		   if(board[i][j]=='R')
		   {
			   helper999(board,i,j,1,0);
			   helper999(board,i,j,0,1);
			   helper999(board,i,j,-1,0);
			   helper999(board,i,j,0,-1);
		   }
	   }
   }
   return num999;
}
public void helper999(char[][] board,int x,int y,int mx,int my)
{
	if(x<0||y<0||x>=board.length||y>=board[x].length||board[x][y]=='B')
	{
		return;
	}
	if(board[x][y]=='p')
	{
		num999++;
		return;
	}
	helper999(board, x+mx, y+my, mx, my);
	
}
public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
public TreeNode insertIntoMaxTree(TreeNode root, int val) {
	return root;
	
    
}
public TreeNode helper998(TreeNode root, TreeNode pre,int val)
{
	TreeNode t = new TreeNode(val);
    if(root.val<val)
    {
    	if(pre==null)
    	{
    		t.left=root;
    		return t;
    	}
    	else
    	{
    		//pre.right=t
    	}
    }
	return t;
}
public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
    int[][]board=new int[N][N];
    int []res=new int[queries.length];
    for(int i=0;i<board.length;i++)
    {
 	   for(int j=0;j<board[i].length;j++)
 	   {
 		   board[i][j]=0;
 	   }
    }
    int index=-1;
    for(int i=lamps.length-1;i>(lamps.length-queries.length);i--)
    {
    	helper1001(board, lamps[i][0], lamps[i][1], 1, 0);
    	helper1001(board, lamps[i][0], lamps[i][1], 0, 1);
    	helper1001(board, lamps[i][0], lamps[i][1], -1, 0);
    	helper1001(board, lamps[i][0], lamps[i][1], 0, -1);
    	helper1001(board, lamps[i][0], lamps[i][1], 1, 1);
    	helper1001(board, lamps[i][0], lamps[i][1], -1, -1);
    	index=i;
    }
    for(int i=queries.length-1;i>0;i--)
    {
    	helper1001(board, lamps[--index][0], lamps[--index][1], 1, 0);
    	helper1001(board, lamps[--index][0], lamps[--index][1], 0, 1);
    	helper1001(board, lamps[--index][0], lamps[--index][1], -1, 0);
    	helper1001(board, lamps[--index][0], lamps[--index][1], 0, -1);
    	helper1001(board, lamps[--index][0], lamps[--index][1], 1, 1);
    	helper1001(board, lamps[--index][0], lamps[--index][1], -1, -1);
    }
	return res;
    
}
public void helper1001(int[][] board,int x,int y,int mx,int my)
{
	if(x<0||y<0||x>=board.length||y>=board[x].length)
	{
		return;
	}
	board[x][y]++;
	helper1001(board, x+mx, y+my, mx, my);
}
//#1002 
//public List<String> commonChars(String[] A) {
//	 List<String> res =new ArrayList<String>();
//		Map<String,Integer> m = new HashMap<String,Integer>();
//		Map<String,Integer> temp = new HashMap<String,Integer>();
//		for(int i=0;i<A[0].length();i++)
//		{
//			String s=A[0].substring(i,i+1);
//			m.put(s,m.getOrDefault(s, 0)+1);
//		}
//		
//		for(int i=1;i<A.length;i++)
//		{
//			for(int j=0;j<A[i].length();j++)
//			{
//				//if(m.contains)
//			}
//		}
//}
//#1003
public boolean isValid(String S) {
	StringBuffer sb =new StringBuffer(S);
    while(sb.indexOf("abc")!=-1)
    {
    	sb.delete(sb.indexOf("abc"),sb.indexOf("abc")+3);
    }
    if(sb.length()==0)
    {
    	return true;
    }
    return false;
}
//1004
//public int longestOnes(int[] A, int K) {
//	StringBuffer a = new StringBuffer();
//	for(int i:A)
//	{
//		a.append(i);
//	}
//	helper1004(a, K, 0);
//	return onemax;
//}
//static int onemax=0;
//public void helper1004(StringBuffer a,int k,int index)
//{
//	if(index==a.length()||k<=0)
//	{
//		return;
//	}
//	for(int i=index;i<a.length();i++)
//	{
//		if(a.charAt(i)=='0')
//		{
//		a.replace(i, i+1, "1");
//		onemax=Math.max(onemax,findone(a));
//		helper1004(a, k-1, index);
//		a.replace(i, i+1, "0");
//		}
//	}
//}
//public int findone(StringBuffer a)
//{
//	int max=0;
//	String temp="";
//	for(int i=0;i<a.length();i++)
//	{
//		temp+="1";
//		if(a.indexOf(temp)!=-1)
//		{
//			max=Math.max(temp.length(),max);
//		}
//	}
//	return max;
//}
//1004
public int longestOnes(int[] A, int k) {
	int countzero=0;
	int left=0;
	int max=0;
	for(int right=0;right<A.length;right++)
	{
		if(A[right]==0)
		{
			countzero++;
		}
		max=Math.max(right-left,max);
		while(countzero>k)
		{
			if(A[left]==0)
			{
				countzero--;
			}
			left++;
		}
	}
	return max;
}
//public int clumsy(int N) {
//	int res=0;
//	for(int i=N+1-4;i>0;i=i-4)
//    {
//    	res+=i;
//    }
//    return clumsy_helper(N)[0]+res;
//}
//public int[] clumsy_helper(int N) {
//    int already=1;
//    int res=N;
//    int index=0;
//    for(int i=N-1;i>=1;i--)
//    {
//        if(already%4==0)
//        {
//            if(i>=1)
//            {
//            	int []t=clumsy_helper(i);
//                res-=t[0];
//                i=t[1];
//                already+=t[2];
//            }
//        }
//        else
//        {
//            if(already%4==1)
//            {
//                res=res*i;
//            }
//            else if(already%4==2)
//            {
//                res/=i;
//            }
////            else if(already%4==3)  do nothing
////            {
////                plus+=i;
////            }
//            already++;
//        }
//        index=i;
//    }
//    return new int[]{res,index,already};
//}
//#1005
public int largestSumAfterKNegations(int[] A, int K) {
    Arrays.sort(A);
    int sum=0;
    PriorityQueue<Integer> q = new PriorityQueue<Integer>();
    for(int i=0;i<A.length;i++)
    {
        q.offer(A[i]);
    }
    for(int i=0;i<K;i++)
    {
        int temp=q.poll();
        temp=-1*temp;
        q.offer(temp);
    }
    for(int i=0;i<A.length;i++)
    {
        sum+=q.poll();
    }
    return sum;
}
//#1006 
public int clumsy(int N) {
    return clumsy_helper(N);
}
public int clumsy_helper(int N) {
    int already=0;
    int res=0;
    for(int i=N;i>=1;i--)
    {
        if(already%4==0)
        {
            if(i>=1)
            {
            	int []t=helper_clu(i);
            	res+=already==0?t[0]:-1*t[0];
            	already+=i-t[1]+1;
            	i=t[1];
            	
            }
        }
        else
        {
            if(already%4==3)  
            {
                res+=i;
            }
            already++;
        }
    }
    return res;
}
public int[] helper_clu(int n)
{
	int res=n;
	if(n-1>=1)
	{
		res*=n-1;
		n=n-1;
	}
	if(n-1>=1)
	{
		res/=n-1;
		n=n-1;
	}
	return new int[]{res,n};
}

//1007  理解错了，我这个写法是 他们合相同
//public int minDominoRotations(int[] A, int[] B) {
//    int diffAB=0;
//    min=A.length;
//    for(int i=0;i<A.length;i++)
//    {
//        diffAB+=A[i]-B[i];
//    }
//    dfsDomino(A,B,diffAB,0,0);
//    return min;
//}
//public static int min=0;
//public void dfsDomino(int[] A, int[] B,int target,int index,int times)
//{
//    if(index==A.length)
//    {
//        return;
//    }
//    if(target==0)
//    {
//        min=Math.min(min,times);
//    }
//    for(int i=index;i<A.length;i++)
//    {
//        int tar=A[i]-B[i];
//        swapDomino(A,B,i);
//        dfsDomino(A,B,target-2*tar,i+1,times+1);
//        swapDomino(A,B,i);
//    }
//}
//public void swapDomino(int[] A, int[] B,int index)
//{
//    int temp=A[index];
//    A[index]=B[index];
//    B[index]=temp;
//}
//1007
public int minDominoRotations(int[] A, int[] B) {
    int diffAB=0;
    min=A.length;
    for(int i=0;i<A.length;i++)
    {
        diffAB+=A[i]-B[i];
    }
    dfsDomino(A,B,diffAB,0,0);
    return min;
}
public static int min=0;
public void dfsDomino(int[] A, int[] B,int target,int index,int times)
{
    if(index==A.length)
    {
        return;
    }
    if(target==0)
    {
        min=Math.min(min,times);
    }
    for(int i=index;i<A.length;i++)
    {
        int tar=A[i]-B[i];
        swapDomino(A,B,i);
        dfsDomino(A,B,target-2*tar,i+1,times+1);
        swapDomino(A,B,i);
    }
}
public void swapDomino(int[] A, int[] B,int index)
{
    int temp=A[index];
    A[index]=B[index];
    B[index]=temp;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
char [][]a= {{'p','B','p'},{'B','R','B'},{'p','B','p'},{'p','p','p'}};
String []A= {"bella","label","roller"};

int []ia= {1,0,0,0,1,1,0,0,1,1,0,0,0};
PriorityQueue<Integer> q = new PriorityQueue<Integer>();
Contest t =new Contest();
t.numRookCaptures(a);
t.longestOnes(ia,4);
t.clumsy(10);
	}

}
