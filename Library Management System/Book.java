import java.util.ArrayList;
import java.util.List;

public class Book {
    private long id;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private boolean isAvailable;
    private List<Review> reviews;

    public Book(String isbn, String title, String author, String category) {
        this.id = IDGenerator.generateID();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = true;
        this.reviews = new ArrayList<>();
    }

    public long getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) return 0;
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                ", averageRating=" + getAverageRating() +
                '}';
    }
}
