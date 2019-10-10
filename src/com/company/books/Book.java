package com.company.books;

public class Book {

    private String title;
    private String author;
    private int pages;

    //constructor
    public Book() {
        title = "no title";
        author = "no author";
        pages = -1;
    }

    //constructor
    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    //get method
    public String getTitle() {
        return title;
    }

    //set method
    public void setTitle(String title) {
        this.title = title;
    }

    //get method
    public String getAuthor() {
        return author;
    }

    //set method
    public void setAuthor(String author) {
        this.author = author;
    }

    //get method
    public int getPages() {
        return pages;
    }

    //set method
    public void setPages(int pages) {
        this.pages = pages;
    }

    //to string
    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages;
    }

}
