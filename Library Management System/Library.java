import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public Book findBook(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public void listBooks() {
        books.forEach(System.out::println);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(long id) {
        members.removeIf(member -> member.getId() == id);
    }

    public Member findMember(long id) {
        return members.stream()
                .filter(member -> member.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void borrowBook(long memberId, String isbn) {
        Member member = findMember(memberId);
        Book book = findBook(isbn);
        if (member != null && book != null && book.isAvailable()) {
            book.setAvailable(false);
            member.borrowBook(book);
            System.out.println("책이 대출되었습니다.");
        } else {
            System.out.println("대출할 수 없습니다.");
        }
    }

    public void returnBook(long memberId, String isbn) {
        Member member = findMember(memberId);
        Book book = findBook(isbn);
        if (member != null && book != null && member.getBorrowedBooks().contains(book)) {
            book.setAvailable(true);
            member.returnBook(book);
            System.out.println("책이 반납되었습니다.");
        } else {
            System.out.println("반납할 수 없습니다.");
        }
    }

    public void updateBookInfo(String isbn, Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.set(i, updatedBook);
                System.out.println("도서 정보가 업데이트되었습니다.");
                return;
            }
        }
        System.out.println("해당 ISBN의 도서를 찾을 수 없습니다.");
    }

    public List<Book> searchBooksByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public void addReview(String isbn, String comment, int rating) {
        Book book = findBook(isbn);
        if (book != null) {
            book.addReview(new Review(comment, rating));
            System.out.println("리뷰가 추가되었습니다.");
        } else {
            System.out.println("해당 ISBN의 도서를 찾을 수 없습니다.");
        }
    }

    public Map<String, Integer> getBookStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        for (Book book : books) {
            stats.put(book.getCategory(), stats.getOrDefault(book.getCategory(), 0) + 1);
        }
        return stats;
    }

    public static void main(String[] args) {
        Library library = new Library();

        // Example usage
        Book book1 = new Book("978-3-16-148410-0", "Harry Potter", "J.K. Rowling", "Fantasy");
        library.addBook(book1);

        Member member1 = new Member("John Doe");
        library.addMember(member1);

        library.borrowBook(member1.getId(), book1.getIsbn());
        library.addReview(book1.getIsbn(), "Great book!", 5);

        library.listBooks();
        library.listMembers();

        library.returnBook(member1.getId(), book1.getIsbn());
    }
    
    public void listMembers() {
        members.forEach(System.out::println);
    }
}
