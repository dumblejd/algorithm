package algorithmtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class dfs {

	public void dfs_Permutation(int[] s, Map m, List<List<Integer>> result, List<Integer> temp)

	{
		for (int i = 0; i < s.length; i++) {
			if (!m.containsKey(i)) {
				m.put(i, 1);
				temp.add(s[i]);
				if (temp.size() == s.length) {
					result.add(new ArrayList<Integer>(temp));
				}
				dfs_Permutation(s, m, result, temp);
				temp.remove(temp.size() - 1);
				m.remove(i);
			}
		}
	}

	public static void print(List<List<Integer>> result)

	{
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < result.get(i).size(); j++) {
				System.out.print(result.get(i).get(j));
			}
			System.out.println();
		}
	}

	public static void printss(List<List<String>> result) {
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < result.get(i).size(); j++) {
				System.out.print(result.get(i).get(j));
			}
			System.out.println();
		}
	}

	public static void prints(List<String> result) {
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}

	public static void printint(List<int[]> result) {
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < result.get(i).length; j++) {
				System.out.print(result.get(i)[j]);
			}
			System.out.println();
		}
	}

	public static void printcharlist(List<char[][]> result)

	{
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < result.get(i).length; j++) {
				for (int k = 0; k < result.get(i)[j].length; k++) {
					System.out.print(result.get(i)[j][k]);
				}
				System.out.println();
			}

			System.out.println("=========");
		}
	}

	public static void printchar(char[][] b) {
		for (int j = 0; j < b.length; j++) {
			for (int k = 0; k < b[j].length; k++) {
				System.out.print(b[j][k]);
			}
			System.out.println();
		}
	}

	public void dfs_sum(int s[], List<List<Integer>> result, List<Integer> temp, int target, int index) {
		if (target == 0) {
			result.add(new ArrayList<Integer>(temp));
			return;
		}
		for (int i = index; i < s.length; i++) {
			if (s[i] > target) {
				break;
			} else {
				temp.add(s[i]);
				dfs_sum(s, result, temp, target - s[i], i + 1);
				temp.remove(temp.size() - 1);
				while (i < s.length - 1 && s[i] == s[i + 1])
					i++;
			}

		}
	}

	public void dfs_parentheses(int left, int right, String s, List<String> result) {
		if (right == 0 && left == 0) {
			result.add(s);
			return;
		}

		if (left > 0) {
			dfs_parentheses(left - 1, right, s + "(", result);
		}
		if (left < right) {
			dfs_parentheses(left, right - 1, s + ")", result);
		}

	}

	public static void dfs_uniquepath(int row, int col, int x, int y, Map<String, Integer> m, String temp) {
		if (x == col - 1 && y == row - 1) {
			m.put(new String(temp), 1);

			return;
		}
		while (y < (row - 1) || x < (col - 1)) {
			if (x < col - 1) {
				dfs_uniquepath(row, col, x + 1, y, m, temp + "right!");
			}
			if (y < row - 1) {
				dfs_uniquepath(row, col, x, y + 1, m, temp + "down!");
			}
			return; // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!和上面的括号题不同，如果没有return 会死循环
		}
	}

	public void dfs_phone(String[] s, String input, List<String> result, String temp) {
		if (input.equals("")) {
			result.add(new String(temp));
			return;
		}
		int index = Integer.valueOf(input.substring(0, 1)) - 1;
		for (int i = 0; i < s[index].length(); i++) {
			dfs_phone(s, input.substring(1), result, temp + s[index].substring(i, i + 1));
		}
	}

	public void dfs_ip(String s, List<String> result, String temp) {
		if (temp.length() == 3 && s.length() <= 3 && Integer.valueOf(s) <= 255) {
			result.add(temp);
			return;
		}
		for (int i = 0; i < s.length() - 1 && temp.length() < 3; i++) {
			String num = s.substring(0, i + 1);
			if (Integer.valueOf(num) > 255) {
				break;
			}
			if (num.substring(0, 1).equals("0")) {
				continue;
			}
			dfs_ip(s.substring(i + 1), result, temp + Integer.toString(i));
		}
	}

	public void dfs_ip_visual(String s, List<String> result, String temp, int count) {
		if (count == 3 && s.length() <= 3 && Integer.valueOf(s) <= 255) {
			temp = temp + s;
			result.add(temp);
			return;
		}
		for (int i = 0; i < s.length() - 1 && count < 3; i++) {
			String num = s.substring(0, i + 1);
			if (Integer.valueOf(num) > 255) {
				break;
			}
			if (num.substring(0, 1).equals("0")) {
				continue;
			}
			dfs_ip_visual(s.substring(i + 1), result, temp + num + ".", count + 1);
		}
	}

	public void dfs_huiwen(String s, List<List<String>> result, List<String> temp) // 拆成 所有部分是回文
	{
		// if(huiwen(s))
		// {
		// result.add(s);
		// return;
		// }

		if (s.length() == 0) {
			result.add(new ArrayList<String>(temp));
			return;
		}
		for (int i = 0; i < s.length(); i++) {
			String sub = s.substring(0, i + 1);
			if (huiwen(sub)) {
				temp.add(sub);
				dfs_huiwen(s.substring(i + 1), result, temp);
				temp.remove(temp.size() - 1);
			}

		}
	}

	public static boolean huiwen(String s) {
		int i = 0;
		int j = s.length() - 1;
		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	public boolean run_wordsearch(String[] s, String target) {
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[i].length(); j++) {

				if (dfs_wordsearch(s, target, i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean dfs_wordsearch(String[] s, String target, int x, int y) {
		if (target.equals("")) {
			return true;
		}
		if (x < 0 || y < 0 || x >= s.length || y >= s[x].length()) {
			return false;
		}
		if (s[x].charAt(y) == target.charAt(0)) {
			String temp = s[x];
			s[x] = s[x].substring(0, y) + "*" + s[x].substring(y + 1);
			if (dfs_wordsearch(s, target.substring(1), x, y + 1)) {
				return true;
			}
			if (dfs_wordsearch(s, target.substring(1), x + 1, y)) {
				return true;
			}
			if (dfs_wordsearch(s, target.substring(1), x - 1, y)) {
				return true;
			}
			if (dfs_wordsearch(s, target.substring(1), x, y - 1)) {
				return true;
			}
			s[x] = temp;
		}
		return false;
	}

	public void dfs_queen(int[] b, int index, List<int[]> result) {

		if (index == b.length) {
			result.add(b.clone());
			return;
		}
		for (int i = 0; i < b.length; i++) {
			b[index] = i;
			if (truequeen(b, index)) {
				dfs_queen(b, index + 1, result);
			}
		}
	}

	public boolean truequeen(int[] b, int index) {
		for (int i = 0; i < index; i++) {
			if (b[i] == b[index] || b[i] - b[index] == i - index || b[i] - b[index] == index - i) {
				return false;
			}
		}
		return true;
	}

	public void dfs_sudoku(char[][] b, int x, int y, List<char[][]> result) {
		if (y == b[x].length && x == b.length - 1) {
			char[][] temp = new char[b.length][b.length];
			for (int i = 0; i < b.length; i++) {
				temp[i] = b[i].clone();
			}
			result.add(temp);
			return;
		}
		if (y == b[x].length) {
			x = x + 1;
			y = 0;
		}
		if (b[x][y] != '.') {

			dfs_sudoku(b, x, y + 1, result);
			return;
		}
		for (int i = 1; i < 10; i++) {
			char temp = b[x][y];
			b[x][y] = (char) ('0' + i);
			if (sudoku_valid(b, x, y)) {
				dfs_sudoku(b, x, y + 1, result);
			}
			b[x][y] = temp;
		}
	}

	public boolean sudoku_valid(char[][] b, int x, int y) {
		for (int i = 0; i < b[x].length; i++) {
			if (b[x][i] == b[x][y] && y != i) {
				return false;
			}
		}
		for (int i = 0; i < b.length; i++) {
			if (b[i][y] == b[x][y] && x != i) {
				return false;
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (b[3 * (x / 3) + i][3 * (y / 3) + j] == b[x][y] && !(x == 3 * (x / 3) + i && y == 3 * (y / 3) + j)) {
					return false;
				}
			}
		}
		return true;
	}

	public static void use_crackpassword(int length, int digit) {
		int total = (int) Math.pow(digit, length);
		Map<String, Integer> combination = new HashMap<String, Integer>();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append("0");
		}
		combination.put(new String(sb), 1);
		dfs_crackpassword(combination, sb, total, length, digit);
	}

	public static void dfs_crackpassword(Map combination, StringBuffer password, int totalcomb, int length, int digit) {
		if (combination.size() == totalcomb) {
			System.out.println(password.length() + " : " + password);
			return;
		}
		for (int i = 0; i < digit; i++) {
			String onecode = password.substring(password.length() - length + 1, password.length());
			onecode += i;
			if (combination.containsKey(onecode)) {
				continue;
			} else {
				password.append(i);
				combination.put(onecode, 1);
				dfs_crackpassword(combination, password, totalcomb, length, digit);
				password.delete(password.length() - 1, password.length());
				combination.remove(onecode);
			}
		}
	}

	public static void selectroom(List<List<Integer>> choices, int[] rooms, int peopleNumber, List<Integer> temp,
			int index) {
		if (peopleNumber <= 0) {
			choices.add(new ArrayList<Integer>(temp));
			return;
		}
		for (int i = index; i < rooms.length; i++) {
			temp.add(rooms[i]);
			peopleNumber -= rooms[i];
			selectroom(choices, rooms, peopleNumber, new ArrayList<Integer>(temp), i + 1);
			peopleNumber += rooms[i];
			temp.remove(temp.size() - 1);
		}
	}

	// 131. Palindrome Partitioning
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<List<String>>();
		helper131(s, res, new LinkedList<String>());
		return res;
	}

	public void helper131(String s, List<List<String>> res, LinkedList<String> temp) {
		if (s.equals("")) {
			res.add(new LinkedList<String>(temp));
			return;
		}
		for (int i = 1; i < s.length() + 1; i++) {
			String cut = s.substring(0, i);
			if (isPalindrome(cut, 0, cut.length() - 1)) {
				temp.add(cut);
				helper131(s.substring(i, s.length()), res, temp);
				temp.removeLast();
			}
		}
	}

	public boolean isPalindrome(String str, int l, int r) {
		if (l == r) {
			return true;
		}
		while (l < r) {
			if (str.charAt(l) != str.charAt(r))
				return false;
			l++;
			r--;
		}
		return true;
	}

	// #200. Number of Islands still doesn't pass a big case
	// 41 / 47 test cases passed.
	// public int numIslands(char[][] grid) {
	// Map<String, Integer> m = new HashMap<String, Integer>();
	// int res = 0;
	// for (int y = 0; y < grid.length; y++) {
	// for (int x = 0; x < grid[y].length; x++) {
	// if (grid[y][x]=='1'&&!m.containsKey(x + "" + y)) {
	// helper200(grid, x, y, m);
	// res++ ;
	// }
	// }
	// }
	// return res;
	// }
	//
	// public void helper200(char[][] grid, int x, int y, Map<String, Integer> m) {
	// if (x < 0 || x > grid[y].length - 1 || y < 0 || y > grid.length - 1 ||
	// grid[y][x] == '0'||m.containsKey(x+""+y)) // means it's a zero
	// // or edge
	// {
	// return;
	// }
	// if (grid[y][x] == '1') {
	// m.put(x + "" + y, 1);
	// helper200(grid, x, y - 1, m);
	// helper200(grid, x, y + 1, m);
	// helper200(grid, x - 1, y, m);
	// helper200(grid, x + 1, y, m);
	// }
	// }
	// #200 back track version not found

	// #261

	// class Solution {
	// public:
	// bool validTree(int n, vector<pair<int, int>>& edges) {
	// if(n != edges.size()+1) return false;
	// vector<int> par(n);
	// for(int i = 0; i < n; i++) par[i] = i;
	// for(auto val: edges)
	// {
	// int par1 = val.first, par2 = val.second;
	// while(par[par1] != par1) par1 = par[par1];
	// while(par[par2] != par2) par2 = par[par2];
	// if(par1 != par2) par[par2] = par1, n--;
	// }
	// return n==1;
	// }
	// };
	// #301. Remove Invalid Parentheses

	// #20 Valid Parentheses //stupid method and WRONG WRONGWRONGWRONGWRONGWRONG
	// public boolean isValid(String s) {
	// Map<Character,Integer> m = new HashMap<Character,Integer>();
	// int i=0;
	// while(i<s.length())
	// {
	// if(s.charAt(i)==')')
	// {
	// if(m.getOrDefault('(', 0)<=0)
	// {
	// return false;
	// }
	// m.put('(', m.get('(')-1);
	// }
	// else if(s.charAt(i)==']')
	// {
	// if(m.getOrDefault('[', 0)<=0)
	// {
	// return false;
	// }
	// m.put('[', m.get('[')-1);
	// }
	// else if(s.charAt(i)=='}')
	// {
	// if(m.getOrDefault('{', 0)<=0)
	// {
	// return false;
	// }
	// m.put('{', m.get('{')-1);
	// }
	// else
	// {
	// m.put(s.charAt(i), m.getOrDefault(s.charAt(i), 0)+1);
	// }
	// i++;
	// }
	// if(m.getOrDefault('(', 0)>0||m.getOrDefault('[', 0)>0||m.getOrDefault('{',
	// 0)>0)
	// {
	// return false;
	// }
	// return true;
	// }
	public static boolean isValid(String s) {
		if (s == null||s.equals("")) {
			return true;
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				if (i + 1 < s.length()) {
					if (s.charAt(i + 1) == ')') {
						return isValid(s.substring(0, i) + s.substring(i + 2));
					}
				}
			}
			else if (s.charAt(i) == '[') {
				if (i + 1 < s.length()) {
					if (s.charAt(i + 1) == ']') {
						return isValid(s.substring(0, i) + s.substring(i + 2));
					}
				}
			}
			else if (s.charAt(i) == '{') {
				if (i + 1 < s.length()) {
					if (s.charAt(i + 1) == '}') {
						return isValid(s.substring(0, i) + s.substring(i + 2));
					}
				}
			}
		}
		return false;
	}
	public static boolean isValid_3(String s) {
		HashMap<Character, Character> mappings= new HashMap<Character, Character>();
		mappings.put(')', '(');
		mappings.put(']', '[');
		mappings.put('}', '{');
		Stack<Character> st = new Stack<Character>();
		for(int i=0;i<s.length();i++)
		{
			char now=s.charAt(i);
			char compare=mappings.getOrDefault(now, '*');
			if(!st.empty()&&compare==st.peek())
			{
				st.pop();
			}
			else
			{
				st.push(s.charAt(i));
			}
		}
		return st.empty();
	}
	//22. Generate Parentheses  i think it's dfs
public static List<String> generateParenthesis(int n) {
        List<String> res= new ArrayList<String>();
        helper22(new char[2*n],0,res);
        return res;
    }
public static void helper22(char[] temp,int index, List<String> res)
{
	if(index==temp.length)
	{
		if(valid_22(temp.toString()))
		{
			res.add(temp.toString());
		}
		return;
	}
	temp[index]='(';
	helper22(temp,index+1,res);
	temp[index]=')';
	helper22(temp,index+1,res);
}
public static boolean valid_22(String s)
{
	int count=0;
	for(int i=0;i<s.length();i++)
	{
		if(s.charAt(i)=='(')
		{
			count++;
		}
		else
		{
			count--;
		}
		if(count<0)
		{
			return false;
		}
	}
	return count==0;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stubs
		Map m = new HashMap<Integer, Integer>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<List<String>> results = new ArrayList<List<String>>();
		List<String> string_result = new ArrayList<String>();
		List<Integer> temp = new ArrayList<Integer>();
		List<String> temps = new ArrayList<String>();
		List<char[][]> char_result = new ArrayList<char[][]>();
		List<int[]> int_result = new ArrayList<int[]>();
		int[] s = { 1, 1, 2, 5, 6, 7, 10 };
		int[] b = { 0, 0, 0, 0, 0, 0, 0, 0 };
		char[][] board = { { '.', '.', '9', '7', '4', '8', '.', '.', '.' },
				{ '7', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '2', '.', '1', '.', '9', '.', '.', '.' },
				{ '.', '.', '7', '.', '.', '.', '2', '4', '.' }, { '.', '6', '4', '.', '1', '.', '5', '9', '.' },
				{ '.', '9', '8', '.', '.', '.', '3', '.', '.' }, { '.', '.', '.', '8', '.', '3', '.', '2', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '6' }, { '.', '.', '.', '2', '7', '5', '9', '.', '.' } };
		String[] ss = { "ABCE", "SFCS", "ADEE", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		String sss = "()";
		int count = 0;
		// a.dfs_Permutation(s,m,result,temp);
		// a.dfs_ip(sss, string_result,"");
		// a.dfs_ip_visual(sss, string_result,"",0);
		// a.dfs_queen(b, 0, int_result);
		// a.dfs_sudoku(board,0,0,char_result);
		// System.out.println(char_result.size());
		// System.out.println(a.run_wordsearch(ss, "ABCB"));
		int[] rooms = { 1, 1, 2, 2, 2, 1 };
		selectroom(result, rooms, 5, temp, 0);
		generateParenthesis(3);
		// printcharlist(char_result);
		// use_crackpassword(3, 2);
	}

}
