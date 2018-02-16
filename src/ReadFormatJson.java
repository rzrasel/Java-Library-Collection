
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rz Rasel
 */
public class ReadFormatJson {

    private String filePath = "app-jar-libs/json.txt";

    public static void main(String args[]) {
        ReadFormatJson readFormatJson = new ReadFormatJson();
        String fileData = readFormatJson.onReadSDCard(readFormatJson.filePath);
        //System.err.println("FILE DATA: " + fileData);
        /*if (readFormatJson.isValidJson(fileData)) {
            //
        } else {
            System.err.println("Json error");
        }*/
        readFormatJson.onFormat(fileData);
    }

    private String onReadSDCard(String argFilePath) {
        InputStream inputStream = null;
        StringBuilder stringBuilder = null;
        try {
            File file = new File(argFilePath);
            inputStream = new FileInputStream(file);
            //Reader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            /*Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            System.err.println("READER: " + reader.toString());
            int data = reader.read();
            stringBuilder = new StringBuilder();
            while (data != -1) {
                char theChar = (char) data;
                data = reader.read();
                stringBuilder.append(theChar);
            }
            reader.close();*/
            stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            boolean flag = false;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                stringBuilder.append(line);
                //System.out.println(line);
                //result.append(flag ? newLine : "").append(line);
                flag = true;
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(ReadFormatJson.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(ReadFormatJson.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                Logger.getLogger(ReadFormatJson.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return stringBuilder.toString();
    }

    public boolean isValidJson(String argJsonData) {
        try {
            new JSONObject(argJsonData);
        } catch (JSONException e) {
            Logger.getLogger(ReadFormatJson.class.getName()).log(Level.SEVERE, null, e);
            try {
                new JSONArray(argJsonData);
            } catch (JSONException ex) {
                Logger.getLogger(ReadFormatJson.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    private void onFormat(String argJson) {
        argJson = argJson.replaceAll("\\s+", " ").trim();
        argJson = argJson.trim();
        //argJson = argJson.replaceAll(" "," ");
        String[] parts = argJson.split("\\[");
        argJson = "[" + parts[1];
 /*System.out.println(parts.length);
        System.out.println(argJson);
        System.out.println("TEST");*/
        try {
            //JSONParser jsonParser = new JSONParser();
            //JSONObject jsonObject = new JSONObject(argJson);
            //JSONArray jsonArray = new JSONArray(new JSONTokener(argJson));
            //argJson = "[" + argJson + "]";
            //System.out.println(argJson);
            JSONArray json = new JSONArray(argJson);
            System.out.println("#EXTM3U");
            JSONArray jsonArray = new JSONArray(argJson.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.get(i) instanceof JSONObject) {
                    JSONObject jsnObj = (JSONObject) jsonArray.get(i);
                    String chaName = (String) jsnObj.get("channel_name");
                    String chaURL = (String) jsnObj.get("channel_url");
                    System.out.println("");
                    System.out.println("#EXTINF: " + chaName);
                    System.out.println("" + chaURL);
                }
            }
        } catch (JSONException e) {
            Logger.getLogger(ReadFormatJson.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
