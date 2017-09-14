package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Sebastian on 2017-08-01.
 */

public class BaseTask {
    //[idTask][
    public static final int COUNT_TASKS = 12;
    private static final String[][] LIST_TASKS = new String[COUNT_TASKS][3];
    private static final int[][] REWARD_TASK = new int[COUNT_TASKS][2];
    private static final int[] LIST_PROGRESS = new int[COUNT_TASKS];
    private static final boolean[] TASK_COMPLETE = new boolean[COUNT_TASKS];
    private static final String[] ENEMIES = new String[COUNT_TASKS];

    public static void loadAllTasks(){
        //load default setting
        for(boolean b: TASK_COMPLETE)
            b = false;

        //Task dodajemy do npc w konstruktorze
        //task #1
        LIST_TASKS[0][0] = "Herald";
        LIST_TASKS[0][1] = "Zabij 5 Grykonów";
        LIST_TASKS[0][2] = "Grykon";
        REWARD_TASK[0][0] = 2390;
        REWARD_TASK[0][1] = 1150;

        LIST_PROGRESS[0] = 5;
        ENEMIES[0] = "enemy/map2/p3.png";

        //task #2
        LIST_TASKS[1][0] = "Lars";
        LIST_TASKS[1][1] = "Zabij 6 Ognistych wilków";
        LIST_TASKS[1][2] = "Ognisty wilk";
        REWARD_TASK[1][0] = 5800;
        REWARD_TASK[1][1] = 1950;

        LIST_PROGRESS[1] = 6;
        ENEMIES[1] = "enemy/map2/p5.png";

        //task #3
        LIST_TASKS[2][0] = "Adar";
        LIST_TASKS[2][1] = "Zabij 5 Białych tarantul";
        LIST_TASKS[2][2] = "Biała tarantula";
        REWARD_TASK[2][0] = 9600;
        REWARD_TASK[2][1] = 3000;

        LIST_PROGRESS[2] = 5;
        ENEMIES[2] = "enemy/map3/p2.png";

        //task #4
        LIST_TASKS[3][0] = "Trumv";
        LIST_TASKS[3][1] = "Zabij 4 Wachlaczy";
        LIST_TASKS[3][2] = "Wachlacz";
        REWARD_TASK[3][0] = 12000;
        REWARD_TASK[3][1] = 3050;

        LIST_PROGRESS[3] = 4;
        ENEMIES[3] = "enemy/map3/p4.png";

        //task #5
        LIST_TASKS[4][0] = "Tumvill";
        LIST_TASKS[4][1] = "Zabij 7 Dinotopów";
        LIST_TASKS[4][2] = "Dinotop";
        REWARD_TASK[4][0] = 30000;
        REWARD_TASK[4][1] = 7300;

        LIST_PROGRESS[4] = 7;
        ENEMIES[4] = "enemy/map4/p3.png";

        //task #6
        LIST_TASKS[5][0] = "Simza";
        LIST_TASKS[5][1] = "Zabij 5 Bevterów";
        LIST_TASKS[5][2] = "Bevter";
        REWARD_TASK[5][0] = 25000;
        REWARD_TASK[5][1] = 6150;

        LIST_PROGRESS[5] = 5;
        ENEMIES[5] = "enemy/map4/p5.png";

        //task #7
        LIST_TASKS[6][0] = "Tahar";
        LIST_TASKS[6][1] = "Zabij 8 Kratherów";
        LIST_TASKS[6][2] = "Krather";
        REWARD_TASK[6][0] = 50000;
        REWARD_TASK[6][1] = 13280;

        LIST_PROGRESS[6] = 8;
        ENEMIES[6] = "enemy/map4/p9.png";

        //task #8
        LIST_TASKS[7][0] = "Flegrum";
        LIST_TASKS[7][1] = "Zabij 7 Cybrisów";
        LIST_TASKS[7][2] = "Cybris";
        REWARD_TASK[7][0] = 60000;
        REWARD_TASK[7][1] = 15150;

        LIST_PROGRESS[7] = 7;
        ENEMIES[7] = "enemy/map5/p5.png";

        //task #9
        LIST_TASKS[8][0] = "Alwas";
        LIST_TASKS[8][1] = "Zabij 5 Upadłych";
        LIST_TASKS[8][2] = "Upadły";
        REWARD_TASK[8][0] = 55000;
        REWARD_TASK[8][1] = 13800;

        LIST_PROGRESS[8] = 5;
        ENEMIES[8] = "enemy/map5/p7.png";

        //task #10
        LIST_TASKS[9][0] = "Miranda";
        LIST_TASKS[9][1] = "Zabij 4 Diabeuze";
        LIST_TASKS[9][2] = "Diabeuza";
        REWARD_TASK[9][0] = 45000;
        REWARD_TASK[9][1] = 14500;

        LIST_PROGRESS[9] = 4;
        ENEMIES[9] = "enemy/map5/p10.png";

        //task #11
        LIST_TASKS[10][0] = "Bertrand";
        LIST_TASKS[10][1] = "Zabij 7 Hydr";
        LIST_TASKS[10][2] = "Hydra";
        REWARD_TASK[10][0] = 90000;
        REWARD_TASK[10][1] = 26750;

        LIST_PROGRESS[10] = 7;
        ENEMIES[10] = "enemy/map6/p3.png";

        //task #12
        LIST_TASKS[11][0] = "Nevil";
        LIST_TASKS[11][1] = "Zabij 3 Xantessy";
        LIST_TASKS[11][2] = "Xantes";
        REWARD_TASK[11][0] = 65000;
        REWARD_TASK[11][1] = 21000;

        LIST_PROGRESS[11] = 3;
        ENEMIES[11] = "enemy/map6/p8.png";
    }

    public static String getNpcName(int idTask){
        return LIST_TASKS[idTask][0];
    }

    public static String getTarget(int idTask){
        return LIST_TASKS[idTask][1];
    }

    public static String getTargetName(int idTask){
        return LIST_TASKS[idTask][2];
    }

    public static String getPathTexture(int idTask){
        return ENEMIES[idTask];
    }

    public static int getProgressMax(int idTask){
        return LIST_PROGRESS[idTask];
    }

    public static int getRewardExperience(int idTask){
        return REWARD_TASK[idTask][0];
    }

    public static int getRewardMoney(int idTask){
        return REWARD_TASK[idTask][1];
    }

    public static boolean isComplete(int idTask){
        return TASK_COMPLETE[idTask];
    }

    public static void setTaskComplete(int idTask, boolean isComplete){
        if(idTask < COUNT_TASKS)
             TASK_COMPLETE[idTask] = isComplete;
    }
}
