package com.company.Msetting;

import com.company.IO.MInputIO.GetInteger;
import com.company.IO.MInputIO.GetLine;
import com.company.IO.OutputIO;


public class IMenuLabSix implements IMenu { // вариант 6-5 f(x,k)= (x<<1)⊕(x<<6)⊕k
    private byte K = 0; //

    private int getKeyByte()
    {
        return K & 0xff;
    }

    private static int leftRotate(int n, int d) { // Циклический сдвиг влево
        int res = ((n << d) | (n >> (8 - (d % 8))));
        return ((byte)res & 0xff); // Так как в java byte знаковый, то есть -128..128, используем & 0xff, чтобы сделать byte беззнаковым
    }


    private static String formationEndText(int X) { // Формирования конечного текса из 8 символов(бит)
        return String.format("%8s",Integer.toBinaryString(X)).replaceAll(" ", "0");
    }

    private int function(int x) { // функция (x<<1)⊕(x<<6)⊕k
        int res = (leftRotate(x, 2) ^ leftRotate(x, 4) ^ getKeyByte());
        return ((byte)res & 0xff);
    }

    private int [] feistelCell(int[] Xi, boolean state) { // Формирования ячейки Фейстеля
        int[] X = new int[2];
        if(state == false) {
            X[0] = Xi[1]; // Li = Ri-1
            X[1] = ((byte) (Xi[0] ^ function(Xi[1])) & 0xff); // Ri = Li-1 ⊕ F(Ri-1)
        }
        else {
            X[0] = ((byte) (Xi[1] ^ function(Xi[0])) & 0xff); // Li = Ri-1 ⊕ F(Li-1)
            X[1] = Xi[0]; // Ri = Li-1
        }
        return X;
    }

    private int [] formationBinaryEight(String text, int bit) { // Разделение 16 бит, по 8 бит, L и R
        String binaryEight = "";
        int[] X = new int[2];
        for(int j = 0; j < 8; j ++)binaryEight = binaryEight + text.charAt(bit+j);
        X[0] = Integer.parseInt(binaryEight,2); // узнаём столбец в S5 блоки
        binaryEight = "";
        for(int j = 8; j < 16; j ++)binaryEight = binaryEight + text.charAt(bit+j);
        X[1] = Integer.parseInt(binaryEight,2); // узнаём столбец в S5 блоки
        return X;
    }

    private String stringToBinary(String s) { // Перевод STRING в двоичный код

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            answer.append(String.format("%16s",Integer.toBinaryString(c)).replaceAll(" ", "0"));
            // Используется 16 бит , для mod 16
        }
        return answer.toString();
    }

    private String binaryToString(String s){ // Перевод двоичного кода в STRING
        String endText = "";
        String binary = "";
        for(int i = 0; i < s.length(); i += 16) // пробегаемся по 12 символов
        {
            binary = "";
            for(int j = 0; j < 16 && i+j < s.length(); j ++)binary = binary + s.charAt(i + j); // формируем двоичную запись
            endText = endText + ((char)Integer.parseInt( binary,2)); // Переводим в число, а далее в символ
        }
        return endText;
    }

    private String crypt(String Text,boolean state) // Функция принимает текст и статус. False - encrypt, True - decrypt
    {
        String binaryText = ""; // Хранит двоичный текст
        String endText = ""; // Хранит зашифрованный двоичный текст

        binaryText = stringToBinary(Text); // Перевод текст в двоичное представление
        OutputIO.println("Текст в двоичном представлений:");
        OutputIO.println(binaryText);
        for(int i = 0; i < binaryText.length(); i += 16)
        {
            int[] X = formationBinaryEight(binaryText,i);
            for(int j = 0; j < 3; j++) X = feistelCell(X,state);
            endText = endText + formationEndText(X[0]) + formationEndText(X[1]);
        }
        OutputIO.println( ( !state ? "Зашифрованный" : "Расшифрованный" ) + " текст в двоичном представлений:\n" + endText);
        return binaryToString(endText);
    }


    public void action() {

        int iMenuItem = 0;
        String text = null;
        while (true) {
            OutputIO.println(" 0) Exit\n" + " 1) Encrypt\n" + " 2) Decrypt\n");
            iMenuItem = GetInteger.get("пункт");
            text = GetLine.get("текст");
            K = (byte)(GetInteger.getRange("ключ от 0 до 255",0,255) & 0xff); // так как ключ 8 бит, 2^8=256
            switch(iMenuItem)
            {
                case 1:OutputIO.println("Зашифрованный текст:\n" + crypt(text,false)); break;
                case 2:OutputIO.println("Расшифрованный текст:\n" + crypt(text,true)); break;
                default:return;
            }
        }

    }

    public String getTitle()
    {
        return "Элементы блочных шифров. Сеть Фейстеля";
    }

}
