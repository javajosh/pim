package com.javajosh.pim;

import java.io.IOException;
import java.net.*;
import java.net.http.*;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * This style of HTTPClient was introduced into the Java 11 standard library. It competes with Apache HttpClient.
 */
public class HttpClientJava {

  public static void main(String[] args) throws IOException, InterruptedException {
    // 1 - Build a client
    HttpClient client = HttpClient.newBuilder()
      .version(HttpClient.Version.HTTP_1_1)
      .followRedirects(HttpClient.Redirect.NORMAL)
      .connectTimeout(Duration.ofSeconds(20))
      //.proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
      //.authenticator(Authenticator.getDefault())
      .build();

    // 2 - Build a request
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("http://localhost:8080/item?name=" + HttpClientJava.class.getCanonicalName()))
      .timeout(Duration.ofSeconds(30))
      .header("Content-Type", "application/json")
      //.POST(HttpRequest.BodyPublishers.ofFile(Paths.get("file.json")))
      .build();

    // 3 - Get the response (this form is blocking)
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    System.out.println(response.statusCode());
    System.out.println(response.body());
  }
}
