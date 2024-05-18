import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {
    private final String url;

    public Request(String url) {
        this.url = url;
    }

    public Response hacerSolicitud() {
        try {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            int codigoRespuesta = respuesta.statusCode();
            String cuerpoRespuesta = respuesta.body();

            System.out.println("Respuesta completa de la API: " + cuerpoRespuesta);

            if (codigoRespuesta == 200) {
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(cuerpoRespuesta).getAsJsonObject();
                System.out.println("JSON parseado: " + json.toString());
                JsonObject tasas = json.has("conversion_rates") ? json.getAsJsonObject("conversion_rates") : null;
                if (tasas != null) {
                    System.out.println("Tasas de cambio obtenidas: " + tasas.toString());
                } else {
                    System.out.println("El campo 'conversion_rates' no está presente en la respuesta.");
                }
                return new Response(codigoRespuesta, tasas);
            } else {
                System.out.println("Código de respuesta: " + codigoRespuesta);
                return new Response(codigoRespuesta, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
