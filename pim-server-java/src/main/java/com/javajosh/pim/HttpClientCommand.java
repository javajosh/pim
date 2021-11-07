package com.javajosh.pim;

import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

// Learning curve - Commands run well before everything else, and so there is no environment, etc.
// Commands use the DW infrastructure around configuration and invocation, but we are starting from
// scratch with the code here.
public class HttpClientCommand extends Command {

  public HttpClientCommand() {
    super("http", "get a url");
  }

  @Override
  public void configure(Subparser subparser) {
    // Add a command line option
    subparser.addArgument("-url", "--url")
      .dest("url")
      .setDefault("http://localhost:8080/item?name=httpCommand")
      .type(String.class)
      .required(true)
      .help("The url to access");
  }

  @Override
  public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
    String url = namespace.getString("url");
    System.out.println("You are trying to get " + url);

    HttpClient client = HttpClient.newBuilder()
      .version(HttpClient.Version.HTTP_1_1)
      .followRedirects(HttpClient.Redirect.NORMAL)
      .connectTimeout(Duration.ofSeconds(20))
      //.proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
      //.authenticator(Authenticator.getDefault())
      .build();

    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(url))
      .timeout(Duration.ofSeconds(30))
      .header("Content-Type", "application/json")
      //.POST(HttpRequest.BodyPublishers.ofFile(Paths.get("file.json")))
      .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    System.out.println(response.statusCode());
    System.out.println(response.body());

//    try {
//      httpClient.execute(new HttpGet("http://localhost:8080/item?name=bob%20marley"));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }
}
