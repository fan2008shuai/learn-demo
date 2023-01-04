package org.fan.learn.demo.java.other;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author author
 * @date 2022/12/27
 */
public class BloomTest {

    private static final Map<String, String> hashKeyMapping = new HashMap<String, String>(256);

    private static final Set<String> bitSetKeySet = new HashSet<String>();

    private static String hexStringPadding(String hexString) {
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }

    private static void createBitKeyMapping() {
        String bitSetKey = "";
        for (int keyIndex = 0; keyIndex < 256; keyIndex++) {
            String hexString = hexStringPadding(Integer.toHexString(keyIndex));
            if (keyIndex % 4 == 0) {
                bitSetKey = hexString;
            }
            bitSetKeySet.add(bitSetKey);
            hashKeyMapping.put(hexString, bitSetKey);
        }
    }

    @Test
    public void test() {
        createBitKeyMapping();
        System.out.println(bitSetKeySet);
        System.out.println(bitSetKeySet.size());
        System.out.println("=====");
        System.out.println(hashKeyMapping);
    }


    /**
     * @param n insert个元素
     * @param m 总量
     * @return
     */
    public static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    /**
     * @param n insert的元素
     * @param p 期望误报率
     * @return
     */
    public static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    public static double expectedFpp(long bitCount, long m, long numHashFunctions) {
        return Math.pow((double) bitCount / m, numHashFunctions);
    }

    @Test
    public void calRedisCount() {
        // 1.5万亿
        long n = 1500000000000L;
        double fpp = 0.005;
        long res = optimalNumOfBits(n, fpp);
        System.out.println(res);
        long _8G = 8 * 8 * 1024 * 1024 * 1024L;
        long redisCount = res / _8G;
        System.out.println("redisCount: " + redisCount);
    }

    @Test
    public void calFpp() {
        long m = 30312857779048L;
        long bitCount = 489646983168L;
        System.out.println(expectedFpp(bitCount, m, 3));
    }

}
