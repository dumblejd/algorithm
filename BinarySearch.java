package algorithmtest;

public class BinarySearch {
	public static int basicBinary(int[] s, int num)

	{
		int start = 0;
		int end = s.length - 1;
		int mid = 0;
		while (start <= end) {
			mid = start + (end - start) / 2;
			if (num < s[mid]) {
				end = mid - 1;
			}
			if (num > s[mid]) {
				start = mid + 1;
			}
			if (num == s[mid]) {
				return mid;
			}
		}
		return -1;
	}

	public static int basicBinary(int[][] s, int num) {
		int row = s.length;
		int col = s[0].length;
		int start = 0;
		int end = col * row - 1;
		int mid = 0;
		while (start < end) {
			mid = start + (end - start) / 2;
			if (num == s[mid / col][mid % col]) {
				return mid;
			}
			if (num < s[mid / col][mid % col]) {
				end = mid;
			}
			if (num > s[mid / col][mid % col]) {
				start = mid;
			}
		}
		return -1;
	}

	public static int insertBinary(int[] s, int num) {
		int start = 0;
		int end = s.length - 1;
		int mid = 0;
		while (start <= end) {
			mid = start + (end - start) / 2;
			if (num < s[mid]) {
				end = mid - 1;
			}
			if (num > s[mid]) {
				start = mid + 1;
			}
			if (num == s[mid]) {
				return mid;
			}
		}
		return start;
	}

	public static int[] rangeBinary(int[] s, int num) {
		int start = -1;
		int end = -1;

		start = insertBinary(s, num);
		if (s[start] == num) {
			while (start > 0 && s[start - 1] == num) {
				start--;
			}
			end = insertBinary(s, num + 1);
			end--;
		} else {
			start = -1;
			end = -1;
		}
		int[] result = { start, end };
		return result;
	}

	public static int rotateBinary(int[] s, int num) {
		int start = 0;
		int end = s.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (s[mid] == num) {
				return mid;
			}
			if (s[mid] > s[start]) {
				if (num >= s[start] && num <= s[mid]) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			} else {
				if (num <= s[end] && num >= s[mid]) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}
		}
		return -1;
	}

	// =====#4
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		return 0;

	}

	public static int findkth(int a[],int starta,int b[],int startb,int findk)
	{
		//end condition
		if(starta>=a.length)  //if a is ended and  smaller 
		{
			return b[startb+findk-1];
		}
		if(startb>=b.length)// if b is ended and smaller 
		{
			return a[starta+findk-1];
		}
		if(findk==1)
		{
			return Math.min(a[starta], b[startb]);
		}
		//check if out of range
		int avalue_halfk= starta+findk/2-1<a.length?a[starta+findk/2-1]:Integer.MAX_VALUE;
		int bvalue_halfk= startb+findk/2-1<b.length?b[startb+findk/2-1]:Integer.MAX_VALUE; 
		//compare the half kth value of both to delete half of k each time
		if(avalue_halfk<bvalue_halfk)
		{
			return findkth(a,starta+findk/2,b,startb,findk-findk/2);
		}
		else
		{
			return findkth(a,starta,b,startb+findk/2,findk-findk/2);
		}
	}

	public static void main(String[] args) {
		int[] s = { 1, 2, 3, 4, 5, 6, 7 };
		int[] s2 = { 1, 3, 5, 6 };
		int[][] ss = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
				{ 21, 22, 23, 24, 25 }, { 26, 27, 28, 29, 30 } };
		int[] ranges = { 5, 6, 7, 8, 0, 1, 2, 3 };
		System.out.println(rotateBinary(ranges, 0));
	}
}
