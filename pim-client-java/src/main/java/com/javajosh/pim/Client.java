package com.javajosh.pim;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

public class Client {

  public static void main(String[] args) throws IOException, InterruptedException {

    HttpClient client = HttpClient.newBuilder()
      .version(HttpClient.Version.HTTP_1_1)
      .followRedirects(HttpClient.Redirect.NORMAL)
      .connectTimeout(Duration.ofSeconds(20))
      //.proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
      //.authenticator(Authenticator.getDefault())
      .build();

    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("http://localhost:8080/item?name=bob%20marley"))
      .timeout(Duration.ofSeconds(30))
      .header("Content-Type", "application/json")
      //.POST(HttpRequest.BodyPublishers.ofFile(Paths.get("file.json")))
      .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    System.out.println(response.statusCode());
    System.out.println(response.body());
  }
}
