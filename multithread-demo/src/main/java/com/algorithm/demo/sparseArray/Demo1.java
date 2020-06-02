package com.algorithm.demo.sparseArray;

public class Demo1 {
    public static void main(String[] args) {
        int array[][] = new int[11][11];
        array[1][2] = 1;
        array[2][3] = 2;

        for(int[] array1:array ){
            for(int str:array1){
                System.out.printf("%d\t",str);
            }
            System.out.println();
        }
        System.out.println();
        int sum = 0;

        //将数组转为稀疏数组
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(array[i][j]>0){
                    sum++;
                }
            }
        }

        int[][] sparseArr =  new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int count =0;//用于记录是第几个非0数据
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(array[i][j]>0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = array[i][j];
                }
            }
        }
        for(int[] arr:sparseArr ){
            for(int str:arr){
                System.out.printf("%d\t",str);
            }
            System.out.println();
        }

        System.out.println();
        //稀疏数组转为原始数组
        int row = sparseArr[0][0];
        int col = sparseArr[0][1];
        int initArray[][] = new int[row][col];
        for(int i=1;i<sparseArr.length;i++){
            int rowIndex = sparseArr[i][0];
            int colIndex = sparseArr[i][1];
            int value = sparseArr[i][2];
            initArray[rowIndex][colIndex] = value;
        }
        for(int[] arr:initArray ){
            for(int str:arr){
                System.out.printf("%d\t",str);
            }
            System.out.println();
        }

    }
}
