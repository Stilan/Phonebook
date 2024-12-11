package com.example.phonebook;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[50];
        for(int i=0;i<array.length;i++){
            array[i]= random.nextInt(-50,50);
        }
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(sort(array)));

    }
    public static int[] sort(int[] array){
        int current;
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j+1]) {
                    current = array[j];
                    array[j] = array[j+1];
                    array[j+1] = current;
                }
            }
        }
        return array;
    }


}
