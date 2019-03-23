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
		if (s == null || s.equals("")) {
			return true;
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				if (i + 1 < s.length()) {
					if (s.charAt(i + 1) == ')') {
						return isValid(s.substring(0, i) + s.substring(i + 2));
					}
				}
			} else if (s.charAt(i) == '[') {
				if (i + 1 < s.length()) {
					if (s.charAt(i + 1) == ']') {
						return isValid(s.substring(0, i) + s.substring(i + 2));
					}
				}
			} else if (s.charAt(i) == '{') {
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
		HashMap<Character, Character> mappings = new HashMap<Character, Character>();
		mappings.put(')', '(');
		mappings.put(']', '[');
		mappings.put('}', '{');
		Stack<Character> st = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char now = s.charAt(i);
			char compare = mappings.getOrDefault(now, '*');
			if (!st.empty() && compare == st.peek()) {
				st.pop();
			} else {
				st.push(s.charAt(i));
			}
		}
		return st.empty();
	}

	// 22. Generate Parentheses i think it's dfs, for easier solution read google
	// note
	public static List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<String>();
		helper22(new char[2 * n], 0, res);
		return res;
	}

	public static void helper22(char[] temp, int index, List<String> res) {
		if (index == temp.length) {
			if (valid_22(new String(temp))) {
				res.add(new String(temp));
			}
			return;
		}
		temp[index] = '(';
		helper22(temp, index + 1, res);
		temp[index] = ')';
		helper22(temp, index + 1, res);
	}

	public static boolean valid_22(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				count++;
			} else {
				count--;
			}
			if (count < 0) {
				return false;
			}
		}
		return count == 0;
	}

	// 32 Longest Valid Parentheses
	//// wrong version
	// public int longestValidParentheses(String s) {
	// Stack <Character> st = new Stack<Character>();
	// int count=0;
	// int res=0;
	// for(int i=0;i<s.length();i++)
	// {
	// if(s.charAt(i)=='(')
	// {
	// st.push(')');
	// }
	// else if(!st.isEmpty()&&st.peek()==s.charAt(i))
	// {
	// st.pop();
	// count+=2;
	// }
	// else
	// {
	// res=Math.max(count, res);
	// count=0;
	// st.push('*');
	// }
	// }
	// return res;
	// }
	// 32 Longest Valid Parentheses correct version learned from leetcode
	public int longestValidParentheses(String s) {
		Stack<Integer> st = new Stack<Integer>();
		st.push(-1);
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				st.push(i);
			} else {
				int pre = st.pop();
				if (!st.isEmpty() && s.charAt(pre) == '(') {
					max = Math.max(max, i - st.peek());
				} else {
					st.push(i);
				}
			}
		}
		return max;
	}

	// // 301. Remove Invalid Parentheses time limited
	// public List<String> removeInvalidParentheses(String s) {
	// List<String> res = new ArrayList<String>();
	// Map<String, Integer> m = new HashMap<String, Integer>();
	// if (s.length() == 0||helper301(s)) {
	// res.add(s);
	// return res;
	// }
	// bfs301(s, res, m);
	// if(res.size()==0)
	// {
	// res.add("");
	// return res;
	// }
	// List<String> trueres = new ArrayList<String>();
	// for(int i=0;i<res.size();i++)
	// {
	// if(res.get(i).length()==max301)
	// {
	// trueres.add(res.get(i));
	// }
	// }
	// return trueres;
	// }
	// int max301=0;
	// public void bfs301(String temp, List<String> res, Map<String, Integer> m) {
	// if(temp.equals(""))
	// {
	// return;
	// }
	// for (int i = 0; i < temp.length(); i++) {
	// String cut = temp.substring(0, i) + temp.substring(i + 1);
	// if (helper301(cut) && !m.containsKey(cut)&&!cut.equals("")) {
	// m.put(cut, 1);
	// res.add(cut);
	// max301=Math.max(max301, cut.length());
	// }
	// // dfs301(temp.substring(0, i)+temp.substring(i+1),res,deleted+1);
	// }
	// for (int i = 0; i < temp.length(); i++) {
	// bfs301(temp.substring(0, i) + temp.substring(i + 1), res, m);
	// }
	// }
	//
	// public boolean helper301(String s) {
	// int count = 0;
	// for (int i = 0; i < s.length(); i++) {
	// if (s.charAt(i) == '(') {
	// count++;
	// } else if (s.charAt(i) == ')') {
	// count--;
	// }
	// if (count < 0) {
	// return false;
	// }
	// }
	// return count == 0;
	// }
	// 301. Remove Invalid Parentheses
	public List<String> removeInvalidParentheses(String s) {
		int pleft = 0;
		int pright = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				pleft++;
			} else if (s.charAt(i) == ')') {
				if (pleft > 0) {
					pleft--;
				} else {
					pright++;
				}
			}
		}
		// pleft or right means how many parentheses are redundant to be legal. And if
		// ")" appear before any singel "(" it's redundant for sure
		List<String> res = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		helper301(s, pleft, pright, 0, 0, "", set);
		res = new ArrayList<String>(set);
		return res;
	}

	public void helper301(String s, int pleft, int pright, int index, int open, String temp, Set<String> set) {
		if (pleft < 0 || pright < 0 || open < 0) {
			return;
			// means if there is a soultion it will not be the 'minimum delete' beacuse we
			// count the redundant number already
			// also it may will be illegal
		}
		if (index == s.length()) {
			if (pleft == 0 && pright == 0 && open == 0) {
				set.add(temp);
			}
			return;
		}
		if (s.charAt(index) == '(') {
			helper301(s, pleft, pright, index + 1, open + 1, temp + "(", set);// use
			helper301(s, pleft - 1, pright, index + 1, open, temp, set);// not
		} else if (s.charAt(index) == ')') {
			helper301(s, pleft, pright, index + 1, open - 1, temp + ")", set);// use
			helper301(s, pleft, pright - 1, index + 1, open, temp, set);// not
		} else {
			helper301(s, pleft, pright, index + 1, open, temp + String.valueOf(s.charAt(index)), set); // just add
		}
	}

	// 329. Longest Increasing Path in a Matrix //it go through 134/137 //cache
	// added to pass
	public int longestIncreasingPath(int[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}
		int[][] cache = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				int cur = matrix[i][j];
				helper329(matrix, cache, i, j, cur, true);
			}
		}
		return res329;
	}

	int res329 = 1;

	public int helper329(int[][] matrix, int[][] cache, int i, int j, int pre, boolean first) {
		if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[i].length || (!first && pre <= matrix[i][j]))// find who
																												// pre
																												// is
		// bigger than me so
		// my value is
		// cache[pre]+1
		{
			return 0;
		}

		if (cache[i][j] > 0) {
			return cache[i][j];
		} else {
			int tempMax = 0;
			int cur = matrix[i][j];
			tempMax = Math.max(tempMax, helper329(matrix, cache, i + 1, j, cur, false));
			tempMax = Math.max(tempMax, helper329(matrix, cache, i - 1, j, cur, false));
			tempMax = Math.max(tempMax, helper329(matrix, cache, i, j + 1, cur, false));
			tempMax = Math.max(tempMax, helper329(matrix, cache, i, j - 1, cur, false));
			cache[i][j] = ++tempMax;
			res329 = Math.max(tempMax, res329);
			return tempMax;
		}
	}
	// 329. Longest Increasing Path in a Matrix second second second second second
	// second second second try
	// out of memory don't know why since list won't have this problem.
	// public int longestIncreasingPath(int[][] matrix) {
	//
	// if(matrix.length==0)
	// {
	// return 0;
	// }
	// for(int i=0;i<matrix.length;i++)
	// {
	// for (int j = 0; j < matrix[i].length; j++) {
	// String temp=""+matrix[i][j];
	// helper329(matrix, i, j+1, temp);
	// helper329(matrix, i, j-1, temp);
	// helper329(matrix, i+1, j, temp);
	// helper329(matrix, i-1, j, temp);
	// }
	// }
	// return res329;
	// }
	// int res329=1;
	// public void helper329(int[][] matrix, int i,int j,String temp)
	// {
	// if(i<0||j<0||i>=matrix.length||j>=matrix[i].length||Character.getNumericValue(temp.charAt(temp.length()-1))>=matrix[i][j])
	// {
	// return;
	// }
	// else
	// if(Character.getNumericValue(temp.charAt(temp.length()-1))<matrix[i][j]){
	// temp+=matrix[i][j];
	// res329=Math.max(res329, temp.length());
	// helper329(matrix, i, j+1, temp);
	// helper329(matrix, i, j-1, temp);
	// helper329(matrix, i+1, j, temp);
	// helper329(matrix, i-1, j, temp);
	// //temp.removeLast();
	// }
	// }
	// 329. Longest Increasing Path in a Matrix third third third third third try
	// //see first try has been modified
	// this is a nice version only dfs, but still not pass the time limit
	// public int longestIncreasingPath(int[][] matrix) {
	// if(matrix.length==0)
	// {
	// return 0;
	// }
	// for(int i=0;i<matrix.length;i++)
	// {
	// for (int j = 0; j < matrix[i].length; j++) {
	// LinkedList<Integer>temp=new LinkedList<Integer>();
	// helper329(matrix, i, j, temp,true);
	// }
	// }
	// return res329;
	// }
	// int res329=1;
	// public void helper329(int[][] matrix, int i,int
	// j,LinkedList<Integer>temp,boolean first)
	// {
	// if(i<0||j<0||i>=matrix.length||j>=matrix[i].length||(!first&&temp.getLast()>=matrix[i][j]))
	// {
	// return;
	// }
	// if(first||temp.getLast()<matrix[i][j]){
	// temp.add(matrix[i][j]);
	// res329=Math.max(res329, temp.size());
	// helper329(matrix, i, j+1,temp,false);
	// helper329(matrix, i, j-1, temp,false);
	// helper329(matrix, i+1, j,temp,false);
	// helper329(matrix, i-1, j, temp,false);
	// temp.removeLast();
	// }
	//
	// }

	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementatio
	public interface NestedInteger {

		// @return true if this NestedInteger holds a single integer, rather than a
		// nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds a
		// single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// @return the nested list that this NestedInteger holds, if it holds a nested
		// list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}

	// #339 Nested List Weight Sum
	public int depthSum(List<NestedInteger> nestedList) {
		int res = helper339(nestedList, 1);
		return res;
	}

	public int helper339(List<NestedInteger> nl, int level) {
		int res = 0;
		for (int i = 0; i < nl.size(); i++) {
			NestedInteger curr = nl.get(i);
			if (curr.isInteger()) {
				res += level * curr.getInteger();
			} else {
				res += helper339(curr.getList(), level + 1);
			}
		}
		return res;
	}

	// # 364 Nested List Weight Sum 2
	public void helper369(List<NestedInteger> nl, int level, int[] note) {
		for (int i = 0; i < nl.size(); i++) {
			NestedInteger curr = nl.get(i);
			if (curr.isInteger()) {
				note[level] += curr.getInteger();
			} else {
				note[level + 1] += helper339(curr.getList(), level + 1);
			}
		}
		return;
	}

	// #490 MAZE //brute force
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		helper490(maze, start, left, new int[maze.length][maze[0].length], destination);
		helper490(maze, start, right, new int[maze.length][maze[0].length], destination);
		helper490(maze, start, up, new int[maze.length][maze[0].length], destination);
		helper490(maze, start, down, new int[maze.length][maze[0].length], destination);
		return flag490;
	}

	boolean flag490 = false;
	int[] left = new int[] { 0, -1 };
	int[] right = new int[] { 0, 1 };
	int[] up = new int[] { -1, 0 };
	int[] down = new int[] { 1, 0 };

	public void helper490(int[][] maze, int[] start, int[] direction, int[][] visited, int[] destination) {
		int row = start[1];
		int col = start[0];
		int drow = destination[1];
		int dcol = destination[0];
		if ((row < 0 || col < 0 || col >= maze.length || row >= maze[col].length || flag490 == true
				|| maze[col][row] == 1)) {
			return;
		}
		if (start[0] == destination[0] && destination[1] == start[1]) {
			flag490 = true;
			return;
		}
		visited[col][row] = 1;
		while (!(direction[0] == 0 && direction[1] == 0)) {
			if (row + direction[1] < 0 || col + direction[0] < 0 || col + direction[0] >= maze.length
					|| row + direction[1] >= maze[col].length || maze[col + direction[0]][row + direction[1]] == 1) {
				break;
			} else {
				row += direction[1];
				col += direction[0];
			}
		}
		int[] temp = new int[] { col, row };
		if (visited[col][row] == 1) {
			return;
		}
		helper490(maze, temp, left, visited, destination);
		helper490(maze, temp, right, visited, destination);
		helper490(maze, temp, up, visited, destination);
		helper490(maze, temp, down, visited, destination);
	}

	// #695 Max Area of Island
	public int maxAreaOfIsland(int[][] grid) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				helper695(grid, j, i, visited);
			}
		}
		return max695;
	}

	int max695 = 0;

	public int helper695(int[][] grid, int x, int y, boolean[][] visited) {
		if (x < 0 || y < 0 || y >= grid.length || x >= grid[y].length || grid[y][x] == 0 || visited[y][x]) {
			return 0;
		}
		int temp = 1;
		visited[y][x] = true;
		temp += helper695(grid, x + 1, y, visited);
		temp += helper695(grid, x - 1, y, visited);
		temp += helper695(grid, x, y + 1, visited);
		temp += helper695(grid, x, y - 1, visited);
		max695 = Math.max(temp, max695);
		return temp;

	}

	// #737 Sentence Similarity II transitable similar word
	public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
		return helper737(words1, words2, pairs);
	}

	// method 1:union find
	public boolean helper737(String[] words1, String[] words2, String[][] pairs) {
		if (words1.length != words2.length) {
			return false;
		}
		Map<String, String> m = new HashMap<String, String>();

		for (int i = 0; i < pairs.length; i++) {
			String first = find737(pairs[i][0], m);
			String second = find737(pairs[i][1], m);
			if (!first.equals(second)) {
				m.put(first, second);
			}
		}
		for (int i = 0; i < words1.length; i++) {
			if (!find737(words1[i], m).equals(find737(words2[i], m))) {
				return false;
			}
		}
		return true;
	}
	public String find737(String t, Map<String, String> m) {
		while (m.containsKey(t)) {
			t = m.get(t);
		}
		return t;
	}
//#841. Keys and Rooms
public boolean canVisitAllRooms(List<List<Integer>> rooms) {
	boolean []visited=new boolean[rooms.size()];
	boolean flag=true;
	helper841(rooms, visited, 0);
	for(int i=0;i<visited.length;i++)
	{
		flag=flag&&visited[i];
	}
	return flag;
    }
public void helper841(List<List<Integer>> rooms,boolean []visited,int now)
{
	if(visited[now])
	{
		return;
	}
	visited[now]=true;
	for (int i = 0; i < rooms.get(now).size(); i++) {
		helper841(rooms, visited, rooms.get(now).get(i));
    }
}public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[]visited= new int [numCourses];//0 is unknow -1 is loop 1 is no loop ,2 is visiting
    int []n=new int[numCourses]
    for (int i = 0; i < prerequisites.length; i++) 
    {
    	int from=prerequisites[i][0];
    	int to=prerequisites[i][1];
			if(graph.get(from)==null)
			{
				graph.add(new ArrayList<Integer>());
			}
			graph.get(from).add(to);
	}
    for( int i = 0; i<graph.size();i++)
    {
        if(dfs207(i,graph,visited))
        {
            return false;
        }
    }
    return true;
    
   
   
}
public boolean dfs207(int cur, List<List<Integer>> graph,int[]visited)
{
	if(visited[cur]==2||visited[cur]==-1)
	{
		return true;
	}
	if(visited[cur]==1)
	{
		return false;
	}
	visited[cur]=2;
	for(int i=0;i<graph.get(cur).size();i++)
	{
		if(dfs207(graph.get(cur).get(i), graph, visited))
		{
			visited[cur]=-1;
			return true;
		}
	}
	visited[cur]=1;
	return false;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stubs
		Map m = new HashMap<Integer, Integer>();
		dfs a = new dfs();
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
		int[][] data = { { 6, 8 }, { 7, 2 } };
		int[][] maze = { { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0 }, { 1, 1, 0, 1, 1 },
				{ 0, 0, 0, 0, 0 } };
		selectroom(result, rooms, 5, temp, 0);
		a.hasPath(maze, new int[] { 0, 4 }, new int[] { 4, 4 });
		System.out.println("123".substring(3));
		// printcharlist(char_result);
		// use_crackpassword(3, 2);
	}

}
