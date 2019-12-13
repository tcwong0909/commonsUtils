package com.tcwong;

import java.util.Arrays;

public class Shell {

    public static void main(String[] args) {
        int[] a = {1, 5, 6, 9, 8, 3, 25, 8, 99, 633, 54, 88, 222, 444, 888, 4};
        sort(a);
    }

    static void sort(int[] a) {
        int length = a.length;
        int gap = 1;
        while (gap < length) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < length; i++) {
                int temp = a[i];
                int j = i - gap;
                while (j >= 0 && a[j] > temp) {
                    a[j + gap] = a[j];
                    j -= gap;
                }
                a[j + gap] = temp;
            }
            gap = gap / 3;
        }
        System.out.println(Arrays.toString(a));
    }
}
