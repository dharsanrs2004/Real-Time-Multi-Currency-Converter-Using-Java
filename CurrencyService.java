import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyService {

    public double getRate(String from, String to) {
        try {
            String url = "https://open.er-api.com/v6/latest/" + from;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            // find "INR": value
            String key = "\"" + to + "\":";
            int index = body.indexOf(key);

            if (index == -1) {
                throw new RuntimeException("Currency not found");
            }

            int start = index + key.length();
            int end = body.indexOf(",", start);

            return Double.parseDouble(body.substring(start, end));

        } catch (Exception e) {
            System.out.println("Error fetching exchange rate");
            return 0;
        }
    }
}
