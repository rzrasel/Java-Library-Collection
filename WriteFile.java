/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlitejdbcdriverconnection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rz Rasel
 */
public class WriteFile {
    public static void onFileWrite(String argFilePath, String argFileData) {
        FileWriter fileWriter = null;
        File file = null;
        try {
            file = new File(argFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            fileWriter.write(argFileData);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("File written Succesfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
