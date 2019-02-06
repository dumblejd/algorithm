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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
