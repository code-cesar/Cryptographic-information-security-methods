package com.company.IO;

public class OutputIO
{
    public static String lastSafeMessage = "None";
    public static void print(String message)
    {
        System.out.print(message);
    }
    public static void println(String message)
    {
        System.out.println(message);
    }
    public static void printlnSafe(String message)
    {
        lastSafeMessage = message;
        System.out.println(message);
    }
    public static void SafeMessage(String message)
    {
        lastSafeMessage = message;
    }
    public static void printf(String message,Object ...args)
    {
        System.out.printf(message,args);
    }

}
