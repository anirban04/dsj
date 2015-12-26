import java.util.*;

public class GraphAdjLst extends Graph {

	/* The graph is stored as a Map of vertices and their 1 hop neighbors */
	Map<Integer, ArrayList<Integer>> adjLst;
	
	public GraphAdjLst() {
		/* Create an empty hash map to hold the graph */
		adjLst = new HashMap<Integer, ArrayList<Integer>>();
	}
	
	@Override
	public void addVertex() {
		adjLst.put(getNumVertices(), new ArrayList<Integer>());
		setNumVertices(getNumVertices() + 1);
	}

	@Override
	public void addEdge(int v1, int v2) {
		if ((v1 >= getNumVertices()) || (v2 >= getNumVertices()))
			return;

		adjLst.get(v1).add(v2);
		setNumEdges(getNumEdges() + 1);
	}

	@Override
	public List<Integer> getNeighbors(int v) {
		if (v >= getNumVertices())
				return null;
		
		/* Create a copy and return so that the outside world 
		 * does not get access to the internals of the graph */
		return new ArrayList<Integer>(adjLst.get(v));
	}

	@Override
	public List<Integer> getInNeighbors(int v) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		ArrayList<Integer> nbor;
		/* Check if input vertex is a valid one */
		if (v >= getNumVertices())
			return null;

		/* Iterate through all the ArrayLists in the Map and if an ArrayList
		 * element matches the input vertex, add the corrosponding key into
		 * the result ArrayList. */
		for (int i = 0; i < getNumVertices(); i++) {
			nbor = adjLst.get(i);
			for (int n : nbor) {
				if (n == v)
					ret.add(i);
			}
		}
		return ret;
	}

	@Override
	public List<Integer> getNeighbors2(int v) {
		 Set <Integer> set = new HashSet<Integer>();
		 List <Integer> ret = new ArrayList<Integer>();
		 List <Integer> n1, n2;
		 /* Get the 1 distance neighbors */
		 n1 = getNeighbors(v);
		 final boolean inclDup = true;
		 
		 for (int n : n1) {
			 /* Get neighbors of all the neighbors */
			 n2 = getNeighbors(n);
			 
			 if (inclDup) {
				 /* Use a set since if you do not want to include duplicates */
				 ret.addAll(n2);
			 }
			 else {
				 /* Else just add directly to the result array list */
				 set.addAll(n2);
			 }
		 }
		 
		 if (!inclDup) {
			 /* If we used a set, convert to an ArrayList */
		 	ret.addAll(set);
		 }
		 return ret;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("This is a Graph with " + getNumVertices() + " vertices and " +  getNumEdges() + " edges.");
		sb.append("\n");
		Set<Integer> nodeSet = adjLst.keySet();
		for (int i : nodeSet) {
			sb.append(i);
			sb.append("->");
			sb.append("{");
			ArrayList<Integer> lst = adjLst.get(i);
			for (int j : lst)
				sb.append(j + ", ");
			sb.append("}\n");
		}
		return sb.toString();
	}
}
