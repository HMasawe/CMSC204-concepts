package TREES;

class TreeNode<T> {
	
	// fields
	private T data;
	private TreeNode<T> leftChild;
	private TreeNode<T> rightChild;
	
	// constructors
	public TreeNode() {
		this(null);
	}
	
	public TreeNode(T data) {
		this(data, null, null);
	}
	
	public TreeNode(T data, TreeNode<T> leftChild, TreeNode<T> rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public TreeNode<T> getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(TreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	
	public boolean hasLeftChild() {
		return leftChild != null;
	}
	
	public TreeNode<T> getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(TreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	public boolean hasRightChild() {
		return rightChild != null;
	}
	
	public boolean isLeaf() {
		return !hasLeftChild() && !hasRightChild();
	}
	
	public int getNumberOfNodes() {
		return getNumberOfNodes(this);
		
	}
	
	public int getHeight() {
		return getHeight(this);
		
	}
	
	public TreeNode<T> copy() {
		return null;
		
	}
	
	private int getHeight(TreeNode<T> node) {
		int height = 0;
		if (node != null) {
			height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
		}
		return height;
	}
	
	private int getNumberOfNodes(TreeNode<T> node) {
		int leftNumber = 0;
		int rightNumber = 0;
		if (leftChild != null) {
			leftNumber = leftChild.getNumberOfNodes();
		}
		if (rightChild != null) {
			rightNumber = rightChild.getNumberOfNodes();
		}
		return 1 + leftNumber + rightNumber;
	}
	
	

}
