package TREES;

import java.util.Iterator;

public class BinaryTree<T> implements BinaryTreeInterface<T> {
	// fields
	private TreeNode<T> root;
	
	// constructors
	public BinaryTree() {
		this.root = null;
	}
	
	public BinaryTree(T rootData) {
		this.root = new TreeNode<>(rootData);
	}
	
	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		initializeTree(rootData, leftTree, rightTree);
	}
	

	@Override
	public T getData() {
		if (isEmpty()) {
			return null;
		} else {
			return root.getData();
		}
	}

	@Override
	public int getHeight() {
		int height = 0;
		if (root != null) {
			height = root.getHeight();
		}
		return height;
	}

	@Override
	public int getNumberOfNodes() {
		int numberOfNodes = 0;
		if (root != null) {
			numberOfNodes = root.getNumberOfNodes();
		}
		return numberOfNodes;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public Iterator<T> getInorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getPreorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getPostorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getLevelOrderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRootData(T rootData) {
		root.setData(rootData);
		
	}

	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		initializeTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
		
	}
	
	private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		root = new TreeNode<>(rootData);
		if (leftTree != null && !leftTree.isEmpty()) {
			root.setLeftChild(leftTree.root);
			
		}
		if (rightTree != null && !rightTree.isEmpty()) {
			if (rightTree != leftTree) {
				root.setRightChild(rightTree.root);
			} else {
				root.setRightChild(rightTree.root.copy());
			}
		}
		if (leftTree != null && leftTree != this) {
			leftTree.clear();
		}
		if (rightTree != null && rightTree != this) {
			rightTree.clear();
		}
	}
	
	protected void setRootNode(TreeNode<T> rootNode) {
		this.root = rootNode;
	}
	
	protected TreeNode<T> getRootNode() {
		return root;
	}
}
