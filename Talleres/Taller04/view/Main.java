package view;

import java.util.Arrays;
import java.util.Scanner;

import domain.Airplane;
import domain.Boat;
import domain.Bus;
import domain.Taxi;
import domain.Train;
import domain.TransportService;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransportService[] services = new TransportService[0];
        boolean running = true;

        while (running) {
            printMenu();
            int option = readInteger(scanner, "Seleccione una opcion: ");

            switch (option) {
                case 1:
                    try {
                        TransportService service = createService(scanner, getNextId(services));
                        services = addService(services, service);
                        System.out.println("Servicio creado con id " + service.getId() + ".");
                    } catch (IllegalArgumentException exception) {
                        System.out.println("No se pudo crear el servicio: " + exception.getMessage());
                    }
                    break;
                case 2:
                    showAllServices(services);
                    break;
                case 3:
                    showServiceById(services, readInteger(scanner, "Ingrese el id: "));
                    break;
                case 4:
                    showTotalFare(services);
                    break;
                case 5:
                    running = false;
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Servicios de transporte ===");
        System.out.println("1. Crear servicio");
        System.out.println("2. Mostrar todos los servicios");
        System.out.println("3. Consultar servicio por id");
        System.out.println("4. Mostrar el total de las tarifas");
        System.out.println("5. Salir");
    }

    private static TransportService createService(Scanner scanner, int id) {
        System.out.println("1. Taxi  2. Bus  3. Train  4. Airplane  5. Boat");
        int type = readInteger(scanner, "Seleccione el tipo de servicio: ");

        switch (type) {
            case 1:
                return new Taxi(
                        id,
                        readDouble(scanner, "Tarifa base: "),
                        readDouble(scanner, "Distancia en kilometros: "),
                        readDouble(scanner, "Tarifa por kilometro: "));
            case 2:
                return new Bus(
                        id,
                        readDouble(scanner, "Tarifa por pasajero: "),
                        readInteger(scanner, "Numero de pasajeros: "));
            case 3:
                return new Train(
                        id,
                        readDouble(scanner, "Distancia en kilometros: "),
                        readDouble(scanner, "Tarifa por kilometro: "),
                        readDouble(scanner, "Cargo ferroviario fijo: "));
            case 4:
                return new Airplane(
                        id,
                        readDouble(scanner, "Tarifa base: "),
                        readDouble(scanner, "Peso del equipaje en kilogramos: "),
                        readDouble(scanner, "Tarifa por kilogramo: "));
            case 5:
                return new Boat(
                        id,
                        readDouble(scanner, "Tarifa base: "),
                        readDouble(scanner, "Distancia en kilometros: "),
                        readDouble(scanner, "Tarifa por kilometro: "),
                        readDouble(scanner, "Cargo fijo de embarque: "));
            default:
                throw new IllegalArgumentException("Tipo de servicio no valido.");
        }
    }

    private static int getNextId(TransportService[] services) {
        int nextId = 1;
        for (TransportService service : services) {
            nextId = Math.max(nextId, service.getId() + 1);
        }
        return nextId;
    }

    private static TransportService[] addService(
            TransportService[] services, TransportService service) {
        TransportService[] expandedServices = Arrays.copyOf(services, services.length + 1);
        expandedServices[services.length] = service;
        return expandedServices;
    }

    private static void showAllServices(TransportService[] services) {
        if (services.length == 0) {
            System.out.println("No hay servicios almacenados.");
            return;
        }
        for (TransportService service : services) {
            System.out.println(service);
        }
    }

    private static void showServiceById(TransportService[] services, int id) {
        for (TransportService service : services) {
            if (service.getId() == id) {
                System.out.println(service);
                return;
            }
        }
        System.out.println("No se encontro un servicio con ese id.");
    }

    private static void showTotalFare(TransportService[] services) {
        double totalFare = 0;
        for (TransportService service : services) {
            totalFare += service.calculateFare();
        }
        System.out.printf("Total de las tarifas: %.2f%n", totalFare);
    }

    private static int readInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }
}
