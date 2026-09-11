public class TestThermometer {

    public static void main(String[] args) {

        Thermometer thermometer = new Thermometer(20);

        System.out.println("Initial temperature: "
                + thermometer.getTemperature() + " °C");

        // Valid temperature
        boolean accepted = thermometer.setTemperature(35);

        System.out.println("\nAttempt: 35 °C");
        System.out.println("Accepted: " + accepted);
        System.out.println("Current temperature: "
                + thermometer.getTemperature() + " °C");

        // Invalid temperature
        accepted = thermometer.setTemperature(150);

        System.out.println("\nAttempt: 150 °C");
        System.out.println("Accepted: " + accepted);
        System.out.println("Current temperature: "
                + thermometer.getTemperature() + " °C");

        // Valid temperature
        accepted = thermometer.setTemperature(-40);

        System.out.println("\nAttempt: -40 °C");
        System.out.println("Accepted: " + accepted);
        System.out.println("Current temperature: "
                + thermometer.getTemperature() + " °C");

        // Invalid temperature
        accepted = thermometer.setTemperature(-60);

        System.out.println("\nAttempt: -60 °C");
        System.out.println("Accepted: " + accepted);
        System.out.println("Current temperature: "
                + thermometer.getTemperature() + " °C");
    }
}