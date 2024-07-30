public class Review {
    private long id;
    private long bookId;
    private long memberId;
    private String content;
    private int rating;

    public Review(long bookId, long memberId, String content, int rating) {
        this.id = IDGenerator.generateID();
        this.bookId = bookId;
        this.memberId = memberId;
        this.content = content;
        this.rating = rating;
    }

    public long getId() { return id; }
    public long getBookId() { return bookId; }
    public long getMemberId() { return memberId; }
    public String getContent() { return content; }
    public int getRating() { return rating; }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", memberId=" + memberId +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}
