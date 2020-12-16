package com.company;
import com.company.Msetting.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        while(true)
        {
            Menu.print();
            IMenu IMenuSelected = Menu.getSelectedIMenu();
            IMenuSelected.action();
        }
    }
}