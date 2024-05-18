import java.util.Scanner;
import com.google.gson.JsonObject;


public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Historial historial = new Historial();

        boolean continuar = true;
        while (continuar) {
            System.out.println("Ingrese la divisa base (por ejemplo, USD): ");
            String divisaBase = scanner.next().toUpperCase();
            System.out.println("Ingrese la divisa a convertir (por ejemplo, EUR): ");
            String divisaObjetivo = scanner.next().toUpperCase();
            System.out.println("Ingrese el valor a convertir: ");
            double cantidad = scanner.nextDouble();

            String url = "https://v6.exchangerate-api.com/v6/773015d1316015493586e10b/latest/" + divisaBase;
            Request solicitud = new Request(url);
            Response respuesta = solicitud.hacerSolicitud();

            if (respuesta != null && respuesta.getCodigoRespuesta() == 200) {
                JsonObject tasas = respuesta.getTasas();
                if (tasas != null) {
                    System.out.println("Monedas disponibles: " + tasas.keySet());
                    if (tasas.has(divisaObjetivo)) {
                        double tasa = tasas.get(divisaObjetivo).getAsDouble();
                        double cantidadConvertida = cantidad * tasa;
                        System.out.printf("El valor convertido es: %.2f %s\n", cantidadConvertida, divisaObjetivo);

                        historial.agregarConversion(divisaBase, divisaObjetivo, cantidad, cantidadConvertida);
                    } else {
                        System.out.println("La divisa objetivo no está disponible.");
                    }
                } else {
                    System.out.println("No se encontraron tasas de cambio en la respuesta.");
                }
            } else {
                System.out.println("Error en la solicitud.");
            }

            System.out.println("¿Desea realizar otra consulta? (s/n): ");
            continuar = scanner.next().equalsIgnoreCase("s");
        }

        System.out.println("Historial de conversiones:");
        System.out.println(historial.toJson());

        scanner.close();
    }
}

