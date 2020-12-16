package com.company.Msetting;

import java.io.FileNotFoundException;

public interface IMenu {
    void action() throws FileNotFoundException;
    String getTitle();

}