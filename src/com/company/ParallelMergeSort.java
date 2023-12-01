package com.company;

import java.util.concurrent.RecursiveAction;

class ParallelMergeSort extends RecursiveAction {
    private final int[] array;
    private final int low;
    private final int high;

    public ParallelMergeSort(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        if (low < high) {
            int middle = (low + high) / 2;

            ParallelMergeSort leftTask = new ParallelMergeSort(array, low, middle);
            ParallelMergeSort rightTask = new ParallelMergeSort(array, middle + 1, high);

            invokeAll(leftTask, rightTask);

            merge(middle);
        }
    }

    private void merge(int middle) {
        int leftSize = middle - low + 1;
        int rightSize = high - middle;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        System.arraycopy(array, low, leftArray, 0, leftSize);
        System.arraycopy(array, middle + 1, rightArray, 0, rightSize);

        int i = 0, j = 0, k = low;

        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}