package algorithmtest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sort {
	
	public static void sort_mergeinterval(int[][]s)
	{
		for(int i=0; i<s.length;i++)
		{
			for(int j=i;j<s.length;j++)
			{
				if(s[i][0]>s[j][0])
				{
					int []temp = s[i].clone();
					s[i]=s[j].clone();
					s[j]=s[i].clone();
				}
			}
		}
		int temp_max=s[0][1];
		int temp_min=s[0][0];
		List<int[]> result=new ArrayList<int[]>();
		for(int i=1;i<s.length;i++) 
		{
			
			if(s[i][0]<temp_max)
			{
				temp_max=Math.max(s[i][1], s[i-1][1]);
			}
			else
			{
				int [] r = {temp_min,temp_max};
				result.add(r.clone());
				temp_min=s[i][0];
				temp_max=s[i][1];
			}
			if(i==s.length-1)
			{
				int []r = {temp_min,temp_max};
				result.add(r.clone());
			}
		}
		for(int[]i:result)
		{
			System.out.println(i[0]+","+i[1]+"  ");
		}
	}
	public static void sort_insertinterval(int[][]s,int[]in)
	{
		int temp_min=s[0][0];
		int temp_max=s[0][1];
		List<int[]> result=new ArrayList<int[]>();

		for(int i=0;i<s.length;i++)
		{
			if(in[0]<s[i][1]&&in[0]>s[i][0])
			{
				temp_min=s[i][0];
				temp_max=Math.max(in[1], s[i][1]);
			}
			else if(s[i][0]<temp_max&&i!=0)
			{
				temp_max=Math.max(temp_max, s[i][1]);
			}
			else
			{
				int [] r = {temp_min,temp_max};
				result.add(r.clone());
				temp_min=s[i][0];
				temp_max=s[i][1];
			}
			if(i==s.length-1)
			{
				int []r = {temp_min,temp_max};
				result.add(r.clone());
			}
		}
		for(int[]i:result)
		{
			System.out.println(i[0]+","+i[1]+"  ");
		}
	}
	public static void sort_countsort(int []s,int max)
	{
		int []count =  new int[max+1];
		for(int i=0;i<s.length;i++)
		{
			count[s[i]]++;
		}
		for(int i=1;i<count.length;i++)
		{
			count[i]+=count[i-1];
		}
		
		int []result = new int[s.length];
		for(int i=0;i<s.length;i++)
		{
			result[count[s[i]]-1]=s[i];  //千万要注意  index的范围     因为我是看了YouTube的视频，上面是从1开始的 就容易混淆
			count[s[i]]--;
		}
		
		for(int i:result)
		{
			System.out.println(i+" ");
		}
	}

public static void sortColors(int[] A) {
    if(A==null || A.length==0)
        return;
    int idx0 = 0;
    int idx1 = 0;
    for(int i=0;i<A.length;i++)
    {
        if(A[i]==0)
        {
            A[i] = 2;
            A[idx1++] = 1;
            A[idx0++] = 0;
        }
        else if(A[i]==1)
        {
            A[i] = 2;
            A[idx1++] = 1;
        }
    }
}
public static void insertionsort(int [] s)  //需要注意的地方是 index--  不然换了位置以后  index没变  就还在操作没有意义的数
{
	for(int i=0;i<s.length;i++)
	{
		int index=i;
		for(int j=i;j>=0;j--)
		{
			if(s[index]<s[j])
			{
				int temp=s[index];
				s[index]=s[j];
				s[j]=temp;
				index--;  //注意！！！
			}
		}
	}
	for(int i:s)
	{
		System.out.println(i+" ");
	}
}
public static void insertionsort2(int [] s)//这个是往后挪动的版本
{
	for(int i=1;i<s.length;i++)
	{
		int value=s[i];
		for(int j=i;j>0;j--)
		{
			if(value<s[j-1])
			{
				s[j]=s[j-1];
				continue;
			}
			if(value>=s[j-1])
			{
				s[j]=value;
				break;
			}
		}
	}
	for(int i:s)
	{
		System.out.println(i+" ");
	}
}
 public static class ListNode {
	      int val;
	      ListNode next;
	     ListNode(int x) { val = x; }
	  }
 public static void sort_insertionlist(ListNode h)
 {
	 ListNode head = new ListNode(0); //新加入一个头
	 head.next=h;
	 while(h!=null)
	 {
		// ListNode end = h;
		 ListNode t = head;
		 while(t!=h)
		 {
			 if(t.next.val>h.val)
			 {
				 if(h.next==null)
				 {
					 ListNode end = t.next;
					 t.next=h;
					 h.next=end;
					 end.next=null;
					 h=end;
				 }
				 else
				 {
				 ListNode third = t.next;
				 ListNode fourth = h.next;
				 t.next=h;
				 h.next=third;
				 third.next=fourth;
				 h=third;
				 }
				 break;
			 }
			 t=t.next;
		 }
		 h=h.next;
	 }
	 h=head.next;
 }
 public static void printlist(ListNode a)
 {
	 while(a!=null)
	 {
		 System.out.println(a.val);
		 a=a.next;
	 }
 }
 public static ListNode insertionSortList(ListNode head) {

//处理特殊情况
       if(head == null) {
           return head;
       }
       

//定义helper节点，在下面它会被插到链表的开头，作为链表新的开头，可以方便的使用
       ListNode helper = new ListNode(0);

//1.因为是单向的链表，所以插入项的前一项要被记录2.用它来遍历已经排序的项依次与插入项比较，得到插入位置
       ListNode pre = helper;

//定义插入节点，开始时为head
       ListNode ins = head;

//定义一个next节点来在循环中保存插入项的下一个节点
       ListNode next = null;
       while(ins != null) {
           next = ins.next; 
           

  //此循环第一次循环时把helper加到链表前面，后续的循环，把每个插入项插到正确的位置
           while(pre.next != null && pre.next.val < ins.val) {
               pre = pre.next;
           }
           
           ins.next = pre.next;
           pre.next = ins;
           pre = helper;
           ins = next;
       }
       return helper.next;
   }
 public static void sort_bucket(int []s)
 {

	 int max = Integer.MIN_VALUE;
	 int min = Integer.MAX_VALUE;
	 for(int i:s)
	 {
		  max = Math.max(i, max);
		  min = Math.min(i, min);
	 }
	 int bucketnum = (max-min)/s.length+1; //不然可能会0个
	 
	 List<List<Integer>> bucket = new ArrayList<List<Integer>>();
	 
	 for(int i=0;i<bucketnum;i++)
	 {
		 bucket.add(new ArrayList<Integer>());
	 }
	 
	 for(int i:s)
	 {
		 int index = (i-min)/s.length;
		 if(index<0) index++;
		 bucket.get(index).add(i);
	 }
	 
	 for(List<Integer> l:bucket)
	 {
		 Collections.sort(l);
	 }
	 int temp = 0;
	 for(List<Integer> l:bucket)
	 {
		 for(int i:l)
		 {
			 s[temp++]=i;
		 }
	 }
	 for(int i:s)
	 {
		 System.out.println(i);
	 }
 }
 public static void test(String a)
 {
	 a+="213";
 }
 public static void sort_dfs_maximum(int []s,String num,Map m,List<String> result)
 {
	 if(m.size()==s.length)
	 {
		 if(result.get(0).length()<num.length())
		 {
			 result.remove(0);
			 String zero="";
			 while(zero.length()<num.length())
			 {
				 zero+="0";
			 }
			 result.add(zero);
		 }
		 int bigger=-1;
		 int less = -1;
		 for(int i=0;i<num.length();i++)
		 {
			 if(Integer.valueOf(num.charAt(i))<result.get(0).charAt(i))
			 {
				 less=less==-1?i:Math.min(i, less);
			 }
			 if(Integer.valueOf(num.charAt(i))>result.get(0).charAt(i))
			 {
				 bigger=bigger==-1?i:Math.min(i, bigger);
			 }
		 }
		 if(bigger<less&&bigger!=-1||(bigger!=-1&&less==-1))
		 {
			 result.remove(0);
			 result.add(num);
			 System.out.println(result.get(0));
		 }
		 return;
	 }
	 for(int i=0;i<s.length;i++)
	 {
		 if(m.get(i)==null)
		 {
			 m.put(i, 1);
			 sort_dfs_maximum(s,num+s[i],m,result);
			 m.remove(i);
		 }
	 }
 }
 public static String largestNumber(int[] num) {
		if(num == null || num.length == 0) return "";
		String[] s_num = new String[num.length];
		for(int i = 0; i < num.length; i++)
			s_num[i] = String.valueOf(num[i]);
		Comparator<String> comp = new Comparator<String>(){
			public int compare(String str1, String str2){
				String s1 = str1 + str2;
				String s2 = str2 + str1;
				return s2.compareTo(s1);
			}
		};
		Arrays.sort(s_num, comp);
		if(s_num[0].charAt(0) == '0') return "0";
		StringBuilder sb = new StringBuilder();
		for(String s: s_num)
			sb.append(s);
		return sb.toString();
	}
 	public static void sort_hindex(int [] a)
 	{
 		Map m = new HashMap<Integer,Integer>();
 		Arrays.sort(a);
 		int hindex=a[1];
 		for(int i = 1;i<a.length;i++)
 		{
 			if(a[i]==a.length-i)
 			{
 				hindex=i;
 			}
 		}
 		System.out.println(hindex+1);
 	}
 	public static void swap(int[] a ,int x,int y)
 	{
 		int temp = a[x];
 		a[x]=a[y];
 		a[y]=temp;
 	}
 	public static void sort_wiggle(int[] a)
 	{
 		for(int i=0;i<a.length-1;i++)
 		{
 			if(i%2==0&&a[i]>a[i+1])//是奇数
 			{
 				swap(a,i,i+1);
 				
 			}
 			if(i%2!=0&&a[i]<a[i+1])//偶数
 			{
 				swap(a,i,i+1);
 			}
 		}
 	}
 	public static void sort_wiggle_2(int[]a)
 	{
 		int []result = new int[a.length];
 		Arrays.sort(a);
 		int mid=(a.length&1)==0?(a.length-1)/2:a.length/2;
 		int l=mid; 
 		int h=result.length-1;
 		for(int i=0;i<result.length;i++)
 		{
 			result[i]=(i&1)==0?a[l--]:a[h--];
 		}
 		printarr(result);
 	}
	public static void printarr(int a[])
	{
		for(int i:a)
		{
			System.out.print(i+" ");
		}
	}
	public static void findmaxmatch(String s,String sub)
	{
		int max=0;
		for(int i=0;i<s.length();i++)
		{
			int count=0;
			int tempi=i;
			for(int j=0;j<sub.length()&&tempi<s.length();j++,tempi++)
			{
				if(s.charAt(tempi)==sub.charAt(j))
				{
					count++;
				}
				else 
				{
					break;
				}
			}
			max=Math.max(count, max);
		}
		System.out.println(max);
	}
	public static void sort_intersection(int[]a,int[]b)
	{
		Map m = new HashMap<Integer,Integer>();
		Map<Integer,Integer> result = new HashMap<Integer,Integer>();
		for(int i=0;i<a.length;i++)
		{
			m.put(a[i], 1);
		}
		for(int i=0;i<b.length;i++)
		{
			if(m.get(b[i])!=null)
			{
				result.put(b[i], 1);
			}
		}
		for(int key:result.keySet())
		{
			System.out.print(key+" ");
		}
	}
	public static void sort_frequency(String s)
	{
		Map<Character,Integer> m = new HashMap<Character,Integer>();
		for(char c:s.toCharArray())
		{
			if(m.containsKey(c))
			{
				int temp=(int) m.get(c);
				m.put(c, ++temp);
			}
			else
			{
				m.put(c, 1);
			}
		}
		char[] result = new char[s.length()];
		
		int index=0;
		for(char c:m.keySet())
		{
			result[index]=c;
			int innerindex=index;
			for(int i=index;i>0;i--)
			{
				if(m.get(result[innerindex])<m.get(result[i]))
				{
					char temp = result[innerindex];
					result[innerindex]=result[i];
					result[i]=temp;
					innerindex=i;
				}
			}
			index++;
		}
		StringBuffer re=new StringBuffer(String.valueOf(result));
		re.reverse();
		String r = String.valueOf(re);
		System.out.println(r);
	}
	public static void sort_longestword(String s,String[]sub)
	{
		int maxsize=0;
		List<String> answers=new ArrayList<String>();
		for(int i=0;i<sub.length;i++)
		{
			int j=0;
			int k=0;
			while(j<sub[i].length()&&k<s.length())
			{
				if(s.charAt(k)==sub[i].charAt(j))
				{
					j++;
				}
				k++;
				if(j>=sub[i].length())
				{
					answers.add(sub[i]);
				}
			}
		}
		Collections.sort(answers,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub 
				if(o1.length()>o2.length())
				{
					return -1;
				}
				if(o1.length()<o2.length())
				{
					return 1;
				}
				return o1.compareTo(o2);
				
			}});
		//Collections.sort(answers);
		System.out.println(answers.get(0));
	}
 	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][]temp= {{1,3},{2,6},{8,10},{15,18}};
		int [][]temp2= {{1,2},{3,5},{6,7},{8,10},{12,16}};
		//sort_mergeinterval(temp);
		int []in= {4,9};
		int []input= {1,4,1,2,7,5,2,0};
		int [] input3 = {3,0,6,1,5};
		int []input2= {1,4,1,2,7,5,2,30,100};
		int []a= {0,1,2,0,0,1,1,2,2};
		//sort_insertinterval(temp2, in);
		//sortColors(a);
		//sort_countsort(input, 7);
		ListNode a1=new ListNode(1);
		ListNode a2=new ListNode(4);
		ListNode a3=new ListNode(1);
		ListNode a4=new ListNode(7);
		ListNode a5=new ListNode(5);
		a1.next=a2;a2.next=a3;a3.next=a4;a4.next=a5;
		int []maximum= {67,4,2,1,77,12314324};
		//insertionsort2(input);
		//sort_insertionlist(a1);
		//printlist(insertionSortList(a1));
		//sort_bucket(input2);
		
		Map m = new HashMap<Integer,Integer>();
		List<String> result = new ArrayList();
		result.add("0");
		//sort_dfs_maximum(maximum, "", m, result);
		//largestNumber(maximum);
		//System.out.println("330".compareTo("303"));
		//sort_hindex(input3);
		//printlist(a1);
		//sort_wiggle_2(input3);
		//findmaxmatch("abbcabcab", "bcad");
		//sort_intersection(input, input3);
		//sort_frequency("bbcccAa");
		String[]st={"ale","apple","monkey","plea"};
		String[]st2={"a","b","c","p"};
		sort_longestword("abpcplea", st2);
	}

}
