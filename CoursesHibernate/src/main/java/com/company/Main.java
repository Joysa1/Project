package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        UserService service = new UserService();
        User user = new User();
        user.setAdmin(true);
        user.setAge(20);
        user.setLastname("Proverka2");
        user.setName("Proverka2");
        user.setNumber("+375292576771");
        user.setPassword("sad2");
        user.setSurname("Proverka2");
        user.setUsername("Proverka2");
        service.saveUser(user);
    }
}
