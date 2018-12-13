package algorithmtest;

import java.util.Arrays;

public class Kmin {

	    public static void main(String[] args) {
	        int[] A = { 8, 33, 17, 51, 57, 49, 35, 11, 25, 37, 14, 3, 2, 13, 52,
	                12, 6, 29, 32, 54, 5, 16, 22, 23, 7 };
	        int K = 18;
	        int theLastKMin = findTheLastKMinNum(A, 0, A.length - 1, K);
	        System.out.println("第" + K + "小的数是：" + theLastKMin);
	    }

	    /**
	     * 
	    * 方法名: findTheLastKMinNum
	    * 方法作用: 分治法求第k小值
	    * 创建人：WENTAO Wan
	    * 创建时间：2016年4月4日 下午11:15:39   
	    * @param @param A
	    * @param @param low
	    * @param @param high
	    * @param @param K
	    * @param @return    
	    * 返回值类型： int    
	    * @throws
	     */
	    public static int findTheLastKMinNum(int[] A, int low, int high, int K) {

	        // 设置阈值
	        int p = high - low + 1;
	        if (p < 6) {

	            // 这里要注意新分配的空间 q+1造成干扰，不能直接Sort(A)
	            Arrays.sort(A, low, p);
	            return A[K - 1];
	        } else {

	            // 分为五段
	            int q = p / 5;
	            int remainder = p - q * 5;

	            // 每段排序并把中项存入mid
	            int[] mid = new int[q + 1];
	            for (int i = 0; i < q; i++) {
	                Arrays.sort(A, 5 * i, (i + 1) * 5);
	                mid[i] = A[i * 5 + 2]; // low
	            }

	            // 除不尽5之后的分为一段并找出中项存入mid
	            if (remainder > 0) {
	                Arrays.sort(A, 5 * q, 5 * q + remainder);
	                mid[q] = A[q * 5 + (remainder + 1) / 2 - 1];
	            }

	            // 中项集合的中项
	            int mm = findTheLastKMinNum(mid, 0, q - 1, (q + 1) / 2);

	            int[] A1 = new int[p];
	            int[] A2 = new int[p];
	            int[] A3 = new int[p];
	            int lenA1 = 0, lenA2 = 0, lenA3 = 0;

	            // 分别与中项比较，分为新的三段
	            for (int i = low; i <= high; i++) {
	                if (A[i] < mm) {
	                    A1[lenA1++] = A[i];
	                } else if (A[i] == mm) {
	                    A2[lenA2++] = A[i];
	                } else if (A[i] > mm) {
	                    A3[lenA3++] = A[i];
	                }
	            }

	            // 将三段的长度与K比较判断K的位置并递归
	            int theLastKMin = 0;
	            if (lenA1 >= K) {
	                theLastKMin = findTheLastKMinNum(A1, 0, lenA1 - 1, K);
	            } else if (lenA2 + lenA1 < K) {
	                theLastKMin = findTheLastKMinNum(A3, 0, lenA3 - 1, K - lenA1
	                        - lenA2);
	            } else if (lenA1 + lenA2 >= K) {
	                theLastKMin = mm;
	            }

	            return theLastKMin;
	        }
	}

}
