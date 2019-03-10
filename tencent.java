package algorithmtest;

import java.util.Arrays;
import java.util.Scanner;

public class tencent {

	static int total=0;
    static int L=0;
    public static void check(int[]seq,int L)
    {
        int num=1;
        int temp=seq[0];
        for(int i=1;i<seq.length;i++)
        {
            if(seq[i]-temp>=0)
            {
                num++;
               temp=seq[i];
            }
           
        }
        if(num==L)
            {
             total++;
         	System.out.println(Arrays.toString(seq));
            }
    }
    public static void permute(int[]array,int start)
    {
        if(start==array.length)
        {
        	//total++;
            check(array,L);
        }
        else{
            for(int i=start;i<array.length;++i)
            {
                swap(array,start,i);
                permute(array,start+1);
                swap(array,start,i);
            }
        }
    }
    public static void swap(int[]array,int s,int i)
    {
        int t=array[s];
        array[s]=array[i];
        array[i]=t;
    }
    public static void main(String[] args) {
        total=0;
    	while (true) {
    		Scanner sc = new Scanner(System.in);
            int size=sc.nextInt();
            int num=sc.nextInt();
            L=num;
            int []input=new int[size];
            for(int i=0;i<size;i++)
            {
                input[i]=sc.nextInt();
            }
            permute(input,0);
             System.out.println(total);  
    	}
        
    }
}
