import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpRequest {
    public static void main(String[] args) {
        try {
            //Solicitar peticion
            //https://v6.exchangerate-api.com/v6/773015d1316015493586e10b/latest/USD
            String urlStr = "https://v6.exchangerate-api.com/v6/773015d1316015493586e10b/latest/USD";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //ver si la peticion es correcta
            int responseCode = conn.getResponseCode();
            if(responseCode != 200){
                throw new RuntimeException("Ocurrio un error: "+ responseCode);
            }else {
                //abrir un escaner que lea el flujo de datos
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()){
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                //solo arroja la informacion de solicitud para USD
                System.out.println(informationString);

                //pintar la informacion obtenida
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
