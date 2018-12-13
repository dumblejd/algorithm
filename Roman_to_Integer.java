package algorithmtest;

import java.util.Arrays;

public class Roman_to_Integer {

	public int function(String s)
	{
		int integers[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	    String romans[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	    int result = 0;
	    int start=0;
	    for(int i=0; i<13; i++){
	        while((start+romans[i].length()<=s.length())&&s.substring(start, start+romans[i].length()).equals(romans[i])){
	            result += integers[i];
	            start += romans[i].length();
	        }
	    }
	    System.out.println(result);
	    return result;


	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Roman_to_Integer r = new Roman_to_Integer();
		r.function("");
		String []sa = {"abc","abb","baaa","qwzzc","abaw"};
		String []sa2 = {"1","121","22","333","4444"};
	
		Arrays.sort(sa2);
		for(String t : sa2)
		{
			System.out.println(t);
		}
	}

}
