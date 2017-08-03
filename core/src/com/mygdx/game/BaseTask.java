package com.mygdx.game;

/**
 * Created by Sebastian on 2017-08-01.
 */

public class BaseTask {
    public static final int COUNT_TASKS = 4;
    private static final String[][] LIST_TASKS = new String[COUNT_TASKS][3];
    private static final int[][] REWARD_TASK = new int[COUNT_TASKS][2];
    private static final int[] LIST_PROGRESS = new int[COUNT_TASKS];

    public static void loadAllTasks(){
        //Task dodajemy do npc w konstruktorze
        //task #1
        LIST_TASKS[0][0] = "Winthor";
        LIST_TASKS[0][1] = "Zabij 5 Glominow";
        LIST_TASKS[0][2] = "glomin";
        REWARD_TASK[0][0] = 650;
        REWARD_TASK[0][1] = 1000;

        LIST_PROGRESS[0] = 2;

        //task #2
        LIST_TASKS[1][0] = "Mag Olaf";
        LIST_TASKS[1][1] = "Zabij 15 Czarnogowych jezdzcow";
        LIST_TASKS[1][2] = "czarnogowych jezdzcow";
        REWARD_TASK[1][0] = 950;
        REWARD_TASK[1][1] = 1600;

        LIST_PROGRESS[1] = 15;

        //task #3
        LIST_TASKS[2][0] = "Krasnolud Eraf";
        LIST_TASKS[2][1] = "Przynies mi informacje na temat czarnej listy";
        LIST_TASKS[2][2] = "czarna lista";
        REWARD_TASK[2][0] = 1650;
        REWARD_TASK[2][1] = 2100;

        LIST_PROGRESS[2] = 1;

        //task #4
        LIST_TASKS[3][0] = "Elf Moklen";
        LIST_TASKS[3][1] = "Zabij przywodce orkow 'Orka Czarnokrwistego'";
        LIST_TASKS[3][2] = "ork czarnokrwisty";
        REWARD_TASK[3][0] = 2050;
        REWARD_TASK[3][1] = 3000;

        LIST_PROGRESS[3] = 1;
        //task #5
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
}
