public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean available;

    // Constructor 1: ISBN et titre seulement
    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.author = "Unknown";
        // Erreur humaine #1: oublié d'initialiser available ici
    }

    // Constructor 2: Avec auteur (surcharge)
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    // Getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }
}

