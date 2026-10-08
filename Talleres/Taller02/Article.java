public class Article {

    private String title;
    private String topic;
    private Author author;

    public Article(String title, String topic, Author author) {
        if (!setTitle(title) || !setTopic(topic) || !setAuthor(author)) {
            throw new IllegalArgumentException("Datos invalidos para el articulo");
        }
    }

    public String getTitle() {
        return title;
    }

    public boolean setTitle(String title) {
        if (title == null || title.isBlank()) {
            return false;
        }
        this.title = title;
        return true;
    }

    public String getTopic() {
        return topic;
    }

    public boolean setTopic(String topic) {
        if (topic == null || topic.isBlank()) {
            return false;
        }
        this.topic = topic;
        return true;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean setAuthor(Author author) {
        if (author == null) {
            return false;
        }
        this.author = author;
        return true;
    }
}
