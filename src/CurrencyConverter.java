import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter {
    private String apiKey;

    public CurrencyConverter(String apiKey) {
        this.apiKey = apiKey;
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        try {
            String urlStr = "https://v6.exchangerate-api.com/v6/773015d1316015493586e10b/latest/" + "/latest/" + fromCurrency;//"https://v6.exchangerate-api.com/v6/773015d1316015493586e10b/latest/"+fromCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            conn.disconnect();

            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(response.toString()).getAsJsonObject();
            JsonObject rates = json.getAsJsonObject("rates");
            JsonElement rateElement = rates.get(toCurrency);

            double conversionRate = rateElement.getAsDouble();

            return amount * conversionRate;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Error occurred during conversion
        }
    }

    public static void main(String[] args) {
        String apiKey = "773015d1316015493586e10b";
        CurrencyConverter converter = new CurrencyConverter(apiKey);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Ingrese la cantidad a convertir: ");
            double amount = Double.parseDouble(reader.readLine());

            System.out.print("Ingrese la moneda de origen (ej. USD): ");
            String fromCurrency = reader.readLine().toUpperCase();

            System.out.print("Ingrese la moneda de destino (ej. EUR): ");
            String toCurrency = reader.readLine().toUpperCase();

            double convertedAmount = converter.convert(amount, fromCurrency, toCurrency);
            if (convertedAmount != -1) {
                System.out.println(amount + " " + fromCurrency + " equivale a " + convertedAmount + " " + toCurrency);
            } else {
                System.out.println("No se pudo realizar la conversión.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


