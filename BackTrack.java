package algorithmtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BackTrack {
	// #10. Regular Expression Matching
	public boolean isMatch(String s, String p) {
		return helper10(s, p);
	}

	public boolean helper10(String s, String p) // complicated version //actually it's same logic with leetcode,but his
												// version is more integrated
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
			if (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') { // it matter, i thought it doesn't
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

	// class Solution { //leetcode version //more integrated, but same logic
	// public boolean isMatch(String text, String pattern) {
	// if (pattern.isEmpty())
	// return text.isEmpty();
	// boolean first_match = (!text.isEmpty()
	// && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
	//
	// if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
	// return (isMatch(text, pattern.substring(2)) || (first_match &&
	// isMatch(text.substring(1), pattern)));
	// } else {
	// return first_match && isMatch(text.substring(1), pattern.substring(1));
	// }
	// }
	// }
	// #17. Letter Combinations of a Phone Number
	public List<String> letterCombinations(String digits) {
		String[] code = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		List<String> res = new ArrayList<String>();
		helper17(digits, 0, code, res, new StringBuilder());
		return res;
	}

	public void helper17(String digits, int index, String[] code, List<String> res, StringBuilder temp) {
		if (temp.length() == digits.length() && digits.length() > 0) {
			res.add(new String(temp));
			return;
		}
		if (index >= digits.length()) {
			return;
		}
		int now = Character.getNumericValue(digits.charAt(index));
		for (int i = 0; i < code[now].length(); i++) {
			temp.append(code[now].charAt(i));
			helper17(digits, index + 1, code, res, temp);
			temp.deleteCharAt(temp.length() - 1);
		}
	}

	// #22 Generate Parentheses
	public List<String> generateParenthesis(int n) {

		List<String> res = new ArrayList<String>();
		if (n <= 0) {
			return res;
		}
		helper22(n, n, new StringBuilder(), res);
		return res;
	}

	public void helper22(int left, int right, StringBuilder temp, List<String> res) {
		if (left == 0 && right == 0) {
			res.add(new String(temp));
		}
		if (left > 0) {
			temp.append("(");
			helper22(left - 1, right, temp, res);
			temp.deleteCharAt(temp.length() - 1);
		}
		if (right > left) {
			temp.append(")");
			helper22(left, right - 1, temp, res);
			temp.deleteCharAt(temp.length() - 1);
		}
	}

	// #39. Combination Sum
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		helper39(candidates, target, res, new ArrayList<Integer>(), 0);
		return res;
	}

	public void helper39(int[] c, int target, List<List<Integer>> res, ArrayList<Integer> temp, int index) {
		if (target == 0) {
			res.add(new ArrayList<Integer>(temp));
		}
		for (int i = index; i < c.length; i++) {
			if (c[i] <= target) {
				temp.add(c[i]);
				helper39(c, target - c[i], res, temp, i);
				temp.remove(temp.size() - 1);
			}
		}
	}

	// #40. Combination Sum 2
	public List<List<Integer>> combinationSum2(int[] candidates, int target) { // different from 39
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		helper40(candidates, target, res, new ArrayList<Integer>(), 0);
		return res;
	}

	public void helper40(int[] c, int target, List<List<Integer>> res, ArrayList<Integer> temp, int index) {
		if (target == 0) {
			res.add(new ArrayList<Integer>(temp));
		}
		for (int i = index; i < c.length; i++) {
			if (i > index && c[i] == c[i - 1])// it's sorted so c[i-1]=c[i] means it has been added like 1 1 2 5 6
			{
				continue;
			}
			if (c[i] <= target) {
				temp.add(c[i]);
				helper40(c, target - c[i], res, temp, i + 1);

				temp.remove(temp.size() - 1);
			}
		}
	}

	// #77 combination
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		helper77(n, k, res, new ArrayList<Integer>(), 0);
		return res;
	}
	public void helper77(int n, int k, List<List<Integer>> res, ArrayList<Integer> temp,int index) {
		if(temp.size()==k)
		{
			res.add(new ArrayList<Integer>(temp));
			return;
		}
		for(int i=index;i<n;i++)
		{
			temp.add(i);
			helper77(n, k, res, temp, i+1);
			temp.remove(temp.size()-1);
		}
	}
	//216. Combination Sum III
public List<List<Integer>> combinationSum3(int k, int n) {
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	helper216(n, k, res, new ArrayList<Integer>(), 1);
	return res;
    }
public void helper216(int n, int k, List<List<Integer>> res, ArrayList<Integer> temp,int index) {
	if(temp.size()>k)
	{
		return;
	}
	if(n==0&&temp.size()==k)
	{
		res.add(new ArrayList<Integer>(temp));
	}
	for(int i=index;i<=9;i++)
	{
		if(i<=n)
		{
		temp.add(i);
		helper216(n-i, k, res, temp, i+1);
		temp.remove(temp.size()-1);
		}
	}
}
//#377. Combination Sum IV   dp
public int combinationSum4(int[] nums, int target) {
    Arrays.sort(nums);
    int []comb=new int[target+1];
    comb[0]=1;
    for(int i=0;i<=target;i++)
    {
    	for (int j = 0; j < nums.length; j++) {
    		if(nums[j]>i)
    		{
			break;
    		}
    		comb[i]+=comb[i-nums[j]];
		}
    }
    return comb[target];
}
//46. Permutations
public List<List<Integer>> permute(int[] nums) {
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	helper46(nums, new boolean[nums.length],res, new ArrayList<Integer>());
	return res;
}
public void helper46(int []nums,boolean []visited, List<List<Integer>> res, ArrayList<Integer> temp) {
	if(temp.size()==nums.length)
	{
		res.add(new ArrayList<Integer>(temp));
	}
	for(int i=0;i<nums.length;i++)
	{
		if(!visited[i])
		{
			visited[i]=true;
			temp.add(nums[i]);
			helper46(nums, visited, res, temp);
			temp.remove(temp.size()-1);
			visited[i]=false;
		}
	}
}
//47. Permutations 2
public List<List<Integer>> permuteUnique(int[] nums) {
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	Arrays.sort(nums);
	helper47(nums,new boolean[nums.length],res, new ArrayList<Integer>());
	return res;
}
public void helper47(int []nums,boolean[]visited, List<List<Integer>> res, ArrayList<Integer> temp) {
	if(temp.size()==nums.length)
	{
		res.add(new ArrayList<Integer>(temp));
	}
	for(int i=0;i<nums.length;i++)
	{
		if(visited[i])
		{
			continue;
		}
		if(i>0&&nums[i]==nums[i-1]&&!visited[i-1]) //be careful of the last judgement
		{
			continue;
		}
			temp.add(nums[i]);
			visited[i]=true;
			helper47(nums,visited, res, temp);
			temp.remove(temp.size()-1);
			visited[i]=false;
	}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BackTrack bt = new BackTrack();
		char[][] a = new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
				{ '1', '1', '0', '0', '0' } };
		int[] c = new int[] { 2, 3, 6, 7 };
		bt.helper10("mississippi", "mis*is*p*.");
		int []nums=new int[] {1,1,2};
		bt.permuteUnique(nums);
		System.out.println();
	}

}
