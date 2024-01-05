package Model.Service.Service;

public class DecisionEvaluator {
    public static String evaluateDecisionTree(DecisionTree decisionTree,
                                              double mpgValue,
                                              double displacementValue,
                                              double accelerationValue,
                                              double weightValue,
                                              double horsePowerValue) {

        String mpg = mpgValue >= 32.66666667 ? "A" : "B";
        System.out.println("m"+mpg);

        String displacement = displacementValue >= 84.73333333 ? "A" : "B";
        System.out.println("d"+displacement);


        String horsePower = horsePowerValue >= 59.86666667 ? "A" : "B";
        System.out.println("h"+horsePower);


        String weight = weightValue >= 1782.666667 ? "A" : "B";
        System.out.println("w"+weight);


        String acceleration = accelerationValue >= 17.26666667 ? "A" : "B";
        System.out.println("a"+acceleration);



        DecisionTreeNode currentNode = decisionTree.getRoot();

        while (currentNode != null) {
            if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
                return currentNode.getDecision(); // C'est un nœud feuille
            }

            String attributeValue = getAttributeValue(currentNode.getAttribute(), mpg, displacement, acceleration, weight, horsePower);
            currentNode = "A".equals(attributeValue) ? currentNode.getLeftChild() : currentNode.getRightChild();
        }

        return "No decision";
    }

    private static String getAttributeValue(String attribute, String mpg, String displacement, String acceleration, String weight, String horsePower) {
        switch (attribute) {
            case "mpg":
                return mpg;
            case "horsePower":
                return horsePower;
            case "displacement":
                return displacement;
            case "weight":
                return weight;
            case "acceleration":
                return acceleration;
            default:
                return null;
        }
    }
}
