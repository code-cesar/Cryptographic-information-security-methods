package com.company.Msetting;

import com.company.IO.MInputIO.GetInteger;
import com.company.IO.MInputIO.GetLine;
import com.company.IO.OutputIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class IMenuLabOne implements IMenu {
    private String filePath = null;
    private FileInputStream fileIn = null;
    private FileOutputStream fileOut = null;

    private static int MODULE = 33;
    private static int[] K = {10,27};// Вариант 1-5


    public static int enCode(int a,int b,int character,int m) {
        return (( a * character + b) % m); // Ek(M)=aM+b mod m,
    }


    public static int modInverse(int a, int m){ // нахождение обратного элемента a^-1
        a = a % m; // делим по модулю число a
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)return x;  // перебором находим обратный элемент
        return 1;
    }

    public static int deCode(int a,int b,int character,int m) {
        return (modInverse(a,m) * (character - b)) % m; // Dk(C)= (a^-1 * (C-b)) mod m.
    }

    public void action() {

        int iMenuItem = 0;
        while (true)
        {
            OutputIO.println(" 0) Exit\n" + " 1) Encode\n" + " 2) Decode" );
            filePath = GetLine.get("путь до файла");
            iMenuItem = GetInteger.get("пункт");
            if(iMenuItem != 1 && iMenuItem != 2)return;

            try {
                fileIn = new FileInputStream(filePath);
                fileOut = new FileOutputStream(filePath + (iMenuItem == 1 ? "E" : "D"));

                while (fileIn.available() > 0)
                {
                    int oneByte = fileIn.read(); // Читаем по символьно
                    fileOut.write(
                            iMenuItem == 1 ? enCode(K[0],K[1],oneByte,MODULE) : deCode(K[0],K[1],oneByte,MODULE)
                    );// Записываем зашифррованный символ, либо расшифрованный. Смотря какой пункт был выбран.
                }

                fileIn.close();
                fileOut.close();
            }catch(FileNotFoundException ex) {
                OutputIO.println(ex.getMessage());
            }catch (IOException ex) {
                OutputIO.println(ex.getMessage());
            }
        }
    }

    public String getTitle()
    {
        return "Аффинный шифр";
    }

}
