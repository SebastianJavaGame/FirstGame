package com.mygdx.game;

/**
 * Created by Sebastian on 2017-07-28.
 */

public class BaseDialogs {
    //[npc][text][nextTex]//
    private static final int COUNT_NPC = 1;
    private static final int COUNT_HERO_TEXT_OPTION = 4;
    private static final int COUNT_ALL_TEXT = 6;
    //[npc][text][nextTex]//
    private static final String[][] TEXT = new String[COUNT_NPC][COUNT_ALL_TEXT];
    private static final int[][][] INDEX_OPTIONS = new int[COUNT_NPC][COUNT_HERO_TEXT_OPTION][];
    private static final int[][] INDEX_LISTENER = new int[COUNT_NPC][COUNT_HERO_TEXT_OPTION];

    public static final void loadNpcTextList(){
        //Npc #1
            //hero answer
        TEXT[0][0] = " Kim jetes?";
        TEXT[0][1] = " Co to za miejsce?";
        TEXT[0][2] = " Co ja tu robie?";
        TEXT[0][3] = " Ide dalej nie zatrzymuj mnie";
            //npc text
        TEXT[0][4] = " Witaj jestem Winthor, jestes na wyspie gdzie na każdym rogu czai sie niebezpieczenstwo muszisz byc czujny";
        TEXT[0][5] = " Jest to wyspa zla";
    }

    public static final void loadIndexOptions(){
        //Npc #1 to hero answer
        INDEX_OPTIONS[0][0] = new int[]{4, 1, 3};
        INDEX_OPTIONS[0][1] = new int[]{4, 2, 3};
        INDEX_OPTIONS[0][2] = new int[]{4, 0, 3};
        INDEX_OPTIONS[0][3] = new int[]{};
    }

    //info = 0
    //exit = 1
    //shop = 2
    //task = 3
    //replace = 4
    public static final void loadIndexListener(){
        //Npc #1 to hero answer
        INDEX_LISTENER[0][0] = 0;
        INDEX_LISTENER[0][1] = 0;
        INDEX_LISTENER[0][2] = 0;
        INDEX_LISTENER[0][3] = 1;
    }

    public static String getText(int indexFirst, int indexSecond){
        return TEXT[indexFirst][indexSecond];
    }

    public static int[] getIndexToNextText(int indexFirst, int indexSecond){
        return INDEX_OPTIONS[indexFirst][indexSecond];
    }

    public static int getIndexListener(int indexFirst, int indexSecond){
        return INDEX_LISTENER[indexFirst][indexSecond];
    }
}