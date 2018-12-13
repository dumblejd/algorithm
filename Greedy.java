package algorithmtest;

import java.util.Stack;

public class Greedy {

	public static int trap4(int[] height){
        int length = height.length;
        int result = 0, current = 0;
        
        Stack<Integer> s = new Stack<Integer>();
        
        while(current < length){
            while(!s.isEmpty() && height[current] > height[s.peek()])
            {
                int top = s.pop();
                if(s.isEmpty())
                {
                    break;
                }
                //获得两个节点之间的宽度
                int distance = current - s.peek() - 1;
                int tempHeight = Math.min(height[current], height[s.peek()]) - height[top];
                result += tempHeight  * distance;
            }
            s.push(current++);
        }
        return result;
    }
	public static boolean recur_regular (String s, String p) //pattern
	{
		if(p.length()==0)
		{
			return s.length()==0;   
		}
		if(p.length()==1||p.charAt(1)!='*')
		{
			if(!s.isEmpty()&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.'))//思考下为什么要加!s.empty()   我个人感觉不加也可以   情况 s=''  p=abc/a*b*  //还是需要的 不然越界
			{
				return recur_regular(s.substring(1),p.substring(1));
			}
		}
		else
		{
			if(recur_regular(s,p.substring(2))) 
			{
				return true;
			}
			if(!s.isEmpty()&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.'))
			{
				return recur_regular(s.substring(1),p);
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] a = {0,1,0,2,1,0,1,3,2,1,2,1};
		String s = "aab";
		String p = "a*bc*";
		System.out.println(recur_regular(s,p));	

}
}
