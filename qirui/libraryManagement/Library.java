package qirui.libraryManagement;
import java.util.*;

class User {
    private static int COUNT;
    private String name;
    private int age;
    private int id;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
        this.id = COUNT++;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
}

abstract class Record {
    private final Calendar dueDate;
    private final BookItem bookItem;
    private final MemberShip memberShip;

    public Record(BookItem bookItem, MemberShip memberShip, int days) {
        this.bookItem = bookItem;
        this.memberShip = memberShip;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        this.dueDate = calendar;
    }

    public MemberShip getMemberShip() {
        return this.memberShip;
    }

    public Calendar getDueDate() {
        return this.dueDate;
    }
}

class Transaction extends Record {
    private static int COUNT = 0;
    private static final int DEFAULT_DAYS = 30;
    private int id;

    public Transaction(BookItem bookItem, MemberShip memberShip) {
        super(bookItem, memberShip, DEFAULT_DAYS);
        this.id = this.COUNT++;
    }
}

class Reservation extends Record {
    private static int COUNT = 0;
    private static final int DEFAULT_DAYS = 10;
    private int id;

    public Reservation(BookItem bookItem, MemberShip memberShip) {
        super(bookItem, memberShip, DEFAULT_DAYS);
        this.id = this.COUNT++;
    }
}

class FineRequiredException extends Exception {
    public FineRequiredException() {
        super("There is outstanding fine.");
    }
}

interface MemberShip {
    Transaction borrow(BookItem book);
    Reservation reserve(BookItem book);
    void returnBack(BookItem book) throws FineRequiredException;
    boolean payFine();
}

class SilverMember implements MemberShip {
    private final int MAX_ITEMS = 5;
    private Map<BookItem, Transaction> borrows;
    private Map<BookItem, Reservation> reserves;
    private List<Transaction> archives;
    private User user;

    public SilverMember(User user) {
        this.borrows = new HashMap<>();
        this.reserves = new HashMap<>();
        this.user = user;
    }

    @Override
    public Transaction borrow(BookItem book) {
        if (!canBorrow(book)) {
            return null;
        }
        Transaction record = new Transaction(book, this);
        book.borrow();
        borrows.put(book, record);
        return record;
    }

    @Override
    public Reservation reserve(BookItem book) {
        if (!canReserve(book)) {
            return null;
        }
        Reservation record = new Reservation(book, this);
        book.reserve();
        reserves.put(book, record);
        return record;
    }

    @Override
    public void returnBack(BookItem book) throws FineRequiredException {
        Transaction record = borrows.get(book);
        if (record == null) {
            throw new IllegalArgumentException("No such book borrowed");
        }
        Calendar calendar = Calendar.getInstance();
        if (record.getDueDate().compareTo(calendar) < 0) {
            throw new FineRequiredException();
        }
        borrows.remove(book);
        this.archives.add(record);
        book.makeAvailable();
    }

    @Override
    public boolean payFine() {
        // TODO
        return false;
    }

    private boolean canBorrow(BookItem book) {
        if (!book.canBorrow()) {
            return false;
        }
        if (borrows.size() >= MAX_ITEMS) {
            return false;
        }
        return true;
    }

    private boolean canReserve(BookItem book) {
        if (!book.canReserve()) {
            return false;
        }
        if (reserves.size() >= MAX_ITEMS) {
            return false;
        }
        return true;
    }
}

class Catalog {
    private Map<String, List<BookItem>> records;
    private Map<String, BookItem> recordsByCode;  // barcode -> bookitem

    public Catalog(List<BookItem> books) {
        this.records = new HashMap<>();
        this.recordsByCode = new HashMap<>();
        for (BookItem bookItem : books) {
            String title = bookItem.getTitle();
            if (!this.records.containsKey(bookItem.getTitle())) {
                this.records.put(title, new ArrayList<>());
            }
            this.records.get(title).add(bookItem);
            this.recordsByCode.put(bookItem.getBarCode(), bookItem);
        }
    }

    public List<BookItem> search(String title) {
        return this.records.get(title);
    }

    public BookItem getBookByCode(String barCode) {
        return this.recordsByCode.get(barCode);
    }
}

public class Library {
    private Map<Integer, MemberShip> members;  // user id - > membership
    private Map<String, List<MemberShip>> records; // user name -> membership
    private Map<BookItem, Transaction> borrowHistory;
    private Map<BookItem, Reservation> reserveHistory;
    private final Catalog catalog;

    public Library(List<BookItem> books) {
        this.catalog = new Catalog(books);
        this.members = new HashMap<>();
        this.records = new HashMap<>();
        this.borrowHistory = new HashMap<>();
        this.reserveHistory = new HashMap<>();
    }

    public Transaction borrow(BookItem bookItem, MemberShip memberShip) {
        Transaction transaction = memberShip.borrow(bookItem);
        if (transaction != null) {
            borrowHistory.put(bookItem, transaction);
        }
        return transaction;
    }

    public List<BookItem> searchByTitle(String title) {
        return this.catalog.search(title);
    }

    public BookItem getBook(String barCode) {
        return this.catalog.getBookByCode(barCode);
    }

    public void returnBack(BookItem bookItem, MemberShip memberShip) throws FineRequiredException {
        memberShip.returnBack(bookItem);
        borrowHistory.remove(bookItem);
    }

    public MemberShip join(User user) {
        if (getMember(user) != null) {
            return getMember(user);
        }
        MemberShip memberShip = new SilverMember(user);
        if (records.get(user.getName()) == null) {
            records.put(user.getName(), new ArrayList<>());
        }
        records.get(user.getName()).add(memberShip);
        members.put(user.getId(), memberShip);
        return memberShip;
    }

    public MemberShip getMember(User user) {
        return members.get(user.getId());
    }
}

/*
reserve a book (membership) - returns reservation record or raise exception
        borrow a book (membership) - returns transaction record or raise exception
        search a book by title (String title) - List<BookItem>
        create membership (user) - returns membership record. with default expiry date
        get membership by user - return membership record
 */