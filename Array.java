package algorithmtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Array {
	public static void print(List<List<Integer>> result)

	{
		for(int i=0;i<result.size();i++)
		{
			for(int j = 0;j<result.get(i).size();j++)
			{
				System.out.print(result.get(i).get(j)+",");
			}
			System.out.println();
		}
	}
	public static void print(ArrayList<String> result)

	{
		for(int i=0;i<result.size();i++)
		{
			
				System.out.println(result.get(i)+",");
			
		}
	}
	public static void dfs_three_sum(int[]a,List<List<Integer>> result,int left,List<Integer> now,int sumnumber,int index)
	{
		
		if(now.size()>sumnumber||index>=a.length)
		{
			return;
		}
		else if(left==0&&now.size()==sumnumber)
		{
			result.add(new ArrayList<Integer>(now));
		}
		else
		{
			for(int i=index;i<a.length;i++)
			{
					now.add(a[i]);
					dfs_three_sum(a,result,left-a[i],now,sumnumber,i+1);
					now.remove(now.size()-1);
			}
		}
	}
	public static void printint(List<int[]>result) 
	{
		for(int i=0;i<result.size();i++)
		{
			for(int j = 0;j<result.get(i).length;j++)
			{
				System.out.print(result.get(i)[j]);
			}
			System.out.println();
		}
	}
	public static void three_sum(int a[],int target)
	{
		
		Arrays.sort(a);
		List<int[]> result = new ArrayList<int[]>();
		for(int i=0;i<a.length;i++)
		{
			int start=i+1;
			int end = a.length-1;
			while(start<end)
			{
				if(a[i]+a[start]+a[end]==0)
				{
					int[]t = {a[i],a[start],a[end]};
					result.add(t);
					end--;
					start--;
				}
				if(a[i]+a[start]+a[end]>0)
				{
					end--;
				}
				if(a[i]+a[start]+a[end]<0)
				{
					start++;
				}
			}
		}
		printint(result);
	}
//	public static void dfs_sum(int s[],List<List<Integer>> result,List<Integer> temp,int target,int index)
//	{
//		if (target==0)    //这里是个缺陷  如果目标本身就是0  就是错的
//		{
//			result.add(new ArrayList<Integer>(temp));
//			return;
//		}
//		for(int i=index;i<s.length;i++)
//		{
//			if(s[i]>target)
//			{
//				break;
//			}
//			else
//			{
//				temp.add(s[i]);
//				dfs_sum(s,result,temp,target-s[i],i+1);
//				temp.remove(temp.size()-1);
//	            while(i<s.length-1&&s[i]==s[i+1]) i++;			
//	        }
//			
//		}
//	}
	//默认是2sum问题
    private int kValue = 2;
    // 注入k值，动态实现K值问题
    public void setK(int k) {
        kValue = k < 2 ? 2 : k;
    }
    /**
     * 外部调用方法，公有权限
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums,int target) {
        return kSum(nums, kValue, target);
    }
    /**
     * 做一些判断、排序之类的的事情
     * @param nums
     * @param k
     * @param target
     * @return
     */
    private List<List<Integer>> kSum(int[] nums, int k, int target) {
        //返回结果数据集合，这里这样写是因为Leetcode接口要求
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        // 结果集里面的每个结果元素
        ArrayList<Integer> tempList = null;
        // 判断
        if (nums.length < k) {
            return resultList;
        } else if (nums.length == k) {
            int sumValue = 0;
            tempList = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; ++i) {
                sumValue += nums[i];
                tempList.add(nums[i]);
            }
            if (sumValue == target) {
                resultList.add(tempList);
            }
            return resultList;
 
        }
        // 数组排序
        Arrays.sort(nums);
        recusionSolution(nums, k, target, 0, tempList, resultList);
        return resultList;
    }
    /**
     * 主方法
     * @param nums 数组
     * @param k    几个值的和
     * @param sum  目标值
     * @param index 索引开始值
     * @param tempList 盛放元素结果集
     * @param resultList  最终结果集合
     */
    private void recusionSolution(int[] nums, int k, int sum, int index,
            ArrayList<Integer> tempList, List<List<Integer>> resultList) {
        // 判断是不是2sum了，做一些指针的相应移动
        if (k == 2) {
            int first = index;
            int tail = nums.length - 1;
            int first_last_value = 0;
            int tail_last_value = 0;
            boolean isMatch = false;
            while (first < tail) {
                if (nums[first] + nums[tail] == sum) 
                {
 
                    if (isMatch && first_last_value == nums[first]) 
                    {
                        first++;
                        continue;
                    } else if (isMatch && tail_last_value == nums[tail]) 
                    {
                        tail--;
                        continue;
                    } else if (isMatch && tail_last_value == nums[tail]
                            && first_last_value == nums[first]) 
                    {
                        first++;
                        tail--;
                        continue;
                    }
 
                    ArrayList<Integer> tempList_copy = (ArrayList<Integer>) tempList.clone();
                    tempList.add(nums[first]);
                    tempList.add(nums[tail]);
                    resultList.add(tempList);
                    tempList = tempList_copy;
                    isMatch = true;
                    first_last_value = nums[first];
                    tail_last_value = nums[tail];
                    first++;
                    tail--;
 
                } else if (nums[first] + nums[tail] > sum) {
                    tail--;
                } else if (nums[first] + nums[tail] < sum) {
                    first++;
                }
            }
        } else {
            //递归
            for (int i = index; i < nums.length - k; ++i) {
                if (k == kValue) {
                    tempList = new ArrayList<Integer>();
                }
                if (i > 0 && nums[i - 1] == nums[i]) {
                    continue;
                }
                tempList.add(nums[i]);
                recusionSolution(nums, k - 1, sum - nums[i], i + 1, tempList,
                        resultList);
 
            }
        }
        return;
    }

   public static void nextPermutation(int[] a)
   {
	   int i = a.length-1;
	   int index=-1;
	   while(i>0)
	   {
		   if(a[i]>a[i-1])
		   {
			   	index=i;
			   	break;
		   }
		   i--;
	   }
	   if(index==a.length-1)
	   {
		   int temp=a[index];
		   a[index]=a[index-1];
		   a[index-1]=temp;
	   }
	   else if(i==0)
	   {
		   Arrays.sort(a);
	   }
	   else
	   {
		   int bigger=0;
		   i=a.length-1;
		   while(a[i]<a[index-1]&&i>0)
		   {
			   i--;
		   }
		   int temp=a[i];
		   a[i]=a[index-1];
		   a[index-1]=temp;
		   Arrays.sort(a,index,a.length);
	   }
   }
   public static void print(int []a)
   {
	   for(int k:a)
	   {
		   System.out.print(k+" ");
	   }
   }
//	1 2 3   1 4 7  7 4 1
//	4 5 6   2 5 8  8 5 2
//	7 8 9	3 8 9  9 8 3
   public static int insert_try(int a[],int target)
   {
	   int start = 0;
	   int end = a.length-1;
	   int mid=-1;
	   while(start<end)
	   {
		   mid = (start+end)/2;
		  if(target==a[mid])
		  {
			  return mid;
		  }
		  else if(target>a[mid])
		  {
			  start=mid+1;
		  }
		  else
		  {
			  end = mid-1;
		  }
	   }
		   return mid;
   }
   public static int[] maxSlidingWindow(int[] nums, int k) {
       if (k<=0) return new int[0];
       TreeMap<Integer, Integer> map = new TreeMap<>();
       for(int i=0; i<k-1; i++) {
           Integer count = map.get(nums[i]);
           if (count == null) count = 1; else count ++;
           map.put(nums[i], count);
       }
       // System.out.println(map);
       int[] max = new int[nums.length-k+1];
       for(int i=0,j=k-1; j<nums.length; i++, j++) {
           Integer count = map.get(nums[j]);
           if (count == null) count = 1; else count ++;
           map.put(nums[j], count);
           max[i] = map.lastKey();
           count = map.get(nums[j-k+1]);
           count --;
           if (count == 0) map.remove(nums[j-k+1]); else map.put(nums[j-k+1], count);
           // System.out.println(map);
       }
       return max;
   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]a= {5,4,7,5,3,2};
		int []b= {1,3,3,-3,2,3,2,1};
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> now = new ArrayList<Integer>();
		//Arrays.sort(a);
		//dfs_three_sum(a, result, 0, now, 3,0);
		//three_sum(a, 0);
		//dfs_sum(a, result, temp, 0, 0);
		//print(result);
		//nextPermutation(a);
		//print(a);
		System.out.println(maxSlidingWindow(b, 3)[2]);
	}

}
