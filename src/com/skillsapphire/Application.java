package com.skillsapphire;

import com.skillsapphire.controller.EmsController;

public class Application {

    public static void main(String[] args) {
        EmsController emsController = new EmsController();
        emsController.displayMenu();
    }
}
