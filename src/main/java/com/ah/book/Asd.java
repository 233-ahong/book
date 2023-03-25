package com.ah.book;

import java.util.Arrays;

public class Asd {
        public static void main(String[] args) {
            int[] arr = {5, 3, 8, 4, 2};
            bubbleSort(arr);
            System.err.println(Arrays.toString(arr));
        }

        public static void bubbleSort(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        // 交换相邻元素
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
}

