public class BankAccount {

    private String account_number;
    private String account_holder;
    private double balance;

    //constructor
    public BankAccount(String account_number, String account_holder, double balance){
        if (account_number != "" && account_holder != "" && balance >= 0){
            this.account_number = account_number;
            this.account_holder = account_holder;
            this.balance = balance;
        } else {
            System.out.println("Datos no válidos");
        }
    }
    public boolean deposits(double deposit){
        if(deposit > 0){
            this.balance += deposit;
            return true;
        }
        return false;
    }
    public boolean withdrawals(double withdrawal){
        if(withdrawal > 0 && withdrawal <= this.balance){
            this.balance -= withdrawal;
            return true;
        }
        return false;
    }
    public double getBalance(){
        return this.balance;
    }

}
