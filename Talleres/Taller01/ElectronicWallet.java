public class ElectronicWallet {
    
    private String owner;
    private int balance;
    private int top_up;
    private int payment;

    //constructor
    public ElectronicWallet(String owner){
        if(!setOwner(owner)){
            throw new IllegalArgumentException("Datos invalidos para la billetera");
        }
        this.balance = 0;
    }
    public boolean setTop_ups(int top_up){
        if(top_up > 0){
            this.top_up = top_up;
            this.balance = this.balance + top_up;
            return true;
        }
        return false;
    }
    public boolean setPayment(int payment){
        if(payment > 0 && payment <= balance && payment <= 500000){
            this.payment = payment;
            this.balance = this.balance - payment;
            return true;
        }
        return false;
    }
    public String getOwner(){
        return owner;
    }

    public boolean setOwner(String owner){
        if(owner == null || owner.isBlank()){
            return false;
        }
        this.owner = owner;
        return true;
    }
    public int getBalance(){
        return balance;
    }
    public int getTop_up(){
        return top_up;
    }
    public int getPayment(){
        return payment;
    }

}
