package com.epam.paltarzhytski;

import java.util.Scanner;


public class User {
    private static Scanner scan1;

	public static String Command(String request){
        System.out.print(request+" ---> ");
        scan1 = new Scanner(System.in);
        String command=scan1.next();
        return command;
    }   
}
