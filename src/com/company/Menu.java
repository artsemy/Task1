package com.company;

import com.company.books.Book;
import com.company.books.DigitalBook;
import com.company.books.PaperBook;
import com.company.library.Library;
import com.company.users.User;

import java.util.Scanner;

public class Menu {

    private boolean status;
    private Library library;

    public Menu() {
        library = new Library();
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
            User user = library.findLoginUser(login, password);
            if (user != null){
                correct = true;
                status = user.isAdmin();
                System.out.println("we are in!!!");
            } else {
                System.out.println("incorrect login or password");
            }
        }
    }

    private void libMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        printMainCommands();
        while (!exit){
            System.out.println("main menu");
            String command = scanner.nextLine();
            switch (command){
                case "commands":
                    printMainCommands();
                    break;
                case "login":
                    login();
                    break;
                case "print_books":
                    formatPrintBooks();
                    break;
                case "print_users":
                    if (status){
                        library.printUsers();
                    } else {
                        System.out.println("only admins can use it");
                    }
                    break;
                case "add_book":
                    if (status){
                        addBook();
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

    private void printMainCommands(){
        System.out.println("'commands' - to print commands");
        System.out.println("'login' - to change user");
        System.out.println("'print_books' - to print all books");
        if (status){
            System.out.println("'print_users' - to print all users");
            System.out.println("'add_book' - to add book");
        }
        System.out.println("'find' - to start search");
        System.out.println("'exit' - to exit");
    }

    private void formatPrintBooks(){
        System.out.println("'next' - to next page");
        System.out.println("'prev' - to prev page");
        System.out.println("'exit' - to exit");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int page = 1;
        if (library.hasPage(page)){
            library.printBooks(page);
        }
        while (!exit){
            String command = scanner.nextLine();
            switch (command){
                case "next":
                    page++;
                    if (library.hasPage(page)){
                        System.out.println("page = " + page);
                        library.printBooks(page);
                    } else {
                        page--;
                        System.out.println("no next page");
                    }
                    break;
                case "prev":
                    page--;
                    if (library.hasPage(page)){
                        System.out.println("page = " + page);
                        library.printBooks(page);
                    } else {
                        page++;
                        System.out.println("no prev page");
                    }
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("bad command");
            }
        }
    }

    private void find(){
        Scanner scanner = new Scanner(System.in);
        printSearchCommands();
        boolean exit = false;
        while (!exit) {
            System.out.println("search menu");
            String command = scanner.nextLine();
            switch (command) {
                case "author":
                    System.out.println("insert name of author");
                    command = scanner.nextLine();
                    library.findByAuthor(command);
                    break;
                case "title":
                    System.out.println("insert title");
                    command = scanner.nextLine();
                    library.findByTitle(command);
                    break;
                case "exit":
                    exit = true;
                    break;
                case "commands":
                    printSearchCommands();
                    break;
                default:
                    System.out.println("bad command");
            }
        }
    }

    private void printSearchCommands(){
        System.out.println("'author' - to find by author");
        System.out.println("'title' - to find by title");
        System.out.println("'exit' - to exit search");
        System.out.println("'commands' - to print all commands");
    }

    private void addBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("insert type of book: 'eBook' or 'pBook'");
        String type = scanner.nextLine();
        Book book;
        System.out.println("insert title");
        String title = scanner.nextLine();
        System.out.println("insert author");
        String author = scanner.nextLine();
        System.out.println("insert pages");
        int pages = scanner.nextInt();
        if (type.equals("eBook")){
            System.out.println("insert link");
            String link = scanner.nextLine();
            link = "./resources/text.txt";
            book = new DigitalBook(title, author, pages, link);
        } else {
            System.out.println("insert is in library: 'true' or 'false'");
            Boolean inAccess = scanner.nextBoolean();
            book = new PaperBook(title, author, pages, inAccess);
        }
        library.insertBook(book);
        System.out.println("added: " + book);
    }
}
