public class TestProduct {

    public static void main(String[] args) {

        Product product = new Product("Laptop", 999.99, 100);

        System.out.println("Product: " + product.getName());
        System.out.println("Price: $" + product.getPrice());
        System.out.println("Quantity: " + product.getStock());
    
        // Invalid name
        boolean accepted = product.setName("");

        System.out.println("\nAttempt: nombre vacio");
        System.out.println("Accepted: " + accepted);

        // Valid name
         accepted = product.setName("gaming Laptop");

        System.out.println("\nAttempt: Gaming Laptop");
        System.out.println("Accepted: " + accepted);


        // Invalid price
         accepted = product.setPrice(-10.99);

        System.out.println("\nAttempt: menor 0");
        System.out.println("Accepted: " + accepted);

        // Valid price
         accepted = product.setPrice(1000.00);

        System.out.println("\nAttempt: precio valido");
        System.out.println("Accepted: " + accepted);

        // Invalid stock
         accepted = product.setStock(-10);

        System.out.println("\nAttempt: menor 0");
        System.out.println("Accepted: " + accepted);

        // Valid stock
         accepted = product.setStock(100);

        System.out.println("\nAttempt: stock valido");
        System.out.println("Accepted: " + accepted);
       
    
       
    }
}