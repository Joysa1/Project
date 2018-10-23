package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        dataBaseHandler.InsertInUser("Vlad", "Drobovich","Serg",12,"+375292576771","Vlad", "Vlad",true);
        System.out.println( dataBaseHandler.SelectFromDb("Joysa", "sad"));
    }
}
