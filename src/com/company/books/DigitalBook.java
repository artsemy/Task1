package com.company.books;

public class DigitalBook extends Book {

    private String textLink;

    //constructor
    public DigitalBook() {
        super();
        textLink = "no text";
    }

    //constructor
    public DigitalBook(String title, String author, int pages, String textLink) {
        super(title, author, pages);
        this.textLink = textLink;
    }

    //get method
    public String getTextLink() {
        return textLink;
    }

    //set method
    public void setTextLink(String textLink) {
        this.textLink = textLink;
    }

    //to string
    @Override
    public String toString() {
        return super.toString() + ", textLink='" + textLink + '\'';
    }

}
