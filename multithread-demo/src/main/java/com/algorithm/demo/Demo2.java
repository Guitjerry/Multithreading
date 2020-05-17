package com.algorithm.demo;

/**
 * 给定m行n列的网格，有一个机器人从左上角（0，0）出发，每一步可以向下或向右走一部
 * 问有多少种不同的方式走到右下角
 * 步骤一: 确认状态
 * 最后，无论机器人以什么方式到达右下角，总有挪动的最后一步，向下或者向右
 * 状态就是F[i,j]为机器人有多少种方式从左上角走到(i,j)
 * 步骤二：确认最后一步
 *  右下角的左边可以设置为(m-1,n-1)
 *  那么机器人前一步是在(m-2,n-1)或者(m-1,n-2)
 * 步骤三: 确定子问题
 * 那么，如果机器有x种方式从左上角走到(m-2,n-1)，有Y种方式从左上角走到（m-1,n-2），
 * 那么总共就有x+y种方式从左上角走到（m-1,n-1）
 * 问题就可以转换成有多少种方式从左上角走到（m-2,n-1）或(m-1,n-2)
 * 步骤四：确认转移方程
 * 对于任意一个格子：
 * f[i][j] = f[i-1][j]+f[i][j-1]
 * 步骤五：初始条件和边界
 * f[0][0]=1
 * 边界情况：
 * 当i=0或j=0,则前一步只能有一个方向过来，f[i][j]=1
 *
 */
public class Demo2 {
    public static int unitPaths(int m, int n) {
        int f[][] = new int[m][n];
        int i, j;
        for (i = 0; i < m; ++i) {
            for (j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                } else {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }

        }
        return f[m - 1][n - 1];
    }

    public static void main(String[] args) {
       int count =  unitPaths(4,3);
       System.out.println(count);
    }

}
