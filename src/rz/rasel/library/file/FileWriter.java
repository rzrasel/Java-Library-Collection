/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rz.rasel.library.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Rz Rasel
 */
public class FileWriter {

    public static void onWrite(String argFile, String argData) {
        String encoding = "UTF-8";
        File file = null;
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        //writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("filename.txt"), "utf-8")); writer.write("Something");
        try {
            file = new File(argFile);
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, encoding);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(argData);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(OnFileWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            //Logger.getLogger(OnFileWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(OnFileWrite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                /*if (fileOutputStream != null) {
                 fileOutputStream.close();
                 }
                 if (outputStreamWriter != null) {
                 outputStreamWriter.close();
                 }*/
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException ex) {
                //Logger.getLogger(OnFileWrite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
