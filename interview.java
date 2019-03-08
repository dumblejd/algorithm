package algorithmtest;

import java.util.HashMap;
import java.util.Map;

public class interview {

	
	public static void findlongestsub(String s)  //最大非重复单个字符 的 
	{
		Map<Character,Integer> m = new HashMap<Character,Integer>();
		String out="";
		for(int i=0;i<s.length();i++)
		{
			int index=i;
			while(index<s.length())
			{
				if(m.containsKey(s.charAt(index)))
				{
					out=out.length()>m.size()?out:s.substring(i, index);
					m = new HashMap<Character,Integer>();
					break;
				}
				else
				{
					m.put(s.charAt(index), 1);
				}
				index++;
			}
		}
		System.out.println(out);
	}
    public static int longestPalindrome(String s) {  //find sub palindrome
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<s.length();i++)
        {
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");
        int max=0;
        char [] c = sb.toString().toCharArray();
        for(int i=0;i<c.length;i++)
        {
            max=Math.max(max,search(c,i));
        }
        return max;
    }
    public static int search(char[]c,int index)
    {
        int max=0;
        for(int i=0;i+index<c.length&&index-i>=0;i++)
        {
            if(c[index-i]==c[index+i])
            {
                max++;
            }
        }
        return max-1;
    }
    //#409 easy
    public static int longestPalindrome409(String s) {
        int[]c =new int[26];
        for(int i=0;i<s.length();i++)
        {
            c[s.charAt(i)-'a']++;
        }
        int single=0;
        int max=0;
        for(int temp:c)
        {
            if(temp%2==0)
            {
                max+=temp;
            }
            else
            {
                single=1;
                max+=temp-1;
            }
        }
        max=max+single;
        return max;
    }
    public static int longestPalindrome_dp(String s) 
    {
    	boolean [][]dp=new boolean[s.length()][s.length()];
    	int max=0;
//    	for(int i=0;i<s.length();i++)
//    	{
//    		dp[i][i]=true;
//    	}
    	for(int i=0;i<s.length();i++)
    	{
    		for(int j=0;j+i<s.length();j++)
    		{
    			if(j==i+j)
    			{
    				dp[j][j+i]=true;
    				
    			}
    			else if(i==1)
    			{
    				dp[j][j+i]=(s.charAt(i+j)==s.charAt(j));
    			}
    			else 
    			{
    				dp[j][j+i]=dp[j+1][j+i-1]&&(s.charAt(i+j)==s.charAt(j));
    			}
    			if(dp[j][j+i])
    			{
    				max=Math.max(max,i+1);
    			}
    		}
    	}
    	return max;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		findlongestsub("abbccdbabcdccdd");
		longestPalindrome_dp("aabbabac");
	}

}
