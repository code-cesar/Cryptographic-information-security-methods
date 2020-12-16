package com.company.Msetting;

public class IMenuExit implements IMenu
{

    public String getTitle()
    {
        return "Выход из программы";
    }

    public void action()
    {
        System.exit(0);
    }
}
