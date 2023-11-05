package Operando;

public class Node {

    private String data;
    private Node parent;
    private Node left;
    private Node right;

    public Node() {
        data = "";
    }

    public Node(String data) {
        this.data = data;
    }

    public Node(String data, Node parent) {
        this.data = data;
        this.parent = parent;
    }

    public Node(String data, Node parent, Node left, Node right) {
        this.data = data;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isRoot() {
        if (this.parent == null) {
            return true;
        }
        return false;
    }

    public boolean isLeaf() {
        if ((this.left == null) && (this.right == null)) {
            return true;
        }
        return false;
    }

    public int getDegree() {
        int degree = 0;
        if (this.left != null) {
            degree++;
        }
        if (this.right != null) {
            degree++;
        }
        return degree;
    }

    public int getLevel() {
        if (this.parent == null) {
            return 0;
        }
        return 1 + this.parent.getLevel();
    }

    public int getHeight() {
        if (isLeaf()) {
            return 0;
        }

        int leftHeight = this.left != null ? this.left.getHeight() : 0;
        int rightHeight = this.right != null ? this.right.getHeight() : 0;

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public float visitar() {
        return Float.NaN;
    }

    @Override
    public String toString() {
        return data + " ";
    }
}
