package com.company.IO.MInputIO;

import com.company.IO.*;
import com.company.Exception.ValidationException;
import java.util.Scanner;

public class GetDouble extends InputIO
{
    private static void isValidInput(String varName)throws ValidationException
    {
        if(!getScanner().hasNextDouble())throw new ValidationException("Не является вещественным числом!\n" + "Введите " + varName + ": ");
    }
    public static double get(String varName)
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

        return scan.nextDouble();
    }


}
