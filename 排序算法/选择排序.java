package com.tcwong;

import java.util.Arrays;

public class Selection {
    public static void main(String[] args) {
        int[] a = {5, 9, 8, 6, 36, 48, 12, 96, 123, 4, 3, 8};
        sort(a);
    }

    static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min=i;
            for (int j = i + 1; j < a.length ; j++) {

                if (a[min] > a[j]) {
                    min=j;
                }
            }
            int temp = a[i];
            a[i] = a[min];
            a[min]=temp;
        }
        System.out.println(Arrays.toString(a));
    }
}
