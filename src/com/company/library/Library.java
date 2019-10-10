package com.company.library;

import com.company.books.Book;
import com.company.users.User;

public class Library {

    private BooksArray booksArray;
    private UsersArray usersArray;
    private int pageSize = 5;

    //init library
    public Library() {
        booksArray = new BooksArray();
        usersArray = new UsersArray();
    }

    //find login user
    public User findLoginUser(String login, String password){
        String newPassword = cryptPassword(password);
        for (User u: usersArray.getUsers()) {
            if (u.getLogin().equals(login) && u.getPassword().equals(newPassword)){
                return u;
            }
        }
        return null;
    }

    //encrypting pass
    private String cryptPassword(String password){
        char[] mass = password.toCharArray();
        for (int i = 0; i < mass.length; i++) {
            mass[i] = (char) (mass[i] - 15);
        }
        return String.valueOf(mass);
    }

    //print page 'page' of books
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

    //check for page exists
    public boolean hasPage(int page){
        return page <= countPages() && page > 0;
    }

    //count pages
    private int countPages(){
        int k1 = booksArray.getBooks().length / pageSize;
        int k2 = booksArray.getBooks().length % pageSize;
        if (k2 == 0){
            return k1;
        } else {
            return k1+1;
        }
    }

    //print users
    public void printUsers(){
        usersArray.printUsers();
    }

    //print find by author
    public void findByAuthor(String author){
        booksArray.findByAuthor(author).print();
    }

    //print find by title
    public void findByTitle(String title){
        booksArray.findByTitle(title).print();
    }

    //insert book
    public void insertBook(Book book){
        booksArray.insertBook(book);
    }

    //send emails when added book
    public void newBookMessage(User user, Book b){
        usersArray.newBookMassage(user, b);
    }

    //send email with offered book
    public void offerBookMessage(String name, String link){
        usersArray.offerBookMessage(name, link);
    }

}
