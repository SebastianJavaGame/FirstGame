package com.mygdx.game;

/**
 * Created by Sebastian on 2017-08-04.
 */

public class BaseEnemyAI {
    //[HIT_TYPE][POINT_ENEMY][COUNT_OPTIONS][4_SETTING]
    private static final int[][][][] AI = new int[3][8][4][];

    public static void loadAI(){
        //Defense
            //point = 5
            AI[0][0][0] = new int[]{1, 2, 1, 1};
            AI[0][0][1] = new int[]{1, 2, 0, 2};
            AI[0][0][2] = new int[]{1, 3, 0, 1};

            //point = 6
            AI[0][1][0] = new int[]{1, 3, 1, 1};
            AI[0][1][1] = new int[]{1, 2, 1, 2};
            AI[0][1][2] = new int[]{1, 1, 1, 3};

            //point = 7
            AI[0][2][0] = new int[]{1, 3, 1, 2};
            AI[0][2][1] = new int[]{1, 2, 1, 3};
            AI[0][2][2] = new int[]{1, 4, 1, 1};

            //point = 8
            AI[0][3][0] = new int[]{1, 3, 1, 3};
            AI[0][3][1] = new int[]{2, 3, 1, 2};
            AI[0][3][2] = new int[]{1, 4, 1, 2};

            //point = 9
            AI[0][4][0] = new int[]{1,4 ,1 ,3};
            AI[0][4][1] = new int[]{1, 3, 1, 4};
            AI[0][4][2] = new int[]{1, 3, 2, 3};
            AI[0][4][3] = new int[]{2, 3, 1, 3};

            //point = 10
            AI[0][5][0] = new int[]{1, 4, 1, 4};
            AI[0][5][1] = new int[]{1, 5, 1, 3};
            AI[0][5][2] = new int[]{2, 4, 1, 3};
            AI[0][5][3] = new int[]{2, 3, 2, 3};


        //Normal
            //point = 5
            AI[1][0][0] = new int[]{2, 1, 1, 1};
            AI[1][0][1] = new int[]{1, 2, 1, 1};
            AI[1][0][2] = new int[]{1, 1, 2, 1};

            //point = 6
            AI[1][1][0] = new int[]{2, 1, 2, 1};
            AI[1][1][1] = new int[]{2, 2, 1, 1};
            AI[1][1][2] = new int[]{1, 1, 2, 2};

            //point = 7
            AI[1][2][0] = new int[]{3, 1, 2, 1};
            AI[1][2][1] = new int[]{2, 2, 2, 1};
            AI[1][2][2] = new int[]{2, 1, 2, 2};

            //point = 8
            AI[1][3][0] = new int[]{2, 2, 2, 2};
            AI[1][3][1] = new int[]{3, 2, 2, 1};
            AI[1][3][2] = new int[]{3, 1, 2, 2};

            //point = 9
            AI[1][4][0] = new int[]{2, 2, 3, 2};
            AI[1][4][1] = new int[]{2, 2, 2, 3};
            AI[1][4][2] = new int[]{3, 2, 2, 2};
            AI[1][4][3] = new int[]{2, 3, 2, 2};

            //point = 10
            AI[1][5][0] = new int[]{3, 2, 3, 2};
            AI[1][5][1] = new int[]{1, 5, 1, 3};
            AI[1][5][2] = new int[]{2, 4, 1, 3};
            AI[1][5][3] = new int[]{2, 3, 2, 3};

        //Attack
            //point = 5
            AI[2][0][0] = new int[]{3, 1, 1, 0};
            AI[2][0][1] = new int[]{2, 1, 2, 0};
            AI[2][0][2] = new int[]{3, 0, 2, 0};

            //point = 6
            AI[2][1][0] = new int[]{3, 1, 1, 1};
            AI[2][1][1] = new int[]{3, 1, 2, 0};
            AI[2][1][2] = new int[]{3, 0, 2, 1};

            //point = 7
            AI[2][2][0] = new int[]{3, 1, 2, 1};
            AI[2][2][1] = new int[]{4, 1, 2, 0};
            AI[2][2][2] = new int[]{4, 1, 3, 0};

            //point = 8
            AI[2][3][0] = new int[]{4, 1, 2, 1};
            AI[2][3][1] = new int[]{3, 1, 3, 1};
            AI[2][3][2] = new int[]{4, 1, 3, 0};

            //point = 9
            AI[2][4][0] = new int[]{4, 1, 3, 1};
            AI[2][4][1] = new int[]{3, 1, 4, 1};
            AI[2][4][2] = new int[]{5, 1, 2, 1};
            AI[2][4][3] = new int[]{4, 2, 2, 1};

            //point = 10
            AI[2][5][0] = new int[]{4, 1, 4, 1};
            AI[2][5][1] = new int[]{5, 1, 3, 1};
            AI[2][5][2] = new int[]{3, 1, 5, 1};
            AI[2][5][3] = new int[]{4, 2, 3, 1};
    }

    public static int[] getEnemyAIPoint(int type, int freePoint, int combination){
        return AI[type][freePoint -5][combination];
    }
}
