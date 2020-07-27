package com.algorithm.demo.duigui;

public class MiGong {
    public static void main(String[] args) {
        //初始化一个迷宫
        int[][] map = new int[8][7];
        //使用1表示墙，上下置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }


        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //给小球找路
        setWay(map, 1, 1);
        System.out.println("小球走过并标识过的路径");
        //输出新的地图，小球走过并标识过的递归
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 1.map表示地图
     * 2. i,j 表示地图的哪个位置开始出发
     * 3.如果小球能到map[6][5]位置，则说明通路找到
     * 4.约定，当map[i][j]为0表示该点没有走过，当为1表示墙，2表示通路可以走，3表示该点已经走过，但是走不通
     * 5.在走迷宫时，需要确定一个策略（方法）下-右-上-左
     *
     * @param map 表示地图
     * @param i   表示从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回true,否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            //通路找到
            return true;
        } else {
            if (map[i][j] == 0) {
                //当前点还未走过,按照策略下-右-上-左
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向下走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向右走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //说明该点时走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //map[i][j] != 0,可能是1，2，3
                return false;
            }
        }
    }
}
