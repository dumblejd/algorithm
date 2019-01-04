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
//↓ too much space consume
//	public void helper113(TreeNode root, int sum, List<List<Integer>> res, List<Integer> temp) {
//		if (root == null) {
//			return;
//		}
//		if (root.left == null && root.right == null && sum == root.val) {
//			temp.add(root.val);
//			res.add(new ArrayList<Integer>(temp));
//			return;
//		}
//
//		temp.add(root.val);
//		helper113(root.left, sum - root.val, res, new ArrayList<Integer>(temp));
//		helper113(root.right, sum - root.val, res, new ArrayList<Integer>(temp));
//	}
	public void helper113(TreeNode root, int sum, List<List<Integer>> res, List<Integer> temp) {
	if (root == null) {
		return;
	}
	if (root.left == null && root.right == null && sum == root.val) {
		temp.add(root.val);
		res.add(new ArrayList<Integer>(temp));
		temp.remove(temp.size()-1);
		return;
	}

	temp.add(root.val);
	helper113(root.left, sum - root.val, res, temp);
	helper113(root.right, sum - root.val, res, temp);
	temp.remove(temp.size()-1);
}
	//#114 Flatten Binary Tree to Linked List
	 public void flatten(TreeNode root) {
		 pre114=new TreeNode(-1);
		 TreeNode temp=pre114;
	       	helper114(root);
	       	root=temp.right;
	       	
	    }
	 // failed version it can't be done, it needs an outside variable
//	 public TreeNode helper114(TreeNode root,TreeNode pre)
//	 {
//		 if(root==null)
//		 {
//			 return null;
//		 }
//		 pre.right=new TreeNode(root.val);
//		 if(root.left!=null)
//		 {
//			 pre=helper114(root.left,pre.right);
//		 }
//		 if(root.right!=null)
//		 {
//			 pre=helper114(root.right,pre.right);
//		 }
//		 return pre;
//	 }
	 //this version actually work, but since it's void,so the result can't be returned(!!!change root=res is not actually changing outside object)
//	 private TreeNode pre114=null;
//	 public void helper114(TreeNode root)
//	 {
//		 if(root==null)
//		 {
//			 return;
//		 }
//		 pre114.right=new TreeNode(root.val);
//		 pre114=pre114.right;
//		 if(root.left!=null)
//		 {
//			 helper114(root.left);
//		 }
//		 if(root.right!=null)
//		 {
//			 helper114(root.right);
//		 }
//	 }
	 private TreeNode pre114=null;
	 public void helper114(TreeNode root)
	 {
		 if(root==null)
		 {
			 return;
		 }
		 
		 if(pre114!=null)
		 {
			 pre.left=null;
			 pre.right=root;
		 }
		 pre114=root;
		 TreeNode right=root.right;
		 helper114(root.left);
		 helper114(right);
	 }
	 // post order iterator without recursion
	 public void travers_post(TreeNode root)
	 {
		 Stack <TreeNode>s = new Stack<TreeNode>();
		 TreeNode current=root;
		 TreeNode lastoutput=null; 
		 while(current!=null||!s.isEmpty())
		 {
			 while(current!=null)
			 {
				 s.push(current);
				 current=current.left;
			 }
			 current=s.pop(); //it should use pop otherwise current would be null caused by upper while
			 while(current.right==null||current.right==lastoutput)//it has right or it's right has been ouput  
				//here is while not if  otherwise it will repeat add some point
			 {
				 System.out.println(current.val);   //output
				 if(s.isEmpty())
				 {
					 return;
				 }
				 lastoutput=current;
				 current=s.pop();  //re get a new node to continue   
			 }
			 //current nodes from here has right node. so re put them into the stack since it's a post order 
			s.push(current); //push again the current since it has right node to push and process
			current=current.right; //process right node
		 }
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
		Tree t = new Tree();
		t.flatten(b);
		
	}

}
