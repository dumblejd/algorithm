package algorithmtest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
//317. Shortest Distance from All Buildings   bfs    to complicated buy understand the idea in google note
//public int shortestDistance(int[][] grid) {
//  
//}
//public int helper317(int[][] grid)
//{
//	
//	int min=Integer.MAX_VALUE;
//
//	for (int i = 0; i < grid.length; i++) {
//		for (int j = 0; j < grid[i].length; j++) {
//			int sum=0;
//			Queue <int[]>q=new LinkedList<int[]>();
//			q.add(new int[]{i,j});
//			while(!q.isEmpty())
//			{
//				int []a=q.poll();
//				int x=a[0];
//				int y=a[1];
//				if(x<0||y<0||x>=grid.length||y>=grid[x].length||grid[x][y]==2||visited[x][y])
//			}
//		}
//	}
//	
//} 
//207. Course Schedule
//try union find (can't do it) because parent child relation may be reverse
//try a method similar to union find  but I can't find where. it work
//try dfs
public boolean canFinish(int numCourses, int[][] prerequisites) {
	Map<Integer,List<Integer>> path= new HashMap<Integer,List<Integer>> ();
    for (int i = 0; i < prerequisites.length; i++) {
    	int from=prerequisites[i][0];
    	int to=prerequisites[i][1];
    	if(!helper207(path, to, from))
    	{
			if(!path.containsKey(from))
			{
				path.put(from,new ArrayList<Integer>());
				path.get(from).add(to);
			}
			else
			{
				path.get(from).add(to);
			}
    	}
    	else
    	{
    		return false;
    	}
	}
    return true;
    
   
   
}
public boolean helper207(Map<Integer,List<Integer>> path,int from,int to)
{
	Queue<Integer> q=new LinkedList<Integer>();
	q.offer(from);
		 while(!q.isEmpty())
		 {
			 int now=q.poll();
			 if(now==to)
			 {
				 return true;
			 }
			 if(path.containsKey(now))
			 {
				 List<Integer> temp=path.get(now);
				 for(int i=0;i<temp.size();i++)
				 {
					 q.offer(temp.get(i));
				 }
			 }
			
		 }
		 return false;
}

//public boolean helper207(Map<Integer,List<Integer>> path,Map<Integer,Integer> visited,int from,int to)
//{
//	if(from==to)
//	{
//		return true;
//	}
//	if(!path.containsKey(from)||visited.get(from)>=path.get(from).size())
//	{
//		return false;
//	}
//	boolean flag=false;
//	for(int i=0;i<path.get(from).size();i++)
//	{
//		flag=flag||helper207(path, visited, path.get(from).get(i), to);
//	}
//	return flag;
//}
//207 bfs from others leetcode
public boolean canFinish_bfs(int numCourses, int[][] prerequisites) {
    ArrayList[] graph = new ArrayList[numCourses];
    int[] degree = new int[numCourses];
    Queue queue = new LinkedList();
    int count=0;
    
    for(int i=0;i<numCourses;i++)
        graph[i] = new ArrayList();
        
    for(int i=0; i<prerequisites.length;i++){
        degree[prerequisites[i][1]]++;
        graph[prerequisites[i][0]].add(prerequisites[i][1]);
    }
    for(int i=0; i<degree.length;i++){
        if(degree[i] == 0){
            queue.add(i);
            count++;
        }
    }
    
    while(queue.size() != 0){
        int course = (int)queue.poll();
        for(int i=0; i<graph[course].size();i++){
            int pointer = (int)graph[course].get(i);
            degree[pointer]--;
            if(degree[pointer] == 0){
                queue.add(pointer);
                count++;
            }
        }
    }
    if(count == numCourses)
        return true;
    else    
        return false;
}
// 210 course schedule
public int[] findOrder(int numCourses, int[][] prerequisites) {
	int []res= new int[numCourses];
	int index=0;
	 ArrayList[] graph = new ArrayList[numCourses];
	    Queue<Integer>queue = new LinkedList();
	    for(int i=0;i<numCourses;i++)
	        graph[i] = new ArrayList();
	    for(int i = 0;i<prerequisites.length;i++)
	    {
	    	graph[prerequisites[i][0]].add(prerequisites[i][1]);
	    }
	    for (int i = 0; i < graph.length; i++) {
			if(graph[i].size()==0)
			{
				queue.offer(i);
			}
		}
	    if(queue.size()==0)
	    {
	    	return new int[] {};
	    }
	    while(!queue.isEmpty())
	    {
	    	int head=queue.poll();
	    	res[index++]=head;
	    	for(int i=0;i<graph.length;i++)
	    	{
	    		if(graph[i].contains(head))
	    		{
	    			int j=graph[i].indexOf(head);
	    			graph[i].remove(j);
	    			if(graph[i].size()==0)
		    		{
		    			queue.offer(i);
		    		}
	    		}
	    		
	    	}
	    }
	    if(index==numCourses)
	    {
	    	return res;
	    }
	    return new int[]{};
}
//630 Course Schedule III  use dp
public int scheduleCourse(int[][] courses) {
    Arrays.sort(courses,(a,b)->{return a[1]-b[1];});
    //int []dp=new int[courses.length];
    
    return helper630(courses, 0, 0);
}
public int helper630(int[][] courses,int index,int time)
{
	if(index<0||index==courses.length)
	{
		return 0;
	}
    int notake=0;
    int take=0;
	if(time+courses[index][0]>courses[index][1])
	{
		notake = helper630(courses, index+1, time);
	}
	else
	{
		notake = helper630(courses, index+1, time);
		take=helper630(courses, index+1, time+courses[index][0])+1;
	}
    return Math.max(notake,take);
}
//305 Number of Islands II   beats 95%   100%space
public List<Integer> numIslands2(int m, int n, int[][] positions) {
		int []all=new int[m*n];
		List<Integer> res=new ArrayList<Integer>();
		int now=0;
		for(int i=0; i<all.length; i++) {
		    all[i]=-1;
		}
		for(int i=0;i<positions.length;i++)
		{
			int num=positions[i][0]*n+positions[i][1];
			all[num]=num;
			int up=positions[i][0]==0?0:union305(num,num-n,all);
			int down=positions[i][0]==m-1?0:union305(num,num+n,all);
			int left=positions[i][1]==0?0:union305(num,num-1,all);
			int right=positions[i][1]==n-1?0:union305(num,num+1,all);
			now = now-(up+down+left+right)+1;
			res.add(now);
		}
		return res;
}
public int union305(int original,int near,int[] all)
{
	if(all[near]==-1)
	{
		return 0;
	}
	int f1=find305(original,all);
	int f2=find305(near,all);
	if(f1!=f2)
	{
		all[f1]=all[f2];
		return 1;
	}
	return 0;
}
public int find305(int num,int[] all)
{
	while(all[num]!=(num))
	{
		num=all[num];
	}
	return num;
}
//200 number of island 1 try union find    will work
public int numIslands(char[][] grid) {	
	int []all=new int[grid.length*grid[0].length];
	int now=0;
	for(int i=0; i<all.length; i++) {
	    all[i]=-1;
	}
	for(int i=0;i<grid.length;i++)
	{
		for (int j = 0; j < grid[i].length; j++) {
			
			if(grid[i][j]=='1')
			{
			int num=i*grid[i].length+j;
			all[num]=num;
			int up=i==0?0:union305(num,num-grid[i].length,all);
			int down=i==grid.length-1?0:union305(num,num+grid[i].length,all);
			int left=j==0?0:union305(num,num-1,all);
			int right=j==grid[i].length-1?0:union305(num,num+1,all);
			now = now-(up+down+left+right)+1;
			}
		}
	}
	return now;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BFS b= new BFS();
		int [][]t=new int[][] {{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
		char [][]grid=new char [][] {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
		b.numIslands(grid);
		b.findOrder(2,new int[][]{{1,0},{0,1}});
	}

}
