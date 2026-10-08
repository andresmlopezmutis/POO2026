public class TestArticle {

    public static void main(String[] args) {
        Author author = new Author("Gabriel Garcia Marquez", "Colombiana");
        Article article = new Article("El realismo magico", "Literatura", author);

        System.out.println("Articulo: " + article.getTitle());
        System.out.println("Tema: " + article.getTopic());
        System.out.println("Autor: " + article.getAuthor().getName());
        System.out.println("Nacionalidad: " + article.getAuthor().getNationality());
    }
}
