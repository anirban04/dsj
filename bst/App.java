
public class App {
	public static void main(String[] args) {
		BST<Integer> t = new BST<Integer>();
		
		t.add(10);
		t.add(2);
		t.add(6);
		t.add(20);
		t.add(15);
		//t.add(22);
		t.add(1);
		
		//System.out.println(t.isPresent(22));
		//t.printPreOrder(t.root);
		//t.printPostOrder(t.root);
		t.printLevelOrder(t.root);
		t.remove(20);
		System.out.println();
		t.printLevelOrder(t.root);
	}
}
