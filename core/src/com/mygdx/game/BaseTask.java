package com.mygdx.game;

/**
 * Created by Sebastian on 2017-08-01.
 */

public class BaseTask {
    //[idTask][
    public static final int COUNT_TASKS = 14;
    private static final String[][] LIST_TASKS = new String[COUNT_TASKS][3];
    private static final int[][] REWARD_TASK = new int[COUNT_TASKS][2];
    private static final int[] LIST_PROGRESS = new int[COUNT_TASKS];
    private static final boolean[] TASK_COMPLETE = new boolean[COUNT_TASKS];

    public static void loadAllTasks(){
        //load default setting
        for(boolean b: TASK_COMPLETE)
            b = false;

        //Task dodajemy do npc w konstruktorze
        //task #1
        LIST_TASKS[0][0] = "Herald";
        LIST_TASKS[0][1] = "Zabij 5 Grykonów";
        LIST_TASKS[0][2] = "Grykon";
        REWARD_TASK[0][0] = 650;
        REWARD_TASK[0][1] = 1000;

        LIST_PROGRESS[0] = 5;

        //task #2
        LIST_TASKS[1][0] = "Lars";
        LIST_TASKS[1][1] = "Zabij 6 Ognistych wilków";
        LIST_TASKS[1][2] = "Ognisty wilk";
        REWARD_TASK[1][0] = 950;
        REWARD_TASK[1][1] = 1600;

        LIST_PROGRESS[1] = 6;

        //task #3
        LIST_TASKS[2][0] = "Marsel";
        LIST_TASKS[2][1] = "Zabij 4 wilkołaków";
        LIST_TASKS[2][2] = "Wilkołak";
        REWARD_TASK[2][0] = 1650;
        REWARD_TASK[2][1] = 2100;

        LIST_PROGRESS[2] = 4;

        //task #4
        LIST_TASKS[3][0] = "Adar";
        LIST_TASKS[3][1] = "Zabij 5 białych tarantul";
        LIST_TASKS[3][2] = "Biała tarantula";
        REWARD_TASK[3][0] = 2050;
        REWARD_TASK[3][1] = 3000;

        LIST_PROGRESS[3] = 5;
        //task #5
        LIST_TASKS[4][0] = "Trumv";
        LIST_TASKS[4][1] = "Zabij 4 Wachlaczy";
        LIST_TASKS[4][2] = "Wachlacz";
        REWARD_TASK[4][0] = 2050;
        REWARD_TASK[4][1] = 3000;

        LIST_PROGRESS[4] = 4;
        //task #6
        LIST_TASKS[5][0] = "Jaris";
        LIST_TASKS[5][1] = "Zabij 6 Skalniaków";
        LIST_TASKS[5][2] = "Skalniak";
        REWARD_TASK[5][0] = 2050;
        REWARD_TASK[5][1] = 3000;

        LIST_PROGRESS[5] = 6;
        //task #7
        LIST_TASKS[6][0] = "Tumvill";
        LIST_TASKS[6][1] = "Zabij 7 Dinotopów";
        LIST_TASKS[6][2] = "Dinotop";
        REWARD_TASK[6][0] = 2050;
        REWARD_TASK[6][1] = 3000;

        LIST_PROGRESS[6] = 7;
        //task #8
        LIST_TASKS[7][0] = "Simza";
        LIST_TASKS[7][1] = "Zabij 5 Bevterów";
        LIST_TASKS[7][2] = "Bevter";
        REWARD_TASK[7][0] = 2050;
        REWARD_TASK[7][1] = 3000;

        LIST_PROGRESS[7] = 5;
        //task #9
        LIST_TASKS[8][0] = "Tahar";
        LIST_TASKS[8][1] = "Zabij 8 Kratherów";
        LIST_TASKS[8][2] = "Krather";
        REWARD_TASK[8][0] = 2050;
        REWARD_TASK[8][1] = 3000;

        LIST_PROGRESS[8] = 8;
        //task #10
        LIST_TASKS[9][0] = "Flegrum";
        LIST_TASKS[9][1] = "Zabij 7 Cybrisów";
        LIST_TASKS[9][2] = "Cybris";
        REWARD_TASK[9][0] = 2050;
        REWARD_TASK[9][1] = 3000;

        LIST_PROGRESS[9] = 7;
        //task #11
        LIST_TASKS[10][0] = "Alwas";
        LIST_TASKS[10][1] = "Zabij 5 Upadłych";
        LIST_TASKS[10][2] = "Upadły";
        REWARD_TASK[10][0] = 2050;
        REWARD_TASK[10][1] = 3000;

        LIST_PROGRESS[10] = 5;
        //task #12
        LIST_TASKS[11][0] = "Miranda";
        LIST_TASKS[11][1] = "Zabij 4 Diabeuze";
        LIST_TASKS[11][2] = "Diabeuza";
        REWARD_TASK[11][0] = 2050;
        REWARD_TASK[11][1] = 3000;

        LIST_PROGRESS[11] = 4;
        //task #13
        LIST_TASKS[12][0] = "Bertrand";
        LIST_TASKS[12][1] = "Zabij 7 Hydr";
        LIST_TASKS[12][2] = "Hydra";
        REWARD_TASK[12][0] = 2050;
        REWARD_TASK[12][1] = 3000;

        LIST_PROGRESS[12] = 7;
        //task #14
        LIST_TASKS[13][0] = "Nevil";
        LIST_TASKS[13][1] = "Zabij 3 Xantessy";
        LIST_TASKS[13][2] = "Xantes";
        REWARD_TASK[13][0] = 2050;
        REWARD_TASK[13][1] = 3000;

        LIST_PROGRESS[13] = 3;

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
        TASK_COMPLETE[idTask] = isComplete;
    }
}
