/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

/**
 *
 * @author Rz Rasel
 */
public class HTTPSClient {

    private HttpsURLConnection httpsURLConnection = null;

    public static void main(String[] args) {
        HTTPSClient httpsClient = new HTTPSClient();
        //httpsClient.HTTPURLConn("https://www.facebook.com/himelshop/posts/1489960441099898");
        httpsClient.HTTPURLConn("https://www.facebook.com/himelshop/posts/1489960441099898");
        httpsClient.HTTPReader();
    }

    private void HTTPURLConn(String argURL) {
        try {
            URL url = new URL(argURL);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
        } catch (MalformedURLException ex) {
            Logger.getLogger(HTTPSClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HTTPSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void HTTPReader() {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStreamReader = new InputStreamReader(httpsURLConnection.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            String input;
            StringBuilder stringBuilder = new StringBuilder();
            while ((input = bufferedReader.readLine()) != null) {
                stringBuilder.append(input);
            }
            System.out.println(stringBuilder.toString());
            WriteToFile("fbPageData.html", stringBuilder.toString());
        } catch (IOException ex) {
            Logger.getLogger(HTTPSClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
                inputStreamReader.close();
            } catch (IOException ex) {
                Logger.getLogger(HTTPSClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void WriteToFile(String argFileName, String argFileData) {
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = new FileOutputStream(argFileName);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(argFileData);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HTTPSClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HTTPSClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HTTPSClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedWriter.close();
                outputStreamWriter.close();
                fileOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(HTTPSClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void print_https_cert(HttpsURLConnection con) {

        if (con != null) {

            try {

                System.out.println("Response Code : " + con.getResponseCode());
                System.out.println("Cipher Suite : " + con.getCipherSuite());
                System.out.println("\n");

                Certificate[] certs = con.getServerCertificates();
                for (Certificate cert : certs) {
                    System.out.println("Cert Type : " + cert.getType());
                    System.out.println("Cert Hash Code : " + cert.hashCode());
                    System.out.println("Cert Public Key Algorithm : "
                            + cert.getPublicKey().getAlgorithm());
                    System.out.println("Cert Public Key Format : "
                            + cert.getPublicKey().getFormat());
                    System.out.println("\n");
                }

            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
