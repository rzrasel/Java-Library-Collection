
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.JSONObject;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rz Rasel
 */
public class ConvertingFromUnicode {

    public static void main(String args[]) {
        ConvertingFromUnicode convertingFromUnicode = new ConvertingFromUnicode();
        /*String uniString = "টাকা না দেয়ায় ১৫ দিনের শিশুকে পানিতে ফেলে হত্যা হিজড়ারাদের";
        byte[] bytes = uniString.getBytes(Charset.forName("UTF-8"));
        String newString = new String(bytes);
        System.out.println("Text Decryted : " + bytes.toString());*/
        String fileRawData = "";
        InputStream inputStream = null;
        StringBuilder stringBuilder = null;
        try {
            inputStream = new FileInputStream("app-dir/utf-8-text.txt");
            Reader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            int data = reader.read();
            stringBuilder = new StringBuilder();
            while (data != -1) {
                char theChar = (char) data;
                data = reader.read();
                stringBuilder.append(theChar);
            }
            reader.close();
            fileRawData = stringBuilder.toString();
            System.out.println("DEBUG PRINT: " + stringBuilder.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConvertingFromUnicode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConvertingFromUnicode.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ConvertingFromUnicode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (stringBuilder != null) {
            int[][] ranges = new int[][]{
                {1024, 1279},
                {1280, 1327},
                {11744, 11775},
                {42560, 42655},};
            StringBuilder build = new StringBuilder();
            String uniString = stringBuilder.toString();
            for (char c : uniString.toCharArray()) {
                int[] insideRange = null;
                for (int[] range : ranges) {
                    if (range[0] <= c && c <= range[1]) {
                        insideRange = range;
                        break;
                    }
                }

                if (insideRange != null) {
                    build.append("\\u").append(Integer.toHexString(c));
                } else {
                    build.append(c);
                }
            }
            System.out.println("DEBUG PRINT: ^^^^^^^^^" + build);
        }

        if (stringBuilder != null) {
            try {
                JSONObject json = new JSONObject();
                json.put("string", stringBuilder.toString());
                String converted = json.getString("string");
                System.out.println("DEBUG PRINT JSON: \n" + converted);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                String input = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                for (char c : input.toCharArray()) {
                    if (c >= 128) {
                        stringBuilder.append("\\u").append(String.format("%04X", (int) c));
                    } else {
                        stringBuilder.append(c);
                    }
                }
                System.out.println("DEBUG PRINT: " + stringBuilder.toString());
                byte[] utf8Bytes = stringBuilder.toString().getBytes("UTF-8");
                String text = new String(utf8Bytes, "UTF-8");
                System.out.println("DEBUG PRINT: " + text);
                /*try {
                    JSONObject json = new JSONObject();
                    json.put("string", stringBuilder.toString());
                    String converted = json.getString("string");
                    System.out.println("DEBUG PRINT JSON: \n" + converted);

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
 /*String str = stringBuilder.toString();
                str = str.replace("\\", "");
                String[] arr = str.split("u");
                text = "";
                for (int i = 1; i < arr.length; i++) {
                    int hexVal = Integer.parseInt(arr[i], 16);
                    text += (char) hexVal;
                }
                System.out.println("DEBUG PRINT: " + text);*/
                //escapeHtml
                //input).unescapeJava(
                String title = StringEscapeUtils.escapeHtml3(stringBuilder.toString());
                System.out.println("DEBUG PRINT: -------------" + title);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ConvertingFromUnicode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("app-dir/output.html");
            String fileData = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                    + "<html lang=\"en-US\"xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                    + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>\n"
                    + "        <meta name=\"description\" content=\"AOS - Animate On Scroll library using CSS3\"/>\n"
                    + "        <meta name=\"keywords\" content=\"AOS, animate on scroll, css3 scroll animations, simple scroll animations\"/>\n"
                    + "        <title>App Store</title>\n"
                    + "    </head>\n"
                    + "    <body>\n\n"
                    + convertingFromUnicode.buildHtmlEntityCode(fileRawData)
                    + "\n\n</body>\n"
                    + "</html>";
            Writer writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
            writer.write(fileData);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConvertingFromUnicode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConvertingFromUnicode.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ConvertingFromUnicode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //convertingFromUnicode.buildHtmlEntityCode(fileRawData);
    }

    public static String buildHtmlEntityCode(String input) {
        StringBuffer output = new StringBuffer(input.length() * 2);

        int len = input.length();
        int code, code1, code2, code3, code4;
        char ch;

        for (int i = 0; i < len;) {
            code1 = input.codePointAt(i);
            if (code1 >> 3 == 30) {
                code2 = input.codePointAt(i + 1);
                code3 = input.codePointAt(i + 2);
                code4 = input.codePointAt(i + 3);
                code = ((code1 & 7) << 18) | ((code2 & 63) << 12) | ((code3 & 63) << 6) | (code4 & 63);
                i += 4;
                output.append("&#" + code + ";");
            } else if (code1 >> 4 == 14) {
                code2 = input.codePointAt(i + 1);
                code3 = input.codePointAt(i + 2);
                code = ((code1 & 15) << 12) | ((code2 & 63) << 6) | (code3 & 63);
                i += 3;
                output.append("&#" + code + ";");
            } else if (code1 >> 5 == 6) {
                code2 = input.codePointAt(i + 1);
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
}
//http://shankarpshetty.blogspot.com/2009/11/java-function-to-convert-string-to-html.html
//http://yagudaev.com/posts/jsp-escaping-html/
//http://www.java2s.com/Tutorial/Java/0120__Development/Convertsthestringtotheunicodeformat.htm
//https://www.experts-exchange.com/questions/23649463/How-to-convert-unicode-character-to-its-Hex-equivalant-java.html