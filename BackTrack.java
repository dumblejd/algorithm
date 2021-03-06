package algorithmtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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

	public void helper77(int n, int k, List<List<Integer>> res, ArrayList<Integer> temp, int index) {
		if (temp.size() == k) {
			res.add(new ArrayList<Integer>(temp));
			return;
		}
		for (int i = index; i < n; i++) {
			temp.add(i);
			helper77(n, k, res, temp, i + 1);
			temp.remove(temp.size() - 1);
		}
	}

	// 216. Combination Sum III
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		helper216(n, k, res, new ArrayList<Integer>(), 1);
		return res;
	}

	public void helper216(int n, int k, List<List<Integer>> res, ArrayList<Integer> temp, int index) {
		if (temp.size() > k) {
			return;
		}
		if (n == 0 && temp.size() == k) {
			res.add(new ArrayList<Integer>(temp));
		}
		for (int i = index; i <= 9; i++) {
			if (i <= n) {
				temp.add(i);
				helper216(n - i, k, res, temp, i + 1);
				temp.remove(temp.size() - 1);
			}
		}
	}

	// #377. Combination Sum IV dp
	public int combinationSum4(int[] nums, int target) {
		Arrays.sort(nums);
		int[] comb = new int[target + 1];
		comb[0] = 1;
		for (int i = 0; i <= target; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (nums[j] > i) {
					break;
				}
				comb[i] += comb[i - nums[j]];
			}
		}
		return comb[target];
	}

	// 46. Permutations
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		helper46(nums, new boolean[nums.length], res, new ArrayList<Integer>());
		return res;
	}

	public void helper46(int[] nums, boolean[] visited, List<List<Integer>> res, ArrayList<Integer> temp) {
		if (temp.size() == nums.length) {
			res.add(new ArrayList<Integer>(temp));
		}
		for (int i = 0; i < nums.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp.add(nums[i]);
				helper46(nums, visited, res, temp);
				temp.remove(temp.size() - 1);
				visited[i] = false;
			}
		}
	}

	// 47. Permutations 2
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		helper47(nums, new boolean[nums.length], res, new ArrayList<Integer>());
		return res;
	}

	public void helper47(int[] nums, boolean[] visited, List<List<Integer>> res, ArrayList<Integer> temp) {
		if (temp.size() == nums.length) {
			res.add(new ArrayList<Integer>(temp));
		}
		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}
			if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) // be careful of the last judgement
			{
				continue;
			}
			temp.add(nums[i]);
			visited[i] = true;
			helper47(nums, visited, res, temp);
			temp.remove(temp.size() - 1);
			visited[i] = false;
		}
	}

	// 78 subset
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> temp = new ArrayList<Integer>();
		helper78(nums, 1, 0, res, temp);
		res.add(new ArrayList<Integer>());
		return res;
	}

	public void helper78(int[] nums, int n, int index, List<List<Integer>> res, List<Integer> temp) {
		if (n == nums.length + 2) {
			return;
		}
		if (!temp.isEmpty() && temp.size() == n - 1) {
			res.add(new ArrayList<Integer>(temp));
		}
		for (int i = index; i < nums.length; i++) {
			if (i > index && nums[i] == nums[i - 1])
			// already concern the duplicate condition //it will work on dupicated with
			// Arrays.sort uesed outside
			{
				continue;
			}
			temp.add(nums[i]);
			helper78(nums, n + 1, i + 1, res, temp);
			temp.remove(temp.size() - 1);
		}
	}

	// 79. Word Search
	public boolean exist(char[][] board, String word) {
		boolean flag = false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				flag = flag
						|| helper79(board, new boolean[board.length][board[0].length], new StringBuilder(word), i, j);
			}
			if (flag == true) {
				return true;
			}
		}
		return false;
	}

	public boolean helper79(char[][] board, boolean[][] visited, StringBuilder word, int x, int y) {
		boolean flag = false;
		if (word.length() == 0) {
			return true;
		}
		if (x < 0 || y < 0 || x >= board.length || y >= board[x].length || board[x][y] != word.charAt(0)
				|| visited[x][y]) {
			return false;
		}
		word.deleteCharAt(0);
		visited[x][y] = true;
		flag = flag || helper79(board, visited, word, x + 1, y) || helper79(board, visited, word, x - 1, y)
				|| helper79(board, visited, word, x, y + 1) || helper79(board, visited, word, x, y - 1);
		visited[x][y] = false;
		word.insert(0, board[x][y]);
		return flag;
	}

	// 90 subset 2 with duplicate
	// just add a sort on subset 1 I wrote
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> temp = new ArrayList<Integer>();
		Arrays.sort(nums);
		helper90(nums, 0, res, temp);
		res.add(new ArrayList<Integer>());
		return res;
	}

	public void helper90(int[] nums, int index, List<List<Integer>> res, List<Integer> temp) {
		if (index == nums.length) {
			return;
		}
		if (!temp.isEmpty()) {
			res.add(new ArrayList<Integer>(temp));
		}
		for (int i = index; i < nums.length; i++) {
			if (i > index && nums[i] == nums[i - 1])
			// already concern the duplicate condition //it will work on dupicated with
			// Arrays.sort uesed outside
			{
				continue;
			}
			temp.add(nums[i]);
			helper90(nums, i + 1, res, temp);
			temp.remove(temp.size() - 1);
		}
	}

	// 126. Word Ladder II //out time limit
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> res = new ArrayList<List<String>>();
		List<String> temp = new ArrayList<String>();
		Map<Integer, List<List<String>>> m = new HashMap<Integer, List<List<String>>>();
		Set set = new HashSet<String>();
		set.add(beginWord);
		temp.add(beginWord);
		helper126(beginWord, endWord, wordList, temp, m, set);
		if (smallest == Integer.MAX_VALUE) {
			return res;
		}

		return m.get(smallest);
	}

	int smallest = Integer.MAX_VALUE;

	public void helper126(String beginWord, String endWord, List<String> wl, List<String> temp,
			Map<Integer, List<List<String>>> m, Set<String> set) {
		if (temp.size() == wl.size() + 1) {
			return;
		}
		if (temp.get(temp.size() - 1).equals(endWord)) {
			int size = temp.size();
			if (!m.containsKey(size)) {
				m.put(size, new ArrayList<List<String>>());
			}
			m.get(size).add(new ArrayList<String>(temp));
			smallest = Math.min(smallest, size);
			return;
		}
		for (int i = 0; i < wl.size(); i++) {
			if (diff126(temp.get(temp.size() - 1), wl.get(i)) == 1 && !set.contains(wl.get(i))) {
				temp.add(wl.get(i));
				set.add(wl.get(i));
				helper126(beginWord, endWord, wl, temp, m, set);
				set.remove(wl.get(i));
				temp.remove(temp.size() - 1);
			}
		}
	}

	public int diff126(String a, String b) {
		int res = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				res++;
			}
		}
		return res;
	}

	// 140 Word Break II //out of time
	public List<String> wordBreak(String s, List<String> wordDict) {
		Set set = new HashSet<String>();
		for (int i = 0; i < wordDict.size(); i++) {
			set.add(wordDict.get(i));
		}
		List<String> res = new ArrayList<String>();
		helper140(set, 0, 1, res, new StringBuilder(s));
		return res;
	}

	public void helper140(Set<String> dict, int start, int end, List<String> res, StringBuilder temp) {
		if (end > temp.length()) {
			return;
		}
		String now = temp.substring(start, end);

		if (dict.contains(now)) {
			if (end == temp.length()) {
				res.add(new String(temp));
			}
			temp.insert(end, " ");
			helper140(dict, end + 1, end + 2, res, temp);
			temp.deleteCharAt(end);
		}
		helper140(dict, start, end + 1, res, temp);
	}

	// copy from others, look google note
	public List<String> wordBreak_140_2(String s, List<String> wordDict) {
		return DFS_140(s, wordDict, new HashMap<String, LinkedList<String>>());
	}

	// DFS function returns an array including all substrings derived from s.
	List<String> DFS_140(String s, List<String> wordDict, HashMap<String, LinkedList<String>> map) {
		if (map.containsKey(s))
			return map.get(s);

		LinkedList<String> res = new LinkedList<String>();
		if (s.length() == 0) {
			res.add("");
			return res;
		}
		for (String word : wordDict) {
			if (s.startsWith(word)) {
				List<String> sublist = DFS_140(s.substring(word.length()), wordDict, map);
				for (String sub : sublist)
					res.add(word + (sub.isEmpty() ? "" : " ") + sub);
			}
		}
		map.put(s, res);
		return res;
	}

	// reverse a stack
	public void helper_use(Stack<Integer> st) {
		st.push(0);
		for (int i = 0; i < st.size() - 1; i++) {
			helper_reverse(st, 0, i);
		}
		st.pop();
	}

	int bottom;

	public void helper_reverse(Stack<Integer> st, int index, int times) {
		if (st.size() > 1) {
			int temp = st.pop();
			helper_reverse(st, index + 1, times);
			if (index == times) {
				st.push(bottom);
			}
			st.push(temp);
		} else if (st.size() == 1) {
			bottom = st.pop();
			return;
		}

	}

	public void reverseStack(Stack<Integer> st) {
		if (st.size() == 1) {
			return;
		}

		int tmp = st.peek();
		st.pop();

		reverseStack(st);
		addToBottom(st, tmp);
	}

	public void addToBottom(Stack<Integer> st, Integer data) {
		if (st.size() == 0) {
			st.push(data);
			return;
		}

		int tmp = st.peek();
		st.pop();
		addToBottom(st, data);
		st.push(tmp);
	}

	// 212. Word Search II
	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<String>();
		TrieNode root = construct_trie(words);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				helper212(board, words, res, j, i, root);
			}
		}
		return res;
	}

	public void helper212(char[][] board, String[] words, List<String> res, int x, int y, TrieNode t) {
		if (t.word != null) {
			res.add(t.word);
			t.word = null; // de-duplicate
			return;
		}
		if (x < 0 || y < 0 || y >= board.length || x >= board[y].length || board[y][x] == '#') {
			return;
		}

		char c = board[y][x];
		if (t.next[c - 'a'] == null) {
			return;
		}
		t = t.next[c - 'a'];
		board[y][x] = '#';
		helper212(board, words, res, x + 1, y, t);
		helper212(board, words, res, x - 1, y, t);
		helper212(board, words, res, x, y + 1, t);
		helper212(board, words, res, x, y - 1, t);
		board[y][x] = c;
	}

	public TrieNode construct_trie(String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode t = root;
			for (char c : word.toCharArray()) {
				if (t.next[c - 'a'] == null)// it must be judged or it will be overwritten . word will disapear
				{
					t.next[c - 'a'] = new TrieNode();
				}
				t = t.next[c - 'a'];
			}
			t.word = word;
		}
		return root;
	}

	class TrieNode {
		TrieNode[] next = new TrieNode[26];
		String word;
	}
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res= new ArrayList<List<Integer>>();
		List<Integer> temp=new ArrayList<Integer>();
		helper251(n, 2, n,temp, res);
		return res;
	    }
		public void helper251(int n, int fac,int now, List<Integer> temp, List<List<Integer>> res) {
			if(now==1&&temp.size()>1)
			{
				res.add(new ArrayList<Integer>(temp));
	            return;
			}
			for(int i=fac;i<=now;i++)
			{
				if(now%i==0&&i!=n)
				{
				temp.add(i);
				helper251(n, i,now/i, temp, res);
				temp.remove(temp.size()-1);
				}
			}
		}
		//faster  only 1ms
		public void helper251_prune(int n, int fac, List<Integer> temp, List<List<Integer>> res) {
			for(int i=fac;i<=Math.sqrt(n);i++)
			{
				if(n%i==0)
				{
				temp.add(i);
				helper251_prune(n/i, i, temp, res);
				temp.add(n/i);
				res.add(new ArrayList<Integer>(temp));
				temp.remove(temp.size()-1);
				temp.remove(temp.size()-1);
				}
			}
		}
		//more dfs
		public void helper251_prune_more_dfs(int n, int fac, List<Integer> temp, List<List<Integer>> res) {
			
				if(fac>Math.sqrt(n))
				{
					return;
				}
				
				if(n%fac==0)
				{
				temp.add(fac);
				helper251_prune_more_dfs(n/fac, fac, temp, res);
				temp.add(n/fac);
				res.add(new ArrayList<Integer>(temp));
				temp.remove(temp.size()-1);
				temp.remove(temp.size()-1);
				}
				helper251_prune_more_dfs(n, fac+1, temp, res);
		}
		static class Trie {

		    /** Initialize your data structure here. */
			Trie[]node;
			String word;
		    public Trie() {
		        node=new Trie[26];
		    }
		    
		    /** Inserts a word into the trie. */
		    public void insert(String word) {
		    	Trie temp=this;
		    	
		    	char last = 0;
		        for (char c:word.toCharArray()) {
		        	if(temp.node[c-'a']==null)
		        	{
					temp.node[c-'a']=new Trie();
		        	}
		        	temp=temp.node[c-'a'];
					last=c;
				}
		        temp.word=word;
		    }
		    
		    /** Returns if the word is in the trie. */
		    public boolean search(String word) {
		    	Trie temp=this;
		        for (char c:word.toCharArray()) {
					if(temp.node[c-'a']==null)
					{
						return false;
					}
					temp=temp.node[c-'a'];
				}
		        if(temp.word==null)
		        {
		        	return false;
		        }
		        return true;
		    }
		    
		    /** Returns if there is any word in the trie that starts with the given prefix. */
		    public boolean startsWith(String prefix) {
		    	Trie temp=this;
		        for (char c:prefix.toCharArray()) {
					if(temp.node[c-'a']==null)
					{
						return false;
					}
					temp=temp.node[c-'a'];
				}
		        return true;
		    }
		}
		//#291 word pattern 2
		public boolean wordPatternMatch(String pattern, String str) {
	        return helper291(pattern, 0, str, 0, new HashMap<String,String>(), new HashSet<String>());
	    }
		public boolean helper291(String pattern,int i, String str,int j,Map<String,String>m,Set<String>set)
		{
			if(i==pattern.length()&&j==str.length())
			{
				return true;
			}
			if(i==pattern.length()||j==str.length())
			{
				return false;
			}
			String temp_p=String.valueOf(pattern.charAt(i));
			if(m.containsKey(temp_p))
			{
				String temp=m.get(temp_p);
				if(str.startsWith(temp,j))
				{
					return helper291(pattern, i+1, str, j+temp.length(), m, set);
				}
			}
			else//not contain
			{
				for (int k = j+1; k <= str.length(); k++) {
					String temp=str.substring(j, k);
					if(set.contains(temp))
					{
						continue;
					}
					m.put(temp_p, temp);
					set.add(temp);
					if(helper291(pattern, i+1, str, k, m, set))
					{
						return true;
					}
					m.remove(temp_p);
					set.remove(temp);
				}
			}
			return false;
		}
		//#294 flip game
		//wrong version
//		public boolean canWin(String s) {
//			StringBuilder sb= new StringBuilder(s);
//			if(sb.indexOf("++")==-1)
//			{
//				return false;
//			}
//			boolean flag=false;
//			for (int i = 0; i <sb.length()-1; i++) {
//				if(sb.substring(i,i+2).equals("++"))
//				{
//					sb.replace(i, i+2,"**");
//					flag=flag||helper294(sb,1);
//					sb.replace(i,i+2, "++");
//				}
//			}
//			return flag;
//	    }
//		public boolean helper294(StringBuilder sb,int step)
//		{
//			if(sb.indexOf("++")==-1)
//			{
//				if(step%2==0)
//				{
//					return false;
//				}
//				else
//				{
//					return true;
//				}
//			}
//			boolean flag=true;
//			for (int i = 0; i <sb.length()-1; i++) {
//				if(sb.substring(i,i+2).equals("++"))
//				{
//					sb.replace(i, i+2,"**");
//					flag=flag&&helper294(sb, step+1);
//					sb.replace(i,i+2, "++");
//				}
//			}
//			return flag;
//		}
		//from others
		public boolean canWin(String s) {
			  if (s == null || s.length() < 2) {
			    return false;
			  }
			    
			  for (int i = 0; i < s.length() - 1; i++) {
			    if (s.startsWith("++", i)) {
			      String t = s.substring(0, i) + "--" + s.substring(i + 2);
			      
			      if (!canWin(t)) {
			        return true;
			      }
			    }
			  }
			    
			  return false;
			}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BackTrack bt = new BackTrack();
		char[][] a = new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
				{ '1', '1', '0', '0', '0' } };
		int[] c = new int[] { 2, 3, 6, 7 };
		bt.helper10("mississippi", "mis*is*p*.");
		String[] ss = { "cat", "cats", "and", "sand", "dog" };
		List<String> wordList = new ArrayList<String>();
		for (int i = 0; i < ss.length; i++) {
			wordList.add(ss[i]);
		}
		bt.wordBreak("catsanddog", wordList);
		int[] nums = new int[] { 1, 1, 2 };
		char[][] cc = new char[][] { { 'a', 'b' }, { 'c', 'd' } };
		String[] words = { "ab", "cb", "ad", "bd", "ac", "ca", "da", "bc", "db", "adcb", "dabc", "abb", "acb" };
		bt.findWords(cc, words);
		Stack<Integer> st = new Stack<Integer>();
		st.push(1);
		st.push(2);
		st.push(3);
		st.push(4);
		st.push(5);
		st.push(6);
//		bt.reverseStack(st);
//		bt.permuteUnique(nums);
		bt.getFactors(12);
		Trie trie= new Trie();
		trie.insert("dog");
		bt.canWin("++++--++++");
		System.out.println();
	}

}
