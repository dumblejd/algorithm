package algorithmtest;

public class Google {

	public static void flip(int [][]a)
	{
		int C = a[0].length;
        for (int[] row: a)
            for (int i = 0; i < (C + 1) / 2; ++i) {
                int tmp = row[i] ^ 1;
                row[i] = row[C - 1 - i] ^ 1;
                row[C - 1 - i] = tmp;
            }

        System.out.println(a);
	}
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[][]= {{0,1,1,0,0}};
		flip(a);
	}

}
