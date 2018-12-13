package algorithmtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Amazon {

	public static void highfeqword(String s,String[]ex)
	{
		char []temp=s.toLowerCase().toCharArray();
		
		for(int i=0;i<temp.length;i++)
		{
			if(!(temp[i]>='a'&&temp[i]<='z'||temp[i]>='A'&&temp[i]<='Z'))
			{
				temp[i]=' ';
			}
		}
		String[]as=new String(temp).split(" ");;
		Map<String,Integer> m = new HashMap<String,Integer>();
		for(int i=0;i<ex.length;i++)
		{
			
			m.put(ex[i].toLowerCase(), -1);
		}
		int max=-1;
		for(int i=0;i<as.length;i++)
		{
			if(m.containsKey(as[i])&&m.get(as[i])!=-1)
			{
				m.put(as[i], m.get(as[i])+1);
				max=Math.max(max, m.get(as[i]));
			}
			else if(m.containsKey(as[i])&&m.get(as[i])==-1)
			{
			}
			else if(as[i]!="")
			{
				m.put(as[i], 1);
				max=Math.max(max, m.get(as[i]));
			}
		}
		
		ArrayList<String> result=new ArrayList<String>();
		for(String ts:m.keySet())
		{
			if(m.get(ts)==max)
			{
				result.add(ts);
			}
		}
		Array.print(result);
	}
public void reorderfile(String []file)
{
	List<String>number=new ArrayList<String>();
	for(int i=0;i<file.length;i++)
	{
		String[]temp=file[i].split(" ");
		if(temp[1].charAt(0)>='0'&&temp[1].charAt(0)<='9')
		{
			number.add(new String(file[i]));
		}
	}
	class sort
	{
		boolean compare(String a,String b)
		{
			String keya=a.substring(0, a.indexOf(" "));
			String valuea=a.substring(a.indexOf(" ")+1,a.length());
			String keyb=b.substring(0, a.indexOf(" "));
			String valueb=b.substring(a.indexOf(" ")+1,a.length());
			List<String> key=new ArrayList<String>();
			List<String> value=new ArrayList<String>();
			key.add(keya);
			key.add(keyb);
			
			if(valuea.equals(valueb))
			{
				return ;
			}
			return false;
			
		}
	}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";
				String []exclude = {"and", "he", "the", "to", "is", "Jack", "Jill"};
				highfeqword(text, exclude);

				Map<String, Integer> map = new HashMap<String, Integer>();
		        map.put(null, 0);
		        map.put("java", 1);
		        map.put("c++", 2);
		        map.put("python", 3);
		        map.put("php", 4);
		        map.put("nodejs", 5);
		        for(Entry<String, Integer> entry : map.entrySet()) {
		            System.out.println(entry.getKey() + ": " + entry.getValue());
		        }
		        System.out.println("php".hashCode() == "c++".hashCode());

		
	}

}





