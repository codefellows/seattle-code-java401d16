package datastructures.tree;

public class BinaryTreeNode<T> {
    T value;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

    public BinaryTreeNode(T value) {
        this.value = value;
    }
}
