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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		findlongestsub("abbccdbabcdccdd");
	}

}
