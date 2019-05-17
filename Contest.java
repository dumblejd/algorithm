package algorithmtest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
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
//#1022
public static boolean canThreePartsEqualSum(int[] A) {
    int []sum=new int [A.length];
    sum[0]=A[0];
    for(int i=1;i<A.length;i++)
    {
        sum[i]=sum[i-1]+A[i];
    }
    for(int i=1;i<A.length;i++)
    {
        for(int j=i;j<A.length;j++)
        {
            if(i-1>=0&&sum[i-1]==sum[j-1]-sum[i-1]&&sum[j-1]-sum[i-1]==sum[A.length-1]-sum[j-1])
            {
                return true;
            }
        }
    }
    return false;
}
//理解错了  我理解的和题是返回来的 ，这个是反过来的解 
public static List<Boolean> prefixesDivBy5_reverse(int[] A) {
    boolean []res = new boolean[A.length];
    
    int []rest=new int [A.length];
    rest[0]=A[0];
    res[0]=rest[0]%5==0;
    int temp=1;
    for(int i=1;i<A.length;i++)
    {
        temp=temp*2%5;
       if(A[i]==1)
       {
           rest[i]=(rest[i-1]+temp)%5;
          
        }
        else
        {
            rest[i]=rest[i-1];
        }
        res[i]=rest[i]%5==0;
    }
    List<Boolean> r = new ArrayList<Boolean>();
    for(boolean b:res)
    {
    	r.add(b);
    }
    return r;
}
public List<Boolean> prefixesDivBy5(int[] A) {
    int rest=A[0];
    ArrayList<Boolean> res=new ArrayList<Boolean>();
    res.add(rest%5==0);
    for(int i=1;i<A.length;i++)
    {
        if(A[i]==1)
        {
        rest=rest*2%5+1;
        }
        else
        {
            rest=rest*2%5;
        }
        res.add(rest%5==0);
    }
    return res;
}
static String res="";
public static String baseNeg2(int N) {
	if(N==0)
	{
		return "0";
	}
	int limit=(int)(Math.log((double)N)/Math.log(2.0)+1);
    helper1028(N,new StringBuffer(),0,0,limit);
    return res;
}
public static void helper1028(int N,StringBuffer now,int value,int index,int limit)
{
	if(index-4>limit)
	{
		return;
	}
    if(N==value)
    {
        res=new String(now);
        return;
    }
    helper1028(N,now.insert(0,1),value+(int)Math.pow(-2,index),index+1,limit);
    now.delete(0,1);
    helper1028(N,now.insert(0,0),value,index+1,limit);
    now.delete(0,1);
}
public class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }
//1030 
public int[] nextLargerNodes(ListNode head) {
    Stack <ListNode> s = new Stack<ListNode>();
    ListNode temp=head;
    s.push(temp);
    
    temp=temp.next;
    Map<ListNode,Integer> m = new HashMap<ListNode,Integer>();
    int count=1;
    while(!s.isEmpty()&&temp!=null)
    {
        count++;
        while(!s.isEmpty()&&temp.val>s.peek().val)
        {
            m.put(s.pop(),temp.val);
        }
        s.push(temp);
        temp=temp.next;
    }
    int []res=new int[count];
    int i=0;
    while(head!=null)
    {
        res[i++]=m.getOrDefault(head,0);
        head=head.next;
    }
    return res;
}
public static String baseNeg2_2(int N) {
    String res = "";
    while (N != 0) {
        res = Integer.toString(N & 1) + res;
        N = -(N >> 1);
    }
    return res == ""  ? "0" : res;
}

public int twoCitySchedCost(int[][] costs) {
	min1029=Integer.MAX_VALUE;
    helper(costs,0,0,0);
    return min1029;
}
static int min1029=0;
public void helper(int[][] costs,int index,int balance,int sum)
{
    if(index>costs.length||balance>costs.length/2||balance<costs.length/2*-1)
    {
        return;
    }
    if(index==costs.length&&balance==0)
    {
        if(sum<min1029)
        {
        	min1029=sum;
        }
        return;
    }
    if(index==costs.length&&balance!=0)
    {
        return;
    }
    helper(costs,index+1,balance+1,sum+costs[index][0]);
     helper(costs,index+1,balance-1,sum+costs[index][1]);
    
}
//1029 mine
public int twoCitySchedCost_pq(int[][] costs) {
	PriorityQueue<int[]>q=new PriorityQueue<int[]>(new Comparator<int[]>() {

		@Override
		public int compare(int[] o1, int[] o2) {
			// TODO Auto-generated method stub
			return Math.abs(o1[0]-o1[1])>=Math.abs(o2[0]-o2[1])?-1:1;
		}
		
	});
	for(int i=0;i<costs.length;i++)
	{
		q.offer(costs[i].clone());
	}
	int n1=costs.length/2;
	int n2=n1;
	int sum=0;
	while(!q.isEmpty())
	{
		int []temp=q.poll();
		if(n1==0)
		{
			sum+=temp[1];
			n2--;
			continue;
		}
		if(n2==0)
		{
			sum+=temp[0];
			n1--;
			continue;
		}
		if(temp[0]>=temp[1])
		{
			sum+=temp[1];
			n2--;
		}
		else
		{
			sum+=temp[0];
			n1--;
		}
	}
	return sum;
	
}
//1031
//public int maxSumTwoNoOverlap(int[] A, int L, int M) {
//    int []resl=new int[A.length-L+1];
//    int []resm=new int[A.length-M+1];
//    int temp=0;
//    for(int i=0;i<L;i++)
//    {
//    	temp+=A[i];
//    }
//    resl[0]=temp;
//    for(int l=0,r=L-1,j=1;r<A.length;r++,l++,j++)
//    {
//    	resl[j]=resl[j-1]+A[r]-A[l];
//    }
//    
//    temp=0;
//    for(int i=0;i<M;i++)
//    {
//    	temp+=A[i];
//    }
//    resm[0]=temp;
//    for(int l=0,r=M-1,j=1;r<A.length;r++,l++,j++)
//    {
//    	resl[j]=resm[j-1]+A[r]-A[l];
//    }
    
//}
//1031
public int maxSumTwoNoOverlap(int[] A, int L, int M) {
    int n = A.length;
    int[] prefix = new int[n+1];

    for(int i = 1; i <= n; i++) {
        prefix[i] = A[i-1] + prefix[i-1];
    }
    int res = 0;
    
    for(int i = 0; i <= n-L; i++) {
        int sum = prefix[i+L] - prefix[i];
        for(int j = 0; j <= i-M; j++) {
            res = Math.max(sum+prefix[j+M]-prefix[j], res);
        }
        for(int j = i+L; j <= n-M; j++) {
            res = Math.max(sum+prefix[j+M]-prefix[j], res);
        }

    }
    return res;
}
    public int[] numMovesStones(int a, int b, int c) {
        int []arr=new int[]{a,b,c};
        Arrays.sort(arr);

        int minres=arr[2]-arr[1]>1?1:0;
        minres+=(arr[1]-arr[0])>1?1:0;
        int maxres=(arr[2]-arr[0]-2)>=0?arr[2]-arr[0]-2:0;
        return new int[]{minres,maxres};
    }
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        int[][]clone=new int[grid.length][grid[0].length];
        boolean [][]visited=new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++)
        {
            clone[i]=grid[i].clone();
        }
        helper_1034(clone,grid,visited,r0,c0,grid[r0][c0],color);
        return grid;
    }

    public void helper_1034(int[][]clone,int[][] grid,boolean[][] visited,int x,int y,int origin,int color)
    {
        if(x<0||y<0||x>=grid.length||y>=grid[x].length||visited[x][y]||clone[x][y]!=origin)
        {
            return;
        }
        visited[x][y]=true;
        if(judge_1034(clone,x-1,y,origin)||judge_1034(clone,x,y-1,origin)||judge_1034(clone,x,y+1,origin)||judge_1034(clone,x+1,y,origin))
        {
            if(clone[x][y]==origin)
            {
                grid[x][y]=color;
            }
        }
        helper_1034(clone,grid,visited,x+1,y,origin,color);
        helper_1034(clone,grid,visited,x,y+1,origin,color);
        helper_1034(clone,grid,visited,x-1,y,origin,color);
        helper_1034(clone,grid,visited,x,y-1,origin,color);
    }
    public boolean judge_1034(int[][]clone,int x,int y,int origin)
    {
        if(x<0||y<0||x>=clone.length||y>=clone[x].length||clone[x][y]!=origin)
        {
            return true;
        }
        return false;
    }
    public int maxUncrossedLines(int[] A, int[] B) {
        int[][]res=new int[A.length+1][B.length+1];
        for(int i=1;i<=A.length;i++)
        {
            for(int j=1;j<=B.length;j++)
            {
                if(A[i-1]==B[j-1])
                {
                    res[i][j]=1+res[i-1][j-1];
                }
                else
                {
                    res[i][j]=Math.max(res[i-1][j],res[i][j-1]);
                }
            }
        }
        return res[A.length][B.length];
    }
//370
    public int[] getModifiedArray(int length, int[][] updates) {
        int []res=new int[length];

        for(int i=0;i<updates.length;i++)
        {
            int s=updates[i][0];
            int e=updates[i][1];
            int add=updates[i][2];
            res[s]+=add;
            if(e<length-1)
            {
                res[e+1]-=add;
            }
        }
        int sum=0;
        for(int i=0;i<length;i++)
        {
            sum+=res[i];
            res[i]=sum;
        }
        return res;
    }
    //1041   i think it will in the loop at most four step
    public boolean isRobotBounded(String instructions) {
        if(instructions==null||instructions.equals(""))
        {
            return true;
        }
        int x=0;
        int y=0;
        int [][]dir={{0,1},{-1,0},{0,-1},{1,0}};
        int dir_c=0;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<instructions.length();j++)
            {
                if(instructions.charAt(j)=='G')
                {
                    dir_c=dir_c%4;
                    x+=dir[dir_c][0];
                    y+=dir[dir_c][1];
                }
                else if(instructions.charAt(j)=='L')
                {
                    dir_c++;
                }
                else
                {
                    dir_c--;
                    if(dir_c<0)
                    {
                        dir_c=3;
                    }
                }
            }
            if(x==0&&y==0)
            {
                return true;
            }
        }
        return false;
    }
    //1042 flower painting 涂色问题
    public int[] gardenNoAdj(int N, int[][] paths) {
        List<HashSet<Integer>> setlist = new ArrayList<HashSet<Integer>>();
        for(int i=0;i<N;i++)
        {
            setlist.add(new HashSet<Integer>());//set a set for each garden to record the # of connected gardens
        }
        for(int i=0;i<paths.length;i++)
        {
            setlist.get(paths[i][0]-1).add(paths[i][1]);
            setlist.get(paths[i][1]-1).add(paths[i][0]);
        }
        int []res=new int[N];
        for(int i=0;i<res.length;i++)//greedy
        {
            HashSet<Integer> exist = new HashSet<Integer>();
            for(int t:setlist.get(i))
            {
                exist.add(res[t-1]);//record the color of connected garden
            }
            for(int j=1;j<=N;j++)
            {
                if(!exist.contains(j))
                {
                    res[i]=j;
                    break;
                }
            }
        }
        return res;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Contest t =new Contest();
//char [][]a= {{'p','B','p'},{'B','R','B'},{'p','B','p'},{'p','p','p'}};
//String []A= {"bella","label","roller"};
//
//int []ia= {3,3,6,5,-2,2,5,1,-9,4};
//canThreePartsEqualSum(ia);
//Queue<int[]> q = new LinkedList<int[]>();
//q.offer(new int[] {0,1});
//
//t.numRookCaptures(a);
//t.longestOnes(ia,4);
//t.clumsy(10);
//int []aa= {1,1,0,0,0,1,0,0,1};
//StringBuffer sb= new StringBuffer();
//baseNeg2_2(6);
//int [][]test=new int[][] {{10,20},{30,200},{400,50},{30,20}};
//t.twoCitySchedCost_pq(test);
String a="";
String b="";String c="";
a = b == null ? "12" : 20 + "?" + 55;


       // t.isRobotBounded("GLRLGLLGLGRGLGL");
	}


}
