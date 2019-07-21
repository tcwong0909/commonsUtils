package com.tcwong;

import java.util.Arrays;

public class Insertion {

    public static void main(String[] args) {
        int[] a = {1, 5, 6, 2, 88, 69, 33, 477, 22, 99, 7, 5};
        sort(a);
    }
    static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            int j = 0;
            for ( j = i - 1; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                }else {
                    break;
                }
            }
            a[j + 1] = value;
        }
        System.out.println(Arrays.toString(a));
    }
}
