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
         */
        renderPolygonTest(1368,840,1562,821,1490,701,1299,736);
        renderPolygonTest(765,901,868,925,933,892,1167,1053,910,1189,1046,1261,975,1539,941,1539,893,1408,816,1422,751,1326,749,1215,828,1197,828,1156,756,1123,732,1144,695,1166,651,1143,652,1107,583,1073,546,1019,538,934,606,903);
        renderPolygonTest(1177,1840,1260,1890,1240,1945,1188,1956,1119,1890,1147,1861);
        renderPolygonTest(966,1909,891,2012,812,2044,747,1964,728,1844,591,1908,570,1856,814,1715,520,1548,527,1505,565,1506,653,1522,695,1525,693,1575,817,1640,853,1621,885,1631,884,1682,927,1721,847,1790,975,1876);
        renderPolygonTest(172,809,422,669,340,582,584,455,562,430,631,395,804,490,1005,393,1155,405,1341,493,1410,491,1522,447,1754,540,1982,451,2300,456,2295,290,1000,200,79,462);
        renderPolygonTest(2060,450,2070,652,2068,740,2061,795,2080,843,1957,929,1902,1044,1949,1242,1927,1326,2035,1688,2006,1728,2048,1930,1950,1878,1745,1979,1728,2030,1555,2141,1455,2085,1359,2124,1287,2216,1272,2279,2327,2461);
        renderPolygonTest(221,784,313,843,292,1005,250,1037,254,1077,420,1169,372,1378,420,1169,372,1378,420,1388,457,1495,373,1675,416,1750,383,1853,352,1928,467,1993,471,2033,850,2233,919,2181,1006,2188,1103,2150,1214,2242,105,2434);
        renderPolygonTest(1708,1567,1859,1634,1958,1714,1954,1763,1839,1878,1772,1825,1675,1829,1483,1743,1481,1696);
        /**
         *Render collision MAP_02
         */
        //renderPolygonTest(2001,956,1825,1056,1817,1488,1843,1762,1990,1842,1999,1906,2056,1908,2068,1832,2195,1836,2151,1884,2130,1956,2160,2012,1845,2054,1866,2325,1756,2317,1757,2226,1647,2196,1554,2253,1354,2221,1305,2179,1192,2225,1131,2204,1005,2144,865,2214,814,2197,652,2190,558,2130,429,2189,322,2161,328,2012,284,1755,253,1734,177,1769,234,2297,1992,2440,2198,2022,2263,1707,2198,940);
        //renderPolygonTest(1312,251,1302,380,1388,374,1525,283,1485,342,1475,418,1546,527,1606,516,1694,403,1813,300,1893,476,1977,456,2030,507,1908,618,1910,675,1990,770,2094,775,2120,817,2081,849,2073,944,2199,933, 2176,642,1850,218);
        //renderPolygonTest(1186,276,1039,403,1145,403,1151,510,1116,579,1012,628,949,620,873,530,865,460,971,352,860,355,756,461,645,490,560,472,458,545,354,326,260,630,277,760,284,956,307,1066,317,1700,161,1705,132,302,380,229);
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
        //renderPolygonTest(927,246,956,323,1020,330,1065,383,1134,425,1196,436,1257,478,1394,563,1445,592,1493,637,1538,617,1572,638,1584,690,1515,814,1473,843,1436,848,1374,840,1363,808,1331,799,1285,730,1252,762,1219,720,1184,722,1110,625,1070,626,1015,584,962,569,923,569,885,539,837,475,795,462,725,367,709,414,657,435,578,462,560,452,525,357,303,441,336,594,332,667,285,715,289,785,378,821,368,947,375,996,318,1187,402,1217,350,1534,404,1592,308,1682,320,1728,213,1871,227,338,678,242);
        //renderPolygonTest(1323,297,1436,328,1489,310,1654,342,1707,295,1743,309,1792,253,1831,337,1877,353,1925,305,1978,315,1991,349,2025,459,2052,553,1970,644,2037,771,2008,814,2047,924,1979,951,1962,1015,1871,1011,1697,1140,1620,1169,1590,1137,1534,1146,1481,1183,1465,1262,1401,1274,1355,1347,1371,1422,1470,1550,1631,1501,1702,1471,1722,1468,1783,1402,1939,1272,1966,1273,2013,1208,2006,1308,2037,1450,2090,1463,2126,1397,2177,1434,2187,308,1629,185);
        //renderPolygonTest(2210,1518,2147,1541,2120,1681,2102,2076,2024,2214,1854,2214,1777,2225,1756,2262,1723,2254,1677,2213,1541,2238,1457,2243,1416,2258,1353,2261,1332,2208,1291,2205,1276,2250,1089,2187,1006, 2238, 955, 2215, 832, 2225,790,2258,739,2256,732,2213,611,2199,593,2151,543,2141,428,2197,393,2076,333,1985,281,1935,256,2286,2216,2328);
        //renderPolygonTest(1912,1640,2050,1705,2057,1821,2017,1898,1989,1901,1776,1791,1783,1681,1870,1635);
        //renderPolygonTest(924,1752,993,1775,1024,1790,1025,1857,991,1933,963,1962,917,1955,904,1928,828,1911,822,1821,840,1807);
        //renderPolygonTest(1139,1466,1264,1533,1264,1643,1164,1702,1108,1773,1082,1777,867,1669,878,1558,990,1501,1040,1520);
        //renderPolygonTest(1000,1087,1043,1086,1128,1131,1136,1227,922,1338,863,1325,744,1340,699,1327,699,1211,742,1181,819,1173,854,1175,871,1149);
        /**
         * Render collision MAP_05
         */
        //renderPolygonTest(178,624,276,645,242,835,282,856,274,916,276,1052,323,1054,319,1161,288,1188,347,1212,349,1276,321,1369,299,1361,303,1498,380,1544,373,1650,311,1723,341,1746,341,1790,314,1817,318,1890,389,1920,391,2007,305,2078,344,2142,413,2199,448,2170,507,2226,537,2192,601,2241,726,2207,833,2233,903,2200,1020,2215,1081,2193,1148,2233,1107,2304,430,2304,215,2152);
        //renderPolygonTest(1218,2311,1223,2273,1546,2190,1570,2207,1617,2216,1615,2274,1657,2291,1666,2237,1710,2245,1714,2280,1806,2248,1859,2276,1904,2215,1932,2222,1936,2285,1977,2295,1971,2262,2006, 2230,2107,2213,2162,2237,2188,2192,2154,2176,2142,2098,2209,2034,2199,1973,2139,1871,2153,1830,2047,1756,2040,1506,2103,1470,2143,1506,2195,1569,2291,2307);
        //renderPolygonTest(2205,1511,2191,1380,2158,1394,2109,1385,2106,1297,2155,1285,2127,1193,2134,1134,2109,1079,2107,1038,2099,960,2124,937,2101,863,2168,802,2155,722,2119,715,2116,669,2089,628,2080,572,2152,553,2143,477,2117,428,2095,420,2048,450,2020,446,2016,411,1968,407,1936,388,1887,304,1820,358,1763,332,1750,291,1692,248,1558,267,1438,353,1378,328,1364,286,1337,282,1261,320,1179,322,1158,365,1057,412,1051,468,903,510,894,459,857,432,784,463,767,409,746,382,742,329,810,304,804,260,748,258,629,337,553,308,501,317,439,374,358,381,333,426,286,504,281,580,241,586,213,266,904,181,1657,176,2186,257);
        //renderPolygonTest(1061,1103,1058,1059,1157,993,1178,671,1209,655,1238,674,1255,635,1330,603,1467,621,1563,665,1613,707,1658,773,1842,836,1863,879,1887,899,1899,940,1946,964,1966,1003,1961,1033,2007,1051,2016,1077,1928,1192,1881,1203,1867,1256,1833,1262,1837,1300,1860,1332,1912,1345,1915,1404,1900,1450,1820,1506,1775,1506,1757,1524,1775,1575,1772,1642,1747,1711,1713,1748,1668,1750,1590,1800,1568,1834,1540,1833,1496,1808,1483,1792,1447,1815,1411,1811,1409,1773,1358,1776,1330,1750,1266,1730,1224,1660,1200,1691,1174,1689,1155,1636,1115,1628,1084,1591,1021,1545,957,1537,923,1491,868,1505,850,1450,804,1446,769,1380,724,1333,723,1293,804,1272,999,1139,1041,1163,1030,1194,1057,1216,1065,1319,1103,1339,1116,1390,1169,1401,1319,1308,1354,1325,1454,1260,1573,1331,1589,1300,1530,1210,1530,1175,1606,1125,1453,1076,1419,1024,1349,1026,1308,1013,1267,1043,1272,1137,1251,1187,1170,1146,1162,1077,1115,1104);
        //renderPolygonTest(716,1849,744,1879,826,1893,858,1926,852,1960,814,1991,835,2005,950,1952,962,1987,951,2020,801,2094,782,2090,553,1974,551,1918);
        //renderPolygonTest(1696,2039,1711,2004,1775,1971,1875,1999,1912,2047,1912,2070,1876,2101,1843,2159,1791,2163,1757,2144,1717,2084,1700,2048);
        /**
         * Render collision MAP_06
         */
        //renderPolygonTest(1034,236,1006,324,981,328,958,237,921,256,903,247,867,339,847,340,812,237,783,335,753,342,724,247,716,280,691,280,662,238,624,351,593,349,552,248,534,293,510,298,435,363,406,371,377,276,332,283,289,496,259,638,284,657,264,902,306,921,267,1173,306,1191,282,1336,313,1359,283,1544,328,1580,283,1852,368,1889,360,1969,159,1939,203,185);
        //renderPolygonTest(1428,270,1524,242,1546,285,1561,285,1584,243,1618,336,1644,338,1685,238,1729,353,1764,356,1799,240,1841,324,1870,319,1888,233,1921,241,1944,336,1977,336,1994,259,2062,254,2080,317,2107,323,2115,294,2174,311,2155,410,2146,557,2177,698,2141,769,2159,868,2204,1154,2172,1189,2180,1294,2182,1374,2213,1509,2180,1650,2330,1682,2281,182, 1523,190);
        //renderPolygonTest(2180,1648,1913,1792,1772,1721,1755,1739,1759,1780,1906,1859,1937,1859,1941,1922,2110,2008,2048,2048,2052,2118,2014,2115,1990,2160,1862,2167,1852,2151,1817,2158,1792,2172,1603,2172,1603,2161,1605,2088,1538,2037,1494,2050,1465,2072,1469,2122,1524,2172,1122,2168,1116,2145,1019,2111,1022,2099,895,2025,703,2141,588,2085,633,2069,618,2022,550,2045,536,2026,481,2029,363,1972,733,2302,2146,2305);
        //renderPolygonTest(1586,1705,1586,1651,1515,1614,1475,1621,1424,1641,1363,1612,1302,1640,1245,1618,1181,1650,1181,1734,713,1963,722,2010,1194,1797,1234,1822,1257,1821,1308,1795,1348,1821,1434,1797,1476,1824,1495,1823,1539,1795,1548,1700);
        //renderPolygonTest(745,1494,730,1471,699,1456,669,1447,624,1418,575,1423,550,1449,543,1567,568,1609,597,1609,597,1621,621,1606,689,1573,706,1547,740,1538);
        //renderPolygonTest(1076,979,1116,1008,1126,1058,1165,1073,1186,1092,1195,1114,1197,1162,1222,1194,1184,1393,1155,1401,1122,1316,1094,1226,1042,1388,1009,1382,989,1297,963,1292,906,1069,999,1053,1003,993);
        //renderPolygonTest(1796,1156,1893,1172,1930,1198,1931,1236,1916,1258,1874,1280,1841,1316,1796,1332,1747,1309,1730,1208,1753,1179);
        //renderPolygonTest(1040,628,928,674,923,722,856,816,796,839,762,824,760,786,617,719,558,644,507,619,504,548,566,577,604,566,638,578,761,537,926,616,1028,560);
        //renderPolygonTest(1417,508,1536,454,1571,477,1593,446,1643,437,1680,474,1733,480,1835,539,1906,557,1903,761,1843,797,1842,866,1690,937,1561,873,1557,804,1455,759,1446,714,1391,713,1360,696,1363,564);
        /**
         * Render collision BOSS_MAP_01
         */
        //renderPolygonTest(338,317,434,373,1022,380,1027,698,877,782,228,778,236,525,213,460,163,422,158,328,204,301,77,321,124,946,1200,950,1200,10);
        /**
         * Render collision BOSS_MAP_02
         */
        //renderPolygonTest(258,579,267,386,1018,386,1026,698,903,767,267,770,264,736,221,681,233,623,145,625,143,869,1167,880,1167,20,20,20);
        /**
         * Render collision BOSS_MAP_03
         */
        //renderPolygonTest(262,382,1035,374,1032,477,1081,555,1189,538,1079,228,173,288,165,743,372,872,1130,845,1031,602,997,625,989,686,954,710,934,778,389,781,262,694);
        /**
         * Render collision BOSS_MAP_04
         */
        //renderPolygonTest(246,386,1017,382,1024,691,895,780,345,779,334,719,284,674,280,623,243,602,223,861,1110,874,1102,280,151,296,155,588,205,539,233,512,251,473);
        /**
         * Render collision BOSS_MAP_05
         */
        //renderPolygonTest(244,572,290,436,271,378,1012,383,1026,706,907,774,265,775,270,727,242,698,162,865,1136,873,1146,281,140,277);
        /**
         * Render entriences
         */
        //drawRect(1180, 185, 100, 100); //map 2
        //drawRect(2160, 560, 100, 100); //map 3
        //drawRect(940, 200, 500, 100); //map 4
        //drawRect(145, 570, 100, 100); //map 5
        //drawRect(1020, 143, 450, 100); //map 6
        /**
         * Render END
         */
        //drawRect(Map_01.e1.getX() + Map_01.e1.x, Map_01.e1.getY() + Map_01.e1.y, Map_01.e1.getWidth() + Map_01.e1.w, Map_01.e1.getHeight() + Map_01.e1.h);
        //drawRect(Map_01.e2.getX() + Map_01.e2.x, Map_01.e2.getY() + Map_01.e2.y, Map_01.e2.getWidth() + Map_01.e2.w, Map_01.e2.getHeight() + Map_01.e2.h);
        //drawRect(Map_01.e3.getX() + Map_01.e3.x, Map_01.e3.getY() + Map_01.e3.y, Map_01.e3.getWidth() + Map_01.e3.w, Map_01.e3.getHeight() + Map_01.e3.h);
        //drawRect(Map_01.e4.getX() + Map_01.e4.x, Map_01.e4.getY() + Map_01.e4.y, Map_01.e4.getWidth() + Map_01.e4.w, Map_01.e4.getHeight() + Map_01.e4.h);
        //drawRect(Map_01.e5.getX() + Map_01.e5.x, Map_01.e5.getY() + Map_01.e5.y, Map_01.e5.getWidth() + Map_01.e5.w, Map_01.e5.getHeight() + Map_01.e5.h);
        //drawRect(Map_01.e6.getX() + Map_01.e6.x, Map_01.e6.getY() + Map_01.e6.y, Map_01.e6.getWidth() + Map_01.e6.w, Map_01.e6.getHeight() + Map_01.e6.h);
        //drawRect(Map_01.e7.getX() + Map_01.e7.x, Map_01.e7.getY() + Map_01.e7.y, Map_01.e7.getWidth() + Map_01.e7.w, Map_01.e7.getHeight() + Map_01.e7.h);
        //drawRect(Map_01.e8.getX() + Map_01.e8.x, Map_01.e8.getY() + Map_01.e8.y, Map_01.e8.getWidth() + Map_01.e8.w, Map_01.e8.getHeight() + Map_01.e8.h);
        //drawRect(Map_01.e9.getX() + Map_01.e9.x, Map_01.e9.getY() + Map_01.e9.y, Map_01.e9.getWidth() + Map_01.e9.w, Map_01.e9.getHeight() + Map_01.e9.h);
        //drawRect(Map_03.e10.getX() + Map_03.e10.x, Map_03.e10.getY() + Map_03.e10.y, Map_03.e10.getWidth() + Map_03.e10.w, Map_03.e10.getHeight() + Map_03.e10.h);
        shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +hero.getX() -7, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +hero.getY() -24, 22, 60);
        //shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +500, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +300, 100, 100);
        shapeRenderer.end();
    }

    public static void drawRect(int a, int b, int w, int h){
        shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +a, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +b, w, h);
    }

    public static void drawRect(float a, float b, float w, float h){
        shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +a, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +b, w, h);
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
