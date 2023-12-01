package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        int[] array = generateRandomArray(10);
        System.out.println("Original array: " + Arrays.toString(array));

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ParallelMergeSort task = new ParallelMergeSort(array, 0, array.length - 1);

        forkJoinPool.invoke(task);

        System.out.println("Sorted array: " + Arrays.toString(array));
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100); // Генерация случайных чисел от 0 до 99
        }
        return array;
    }
}
