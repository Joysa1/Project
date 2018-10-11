package com.company;
import entity.*;
public class Main {

    public static void main(String[] args) {
	// write your code here
        User u  = new User();

       try{  u.setNumber("asdsadas+375292576771asdaeqwe");
           System.out.println(u.getNumber());
       }
       catch (IllegalArgumentException e )
       {
           System.out.println("Ошибка");
       }
    }
}
