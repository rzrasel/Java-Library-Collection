
import com.rz.librarycore.http.PowerHTTPConnection;
import com.rz.librarycore.logger.LogWriter;
import java.awt.image.BufferedImage;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.commons.lang3.StringEscapeUtils;
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
        String imageURLRawData = "";
        String titleRawData = "";
        String descriptionRawData = "";
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
            String[] fileData = fileRawData.split("\\|\\|");
            imageURLRawData = fileData[0].trim();
            titleRawData = fileData[1].trim();
            descriptionRawData = fileData[2].trim();
            LogWriter.Log(fileData.length + " Lenght");
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
                //String title = StringEscapeUtils.escapeHtml3(stringBuilder.toString());
                //System.out.println("DEBUG PRINT: -------------" + title);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ConvertingFromUnicode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        OutputStream outputStream = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String strDomainURL;
            String metaHostUrl;
            String metaImageUrl;
            BufferedImage bufferedImage = null;
            strDomainURL = "http://pngimagesfree.com/NATURE/Grass/pond_with_grass-png.png";
            strDomainURL = imageURLRawData;
            String metaFileName = "";
            //metaHostUrl = "http://apphive.me/fb-page/";
            //metaImageUrl = "http://apphive.me/fb-page/p-003.jpg";
            //metaFileName = "literotica-" + simpleDateFormat.format(date) + ".html";
            metaHostUrl = "http://apphive.me/fb-story/";
            metaImageUrl = "http://apphive.me/fb-story/s-002.jpg";
            metaFileName = "story-" + simpleDateFormat.format(date) + ".html";
            /*PowerHTTPConnection powerHTTPConnection = new PowerHTTPConnection();
            bufferedImage = powerHTTPConnection.onReadImage(strDomainURL, PowerHTTPConnection.FileType.PNG, "");
            String base64Data = powerHTTPConnection.onImageEncodeToString(bufferedImage);
            String imageString = "data:image/png;base64," + base64Data;
            LogWriter.Log("DEBUG base64Data: " + base64Data);*/
            String htmlTitle = convertingFromUnicode.buildHtmlEntityCode(titleRawData);
            String htmlDescription = convertingFromUnicode.buildHtmlEntityCode(descriptionRawData);
            convertingFromUnicode.onWriteHtmlEntity(htmlTitle, htmlDescription);
            String htmlDescriptionNew;
            htmlTitle = htmlTitle.replaceAll("&#32;", " ");
            htmlDescription = htmlDescription.replaceAll("&#32;", " ");
            htmlDescriptionNew = htmlDescription.replaceAll("&#13;&#10;", "<br />");
            metaHostUrl = metaHostUrl + metaFileName;
            convertingFromUnicode.onWriteIndex(metaFileName);
            //outputStream = new FileOutputStream("app-dir/output.html");
            //outputStream = new FileOutputStream("app-dir/story.php");
            outputStream = new FileOutputStream("app-dir/" + metaFileName);
            /*String fileData = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                    + "<html lang=\"en-US\"xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                    + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>\n"
                    + "        <meta name=\"description\" content=\"AOS - Animate On Scroll library using CSS3\"/>\n"
                    + "        <meta name=\"keywords\" content=\"AOS, animate on scroll, css3 scroll animations, simple scroll animations\"/>\n"
                    + "        <title>App Store</title>\n"
                    + "        <meta property=\"og:title\" content=\"" + htmlTitle + "\" />\n"
                    + "        <meta property=\"og:description\" content=\"" + htmlDescription + " Note: If you have any issues, contact us before giving a bad review. If you have any suggestions please send us an email instead of giving one star and bad review. Suggestions and feedback please contact contact@apphive.me - App Hive\" />\n"
                    + "        <meta property=\"og:type\" content=\"article\" />\n"
                    //+ "        <meta property=\"og:image\" content=\"<?= base_url(); ?>assets/fb-quick-story/001.jpg\" />\n"
                    + "        <meta property=\"og:image\" content=\""+ metaImageUrl + "\" />\n"
                    //+ "        <meta property=\"og:url\" content=\"<?= base_url(\"/fb-story\"); ?>\" />\n"
                    + "        <meta property=\"og:url\" content=\"http://apphive.me/story.php\" />\n"
                    + "        <meta property=\"og:locale\" content=\"en_US\" />\n"
                    + "        <meta property=\"og:site_name\" content=\"App Hive - Math Play\" />\n"
                    + "    </head>\n"
                    + "    <body>\n\n"
                    + "<img src=\"" + strDomainURL + "\" width=\"100%\">"
                    + htmlDescriptionNew
                    + "\n\n</body>\n"
                    + "</html>";*/
            String fileData = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                    + "<html lang=\"en-US\"xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                    + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>\n"
                    + "        <meta name=\"description\" content=\"" + titleRawData + " App Hive, App Hive Me\"/>\n"
                    + "        <meta name=\"keywords\" content=\"" + titleRawData + " App Hive, App Hive Me\"/>\n"
                    + "\n"
                    + "        <meta property=\"og:title\" content=\"" + htmlTitle + "\" />\n"
                    + "        <meta property=\"og:description\" content=\"" + htmlDescription + " Note: If you have any issues, contact us before giving a bad review. If you have any suggestions please send us an email instead of giving one star and bad review. Suggestions and feedback please contact contact@apphive.me - App Hive\" />\n"
                    + "        <meta property=\"og:type\" content=\"article\" />\n"
                    + "        <meta property=\"og:image\" content=\"" + metaImageUrl + "\" />\n"
                    + "        <meta property=\"og:url\" content=\"" + metaHostUrl + "\" />\n"
                    + "        <meta property=\"og:locale\" content=\"en_US\" />\n"
                    + "        <meta property=\"og:site_name\" content=\"App Hive - Math Play\" />\n"
                    + "        <title>APP Hive - " + titleRawData + "</title>\n"
                    + "        <link rel=\"icon\" type=\"image/png\" href=\"http://apphive.me/assets/favicon-apphive.png\" />\n"
                    + "        <link rel=\"stylesheet\" href=\"http://apphive.me/assets/css/main.css\" />\n"
                    + "        <link rel=\"stylesheet\" href=\"http://apphive.me/assets/css/app-grid.css\" />\n"
                    + "    </head>\n"
                    + "    <body class=\"subpage\">\n"
                    + "        <!-- Header -->\n"
                    + "        <header id=\"header\">\n"
                    + "            <div class=\"logo\"><a href=\"http://apphive.me/app-store\">APP <span>Hive</span></a></div>\n"
                    + "            <a href=\"#menu\">Menu</a>\n"
                    + "        </header>\n"
                    + "\n"
                    + "        <!-- Nav -->\n"
                    + "        <nav id=\"menu\">\n"
                    + "            <ul class=\"links\">\n"
                    + "                <li><a href=\"http://apphive.me/\">Home</a></li>\n"
                    + "                <li><a href=\"http://apphive.me/app-store\">App Store</a></li>\n"
                    + "                <!--<li><a href=\"elements.html\">Elements</a></li>-->\n"
                    + "            </ul>\n"
                    + "        </nav>\n"
                    + "\n"
                    + "        <!-- One -->\n"
                    + "        <section id=\"One\" class=\"wrapper style3\">\n"
                    + "            <div class=\"inner\">\n"
                    + "                <header class=\"align-center\">\n"
                    + "                    <p>Eleifend vitae urna</p>\n"
                    + "                    <h2>APP HIVE</h2>\n"
                    + "                </header>\n"
                    + "            </div>\n"
                    + "        </section>\n"
                    + "\n"
                    + "        <!-- Two -->\n"
                    + "        <section id=\"two\" class=\"wrapper style2\">\n"
                    + "            <div class=\"inner\">\n"
                    + "                <div class=\"box\">\n"
                    + "                    <div class=\"content\">\n"
                    + "                        <header class=\"align-center\">\n"
                    + "                            <p>maecenas sapien feugiat ex purus</p>\n"
                    + "                            <h2>APP HIVE</h2>\n"
                    + "                        </header>\n"
                    + "                        <img src=\"" + metaImageUrl + "\" alt=\"\" width=\"100%\" />\n"
                    + "                        <br />\n"
                    + "                        <br />\n"
                    + "                        <h4>" + htmlTitle + "</h4>\n"
                    + "                        <p style=\"text-align:justify; color: #000000;\">" + htmlDescriptionNew + "\n"
                    + "                        </p>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </section>\n"
                    + "\n"
                    + "        <!-- Footer -->\n"
                    + "        <footer id=\"footer\">\n"
                    + "            <div class=\"container\">\n"
                    + "                <ul class=\"icons\">\n"
                    + "                    <li><a href=\"#\" class=\"icon fa-twitter\"><span class=\"label\">Twitter</span></a></li>\n"
                    + "                    <li><a href=\"#\" class=\"icon fa-facebook\"><span class=\"label\">Facebook</span></a></li>\n"
                    + "                    <li><a href=\"#\" class=\"icon fa-instagram\"><span class=\"label\">Instagram</span></a></li>\n"
                    + "                    <li><a href=\"#\" class=\"icon fa-envelope-o\"><span class=\"label\">Email</span></a></li>\n"
                    + "                </ul>\n"
                    + "            </div>\n"
                    + "            <div class=\"copyright\">\n"
                    + "                &copy; App Hive. All rights reserved.\n"
                    + "            </div>\n"
                    + "        </footer>\n"
                    + "\n"
                    + "        <!-- Scripts -->\n"
                    + "        <script src=\"http://apphive.me/assets/js/jquery.min.js\"></script>\n"
                    + "        <script src=\"http://apphive.me/assets/js/jquery.scrollex.min.js\"></script>\n"
                    + "        <script src=\"http://apphive.me/assets/js/skel.min.js\"></script>\n"
                    + "        <script src=\"http://apphive.me/assets/js/util.js\"></script>\n"
                    + "        <script src=\"http://apphive.me/assets/js/main.js\"></script>\n"
                    + "    </body>\n"
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

    public void onWriteHtmlEntity(String argTitle, String argDescription) {
        OutputStream outputStream = null;
        try {
            String fileData = argTitle + "\n||\n" + argDescription;
            outputStream = new FileOutputStream("app-dir/html-entity.txt");
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
    }

    public void onWriteIndex(String argMenuLink) {
        OutputStream outputStream = null;
        try {
            String fileData = "<a href=\"" + argMenuLink + "\">" + argMenuLink + "</a>";
            outputStream = new FileOutputStream("app-dir/index.html");
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
/*
INSERT INTO destinationTable (risposta, data_ins)
SELECT STATUS risposta, DATETIME('now') data_ins 
FROM   sourceTable
COPY AND RE ARRANGE:-
DELETE FROM tbtmp_fb_obscene_dirt_temp;
INSERT INTO tbtmp_fb_obscene_dirt_temp SELECT * FROM tbtmp_fb_obscene_dirt ORDER BY fobsd_dirt_slug ASC;
DELETE FROM tbtmp_fb_obscene_dirt;
INSERT INTO tbtmp_fb_obscene_dirt SELECT * FROM tbtmp_fb_obscene_dirt_temp ORDER BY fobsd_dirt_slug ASC;
DELETE FROM tbtmp_fb_obscene_dirt_temp;

COPY AND RE ARRANGE:-
CREATE TABLE tbtmp_fb_obscene_dirt_temp AS SELECT * FROM tbtmp_fb_obscene_dirt ORDER BY fobsd_dirt_slug ASC;
DROP TABLE IF EXISTS tbtmp_fb_obscene_dirt;
DELETE FROM tbtmp_fb_obscene_dirt_temp;
DROP TABLE IF EXISTS tbtmp_fb_obscene_dirt_temp;

incest 

http://www.choti69.com/2015/11/english-chodar-golpo.html
https://www.banglachoticlub.com/
https://www.bangla-choti-golpo.com/
http://www.exluv.com/
https://www.facebook.com/Bangla-Choti-Golpo-318316995343026/?ref=br_rs
https://www.facebook.com/sexychoti2018/?ref=br_rs
http://newbanglachoti1.blogspot.com/2016/03/bangla-choti-golpo_29.html
https://www.banglachotifull.com/2016/11/20/bangla-choti-chuda-maayer-guder-jala-mitano/
http://banglachotigalpo.blogspot.com/2017/01/Bangla-Sexy-Story.html#
https://tanbazar.wordpress.com/tag/bangla-choti/
http://www.banglachotigolpo24.com/
http://banglachotimasla24.blogspot.com/
https://www.facebook.com/%E0%A6%95%E0%A6%BE%E0%A6%AE%E0%A7%81%E0%A6%95-%E0%A6%AD%E0%A6%BE%E0%A6%87-%E0%A6%AC%E0%A7%8B%E0%A6%A8-324889181357549/

https://www.facebook.com/4girlswoman/

https://vhalobashi.wordpress.com/tag/bangla-love-story/page/2/
http://www.somewhereinblog.net/blog/nissongojoddha/29128150
http://www.deshebideshe.com/news/details/126460
http://www.rupalialo.com/2017/10/07/%E0%A6%B8%E0%A6%BE%E0%A6%AC%E0%A6%B2%E0%A7%87%E0%A6%9F-%E0%A6%AE%E0%A7%8D%E0%A6%AF%E0%A6%BE%E0%A6%B0%E0%A6%BF%E0%A6%A8%E0%A6%BE-%E0%A6%A8%E0%A6%BE%E0%A6%B8%E0%A6%B0%E0%A7%80%E0%A6%A8/
https://arts.bdnews24.com/?p=6825

*/
//http://shankarpshetty.blogspot.com/2009/11/java-function-to-convert-string-to-html.html
//http://yagudaev.com/posts/jsp-escaping-html/
//http://www.java2s.com/Tutorial/Java/0120__Development/Convertsthestringtotheunicodeformat.htm
//https://www.experts-exchange.com/questions/23649463/How-to-convert-unicode-character-to-its-Hex-equivalant-java.html
