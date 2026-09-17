public class Thermometer {

    private double temperature;

    // Constructor
    public Thermometer(double temperature) {
        if (temperature >= -50 && temperature <= 100) {
            this.temperature = temperature;
        } else {
            throw new IllegalArgumentException("Temperatura no válida");
        }
    }

    // Consultar temperatura
    public double getTemperature() {
        return temperature;
    }

    // Modificar temperatura
    public boolean setTemperature(double temperature) {
        if (temperature >= -50 && temperature <= 100) {
            this.temperature = temperature;
            return true;
        }

        return false;
    }
}