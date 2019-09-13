package com.company.books;

public class PaperBook extends Book {

    private boolean isInAccess;

    public PaperBook() {
        super();
        isInAccess = false;
    }

    public PaperBook(String title, String author, int pages, boolean isInAccess) {
        super(title, author, pages);
        this.isInAccess = isInAccess;
    }

    public boolean isInAccess() {
        return isInAccess;
    }

    public void setInAccess(boolean inAccess) {
        isInAccess = inAccess;
    }

    @Override
    public String toString() {
        return super.toString() + ", isInAccess=" + isInAccess;
    }
}
