package com.mygdx.game;

/**
 * Created by Sebastian on 2017-07-23.
 */

public class ExperienceRequired {
    private static final int[] EXPERIENCE = new int[13]; //new int[99]; = 100 levels, becouse first level is not recommend

    public static final void loadExperienceList(){
        EXPERIENCE[0] = 100;
        EXPERIENCE[1] = 200;
        EXPERIENCE[2] = 350;
        EXPERIENCE[3] = 600;
        EXPERIENCE[4] = 1000;
        EXPERIENCE[5] = 1500;
        EXPERIENCE[6] = 2300;
        EXPERIENCE[7] = 3400;
        EXPERIENCE[8] = 5700;
        EXPERIENCE[9] = 7000;
        EXPERIENCE[10] = 7000;
        EXPERIENCE[11] = 7000;
        EXPERIENCE[12] = 7000;
    }

    //return max experience to (index) level
    public static int getMaxExperience(int index) {
        return EXPERIENCE[index -1];
    }
}