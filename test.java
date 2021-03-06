package algorithmtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class test {
	public List<String> wordBreak(String s, List<String> wordDict) {
	    return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
	}       

	// DFS function returns an array including all substrings derived from s.
	List<String> DFS(String s, List<String> wordDict, HashMap<String, LinkedList<String>>map) {
	    if (map.containsKey(s)) 
	        return map.get(s);
	        
	    LinkedList<String>res = new LinkedList<String>();     
	    if (s.length() == 0) {
	        res.add("");
	        return res;
	    }               
	    for (String word : wordDict) {
	        if (s.startsWith(word)) {
	            List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
	            for (String sub : sublist) 
	                res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
	        }
	    }       
	    map.put(s, res);
	    return res;
	}
	 public String convertToTitle(int n) {
		 if(n<0)
	        {
	            return "";
	        }
		        String res="";
		        while (n>0)
		        {
	                int temp=n%26;
	                if(temp==0)
	                {
	                    temp=26;
	                    }
		            res=(char)(temp+'A'-1)+res;
		            n=(n-temp)/26;
		    }
		        return res;
	        }
		public static void getIndex(String str){
			byte[] bytes = str.getBytes();
			for (int i = 0; i < bytes.length; i++) {
				byte b = bytes[i];
				System.out.println(b - 'A'+1);
			}
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test t= new test();
			String[] ss = {"boy","sand","boys","sands","san","dog","an","and"};
			List<String> wordList = new ArrayList<String>();
			for (int i = 0; i < ss.length; i++) {
				wordList.add(ss[i]);
			}
			t.wordBreak("boysandsandog", wordList);
			
			int a=0;
			int b=0;
		   a=a+1 ;
		  getIndex("TENNIS UDOMJR CRICKET");
//		  System.out.println("----");
//		  getIndex("fzhkf");
	}

}
