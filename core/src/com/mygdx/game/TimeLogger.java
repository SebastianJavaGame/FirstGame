package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.util.LinkedHashMap;

public class TimeLogger
{
    public static boolean log = false;
    public static long gatherFor = 1; // how many frames before logging
    public static long starttime;
    public static long gatherStart;
    private static LinkedHashMap<String, Long> times = new LinkedHashMap<String, Long>();
    private static LinkedHashMap<String, Long> counts = new LinkedHashMap<String, Long>();
    private static long currentFrame = 0;
    public static int nrLog = 0;

    public static void timeStart()
    {
        if(log)
        {
            if(gatherFor==1)
            {
                starttime = System.currentTimeMillis();
                Gdx.app.log("timeLog", "start."+starttime);
            }
            else
            {
                gatherStart();
                starttime = System.currentTimeMillis();
            }
        }
    }

    public static void gatherEventCount(String str)
    {
        String nrLogStr = "COUNT . ";
        if(times.containsKey(nrLogStr+str))
        {
            times.put(nrLogStr+str, times.get(nrLogStr+str)+1);
        }
        else
        {
            times.put(nrLogStr+str, 1l);
        }
    }

    public static void timeLog(String str)
    {
        if(log)
        {
            if(gatherFor==1)
                Gdx.app.log("timeLog", str+":"+(System.currentTimeMillis()-starttime));
            else
                gatherLog(str);
        }
    }

    public static void gatherLog(String str)
    {
        long time = System.currentTimeMillis()-starttime;
        String nrLogStr = ""+nrLog+" . ";
        if(nrLog<10)
            nrLogStr = "0"+nrLog+" . ";
        if(times.containsKey(nrLogStr+str))
        {
            times.put(nrLogStr+str, times.get(nrLogStr+str)+time);
        }
        else
        {
            times.put(nrLogStr+str, time);
        }
        starttime = System.currentTimeMillis();
        nrLog++;
    }

    public static void gatherPrintSummary()
    {
        float now = (System.currentTimeMillis()-gatherStart)/(float)gatherFor;
        Gdx.app.log("timeLog", "*** AVERAGE FRAME: "+now);
        float sum = 0;
        for(String key : times.keySet())
        {
            if(key.startsWith("COUNT"))
            {
                Gdx.app.log("timeLog", key+": "+times.get(key)/(float)gatherFor);
            }
            else
            {
                Gdx.app.log("timeLog", key+": "+times.get(key)/(float)gatherFor);
                sum+=times.get(key)/(float)gatherFor;
            }
        }
        Gdx.app.log("timeLog", "SUM: "+sum);
    }

    public static void gatherStart()
    {
        nrLog = 0;
        if(currentFrame>gatherFor)
        {
            gatherPrintSummary();
            times.clear();
            currentFrame = 0;
            gatherStart = System.currentTimeMillis();
        }
        currentFrame++;
    }

}