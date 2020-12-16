package com.company.Msetting;

import com.company.IO.MInputIO.GetInteger;
import com.company.IO.MInputIO.GetLine;
import com.company.IO.OutputIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class IMenuLabTwo implements IMenu {
    private String filePath = null;
    private FileInputStream fileIn = null;
    private FileOutputStream fileOut = null;

    private static int MODULE = 6;
    private static int[] _KEY = {2, 1, 4, 3, 6, 5};// Вариант 2-5

    public static byte[] permutation(byte[] m, int[] key) { // перестановка
        byte[] result = new byte[MODULE];
        for(int i = 0; i < key.length; i ++) {
            result[key[i]-1] = m[i]; // записываем на key[i]-1 место символ m[i]
        }
        return result;
    }


    public static int[] reversePermutation(int[] key){ // нахождение обратную перестановку
        int[] result = new int[MODULE];
        for(int i = 0; i < key.length; i ++) result[key[i]-1] = i+1; // на место key[i]-1 записываем число i+1, таким образом получаем обратную перестановку.
        return result;
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


                byte[] oneByte = new byte [MODULE];
                int sizeReadFile = 0; // кол-во считанных байт
                while((sizeReadFile = fileIn.read(oneByte)) != -1) {
                    if(sizeReadFile != MODULE) { // если кол-во считанных байт меньше нашего модуля, то мы дошли до конца
                        fileIn.getChannel().position(0); // перемешаем указатель в начало
                        for(int i = sizeReadFile; i < MODULE; i ++) oneByte[i] = (byte)fileIn.read(); // записываем в конец не достающие символы из начала
                        fileOut.write(permutation(oneByte,(iMenuItem == 1 ? _KEY : reversePermutation(_KEY))));
                        break;
                    }
                    fileOut.write(permutation(oneByte,(iMenuItem == 1 ? _KEY : reversePermutation(_KEY))));
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
        return "Шифр простой перестановки";
    }

}
