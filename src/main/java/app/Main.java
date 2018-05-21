package app;

import gui.main.LoginMenu;

import java.io.File;

/*
Main class which we use for running program.
*/
public class Main {


    public static void main(String[] args){
        boolean f = new File("logs").mkdir();//create logs directory
        LoginMenu l = new LoginMenu();
        l.actionLogin();








    }
}
