// Anisha Aggarwal	CIS 212	Assignment 6
import java.util.Scanner;
import java.util.Arrays; 
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.SortedSet;


class OccurrenceSet<T> implements Set<T> {
	private HashMap<T, Integer> myMap = new HashMap<T, Integer>();
	private int _index;
	
	@Override
	public boolean add(T e) {
		int _count;
		if (myMap.containsKey(e)){
			_count = myMap.get(e);
			myMap.put(e, _count+1);
		} else {
			myMap.put(e,  1);
		}
		//System.out.println(myMap);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends T> c) {
		Iterator<? extends T> i = c.iterator();
		while(i.hasNext()) {
			((Set<T>) myMap).add((T) i.next());
		}
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (myMap.containsKey(o)) {
			myMap.remove(o);
			System.out.println(myMap);
			return true;
		} else {
			System.out.println(myMap);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> c) {
		((Collection<T>) myMap).removeAll(c);
		return true;
		
		/*Set<T> mySet = myMap.keySet();
		System.out.println(mySet);
		mySet.removeAll((Set<T>) c);
		return true;*/
		/*Iterator<?> i = c.iterator();
		while(i.hasNext()) {
			System.out.println("while");
			((Set<T>) myMap).remove((T) i.next());
		}
		return true;*/ 
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		
		return false;
	}
	
	@Override
	public boolean contains(Object o) {
		if (myMap.containsKey(o)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (myMap.containsKey(c)) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int size() {
		return myMap.size();
	}

	@Override
	public boolean isEmpty() {
		return myMap.isEmpty();
	}
	
	@Override
	public void clear() {
		myMap.clear();
	}

	@Override
	public Iterator<T> iterator() {
		private Set<T> mySet;
		Iterator <T> i;
		
		pulic OSet
		return null;
	}

	@Override
	public Object[] toArray() {
		Set<T> mySet = myMap.keySet();
		Object[] a = mySet.toArray();
		return a;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		
		return null;
	}
	
	@Override
	public String toString() {
		Iterator<Entry<T, Integer>> it = myMap.entrySet().iterator();
		String s = "";
		while (it.hasNext()) {
			HashMap.Entry<T, Integer> e = (Entry<T, Integer>) it.next();
			 s = s + e.getKey() + " ";
		}
		return s;
	}
	
}

public class Main_6 {
	public static void main(String[] args) {
		OccurrenceSet<Integer> intSet = new OccurrenceSet<Integer>();
		intSet.add(1);
		intSet.add(3);
		intSet.add(5);
		intSet.add(5);
		intSet.add(3);
		intSet.add(3);
		intSet.add(3);
		System.out.println(intSet);
		intSet.remove(5);
		System.out.println(intSet);
		if (intSet.contains(5)) {
			System.out.println("intSet contains 5");
		} else {
			System.out.println("intSet DOESN'T contains 5");
		}
		System.out.println(intSet.size());
		
		OccurrenceSet<String> stringSet = new OccurrenceSet<String>();
		stringSet.add("hello");
		stringSet.add("hello");
		stringSet.add("world");
		stringSet.add("world");
		stringSet.add("world");
		stringSet.add("here");
		System.out.println(stringSet);
		
		Object[] a = stringSet.toArray();
		System.out.println(a);
		
		OccurrenceSet<String> stringSet_2 = new OccurrenceSet<String>();
		stringSet_2.add("world");
		System.out.println(stringSet_2);
		//stringSet.removeAll(stringSet_2);
		//System.out.println(stringSet);
		
		OccurrenceSet<Integer> intSet_2 = new OccurrenceSet<Integer>();
		intSet_2.add(10);
		intSet_2.add(15);
		intSet.addAll(intSet_2);
		System.out.println(intSet);
		
	}
}
