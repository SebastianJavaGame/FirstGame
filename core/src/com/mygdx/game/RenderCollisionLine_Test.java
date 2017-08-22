package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

import Screen.BaseMap;
import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-06-17.
 */

public class RenderCollisionLine_Test implements Disposable{
    private static ShapeRenderer shapeRenderer;
    private static Camera camera;
    private static Hero hero;


    public RenderCollisionLine_Test(Camera camera, Hero hero) {
        shapeRenderer = new ShapeRenderer();
        this.camera = camera;
        this.hero = hero;
    }

    public static void draw(){
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //DRAW
        //renderPolygonTest(287, 241, 381, 297, 380, 358, 355, 398, 352, 426);
        //renderPolygonTest((int)hero.getX(), (int)hero.getY(), (int)hero.getX(), (int)hero.getY() + (int)hero.getHeight(),
                //(int)hero.getX() + (int)hero.getWidth(), (int)hero.getY() + (int)hero.getHeight(), (int)hero.getX() + (int)hero.getWidth(), (int)hero.getY());
        /*shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 440, 40, 480});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 40, 480, 120, 480});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 120, 480, 200, 480});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 200, 480, 280, 480});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 280, 480, 320, 440});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 440, 320, 360});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 360, 320, 280});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 280, 320, 200});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 200, 320, 120});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 120, 320, 40});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 344, 10, 295, -30});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 280, 0, 200, 0});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 200, 0, 120, 0});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 120, 0, 40, 0});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 40, 0, -32, 0});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 40, 0, 120});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 120, 0, 200});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 200, 0, 280});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 280, 0, 360});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 360, 0, 440});
        */
        //hero.drawCollisionLine();
        /**
         * Render collision MAP_01
         *//*
        renderPolygonTest(1368,840,1562,821,1490,701,1299,736);
        renderPolygonTest(765,901,868,925,933,892,1167,1053,910,1189,1046,1261,975,1539,941,1539,893,1408,816,1422,751,1326,749,1215,828,1197,828,1156,756,1123,732,1144,695,1166,651,1143,652,1107,583,1073,546,1019,538,934,606,903);
        renderPolygonTest(1177,1840,1260,1890,1240,1945,1188,1956,1119,1890,1147,1861);
        renderPolygonTest(966,1909,891,2012,812,2044,747,1964,728,1844,591,1908,570,1856,814,1715,520,1548,527,1505,565,1506,653,1522,695,1525,693,1575,817,1640,853,1621,885,1631,884,1682,927,1721,847,1790,975,1876);
        renderPolygonTest(172,809,422,669,340,582,584,455,562,430,631,395,804,490,1005,393,1155,405,1341,493,1410,491,1522,447,1754,540,1982,451,2300,456,2295,290,1000,200,79,462);
        renderPolygonTest(2060,450,2070,652,2068,740,2061,795,2080,843,1957,929,1902,1044,1949,1242,1927,1326,2035,1688,2006,1728,2048,1930,1950,1878,1745,1979,1728,2030,1555,2141,1455,2085,1359,2124,1287,2216,1272,2279,2327,2461);
        renderPolygonTest(221,784,313,843,292,1005,250,1037,254,1077,420,1169,372,1378,420,1169,372,1378,420,1388,457,1495,373,1675,416,1750,383,1853,352,1928,467,1993,471,2033,850,2233,919,2181,1006,2188,1103,2150,1214,2242,105,2434);
        renderPolygonTest(1708,1567,1859,1634,1958,1714,1954,1763,1839,1878,1772,1825,1675,1829,1483,1743,1481,1696);
        */
        /**
         *Render collision MAP_02
         */
        //renderPolygonTest(2001,956,1825,1056,1817,1488,1843,1762,1990,1842,1999,1906,2056,1908,2068,1832,2195,1836,2151,1884,2130,1956,2160,2012,1845,2054,1866,2325,1756,2317,1757,2226,1647,2196,1554,2253,1354,2221,1305,2179,1192,2225,1131,2204,1005,2144,865,2214,814,2197,652,2190,558,2130,429,2189,322,2161,328,2012,284,1755,253,1734,177,1769,234,2297,1992,2440,2198,2022,2263,1707,2198,940);
        //renderPolygonTest(1312,251,1302,380,1388,374,1525,283,1485,342,1475,418,1546,527,1606,516,1694,403,1813,300,1893,476,1977,456,2030,507,1908,618,1910,675,1990,770,2094,775,2120,817,2081,849,2073,944,2199,933, 2176,642,1850,218);
        //renderPolygonTest(1186,276,1039,336,1145,403,1151,510,1116,579,1012,628,949,620,873,530,865,460,971,352,860,355,756,461,645,490,560,472,354,326,260,630,277,760,284,956,307,1066,317,1700,161,1705,132,302,380,229);
        //renderPolygonTest(729,596,842,682,756,803,702,823,622,734,616,678,639,619);
        //renderPolygonTest(542,1078,659,1179,555,1304,513,1311,437,1219,426,1091);
        //renderPolygonTest(602,954,691,978,691,978,690,1024,633,1052,572,997,572,957);
        //renderPolygonTest(1439,1206,1586,1174,1585,1124,1632,1118,1710,1161,1711,1276,1591,1400,1533,1391,1353,1475,1299,1428,1213,1348,1292,1273,1361,1333,1351,1251);
        //renderPolygonTest(1692,1927,1754,1954,1781,2038,1750,2082,1711,2078,1666,2036,1645,1940);
        //renderPolygonTest(1383,1679,1556,1769,1482,1831,1482,1900,1379,1960,1359,2002,1335,2010,1293,1987,1228,1873,1160,1838,1171,1786,1248,1754,1290,1776,1302,1715);
        //renderPolygonTest(875,1741,1075,1854,1081,1983,995,2049,928,2019,857,2064,800,2026,680,1951,670,1850);
        /**
         * Render collison MAP_03
         */
        //renderPolygonTest(264,1836,316,1854,340,1916,314,1971,369,1994,386,2041,437,2055,459,2128,608,2199,662,2166,762,2219,853,2216,922,2271,981,2243,1061,2285,1059,2322,207,2344);
        //renderPolygonTest(1172,2330,1174,2280,1230,2238,1330,2241,1420,2197,1520,2221,1592,2187,1682,2179,1875,2205,1964,2210,1985,2161,2040,2142,2118,2161,2091,1989,2078,1858,2075,1752,2014,1786,1944,1694,1939,1486,1958,1449,2062,1427,2074,919,2039,928,1962,817,1960,744,1989,712,2060,692,2089,691,2098,634,2131,615,2215,641,2250,2412);
        //renderPolygonTest(2212,558,2089,556,2090,343,2062,344,2003,322,1925,386,1890,384,1838,284,1792,301,1728,263,1681,325,1661,327,1631,313,1615,280,1602,312,1575,322,1554,324,1505,295,1493,323,1390,315,1354,288,1315,308,1226,306,1172,284,1135,251,1134,275,1021,387,989,386,956,361,919,283,847,294,831,341,782,371,759,364,726,312,707,307,686,283,675,309,656,320,607,243,532,298,484,285,449,363,375,462,338,439,281,381,300,540,278,591,279,683,316,741,269,949,303,1005,259,1134,305,1182,252,1355,303,1428,264,1537,289,1571,257,1707,206,1749,204,1798,138,1628,238,318,656,132,2170,132,2170,238,2254,479);
        //renderPolygonTest(1086,564,1196,607,1258,654,1326,680,1337,726,1306,832,1278,877,1243,892,1217,874,1183,828,1137,895,1096,896,1057,875,1024,838,995,835,964,778,941,776,909,703,930,637,987,622,1004,590);
        //renderPolygonTest(581,973,635,1016,635,1055,520,1136,367,1054,379,1003,480,964,541,966);
        //renderPolygonTest(847,1338,1148,1465,1127,1622,1090,1642,1052,1618,1027,1590,878,1511,868,1557,824,1542,819,1480,728,1421,761,1372);
        //renderPolygonTest(1209,1119,1278,1104,1493,1226,1487,1272,1454,1312,1379,1339,1338,1366,1325,1332,1281,1297,1283,1230,1210,1274,1108,1206,1113,1166);
        //renderPolygonTest(1402,1442,1499,1444,1670,1559,1680,1615,1607,1660,1532,1771,1439,1797,1236,1603,1239,1564,1242,1528);
        //renderPolygonTest(948,1744,1048,1833,1039,1891,1063,1949,1054,1986,934,2076,878,2019,792,2095,751,2089,710,1993,696,1800,792,1747,853,1772);
        /**
         * Render collision MAP_04
         */
        renderPolygonTest(927,246,956,323,1020,330,1065,383,1134,425,1196,436,1257,478,1394,563,1445,592,1493,637,1538,617,1572,638,1584,690,1515,814,1473,843,1436,848,1374,840,1363,808,1331,799,1285,730,1252,762,1219,720,1184,722,1110,625,1070,626,1015,584,962,569,923,569,885,539,837,475,795,462,725,367,709,414,657,435,578,462,560,452,525,357,303,441,336,594,332,667,285,715,289,785,378,821,368,947,375,996,318,1187,402,1217,350,1534,404,1592,308,1682,320,1728,213,1871,227,338,678,242);
        renderPolygonTest(1323,297,1436,328,1489,310,1654,342,1707,295,1743,309,1792,253,1831,337,1877,353,1925,305,1978,315,1991,349,2025,459,2052,553,1970,644,2037,771,2008,814,2047,924,1979,951,1962,1015,1871,1011,1697,1140,1620,1169,1590,1137,1534,1146,1481,1183,1465,1262,1401,1274,1355,1347,1371,1422,1470,1550,1631,1501,1702,1471,1722,1468,1783,1402,1939,1272,1966,1273,2013,1208,2006,1308,2037,1450,2090,1463,2126,1397,2177,1434,2187,308,1629,185);
        renderPolygonTest(2210,1518,2147,1541,2120,1681,2102,2076,2024,2214,1854,2214,1777,2225,1756,2262,1723,2254,1677,2213,1541,2238,1457,2243,1416,2258,1353,2261,1332,2208,1291,2205,1276,2250,1089,2187,1006, 2238, 955, 2215, 832, 2225,790,2258,739,2256,732,2213,611,2199,593,2151,543,2141,428,2197,393,2076,333,1985,281,1935,256,2286,2216,2328);
        renderPolygonTest(1912,1640,2050,1705,2057,1821,2017,1898,1989,1901,1776,1791,1783,1681,1870,1635);
        renderPolygonTest(924,1752,993,1775,1024,1790,1025,1857,991,1933,963,1962,917,1955,904,1928,828,1911,822,1821,840,1807);
        renderPolygonTest(1139,1466,1264,1533,1264,1643,1164,1702,1108,1773,1082,1777,867,1669,878,1558,990,1501,1040,1520);
        renderPolygonTest(1000,1087,1043,1086,1128,1131,1136,1227,922,1338,863,1325,744,1340,699,1327,699,1211,742,1181,819,1173,854,1175,871,1149);
        /**
         * Render collision MAP_05
         */
        shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +hero.getX() -7, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +hero.getY() -24, 22, 60);
        //shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +500, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +300, 100, 100);
        shapeRenderer.end();
    }

    public static void drawPublic(float x, float y, float w, float h){
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        camera = BaseScreen.camera;
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y, w, h);
        shapeRenderer.end();
    }

    public static void drawPublic(Rectangle rectangle){
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        camera = BaseScreen.camera;
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +rectangle.getX(), -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        shapeRenderer.end();
    }

    public static void drawPointSquare(int x, int y){
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        camera = BaseScreen.camera;
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.polygon(new float[]{-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x -2,
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  -2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x  -2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  +2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x  +2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  +2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x +2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y -2)});
        shapeRenderer.end();
    }

    public static void drawPointSquare(Vector2 vector){
        int x = (int)vector.x;
        int y = (int)vector.y;
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        camera = BaseScreen.camera;
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.polygon(new float[]{-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x -2,
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  -2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x  -2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  +2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x  +2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  +2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x +2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y -2)});
        shapeRenderer.end();
    }

    private static void renderPolygonTest(int ... position) {
        ArrayList<Integer> listPosition = new ArrayList<Integer>();
        for(int i = -1; i < position.length-1; i+=2){
            listPosition.add((int)-camera.position.x + BaseMap.VIEW_WIDTH / 2 + position[i+1]);
            listPosition.add((int)-camera.position.y + BaseMap.VIEW_HEIGHT / 2 + position[i+2]);
        }
        float[] tabPosition = new float[listPosition.size()];

        int i =0;
        for(int element : listPosition){
            tabPosition[i] = element;
            i++;
        }

        shapeRenderer.polygon(tabPosition);
    }

    @Override
    public void dispose(){
        shapeRenderer.dispose();
    }
}
