package com.company;

import com.company.books.Book;
import com.company.books.DigitalBook;
import com.company.books.PaperBook;
import com.company.users.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Library {

    private User[] users;
    private Book[] books;
    private boolean status;

    public Library() {
        users = new User[0];
        books = new Book[0];
        status = false;
        initUsers("./resources/users.txt");
        initBooks("./resources/books.txt");
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
        User user = new User(mass[0], mass[1], Boolean.parseBoolean(mass[2]));
        return user;
    }

    private void addUser(User user){
        users = Arrays.copyOf(users, users.length+1);
        users[users.length-1] = user;
    }

    private void initBooks(String sPath){
        String str;
        Path path = Paths.get(sPath);
        try (Scanner scanner = new Scanner(path)) {
            if (scanner.hasNext()){
                str = scanner.nextLine();
                Book book = buildBook(str);
                addBook(book);
            }
            while (scanner.hasNext()) {
                str = scanner.nextLine();
                Book book = buildBook(str);
                addBook(book);
            }
        } catch (IOException ignored) {
        }
    }

    private Book buildBook(String str){
        Book book;
        String[] mass = str.split(" ");
        if (mass[0].equals("eBook")){
            String text = "./resources/text.txt";
            book = new DigitalBook(mass[1], mass[2], Integer.parseInt(mass[3]), text);
        } else {
            book = new PaperBook(mass[1], mass[2], Integer.parseInt(mass[3]),Boolean.parseBoolean(mass[4]));
        }
        return book;
    }

    private void addBook(Book book){
        books = Arrays.copyOf(books, books.length+1);
        books[books.length-1] = book;
    }

    private void printUsers(){
        for (User u: users) {
            System.out.println(u);
        }
    }

    private void printBooks(){
        for (Book b: books) {
            if (b instanceof DigitalBook) {
                System.out.println("eBook " + b);
            } else {
                System.out.println("pBook " + b);
            }
        }
    }

    public void run(){
        login();
        libMenu();
    }

    private void login(){
        Scanner scanner = new Scanner(System.in);
        boolean correct = false;
        while (!correct) {
            System.out.println("insert login:");
            String login = scanner.nextLine();
            System.out.println("insert password");
            String password = scanner.nextLine();
            User user = findLoginUser(login, password);
            if (user != null){
                correct = true;
                status = user.isAdmin();
                System.out.println("we are in!");
            } else {
                System.out.println("incorrect login or password");
            }
        }
    }

    private User findLoginUser(String login, String password){
        String newPassword = cryptPassword(password);
        for (User u: users) {
            if (u.getLogin().equals(login) && u.getPassword().equals(newPassword)){
                return u;
            }
        }
        return null;
    }

    private String cryptPassword(String password){
        char[] mass = password.toCharArray();
        for (int i = 0; i < mass.length; i++) {
            mass[i] = (char) (mass[i] - 15);
        }
        return String.valueOf(mass);
    }

    private void libMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        printCommands();
        while (!exit){
            String command = scanner.nextLine();
            switch (command){
                case "commands":
                    printCommands();
                case "login":
                    login();
                    break;
                case "print_books":
                    printBooks();
                    break;
                case "print_users":
                    if (status){
                        printUsers();
                    } else {
                        System.out.println("only admins can use it");
                    }
                    break;
                case "find":
                    find();
                    break;
                case "exit":
                    exit = true;
                    System.out.println("bye-bye");
                    break;
                default:
                    System.out.println("bad command");
            }
        }
    }

    private void printCommands(){
        System.out.println("'commands' - to print commands");
        System.out.println("'login' - to change user");
        System.out.println("'print_books' - to print all books");
        if (status){
            System.out.println("'print_users' - to print all users");
        }
        System.out.println("'find' - to start search");
        System.out.println("'exit' - to exit");
    }

    private void find(){
        System.out.println("found");
    }
}
