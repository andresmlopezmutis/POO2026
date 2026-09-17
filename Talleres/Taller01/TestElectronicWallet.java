public class TestElectronicWallet {

    public static void main(String[] args) {

        // Datos no válidos para el constructor
        // ElectronicWallet invalidWallet = new ElectronicWallet(
        //         ""
        // );

        ElectronicWallet wallet =
                new ElectronicWallet("Andres");

        System.out.println("Propietario: " +
                wallet.getOwner());

        System.out.println("Saldo inicial: " +
                wallet.getBalance());

        // Recarga válida
        boolean result = wallet.setTop_ups(300000);

        System.out.println("Recarga 300000: " + result);
        System.out.println("Saldo: " + wallet.getBalance());

        // Recarga inválida
        result = wallet.setTop_ups(-50000);

        System.out.println("Recarga -50000: " + result);
        System.out.println("Saldo: " + wallet.getBalance());

        // Pago válido
        result = wallet.setPayment(100000);

        System.out.println("Pago 100000: " + result);
        System.out.println("Saldo: " + wallet.getBalance());

        // Pago superior al saldo
        result = wallet.setPayment(300000);

        System.out.println("Pago 300000: " + result);
        System.out.println("Saldo: " + wallet.getBalance());

        // Pago superior a 500000
        result = wallet.setPayment(600000);

        System.out.println("Pago 600000: " + result);
        System.out.println("Saldo: " + wallet.getBalance());

        // Pago inválido
        result = wallet.setPayment(-10000);

        System.out.println("Pago -10000: " + result);
        System.out.println("Saldo: " + wallet.getBalance());
    }
}