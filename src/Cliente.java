import java.util.Scanner;
import com.google.gson.JsonObject;


public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Historial historial = new Historial();
        System.out.println("'\n'***********Bienvenido al programa de conversion de monedas***********");
        System.out.println("'\n'Los Cambios de moneda se pueden elegir entre las siguientes divisas:");
        System.out.println("[AED, AFN, ALL, AMD, ANG, AOA, ARS, AUD, AWG, AZN, " +
                "BAM, BBD, BDT, BGN, BHD, BIF, BMD, BND, BOB, BRL, BSD, BTN, BWP, BYN, BZD, " +
                "'\n'CAD, CDF, CHF, CLP, CNY, COP, CRC, CUP, CVE, CZK, DJF, DKK, DOP, DZD, " +
                "EGP, ERN, ETB, EUR, FJD, FKP, FOK, GBP, GEL, GGP, GHS, GIP, GMD, GNF, GTQ, GYD, " +
                "'\n'HKD, HNL, HRK, HTG, HUF, IDR, ILS, IMP, INR, IQD, IRR, ISK, JEP, JMD, JOD, JPY, " +
                "KES, KGS, KHR, KID, KMF, KRW, KWD, KYD, KZT, LAK, LBP, LKR, LRD, LSL, LYD, " +
                "'\n'MAD, MDL, MGA, MKD, MMK, MNT, MOP, MRU, MUR, MVR, MWK, MXN, MYR, MZN, " +
                "NAD, NGN, NIO, NOK, NPR, NZD, OMR, PAB, PEN, PGK, PHP, PKR, PLN, PYG, QAR, RON, RSD, RUB, RWF, " +
                "'\n'SAR, SBD, SCR, SDG, SEK, SGD, SHP, SLE, SLL, SOS, SRD, SSP, STN, SYP, SZL, " +
                "'THB, TJS, TMT, TND, TOP, TRY, TTD, TVD, TWD, TZS, UAH, UGX, USD, UYU, UZS, " +
                "'\n'VES, VND, VUV, WST, XAF, XCD, XDR, XOF, XPF, YER, ZAR, ZMW, ZWL]'\n\n'");

        boolean continuar = true;
        while (continuar) {
            System.out.println("***********Ingrese la divisa base (por ejemplo, USD): ");
            String divisaBase = scanner.next().toUpperCase();
            System.out.println("**********Ingrese la divisa a convertir (por ejemplo, EUR): ");
            String divisaObjetivo = scanner.next().toUpperCase();
            System.out.println("**********Ingrese el valor a convertir: ");
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

        System.out.println("Historial de consultas:");
        System.out.println(historial.toJson());

        scanner.close();
    }
}

