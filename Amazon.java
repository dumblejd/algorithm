package algorithmtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Amazon {

	public static void highfeqword(String s, String[] ex) {
		char[] temp = s.toLowerCase().toCharArray();

		for (int i = 0; i < temp.length; i++) {
			if (!(temp[i] >= 'a' && temp[i] <= 'z' || temp[i] >= 'A' && temp[i] <= 'Z')) {
				temp[i] = ' ';
			}
		}
		String[] as = new String(temp).split(" ");
		;
		Map<String, Integer> m = new HashMap<String, Integer>();
		for (int i = 0; i < ex.length; i++) {

			m.put(ex[i].toLowerCase(), -1);
		}
		int max = -1;
		for (int i = 0; i < as.length; i++) {
			if (m.containsKey(as[i]) && m.get(as[i]) != -1) {
				m.put(as[i], m.get(as[i]) + 1);
				max = Math.max(max, m.get(as[i]));
			} else if (m.containsKey(as[i]) && m.get(as[i]) == -1) {
			} else if (as[i] != "") {
				m.put(as[i], 1);
				max = Math.max(max, m.get(as[i]));
			}
		}

		ArrayList<String> result = new ArrayList<String>();
		for (String ts : m.keySet()) {
			if (m.get(ts) == max) {
				result.add(ts);
			}
		}
		Array.print(result);
	}

	public void reorderfile(String[] file) {
		List<String> number = new ArrayList<String>();
		for (int i = 0; i < file.length; i++) {
			String[] temp = file[i].split(" ");
			if (temp[1].charAt(0) >= '0' && temp[1].charAt(0) <= '9') {
				number.add(new String(file[i]));
			}
		}
		class sort {
			boolean compare(String a, String b) {
				String keya = a.substring(0, a.indexOf(" "));
				String valuea = a.substring(a.indexOf(" ") + 1, a.length());
				String keyb = b.substring(0, a.indexOf(" "));
				String valueb = b.substring(a.indexOf(" ") + 1, a.length());
				List<String> key = new ArrayList<String>();
				List<String> value = new ArrayList<String>();
				key.add(keya);
				key.add(keyb);

				if (valuea.equals(valueb)) {
					// return ;
				}
				return false;

			}
		}
	}

	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	public ListNode reverse(ListNode head) {
		ListNode pre = null;
		ListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;

		}
		return pre;
	}

	public static int maxAreaOfIsland(int[][] grid) {
		max = 0;
		boolean[][] visit = new boolean[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				islandfinder(grid, i, j, visit);
			}
		}
		return max;
	}

	static int max = 0;

	public static int islandfinder(int[][] grid, int x, int y, boolean[][] visit) {

		if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length || visit[x][y] || grid[x][y] == 0) {
			return 0;
		}
		int sum = 1;
		if (grid[x][y] == 1) {

			visit[x][y] = true;
			sum += islandfinder(grid, x + 1, y, visit);
			sum += islandfinder(grid, x - 1, y, visit);
			sum += islandfinder(grid, x, y + 1, visit);
			sum += islandfinder(grid, x, y - 1, visit);

		}
		max = Math.max(max, sum);
		return sum;
	}

	public boolean validTicTacToe(String[] board) {
		return false;
	}

	public boolean validboard(char[][] board, int size)

	{
		// y=x+b
		int[] pxyx = new int[((size - 1) * 2) + 1];
		int[] nxyx = new int[((size - 1) * 2) + 1];
		int[] pxyo = new int[((size - 1) * 2) + 1];
		int[] nxyo = new int[((size - 1) * 2) + 1];
		int[] xx = new int[size];
		int[] yx = new int[size];
		int[] xo = new int[size];
		int[] yo = new int[size];
		int x=0;
		int o=0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 'X') {
					xx[j]++;
					yx[i]++;
					pxyx[j - i]++;
					nxyx[j + i]++;
					x++;
				}
				if (board[i][j] == 'O') {
					xo[j]++;
					yo[i]++;
					pxyo[j - i]++;
					nxyo[j + i]++;
					o++;
				}
				if (board[i][j] == ' ') {
				}
			}
		}
		if(x>o+1||o>x)
		{
			return false;
		}
		return true;
		
	}
//输出所有人的共同好友 dfs
	public List<ArrayList<Integer>> printallfriend(int[][]list)
	{
		List<List<Integer>> res= new ArrayList<List<Integer>> ();
		boolean [][]added=new boolean[list.length][];
		for(int i=0;i<list.length;i++)
		{
			added[i]=new boolean[list[i].length];
		}
		for(int i=0;i<list.length;i++)
		{
			List<Integer> temp=new ArrayList<Integer>();
			temp.add(i);
			find_connection_one(temp, list, added, i);
			if(temp.size()>0)
			{
				res.add(temp);
			}
		}
		return null;
	}
	public void find_connection_one(List<Integer> temp,int[][]list,boolean added[][],int x)
	{
		for(int i=0;i<list[x].length;i++)
		{
			if(!added[x][i])//not visited
			{
				temp.add(list[x][i]);
				added[x][i]=true;
				find_connection_one(temp, list, added,list[x][i]);  //to (x,i) 's list
			}
		}
	}
	//输出所有人的共同好友 union find
	public List<ArrayList<Integer>> uf_friend(int[][]list)
	{
		List<List<Integer>> res= new ArrayList<List<Integer>> ();
		boolean [][]added=new boolean[list.length][];
		for(int i=0;i<list.length;i++)
		{
			added[i]=new boolean[list[i].length];
		}
		for(int i=0;i<list.length;i++)
		{
			List<Integer> temp=new ArrayList<Integer>();
			find_connection_one(temp, list, added, i);
			if(temp.size()>0)
			{
				res.add(temp);
			}
		}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";
		String[] exclude = { "and", "he", "the", "to", "is", "Jack", "Jill" };
		highfeqword(text, exclude);

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(null, 0);
		map.put("java", 1);
		map.put("c++", 2);
		map.put("python", 3);
		map.put("php", 4);
		map.put("nodejs", 5);
		for (Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		System.out.println("php".hashCode() == "c++".hashCode());

		int[][] maze = { { 1, 1, 0, 0, 0 }, { 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 1 },
				{ 0, 0, 0, 0, 0 } };
		int[][] is = { { 0, 1 } };
		maxAreaOfIsland(is);
	}

}
