package algorithmtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BackTrack {
	// #10. Regular Expression Matching
	public boolean isMatch(String s, String p) {
		return helper10(s, p);
	}

	public boolean helper10(String s, String p) // complicated version //actually it's same logic with leetcode,but his version is more integrated
	{
		boolean flag = false;
		if (s.length() == 0 && p.length() == 0) {
			return true;
		}
		if (s.length() == 0) {
			if (p.length() >= 2 && p.charAt(1) == '*') {
				return helper10(s, p.substring(2));
			}
			return false;
		}
		if (p.length() == 0) {
			return false;
		}
		if (2 <= p.length() && p.charAt(1) == '*') {
			if (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') { //it matter, i thought it doesn't
				flag = flag || helper10(s, p.substring(2, p.length()));
				flag = flag || helper10(s.substring(1), p);
			} else {
				flag = flag || helper10(s, p.substring(2, p.length()));
			}
		}

		else {
			if (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) {
				flag = flag || helper10(s.substring(1), p.substring(1));
			} else {
				return false;
			}

		}
		return flag;
	}

//	class Solution {    //leetcode version  //more integrated, but same logic 
//		public boolean isMatch(String text, String pattern) {
//			if (pattern.isEmpty())
//				return text.isEmpty();
//			boolean first_match = (!text.isEmpty()
//					&& (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
//
//			if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
//				return (isMatch(text, pattern.substring(2)) || (first_match && isMatch(text.substring(1), pattern)));
//			} else {
//				return first_match && isMatch(text.substring(1), pattern.substring(1));
//			}
//		}
//	}
//#17. Letter Combinations of a Phone Number
public List<String> letterCombinations(String digits) {
        String []code=new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res= new ArrayList<String>();
        helper17(digits, 0, code, res, new StringBuilder());
        return res;
    }
public void helper17(String digits,int index,String []code,List<String> res,StringBuilder temp)
{
	if(temp.length()==digits.length()&&digits.length()>0)
	{
		res.add(new String(temp));
		return;
	}
	if(index>=digits.length())
	{
		return;
	}
	int now = Character.getNumericValue(digits.charAt(index));
	for (int i = 0; i < code[now].length(); i++) {
		temp.append(code[now].charAt(i));
		helper17(digits, index+1, code, res, temp);
		temp.deleteCharAt(temp.length()-1);
	}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BackTrack bt = new BackTrack();
		char[][] a = new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
				{ '1', '1', '0', '0', '0' } };
		bt.helper10("mississippi", "mis*is*p*.");
		System.out.println();
	}

}
