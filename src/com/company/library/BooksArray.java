package com.company.library;

import com.company.books.Book;
import com.company.books.DigitalBook;
import com.company.books.PaperBook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class BooksArray {

    private Book[] books;

    public BooksArray() {
        books = new Book[0];
        initBooks("./resources/books.txt");
    }

    public BooksArray(Book[] books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
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
            book = new DigitalBook(mass[1], mass[2], Integer.parseInt(mass[3]), mass[4]);
        } else {
            book = new PaperBook(mass[1], mass[2], Integer.parseInt(mass[3]),Boolean.parseBoolean(mass[4]));
        }
        return book;
    }

    private void addBook(Book book){
        books = Arrays.copyOf(books, books.length+1);
        books[books.length-1] = book;
    }

    public void printPageBooks(int start, int number){
        for (int i = start; i < start+number; i++) {
            printBook(books[i]);
        }
    }

    public void printBook(Book book){
        if (book instanceof DigitalBook) {
            System.out.println("eBook " + book);
        } else {
            System.out.println("pBook " + book);
        }
    }

    public void print(){
        for (Book b: books) {
            System.out.println(b);
        }
    }

    public BooksArray findByAuthor(String name){
        BooksArray array = new BooksArray();
        for (Book b: books) {
            if (b.getAuthor().contains(name)){
                array.addBook(b);
            }
        }
        return array;
    }

    public BooksArray findByTitle(String title){
        BooksArray array = new BooksArray();
        for (Book b: books) {
            if (b.getTitle().contains(title)){
                array.addBook(b);
            }
        }
        return array;
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
