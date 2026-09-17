public class BankAccount {

    private String account_number;
    private String account_holder;
    private double balance;

    //constructor
    public BankAccount(String account_number, String account_holder, double balance){
        if(!setAccountNumber(account_number) ||
           !setAccountHolder(account_holder) ||
           !setBalance(balance)){
            throw new IllegalArgumentException("Datos invalidos para la cuenta");
        }
    }
    public boolean deposit(double deposit){
        if(deposit > 0){
            this.balance += deposit;
            return true;
        }
        return false;
    }
    public boolean withdraw(double withdrawal){
        if(withdrawal > 0 && withdrawal <= this.balance){
            this.balance -= withdrawal;
            return true;
        }
        return false;
    }
    public double getBalance(){
        return this.balance;
    }

    public boolean setBalance(double balance){
        if(balance < 0){
            return false;
        }
        this.balance = balance;
        return true;
    }

    public String getAccountNumber(){
        return this.account_number;
    }

    public boolean setAccountNumber(String account_number){
        if(account_number == null || account_number.isBlank()){
            return false;
        }
        this.account_number = account_number;
        return true;
    }

    public String getAccountHolder(){
        return this.account_holder;
    }

    public boolean setAccountHolder(String account_holder){
        if(account_holder == null || account_holder.isBlank()){
            return false;
        }
        this.account_holder = account_holder;
        return true;
    }
}