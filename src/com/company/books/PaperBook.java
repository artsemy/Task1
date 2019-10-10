package com.company.books;

public class PaperBook extends Book {

    private boolean isInAccess;

    //constructor
    public PaperBook() {
        super();
        isInAccess = false;
    }

    //constructor
    public PaperBook(String title, String author, int pages, boolean isInAccess) {
        super(title, author, pages);
        this.isInAccess = isInAccess;
    }

    //get method
    public boolean isInAccess() {
        return isInAccess;
    }

    //set method
    public void setInAccess(boolean inAccess) {
        isInAccess = inAccess;
    }

    //to string
    @Override
    public String toString() {
        return super.toString() + ", isInAccess=" + isInAccess;
    }

}
