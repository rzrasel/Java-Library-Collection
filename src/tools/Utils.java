package tools;

import com.rz.librarycore.logger.LogWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

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
        retVal = argData;
        if (retVal != null) {
            retVal = retVal.trim();
            if (retVal.isEmpty()) {
                retVal = null;
            } else {
                /*try {
                    retVal = new String(retVal.getBytes("utf-8"), "iso8859-1");
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                /*if (isUnicode(retVal)) {
                    System.out.println("|----|------------|UNICODE");
                }*/
                retVal = retVal.replaceAll("\'", "''");
                retVal = "'" + retVal.trim() + "'";
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
        //input.toLowerCase().replaceAll("[^a-z\\s]", "").replaceAll("\\s", "-");
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

    public static String htmlEntityCode(String argStringInput) {
        StringBuffer output = new StringBuffer(argStringInput.length() * 2);

        int len = argStringInput.length();
        int code, code1, code2, code3, code4;
        char ch;

        for (int i = 0; i < len;) {
            code1 = argStringInput.codePointAt(i);
            if (code1 >> 3 == 30) {
                code2 = argStringInput.codePointAt(i + 1);
                code3 = argStringInput.codePointAt(i + 2);
                code4 = argStringInput.codePointAt(i + 3);
                code = ((code1 & 7) << 18) | ((code2 & 63) << 12) | ((code3 & 63) << 6) | (code4 & 63);
                i += 4;
                output.append("&#" + code + ";");
            } else if (code1 >> 4 == 14) {
                code2 = argStringInput.codePointAt(i + 1);
                code3 = argStringInput.codePointAt(i + 2);
                code = ((code1 & 15) << 12) | ((code2 & 63) << 6) | (code3 & 63);
                i += 3;
                output.append("&#" + code + ";");
            } else if (code1 >> 5 == 6) {
                code2 = argStringInput.codePointAt(i + 1);
                code = ((code1 & 31) << 6) | (code2 & 63);
                i += 2;
                output.append("&#" + code + ";");
            } else {
                code = code1;
                i += 1;

                ch = (char) code;
                if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch >= '0' && ch <= '9') {
                    output.append(ch);
                } else {
                    output.append("&#" + code + ";");
                }
            }
        }
        //System.out.println("DATA: " + output.toString());
        return output.toString();
    }

    public static String htmlUnescape3(String str) {
        try {
            HTMLDocument doc = new HTMLDocument();
            new HTMLEditorKit().read(new StringReader("<html><body>" + str), doc, 0);
            return doc.getText(1, doc.getLength());
        } catch (Exception e) {
            return str;
        }
    }

    public static String urlEncode(String argString) {
        String result = null;
        try {
            result = URLEncoder.encode(argString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //throw new RuntimeException("UTF-8 not supported", e);
        }
        return result;
    }

    public static String escapeSqlSpecial(String argSqlString) {
        String[] arrPattern = {"+", "-", "&&", "||", "!", "(", ")", "{", "}",
            "[", "]", "^", "\"", "~", "*", "?", ":", "\\", "AND", "OR"};
        for (int i = 0; i < arrPattern.length; i++) {
            //'search' is my input string
            if (argSqlString.contains((String) arrPattern[i])) {
                String oldString = (String) arrPattern[i];
                //myStr.replaceAll(Pattern.quote("+"), replaceStr);
                //oldString = Pattern.quote("+");
                //oldString = oldString;
                String newString = new String(arrPattern[i]);
                newString = newString.replaceAll("\\\\", "");
                //System.out.println("OLD STRING: " + oldString);
                newString = "\\\\" + newString;
                if (oldString.equals("\\")) {
                    newString = "v" + newString;
                    //System.out.println("asdfakslf " + newString);
                }
                //System.out.println("NEW STRING: " + newString);
                //argSqlString = argSqlString.replaceAll(oldString, (String) ("\\" + newString));
                try {
                    argSqlString = argSqlString.replaceAll(oldString, newString);
                    System.out.println(argSqlString + "===" + oldString + " asdfakslf " + newString);
                } catch (PatternSyntaxException e) {
                    /*oldString = "\\" + oldString;
                    argSqlString = argSqlString.replaceAll(oldString, newString);*/
                } catch (IllegalArgumentException e) {
                    /*oldString = "\\" + oldString;
                    newString = "\\" + newString;
                    argSqlString = argSqlString.replaceAll(oldString, newString);*/
                }
            }
        }
        argSqlString.replaceAll("'", "\\\\'");
        return argSqlString;
    }

    public static boolean isUnicode(String argText) {

        Charset charset = Charset.forName("US-ASCII");
        String checked = new String(argText.getBytes(charset), charset);
        return !checked.equals(argText);

    }

    public void testAscii() throws Exception {
        //Assert.assertFalse(isEncoded("Hello world"));
    }

    public void testNonAscii() throws Exception {
        //Assert.assertTrue(isEncoded("Hellä world"));
    }
}
