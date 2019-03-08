package algorithmtest;

import java.util.PriorityQueue;

public class Heap {
	public static int findKthLargest(int[] nums,int k)
	{
		PriorityQueue<Integer> q=new PriorityQueue<Integer>(k);
		for(int i:nums)
		{
			q.offer(i);
			if(q.size()>k)
			{
				q.poll();
			}
		}
		return q.peek();
	}
	
	 class Record {
	      public int id, score;
	      public Record(int id, int score){
	        this.id = id;
	         this.score = score;
	     }
	 }
	 
	public static int findKthMin(int[] nums,int k)
	{
		PriorityQueue<Integer> q=new PriorityQueue<Integer>((a,b)->b-a);
		for(int i=0;i<nums.length;i++)
		{
			q.offer(nums[i]);
			if(q.size()>k)
			{
				q.poll();
			}
		}
		return q.poll();
	}

public static void main(String[] args) {
	int []num=new int[] {1,2,10,4,5,3};
	findKthMin(num,2);
	String a ="123";
	//a.substring(beginIndex)
}
}