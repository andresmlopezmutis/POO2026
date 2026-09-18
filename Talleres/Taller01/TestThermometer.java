import javax.swing.JOptionPane;

public class TestThermometer {
    public static void main(String[] args) {
        Thermometer thermometer = new Thermometer(20);

        String input = JOptionPane.showInputDialog(null,
                "Ingrese una temperatura entre -50 y 100 °C:");

        if (input == null) {
            return;
        }

        try {
            float temperature = Float.parseFloat(input);
            int status = thermometer.setTemperature(temperature);
            showResult(thermometer, temperature, status);
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null,
                    "Error: debe ingresar un número válido.");
        }
    }

    private static void showResult(Thermometer thermometer, float temperature,
            int status) {
        String message;

        if (status == 0) {
            message = "Temperatura asignada correctamente.";
        } else if (status == -1) {
            message = "Error: la temperatura está por debajo del mínimo (-50 °C).";
        } else if (status == -2) {
            message = "Error: la temperatura está por encima del máximo (100 °C).";
        } else {
            message = "Error: estado desconocido.";
        }

        JOptionPane.showMessageDialog(null,
                "Intento: " + temperature + " °C\n"
                        + message + "\n"
                        + "Temperatura actual: " + thermometer.getTemperature()
                        + " °C");
    }
}