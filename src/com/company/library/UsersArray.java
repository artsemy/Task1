package com.company.library;

import com.company.books.Book;
import com.company.users.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class UsersArray {

    private User[] users;

    //init users
    public UsersArray() {
        users = new User[0];
        initUsers("./resources/users.txt");
    }

    //get method
    public User[] getUsers() {
        return users;
    }

    //set method
    public void setUsers(User[] users) {
        this.users = users;
    }

    //read users from file
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

    //build user from string line
    private User buildUser(String str){
        String[] mass = str.split(" ");
        User user = new User(mass[0], mass[1], Boolean.parseBoolean(mass[2]), mass[3]);
        return user;
    }

    //add user
    private void addUser(User user){
        users = Arrays.copyOf(users, users.length+1);
        users[users.length-1] = user;
    }

    //print users
    public void printUsers(){
        for (User u: users) {
            System.out.println(u);
        }
    }

    //send emails when added book
    //!!! error without correct input
    public void newBookMassage(User user, Book b){
        String msg = b.toString() + "\nfrom: " + user.getLogin() + ", " + user.getEmail();
        String sub = "new book: " + b.toString();
        String from = "from"; //use actual email address to sent from
        String pass = "pass";  //use actual email pass
        String to = "to"; //use actual email to get message
        for (int i = 0; i < users.length; i++) {
            if (!users[i].equals(user)){
                to = users[i].getEmail();
                //error without correct input
//                Mailer.send(from, pass, to, sub, msg);
            }
        }
    }

    //send email with offered book
    //!!! error without correct input
    public void offerBookMessage(String userName, String link){
        String msg = "from: " + userName + " link: " + link;
        String sub = "offer book";
        String from = "from"; //use actual email address to sent from
        String pass = "pass"; //use actual email pass
        String to = "to"; //use actual email to get message
        for (int i = 0; i < users.length; i++) {
            if (users[i].isAdmin()){
                to = users[i].getEmail();
                //error without correct input
//                Mailer.send(from, pass, to, sub, msg);
            }
        }
    }

}
