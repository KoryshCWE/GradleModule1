package org.example;
import com.fasterxml.jackson.databind.JsonNode;

public class NBUExchangeRate {
    private final String NBU_URI = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ExchangeRate> getNBUExchangeRates() {

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(NBU_URI))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse JSON into a list of ExchangeRate objects
            JsonNode jsonNode = objectMapper.readTree(response.body());
            List<ExchangeRate> exchangeRates = objectMapper.convertValue(jsonNode, new TypeReference<List<ExchangeRate>>() {});

            return exchangeRates;

        } catch (URISyntaxException e) {
            System.out.println("NBU URI SYNTAX EXCEPTION!");
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            System.out.println("NBU Json Mapping Exception!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("NBU IOException!");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("NBU Interrupted Exception");
            throw new RuntimeException(e);
        }
    }
}
