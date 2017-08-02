package com.mygdx.game;

/**
 * Created by Sebastian on 2017-08-01.
 */

public class BaseTask {
    public static final int COUNT_TASKS = 4;
    private static final String[][] LIST_TASKS = new String[COUNT_TASKS][2];
    private static final int[] LIST_PROGRESS = new int[COUNT_TASKS];

    public static void loadAllTasks(){
        //task #1
        LIST_TASKS[0][0] = "Winthor";
        LIST_TASKS[0][1] = "Zabij 5 Glominow";

        LIST_PROGRESS[0] = 5;

        //task #2
        LIST_TASKS[1][0] = "Mag Olaf";
        LIST_TASKS[1][1] = "Zabij 15 Czarnogowych jezdzcow";

        LIST_PROGRESS[1] = 15;

        //task #3
        LIST_TASKS[2][0] = "Krasnolud Eraf";
        LIST_TASKS[2][1] = "Przynies mi informacje na temat czarnej listy";

        LIST_PROGRESS[2] = 1;

        //task #4
        LIST_TASKS[3][0] = "Elf Moklen";
        LIST_TASKS[3][1] = "Zabij przywodce orkow 'Orka Czarnokrwistego'";

        LIST_PROGRESS[3] = 1;
        //task #5
    }

    public static String getNpcName(int idTask){
        return LIST_TASKS[idTask][0];
    }

    public static String getTarget(int idTask){
        return LIST_TASKS[idTask][1];
    }

    public static int getProgressMax(int idTask){
        return LIST_PROGRESS[idTask];
    }
}
