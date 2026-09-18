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
    
    public int setTemperature(float newTemperature) {
        if (newTemperature < -50) {
            return -1;
        } else if (newTemperature > 100) {
            return -2;
        } else {
            temperature = newTemperature;
            return 0;
        }
    }
}