package algorithmtest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BackTrack {
	//131. Palindrome Partitioning
public List<List<String>> partition(String s) {
	List<List<String>> res= new ArrayList<List<String>>();
	helper131(s,res,new LinkedList<String>());
	return res;
    }
public void helper131(String s,List<List<String>> res,LinkedList<String> temp)
{
	if(s.equals(""))
	{
		res.add(new LinkedList<String>(temp));
		return;
	}
	for(int i=1;i<s.length()+1;i++)
	{
		String cut=s.substring(0, i);
		temp.add(cut);
		helper131(s.substring(i, s.length()),res,temp);
		temp.removeLast();
	}
	
}
public boolean isPalindrome(String str,int l,int r)
{
	if (l==r)
	{
		return true;
	}
	 while(l<r){
         if(str.charAt(l)!=str.charAt(r)) return false;
         l++;r--;
     }
     return true;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BackTrack bt=new BackTrack();
		bt.partition("aab");
	}

}
