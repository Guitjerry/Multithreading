package com.algorithm.demo;

/**
 * 动态规划算法
 * 需求:你有三枚硬币，分别为2元，5元，7元，每种硬币都有足够多
 * 买一本书需要27元
 * 如何用最少的硬币组合正好付清，不需对方再找
 * 步骤一:
 * 我们不知道最优策略是什么，但是最优策略肯定是K枚硬币a1,a2.....ak的和
 * 所以一定有最后一枚硬币k
 * 除掉这枚硬币，前面硬币的面值加起来的和是27-k
 * 确定最后一步:因为是最优策略，所以拼出的27-ak的硬币数一定要最少，否则就不是最优策略了
 * 子问题:所以我们将原问题转化成了一个子问题，而且规模更小27-ak
 * 我们设状态f(x) = 最少用多少枚硬币拼出X
 */
public class demo1 {
    //A{2,5,7} m 27
    public static int coinChange(int[] A, int M) {
        int[] f = new int[M + 1];
        f[0] = 0;
        int i, j;
        for (i = 1; i <= M; i++) {
            f[i] = Integer.MAX_VALUE;
            for (j = 0; j < A.length; ++j) {
                if (i >= A[j] && f[i - A[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i - A[j]] + 1, f[i]);
                }
            }
        }
        if (f[M] == Integer.MAX_VALUE) {
            f[M] = -1;
        }
        return f[M];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{2, 5, 7}, 27));
    }
}
