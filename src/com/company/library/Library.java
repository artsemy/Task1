package com.company.library;

import com.company.books.Book;
import com.company.users.User;

public class Library {

    private BooksArray booksArray;
    private UsersArray usersArray;
    private int pageSize = 5;

    public Library() {
        booksArray = new BooksArray();
        usersArray = new UsersArray();
    }

    public User findLoginUser(String login, String password){
        String newPassword = cryptPassword(password);
        for (User u: usersArray.getUsers()) {
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

    public void printBooks(int page){
        int k1 = booksArray.getBooks().length / pageSize;
        int k2 = booksArray.getBooks().length % pageSize;
        if (page <= k1){
            int start = (page - 1) * pageSize;
            booksArray.printPageBooks(start, pageSize);
        } else {
            int start = (page - 1) * pageSize;
            booksArray.printPageBooks(start, k2);
        }
    }

    public boolean hasPage(int page){
        return page <= countPages() && page > 0;
    }

    private int countPages(){
        int k1 = booksArray.getBooks().length / pageSize;
        int k2 = booksArray.getBooks().length % pageSize;
        if (k2 == 0){
            return k1;
        } else {
            return k1+1;
        }
    }

    public void printUsers(){
        for (User u: usersArray.getUsers()) {
            System.out.println(u);
        }
    }

    public void findByAuthor(String author){
        booksArray.findByAuthor(author).print();
    }

    public void findByTitle(String title){
        booksArray.findByTitle(title).print();
    }

    public void insertBook(Book book){
        booksArray.insertBook(book);
    }
}
