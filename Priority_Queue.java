package algorithmtest;

import java.util.PriorityQueue;

public class Priority_Queue {
//kth largest
	 public int findKthLargest(int[] nums, int k) {
	        PriorityQueue<Integer>pq = new PriorityQueue<Integer>();
	        for(int i:nums)
	        {
	            pq.offer(i);
	            if(pq.size()>k)
	            {
	                pq.poll();
	            }
	        }
	        return pq.poll();
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
