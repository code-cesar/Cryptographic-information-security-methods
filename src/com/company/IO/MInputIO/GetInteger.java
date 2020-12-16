package com.company.IO.MInputIO;

import com.company.Exception.ValidationException;
import com.company.IO.*;
import java.util.Scanner;

public class GetInteger extends InputIO
{
    private static void isValidInput(String varName)throws ValidationException
    {
        if(!getScanner().hasNextInt())throw new ValidationException("Не является целочисленным числом!\n" + "Введите " + varName + ": ");
    }

    private static void isValidtRange(int outInt,int a, int b)throws ValidationException
    {
        if(outInt < a || outInt > b)throw new ValidationException("Число " + outInt +" должно находиться в диапазоне от " + a + " до " + b);
    }

    public static int get(String varName)
    {
        Scanner scan = getScanner();

        OutputIO.print("Введите " + varName + ": ");

        while(true)
        {
            try
            {
                isValidInput(varName);
                break;
            }catch (ValidationException ex)
            {

                OutputIO.println(ex.getMessage());
                scan.skip(".*\n");
            }
        }
        int outInt = scan.nextInt();
        scan.skip(".*\n");

        return outInt;
    }

    public static int getRange(String varName,int a, int b)
    {
        Scanner scan = getScanner();
        int outInt;
        OutputIO.print("Введите " + varName + ": ");

        while(true)
        {
            try
            {
                isValidInput(varName);
                outInt = scan.nextInt();
                isValidtRange(outInt,a,b);
                scan.skip(".*\n");
                break;
            }catch (ValidationException ex)
            {
                OutputIO.println(ex.getMessage());
                scan.skip(".*\n");
            }
        }

        return outInt;
    }

}
