package com.company.library;

import com.company.users.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class UsersArray {

    private User[] users;

    public UsersArray() {
        users = new User[0];
        initUsers("./resources/users.txt");
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    private void initUsers(String sPath){
        String str;
        Path path = Paths.get(sPath);
        try (Scanner scanner = new Scanner(path)) {
            if (scanner.hasNext()){
                str = scanner.nextLine();
                User user = buildUser(str);
                addUser(user);
            }
            while (scanner.hasNext()) {
                str = scanner.nextLine();
                User user = buildUser(str);
                addUser(user);
            }
        } catch (IOException ignored) {
        }
    }

    private User buildUser(String str){
        String[] mass = str.split(" ");
        User user = new User(mass[0], mass[1], Boolean.parseBoolean(mass[2]), mass[3]);
        return user;
    }

    private void addUser(User user){
        users = Arrays.copyOf(users, users.length+1);
        users[users.length-1] = user;
    }

    public void printUsers(){
        for (User u: users) {
            System.out.println(u);
        }
    }
}
