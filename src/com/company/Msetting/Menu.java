package com.company.Msetting;

import com.company.Exception.ValidationExceptionMenu;

import com.company.IO.OutputIO;
import com.company.IO.MInputIO.GetInteger;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private static List<IMenu> _menuItems = null;

    public static List<IMenu> getMenuItems()
    {
        if (_menuItems == null)
        {
            _menuItems = new ArrayList<IMenu>();
            _menuItems.add(new IMenuExit());
            _menuItems.add(new IMenuLabOne());
            _menuItems.add(new IMenuLabTwo());
            _menuItems.add(new IMenuLabFour());
            _menuItems.add(new IMenuLabFive());
            _menuItems.add(new IMenuLabSix());
            _menuItems.add(new IMenuLabSeven());
        }
        return _menuItems;
    }

    public static void print()
    {
        List<IMenu> menuItems = getMenuItems();
        OutputIO.println("========================================\n" +
                "Криптографические методы защиты информации\n" +
                "Вариант: 5\n" +
                "========================================");
        for (int iMenu = 0; iMenu < menuItems.size(); iMenu++)
        {
            OutputIO.println("  " + iMenu + ") " + menuItems.get(iMenu).getTitle());
        }
    }

    public static IMenu getSelectedIMenu()
    {
        List<IMenu> menuItems = getMenuItems();
        int iMenuItem = 0;
        while (true)
        {
            iMenuItem = GetInteger.get("пункт в меню");
            try {
                if ((iMenuItem < 0) || (iMenuItem >= menuItems.size()))
                    throw new ValidationExceptionMenu("ERROR: Пунки меню должен быть >=0 и <" + menuItems.size() );

                return menuItems.get(iMenuItem);
            }catch (ValidationExceptionMenu ex)
            {
                OutputIO.println(ex.getMessage());
                continue;
            }

        }
    }
}