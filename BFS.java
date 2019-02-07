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
public class BFS {
	//#127  word ladder    out of time  //for better solution similar to dijkstra, look google note
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        helper127(beginWord, endWord, wordList, new boolean[wordList.size()], 0);
        if(min==Integer.MAX_VALUE)
        {
        	return 0;
        }
return min;
    }
int min=Integer.MAX_VALUE;
public void helper127(String beginWord, String endWord, List<String> wordList,boolean []visited,int size)
{
	if(endWord.equals(beginWord))
	{
		min=Math.min(min, size+1);
		return;
	}
	for(int i=0;i<wordList.size();i++)
	{
		if(visited[i])
		{
			continue;
		}
		int diff=diff127(beginWord,wordList.get(i));
		if(diff==1)
		{
			visited[i]=true;
			helper127(wordList.get(i), endWord, wordList, visited, size+1);
			visited[i]=false;
		}
		else if(diff==0)
		{
			visited[i]=true;
			helper127(wordList.get(i), endWord, wordList, visited, size);
			visited[i]=false;
		}
	}
}
public int diff127(String a, String b) {
	int res = 0;
	for (int i = 0; i < a.length(); i++) {
		if (a.charAt(i) != b.charAt(i)) {
			res++;
		}
	}
	return res;
}
//286 walls and gates
public void wallsAndGates(int[][] rooms) {
	for (int i = 0; i < rooms.length; ++i) {
        for (int j = 0; j < rooms[i].length; ++j) {
            if (rooms[i][j]==0) 
            {
            	helper_dfs_286(rooms, i, j, 0,new boolean[rooms.length][rooms[0].length]);
            }
        }
    }
}
public void helper_dfs_286(int[][] rooms,int x,int y,int num,boolean [][]visited)//start from 0   93ms
{
	if(x<0||y<0||x>=rooms.length||y>=rooms[x].length||rooms[x][y]==-1||rooms[x][y]<num||visited[x][y])
	{
		return;
	}
	visited[x][y]=true;
	rooms[x][y]=num;
	
	helper_dfs_286(rooms, x, y+1, num+1, visited);
	helper_dfs_286(rooms, x+1, y, num+1, visited);
	helper_dfs_286(rooms, x, y-1, num+1, visited);
	helper_dfs_286(rooms, x-1, y, num+1, visited);
}
public void wallsAndGates_v1(int[][] rooms) {
	for (int i = 0; i < rooms.length; ++i) {
        for (int j = 0; j < rooms[i].length; ++j) {
            if (rooms[i][j]>0) 
            {
            	rooms[i][j]=helper_dfs_286_v1(rooms, i, j, 0,new boolean[rooms.length][rooms[0].length]);
            }
        }
    }
}
public int helper_dfs_286_v1(int[][] rooms,int x,int y,int num,boolean [][]visited)//version:start from every room 647ms
{
	if(x<0||y<0||x>=rooms.length||y>=rooms[x].length||rooms[x][y]==-1||visited[x][y])
	{
		return Integer.MAX_VALUE;
	}
	if(rooms[x][y]==0)
	{
		return num;
	}
	else
	{
		int min=Integer.MAX_VALUE;
		visited[x][y]=true;
		min=Math.min(helper_dfs_286_v1(rooms, x+1, y, num+1,visited),min);
		min=Math.min(helper_dfs_286_v1(rooms, x-1, y, num+1,visited),min);
		min=Math.min(helper_dfs_286_v1(rooms, x, y-1, num+1,visited),min);
		min=Math.min(helper_dfs_286_v1(rooms, x, y+1, num+1,visited),min);
		visited[x][y]=false;
		return min;
	}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
