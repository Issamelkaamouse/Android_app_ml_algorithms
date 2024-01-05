// DecisionTreeNode.java
package Model.Service.Service;// DecisionTree.java

import java.io.Serializable;

public class DecisionTreeNode implements Serializable {
    public DecisionTreeNode(String attribute, String decision, DecisionTreeNode leftChild, DecisionTreeNode rightChild) {
        this.attribute = attribute;
        this.decision = decision;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public DecisionTreeNode() {
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public DecisionTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(DecisionTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public DecisionTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(DecisionTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    private String attribute;
    private String decision;
    private DecisionTreeNode leftChild;
    private DecisionTreeNode rightChild;

    // Constructeurs, getters et setters

}
