package algorithmtest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
		if(preStart>preorder.length-1||inStart>inEnd) //since for pre it just take one number, for in, it pick sub array(it can be at first or middle or end)
		{
			return null;
		}
		TreeNode root = new TreeNode(preorder[preStart]);//take the root one 
		int inIndex=0;
		for (int i = inStart; i <= inEnd; i++) {
	        if (inorder[i] == root.val) {
	            inIndex = i;
	        }
	    }
		root.left=helper105(preStart+1, inStart, inIndex-1, preorder, inorder);
		root.right=helper105(preStart+inIndex-inStart+1, inIndex+1,inEnd , preorder, inorder);
		return root;
	}
//#106. Construct Binary Tree from Inorder and Postorder Traversal 
	public TreeNode buildTree106(int[] inorder, int[] postorder) {
		return helper106(postorder.length-1, 0, inorder.length - 1, inorder, postorder);
	}

	public TreeNode helper106(int preStart, int inStart, int inEnd,  int[] inorder,int[] postorder) {
		if(preStart<0||inStart>inEnd) //except note in 105, here prestart should be judged by 0 since it's get from the last one to head
		{
			return null;
		}
		TreeNode root = new TreeNode(postorder[preStart]);//take the root one 
		int inIndex=0;
		for (int i = inStart; i <= inEnd; i++) {
	        if (inorder[i] == root.val) {
	            inIndex = i;
	        }
	    }
		root.left=helper106(preStart-inEnd+inStart, inStart,inIndex-1, inorder,postorder);
		root.right=helper106(preStart-1, inIndex+1,inEnd,inorder,postorder);
		return root;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode a = new TreeNode(5);
		TreeNode b = new TreeNode(10);

		TreeNode c = new TreeNode(15);
		TreeNode d = new TreeNode(8);
		c.left = d;
		b.left = a;
		b.right = c;
		isValidBST(b);

	}

}
