package qirui.libraryManagement;
import java.util.*;

public abstract class Book {
    private String ISBN;
    private String title;
    private String subject;
    private String publisher;
    private int numberOfPages;
//    private List<Author> authors;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}

class BookItem extends Book {
    private Calendar borrowedTime;
    private String barCode;
    private boolean borrowed;
    private boolean reserved;
    private Calendar reservedTime;

    public BookItem(String title, String barCode) {
        super(title);
        this.barCode = barCode;
    }

    public boolean borrow() {
        if (!canBorrow()) {
            return false;
        }
        this.borrowedTime = Calendar.getInstance();
        this.borrowed = true;
        return true;
    }

    public boolean reserve() {
        if (!canReserve()) {
            return false;
        }
        this.reservedTime = Calendar.getInstance();
        this.reserved = true;
        return true;
    }

    public boolean canBorrow() {

        return !this.borrowed;
    }

    public boolean canReserve() {
        return !this.reserved;
    }

    public String getBarCode() {
        return this.barCode;
    }

    public void makeAvailable() {
        this.borrowed = false;
        this.borrowedTime = null;
        this.reserved = false;
        this.reservedTime = null;
    }
}

class BookTester {
    public static void main(String[] args) {
        Book book = new BookItem("QIRUI TRYING", "S99090s");
        System.out.println(book.getTitle());
    }
}

