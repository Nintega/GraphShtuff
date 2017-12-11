/**
 * 
 */
package graphTypes;

/**
 * @author mcs
 *
 */
public class UFSet {

	int []parent;
	
	UFSet(int numSets) {
		parent = new int[numSets];
		for (int i = 0; i < numSets; i++)
			parent[i] = i;
	}
	
	public int find(int item) {
		int par = parent[item];
		if (item != par) {
			par = find(par);
			parent[item] = par;
			return par;
		}
		else
			return par;
	}
	
	public void union(int setA, int setB) {
		parent[setA] = setB;
	}	

}
