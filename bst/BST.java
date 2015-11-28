import java.util.*;

public class BST <E extends Comparable<? super E>> {
	BTNode <E> root;
	int size;
	
	public BST() {
		this.root = null;
		this.size = 0;
	}
	
	/* Helper function to get a node from the tree */
	private BTNode<E> getNode(E data) {
		BTNode<E> node = root;
		int comp;
		
		while (node != null) {
			comp = (data).compareTo(node.getData());
			if(comp < 0) {
				node = node.getLeft();
			}
			else if (comp > 0) {
				node = node.getRight();
			}
			else {
				return node;
			}
		}
		return null;
	}
	
	/* Helper function to to delete a node with no children */
	private void delNode(BTNode<E> node) {
		/* Figure out which child of the parent is this node */
		if (node.getParent().getLeft() == node) {
			/* Sever the link */
			node.getParent().setLeft(null);
		}
		else {
			/* Sever the link */
			node.getParent().setRight(null);
		}
	}
	
	/* Helper function to to delete a node with 1 child */
	private void delNodeOneChild(BTNode<E> node) {
		BTNode<E> parent = node.getParent();
		BTNode<E> child;
		
		/* Get the child reference */
		if (node.getLeft() != null)
			child = node.getLeft();
		else
			child = node.getRight();

		/* Figure out which child of its 
		 * parent is node  and set new links
		 */
		if (parent.getLeft() == node)
			parent.setLeft(child);
		else
			parent.setRight(child);
		
		/* Set the parent link for the new child */
		child.setParent(parent);
	}
	
	public void add(E data) {
		
		BTNode<E> newNode = new BTNode<E>(data, null);
		BTNode<E> node;
		BTNode<E> parent;
		boolean isLeft = false;
		
		/* Check if this is the first node that we are adding to the tree */
		if (root == null) {
			root = newNode;
			return;
		}
		/* Set the references for tree traversal */
		else {
			node = root;
			parent = null;
		}
		
		int comp; 
		/* Get the place in the tree where the node can be added */
		while (node != null) {
			parent = node;
			comp = (data).compareTo(node.getData());
			if(comp < 0) {
				node = node.getLeft();
				isLeft = true;
			}
			else if (comp > 0) {
				node = node.getRight();
				isLeft = false;
			}
			else {
				System.out.println("Value already in BST");
				return;
			}
		}
		
		/* Set the child links */
		if(isLeft)
			parent.setLeft(newNode);
		else
			parent.setRight(newNode);
		
		/* Set the parent link */
		newNode.setParent(parent);
		
		size++;
	}
	
	public boolean isPresent(E data) {
		
		if(getNode(data)  == null)
			return false;
		else
			return true;
	}
	
	public void remove(E data) {
		
		BTNode<E> node;
		BTNode<E> lowestRight;
		
		node = getNode(data);
		/* Check if node is present in the tree */
		if(node  == null)
			return;		
		/* Check if the node to be deleted is the root node */
		else if (node == root) {
			root = null;
			return;
		}

		/* Case1 - Node is a leaf node */
		if ((node.getLeft() == null) && (node.getRight() == null)) {
			delNode(node);
		}
		/* Case2 - Node has only one child */ 
		else if ((node.getLeft() == null) || (node.getRight() == null)) {
			delNodeOneChild(node);
		}
		/* Case3 - Node has 2 children */
		else {
			/* Find the lowest value in the right subtree */
			lowestRight = node;
			lowestRight = lowestRight.getRight();
			while(lowestRight.getLeft() != null)
				lowestRight = lowestRight.getLeft();
			
			/* Hoist just the value of the lowest right
			 * Up to the node we want to delete 
			 */
			node.setData(lowestRight.getData());
		
			/* Delete the lowestLeft - This lowestRight 
			 * will have at most one child node (right) */

			/* Check if no children*/
			if ((lowestRight.getLeft() == null) && (lowestRight.getRight() == null))
				delNode(lowestRight);
			else
				delNodeOneChild(lowestRight);
		}
		size--;
	}
	
	public void printPreOrder(BTNode<E> node) {
		/* Check for the base case */
		if (node == null)
			return;
		
		/* Visit the node */
		node.printNode();
		/* Recursively call */
		printPreOrder(node.getLeft());
		printPreOrder(node.getRight());
	}
	
	public void printPostOrder(BTNode<E> node) {
		/* Check for the base case */
		if (node == null)
			return;

		/* Recursively call */
		printPostOrder(node.getLeft());
		printPostOrder(node.getRight());
		/* Visit the node */
		node.printNode();
	}
	
	public void printLevelOrder(BTNode<E> node) {
		
		BTNode<E> n;
		Queue<BTNode<E>> q = new LinkedList<BTNode<E>>();
		
		/* Add the node to the queue */
		if (node!=null)
			q.add(node);
		
		/* Run till the queue has more elements */
		while (!q.isEmpty()) {
			/* Check if the element removed is a null */
			if ((n = q.remove()) != null) {
				/* Visit the node */
				n.printNode();
				/* Add its children to the queue */
				q.add(n.getLeft());
				q.add(n.getRight());
			}
		}
	}
	
	public int Size() {
		return size;
	}
}
