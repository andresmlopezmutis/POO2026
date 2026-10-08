package view;

import java.util.Arrays;
import java.util.Scanner;

import domain.Circle;
import domain.Ellipse;
import domain.Rectangle;
import domain.Shape;
import domain.Square;
import domain.Triangle;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shape[] shapes = new Shape[0];
        boolean running = true;

        while (running) {
            printMenu();
            int option = readInteger(scanner, "Seleccione una opcion: ");

            switch (option) {
                case 1:
                    try {
                        Shape shape = createShape(scanner);
                        shapes = addShape(shapes, shape);
                        System.out.println("Figura creada con id " + shape.getId() + ".");
                    } catch (IllegalArgumentException exception) {
                        System.out.println("No se pudo crear la figura: " + exception.getMessage());
                    }
                    break;
                case 2:
                    showAllShapes(shapes);
                    break;
                case 3:
                    showShapeById(shapes, readInteger(scanner, "Ingrese el id: "));
                    break;
                case 4:
                    showTotals(shapes);
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
        System.out.println("\n=== Figuras geometricas ===");
        System.out.println("1. Crear figura");
        System.out.println("2. Mostrar todas las figuras");
        System.out.println("3. Consultar figura por id");
        System.out.println("4. Mostrar area y perimetro totales");
        System.out.println("5. Salir");
    }

    private static Shape createShape(Scanner scanner) {
        System.out.println("1. Square  2. Rectangle  3. Triangle  4. Circle  5. Ellipse");
        int type = readInteger(scanner, "Seleccione el tipo de figura: ");
        double x = readDouble(scanner, "Coordenada x del centro: ");
        double y = readDouble(scanner, "Coordenada y del centro: ");

        switch (type) {
            case 1:
                return new Square(x, y, readDouble(scanner, "Longitud del lado: "));
            case 2:
                return new Rectangle(
                        x, y,
                        readDouble(scanner, "Longitud del primer lado: "),
                        readDouble(scanner, "Longitud del segundo lado: "));
            case 3:
                return new Triangle(
                        x, y,
                        readDouble(scanner, "Longitud del primer lado: "),
                        readDouble(scanner, "Longitud del segundo lado: "),
                        readDouble(scanner, "Longitud del tercer lado: "));
            case 4:
                return new Circle(x, y, readDouble(scanner, "Radio: "));
            case 5:
                return new Ellipse(
                        x, y,
                        readDouble(scanner, "Longitud del primer radio: "),
                        readDouble(scanner, "Longitud del segundo radio: "));
            default:
                throw new IllegalArgumentException("Tipo de figura no valido.");
        }
    }

    private static Shape[] addShape(Shape[] shapes, Shape shape) {
        Shape[] expandedShapes = Arrays.copyOf(shapes, shapes.length + 1);
        expandedShapes[shapes.length] = shape;
        return expandedShapes;
    }

    private static void showAllShapes(Shape[] shapes) {
        if (shapes.length == 0) {
            System.out.println("No hay figuras almacenadas.");
            return;
        }
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    private static void showShapeById(Shape[] shapes, int id) {
        for (Shape shape : shapes) {
            if (shape.getId() == id) {
                System.out.println(shape);
                return;
            }
        }
        System.out.println("No se encontro una figura con ese id.");
    }

    private static void showTotals(Shape[] shapes) {
        double totalArea = 0;
        double totalPerimeter = 0;

        for (Shape shape : shapes) {
            totalArea += shape.calculateArea();
            totalPerimeter += shape.calculatePerimeter();
        }

        System.out.printf("Area total: %.2f%n", totalArea);
        System.out.printf("Perimetro total: %.2f%n", totalPerimeter);
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
