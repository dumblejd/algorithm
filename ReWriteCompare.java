package algorithmtest;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.*;

public class ReWriteCompare {
	 static class Student {
		public int s_no;
		public String s_name;
		public int s_class;

		public void t()
		{
			
		}
		public Student(int no) {
			this.s_no = no;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeSet <Student>s = new TreeSet<Student>(new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				return o1.s_no-o2.s_no;
			}});
		
		s.add(new Student(3));
		s.add(new Student(6));
		s.add(new Student(2));
		List<Student>c = new ArrayList<Student>();
		Collections.sort(c, ( a, b)->{return a.s_no-b.s_no;});
		
		Map<Student,Integer> m =  new HashMap<Student,Integer>();
		//Map tm = new TreeMap<Student,Integer>(m,);
//		Collections.sort( s,new Comparator<Student>() {   //illegal
//
//			@Override
//			public int compare(Student o1, Student o2) {
//				// TODO Auto-generated method stub
//				return o1.s_no-o2.s_no;
//			}});
	}

}
