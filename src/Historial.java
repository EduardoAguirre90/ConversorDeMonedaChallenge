import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historial {
    private JsonArray historial;

    public Historial() {
        this.historial = new JsonArray();
    }

    public void agregarConversion(String divisaBase, String divisaObjetivo, double cantidad, double cantidadConvertida) {
        JsonObject conversion = new JsonObject();
        conversion.addProperty("divisaBase", divisaBase);
        conversion.addProperty("divisaObjetivo", divisaObjetivo);
        conversion.addProperty("cantidad", cantidad);
        conversion.addProperty("cantidadConvertida", cantidadConvertida);
        conversion.addProperty("fechaHora", obtenerFechaHoraActual());
        this.historial.add(conversion);
    }

    private String obtenerFechaHoraActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public String toJson() {
        return this.historial.toString();
    }
}
