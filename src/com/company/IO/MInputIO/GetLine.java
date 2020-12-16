package com.company.IO.MInputIO;

import com.company.IO.InputIO;
import com.company.IO.OutputIO;

import java.util.Scanner;

public class GetLine extends InputIO {

    public static String get(String varName)
    {
        Scanner scan = getScanner();
        OutputIO.print("Введите " + varName + ": ");
        return scan.nextLine();
    }
}
