package org.example;

public class BitOperations {
    // Get bit value
    public int getBit(int number, int position) {
        return (number >> position) & 1;
    }

    // Set bit as true
    public int setBit(int number, int position) {
        return number | 1 << position;
    }

    // Set bit as false
    public int clearBit(int number, int position) {
        return number & ~(1 << position);
    }

    // Set bit as true / false
    public int updateBit(int number, int position ,  boolean newValue) {
        return (number & ~(1 << position)) | ((newValue ? 1 : 0) << position);
    }
}
