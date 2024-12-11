package com.example.phonebook;

import java.util.Random;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Random random = new Random();
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int q = 0; q < N; q++) {
                 arr[i][q] = random.nextInt(0, 10);
            }
        }

        int min = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            if (min > arr[arr.length - 1 - i][i]) {
                min = arr[arr.length - 1 - i][i];
            }
        }
        System.out.println(min);
    }
}
