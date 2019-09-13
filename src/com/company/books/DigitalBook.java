package com.company.books;

public class DigitalBook extends Book {

    private String textLink;

    public DigitalBook() {
        super();
        textLink = "no text";
    }

    public DigitalBook(String title, String author, int pages, String textLink) {
        super(title, author, pages);
        this.textLink = textLink;
    }

    public String getTextLink() {
        return textLink;
    }

    public void setTextLink(String textLink) {
        this.textLink = textLink;
    }

    @Override
    public String toString() {
        return super.toString() + ", textLink='" + textLink;
    }
}
