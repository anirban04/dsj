import java.util.*;


public abstract class Graph {
	private int numVertices;
	private int numEdges;
	
	public Graph() {
		numVertices = 0;
		numEdges = 0;
	}
	
	public int getNumVertices() {
		return numVertices;
	}
	
	public void setNumVertices(int n) {
		this.numVertices = n;
	}
	
	public void setNumEdges(int n) {
		this.numEdges = n;
	}
	
	public int getNumEdges() {
		return numEdges;
	}
	
	public boolean hasVertex(int v) {
		if (v < numVertices)
			return true;
		else
			return false;
	}
	
	public abstract void addVertex();
	
	public abstract void addEdge(int v1, int v2);
	
	public abstract List<Integer> getNeighbors(int v);
	
	public abstract List<Integer> getInNeighbors(int v);
	
	/* Get Neighbors that are 2 hops away */
	public abstract List<Integer> getNeighbors2(int v);

	public abstract String toString();

	public static void main(String[] args) {
		//GraphAdjMat adjMat = new GraphAdjMat();
		GraphAdjLst adjMat = new GraphAdjLst();
		adjMat.addVertex();
		adjMat.addVertex();
		adjMat.addVertex();
		adjMat.addVertex();

		adjMat.addEdge(0, 1);
		adjMat.addEdge(3, 0);
		adjMat.addEdge(0, 4);
		adjMat.addEdge(1, 4);

		System.out.println(adjMat);
		List<Integer> n = adjMat.getNeighbors(0);
		for (int i : n)
			System.out.printf("%d ", i);
		System.out.printf("\n");
		n = adjMat.getNeighbors2(0);
		for (int i : n)
			System.out.printf("%d ", i);
		System.out.printf("\n");
	}
}
