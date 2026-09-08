package app;

import java.util.List;
import java.util.Scanner;
import modelo.Estudiante;
import modelo.Calificacion;
import servicio.GestionEstudiantes;
import excepciones.CodigoDuplicadoException;
import excepciones.EstudianteNoEncontradoException;
import excepciones.CalificacionInvalidaException;

public class Main {

    private static GestionEstudiantes servicio = new GestionEstudiantes();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        do {
            mostrarMenu();
            try {
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine().trim());
                System.out.println();

                switch (opcion) {
                    case 1:
                        registrarEstudiante();
                        break;
                    case 2:
                        listarEstudiantes();
                        break;
                    case 3:
                        buscarEstudiante();
                        break;
                    case 4:
                        registrarCalificacion();
                        break;
                    case 5:
                        calcularPromedio();
                        break;
                    case 6:
                        System.out.println("Gracias por utilizar el sistema. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numérico válido.");
            }

            if (opcion != 6) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("   SISTEMA DE GESTIÓN DE ESTUDIANTES    ");
        System.out.println("========================================");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante por código");
        System.out.println("4. Registrar calificación");
        System.out.println("5. Calcular promedio");
        System.out.println("6. Salir");
        System.out.println("========================================");
    }

    private static void registrarEstudiante() {
        System.out.println("--- REGISTRO DE ESTUDIANTE ---");
        System.out.print("Ingrese código: ");
        String codigo = scanner.nextLine().trim();

        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            System.out.println("Error: Ningún campo puede quedar vacío.");
            return;
        }

        try {
            Estudiante nuevo = new Estudiante(codigo, nombre, apellido);
            servicio.registrarEstudiante(nuevo);
            System.out.println("Estudiante registrado exitosamente.");
        } catch (CodigoDuplicadoException e) {
            System.out.println("Error de negocio: " + e.getMessage());
        }
    }

    private static void listarEstudiantes() {
        System.out.println("--- LISTA DE ESTUDIANTES REGISTRADOS ---");
        List<Estudiante> lista = servicio.listarEstudiantes();

        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados en el sistema.");
            return;
        }