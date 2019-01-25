package algorithmtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BackTrack {
	//#10. Regular Expression Matching
public boolean isMatch(String s, String p) {
        return helper10(s, p);
    }

	public boolean helper10(String s, String p)   //complicated version
	{
		boolean flag=false;
		if(s.length()==0&&p.length()==0)
		{
			return true;
		}
		if(s.length()==0)
		{
			if(p.length()>=2&&p.charAt(1)=='*')
			{
				return helper10( s,p.substring(2));
			}
			return false;
		}
		if(p.length()==0)
		{
			return false;
		}
		if(2<=p.length()&&p.charAt(1)=='*')
		{
			if(p.charAt(0)==s.charAt(0)||p.charAt(0)=='.')
			{
				flag=flag||helper10(s, p.substring(2, p.length()));
				flag=flag||helper10(s.substring(1), p);
			}
			else
			{
				flag=flag||helper10(s, p.substring(2, p.length()));
			}
		}
		
		else
		{
			if(p.charAt(0)=='.'||p.charAt(0)==s.charAt(0))
			{
				flag=flag||helper10(s.substring(1), p.substring(1));
			}
			else
			{
				return false;
			}

		}
		return flag;
	}
	public boolean helper10_tryToSimplify(String s, String p)   //complicated version
	{
		boolean flag=false;
		if(s.length()==0&&p.length()==0)
		{
			return true;
		}
		if(s.length()==0)
		{
			if(p.length()>=2&&p.charAt(1)=='*')
			{
				return helper10( s,p.substring(2));
			}
			return false;
		}
		if(p.length()==0)
		{
			return false;
		}
		if(2<=p.length()&&p.charAt(1)=='*')
		{
			if(p.charAt(0)==s.charAt(0)||p.charAt(0)=='.')
			{
				flag=flag||helper10(s, p.substring(2, p.length()));
				flag=flag||helper10(s.substring(1), p);
			}
			else
			{
				flag=flag||helper10(s, p.substring(2, p.length()));
			}
		}
		
		else
		{
			if(p.charAt(0)=='.'||p.charAt(0)==s.charAt(0))
			{
				flag=flag||helper10(s.substring(1), p.substring(1));
			}
			else
			{
				return false;
			}

		}
		return flag;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BackTrack bt = new BackTrack();
		char[][] a = new char[][] { { '1', '1', '1', '1', '0' }, { '1','1', '0', '1','0'}, {'1', '1', '0', '0', '0' } };
		bt.helper10("mississippi", "mis*is*p*.", 0, 0);
		System.out.println();
	}

}
