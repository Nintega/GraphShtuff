/**
 * 
 */
package graphTypes;

import java.util.ArrayList;

/**
 * @author mcs
 */

public class MyHeap<T extends Comparable<T> >
{
	ArrayList<T> heap;

	MyHeap() {
		heap = new ArrayList<T>();
		heap.add(null);
	}
	
	//kruskal's doesn't need an index number
	public T get(int i) {
		return heap.get(i);
	}

	public void add(T newItem) {
		heap.add(newItem);
		minify(heap.size() - 1);
	}

	public void minify(int i) {
		int parent = i/2;
		while (heap.get(parent) != null) {
			T parentNode = heap.get(parent);
			if (heap.get(i).compareTo(parentNode) < 1) {
				heap.set(parent, heap.get(i));
				heap.set(i, parentNode);
				i = parent;
				parent = i/2;
			}
			else
				break;
		}
	}

	public T removeMin() {
		T returnNode = heap.get(1);
		if (heap.size() > 2) {
			heap.set(1, heap.remove(heap.size() - 1));
			heapify(1);
		}
		else {
			heap.remove(1);
		}
		return returnNode;
	}

	public void heapify(int i) {
		int ldex = i*2, rdex = i*2 + 1;
		if (rdex < heap.size()) {
			T curr = heap.get(i);
			T left = heap.get(ldex);
			T right = heap.get(rdex);

			if (left.compareTo(curr) < 0) {
				if (left.compareTo(right) < 0) {
					heap.set(i, left);
					heap.set(ldex, curr);
					heapify(ldex);
				}
				else {
					heap.set(i, right);
					heap.set(rdex, curr);
					heapify(rdex);
				}
			}
			else if (right.compareTo(curr) < 0) {
				heap.set(i, right);
				heap.set(rdex, curr);
				heapify(rdex);
			}
		}
		else if (ldex < heap.size()) {
			T curr = heap.get(i);
			T left = heap.get(ldex);
			if (left.compareTo(curr) < 0) {
				heap.set(i, left);
				heap.set(ldex, curr);
			}
		}
	}
}


