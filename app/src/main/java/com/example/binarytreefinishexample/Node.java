package com.example.binarytreefinishexample;

public class Node {
    private Integer key;
    private int height;
    private Node leftChild;
    private Node rightChild;

    public Node( int key) {
        this.key = key;
        this.height=1;
    }

    public Integer getKey() {
        return key;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
