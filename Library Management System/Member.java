import java.util.ArrayList;
import java.util.List;

public class Member {
    private long id;
    private String name;
    private List<Book> borrowedBooks;

    public Member(String name) {
        this.id = IDGenerator.generateID();
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
