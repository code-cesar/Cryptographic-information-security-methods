package com.company.IO;

import java.util.Scanner;

public  class InputIO {
    private static Scanner _scanner = null;

    protected static Scanner getScanner()
    {
        if (_scanner == null)
        {
            _scanner = new Scanner(System.in);
        }
        return _scanner;
    }

}
