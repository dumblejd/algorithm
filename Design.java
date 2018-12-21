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
					freq.append(key);
					map.put(key, value);
			} else {
				if(map.containsKey(key))
				{
					freq.append(key);
					map.put(key, value);
					return;
				}
				freq.append(key);
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
	//===================================================
	//method use linkedhashmap (without using removeEldestEntry)
//	public class LRUCache {
//	    Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
//	    int cap;
//	    
//	    public LRUCache(int capacity) {
//	        cap = capacity;
//	    }
//	    
//	    public int get(int key) {
//	        if (!map.containsKey(key))
//	            return -1;
//	            
//	        int val = map.remove(key);
//	        map.put(key, val);
//	        return val;
//	    }
//	    
//	    public void set(int key, int value) {
//	        if (map.containsKey(key)) {
//	            map.remove(key);
//	            map.put(key, value);
//	            return;
//	        }
//	        
//	        map.put(key, value);
//	        
//	        if (map.size() > cap)
//	            map.remove(map.entrySet().iterator().next().getKey());
//	    }
//	}
	//====================================
	//method three with removeEldestEntry
//	public class LRU<K,V> extends LinkedHashMap<K, V> implements Map<K, V>{
//
//	    private static final long serialVersionUID = 1L;
//
//	    public LRU(int initialCapacity,
//	             float loadFactor,
//	                        boolean accessOrder) {
//	        super(initialCapacity, loadFactor, accessOrder);
//	    }
//
//	    /** 
//	     * @description 重写LinkedHashMap中的removeEldestEntry方法，当LRU中元素多余6个时，
//	     *              删除最不经常使用的元素    
//	     * @created 2017年5月12日 上午11:32:51      
//	     * @param eldest
//	     * @return     
//	     * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)     
//	     */  
//	    @Override
//	    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
//	        // TODO Auto-generated method stub
//	        if(size() > 6){
//	            return true;
//	        }
//	        return false;
//	    }
//
//	    public static void main(String[] args) {
//
//	        LRU<Character, Integer> lru = new LRU<Character, Integer>(
//	                16, 0.75f, true);
//
//	        String s = "abcdefghijkl";
//	        for (int i = 0; i < s.length(); i++) {
//	            lru.put(s.charAt(i), i);
//	        }
//	        System.out.println("LRU中key为h的Entry的值为： " + lru.get('h'));
//	        System.out.println("LRU的大小 ：" + lru.size());
//	        System.out.println("LRU ：" + lru);
//	    }
//	}
//	--------------------- 
//==================reload method, eaiser than upper method 
//	public class LRUCache {
//	    Map<Integer, Integer> cache;
//	    int capacity;
//	    
//	    /*
//	    * @param capacity: An integer
//	    */public LRUCache(int capacity) {
//	        // do intialization if necessary
//	        this.capacity = capacity;
//	        //https://www.baeldung.com/java-linked-hashmap
//	        //The first parameter is the initial capacity, followed by the load factor and the last param is the ordering mode. So, by passing in true, we turned out access-order, whereas the default was insertion-order.
//	        //https://www.geeksforgeeks.org/linkedhashmap-removeeldestentry-method-in-java
//	        cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) { 
//	            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) { 
//	                return size() > capacity; 
//	            } 
//	        }; 
//	    }
//
//	    /*
//	     * @param key: An integer
//	     * @return: An integer
//	     */
//	    public int get(int key) {
//	        // write your code here
//	        if (!cache.containsKey(key)) {
//	            return -1;
//	        }
//	        return cache.get(key);
//	    }
//
//	    /*
//	     * @param key: An integer
//	     * @param value: An integer
//	     * @return: nothing
//	     */
//	    public void set(int key, int value) {
//	        // write your code here
//	        cache.put(key, value);
//	    }
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRUCache t =new LRUCache(2);
		t.put(2, 2);
		t.put(1, 1);
		t.put(3, 3);
		t.get(2);
	}


}
