package com.mygdx.game;

/**
 * Created by Sebastian on 2017-07-28.
 */

public class BaseDialogs {
    //[npc][text][nextTex]//
    private static final int COUNT_NPC = 1;
    public static final int COUNT_HERO_TEXT_OPTION = 7;
    private static final int COUNT_ALL_TEXT = 13;
    //[npc][text][nextTex]//
    private static final String[][] TEXT = new String[COUNT_NPC][COUNT_ALL_TEXT];
    private static final int[][][] INDEX_OPTIONS = new int[COUNT_NPC][COUNT_HERO_TEXT_OPTION][];
    private static final int[][] INDEX_LISTENER = new int[COUNT_NPC][COUNT_HERO_TEXT_OPTION];

    public static final void loadNpcTextList(){
        //Npc #1
            //hero answer
        TEXT[0][0] = " Kim jetes?";
        TEXT[0][1] = " Co to za miejsce?";
        TEXT[0][2] = " Gdzie moge kupic bron?";
        TEXT[0][3] = " Ide dalej nie zatrzymuj mnie";
        TEXT[0][4] = " Pokaz mi co masz";
        TEXT[0][5] = " Potrzebuje zlota czy moglbym cos dla ciebie zrobic?";
        TEXT[0][6] = " Biore to zaraz wracam po nagrode";
            //npc text
        TEXT[0][7] = " Zaczekaj";
        TEXT[0][8] = " Witaj jestem Winthor, jestes na wyspie gdzie na każdym rogu czai sie niebezpieczenstwo muszisz byc czujny";
        TEXT[0][9] = " Jest to wyspa zla";
        TEXT[0][10] = " U mnie mam sklep";
        TEXT[0][11] = " Tak jest cos do zrobienie. Zabij 5 Glominow";
        TEXT[0][12] = " Juz ci zlecilem to zadanie wykonaj je a porozmawiamy o nagrodzie";
    }

    public static final void loadIndexOptions(){
        //Npc #1 to hero answer
        INDEX_OPTIONS[0][0] = new int[]{8, 1, 3};
        INDEX_OPTIONS[0][1] = new int[]{9, 2, 5, 3};
        INDEX_OPTIONS[0][2] = new int[]{10, 4, 3};
        INDEX_OPTIONS[0][3] = new int[]{};
        INDEX_OPTIONS[0][4] = new int[]{};
        INDEX_OPTIONS[0][5] = new int[]{11, 6, 3};
        INDEX_OPTIONS[0][6] = new int[]{};
    }

    /**
     * info = 0
     * exit = 1
     * shop = 2
     * task = 3
     * replace = 4
     */
    public static final void loadIndexListener(){
        //Npc #1 to hero answer
        INDEX_LISTENER[0][0] = 0;
        INDEX_LISTENER[0][1] = 0;
        INDEX_LISTENER[0][2] = 0;
        INDEX_LISTENER[0][3] = 1;
        INDEX_LISTENER[0][4] = 2;
        INDEX_LISTENER[0][5] = 0;
        INDEX_LISTENER[0][6] = 3;
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