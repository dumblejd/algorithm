package algorithmtest;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class Graph {
	//133 clone graph

	static class  UndirectedGraphNode {
	      int label;
	      List<UndirectedGraphNode> neighbors;
	    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	 };
//	 public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
//	        if(node==null)
//	        {
//	        	return null;
//	        }
//			Queue <UndirectedGraphNode> s = new LinkedList<UndirectedGraphNode>();
//			Queue <UndirectedGraphNode> copy = new LinkedList<UndirectedGraphNode>();
//			UndirectedGraphNode root = new UndirectedGraphNode(node.label);
//			UndirectedGraphNode root_work=root;
//			s.offer(node);
//			copy.offer(root_work);
//			while(!s.isEmpty())
//			{
//				UndirectedGraphNode now =s.poll();
//				root_work=copy.poll();
//				for(UndirectedGraphNode temp:now.neighbors)
//				{
//					s.offer(temp);
//					root_work.neighbors.add(new UndirectedGraphNode(temp.label));
//					copy.offer(root_work.neighbors.get(root_work.neighbors.size()-1));
//				}
//			}
//			return root;
//		 }
	 public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
	        if(node==null)
	        {
	        	return null;
	        }
			Queue <UndirectedGraphNode> s = new LinkedList<UndirectedGraphNode>();
			Map <Integer,UndirectedGraphNode> m = new HashMap <Integer,UndirectedGraphNode>();
			UndirectedGraphNode root = new UndirectedGraphNode(node.label);
			m.put(node.label, root);
			UndirectedGraphNode root_work=root;
			s.offer(node);
			while(!s.isEmpty())
			{
				UndirectedGraphNode now =s.poll();
				
				if(!m.containsKey(now.label))
				{
					m.put(now.label,new UndirectedGraphNode(now.label));
				}
				for(UndirectedGraphNode temp:now.neighbors)
				{
					s.offer(temp);
					m.get(now.label).neighbors.add(new UndirectedGraphNode(temp.label));
				}
			}
			return root;
		 }
	 public static void main(String[] args) {
		 UndirectedGraphNode a = new UndirectedGraphNode(0);
		 a.neighbors.add(new UndirectedGraphNode(1) );
		 
		 a.neighbors.add(new UndirectedGraphNode(2) );
		Graph g =new Graph();
		 g.cloneGraph(a);
	}
}
