package algorithmtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Tree {
	// #96
	public int numTrees(int n) {
		int[] num = new int[n + 1];
		num[0] = 1;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < i; j++) {
				num[i] += num[j] * num[i - j - 1];
			}
		}
		return num[n];
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	// #98
	// this version is wrong since it allows [10,5,15,null,null,6,20] to be true
	// which 6 is in wrong place
	// public static boolean isValidBST(TreeNode root) {
	// boolean judge=true;
	//
	// if(root.left!=null)
	// {
	// if(root.val<=root.left.val)
	// return false;
	// judge=judge==false?false:isValidBST(root.left);
	// }
	// if(root.right!=null)
	// {
	// if(root.val>=root.right.val)
	// return false;
	// judge=judge==false?false:isValidBST(root.right);
	// }
	//
	// return judge;
	// }
	// # failed version will not pass [3,1,5,0,2,4,6]
	// public static boolean isValidBST(TreeNode root,int sup) {
	// boolean judge=true;
	// if(root==null)
	// {
	// return true;
	// }
	// if(root.left!=null)
	// {
	// if(root.val<=root.left.val||root.left.val<=sup)
	// return false;
	// judge=judge==false?false:isValidBST(root.left,root.val);
	// }
	// if(root.right!=null)
	// {
	// if(root.val>=root.right.val||root.right.val>=sup)
	// return false;
	// judge=judge==false?false:isValidBST(root.right,root.val);
	// }
	//
	// return judge;
	// }
	// #98
	private static int lastVal = Integer.MIN_VALUE;
	private static boolean firstNode = true;

	public static boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (!isValidBST(root.left)) {
			return false;
		}
		if (!firstNode && lastVal >= root.val) {
			return false;
		}
		firstNode = false;
		lastVal = root.val;
		if (!isValidBST(root.right)) {
			return false;
		}
		return true;
	}

	// #99
	private TreeNode pre = null;
	private TreeNode first = null;
	private TreeNode second = null;

	public void recoverTree(TreeNode root) {
		traverse99(root);
		int temp = first.val;
		first.val = second.val;
		second.val = temp;
	}

	public void traverse99(TreeNode root) {
		if (root == null)
			return;
		traverse99(root.left);
		if (root.val < pre.val) {
			if (first == null) {
				first = pre;
			}
			second = root;
		}
		pre = root;
		traverse99(root.right);

	}

	// #100
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null) // since last if have exclude both null condition
		{
			return false;
		}
		if (p.val != q.val) {
			return false;
		}
		if (!isSameTree(p.left, q.left)) {
			return false;
		}
		if (!isSameTree(p.right, q.right)) {
			return false;
		}
		return true;

	}

	// #101
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return helper101(root.left, root.right);
	}

	public boolean helper101(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}
		if (left.val != right.val) {
			return false;
		}
		return helper101(left.left, right.right) && helper101(left.right, right.left);
	}

	// #103 dfs method and bfs method bfs is written by me dfs is copied since it's
	// easy(though dfs is smarter)
	// # bfs
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Stack<TreeNode> pre = new Stack<TreeNode>();
		Stack<TreeNode> curr = new Stack<TreeNode>();
		int sequence = 0;
		if (root == null)
			return res;
		pre.push(root);

		while (!pre.isEmpty()) {
			List<Integer> row = new ArrayList<Integer>();
			while (!pre.isEmpty()) {
				TreeNode temp = pre.pop();
				row.add(temp.val);
				if (sequence == 0) {
					if (temp.left != null) {
						curr.push(temp.left);
					}
					if (temp.right != null) {
						curr.push(temp.right);
					}
				} else {
					if (temp.right != null) {
						curr.push(temp.right);
					}
					if (temp.left != null) {
						curr.push(temp.left);
					}
				}
			}
			res.add(row);
			pre = curr;
			curr = new Stack<TreeNode>();
			sequence = 1 - sequence;
		}
		return res;
	}

	// #dfs
	public List<List<Integer>> zigzagLevelOrderdfs(TreeNode root) {
		List<List<Integer>> sol = new ArrayList<>();
		travel(root, sol, 0);
		return sol;
	}

	private void travel(TreeNode curr, List<List<Integer>> sol, int level) {
		if (curr == null)
			return;

		if (sol.size() <= level) {
			List<Integer> newLevel = new LinkedList<>();
			sol.add(newLevel);
		}

		List<Integer> collection = sol.get(level);
		if (level % 2 == 0)
			collection.add(curr.val);
		else
			collection.add(0, curr.val);

		travel(curr.left, sol, level + 1);
		travel(curr.right, sol, level + 1);
	}

	// #104
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
	}

	// #105 Construct Binary Tree from Inorder and preorder Traversal
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return helper105(0, 0, inorder.length - 1, preorder, inorder);
	}

	public TreeNode helper105(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if (preStart > preorder.length - 1 || inStart > inEnd) // since for pre it just take one number, for in, it pick
																// sub array(it can be at first or middle or end)
		{
			return null;
		}
		TreeNode root = new TreeNode(preorder[preStart]);// take the root one
		int inIndex = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				inIndex = i;
			}
		}
		root.left = helper105(preStart + 1, inStart, inIndex - 1, preorder, inorder);
		root.right = helper105(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
		return root;
	}

	// #106. Construct Binary Tree from Inorder and Postorder Traversal
	public TreeNode buildTree106(int[] inorder, int[] postorder) {
		return helper106(postorder.length - 1, 0, inorder.length - 1, inorder, postorder);
	}

	public TreeNode helper106(int preStart, int inStart, int inEnd, int[] inorder, int[] postorder) {
		if (preStart < 0 || inStart > inEnd) // except note in 105, here prestart should be judged by 0 since it's get
												// from the last one to head
		{
			return null;
		}
		TreeNode root = new TreeNode(postorder[preStart]);// take the root one
		int inIndex = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				inIndex = i;
			}
		}
		root.left = helper106(preStart - (inEnd - inIndex) - 1, inStart, inIndex - 1, inorder, postorder);
		root.right = helper106(preStart - 1, inIndex + 1, inEnd, inorder, postorder);
		return root;
	}

	// #110. Balanced Binary Tree
	// o(n2
	public boolean isBalanced(TreeNode root) {

		if (root == null) {
			return true;
		}
		// isBalanced(root.left);
		// isBalanced(root.right);
		return isBalanced(root.left) && isBalanced(root.right)
				&& Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1;
	}

	// o(n
	public int dfsheight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftheight = dfsheight(root.left);
		if (leftheight == -1)
			return -1;

		int rightheight = dfsheight(root.right);
		if (rightheight == -1)
			return -1;

		if (Math.abs(rightheight - leftheight) > 1)
			return -1;
		return Math.max(leftheight, rightheight) + 1;
	}

	// #111 Minimum Depth of Binary Tree
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		return (left == 0 || right == 0) ? right + left + 1 : Math.min(left, right) + 1;
	}

	// #112 path sum
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		if (root.left == null && root.right == null && root.val == sum) // it will return true only it's a leaf and
																		// val=sum
		{
			return true;
		}
		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	// #113 path sum 2 with path returned
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> temp = new ArrayList<Integer>();
		helper113(root, sum, res, temp);
		return res;
	}

	// ↓ too much space consume
	// public void helper113(TreeNode root, int sum, List<List<Integer>> res,
	// List<Integer> temp) {
	// if (root == null) {
	// return;
	// }
	// if (root.left == null && root.right == null && sum == root.val) {
	// temp.add(root.val);
	// res.add(new ArrayList<Integer>(temp));
	// return;
	// }
	//
	// temp.add(root.val);
	// helper113(root.left, sum - root.val, res, new ArrayList<Integer>(temp));
	// helper113(root.right, sum - root.val, res, new ArrayList<Integer>(temp));
	// }
	public void helper113(TreeNode root, int sum, List<List<Integer>> res, List<Integer> temp) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null && sum == root.val) {
			temp.add(root.val);
			res.add(new ArrayList<Integer>(temp));
			temp.remove(temp.size() - 1);
			return;
		}

		temp.add(root.val);
		helper113(root.left, sum - root.val, res, temp);
		helper113(root.right, sum - root.val, res, temp);
		temp.remove(temp.size() - 1);
	}

	// #114 Flatten Binary Tree to Linked List
	public void flatten(TreeNode root) {
		pre114 = new TreeNode(-1);
		TreeNode temp = pre114;
		helper114(root);
		root = temp.right;

	}

	// failed version it can't be done, it needs an outside variable
	// public TreeNode helper114(TreeNode root,TreeNode pre)
	// {
	// if(root==null)
	// {
	// return null;
	// }
	// pre.right=new TreeNode(root.val);
	// if(root.left!=null)
	// {
	// pre=helper114(root.left,pre.right);
	// }
	// if(root.right!=null)
	// {
	// pre=helper114(root.right,pre.right);
	// }
	// return pre;
	// }
	// this version actually work, but since it's void,so the result can't be
	// returned(!!!change root=res is not actually changing outside object)
	// private TreeNode pre114=null;
	// public void helper114(TreeNode root)
	// {
	// if(root==null)
	// {
	// return;
	// }
	// pre114.right=new TreeNode(root.val);
	// pre114=pre114.right;
	// if(root.left!=null)
	// {
	// helper114(root.left);
	// }
	// if(root.right!=null)
	// {
	// helper114(root.right);
	// }
	// }
	private TreeNode pre114 = null;

	public void helper114(TreeNode root) {
		if (root == null) {
			return;
		}

		if (pre114 != null) {
			pre.left = null;
			pre.right = root;
		}
		pre114 = root;
		TreeNode right = root.right;
		helper114(root.left);
		helper114(right);
	}

	// post order iterator without recursion
	public void traverse_post(TreeNode root) {
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode current = root;
		TreeNode lastoutput = null;
		while (current != null || !s.isEmpty()) {
			while (current != null) {
				s.push(current);
				current = current.left;
			}
			current = s.pop(); // it should use pop otherwise current would be null caused by upper while
			while (current.right == null || current.right == lastoutput)// it has right or it's right has been ouput
			// here is while not if otherwise it will repeat add some point
			{
				System.out.println(current.val); // output
				if (s.isEmpty()) {
					return;
				}
				lastoutput = current;
				current = s.pop(); // re get a new node to continue
			}
			// current nodes from here has right node that hasn't been output. so re put
			// them into the stack since it's a post order
			s.push(current); // push again the current since it has right node to push and process
			current = current.right; // process right node
		}
	}

	// inorder traver
	public void traverse_in(TreeNode root) {
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode current = root;
		while (current != null || !s.isEmpty()) {
			while (current != null) {
				s.push(current);
				current = current.left;
			}
			if (!s.isEmpty()) {
				current = s.pop();
				s.push(current.right);
				current = s.pop();
			}
		}
	}

	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}

	// #116 Populating Next Right Pointers in Each Node
	public void connect(TreeLinkNode root) {
		if (root == null) {
			return;
		}
		if (root.left != null && root.right != null) {
			root.left.next = root.right;
		}
		if (root.next != null && root.next.right != null && root.left != null) {
			root.right.next = root.next.left;
		}
		connect(root.left);
		connect(root.right);
	}

	// hard #124 Binary Tree Maximum Path Sum

	// failed version. if this question is to find a way (leaf to leaf,any node to
	// leaf) this will work
	// public int maxPathSum(TreeNode root) {
	// max124=root.val;
	// helper124(root);
	// return max124;
	// }
	// private int max124=0;
	// public int helper124(TreeNode root) {
	// if(root==null)
	// {
	// return 0;
	// }
	// int left=root.val+helper124(root.left);
	// int right=root.val+helper124(root.right);
	// int decide=Math.max(left, right);
	// int temp=Math.max(decide, left+right-root.val);//left or right or connect
	// left and right is biggest
	// //left+right-root.val:because it added one more root value;
	// max124=Math.max(temp, max124);
	// return decide;
	// }
	// #124 hard second try : success
	public int maxPathSum(TreeNode root) {
		max124 = root.val;
		helper124(root);
		return max124;
	}

	private int max124 = 0;

	public int helper124(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = root.val + helper124(root.left);
		int right = root.val + helper124(root.right);
		int decide = Math.max(left, right);
		int temp = Math.max(decide, left + right - root.val);
		max124 = Math.max(max124, temp);

		if (left < 0) {
			left = 0;
		}
		if (right < 0) {
			right = 0;
		}
		// left or right = 0 means path in left or right will not be choosed
		return Math.max(left, right);
	}

	// #156.Binary Tree Upside Down
	public void helper156(TreeNode root, TreeNode pre) {
		if (root == null) {
			return;
		}
		if (root.left != null) {
			helper156(root.left, root);
		}
		root.right = pre;
		if (pre != null) {
			root.left = pre.right;
			pre.left = null;
			pre.right = null;
		}
	}

	// # 230. Kth Smallest Element in a BST //this is basic version
	// consider the sequence of number in BST, the next bigger number is in
	// (root.right's leftest node) if root has right,
	// or it will be the upper node
	public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> s = new Stack<>();
		while (root != null) {
			s.push(root);
			root = root.left;
		}
		TreeNode current = null;
		while (k > 0) {
			current = s.pop();
			k--;

			if (current.right != null) {
				s.push(current.right);
				TreeNode temp = s.peek().left;
				while (temp != null) {
					s.push(temp);
					temp = temp.left;
				}
			}
		}
		return current.val;
	}
	// #230
	// Follow up:What if the BST is modified (insert/delete operations) often and
	// you need to find the kth smallest frequently?
	// How would you optimize the kthSmallest routine?
	// 我没实现follow up 主要思想是 记录本node 下有多少个节点，也就是在检测 root.left 下有多少个结点 就知道
	// 有比root小的数有多少了个，再分类讨论

	// #235
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		helper235(root, p.val, q.val);
		return res235;
	}

	TreeNode res235;

	public int helper235(TreeNode root, int p, int q) {
		if (root == null) {
			return 0;
		}
		int left = helper235(root.left, p, q);
		int right = helper235(root.right, p, q);
		int self = 0;
		if (root.val == p || root.val == q) {
			self = 1;
		}
		if (left + right + self >= 2) {
			res235 = res235 == null ? root : res235;// if it's null set the res
		}
		return left + right + self;
	}
	// easier method for 235 since two can be on each side,all left,all right
	// public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	// if(root.val > p.val && root.val > q.val){
	// return lowestCommonAncestor(root.left, p, q);
	// }else if(root.val < p.val && root.val < q.val){
	// return lowestCommonAncestor(root.right, p, q);
	// }else{
	// return root;
	// }
	// }

	// #236 Lowest Common Ancestor of a Binary Tree
	// My method for 235 will work too
	// #250 Count Univalue Subtrees
	int count250 = 0;

	public boolean helper250(TreeNode root, int rootval) {
		if (root == null) {
			return true;
		}
		boolean left = helper250(root.left, root.val);
		boolean right = helper250(root.right, root.val);
		if (root.left == null && root.right == null) {
			count250++;
			return true;
		}
		if (left && right) {
			count250++;
			if (root.val == rootval) {
				return true;
			}
		}
		return false;
	}

	// #257 Binary Tree Paths it's an easy problem but my soultion is far more
	// complicated
	public List<String> binaryTreePaths(TreeNode root) {
		helper257(root, new StringBuffer());
		return res257;
	}

	List<String> res257 = new ArrayList<String>();

	public void helper257(TreeNode root, StringBuffer sb) {
		if (root == null) {
			return;
		}
		String val = Integer.toString(root.val);
		sb.append(val + "->");
		if (root.left == null && root.right == null) {
			sb.delete(sb.length() - 2, sb.length());
			String temp = new String(sb);
			res257.add(temp);
			return;
		}
		helper257(root.left, new StringBuffer(sb));
		helper257(root.right, new StringBuffer(sb));
		
	}

	// # 270: Closest Binary Search Tree Value
	// Given a non-empty binary search tree and a target value, find the value in
	// the BST that is closest to the target.
	//
	// Note:
	//
	// Given target value is a floating point.
	// You are guaranteed to have only one unique value in the BST that is closest
	// to the target.
	int res = Integer.MAX_VALUE;

	public void helper270(TreeNode root, double target) {
		if (root == null) {
			return;
		}
		if (root.val > target) {
			res = Math.abs(res - target) > (root.val - target) ? root.val : res;
			helper270(root.left, target);
		}
		if (root.val < target) {
			res = Math.abs(res - target) > (target - root.val) ? root.val : res;
			helper270(root.left, target);
		}
	}

	// #297 hard Serialize and Deserialize Binary Tree
	// Encodes a tree to a single string.
	// this version is transform tree to string like leetcode
	public String serialize(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		LinkedList res = new LinkedList<String>();

		q.add(root);
		while (!q.isEmpty()) {
			root = q.poll();
			if (root == null) {
				res.add("x");
				continue;

			}
			res.add(root.val);
			q.add(root.left);
			q.add(root.right);
		}
		String s = "";
		while (res.getLast().equals("x")) {
			res.removeLast();
		}
		for (int i = 0; i < res.size() - 1; i++) {
			s += res.get(i) + ",";
		}
		s += res.getLast();
		return s;
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data.equals(""))
			return null;
		String[] c = data.split(",");
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		TreeNode root = new TreeNode(Integer.valueOf(c[0]));
		q.add(root);
		for (int i = 1; i < c.length; i++) {
			TreeNode parent = q.poll();
			if (!c[i].equals("x")) {
				TreeNode temp = new TreeNode(Integer.valueOf(c[i]));
				parent.left = temp;
				q.add(temp);
			}
			if (i + 1 < c.length) {
				if (!c[++i].equals("x")) {
					TreeNode temp = new TreeNode(Integer.valueOf(c[i]));
					parent.right = temp;
					q.add(temp);
				}
			}
		}
		return root;
	}

	// #298 Binary Tree Longest Consecutive Sequence
	int res298 = 1;

	public void helper298(TreeNode root, TreeNode pre, int biggest) {
		if (root == null) {
			return;
		}
		if (root.val + 1 == pre.val) {
			biggest++;
			res298 = Math.max(res298, biggest);
		} else {
			res298 = Math.max(res298, biggest);
			biggest = 1;
		}
		helper298(root.left, root, biggest);
		helper298(root.right, root, biggest);
	}

	// 404. Sum of Left Leaves
	int res404 = 0;

	public int sumOfLeftLeaves(TreeNode root) {
		helper404(root, root);
		return res404;
	}

	public void helper404(TreeNode root, TreeNode pre) {
		if (root == null)
			return;
		if (root.left == null && root.right == null && pre.left == root) {
			res404 += root.val;
		}
		helper404(root.left, root);
		helper404(root.right, root);
	}

	// #437. Path Sum III
	// use two recursion methods
	public int pathSum437(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		int res = 0;
		res += pathSum437(root.left, sum);
		res += pathSum437(root.right, sum);
		res += helper437(root, sum);
		return res;
	}

	public int helper437(TreeNode root, int sum) {

		if (root == null)
			return 0;
		int res = 0;
		if (sum == root.val) {
			res++;
		}
		res += helper437(root.left, sum - root.val);
		res += helper437(root.right, sum - root.val);
		return res;
	}

	// #450. Delete Node in a BST
	public void deleteNode(TreeNode root, TreeNode pre) {
		if (root.left == null && root.right == null) {
			if (pre.val > root.val) {
				pre.left = null;
			} else {
				pre.right = null;
			}
		} else if (root.right == null) {
			if (pre.val > root.val) {
				pre.left = root.left;
			} else {
				pre.right = root.left;
			}
		} else if (root.left == null) {

			if (pre.val > root.val) {
				pre.left = root.right;
			} else {
				pre.right = root.right;
			}
		} else // root have both child
		{
			TreeNode temp = root.right; // it should turn right first
			TreeNode father = root;
			while (temp.left != null)// get the smallest one
			{
				father = temp;
				temp = temp.left;
			}
			root.val = temp.val;
			deleteNode(temp, father);
		}
	}

	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		TreeNode myHead = new TreeNode(Integer.MAX_VALUE);
		myHead.left = root;
		TreeNode p = root;
		TreeNode father = myHead;
		while (p != null) {
			if (p.val > key) {
				father = p;
				p = p.left;
			} else if (p.val < key) {
				father = p;
				p = p.right;
			} else {
				break;
			}
		}
		if (p != null) {
			deleteNode(p, father);
		}
		return myHead.left;
	}

	// #450 easier version of the same concept(from leetcode)
	public TreeNode deleteNode_easy(TreeNode root, int key) {
		if (root == null)
			return null;
		if (key < root.val) {
			root.left = deleteNode_easy(root.left, key);
		} else if (key > root.val) {
			root.right = deleteNode_easy(root.right, key);
		} else {
			if (root.left == null)// one side child
			{
				return root.right;
			} else if (root.right == null)// one side child
			{
				return root.left;
			} else // both side child
			{
				TreeNode node = root.right;
				while (node.left != null) { // find min in right
					node = node.left;
				}
				root.val = node.val;
				root.right = deleteNode_easy(root.right, root.val); // don't forget root.rigth=
			}
		}
		return root;
	}

	// 543. Diameter of Binary Tree //#437 similar
	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int max = helper543(root.left) + helper543(root.right);// no need to -2 cause here return max//different from
																// sum question in #437
		int left = diameterOfBinaryTree(root.left);
		int right = diameterOfBinaryTree(root.right);
		max = Math.max(max, Math.max(left, right));
		return max;
	}

	public int helper543(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = helper543(root.left) + 1;
		int right = helper543(root.right) + 1;
		return Math.max(left, right);

	}

	// #549 Binary Tree Longest Consecutive Sequence II
	int res549 = 1;

	public int[] helper549(TreeNode root, TreeNode pre) {
		if (root == null) {
			return new int[] { 0, 0 };
		}
		int[] left = helper549(root.left, root);// 0 is ins 1 is des
		int[] right = helper549(root.right, root);
		res549 = Math.max(res549, left[0] + right[1] + 1);// connect left and right
		res549 = Math.max(res549, left[1] + right[0] + 1);
		int inc = 0;
		int dec = 0;
		if (root.val - 1 == pre.val) {
			inc = Math.max(left[0], right[0]) + 1;
		} else if (root.val + 1 == pre.val) {
			dec = Math.max(left[1], right[1]) + 1;
		}
		return new int[] { inc, dec };
	}

	// #652. Find Duplicate Subtrees
	Map<String, Integer> paths;
	List<TreeNode> res652;

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		if (root == null) {
			return null;
		}
		paths = new HashMap<String, Integer>();
		res652 = new ArrayList<TreeNode>();
		helper652(root);
		return res652;
	}

	public String helper652(TreeNode root) {
		if (root == null) {
			return "#";
		}
		// pre order
		String path = root.val + "," + helper652(root.left) + "," + helper652(root.right);
		paths.put(path, paths.getOrDefault(path, 0) + 1);
		if (paths.get(path) == 2) {
			res652.add(root);
		}
		return path;
	}

	// #684. Redundant Connection
	public int[] findRedundantConnection(int[][] edges) {
			unionFind uf=new unionFind(2000);   //edges.length will be too small beacuse there will be two node in one array
			for(int i=0;i<edges.length;i++)
			{
				if(uf.find(edges[i][0])==uf.find(edges[i][1]))
				{
					return edges[i];
				}
				uf.union(edges[i][0], edges[i][1]);
			}
			return new int[] {};
	}

class unionFind{
int []parent;
int []size;

public unionFind(int n)
{
	parent=new int[n];
	size=new int[n];
	for(int i=0;i<n;i++)
	{
		parent[i]=i;
		size[i]=1;
	}
}
	public int find(int x) {
		while (parent[x] != x) {
			parent[x] = parent[parent[x]];
			x = parent[x];
		}
		return x;
	}

	public void union(int x, int y) {
		int rootx = find(x);
		int rooty = find(y);
		if (rootx == rooty) {
			return;
		} else {
			if (size[x] > size[y]) {
				parent[rooty]=rootx;
				size[x]+=size[y];
			} else {
				parent[rootx]=rooty;
				size[y]+=size[x];
			}
		}
	}

}

	//// check if there is a loop //totally wrong
	// public boolean helper684(int [][]edges)
	// {
	// Map<int[],Integer>m = new HashMap<int[],Integer>();
	// for(int i=0;i<edges.length;i++)
	// {
	// int []go=new int[] {edges[i][0],edges[i][1]};
	// int []back=new int[] {edges[i][1],edges[i][0]};
	// int judge1=-1;
	// if(m.containsKey(go))
	// {
	// judge1=m.get(go);
	// }
	// else
	// {
	// m.put(go, go[0]);
	// }
	// int judge2=-1;
	// if(judge1==2&&judge2==2)
	// {
	//
	// }
	// }
	//
	// return false;
	// }
//#687. Longest Univalue Path
//#687. Longest Univalue Path
public int longestUnivaluePath(TreeNode root) {
  if(root==null)
  {
  	return 0;
  }
  TreeNode top=new TreeNode(Integer.MAX_VALUE);
  top.left=root;
  int res=helper687(root,root);
  return res687;
}
int res687=0;
public int helper687(TreeNode root,TreeNode pre)
{
	if (root==null)
	{
		return 0;
	}
	int left=helper687(root.left,root);
	int right=helper687(root.right,root);
	int res=Math.max(left,right);
	res687=Math.max(res687, left+right);
	if(root.val==pre.val)
	{
		res++;
	}
	else
	{
		res=0;
	}
	return res;
}
//#685. Redundant Connection II   //code is copied from others
//there are two conditions  
public int[] findRedundantDirectedConnection(int[][] edges) {
    int[] can1 = {-1, -1};
    int[] can2 = {-1, -1};
    int[] parent = new int[edges.length + 1];
    for (int i = 0; i < edges.length; i++) {
        if (parent[edges[i][1]] == 0) {
            parent[edges[i][1]] = edges[i][0];
        } else {//condition one: there is a node with two parent
            can2 = new int[] {edges[i][0], edges[i][1]};
            can1 = new int[] {parent[edges[i][1]], edges[i][1]};
            edges[i][1] = 0;
        }
    }
    for (int i = 0; i < edges.length; i++) {
        parent[i] = i;
    }
    for (int i = 0; i < edges.length; i++) {
        if (edges[i][1] == 0) {
            continue;
        }
        int child = edges[i][1], father = edges[i][0];
        if (root(parent, father) == child) {
            if (can1[0] == -1) {//condition two: there isn't a node with two parent
                return edges[i];
            }
            return can1;
        }
        parent[child] = father;
    }
    return can2;
}

int root(int[] parent, int i) {
    while (i != parent[i]) {
        parent[i] = parent[parent[i]];
        i = parent[i];
    }   
    return i;
}
//#261 graph vaild tree   copy from others
//bool validTree(int n, vector<pair<int, int>>& edges) {
//    if(n != edges.size()+1) return false;
//    vector<int> par(n);
//    for(int i = 0; i < n; i++) par[i] = i;
//    for(auto val: edges)
//    {
//        int par1 = val.first, par2 = val.second;
//        while(par[par1] != par1) par1 = par[par1];
//        while(par[par2] != par2) par2 = par[par2];
//        if(par1 != par2) par[par2] = par1, n--;
//    }
//    return n==1;
//998 insert into maximum tree
public TreeNode insertIntoMaxTree(TreeNode root, int val) {
    if(root==null)
    {
        return new TreeNode(val);
    }
    else if(root.val<val)
    {
        TreeNode add = new TreeNode(val);
        add.left=root;
        return add;
    }
    
        root.right=insertIntoMaxTree(root.right,val);
    return root;
}
//654 create maximum tree
public TreeNode constructMaximumBinaryTree(int[] nums) {
    return helper(nums, 0, nums.length - 1);
}

//max_index denotes the index of the maximum number in range [left, right]
public TreeNode helper(int[] nums, int left, int right){
    if(left>right)return null;
    
    int max_index = left;
    for(int i = left; i <= right; i++){
        if(nums[i] > nums[max_index])max_index = i; 
    }
    
    TreeNode root = new TreeNode(nums[max_index]);
    root.left = helper(nums, left, max_index - 1);
    root.right = helper(nums, max_index + 1, right);
    return root;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);

		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(4);
		TreeNode e = new TreeNode(5);
		TreeNode f = new TreeNode(5);
		TreeNode g = new TreeNode(1);
		a.left = b;
		a.right = c;
		b.right = e;
		b.left = d;
		// b.right = e;
		Tree t = new Tree();
		t.diameterOfBinaryTree(a);

	}

}
