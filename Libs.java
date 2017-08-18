package com.rz.Libraries;

import java.util.Random;

/**
 *
 * @author Rz Rasel
 */
public class Libs {

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
