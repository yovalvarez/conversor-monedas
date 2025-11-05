import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {
    public static void exibirMenu() {
        System.out.println("Conversor de Monedas");
        System.out.println("1. Convertir de CLP a USD");
        System.out.println("2. Convertir de CLP a EUR");
        System.out.println("3. Convertir de CLP a COP");
        System.out.println("4. Convertir de CLP a BRS");
        System.out.println("5. Convertir de CLP a ARS");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    // Obtener los datos de la API de ExchangeRate-API
    public static void obtenerDatosApi(String divisa, String monto, String convertirDivisa) throws Exception {
        // URL de la api
        String apiKey = "91c88a9a8f87999e26794937";
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + divisa;
        
        try {
            // Crear el cliente HTTP
            HttpClient cliente = HttpClient.newHttpClient();

            // Crear la solicitud HTTP
            HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

            // Enviar la solicitud y recibir la respuesta
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            // Analizar la respuesta JSON con Gson
            JsonObject json = JsonParser.parseString(respuesta.body()).getAsJsonObject();
        
            // Validar la respuesta de la API
            if (json.isEmpty()) {
                System.out.println("Error: Respuesta vacia de la API.");
                return;
            }

            if (!json.has("conversion_rates")) {
                System.out.println("Error: La respuesta de la API no contiene tasas de conversion.");
                return;
            }

            // Mostrar las tasas de conversion
            if (json.has("conversion_rates")) {
                JsonObject conversion = json.getAsJsonObject("conversion_rates");
                if (!conversion.has(divisa)) {
                    System.out.println("Error: La divisa ingresada no es valida.");
                    return;
                }
                  
                double valor = conversion.get(convertirDivisa).getAsDouble();
                double resultado = valor * Double.parseDouble(monto);
                System.out.println(monto + " " + divisa + " = " + resultado + " " + convertirDivisa);
            }

        } catch (Exception e) {
            System.out.println("Error al obtener los datos de la API: " + e.getMessage());
        }
    }
}

