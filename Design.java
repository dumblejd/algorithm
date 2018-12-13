package algorithmtest;

import java.util.HashMap;
import java.util.Map;

public class Design {
//146 use a stringbuferr which has contain and append built in .  Once get or put will append the key into the buffer. Reversly read the buffer to know which one to delete 
	static class LRUCache {
		int size = 0;
		StringBuffer freq = new StringBuffer();
		Map map = new HashMap<Integer, Integer>();// new HashMap<Integer,Integer>();

		public LRUCache(int capacity) {
			size = capacity;
		}

		public int get(int key) {
			int re = map.get(key) == null ? -1 : (int) map.get(key); // return value
			if (re != -1) {
				freq.append(key);
			}
			return re;
		}

		public void put(int key, int value) {
			
			if (map.size() < size) {
				if(!map.containsKey(key));
				{
					freq.append(key);
					map.put(key, value);
				}
			} else {
				freq.append(key);
				if(map.containsKey(key))
				{
					return;
				}
				map.put(key, value);
				StringBuffer temp = new StringBuffer();
				int tsize = size;
				for (int i = freq.length() - 1; i >= 0; i--) {
					if (temp.length() < tsize && !temp.toString().contains(freq.substring(i, i + 1))) {//hasn't full 
						temp.append(freq.substring(i, i+1));
					} else if (temp.length() >= tsize && !temp.toString().contains(freq.substring(i, i + 1))) {//has full and not in the list
						map.remove(Integer.valueOf(freq.substring(i, i+1)));
					}
				}
				freq = new StringBuffer(temp.reverse());
			}
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRUCache t =new LRUCache(2);
		t.put(2, 2);
		t.put(1, 1);
		t.put(3, 3);
		t.get(2);
	}


}
