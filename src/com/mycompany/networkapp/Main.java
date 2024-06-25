package com.mycompany.networkapp;

import com.mycompany.networkapp.ui.ClientUI;
import com.mycompany.networkapp.ui.ServerUI;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> ServerUI.main(args)).start();
        new Thread(() -> ClientUI.main(args)).start();
    }
}
 
