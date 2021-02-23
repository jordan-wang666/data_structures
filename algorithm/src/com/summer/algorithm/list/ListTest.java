package com.summer.algorithm.list;

import java.util.Arrays;
import java.util.Random;

/**
 * 表的扩容练习
 *
 * @author Jordan
 */
public class ListTest {
    public static void main(String[] args) {
        int[] arr = new int[10];
        // 赋值
        for (int i=0;i<arr.length;i++) {
            Random random = new Random();
            arr[i] = random.nextInt(10);
        }

        // 扩容
        int[] newArr = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }

        arr = newArr;
        System.out.println(Arrays.toString(arr));
    }
}
