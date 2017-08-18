/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.Libraries;

import java.util.Random;

/**
 *
 * @author Rz Rasel
 */
public class RandomID {
    public static int getRandInt(int argMin, int argMax) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((argMax - argMin) + 1) + argMin;

        return randomNum;
    }

    public static long getLongId() {
        //Long.valueOf((String)map.get("time")).longValue() ;
        long id = 0l;
        String idValue = System.currentTimeMillis() / 1000 + "" + getRandInt(111, 999);
        id = Long.parseLong(idValue);
        return id;
    }
}
