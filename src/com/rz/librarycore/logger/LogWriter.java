/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore.logger;

/**
 *
 * @author Rz Rasel
 */
public class LogWriter {

    public static boolean isDebug = true;

    public static void Log(String argMessage) {
        String buildMessage = "";
        //System.out.println("Package name: " + ("".equals(packageName) ? "[default package]" : packageName));
        String packageName = getCallerPackageName();
        packageName = "".equals(packageName) ? "(default package)." : "";
        buildMessage = "Message: " + argMessage + " |----|> "
                //+ "Package Name: " + getCallerPackageName() + " - "
                + "Class Name: " + packageName + getCallerClassName() + " - "
                + "Method Name: " + getCallerMethodName() + " - "
                + "Line Number: " + getCallerLineNumber();
        if (isDebug) {
            System.out.println("LOG_WRITER_PRINT:- " + buildMessage);
        }
    }

    public static String getCallerPackageName() {
        String className = "";
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        int stackElementsLength = stackTraceElements.length;
        int stackElementsIndexLength = stackElementsLength - 1;
        if (stackElementsIndexLength >= 4) {
            className = stackTraceElements[4].getClassName();
        } else if (stackElementsIndexLength == 3) {
            className = stackTraceElements[3].getClassName();
        } else if (stackElementsIndexLength == 2) {
            className = stackTraceElements[2].getClassName();
            //System.out.println("LN: " + className);
        } else if (stackElementsIndexLength == 1) {
            className = stackTraceElements[1].getClassName();
            //System.out.println("LN: " + className);
        }
        //String packageName = extractPackageName(argClassName);
        //System.out.println("LN: " + stackTraceElements.length);
        if ((null == className) || ("".equals(className))) {
            return "";
        }
        int lastDot = className.lastIndexOf('.');
        if (0 >= lastDot) {
            return "";
        }
        return className.substring(0, lastDot);
    }

    public static String getCallerClassName() {
        /*String callerClassName = new Exception().getStackTrace()[1].getClassName();
        String calleeClassName = new Exception().getStackTrace()[0].getClassName();*/
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int stackElementsLength = stackTraceElements.length;
        int stackElementsIndexLength = stackElementsLength - 1;
        //System.out.println(stackTraceElements.length);
        if (stackElementsIndexLength >= 4) {
            return Thread.currentThread().getStackTrace()[4].getClassName();
        } else if (stackElementsIndexLength == 3) {
            return Thread.currentThread().getStackTrace()[3].getClassName();
        } else if (stackElementsIndexLength == 2) {
            return Thread.currentThread().getStackTrace()[2].getClassName();
        }
        return "";
    }

    public static String getCallerMethodName() {
        /*String callerClassName = new Exception().getStackTrace()[1].getClassName();
        String calleeClassName = new Exception().getStackTrace()[0].getClassName();*/
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int stackElementsLength = stackTraceElements.length;
        int stackElementsIndexLength = stackElementsLength - 1;
        if (stackElementsIndexLength >= 4) {
            return Thread.currentThread().getStackTrace()[4].getMethodName();
        } else if (stackElementsIndexLength == 3) {
            return Thread.currentThread().getStackTrace()[3].getMethodName();
        } else if (stackElementsIndexLength == 2) {
            return Thread.currentThread().getStackTrace()[2].getMethodName();
        }
        return "";
    }

    public static String getCallerLineNumber() {
        /*String callerClassName = new Exception().getStackTrace()[1].getClassName();
        String calleeClassName = new Exception().getStackTrace()[0].getClassName();*/
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int stackElementsLength = stackTraceElements.length;
        int stackElementsIndexLength = stackElementsLength - 1;
        if (stackElementsIndexLength >= 4) {
            return Thread.currentThread().getStackTrace()[4].getLineNumber() + "";
        } else if (stackElementsIndexLength == 3) {
            return Thread.currentThread().getStackTrace()[3].getLineNumber() + "";
        } else if (stackElementsIndexLength == 2) {
            return Thread.currentThread().getStackTrace()[2].getLineNumber() + "";
        }
        return "";
    }
}
/*
http://www.java2s.com/Code/Java/Language-Basics/DisplayStackTraceInformationwithStackTraceElement.htm
 */
