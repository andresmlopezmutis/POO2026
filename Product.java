public class Product {
    
    private String name;
    private double price; 
    private int stock;

    //constructor
    public Product(String name, double price, int stock){
        setName(name);
        setPrice(price);
        setStock(stock);
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getStock(){
        return stock;
    }

    public boolean setName(String name){
        if (name != null && !name.isBlank()){
            this.name = name;
            return true;
        }
        return false;
    }

    public boolean setPrice(double price){
        if (price > 0){
            this.price = price;
            return true;
        }
        return false;
    }

    public boolean setStock(int stock){
        if (stock >= 0){
            this.stock = stock;
            return true;
        }
        return false;
    }

}    