package com.company;

import com.company.books.Book;
import com.company.books.DigitalBook;
import com.company.books.PaperBook;
import com.company.users.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Library {

    private User[] users;
    private Book[] books;

    public Library() {
        users = new User[0];
        books = new Book[0];
        initUsers("./resources/users.txt");
        initBooks("./resources/books.txt");
    }

    public User[] getUsers() {
        return users;
    }

    public Book[] getBooks() {
        return books;
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
        String[] mass = str.split("\\| ");
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

    public void printUsers(){
        for (User u: users) {
            System.out.println(u);
        }
    }

    public void printBooks(){
        for (Book b: books) {
            if (b instanceof DigitalBook) {
                System.out.println("eBook " + b);
            } else {
                System.out.println("pBook " + b);
            }
        }
    }

    public void findByAuthor(String name){
        boolean found = false;
        for (Book b: books) {
            if (b.getAuthor().contains(name)){
                System.out.println(b);
                found = true;
            }
        }
        if (!found){
            System.out.println("nothing found");
        }
    }

    public void findByTitle(String title){
        boolean found = false;
        for (Book b: books) {
            if (b.getTitle().contains(title)){
                System.out.println(b);
                found = true;
            }
        }
        if (!found){
            System.out.println("nothing found");
        }
    }

    public void insertBook(Book book) {
        String line = buildLine(book);
        File file = new File("./resources/books.txt");
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addBook(book);
    }

    private String buildLine(Book book){
        String line;
        String type;
        String param;
        if (book instanceof DigitalBook){
            type = "eBook";
            param = ((DigitalBook) book).getTextLink();
        } else {
            type = "pBook";
            param = String.valueOf(((PaperBook) book).isInAccess());
        }
        line = "\n" + type + "| " + book.getTitle() + "| " +
                book.getAuthor() + "| " + book.getPages() + "| " + param;
        return line;
    }
}
