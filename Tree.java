package algorithmtest;

public class Tree {
	// #96
	public int numTrees(int n) {
		int[] num = new int[n + 1];
		num[0]=1;
		for(int i=1;i<n+1;i++)
		{
			for(int j=0;j<i;j++)
			{
				num[i]+=num[j]*num[i-j-1];
			}
		}
		return num[n];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
