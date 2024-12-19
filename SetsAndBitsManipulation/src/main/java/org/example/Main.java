package org.example;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<String> universe;
    private static List<SetOperations> sets;

    public static void main(String[] args) {
        try {
            createUniverse();
            createSets();
            runOperationsMenu();
        } catch (Exception e) {
            System.out.println("Program terminated due to error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void createUniverse() {
        System.out.println("Enter the size of the universe:");
        int size = Integer.parseInt(scanner.nextLine());
        if(size <= 0) throw new IllegalArgumentException("Universe size must be greater than 0");
        universe = new ArrayList<>();
        System.out.println("Enter " + size + " unique elements for the universe:");

        for (int i = 0; i < size; i++) {
            String element = scanner.nextLine().trim();
            if (universe.contains(element)) {
                System.out.println("Element already exists in universe. Please enter a unique element.");
                i--;
                continue;
            }
            universe.add(element);
        }

        System.out.println("Universe created: " + universe);
    }

    private static void createSets() {
        System.out.println("\nEnter the number of sets you want to create:");
        int numSets = Integer.parseInt(scanner.nextLine());
        if(numSets <= 0) throw new IllegalArgumentException("Number of sets must be greater than 0");
        sets = new ArrayList<>();

        for (int i = 0; i < numSets; i++) {
            System.out.println("\nCreating Set " + (i + 1));
            System.out.println("Enter the number of elements in this set:");
            int setSize = Integer.parseInt(scanner.nextLine());

            SetOperations set = new SetOperations(universe);
            System.out.println("Enter " + setSize + " elements (must be from the universe):");

            for (int j = 0; j < setSize; j++) {
                try {
                    String element = scanner.nextLine().trim();
                    set.addElement(element);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid element. Must be from universe: " + universe);
                    j--;
                }
            }
            sets.add(set);
            System.out.println("Set " + (i + 1) + " created: " + set);
        }
    }

    private static void runOperationsMenu() {
        while (true) {
            printMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    System.out.println("Program terminated.");
                    break;
                }
                processChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    // Our main menu options
    private static void printMenu() {
        System.out.println("\nSet Operations Menu:");
        System.out.println("1. Union of two sets");
        System.out.println("2. Intersection of two sets");
        System.out.println("3. Complement of a set");
        System.out.println("4. Difference between two sets");
        System.out.println("5. Cardinality of a set");
        System.out.println("6. Print a set");
        System.out.println("0. Exit");
        System.out.println("Enter your choice:");
    }
    // Our main menu functions
    private static void processChoice(int choice) {
        switch (choice) {
            case 1 -> performUnion();
            case 2 -> performIntersection();
            case 3 -> performComplement();
            case 4 -> performDifference();
            case 5 -> showCardinality();
            case 6 -> printSet();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private static int selectSet(String message) {
        System.out.println(message);
        for (int i = 0; i < sets.size(); i++) {
            System.out.println((i + 1) + ". " + sets.get(i));
        }
        int selection = Integer.parseInt(scanner.nextLine()) - 1;
        if (selection < 0 || selection >= sets.size()) {
            throw new IllegalArgumentException("Invalid set number");
        }
        return selection;
    }

    private static void performUnion() {
        int set1Index = selectSet("Select first set:");
        int set2Index = selectSet("Select second set:");
        SetOperations result = sets.get(set1Index).union(sets.get(set2Index));
        System.out.println("Union result: " + result);
    }

    private static void performIntersection() {
        int set1Index = selectSet("Select first set:");
        int set2Index = selectSet("Select second set:");
        SetOperations result = sets.get(set1Index).intersection(sets.get(set2Index));
        System.out.println("Intersection result: " + result);
    }

    private static void performComplement() {
        int setIndex = selectSet("Select a set:");
        SetOperations result = sets.get(setIndex).complement();
        System.out.println("Complement result: " + result);
    }

    private static void performDifference() {
        int set1Index = selectSet("Select first set:");
        int set2Index = selectSet("Select second set:");
        SetOperations result = sets.get(set1Index).difference(sets.get(set2Index));
        System.out.println("Difference result: " + result);
    }

    private static void showCardinality() {
        int setIndex = selectSet("Select a set:");
        System.out.println("Cardinality: " + sets.get(setIndex).getCardinality());
    }

    private static void printSet() {
        int setIndex = selectSet("Select a set to print:");
        System.out.println("Set: " + sets.get(setIndex));
    }
}