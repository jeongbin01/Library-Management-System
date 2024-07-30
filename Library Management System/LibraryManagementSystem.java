import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    findBook();
                    break;
                case 4:
                    listBooks();
                    break;
                case 5:
                    addMember();
                    break;
                case 6:
                    removeMember();
                    break;
                case 7:
                    borrowBook();
                    break;
                case 8:
                    returnBook();
                    break;
                case 9:
                    updateBookInfo();
                    break;
                case 10:
                    searchBooksByCategory();
                    break;
                case 11:
                    addReview();
                    break;
                case 12:
                    getBookStatistics();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n도서 관리 시스템 메뉴:");
        System.out.println("1. 도서 추가");
        System.out.println("2. 도서 삭제");
        System.out.println("3. 도서 검색");
        System.out.println("4. 도서 목록 보기");
        System.out.println("5. 회원 추가");
        System.out.println("6. 회원 삭제");
        System.out.println("7. 도서 대출");
        System.out.println("8. 도서 반납");
        System.out.println("9. 도서 정보 수정");
        System.out.println("10. 카테고리별 도서 검색");
        System.out.println("11. 도서 리뷰 작성");
        System.out.println("12. 카테고리별 도서 통계 보기");
        System.out.println("0. 종료");
        System.out.print("선택: ");
    }

    private static void addBook() {
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
        System.out.println("도서가 추가되었습니다: " + book.getId());
    }

    private static void removeBook() {
        System.out.print("삭제할 도서의 ISBN: ");
        String isbn = scanner.nextLine();
        library.removeBook(isbn);
        System.out.println("도서가 삭제되었습니다.");
    }

    private static void findBook() {
        System.out.print("검색할 도서의 ISBN: ");
        String isbn = scanner.nextLine();
        Book book = library.findBook(isbn);
        if (book != null) {
            System.out.println("도서 정보: " + book);
        } else {
            System.out.println("해당 도서를 찾을 수 없습니다.");
        }
    }

    private static void listBooks() {
        System.out.println("도서 목록:");
        library.listBooks();
    }

    private static void addMember() {
        System.out.print("회원 이름: ");
        String name = scanner.nextLine();
        Member member = new Member(name);
        library.addMember(member);
        System.out.println("회원이 추가되었습니다: " + member.getId());
    }

    private static void removeMember() {
        System.out.print("삭제할 회원의 ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();  // Consume newline
        library.removeMember(id);
        System.out.println("회원이 삭제되었습니다.");
    }

    private static void borrowBook() {
        System.out.print("회원 ID: ");
        long memberId = scanner.nextLong();
        System.out.print("대출할 도서의 ISBN: ");
        String isbn = scanner.next();
        scanner.nextLine();  // Consume newline

        library.borrowBook(memberId, isbn);
    }

    private static void returnBook() {
        System.out.print("회원 ID: ");
        long memberId = scanner.nextLong();
        System.out.print("반납할 도서의 ISBN: ");
        String isbn = scanner.next();
        scanner.nextLine();  // Consume newline

        library.returnBook(memberId, isbn);
    }

    private static void updateBookInfo() {
        System.out.print("수정할 도서의 ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("새 제목: ");
        String title = scanner.nextLine();
        System.out.print("새 저자: ");
        String author = scanner.nextLine();
        System.out.print("새 카테고리: ");
        String category = scanner.nextLine();

        Book updatedBook = new Book(isbn, title, author, category);
        library.updateBookInfo(isbn, updatedBook);
    }

    private static void searchBooksByCategory() {
        System.out.print("검색할 카테고리: ");
        String category = scanner.nextLine();
        List<Book> books = library.searchBooksByCategory(category);
        System.out.println("검색 결과:");
        books.forEach(System.out::println);
    }

    private static void addReview() {
        System.out.print("리뷰할 도서의 ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("리뷰 내용: ");
        String comment = scanner.nextLine();
        System.out.print("평점 (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        library.addReview(isbn, comment, rating);
    }

    private static void getBookStatistics() {
        System.out.println("카테고리별 도서 통계:");
        Map<String, Integer> stats = library.getBookStatistics();
        stats.forEach((category, count) -> System.out.println(category + ": " + count));
    }
}
