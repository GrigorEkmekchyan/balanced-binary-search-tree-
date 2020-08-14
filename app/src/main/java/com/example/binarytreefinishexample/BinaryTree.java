package com.example.binarytreefinishexample;


public class BinaryTree {
    private Node root;
    private StringBuilder stringBuilder = new StringBuilder();


    public Node find(int key) {

        Node currentNode = root;
        while (currentNode != null && currentNode.getKey() != key) {

            if (key < currentNode.getKey()) {

                currentNode = currentNode.getLeftChild();


            } else {
                currentNode = currentNode.getRightChild();

            }
        }
        return currentNode;
    }


    public void insert(int item) {
        stringBuilder.delete(0,stringBuilder.length());
        this.root = insert(this.root, item);
    }

    private Node insert(Node node, int item) {
        if (node == null) {
            Node n = new Node(item);
            root = n;
            return n;
        }

        if (item > node.getKey()) {
            node.setRightChild(insert(node.getRightChild(), item));
        } else if (item < node.getKey()) {
            node.setLeftChild(insert(node.getLeftChild(), item));
        }
        node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);

        int heightInfo = calculateHeight(node);
        if (heightInfo > 1 && item < node.getLeftChild().getKey()) {
            return rightRotate(node);
        }
        if (heightInfo < -1 && item > node.getRightChild().getKey()) {
            return leftRotate(node);
        }
        if (heightInfo > 1 && item > node.getLeftChild().getKey()) {
            node.setLeftChild(leftRotate(node.getLeftChild()));
            return rightRotate(node);
        }
        if (heightInfo < -1 && item < node.getRightChild().getKey()) {
            node.setRightChild(rightRotate(node.getRightChild()));
            return leftRotate(node);
        }

        return node;
    }


    private Node searchNextRoot(Node deletingNode) {
        Node parentDeletingRoot = deletingNode;
        Node nextBaseRoot = deletingNode;
        Node current = nextBaseRoot.getRightChild();

        while (current != null) {
            parentDeletingRoot = nextBaseRoot;
            nextBaseRoot = current;
            current = current.getLeftChild();
        }

        if (nextBaseRoot != deletingNode.getRightChild()) {

            parentDeletingRoot.setLeftChild(nextBaseRoot.getRightChild());
            nextBaseRoot.setRightChild(deletingNode.getRightChild());
        }
        return nextBaseRoot;
    }


    public boolean delete(int deleteKey) {
        stringBuilder.delete(0,stringBuilder.length());
        Node currentNode = root;
        Node parent = currentNode;
        boolean isLeftChild = false;
        if (currentNode != null)
            while (currentNode.getKey() != deleteKey) {
                parent = currentNode;
                if (deleteKey < currentNode.getKey()) {
                    currentNode = currentNode.getLeftChild();
                    isLeftChild = true;
                } else {
                    isLeftChild = false;
                    currentNode = currentNode.getRightChild();
                }
                if (currentNode == null)
                    return false;
            }
        if (currentNode != null)
            if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {

                if (currentNode == root)
                    currentNode = null;

                else if (isLeftChild)
                    parent.setLeftChild(null);

                else
                    parent.setRightChild(null);
            } else if (currentNode.getRightChild() == null) {

                if (currentNode == root)
                    root = currentNode.getLeftChild();
                else if (isLeftChild)
                    parent.setLeftChild(currentNode.getLeftChild());
                else
                    currentNode.setRightChild(currentNode.getLeftChild());
            } else if (currentNode.getLeftChild() == null) {
                if (currentNode == root)
                    root = currentNode.getRightChild();
                else if (isLeftChild)
                    parent.setLeftChild(currentNode.getRightChild());
                else
                    parent.setRightChild(currentNode.getRightChild());
            } else {

                Node serachChaild = searchNextRoot(currentNode);

                if (currentNode == root)
                    root = serachChaild;
                else if (isLeftChild)
                    parent.setLeftChild(serachChaild);
                else parent.setRightChild(serachChaild);


                serachChaild.setLeftChild(currentNode.getLeftChild());

            }
        currentNode = null;
        parent = null;

        return true;
    }


    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    private int calculateHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    private Node rightRotate(Node currentNode) {
        Node currentChild = currentNode.getLeftChild();
        Node rightChildCurrent = currentChild.getRightChild();
        // Rotate

        currentChild.setRightChild(currentNode);
        currentNode.setLeftChild(rightChildCurrent);

        //update
        currentNode.setHeight(Math.max(height(currentNode.getLeftChild()), height(currentNode.getRightChild())) + 1);
        currentChild.setHeight(Math.max(height(currentChild.getLeftChild()), height(currentChild.getRightChild())) + 1);


        return currentChild;


    }

    private Node leftRotate(Node currentNode) {
        Node currentChild = currentNode.getRightChild();
        Node rightChildCurrent = currentChild.getLeftChild();
        // Rotate

        currentChild.setLeftChild(currentNode);
        currentNode.setRightChild(rightChildCurrent);

        //update
        currentNode.setHeight(Math.max(height(currentNode.getLeftChild()), height(currentNode.getRightChild())) + 1);
        currentChild.setHeight(Math.max(height(currentChild.getLeftChild()), height(currentChild.getRightChild())) + 1);


        return currentChild;
    }


    public String display() {
        return displayNode(this.root);
    }


    private String displayNode(Node node) {
        if (node == null) {
            return "'";
        }
        String str = "";
        if (node.getLeftChild() == null) {
            str += " [  null  ] ";
        } else {
            str += "'" + node.getLeftChild().getKey() + "'";
        }
        str += " left child <" + node.getKey() + "> right child ";

        if (node.getRightChild() == null) {
            str += " [  null  ] ";
        } else {
            str += "'" + node.getRightChild().getKey() + "'";
            stringBuilder.append(str);
            displayNode(node.getLeftChild());
            displayNode(node.getRightChild());
        }
        return stringBuilder.toString();

    }
}



