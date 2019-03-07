package algorithmtest;

import java.util.HashMap;
import java.util.Map;

public class interview {

	
	public static void findlongestsub(String s)
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
    public static int longestPalindrome(String s) {
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		findlongestsub("abbccdbabcdccdd");
		longestPalindrome("aabbaa");
	}

}
