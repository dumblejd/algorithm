package algorithmtest;
import java.util.ArrayList;  
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    		pre.right=t
    	}
    }
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
char [][]a= {{'p','B','p'},{'B','R','B'},{'p','B','p'},{'p','p','p'}};
Contest t =new Contest();
t.numRookCaptures(a);
	}

}
