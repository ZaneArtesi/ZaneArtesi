package major_project.model.HTTPrequests.OutputAPI;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class PasteBinSend {
    public static String sendPost(String data, String newURI) {

        String toReturn;
        try {
            byte arr[] = data.getBytes(StandardCharsets.UTF_8);

            HttpRequest request = HttpRequest.newBuilder(new URI(newURI)).
                    header("Content-Type", "application/x-www-form-urlencoded").
                    POST(HttpRequest.BodyPublishers.ofByteArray(arr)).
                    build();

            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body() + " " + response.statusCode() + " " + response.headers());
            toReturn = response.body().toString();
            return toReturn;

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
