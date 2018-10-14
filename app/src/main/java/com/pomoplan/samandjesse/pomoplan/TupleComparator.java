package com.pomoplan.samandjesse.pomoplan;

import java.util.ArrayList;
import java.util.Comparator;

public class TupleComparator implements Comparator<ArrayList<Integer>> {
	@Override
	public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
		//both lists must be tuples of size 2
		try{
			if(a.size() != 2 || b.size() != 2) {
			System.out.println(a.size());
			throw  new IllegalArgumentException("ArrayLists must be tuples if given to tuple comparator!");
			}
		}
		catch(IllegalArgumentException e) {
			System.err.println("ArrayLists provided to tuple comparator must be of size 2");
			e.printStackTrace();
		}
		if(a.get(1) > b.get(1)) { //end of tuple is greater in a 
			return 1;
		}
		if(a.get(1) < b.get(1)) { //end of tuple is greater in a 
			return -1;
		}
		return 0;
	}
}