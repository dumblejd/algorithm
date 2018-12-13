package algorithmtest;

import java.util.ArrayList;
import java.util.List;

public class Phone_Number {

	public List<String> function(String input) 
	{
		String dict[] = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		List<String> answer = new ArrayList<String>();
		String a = "";
		int index = Integer.valueOf(input.substring(0, 1));
		for(int j = 0;j < dict[index].length();j++)  //根据按键所在位置对应字母数递归
		{
			a = dict[index].substring(j, j+1);
			if(input.length()>1)
			{
				List<String> temp = function(input.substring(1));
				for(String t : temp)
				{
					a += t;
					answer.add(a);
					a = dict[index].substring(j, j+1);  //还原 算不算回溯？
				}
			}
			else
			{
			answer.add(a);
			}
		}
		return answer;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Phone_Number pn = new Phone_Number();
		long startTime = System.currentTimeMillis();//获取当前时间
		List<String> temp = pn.function("12");
		long endTime = System.currentTimeMillis();
		System.out.println(temp.size());
		for(String a : temp)
		{
			System.out.println(a);
		}


//doSomeThing();   //要运行的java程序

System.out.println("程序运行时间："+(endTime-startTime)+"ms");
	}

}
