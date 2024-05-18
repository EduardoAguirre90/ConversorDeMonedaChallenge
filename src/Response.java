import com.google.gson.JsonObject;

public class Response {
    private int codigoRespuesta;
    private JsonObject tasas;

    public Response() {
    }

    public Response(int codigoRespuesta, JsonObject tasas) {
        this.codigoRespuesta = codigoRespuesta;
        this.tasas = tasas;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public JsonObject getTasas() {
        return tasas;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setTasas(JsonObject tasas) {
        this.tasas = tasas;
    }

    @Override
    public String toString() {
        return "Response{" +
                "codigoRespuesta=" + codigoRespuesta +
                ", tasas=" + tasas +
                '}';
    }
}
