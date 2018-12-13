package algorithmtest;

public class ReverseInt {
	public int reverseint(int o)
	{
		if(o<0)
		{
			return -1*reverseint(-1*o);
		}

		StringBuffer sb = new StringBuffer(Integer.toString(o));
		
		String temp = sb.reverse().toString();
		int t = Integer.parseInt(temp);
		return t;
	}
	public int reverseint_sec(int o)
	{
		String t = "";
		if(o<0)
		{
			t += "-";
			o *= -1;
		}
		t += Integer.toString(o%10);
		while(o>=10)
		{
			o /= 10;
			t += Integer.toString(o%10);
		}
		return Integer.parseInt(t);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReverseInt a = new ReverseInt();
		 System.out.println(a.reverseint_sec(-102));
	}

}
