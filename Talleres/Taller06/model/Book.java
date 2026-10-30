package model;

public class Book {

    private String title;
    private Author author;

    public Book(String title, Author author) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El titulo del libro no puede estar vacio");
        }
        if (author == null) {
            throw new IllegalArgumentException("El libro debe tener un autor");
        }
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean updateDetails(String title, Author author) {
        if (title == null || title.isBlank() || author == null) {
            return false;
        }
        this.title = title;
        this.author = author;
        return true;
    }
}
