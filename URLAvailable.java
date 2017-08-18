package com.rz.Libraries;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rz Rasel
 */
public class URLAvailable {

    private static URLAvailable instance = null;

    public static URLAvailable getInstance() {
        if (instance == null) {
            instance = new URLAvailable();
        }
        return instance;
    }

    public boolean isURLAvailable(String argURL) {
        try {
            URL resourceUrl = new URL(argURL);
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection conn = (HttpURLConnection) resourceUrl.openConnection();
            conn.setRequestMethod("HEAD");
            conn.setConnectTimeout(30 * 1000);
            conn.setReadTimeout(30 * 1000);
            //conn.setInstanceFollowRedirects(false);
            //conn.connect();
            //System.out.println(conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
                //System.out.println("Server exists");
                return true;
            } else {
                //System.out.println("Server not exists");
            }
        } catch (MalformedURLException ex) {
            //Logger.getLogger(JDBCMain.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Server not exists MalformedURLException");
        } catch (IOException ex) {
            //Logger.getLogger(JDBCMain.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Server not exists IOException");
        }
        return false;
    }

    public void URLInfo(String argURL) {
        try {
            URL obj = new URL(argURL);
            URLConnection conn = obj.openConnection();
            System.out.println(conn.getHeaderFields().toString());
            String headerValue = conn.getHeaderFields().toString();
            String checkedValue = "HTTP/1.1 200 OK";
            if (headerValue.toLowerCase().contains(checkedValue.toLowerCase())) {
                System.out.println("Server exists");
            } else {
                System.out.println("Server not exists");
            }
            Map<String, List<String>> map = conn.getHeaderFields();
            System.out.println(map);
            System.out.println("Printing Response Header...\n");

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println("Key : " + entry.getKey()
                        + " ,Value : " + entry.getValue());
            }

            System.out.println("\nGet Response Header By Key ...\n");
            String server = conn.getHeaderField("Server");

            if (server == null) {
                System.out.println("Key 'Server' is not found!");
            } else {
                System.out.println("Server - " + server);
            }

            System.out.println("\n Done");
        } catch (MalformedURLException ex) {
            //Logger.getLogger(JDBCMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(JDBCMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
