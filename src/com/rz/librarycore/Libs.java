/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rz.Libraries;

import java.util.Random;

/**
 *
 * @author Rz Rasel
 */
public class Libs {

    public static int randInt(int argMin, int argMax) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((argMax - argMin) + 1) + argMin;

        return randomNum;
    }

    public static long longId() {
        //Long.valueOf((String)map.get("time")).longValue() ;
        long id = 0l;
        String idValue = System.currentTimeMillis() / 1000 + "" + randInt(11, 99);
        id = Long.parseLong(idValue);
        return id;
    }

    public static String toTitleCase(String argLine) {
        argLine = argLine.trim().toLowerCase();
        argLine = argLine.replaceAll("\\s+", " ");
        String[] words = argLine.split(" ");
        StringBuilder result = new StringBuilder();
        String titleCase = "";
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                words[i] = words[i].toLowerCase();
                titleCase = words[i].charAt(0) + "";
                titleCase = titleCase.toUpperCase();
                if (words[i].length() > 1) {
                    titleCase = titleCase + words[i].substring(1, words[i].length());
                }
                //words[i] = Character.toUpperCase(words[i].charAt(0)) + [i].substring(1);
                result.append(titleCase + " ");
            }
        }
        return result.toString().trim();
    }
}
