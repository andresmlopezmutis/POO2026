public class TestOrder {

    public static void main(String[] args) {
        Customer customer = new Customer("Sofia Martinez", "sofia@example.com");
        Order order = new Order(1001, "Compra de un teclado", customer);

        System.out.println("Pedido: " + order.getOrderNumber());
        System.out.println("Descripcion: " + order.getDescription());
        System.out.println("Cliente: " + order.getCustomer().getName());
        System.out.println("Correo: " + order.getCustomer().getEmail());
    }
}
