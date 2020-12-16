package com.company.Exception;

public class ValidationExceptionMenu extends Exception
{
        public ValidationExceptionMenu(String message) {
            super("[Menu Exception] " + message);
        }
}
