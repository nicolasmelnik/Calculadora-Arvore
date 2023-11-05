package Operando;

import Operador.*;
import java.util.List;
import java.util.Stack;

public class BinaryTree {

    protected Node root;

    public BinaryTree() {

    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    public int getDegree() {
        return root.getDegree();
    }

    public int getHeight() {
        return root.getHeight();
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
        System.out.println();
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
        System.out.println();
    }

    protected void inOrderTraversal(Node node) {
        if (isEmpty()) {
            System.out.println("A árvore está vazia!");
        }
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(node);
            inOrderTraversal(node.getRight());
        }
    }

    protected void preOrderTraversal(Node node) {
        if (isEmpty()) {
            System.out.println("A árvore está vazia!");
        }
        if (node != null) {
            System.out.print(node);
            preOrderTraversal(node.getLeft());
            preOrderTraversal(node.getRight());
        }
    }

    protected void postOrderTraversal(Node node) {
        if (isEmpty()) {
            System.out.println("A árvore está vazia!");
        }
        if (node != null) {
            postOrderTraversal(node.getLeft());
            postOrderTraversal(node.getRight());
            System.out.print(node);
        }
    }

    public void buildTreeFromExpression(List<String> tokens) {
        Stack<Node> stack = new Stack<>();

        for (String token : tokens) {
            if (isNumeric(token)) {
                // Se o token for um número, cria um nó folha e empilha na pilha
                Node NodeOperando = new NodeOperando(token);
                stack.push(NodeOperando);
            } else if (isOperator(token)) {
                // Se o token for um operador, cria um nó de operação e desempilha os operandos
                // da pilha
                Node operatorNode = createOperatorNode(token);
                Node rightOperand = stack.pop();
                Node leftOperand = stack.pop();
                operatorNode.setLeft(leftOperand);
                operatorNode.setRight(rightOperand);
                stack.push(operatorNode);
            }
        }

        if (!stack.isEmpty()) {
            // O último nó na pilha será a raiz da árvore de expressão
            root = stack.pop();
        }
    }

    private Node createOperatorNode(String operator) {
        switch (operator) {
            case "+":
                return new NodeAdicao();
            case "-":
                return new NodeSubtracao();
            case "*":
                return new NodeMultiplicacao();
            case "/":
                return new NodeDivisao();
            default:
                throw new IllegalArgumentException("Operador inválido: " + operator);
        }
    }

    private boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    public float calculateExpression() {
        return root.visitar();
    }
}
