package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Author;
import model.Book;
import model.Customer;
import model.Library;
import model.Reservation;

public class Main {

    public static void main(String[] args) {
        Library library = createLibrary();
        String[] sections = {"Libros", "Autores", "Clientes", "Reservas", "Salir"};

        while (true) {
            int option = showOptionMenu(library.getName(), "Seleccione una seccion:", sections);
            switch (option) {
                case 0:
                    manageBooks(library);
                    break;
                case 1:
                    manageAuthors(library);
                    break;
                case 2:
                    manageCustomers(library);
                    break;
                case 3:
                    manageReservations(library);
                    break;
                default:
                    showMessage("Programa finalizado.", JOptionPane.INFORMATION_MESSAGE);
                    return;
            }
        }
    }

    private static Library createLibrary() {
        Library library = new Library("Biblioteca Central");

        Author gabrielGarciaMarquez = new Author("Gabriel Garcia Marquez");
        Author isabelAllende = new Author("Isabel Allende");
        Author authorWithoutBooks = new Author("Autor registrado sin libros");
        library.registerAuthor(gabrielGarciaMarquez);
        library.registerAuthor(isabelAllende);
        library.registerAuthor(authorWithoutBooks);

        Book oneHundredYearsOfSolitude =
                new Book("Cien anos de soledad", gabrielGarciaMarquez);
        Book loveInTheTimeOfCholera =
                new Book("El amor en los tiempos del colera", gabrielGarciaMarquez);
        Book theHouseOfTheSpirits =
                new Book("La casa de los espiritus", isabelAllende);
        library.registerBook(oneHundredYearsOfSolitude);
        library.registerBook(loveInTheTimeOfCholera);
        library.registerBook(theHouseOfTheSpirits);

        Customer ana = new Customer("Ana Perez");
        Customer luis = new Customer("Luis Gomez");
        Customer customerWithoutReservations = new Customer("Cliente sin reservas");
        library.registerCustomer(ana);
        library.registerCustomer(luis);
        library.registerCustomer(customerWithoutReservations);

        library.registerReservation(new Reservation(oneHundredYearsOfSolitude, ana));
        library.registerReservation(new Reservation(theHouseOfTheSpirits, ana));
        library.registerReservation(new Reservation(loveInTheTimeOfCholera, luis));
        return library;
    }

    private static void manageBooks(Library library) {
        String[] actions = {"Crear libro", "Listar libros", "Actualizar libro",
            "Eliminar libro", "Volver"};
        while (true) {
            switch (showOptionMenu("Libros", "Seleccione una operacion:", actions)) {
                case 0:
                    createBook(library);
                    break;
                case 1:
                    listBooks(library);
                    break;
                case 2:
                    updateBook(library);
                    break;
                case 3:
                    deleteBook(library);
                    break;
                default:
                    return;
            }
        }
    }

    private static void manageAuthors(Library library) {
        String[] actions = {"Crear autor", "Listar autores", "Actualizar autor",
            "Eliminar autor", "Volver"};
        while (true) {
            switch (showOptionMenu("Autores", "Seleccione una operacion:", actions)) {
                case 0:
                    createAuthor(library);
                    break;
                case 1:
                    listAuthors(library);
                    break;
                case 2:
                    updateAuthor(library);
                    break;
                case 3:
                    deleteAuthor(library);
                    break;
                default:
                    return;
            }
        }
    }

    private static void manageCustomers(Library library) {
        String[] actions = {"Crear cliente", "Listar clientes", "Actualizar cliente",
            "Eliminar cliente", "Volver"};
        while (true) {
            switch (showOptionMenu("Clientes", "Seleccione una operacion:", actions)) {
                case 0:
                    createCustomer(library);
                    break;
                case 1:
                    listCustomers(library);
                    break;
                case 2:
                    updateCustomer(library);
                    break;
                case 3:
                    deleteCustomer(library);
                    break;
                default:
                    return;
            }
        }
    }

    private static void manageReservations(Library library) {
        String[] actions = {"Crear reserva", "Listar reservas", "Actualizar reserva",
            "Eliminar reserva", "Volver"};
        while (true) {
            switch (showOptionMenu("Reservas", "Seleccione una operacion:", actions)) {
                case 0:
                    createReservation(library);
                    break;
                case 1:
                    listReservations(library);
                    break;
                case 2:
                    updateReservation(library);
                    break;
                case 3:
                    deleteReservation(library);
                    break;
                default:
                    return;
            }
        }
    }

    private static int showOptionMenu(String title, String prompt, String[] options) {
        return JOptionPane.showOptionDialog(
                null, prompt, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
    }

    private static void createBook(Library library) {
        String title = readText("Titulo del libro:");
        if (title == null) {
            return;
        }
        Author author = selectAuthor(library);
        if (author == null) {
            return;
        }
        if (library.registerBook(new Book(title, author))) {
            showMessage("Libro creado correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage("No se pudo crear el libro.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void listBooks(Library library) {
        List<Book> books = library.getBooks();
        if (books.isEmpty()) {
            showMessage("No hay libros registrados.", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder text = new StringBuilder("Catalogo de libros:\n\n");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            text.append(i + 1).append(". ").append(book.getTitle())
                    .append(" - ").append(book.getAuthor().getName()).append('\n');
        }
        showMessage(text.toString(), JOptionPane.INFORMATION_MESSAGE);
    }

    private static void updateBook(Library library) {
        Book book = selectBook(library);
        if (book == null) {
            return;
        }
        String title = readText("Nuevo titulo:");
        if (title == null) {
            return;
        }
        Author author = selectAuthor(library);
        if (author == null) {
            return;
        }
        if (library.updateBook(book, title, author)) {
            showMessage("Libro actualizado correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage("No se pudo actualizar el libro.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void deleteBook(Library library) {
        Book book = selectBook(library);
        if (book == null) {
            return;
        }
        if (library.removeBook(book)) {
            showMessage("Libro eliminado correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage(
                    "No se puede eliminar: el libro tiene reservas asociadas.",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void createAuthor(Library library) {
        String name = readText("Nombre del autor:");
        if (name == null) {
            return;
        }
        if (library.registerAuthor(new Author(name))) {
            showMessage("Autor creado correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage("No se pudo crear el autor.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void listAuthors(Library library) {
        List<Author> authors = library.getAuthors();
        if (authors.isEmpty()) {
            showMessage("No hay autores registrados.", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder text = new StringBuilder("Autores registrados:\n\n");
        for (int i = 0; i < authors.size(); i++) {
            text.append(i + 1).append(". ").append(authors.get(i).getName()).append('\n');
        }
        showMessage(text.toString(), JOptionPane.INFORMATION_MESSAGE);
    }

    private static void updateAuthor(Library library) {
        Author author = selectAuthor(library);
        if (author == null) {
            return;
        }
        String name = readText("Nuevo nombre del autor:");
        if (name == null) {
            return;
        }
        if (library.updateAuthor(author, name)) {
            showMessage("Autor actualizado correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage("No se pudo actualizar el autor.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void deleteAuthor(Library library) {
        Author author = selectAuthor(library);
        if (author == null) {
            return;
        }
        if (library.removeAuthor(author)) {
            showMessage("Autor eliminado correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage(
                    "No se puede eliminar: el autor tiene libros asociados.",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void createCustomer(Library library) {
        String name = readText("Nombre del cliente:");
        if (name == null) {
            return;
        }
        if (library.registerCustomer(new Customer(name))) {
            showMessage("Cliente creado correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage("No se pudo crear el cliente.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void listCustomers(Library library) {
        List<Customer> customers = library.getCustomers();
        if (customers.isEmpty()) {
            showMessage("No hay clientes registrados.", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder text = new StringBuilder("Clientes registrados:\n\n");
        for (int i = 0; i < customers.size(); i++) {
            text.append(i + 1).append(". ").append(customers.get(i).getName()).append('\n');
        }
        showMessage(text.toString(), JOptionPane.INFORMATION_MESSAGE);
    }

    private static void updateCustomer(Library library) {
        Customer customer = selectCustomer(library);
        if (customer == null) {
            return;
        }
        String name = readText("Nuevo nombre del cliente:");
        if (name == null) {
            return;
        }
        if (library.updateCustomer(customer, name)) {
            showMessage("Cliente actualizado correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage("No se pudo actualizar el cliente.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void deleteCustomer(Library library) {
        Customer customer = selectCustomer(library);
        if (customer == null) {
            return;
        }
        if (library.removeCustomer(customer)) {
            showMessage("Cliente eliminado correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage(
                    "No se puede eliminar: el cliente tiene reservas asociadas.",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void createReservation(Library library) {
        Book book = selectBook(library);
        if (book == null) {
            return;
        }
        Customer customer = selectCustomer(library);
        if (customer == null) {
            return;
        }
        if (library.registerReservation(new Reservation(book, customer))) {
            showMessage("Reserva creada correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage("No se pudo crear la reserva.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void listReservations(Library library) {
        List<Reservation> reservations = library.getReservations();
        if (reservations.isEmpty()) {
            showMessage("No hay reservas registradas.", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder text = new StringBuilder("Reservas registradas:\n\n");
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            text.append(i + 1).append(". ")
                    .append(reservation.getCustomer().getName())
                    .append(" - ")
                    .append(reservation.getBook().getTitle()).append('\n');
        }
        showMessage(text.toString(), JOptionPane.INFORMATION_MESSAGE);
    }

    private static void updateReservation(Library library) {
        Reservation reservation = selectReservation(library);
        if (reservation == null) {
            return;
        }
        Book book = selectBook(library);
        if (book == null) {
            return;
        }
        Customer customer = selectCustomer(library);
        if (customer == null) {
            return;
        }
        if (library.updateReservation(reservation, book, customer)) {
            showMessage("Reserva actualizada correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage("No se pudo actualizar la reserva.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void deleteReservation(Library library) {
        Reservation reservation = selectReservation(library);
        if (reservation == null) {
            return;
        }
        if (library.removeReservation(reservation)) {
            showMessage("Reserva eliminada correctamente.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage("No se pudo eliminar la reserva.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Book selectBook(Library library) {
        List<Book> books = library.getBooks();
        List<String> options = new ArrayList<>();
        for (Book book : books) {
            options.add(book.getTitle() + " - " + book.getAuthor().getName());
        }
        int index = selectIndex("Seleccione el libro:", "Libros", options);
        return index < 0 ? null : books.get(index);
    }

    private static Author selectAuthor(Library library) {
        List<Author> authors = library.getAuthors();
        List<String> options = new ArrayList<>();
        for (Author author : authors) {
            options.add(author.getName());
        }
        int index = selectIndex("Seleccione el autor:", "Autores", options);
        return index < 0 ? null : authors.get(index);
    }

    private static Customer selectCustomer(Library library) {
        List<Customer> customers = library.getCustomers();
        List<String> options = new ArrayList<>();
        for (Customer customer : customers) {
            options.add(customer.getName());
        }
        int index = selectIndex("Seleccione el cliente:", "Clientes", options);
        return index < 0 ? null : customers.get(index);
    }

    private static Reservation selectReservation(Library library) {
        List<Reservation> reservations = library.getReservations();
        List<String> options = new ArrayList<>();
        for (Reservation reservation : reservations) {
            options.add(reservation.getCustomer().getName()
                    + " - " + reservation.getBook().getTitle());
        }
        int index = selectIndex("Seleccione la reserva:", "Reservas", options);
        return index < 0 ? null : reservations.get(index);
    }

    private static int selectIndex(String prompt, String title, List<String> labels) {
        if (labels.isEmpty()) {
            showMessage("No hay elementos registrados.", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }

        String[] options = new String[labels.size()];
        for (int i = 0; i < labels.size(); i++) {
            options[i] = (i + 1) + ". " + labels.get(i);
        }
        String selection = (String) JOptionPane.showInputDialog(
                null, prompt, title, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (selection == null) {
            return -1;
        }
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(selection)) {
                return i;
            }
        }
        return -1;
    }

    private static String readText(String prompt) {
        while (true) {
            String value = JOptionPane.showInputDialog(null, prompt);
            if (value == null) {
                return null;
            }
            value = value.trim();
            if (!value.isEmpty()) {
                return value;
            }
            showMessage("El valor no puede estar vacio.", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void showMessage(String message, int messageType) {
        JOptionPane.showMessageDialog(null, message, "Biblioteca", messageType);
    }
}
