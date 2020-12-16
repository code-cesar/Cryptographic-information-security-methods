package com.company.Msetting;

import com.company.IO.MInputIO.GetInteger;
import com.company.IO.MInputIO.GetLine;
import com.company.IO.OutputIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;


public class IMenuLabFour implements IMenu {
    private String enCodeText = null;
    

    public void action() {

        int iMenuItem = 0;
        while (true) {
            OutputIO.println(" 0) Exit\n" + " 1) Action\n");
            enCodeText = GetLine.get("текст");

            iMenuItem = GetInteger.get("пункт");
            if (iMenuItem != 1) return;
            int[] K = {17, 9};
            int module = 32;
            char[] chEnCode = enCodeText.toCharArray();
            for (int i = 0; i < enCodeText.length(); i++) {
                OutputIO.print("" + (chEnCode[i] - 'a') + ",");
            }
            OutputIO.println("");
            for (int i = 0; i < enCodeText.length(); i++) {
                char deCode = (char) (((K[0] * (chEnCode[i] - 'a') + K[1]) % module) + 'a');
                OutputIO.print("" + deCode);
            }
            OutputIO.println("");
            for (int i = 0; i < enCodeText.length(); i++) {
                int deCode =  (((K[0] * (chEnCode[i] - 'a') + K[1]) % module));
                OutputIO.print("" + deCode + ",");
            }
            OutputIO.println("");
        }

    }

    public String getTitle()
    {
        return "Криптоанализ аффинного шифра";
    }

}
