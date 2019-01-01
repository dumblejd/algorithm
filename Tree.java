package algorithmtest;

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
		if(root==null)
        {
            return true;
        }
		return helper101(root.left,root.right);
	}
	public boolean helper101(TreeNode left,TreeNode right)
	{
		if(left==null&&right==null)
		{
			return true;
		}
		if(left==null||right==null)
		{
			return false;
		}
		if(left.val!=right.val)
		{
			return false;
		}
		return helper101(left.left,right.right)&&helper101(left.right,right.left);
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
