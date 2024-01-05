package Service;// DecisionTree.java

import android.util.Log;

import java.io.Serializable;

public class DecisionTree implements Serializable {
    private DecisionTreeNode root;

    // Constructeurs, getters et setters

    public DecisionTree(DecisionTreeNode root) {
        this.root = root;
    }

    public DecisionTree() {

    }

    public DecisionTreeNode getRoot() {
        return root;
    }

    public void setRoot(DecisionTreeNode root) {
        this.root = root;
    }

    public void clearTree() {
        this.root = null; // Réinitialiser la racine de l'arbre
    }

    public void printTree() {
        printTree(root, "");
    }

    public void printTree(DecisionTreeNode node, String indent) {
        if (node == null) {
            return;
        }

        System.out.println(indent + "Attribute: " + node.getAttribute());
        if (node.getDecision()!=null)
            System.out.println(indent + "Decision: " + node.getDecision());

        printTree(node.getLeftChild(), indent + "  | ");
        printTree(node.getRightChild(), indent + "  | ");
    }


}
