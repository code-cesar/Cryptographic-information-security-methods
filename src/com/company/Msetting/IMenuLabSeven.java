package com.company.Msetting;

import java.util.HashMap;
import java.util.Map;
import com.company.IO.OutputIO;


public class IMenuLabSeven implements IMenu { // вариант 7-5
    public static double log2(double d) {
        return Math.log(d)/Math.log(2.0);
    }

    public void action() {

        String text = "Глухая пора листопада, Последних гусей косяки. Расстраиваться не надо: У страха глаза велики." +
                "Пусть ветер, рябину занянчив, Пугает ее перед сном. Порядок творенья обманчив, Как сказка с хорошим концом.";
        text = text.toLowerCase().replaceAll("\\p{Punct}", "").replace(" ", "").trim();

        Map<String, Integer> hashMap = new HashMap<>();
        OutputIO.println(" " + text.length());
        OutputIO.println(text);
        double [] h = new double[5];
        for(int i = 0; i < 5; i++)
        {
            hashMap.clear();
            for(int j = 0; j < text.length() - i; j ++)
            {
                String elem = text.substring(j,j + i + 1);
                hashMap.put(elem,hashMap.containsKey(elem) ? hashMap.get(elem) + 1 : 1);
            }
            for (int value : hashMap.values()) {
                double ab =  ((double)value/text.length());
                h[i] += ab * log2(ab);
            }
            OutputIO.println("h[" + (i+1) + "] " + (-h[i]));
        }

    }

    public String getTitle()
    {
        return "Модель открытого текста";
    }

}
