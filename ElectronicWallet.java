public class ElectronicWallet {
    
    private String owner;
    private int balance;
    private int top_up;
    private int payment;

    //constructor
    public ElectronicWallet(String owner){
        if(!owner.equals("")){
            this.owner = owner;
            this.balance = 0;
        }else{
            System.out.println("Dato inválido");
        }
    }
    public boolean setTop_ups(int top_up){
        if(top_up > 0){
            this.top_up = top_up;
            this.balance = this.balance + top_up;
            return true;
        }
        return false;
    }
    public boolean setPayment(int payment, int balance){
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
