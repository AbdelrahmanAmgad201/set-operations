package org.example;

public class BitOperations {
    public int getBit(int number, int position) {
        return (number >> position) & 1;
    }

    public int setBit(int number, int position) {
        return number | 1 << position;
    }

    public int clearBit(int number, int position) {
        return number & ~(1 << position);
    }

    public int updateBit(int number, int position ,  boolean newValue) {
        return (number & ~(1 << position)) | ((newValue ? 1 : 0) << position);
    }
}
