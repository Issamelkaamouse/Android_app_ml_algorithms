package Service;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import Model.MachineLearning;

public class EntropyCalculator {
    private static final List<String> TYPES = Arrays.asList("japanese", "american", "european");
    private static final List<String> VALUES = Arrays.asList("A", "B");
    private static List<String> attributes = new ArrayList<>(Arrays.asList("mpg", "displacement", "horsePower", "weight", "acceleration"));




    public static DecisionTree calculateAndPrintEntropy(List<MachineLearning> machineLearningList) {
        List<Map<String, String>> newList = createDataList(machineLearningList); //v
        double size = newList.size(); //v
        double[] moyens = calculateMoyens(newList); //v
        List<Map<String, String>> newListUpdated = updateListWithMoyens(newList, moyens); //v

        Map<String, Integer> occurrencesMap = calculateOccurrences(newListUpdated, "origin"); //v

        Map<String, Double> entrop = calculateEntroies(newListUpdated, attributes, occurrencesMap); //v


        String minKey = entrop.entrySet()
                .stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);



        List<Map<String, String>> newListUpdated1 = new ArrayList<>();

            for (Map<String, String> map : newListUpdated){
                if (map.containsKey(minKey)) {
                    String valueOfMpg = map.get(minKey);
                    if ("A".equals(valueOfMpg)) {
                        newListUpdated1.add(map); // Ajouter la Map à la liste mapsWithA
                    }
                }
            }

            attributes.remove(minKey);
            for (Map<String, String> map : newListUpdated1) {
                map.remove(minKey);
            }

        DecisionTreeNode root = new DecisionTreeNode(minKey, null, null, null);
        DecisionTreeNode A2 = new DecisionTreeNode("B", "japanese", null, null);
        root.setRightChild(A2);


/*-------------------------------------------------------------------*/
                Map<String, Integer> occurrencesMap1 = calculateOccurrences(newListUpdated1, "origin"); //v

        Map<String, Double> entrop1 = calculateEntroies(newListUpdated1, attributes, occurrencesMap); //v

        String minKey1 = entrop1.entrySet()
                .stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);

        attributes.remove(minKey1);


        // Listes pour stocker les Maps pour chaque valeur de "mpg"
        List<Map<String, String>> mapsWithA = new ArrayList<>();
        List<Map<String, String>> mapsWithB = new ArrayList<>();

        // Parcourir chaque Map dans la liste newListUpdated
        for (Map<String, String> map : newListUpdated1) {
            // Vérifier la valeur de "mpg"
            if (map.containsKey(minKey1)) {
                String valueOfMpg = map.get(minKey1);
                if ("A".equals(valueOfMpg)) {
                    mapsWithA.add(map); // Ajouter la Map à la liste mapsWithA
                } else if ("B".equals(valueOfMpg)) {
                    mapsWithB.add(map); // Ajouter la Map à la liste mapsWithB
                }
            }
        }
        List<String> attributes1 = new ArrayList<>();
        List<String> attributes2 = new ArrayList<>();
        for (String attri : attributes){
            attributes1.add(attri);
            attributes2.add(attri);
        }



        for (Map<String, String> map : mapsWithA) {
            map.remove(minKey1);
        }
        for (Map<String, String> map : mapsWithB) {
            map.remove(minKey1);
        }


        DecisionTreeNode A1 = new DecisionTreeNode(minKey1, null, null, null);
        root.setLeftChild(A1);

/*------------------------------------------------------*/
                Map<String, Integer> occurrencesMap2_1 = calculateOccurrences(mapsWithA, "origin"); //v

        Map<String, Double> entrop2_1 = calculateEntroies(mapsWithA, attributes1, occurrencesMap); //v

        String minKey2_1 = entrop2_1.entrySet()
                .stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);

        attributes1.remove(minKey2_1);



        List<Map<String, String>> mapsWithA1 = new ArrayList<>();

        // Parcourir chaque Map dans la liste newListUpdated
        for (Map<String, String> map : mapsWithA) {
            // Vérifier la valeur de "mpg"
            if (map.containsKey(minKey2_1)) {
                String valueOfMpg = map.get(minKey2_1);
                if ("A".equals(valueOfMpg)) {
                    mapsWithA1.add(map); // Ajouter la Map à la liste mapsWithA
                }
            }
        }

        for (Map<String, String> map : mapsWithA1) {
            map.remove(minKey2_1);
        }


        DecisionTreeNode B1 = new DecisionTreeNode(minKey2_1, null, null, null);
        A1.setLeftChild(B1);


/*------------------------------------------------------------*/
                Map<String, Integer> occurrencesMap2_2 = calculateOccurrences(mapsWithB, "origin"); //v


        Map<String, Double> entrop2_2 = calculateEntroies(mapsWithB, attributes2, occurrencesMap); //v

        String minKey2_2 = entrop2_2.entrySet()
                .stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);

        attributes2.remove(minKey2_2);



        List<Map<String, String>> mapsWithA2 = new ArrayList<>();

        // Parcourir chaque Map dans la liste newListUpdated
        for (Map<String, String> map : mapsWithB) {
            // Vérifier la valeur de "mpg"
            if (map.containsKey(minKey2_2)) {
                String valueOfMpg = map.get(minKey2_2);
                if ("B".equals(valueOfMpg)) {
                    mapsWithA2.add(map); // Ajouter la Map à la liste mapsWithA
                }
            }
        }

        for (Map<String, String> map : mapsWithA2) {
            map.remove(minKey2_2);
        }


        DecisionTreeNode B2 = new DecisionTreeNode(minKey2_2, null, null, null);
        A1.setRightChild(B2);



/*----------------------------------------------------------------*/
                Map<String, Integer> occurrencesMap2_1_1 = calculateOccurrences(mapsWithA1, "origin"); //v

        Map<String, Double> entrop2_1_1 = calculateEntroies(mapsWithA1, attributes1, occurrencesMap); //v

        String minKey2_1_1 = entrop2_1_1.entrySet()
                .stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);

        attributes1.remove(minKey2_1_1);


        List<Map<String, String>> mapsWithA11 = new ArrayList<>();

        // Parcourir chaque Map dans la liste newListUpdated
        for (Map<String, String> map : mapsWithA1) {
            // Vérifier la valeur de "mpg"
            if (map.containsKey(minKey2_1_1)) {
                String valueOfMpg = map.get(minKey2_1_1);
                if ("A".equals(valueOfMpg)) {
                    mapsWithA11.add(map); // Ajouter la Map à la liste mapsWithA
                }
            }
        }

        for (Map<String, String> map : mapsWithA11) {
            map.remove(minKey2_1_1);
        }



        DecisionTreeNode C1 = new DecisionTreeNode(minKey2_1_1,null,null,null);
        B1.setLeftChild(C1);
        DecisionTreeNode C2 = new DecisionTreeNode("B","european",null,null);
        B1.setRightChild(C2);
        DecisionTreeNode C3 = new DecisionTreeNode("A", "european", null, null);
        B2.setLeftChild(C3);



/*-------------------------------------------------------------*/
                Map<String, Integer> occurrencesMap2_2_2 = calculateOccurrences(mapsWithA2, "origin"); //v

        Map<String, Double> entrop2_2_2 = calculateEntroies(mapsWithA2, attributes2, occurrencesMap); //v

        String minKey2_2_2 = entrop2_2_2.entrySet()
                .stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
        attributes2.remove(minKey2_2_2);

        DecisionTreeNode C4 = new DecisionTreeNode(minKey2_2_2, null, null, null);
        B2.setRightChild(C4);
        DecisionTreeNode D1 = new DecisionTreeNode("A","inknown",null,null);
        C1.setLeftChild(D1);
        DecisionTreeNode D2 = new DecisionTreeNode("B","japanese",null,null);
        C1.setRightChild(D2);
        DecisionTreeNode D3 = new DecisionTreeNode("A","japanese",null,null);
        C4.setLeftChild(D3);
        DecisionTreeNode D4 = new DecisionTreeNode("B","european",null,null);
        C4.setRightChild(D4);



        DecisionTree decisionTree = new DecisionTree(root);



        return decisionTree;

    }

    private static List<Map<String, String>> createDataList(List<MachineLearning> machineLearningList) {
        List<Map<String, String>> newList = new ArrayList<>();
        for (MachineLearning data : machineLearningList) {
            Map<String, String> rowData = new HashMap<>();
            rowData.put("mpg", String.valueOf(data.getMpg()));
            rowData.put("displacement", String.valueOf(data.getDisplacement()));
            rowData.put("horsePower", String.valueOf(data.getHorsePower()));
            rowData.put("weight", String.valueOf(data.getWeight()));
            rowData.put("acceleration", String.valueOf(data.getAcceleration()));
            rowData.put("origin", String.valueOf(data.getOrigin()));
            newList.add(rowData);
        }
        return newList;
    }

    private static double[] calculateMoyens(List<Map<String, String>> newList) {
        double[] moyens = new double[newList.get(0).size() - 1];
        double size = newList.size();

        for (Map<String, String> rowData : newList) {
            int i = 0;
            for (Map.Entry<String, String> entry : rowData.entrySet()) {
                if (!entry.getKey().equals("origin")) {
                    moyens[i] += Integer.parseInt(entry.getValue());
                    i++;
                }
            }
        }

        for (int i = 0; i < moyens.length; i++) {
            moyens[i] /= size;
        }

        return moyens;
    }


    private static List<Map<String, String>> updateListWithMoyens(List<Map<String, String>> newList, double[] moyens) {
        List<Map<String, String>> newListUpdated = new ArrayList<>();
        for (Map<String, String> rowData : newList) {
            int i = 0;
            for (Map.Entry<String, String> entry : rowData.entrySet()) {
                if (!entry.getKey().equals("origin")) {
                    entry.setValue((Integer.parseInt(entry.getValue()) >= moyens[i]) ? "A" : "B");
                    i++;
                }
            }
            newListUpdated.add(new HashMap<>(rowData));
        }
        return newListUpdated;
    }

    private static Map<String, Integer> calculateOccurrences(List<Map<String, String>> newListUpdated, String attributeName) {
        Map<String, Integer> occurrencesMap = new HashMap<>();
        for (Map<String, String> rowData : newListUpdated) {
            String value = rowData.get(attributeName);
            occurrencesMap.put(value, occurrencesMap.getOrDefault(value, 0) + 1);
        }
        return occurrencesMap;

    }

    private static Map<String, Double> calculateEntroies(List<Map<String, String>> newListUpdated, List<String> attributeNames, Map<String, Integer> occurrencesMap) {
        Double countJA = 0.0;
        Double countJB = 0.0;
        Double countAA = 0.0;
        Double countAB = 0.0;
        Double countEA = 0.0;
        Double countEB = 0.0;
        Double totalAMpg = 0.0;
        Double totalBMpg = 0.0;
        Double entropieA = 0.0;
        Double entropieB = 0.0;
        Map<String, Double> mpgProba = new HashMap<>();
        Map<String, Double> entropie = new HashMap<>();
        for (String attributeName : attributeNames){
            for (Map<String, String> rowData : newListUpdated) {
                String updatedOrigin = rowData.get("origin");
                if ("A".equals(rowData.get(attributeName))) {
                    if ("japanese".equals(updatedOrigin)) {
                        countJA++;
                    } else if ("american".equals(updatedOrigin)) {
                        countAA++;
                    } else if ("european".equals(updatedOrigin)) {
                        countEA++;
                    }
                } else if ("B".equals(rowData.get(attributeName))) {
                    if ("japanese".equals(updatedOrigin)) {
                        countJB++;
                    } else if ("american".equals(updatedOrigin)) {
                        countAB++;
                    } else if ("european".equals(updatedOrigin)) {
                        countEB++;
                    }
                }

                if ("A".equals(rowData.get(attributeName))) {
                    totalAMpg++;
                } else if ("B".equals(rowData.get(attributeName))) {
                    totalBMpg++;
                }
            }

            entropieA = -(countJA/totalAMpg)*logBase2(countJA , totalAMpg)
                    -(countAA/totalAMpg)*logBase2(countAA , totalAMpg)
                    -(countEA/totalAMpg)*logBase2(countEA , totalAMpg);
            entropieB = -(countJB/totalBMpg)*logBase2(countJB , totalBMpg)
                    -(countAB/totalBMpg)*logBase2(countAB , totalBMpg)
                    -(countEB/totalBMpg)*logBase2(countEB , totalBMpg);

            mpgProba.put("JA", countJA);
            mpgProba.put("JB", countJB);
            mpgProba.put("AA", countAA);
            mpgProba.put("AB", countAB);
            mpgProba.put("EA", countEA);
            mpgProba.put("EB", countEB);
            mpgProba.put("entropieA", entropieA);
            mpgProba.put("entropieB", entropieB);



            entropie.put(attributeName, ((countEA + countAA + countJA) / (totalAMpg + totalBMpg)) * entropieA
                    + ((countEB + countAB + countJB) / (totalAMpg + totalBMpg)) * entropieB);



            countJA = 0.0;
            countJB = 0.0;
            countAA = 0.0;
            countAB = 0.0;
            countEA = 0.0;
            countEB = 0.0;
            totalAMpg = 0.0;
            totalBMpg = 0.0;
            entropieA = 0.0;
            entropieB = 0.0;
        }

        return entropie;
    }


    public static Double logBase2(double numerateur, double denominateur) {
        if (numerateur == 0) return 0.0;
        return Math.log10(numerateur / denominateur) / Math.log10(2);
    }

}