package com.tcwong;

import java.util.Arrays;

public class Bubble {


    public static void main(String[] args) {
        int[] a = {1,28,6,1,9,3,8,9,6,8,2,36,4,69,0,11,6695};
        sort(a);
    }

   static  void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            //大的往后冒
            for (int j = 0 ; j < a.length-1-i; j++) {
                if (a[j] >a[j+1]){
                    int tem = 0;
                    tem = a[j];
                    a[j] = a[j + 1];
                    a[j+1]=tem;
                }
            }
        }
       System.out.println(a[a.length-1]+a[a.length-2]);
        System.out.println(Arrays.toString(a));
    }

}
