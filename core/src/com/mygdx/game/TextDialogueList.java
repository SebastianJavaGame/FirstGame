package com.mygdx.game;

/**
 * Created by Sebastian on 2017-07-28.
 */

public class TextDialogueList {
    //[npc][text][nextTex]//
    private static final String[][] TEXT = new String[1][5];
    private static final int[][][] INDEX_OPTIONS = new int[1][3][];

    public static final void loadNpcTextList(){
        //Npc #1
        TEXT[0][0] = " Kim jetem?";
        TEXT[0][1] = " Co to za miejsce?";
        TEXT[0][2] = " Co ja tu robie?";

        TEXT[0][3] = " Witaj jestem Winthor, jestes na wyspie gdzie na każdym rogu czai sie niebezpieczenstwo muszisz byc czujny";
        TEXT[0][4] = " Jest to wyspa zla";
    }

    public static final void loadIndexOptions(){
        //Npc #1
        INDEX_OPTIONS[0][0] = new int[]{4, 2};
        INDEX_OPTIONS[0][1] = new int[]{4, 1};
        INDEX_OPTIONS[0][2] = new int[]{4, 0};
    }

    public static String getText(int indexFirst, int indexSecond){
        return TEXT[indexFirst][indexSecond];
    }

    public static int[] getIndexToNextText(int indexFirst, int indexSecond){
        return INDEX_OPTIONS[indexFirst][indexSecond];
    }
}