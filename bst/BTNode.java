
public class BTNode <E> {
	private BTNode <E> parent;
	private BTNode <E> left;
	private BTNode <E> right;
	private E data;
	
	public BTNode(E data, BTNode <E> parent) {
		this.data = data;
		this.parent = parent;
	}
	
	public BTNode <E> getParent() {
		return this.parent;
	}
	
	public BTNode <E> getLeft() {
		return this.left;
	}
	
	public BTNode <E> getRight() {
		return this.right;
	}
	
	public E getData() {
		return this.data;
	}
	
	public void setParent(BTNode <E> parent) {
		this.parent = parent;
	}
	
	public void setLeft(BTNode <E> left) {
		this.left = left;
	}
	
	public void setRight(BTNode <E> right) {
		this.right = right;
	}
	
	public void setData(E data) {
		this.data = data;
	}
	
	public void printNode() {
		BTNode<E> parent = this.parent;
		if (parent != null)
			System.out.println(" " + this.data + " " + this.parent.getData());
		else
			System.out.println(" " + this.data + " ");
	}
}
