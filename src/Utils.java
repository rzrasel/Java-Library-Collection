
import com.rz.librarycore.logger.LogWriter;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Pattern;

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

    public static String toSlugCase(String argValue) {
        if (argValue == null) {
            return null;
        }
        String slug = "";
        Pattern NONLATIN = Pattern.compile("[^\\w-]");
        Pattern WHITESPACE = Pattern.compile("[\\s]");
        String nowhitespace = WHITESPACE.matcher(argValue).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        slug = NONLATIN.matcher(normalized).replaceAll("");
        /*slug = argValue.replaceAll("\\s+", "-");
        slug = slug.replaceAll("[^\\w\\s\\p{Pd}]", "");
        slug = slug.replaceAll("([-_]){2,}", "");*/
        slug = slug.replaceAll("[-]+", " ").trim();
        if (slug.isEmpty()) {
            slug = null;
        } else {
            slug = slug.replaceAll("\\s+", "-").toLowerCase();
        }
        return slug;
    }

    public static String toEmptyToNull(String argValue) {
        if (argValue == null) {
            return null;
        }
        argValue = argValue.replaceAll("\\s+", " ").trim();
        if (argValue.isEmpty()) {
            argValue = null;
        }
        return argValue;
    }
}
