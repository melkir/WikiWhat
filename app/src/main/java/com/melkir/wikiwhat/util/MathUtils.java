package com.melkir.wikiwhat.util;

public class MathUtils {
    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) throw new IllegalArgumentException("max must be greater than min");
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}
