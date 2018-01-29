
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
দেখুন!বরিশাল নোয়াখালি সিলেট কুমিল্লা ভাষায় তক্কাতক্কি!!
https://www.youtube.com/watch?v=PM9IW4AjgeQ
 */
/**
 *
 * @author developer
 */
public class HTTPUrlConn {

    String strURL;

    public HTTPUrlConn(String argURL) {
        /*try {
            strURL = URLEncoder.encode(argURL, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HTTPUrlConn.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        strURL = argURL;
    }

    public String onRead() {
        try {
            URL url = new URL(strURL);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine + "\n");
                //stringBuilder.append(inputLine);
            }
            bufferedReader.close();
            inputStreamReader.close();
            return stringBuilder.toString();
        } catch (MalformedURLException e) {
            Logger.getLogger(HTTPUrlConn.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(HTTPUrlConn.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
