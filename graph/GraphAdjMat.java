import java.util.ArrayList;
import java.util.List;

public class GraphAdjMat extends Graph {
	
	private int size = 5;
	/* The graph is stored as a 2 dimensional array */
	int[][] adjMat;

	/* Create a new 2 dimensional empty array */
	public GraphAdjMat() {
		adjMat = new int[size][size];
	}
	
	@Override
	public void addVertex() {
		int newVertex = getNumVertices() + 1;
		/* Check if the array needs to be grown */
		if (newVertex >= size) {
			/* If so, allocate an array double the existing size.Then copy
			 * over the elements from the older array to the new one.*/
			 
			int[][] temp = new int[size * 2][size * 2];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					temp[i][j] = adjMat[i][j];
				}
			}
			adjMat = temp;
			/* Update the size to the size of the new array */
			size = size * 2;
		}
		
		/* Clear all edges for the newly added vertex - i.e. Set all the
		 * elements of the new row and the new column to 0  */
		for (int i = 0; i <= newVertex; i++) {
			adjMat[newVertex][i] = 0;
			adjMat[i][newVertex] = 0;
		}
		/* Update the number of vertices count */
		setNumVertices(newVertex);
	}

	@Override
	public void addEdge(int v1, int v2) {
		/* Just update the element at v1,v2 index */
		adjMat[v1][v2] = 1;
		/* Update the number of edges count */
		setNumEdges(getNumEdges() + 1);
	}

	@Override
	public List<Integer> getNeighbors(int v) {
		/* Check if the vertex exists */
		if (v >= getNumVertices())
			return null;
		
		List<Integer> lst = new ArrayList<Integer>();
		/* Index to row = vertex and iterate over all columns.
		 * Add to the return list, any non zero columns */
		for (int j = 0; j < getNumVertices(); j++) {
				/* Every non zero column will need to be added as any times as 
				 * the value of the element in that non zero column. This is to 
				 * take case of the case of more than 1 path between 2 nodes */
				for (int i = 0; i < adjMat[v][j]; i++) {
				lst.add(j);
			}
		}
		
		return lst;
	}
	
	@Override
	public List<Integer> getInNeighbors(int v) {
		/* Check if the vertex exists */
		if (v >= getNumVertices())
			return null;
		
		List<Integer> lst = new ArrayList<Integer>();
		/* Index to column = vertex and iterate over all rows.
		 * Add to the return list, any non zero rows */
		for (int i = 0; i < getNumVertices(); i++) {
			/* Every non zero row will need to be added as any times as 
			 * the value of the element in that non zero row. This is to 
			 * take case of the case of more than one path between 2 nodes */
			for (int j = 0; j < adjMat[i][v]; j++) {
				lst.add(i);
			}	
		}
		return lst;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("This is a Graph with " + getNumVertices() + " vertices and " +  getNumEdges() + " edges.");
		sb.append("\n");
		for (int i = 0; i < getNumVertices(); i++) {
			for (int j = 0; j < getNumVertices(); j++) {
				sb.append(adjMat[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	/* Get Neighbors that are 2 hops away */
	public List<Integer> getNeighbors2(int v) {		
		if (v >= getNumVertices())
			return null;

		List<Integer> ret = new ArrayList<Integer>();
		
		/* Compute the square of the matrix. All the non zero nodes in a square
		 * matrix indicate the presence of a 2 hop edge between the row(start)
		 * and column(end) */
		int [][]sq = matSq();
		for (int i = 0; i < getNumVertices(); i++) {
			for (int j = 0; j < sq[v][i]; j++) {
				ret.add(i);
			}
		}
		return ret;
	}
	
	/* Helper function to find the square of a matrix.
	 * Run 3 loops over the length of the array. 
	 * res[i][j] += adjMat[i][k] * adjMat[k][j]; */
	private int[][] matSq() {
		int[][] res = new int[size][size];
		for (int i = 0; i < getNumVertices(); i++) {
			for (int j = 0; j < getNumVertices(); j++) {
				/* Clear before starting to accumulate */
				res[i][j] = 0;
				for (int k = 0; k < getNumVertices(); k++){
					res[i][j] += adjMat[i][k] * adjMat[k][j];
				}
			}
		}
		return res;
	}

}
