public class TestBankAccount {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(
                "123456789",
                "Andres",
                100000
        );

        System.out.println("Saldo inicial: " + account.getBalance());

        // Depósito válido
        boolean result = account.deposits(50000);
        System.out.println("Depósito de 50000: " + result);
        System.out.println("Saldo: " + account.getBalance());

        // Depósito inválido
        result = account.deposits(-10000);
        System.out.println("Depósito de -10000: " + result);
        System.out.println("Saldo: " + account.getBalance());

        // Retiro válido
        result = account.withdrawals(30000);
        System.out.println("Retiro de 30000: " + result);
        System.out.println("Saldo: " + account.getBalance());

        // Retiro superior al saldo
        result = account.withdrawals(200000);
        System.out.println("Retiro de 200000: " + result);
        System.out.println("Saldo: " + account.getBalance());

        // Retiro inválido
        result = account.withdrawals(-5000);
        System.out.println("Retiro de -5000: " + result);
        System.out.println("Saldo: " + account.getBalance());
    }
}