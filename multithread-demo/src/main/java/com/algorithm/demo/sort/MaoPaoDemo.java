package com.algorithm.demo.sort;

import java.util.Arrays;

public class MaoPaoDemo {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};
        //第一趟排序，把最大的放到最后面
        int temp = 0;
        boolean flag = false;//标识变量，表示是否进行交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //在一趟排序中，一次交换都没有发生过
            if(!flag) {
                break;
            }else{
                flag = false;//重置flag，进行下次判断
            }
        }
        System.out.println("排序后.." + Arrays.toString(arr));
    }
}
