/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rz Rasel
 */
public class OnDirectory {

    public static String cacheDirectory = "app-dir/cache/";
    private boolean isFirstRun = false;
    private String dirPath = "";

    public static void main(String args[]) {
        //onCreate(new OnCreateDri(), "test");
    }

    public static void onCreate(String argDirName) {
        if (argDirName.isEmpty()) {
            System.out.println("DEBUG_PRINT: directory name is empty");
            return;
        }
        OnDirectory onDirectory = new OnDirectory();
        //String directoryName = onDirectory.onDirPath(argDirName);
        //System.out.println("DEBUG_PRINT: directory name " + directoryName);
        String[] directories = argDirName.split("/");
        onDirectory.onMkDir(directories, 0);
    }

    /*private String onDirPath(String argDirName) {
        Package pack = object.getClass().getPackage();
        String PATH = argDirName;
        //String directoryName
        return cacheDirectory + "/" + pack.getName().concat("/" + PATH + "/");
    }*/

    private void onMkDir(String[] argDirectories, int argCounter) {
        if (argCounter >= argDirectories.length) {
            return;
        }
        dirPath += argDirectories[argCounter] + "/";
        String getTimeStamp = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());
        String fileName = getTimeStamp + ".txt";
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdir();
            //System.out.println("DEBUG_PRINT: directory created " + directoryName);
        }
        /*System.out.println("DEBUG_PRINT: created "
                + directory
                + ", Len: " + argDirectories.length
                + ", Counter: " + argCounter);*/
        argCounter++;
        onMkDir(argDirectories, argCounter);
    }
}
/*
String getTimeStamp = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());
String fileName = getTimeStamp + ".txt";
File directory = new File(dirPath);
File file = new File(directoryName + "/" + fileName);
*/