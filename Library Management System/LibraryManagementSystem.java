import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibraryManagementSystem {
    private Library library;
    private Scanner scanner;

    public LibraryManagementSystem() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> findBook();
                case 4 -> library.listBooks();
                case 5 -> borrowBook();
                case 6 -> returnBook();
                case 7 -> addMember();
                case 8 -> removeMember();
                case 9 -> updateBookInfo();
                case 10 -> searchBooksByCategory();
                case 11 -> addBookReview();
                case 12 -> showBookStatistics();
                case 13 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n도서 관리 시스템");
        System.out.println("1. 도서 추가");
        System.out.println("2. 도서 삭제");
        System.out.println("3. 도서 검색");
        System.out.println("4. 도서 목록 보기");
        System.out.println("5. 도서 대출");
        System.out.println("6. 도서 반납");
        System.out.println("7. 회원 추가");
        System.out.println("8. 회원 삭제");
        System.out.println("9. 도서 정보 수정");
        System.out.println("10. 카테고리별 도서 검색");
        System.out.println("11. 도서 리뷰 작성");
        System.out.println("12. 도서 통계 보기");
        System.out.println("13. 종료");
        System.out.print("선택: ");
    }

    private void addBook() {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("제목: ");
        String title = scanner.nextLine();
        System.out.print("저자: ");
        String author = scanner.nextLine();
        System.out.print("카테고리: ");
        String category = scanner.nextLine();

        Book book = new Book(isbn, title, author, category);
        library.addBook(book);
        System.out.println("도서가 추가되었습니다.");
    }

    private void removeBook() {
        System.out.print("삭제할 도서의 ISBN: ");
        String isbn = scanner.nextLine();
        library.removeBook(isbn);
        System.out.println("도서가 삭제되었습니다.");
    }

    private void findBook() {
        System.out.print("검색할 도서의 ISBN: ");
        String isbn = scanner.nextLine();
        Book book = library.findBook(isbn);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("도서를 찾을 수 없습니다.");
        }
    }

    private void borrowBook() {
        System.out.print("회원 ID: ");
        String memberId = scanner.nextLine();
        System.out.print("도서 ISBN: ");
        String isbn = scanner.nextLine();
        library.borrowBook(memberId, isbn);
    }

    private void returnBook() {
        System.out.print("회원 ID: ");
        String memberId = scanner.nextLine();
        System.out.print("도서 ISBN: ");
        String isbn = scanner.nextLine();
        library.returnBook(memberId, isbn);
    }

    private void addMember() {
        System.out.print("회원 ID: ");
        String id = scanner.nextLine();
        System.out.print("회원 이름: ");
        String name = scanner.nextLine();
        Member member = new Member(id, name);
        library.addMember(member);
        System.out.println("회원이 추가되었습니다.");
    }

    private void removeMember() {
        System.out.print("삭제할 회원의 ID: ");
        String id = scanner.nextLine();
        library.removeMember(id);
        System.out.println("회원이 삭제되었습니다.");
    }

    private void updateBookInfo() {
        System.out.print("수정할 도서의 ISBN: ");
        String isbn = scanner.nextLine();
        Book existingBook = library.findBook(isbn);
        if (existingBook == null) {
            System.out.println("해당 ISBN의 도서를 찾을 수 없습니다.");
            return;
        }

        System.out.print("새 제목 (변경하지 않으려면 Enter): ");
        String newTitle = scanner.nextLine();
        System.out.print("새 저자 (변경하지 않으려면 Enter): ");
        String newAuthor = scanner.nextLine();
        System.out.print("새 카테고리 (변경하지 않으려면 Enter): ");
        String newCategory = scanner.nextLine();

        Book updatedBook = new Book(
                isbn,
                newTitle.isEmpty() ? existingBook.getTitle() : newTitle,
                newAuthor.isEmpty() ? existingBook.getAuthor() : newAuthor,
                newCategory.isEmpty() ? existingBook.getCategory() : newCategory
        );

        library.updateBookInfo(isbn, updatedBook);
    }

    private void searchBooksByCategory() {
        System.out.print("검색할 카테고리: ");
        String category = scanner.nextLine();
        List<Book> books = library.searchBooksByCategory(category);
        if (books.isEmpty()) {
            System.out.println("해당 카테고리의 도서를 찾을 수 없습니다.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void addBookReview() {
        System.out.print("리뷰를 작성할 도서의 ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("리뷰 내용: ");
        String comment = scanner.nextLine();
        System.out.print("평점 (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        library.addReview(isbn, comment, rating);
    }

    private void showBookStatistics() {
        Map<String, Integer> stats = library.getBookStatistics();
        System.out.println("카테고리별 도서 수:");
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        new LibraryManagementSystem().run();
    }
}