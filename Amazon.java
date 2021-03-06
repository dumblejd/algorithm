package algorithmtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;

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

    public boolean validboard(char[][] board, int size) {
        // y=x+b
        int[] pxyx = new int[((size - 1) * 2) + 1];
        int[] nxyx = new int[((size - 1) * 2) + 1];
        int[] pxyo = new int[((size - 1) * 2) + 1];
        int[] nxyo = new int[((size - 1) * 2) + 1];
        int[] xx = new int[size];
        int[] yx = new int[size];
        int[] xo = new int[size];
        int[] yo = new int[size];
        int x = 0;
        int o = 0;
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
        if (x > o + 1 || o > x) {
            return false;
        }
        return true;

    }

    //输出所有人的共同好友 dfs
    public List<ArrayList<Integer>> printallfriend(int[][] list) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        boolean[][] added = new boolean[list.length][];
        for (int i = 0; i < list.length; i++) {
            added[i] = new boolean[list[i].length];
        }
        for (int i = 0; i < list.length; i++) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.add(i);
            find_connection_one(temp, list, added, i);
            if (temp.size() > 0) {
                res.add(temp);
            }
        }
        return null;
    }

    public void find_connection_one(List<Integer> temp, int[][] list, boolean added[][], int x) {
        for (int i = 0; i < list[x].length; i++) {
            if (!added[x][i])//not visited
            {
                temp.add(list[x][i]);
                added[x][i] = true;
                find_connection_one(temp, list, added, list[x][i]);  //to (x,i) 's list
            }
        }
    }

    //输出所有人的共同好友 union find
    public List<ArrayList<Integer>> uf_friend(int[][] list) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        boolean[][] added = new boolean[list.length][];
        for (int i = 0; i < list.length; i++) {
            added[i] = new boolean[list[i].length];
        }
        for (int i = 0; i < list.length; i++) {
            List<Integer> temp = new ArrayList<Integer>();
            find_connection_one(temp, list, added, i);
            if (temp.size() > 0) {
                res.add(temp);
            }
        }
        return null;
    }

    //union find 做
    public static int findCircleNum(int[][] M) {
        int[] uf = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            uf[i] = i;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] == 1 && i != j) {
                    union(uf, i, j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < M.length; i++) {
            if (uf[i] == i) {
                res++;
            }
        }
        return res;
    }

    public static int find(int[] uf, int index) {
        while (uf[index] != index) {
            index = uf[index];
        }
        return index;
    }

    public static void union(int[] uf, int a, int b) {
        int a_sup = find(uf, a);
        int b_sup = find(uf, b);
        if (a_sup != b_sup) {
            uf[a_sup] = b_sup;
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, Comparator.comparing(p -> p[0] * p[0] + p[1] * p[1]));
        return Arrays.copyOfRange(points, 0, K);
    }

    public static void reverseString(String s, StringBuffer sb, int index) {
        if (index + 1 < s.length()) {
            reverseString(s, sb, index + 1);
        }
        sb.append(s.charAt(index));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            while (i >= 2 && i <= nums.length - 2 && nums[i] == nums[i + 1]) {
                i--;
            }
            if (i < 2) {
                continue;
            }
            int l = 0;
            int r = i - 1;
            while (l < r) {
                while (r > l && r + 1 < i && nums[r + 1] == nums[r]) {
                    r--;
                }
                while (r > l && l - 1 >= 0 && nums[l - 1] == nums[l]) {
                    l++;
                }
                if (l >= r) {
                    break;
                }
                if (nums[l] + nums[r] + nums[i] < 0) {
                    l++;
                } else if (nums[l] + nums[r] + nums[i] > 0) {
                    r--;
                } else {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(nums[l]);
                    temp.add(nums[r]);
                    temp.add(nums[i]);
                    res.add(temp);
                    r--;
                    l++;
                }
            }
        }
        return res;
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int temp = target - nums[i];
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int twosum = nums[l] + nums[r];
                int sum = nums[l] + nums[r] + nums[i];
                if (twosum > temp) {

                    closest = Math.abs(sum - target) <= Math.abs(closest - target) ? sum : closest;
                    r--;
                } else if (twosum < temp) {
                    closest = Math.abs(sum - target) <= Math.abs(closest - target) ? sum : closest;
                    l++;
                } else {
                    return 0;
                }
            }
        }
        return closest;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //    public static TreeNode desi(StringBuffer data)  only work with single value
//    {
//        
//        String now = data.substring(0,1);
//        data.delete(0,1);
//        if(now.equals("x"))
//        {
//            return null;
//        }
//        TreeNode root = new TreeNode(Integer.valueOf(now));
//        root.left=desi(data);
//        root.right=desi(data);
//        return root;
//    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        seri(sb, root);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void seri(StringBuffer sb, TreeNode root) {
        if (root == null) {
            sb.append("x,");
            return;
        }
        sb.append(root.val);
        sb.append(",");
        seri(sb, root.left);
        seri(sb, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] stringArray = data.split(",");
        ArrayList<String> list = new ArrayList<String>();
        for (String t : stringArray) {
            list.add(t);
        }
        return desi(list);
    }

    public TreeNode desi(ArrayList<String> list) {

        String now = list.get(0);
        list.remove(0);
        if (now.equals("x")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(now));
        root.left = desi(list);
        root.right = desi(list);
        return root;
    }

    public String customSortString(String S, String T) {
        int[] c = new int[26];
        char[] res = T.toCharArray();
        int index = Integer.MAX_VALUE;
        for (char temp : S.toCharArray()) {
            c[temp - 'a'] = index--;
        }
        //Arrays.sort(res,(char aa,char bb)->{return c[bb-'a']-c[aa-'a'];});
        for (int i = 0; i < T.length(); i++) {
            for (int j = i; j < T.length(); j++) {
                int a = T.charAt(i) - 'a';
                int b = T.charAt(j) - 'a';
                if (c[a] < c[b]) {
                    char temp = res[i];
                    res[i] = res[j];
                    res[j] = temp;
                }
            }
        }
        return new String(res);
    }

    public int[][] kClosest2(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((int[] a, int[] b) -> a[0] * a[0] + a[1] * a[1] <= b[0] * b[0] + b[1] * b[1] ? -1 : 1);
        for (int[] t : points) {
            pq.offer(t);
        }
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

    class LRUCache {
        class Node {
            int k;
            int v;
            Node next;
            Node pre; // in order to get the last one quickly

            public Node(int key, int value) {
                this.k = key;
                this.v = value;
            }
        }

        Map<Integer, Node> m;
        Node head;
        Node tail;
        int capacity;
        int count;

        public LRUCache(int capacity) {
            m = new HashMap<Integer, Node>();
            count = 0;
            this.capacity = capacity;
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            if (m.get(key) != null) {
                Node temp = m.get(key);
                remove_f_list(temp);
                add_t_list(temp);
                return temp.v;
            } else {
                return -1;
            }
        }

        public void remove_f_list(Node remove) {
            //m.remove(remove.k);//remove from hashmap
            remove.pre.next = remove.next;
            remove.next.pre = remove.pre;
        }

        public void add_t_list(Node add) {
            Node second = head.next;
            head.next = add;
            add.pre = head;
            add.next = second;
            second.pre = add;

        }

        public void put(int key, int value) {
            if (m.get(key) != null) {
                Node temp = m.get(key);
                temp.v = value;
                remove_f_list(temp);
                add_t_list(temp);
            } else {
                if (count >= capacity) {
                    Node delete = tail.pre;
                    remove_f_list(delete);
                    m.remove(delete.k);
                    count--;
                    put(key, value);
                } else {
                    Node add = new Node(key, value);
                    add_t_list(add);
                    m.put(key, add);
                    count++;
                }

            }
        }
    }

    //5 longest substring Palindrome   dp
    public String longestPalindrome(String s) {

        boolean[][] dp = new boolean[s.length()][s.length()];
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int max = 0;
        int left = 0;
        int right = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i < 2) || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }
// 819 most common word 自己写的太蹩脚了 这是别人的 自己的再leetcode端
    public String mostCommonWord(String p, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> count = new HashMap<>();
        String[] words = p.replaceAll("\\W+" , " ").toLowerCase().split("\\s+");
        for (String w : words) if (!ban.contains(w)) count.put(w, count.getOrDefault(w, 0) + 1);
        return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
//937 重新排序文件   主要用到了String[]asub=a.split(" ",2);
public String[] reorderLogFiles(String[] logs) {
    Arrays.sort(logs,new Comparator<String>(){
        @Override
        public int compare(String a,String b)
        {
            String[]asub=a.split(" ",2);
            String[]bsub=b.split(" ",2);

            boolean an=Character.isDigit(asub[1].charAt(0));
            boolean bn=Character.isDigit(bsub[1].charAt(0));
            if(!an&&!bn)
            {
                int compare=asub[1].compareTo(bsub[1]);
                if(compare==0)
                {
                    return asub[0].compareTo(bsub[0]);
                }
                return compare;
            }
            else if(an&&bn){
                return 0;
            }
            else
            {
                //put the english before number
                return asub[1].charAt(0)<bsub[1].charAt(0)?1:-1;
            }
        }
    });
    return logs;
}
//138 copy list with random pointer
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
public Node copyRandomList(Node head) {
    Map<Node,Node> m=new HashMap<Node,Node>();
    Node point=head;
    while(point!=null)
    {
        Node clone=new Node();
        clone.val=point.val;
        m.put(point,clone);
        point=point.next;
    }
    point=head;
    while(point!=null)
    {
        Node clone=m.get(point);
        clone.next=m.get(point.next);
        clone.random=m.get(point.random);
        point=point.next;
    }
    return m.get(head);

}
//138 copy list with random pointer, without map
//solve it without map , use extra o(n) space
public Node copyRandomList_2(Node head) {
    if(head==null)
    {
        return null;
    }
    Node p=head;
    while(p!=null)
    {
        Node clone=new Node();
        clone.val=p.val;
        clone.next=p.next;
        p.next=clone;
        p=clone.next;
    }
    p=head;
    Node res=head.next;
    while(p!=null)
    {
        Node clone=p.next;
        if(p.random!=null)
        {
            clone.random=p.random.next;
        }
        p=clone.next;
    }
    p=head;
    while(p!=null)
    {
        Node clone=p.next;
        p.next=clone.next;
        clone.next=p.next==null?null:p.next.next;
        p=p.next;
    }
    return res;
}
//prison
public int[] prisonAfterNDays(int[] cells, int N) {
    Map<String,Integer> m = new HashMap<String,Integer>();
    m.put(Arrays.toString(cells),0);
    int cur=1;
    while(N>=cur)
    {
        int[] cell=new int[cells.length];
        cell[0]=0;
        cell[cell.length-1]=0;
        for(int i=1;i<cell.length-1;i++)
        {
            cell[i]=cells[i-1]==cells[i+1]?1:0;
        }
        String temp=Arrays.toString(cell);
        if(m.get(temp)==null)
        {
            m.put(temp,cur);
        }
        else
        {
            int start=m.get(temp);
            int loop=cur-start;
            while(cur+loop<=N)
            {
                cur+=(N-cur)/loop*loop;  //faster
            }
            m.put(temp,cur);
        }

        cur++;
        cells=cell;
    }
    return cells;
}
    class MedianFinder {

        /** initialize your data structure here. */
        PriorityQueue<Integer> pqlg;
        PriorityQueue<Integer> pqsm;
        int size=0;
        public MedianFinder() {
            pqlg=new PriorityQueue<Integer>((a,b)->b-a);//
            pqsm=new PriorityQueue<Integer>();
            size=0;
        }

        public void addNum(int num) {
            pqlg.add(num);
            pqsm.add(pqlg.poll());
            if(pqsm.size()-1>pqlg.size())
            {
                pqlg.add(pqsm.poll());
            }
            size++;
        }

        public double findMedian() {
            if(size%2==0)
            {
                return (pqlg.peek()+pqsm.peek())/2.0;
            }
            else
            {
                return pqsm.peek();
            }
        }
    }
    //103 zig zag
    //this is bfs   if for dfs just use normal dfs and res.get(level).add(0,element) or nomral add
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root==null)
        {
            return res;
        }
        Queue <TreeNode>q = new LinkedList<TreeNode>();
        q.offer(root);
        int seq=1;
        while(!q.isEmpty())
        {
            int limit=q.size();
            List<Integer> tmp = new ArrayList<Integer>();
            for(int i=0;i<limit;i++)
            {
                TreeNode now = q.poll();
                //each level add according to level number  //这题只是 每一层add(0,element) 或者正常add的问题
                if(seq%2==0)
                {
                    tmp.add(0,now.val);
                }
                else
                {
                    tmp.add(now.val);
                }
                if(now.left!=null)
                {
                    q.offer(now.left);
                }
                if(now.right!=null)
                {
                    q.offer(now.right);
                }

            }
            res.add(tmp);
            seq++;
        }
        return res;
    }
    //895
    class FreqStack {
        List<Stack<Integer>> sl;
        Map<Integer,Integer> map;
        public FreqStack() {
            sl= new ArrayList<Stack<Integer>>();
            map= new HashMap<Integer,Integer>();
        }

        public void push(int x) {
            int times = map.getOrDefault(x,0);
            if(sl.size()<times+1)
            {
                sl.add(new Stack<Integer>());
            }
            sl.get(times).push(x);
            map.put(x,times+1);
        }

        public int pop() {
            int res = sl.get(sl.size()-1).pop();
            if(sl.get(sl.size()-1).size()==0)
            {
                sl.remove(sl.size()-1);
            }
            map.put(res,map.get(res)-1);
            return res;
        }
    }
    //2 两数相加add two number
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode p = res;
        int bring=0;
        while(l1!=null||l2!=null||bring!=0)
        {
            int now = (l1!=null?l1.val:0)+(l2!=null?l2.val:0)+bring;
            bring = now/10;
            now = now % 10;
            ListNode t = new ListNode(now);
            p.next=t;
            p=p.next;
            if(l1!=null)
            {
                l1=l1.next;
            }
            if(l2!=null)
            {
                l2=l2.next;
            }
        }
        return res.next;
    }
    //675 golf tree cut
    static int[][] dir = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
    public int cutOffTree(List<List<Integer>> forest) {
        if(forest.get(0).get(0)==0)
        {
            return -1;
        }
        PriorityQueue<int[]> pq= new PriorityQueue<int[]>((a,b)->a[2]-b[2]);
        for(int i=0;i<forest.size();i++)
        {
            for(int j=0;j<forest.get(i).size();j++)
            {
                if(forest.get(i).get(j)>0)
                {
                    int[] t = new int[]{i,j,forest.get(i).get(j)};
                    pq.add(t);
                }
            }
        }
        int step=0;
        int []s=new int[2];//to start with 0,0
        while(!pq.isEmpty())
        {

            int[] e=pq.poll();
            int available=step;
            step+=findminstep(forest,s,e);//find from now to next node;
            if(available>step)
            {
                return -1;
            }
            s=e.clone();
        }
        return step;

    }
    public int findminstep(List<List<Integer>> forest,int[]s,int[]e)
    {
        boolean[][] visited=new boolean[forest.size()][forest.get(0).size()];
        Queue <int[]>q = new LinkedList<int[]>();
        q.add(s);
        int step=0;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                int[]t=q.poll();
                if(t[0]==e[0]&&t[1]==e[1])
                {
                    return step;
                }
                visited[t[0]][t[1]]=true;
                for(int[]d:dir)//four direction
                {
                    int x=t[0]+d[0];
                    int y=t[1]+d[1];
                    if(x<0||y<0||x>=forest.size()||y>=forest.get(0).size()||visited[x][y]||forest.get(x).get(y)==0)
                    {

                    }
                    else
                    {
                        q.add(new int[]{x,y});
                    }
                }
            }
            step++;
        }
        return -1;
    }
    //642 design search auto complete  注意下面的想法
    class AutocompleteSystem {
        Trie root;
        String prefix;//so far the words the user input
        class Trie{
            HashMap<String,Integer>history;//times of string
            HashMap<Character,Trie>node;//trie node that can have son
            boolean isSentence;
            Trie()
            {
                history=new HashMap<String,Integer>();
                node=new HashMap<Character,Trie>();
            }
        }
        class Times{ //for pq use
            String sentence;
            int count;
            Times(String s,int c)
            {
                this.sentence=s;
                this.count=c;
            }
        }
        public void add(String s, int count)
        {
            Trie point=root;
            for(char t:s.toCharArray())
            {
                if(point.node.get(t)==null)//grow the tree
                {
                    point.node.put(t,new Trie());
                }

                point=point.node.get(t);
                point.history.put(s,point.history.getOrDefault(s,0)+count);//will add times of sentence in each node

            }
            point.isSentence=true;//bottom
        }
        public AutocompleteSystem(String[] s, int[] times) {
            root=new Trie();
            prefix="";
            for(int i=0;i<s.length;i++)
            {
                add(s[i],times[i]);
            }
        }

        public List<String> input(char c)
        {
            if (c == '#') {
                add(prefix, 1);
                prefix = "";
                return new ArrayList<String>();
            }
            PriorityQueue<Times> pq=new PriorityQueue<Times>(new Comparator<Times>(){
                @Override
                public int compare(Times a,Times b)
                {
                    if(a.count==b.count)
                    {
                        return a.sentence.compareTo(b.sentence);
                    }
                    else
                    {
                        return b.count-a.count;
                    }
                }
            });
            prefix+=c;
            Trie point=root;
            for(char t:prefix.toCharArray())
            {
                if(point.node.get(t)==null)
                {
                    return new ArrayList<String>();
                }
                point=point.node.get(t);
            }
            for(String s:point.history.keySet())
            {
                pq.add(new Times(s,point.history.get(s)));
            }
            List<String> res = new ArrayList<String>();
            for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
                res.add(pq.poll().sentence);
            }
            return res;
        }
    }

    /**
     * Your AutocompleteSystem object will be instantiated and called as such:
     * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
     * List<String> param_1 = obj.input(c);
     */
    // k closest to original point  ads
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String text = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";
//		String[] exclude = { "and", "he", "the", "to", "is", "Jack", "Jill" };
//		highfeqword(text, exclude);
//
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		map.put(null, 0);
//		map.put("java", 1);
//		map.put("c++", 2);
//		map.put("python", 3);
//		map.put("php", 4);
//		map.put("nodejs", 5);
//		for (Entry<String, Integer> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + ": " + entry.getValue());
//		}
//		System.out.println("php".hashCode() == "c++".hashCode());
//		 int[][] m= new int[][]{{1,1,0},{1,1,0},{0,0,1}};
//		 findCircleNum(m);
//		int[][] maze = { { 1, 1, 0, 0, 0 }, { 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 1 },
//				{ 0, 0, 0, 0, 0 } };
//		int[][] is = { { 0, 1 } };
//		maxAreaOfIsland(is);
        StringBuffer sb = new StringBuffer("2,1,x,x,3,x,x");
        //desi(sb.toString().split(","),0);
        reverseString("dijin", sb, 0);
        System.out.println(sb.toString());
        //int []input=new int[] {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        int[] input = new int[]{-3, -2, -5, 3, -4};
        threeSumClosest(input, -1);
        //threeSum(input);
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((int[] a, int[] b) -> a[0] * a[0] + a[1] * a[1] <= b[0] * b[0] + b[1] * b[1] ? -1 : 1);
        Amazon a = new Amazon();
        Set<String>ban=new HashSet<String>();
//        LRUCache lru=new LRUCache(2);
//        lru.put(1,1);
//        lru.put(2,2);
//        lru.get(1);
//        lru.put(3,3);
//        lru.get(2);
//        lru.put(4,4);
        List<List<Integer>> forest = new ArrayList<List<Integer>>();
       List<Integer> t1 =new ArrayList<>();
       t1.add(1);
       t1.add(2);
       t1.add(3);
        forest.add(t1);
        List<Integer> t2 =new ArrayList<>();
        t2.add(0);
        t2.add(0);
        t2.add(4);
        forest.add(t2);
        List<Integer> t3 =new ArrayList<>();
        t3.add(7);
        t3.add(6);
        t3.add(5);
        String aaa = "2";
        aaa+='c';

        forest.add(t3);
        a.cutOffTree(forest);
        int []test=new int[]{0,1,0,1,1,0,0,1};
        a.prisonAfterNDays(test,27);
    }

}
