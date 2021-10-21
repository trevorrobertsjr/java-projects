// import java.net.http.HttpClient;
// import java.net.http.HttpHeaders;
// import java.net.http.HttpRequest;
// import java.net.http.HttpResponse;
// import java.net.http.HttpResponse.BodyHandlers;
// import java.net.URI;
// import java.time.Duration;
// import java.util.HashMap;
// import java.util.Map;

// import com.amazonaws.services.lambda.runtime.Context;
// import com.amazonaws.services.lambda.runtime.RequestHandler;

// /**
//  * Handler for requests to Lambda function.
//  */
// public class App implements RequestHandler<Object, Object> {

//     public Object handleRequest(final Object input, final Context context) {
//         Map<String, String> headers = new HashMap<>();
//         headers.put("Content-Type", "application/json");
//         headers.put("X-Custom-Header", "application/json");
//         HttpClient client = HttpClient.newHttpClient();
//         HttpRequest request = HttpRequest.newBuilder()
//             .GET()
//             .version(HttpClient.Version.HTTP_2)
//             .uri(URI.create("https://checkip.amazonaws.com"))
//             .timeout(Duration.ofSeconds(15))
//             .build();

//         try {
//             HttpResponse<String> response =
//             client.send(request, BodyHandlers.ofString());

//             String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", response.body());
//             return new GatewayResponse(output, headers, response.statusCode());    
//         } catch (Exception e) {
//             return new GatewayResponse("{}", headers, 500);
//         }
//     }
// }
package webclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WebClient {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://10.0.3.17/"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

    }

}