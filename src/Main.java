import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Conversor.exibirMenu();
        String divisa = "CLP";

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();

        String convertirDivisa = null;

        switch (opcion) {
            case 1 -> convertirDivisa = "USD";
            case 2 -> convertirDivisa = "EUR";
            case 3 -> convertirDivisa = "COP";
            case 4 -> convertirDivisa = "BRS";
            case 5 -> convertirDivisa = "ARS";
            default -> System.out.println("Opcion no valida. Intente de nuevo.");
        }

        if (opcion == 6) {
            System.out.println("Saliendo del programa.");
            return;
        }

        if (convertirDivisa != null) {
            System.out.print("Ingrese el monto a convertir: ");
            String monto = scanner.next();
            try {
                Conversor.obtenerDatosApi(divisa, monto, convertirDivisa);
            } catch (Exception e) {
                System.out.println("Error al obtener los datos de la API: " + e.getMessage());
            }
        }  
    }
}