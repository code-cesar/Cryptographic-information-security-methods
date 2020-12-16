package com.company.Msetting;

import com.company.IO.MInputIO.GetInteger;
import com.company.IO.MInputIO.GetLine;
import com.company.IO.OutputIO;



public class IMenuLabFive implements IMenu { // вариант 5-5

    private int [][] S5 = { // a = 1, b = 6
            {2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
            {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
            {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
            {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}
    };


    private String stringToBinary(String s) { // Перевод STRING в двоичный код

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            answer.append(String.format("%12s",Integer.toBinaryString(c)).replaceAll(" ", "0"));
            // Используется 12 бит , так как 2^12 = 4096 ( если код символа, больше 4095, будет некорректно работать)
        }
        return answer.toString();
    }

    private String binaryToString(String s) // Перевод двоичного кода в STRING
    {
        String endText = "";
        String binary = "";
        for(int i = 0; i < s.length(); i += 12) // пробегаемся по 12 символов
        {
            binary = "";
            for(int j = 0; j < 12 && i+j < s.length(); j ++)binary = binary + s.charAt(i + j); // формируем двоичную запись
            endText = endText + ((char)Integer.parseInt( binary,2)); // Переводим в число, а далее в символ
        }
        return endText;
    }

    private String Encrypt (String enCodeText) // Функция для шифрования
    {
        String binaryText = ""; // Хранит двоичный текст
        String encryptText = ""; // Хранит зашифрованный двоичный текст
        String binaryFour = "";

        binaryText = stringToBinary(enCodeText); // Перевод текст в двоичное представление
        OutputIO.println("Текст в двоичном представлений:");
        OutputIO.println(binaryText);
        for(int i = 0,an = 0, bn = 0; i < binaryText.length(); i += 6)
        {
            an = Integer.parseInt(String.valueOf(binaryText.charAt(i)) + binaryText.charAt(i + 5),2); // узнаём строку в S5 блоки
            binaryFour = "";
            for(int j = 1; j < 5; j ++)binaryFour = binaryFour + binaryText.charAt(i+j);
            bn = Integer.parseInt( binaryFour,2); // узнаём столбец в S5 блоки
            encryptText = encryptText + binaryText.charAt(i) + binaryText.charAt(i + 5) +  String.format("%4s",Integer.toBinaryString(S5[an][bn])).replaceAll(" ", "0");
        }
        return encryptText;
    }

    private String Decrypt (String deCodeText)
    {
        String decryptText = "";
        String binaryFour = "";

        for(int i = 0,an = 0, ab, sElement = 0; i < deCodeText.length(); i += 6)
        {
            an = Integer.parseInt((String.valueOf(deCodeText.charAt(i)) + deCodeText.charAt(i + 1)),2); // узнаём строку в S5 блоки
            binaryFour = "";
            for(int j = 2; j < 6; j ++)binaryFour = binaryFour + deCodeText.charAt(i + j);
            sElement = Integer.parseInt( binaryFour,2); // узнаём число в строке
            for(ab = 0; ab < S5[an].length; ab++) // узнаём столбец, в котором находится число
            {
                if(S5[an][ab] == sElement)break;
            }
            decryptText = decryptText + deCodeText.charAt(i) + String.format("%4s",Integer.toBinaryString(ab)).replaceAll(" ", "0") + deCodeText.charAt(i + 1);
        }

        OutputIO.println("Расшифрованный текст в двоичном представлений:");
        OutputIO.println(decryptText);
        return binaryToString(decryptText);
    }

    public void action() {

        int iMenuItem = 0;
        String text = null;
        while (true) {
            OutputIO.println(" 0) Exit\n" + " 1) Encrypt\n" + " 2) Decrypt\n");
            text = GetLine.get("текст");
            iMenuItem = GetInteger.get("пункт");

            switch(iMenuItem)
            {
                case 1:OutputIO.println("Зашифрованный текст:\n" + Encrypt(text)); break;
                case 2:OutputIO.println(Decrypt(text)); break;
                default:return;
            }
        }

    }

    public String getTitle()
    {
        return "Элементы блочных шифров. S-блоки";
    }

}
