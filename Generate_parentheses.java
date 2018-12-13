package algorithmtest;

import java.util.ArrayList;
import java.util.List;

public class Generate_parentheses {
	public void dfs(List<String> ret, String tmp, int left, int right)
    {
        if (0 == left && 0 == right)
        {
            ret.add(tmp);
            return;
        }
        else if (left > 0)
            dfs(ret, tmp + '(', left - 1, right);

        if (left < right)
            dfs(ret, tmp + ')', left, right - 1);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Generate_parentheses gp = new Generate_parentheses();
		List<String> t = new ArrayList<String>();
		String k = "123";
		t.add(k);
		k = "234";
		t.remove(0);
		gp.dfs(t, "", 3, 3);
	}

}
