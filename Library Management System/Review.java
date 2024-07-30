public class Review {
    private long id;
    private String comment;
    private int rating;

    public Review(String comment, int rating) {
        this.id = IDGenerator.generateID();
        this.comment = comment;
        this.rating = rating;
    }

    public long getId() { return id; }
    public String getComment() { return comment; }
    public int getRating() { return rating; }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}
