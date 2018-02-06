/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rz Rasel 2018-02-06.
 */
public class Utils {

    public static String getDbFromat(String argData) {
        String retVal = null;
        if (argData != null) {
            argData = argData.trim();
            if (argData.isEmpty()) {
                retVal = null;
            } else {
                retVal = "'" + argData.trim() + "'";
            }
        } else {
            retVal = null;
        }
        return retVal;
    }

    public static String toProperCase(String argValue) {
        if (argValue == null) {
            return null;
        }
        argValue = argValue.replaceAll("\\s+", " ");
        final StringBuilder ret = new StringBuilder(argValue.length());
        for (final String word : argValue.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(word.substring(0, 1).toUpperCase());
                ret.append(word.substring(1).toLowerCase());
            }
            if (!(ret.length() == argValue.length())) {
                ret.append(" ");
            }
        }
        return ret.toString();
    }
}
