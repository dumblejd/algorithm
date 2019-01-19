package algorithmtest;

import java.util.LinkedList;

public class Solution {
	   public int longestIncreasingPath(int[][] matrix) {
			if(matrix.length==0)
			{
				return 0;
			}
			for(int i=0;i<matrix.length;i++)
			{
				for (int j = 0; j < matrix[i].length; j++) {
					LinkedList<Integer>temp=new LinkedList<Integer>();
					helper329(matrix, i, j, temp,true);
				}
			}
			return res329;
		    }
		int res329=1;
		public void helper329(int[][] matrix, int i,int j,LinkedList<Integer>temp,boolean first)
		{
			 if(i<0||j<0||i>=matrix.length||j>=matrix[i].length||(!first&&temp.getLast()>=matrix[i][j]))
			{
				return;
			}
			if(first||temp.getLast()<matrix[i][j]){
				temp.add(matrix[i][j]);
				res329=Math.max(res329, temp.size());
				helper329(matrix, i, j+1,temp,false);
				helper329(matrix, i, j-1, temp,false);
				helper329(matrix, i+1, j,temp,false);
				helper329(matrix, i-1, j, temp,false);
				temp.removeLast();
			}
			
		}

		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s =new Solution();
		int [][]data={{6,8},{7,2}};
		s.longestIncreasingPath(data);
	}

}
