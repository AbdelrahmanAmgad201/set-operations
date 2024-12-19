package org.example;

import java.util.ArrayList;
import java.util.List;

public class SetOperations {
    private final BitOperations bitOperations;
    private final List<String> universe;
    private int bitRepresentation;

    // Constructor
    public SetOperations(List<String> universe) {
        if (universe == null || universe.isEmpty()) {
            throw new IllegalArgumentException("Universe cant be null");
        }
        this.universe = new ArrayList<>(universe);
        this.bitOperations = new BitOperations();
        this.bitRepresentation = 0;
    }
    // We set corresponding bit to true
    public void addElement(String element) {
        int position = universe.indexOf(element);
        if (position == -1) {
            throw new IllegalArgumentException("Element not in universe: ");
        }
        bitRepresentation = bitOperations.setBit(bitRepresentation, position);
    }
    // We set corresponding bit to false
    public void removeElement(String element) {
        int position = universe.indexOf(element);
        if (position == -1) {
            throw new IllegalArgumentException("Element not in universe: ");
        }
        bitRepresentation = bitOperations.clearBit(bitRepresentation, position);
    }
    // We map the bit representation to its String representation
    public List<String> getElements() {
        List<String> elements = new ArrayList<>();
        for (int i = 0; i < universe.size(); i++) {
            if (bitOperations.getBit(bitRepresentation, i) == 1) {
                elements.add(universe.get(i));
            }
        }
        return elements;
    }
    // Return Set size
    public int getCardinality() {
        int count = 0;
        for (int i = 0; i < universe.size(); i++) {
            if (bitOperations.getBit(bitRepresentation, i) == 1) {
                count++;
            }
        }
        return count;
    }
    // Using bitwise OR
    public SetOperations union(SetOperations other) {
        validateSameUniverse(other);
        SetOperations result = new SetOperations(universe);
        result.bitRepresentation = this.bitRepresentation | other.bitRepresentation;
        return result;
    }
    // Using bitwise And
    public SetOperations intersection(SetOperations other) {
        validateSameUniverse(other);
        SetOperations result = new SetOperations(universe);
        result.bitRepresentation = this.bitRepresentation & other.bitRepresentation;
        return result;
    }
    // Using bitwise not
    public SetOperations complement() {
        SetOperations result = new SetOperations(universe);
        // Make all fields = 1
        int mask = (1 << universe.size()) - 1;
        result.bitRepresentation = ~this.bitRepresentation & mask;
        return result;
    }
    public SetOperations difference(SetOperations other) {
        validateSameUniverse(other);
        SetOperations result = new SetOperations(universe);
        result.bitRepresentation = this.bitRepresentation & ~other.bitRepresentation;
        return result;
    }

    public boolean contains(String element) {
        int position = universe.indexOf(element);
        if (position == -1) {
            throw new IllegalArgumentException("Element not in universe: ");
        }
        return bitOperations.getBit(bitRepresentation, position) == 1;
    }

    public int getBitRepresentation() {
        return bitRepresentation;
    }

    private void validateSameUniverse(SetOperations other) {
        if (!this.universe.equals(other.universe)) {
            throw new IllegalArgumentException("Sets must share the same universe");
        }
    }

    @Override
    public String toString() {
        return "Set{elements=" + getElements() + "}";
    }
}