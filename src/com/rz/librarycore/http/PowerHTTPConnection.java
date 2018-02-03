/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore.http;

import com.rz.librarycore.logger.LogWriter;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Rz Rasel
 */
public class PowerHTTPConnection {

    private String strDomainURL;
    private final String userAgent = "Mozilla/5.0";
    private HTTPMethod httpMethod;
    private boolean isAllowRedirects = true;
    private int readTimeout = 0;
    private int connectTimeout = 0;
    private boolean isUseCaches = false;
    private HashMap<String, String> urlHeaders = new HashMap<String, String>();
    private HashMap<String, String> urlRequestParameters = new HashMap<String, String>();

    public static void main(String args[]) {
        String strDomainURL;
        strDomainURL = "https://formproratings.co.nz/wp-content/uploads/2015/07/BETATESTINGsquareStampRed.jpg";
        /*PowerHTTPConnection powerHTTPConnection = new PowerHTTPConnection();
        powerHTTPConnection.onPrepareConnection(strDomainURL, HTTPMethod.GET, false)
                .onRunConnection();*/
        BufferedImage bufferedImage = null;
        strDomainURL = "http://pngimagesfree.com/NATURE/Grass/pond_with_grass-png.png";
        PowerHTTPConnection powerHTTPConnection = new PowerHTTPConnection();
        bufferedImage = powerHTTPConnection.onReadImage(strDomainURL, FileType.PNG, "");
        String base64Data = powerHTTPConnection.onImageEncodeToString(bufferedImage);
        LogWriter.Log("DEBUG base64Data: " + base64Data);
        byte[] bytes = powerHTTPConnection.onReadImage(strDomainURL);
        base64Data = powerHTTPConnection.onImageEncodeToString(bytes);
        LogWriter.Log("DEBUG base64Data: " + base64Data);
    }

    public PowerHTTPConnection() {
    }

    public byte[] onReadImage(String argStrDomainURL) {
        byte[] bytes = null;
        try {
            URL domainURL = new URL(argStrDomainURL);
            /*URLConnection urlConnection = domainURL.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                //System.out.println(inputLine);
                stringBuilder.append(inputLine);
            }
            inputStreamReader.close();
            bufferedReader.close();
            byte[] bytes = stringBuilder.toString().getBytes();
            String encoded = Base64.getEncoder().encodeToString(bytes);
            //byte[] decoded = Base64.getDecoder().decode(encoded);
            LogWriter.Log("DEBUG byte String: " + encoded);*/
            /////////////////
            URLConnection urlConnection = domainURL.openConnection();
            /*urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setDoOutput(true);
            urlConnection.connect();*/
            //HttpURLConnection httpURLConnection = (HttpURLConnection) domainURL.openConnection();
            //uRLConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int count;
            while ((count = inputStream.read()) != -1) {
                byteArrayOutputStream.write(count);
            }
            inputStream.close();
            bytes = byteArrayOutputStream.toByteArray();
            /*String byteStr = new String(bytes);
            String encoded = Base64.getEncoder().encodeToString(bytes);
            //byte[] decoded = Base64.getDecoder().decode(encoded);*/
            //LogWriter.Log("DEBUG byte String: " + bytes);
        } catch (IOException e) {
            //e.printStackTrace();
            LogWriter.Log("DEBUG IOException: " + e);
        }
        return bytes;
    }

    public void onImageWriteToDisk() {
        //
    }

    public BufferedImage onReadImage(String argStrDomainURL, FileType argFileType, String argOutFilePath) {
        //Image image = null;
        BufferedImage bufferedImage = null;
        try {
            //URL url = new URL("http://pngimagesfree.com/NATURE/Grass/pond_with_grass-png.png");
            URL url = new URL(argStrDomainURL);
            bufferedImage = ImageIO.read(url);
        } catch (IOException e) {
            //e.printStackTrace();
            LogWriter.Log("DEBUG IOException: " + e);
        }
        File file = null;
        if (argOutFilePath != null) {
            if (!argOutFilePath.isEmpty()) {
                try {
                    file = new File(argOutFilePath + "." + argFileType.getFileType());  //output file path
                    ImageIO.write(bufferedImage, argFileType.getFileType(), file);
                    //System.out.println("Writing complete.");
                    LogWriter.Log("DEBUG Writing complete.");
                } catch (IOException e) {
                    //System.out.println("Error: " + e);
                    LogWriter.Log("DEBUG IOException: " + e);
                }
            }
        }
        return bufferedImage;
    }

    public String onImageEncodeToString(byte[] argByte) {
        //String byteStr = new String(argByte);
        String encoded = Base64.getEncoder().encodeToString(argByte);
        //byte[] decoded = Base64.getDecoder().decode(encoded);
        return encoded;
    }

    public String onImageEncodeToString(BufferedImage argImage) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(argImage, "png", byteArrayOutputStream);

            String base64Data = DatatypeConverter.printBase64Binary(byteArrayOutputStream.toByteArray());
            /*String imageString = "data:image/png;base64," + base64Data;
            String html = "<html><body><img src='" + imageString + "'></body></html>";*/
            return base64Data;
        } catch (IOException e) {
            //System.out.println("Error: " + e);
            LogWriter.Log("DEBUG IOException: " + e);
        }
        return null;
        //http://www.codejava.net/java-se/networking/use-httpurlconnection-to-download-file-from-an-http-url
        //https://javapointers.com/tutorial/java-convert-image-to-base64-string-and-base64-to-image/
    }

    public PowerHTTPConnection onPrepareConnection(String argStrDomainURL, HTTPMethod argHTTPMethod, boolean argIsAllowRedirects) {
        this.strDomainURL = argStrDomainURL;
        this.httpMethod = argHTTPMethod;
        this.isAllowRedirects = argIsAllowRedirects;
        return this;
    }

    public PowerHTTPConnection setUrlHeader(HashMap<String, String> argUrlHeaderList) {
        this.urlHeaders = argUrlHeaderList;
        return this;
    }

    public PowerHTTPConnection setURLParameters(HashMap<String, String> argRequestParameterList) {
        this.urlRequestParameters = argRequestParameterList;
        return this;
    }

    public PowerHTTPConnection setReadTimeout(int argReadTimeout) {
        this.readTimeout = argReadTimeout;
        return this;
    }

    public PowerHTTPConnection setConnectTimeout(int argConnectTimeout) {
        this.readTimeout = argConnectTimeout;
        return this;
    }

    public PowerHTTPConnection setUseCaches(boolean argIsUseCaches) {
        this.isUseCaches = argIsUseCaches;
        return this;
    }

    public String onRunConnection() {
        try {
            URL domainURL = new URL(strDomainURL);
            //Get url protocol: domainURL.getProtocol()
            LogWriter.Log("HTTP_REQUESTED_URL: " + strDomainURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) domainURL.openConnection();
            httpURLConnection.setRequestMethod(httpMethod.getMethodName());
            LogWriter.Log("HTTP_REQUESTED_METHODS: " + httpMethod.getMethodName());
            if (connectTimeout > 0) //15000
            {
                httpURLConnection.setConnectTimeout(connectTimeout);
            }
            if (readTimeout > 0) //15000
            {
                httpURLConnection.setReadTimeout(readTimeout);
            }
            httpURLConnection.setUseCaches(isUseCaches);
            //httpURLConnection.setRequestProperty("User-Agent", userAgent);
            httpURLConnection.setInstanceFollowRedirects(isAllowRedirects);
            httpURLConnection.setRequestProperty("charset", "utf-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            if (urlHeaders.size() > 0) {
                //JSONObject jsonObject = new JSONObject(urlHeaderList);
                //LogWriter.Log("PRINT_HEADER_LIST:- " + jsonObject.toString());
                String strHeaders = "";
                for (Map.Entry<String, String> entry : urlHeaders.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    strHeaders += key + "=" + value + "&";
                    httpURLConnection.setRequestProperty(key, value);
                }
                LogWriter.Log("HTTP_HEADER: " + strHeaders);
            }
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            if (httpMethod.POST == httpMethod && urlRequestParameters.size() > 0) {
                //onWriteHttpUrlData(httpURLConnection);
                String urlRequestParam = new PowerURLParameters().getFormatedURLParameters(urlRequestParameters);
                new PowerURLReadWrite().onURLWriter(httpURLConnection, urlRequestParam);
                LogWriter.Log("HTTP_PARAMETERS:" + urlRequestParam);
            }
            int responseCode = httpURLConnection.getResponseCode();
            LogWriter.Log("HTTP_RESPONSE_CODE: " + responseCode);
            String httpURLData = new PowerURLReadWrite().onURLReader(httpURLConnection);
            LogWriter.Log("HTTP_DATA: " + httpURLData);
            httpURLConnection.disconnect();
            if (httpURLData != null) {
                //LogWriter.Log("URL_DATA: " + httpURLData);
            }
            return httpURLData;
        } catch (MalformedURLException e) {
            LogWriter.Log("PRINT_ERROR_MalformedURLException: " + e.toString());
        } catch (IOException e) {
            LogWriter.Log("PRINT_ERROR_IOException: " + e.toString());
        }
        return null;
    }

    private class PowerURLReadWrite {

        protected void onURLWriter(HttpURLConnection argHttpURLConnection, String argRequestParameters) {
            String urlParams = argRequestParameters;
            try {
                OutputStream outputStream = argHttpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(urlParams);
                writer.flush();
                writer.close();
                outputStream.close();
            } catch (IOException e) {
                LogWriter.Log("PRINT_ERROR_IOException:- " + e.getMessage().toString());
            }
        }

        protected String onURLReader(HttpURLConnection argHttpURLConnection) {
            String retVal = null;
            try {
                InputStream inputStream = null;
                StringBuilder stringBuilder = null;
                if (argHttpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    inputStream = argHttpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        //sb.append(line + "\n");
                        stringBuilder.append(line);
                    }
                    inputStream.close();
                    //argHttpURLConnection.disconnect();
                    retVal = stringBuilder.toString();
                    LogWriter.Log("PRINT_HTTP_DATA_STRING: " + stringBuilder.toString());
                } else {
                    inputStream = argHttpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        //sb.append(line + "\n");
                        stringBuilder.append(line);
                    }
                    inputStream.close();
                    //argHttpURLConnection.disconnect();
                    LogWriter.Log("PRINT_HTTP_DATA_STRING: " + stringBuilder.toString());
                }
            } catch (IOException e) {
                //e.printStackTrace();
                LogWriter.Log("PRINT_ERROR_IOException:- " + e.getMessage().toString());
            }
            return retVal;
        }
    }

    private class PowerURLParameters {

        protected String getFormatedURLParameters(Map<String, String> argUrlParameters) {
            //byte[] retVal;
            String retVal = null;
            StringBuilder stringBuilderUrlParams = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = argUrlParameters.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> urlParameters = iterator.next();
                stringBuilderUrlParams.append(urlParameters.getKey()).append('=').append(urlParameters.getValue());
                if (iterator.hasNext()) {
                    stringBuilderUrlParams.append('&');
                }
            }
            //String strUrlParams = stringBuilderUrlParams.toString();
            //retVal = strUrlParams.getBytes();
            retVal = stringBuilderUrlParams.toString();
            LogWriter.Log("HTTP_REQUESTED_PARAMETERS: " + retVal);
            return retVal;
        }
    }

    public enum HTTPMethod {
        GET("GET"), POST("POST"), DELETE("DELETE");
        private final String methodName;

        HTTPMethod(String argMethodName) {
            this.methodName = argMethodName;
        }

        public String getMethodName() {
            return this.methodName;
        }
    }

    public enum FileType {
        BMP("bmp"), GIF("gif"), JPG("jpg"), PNG("png"), DELETE("DELETE");
        private final String fileType;

        FileType(String argMethodName) {
            this.fileType = argMethodName;
        }

        public String getFileType() {
            return this.fileType;
        }
    }
}
