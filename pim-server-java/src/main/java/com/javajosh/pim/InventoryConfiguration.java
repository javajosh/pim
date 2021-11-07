package com.javajosh.pim;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InventoryConfiguration extends Configuration {

  @Valid
  @NotNull
  private HttpClientConfiguration httpClient = new HttpClientConfiguration();

  @JsonProperty("httpClient")
  public HttpClientConfiguration getHttpClientConfiguration() {
    return httpClient;
  }

  @JsonProperty("httpClient")
  public void setHttpClientConfiguration(HttpClientConfiguration httpClient) {
    this.httpClient = httpClient;
  }

  @NotEmpty
  private String version;

  @JsonProperty
  public String getVersion() {
    return version;
  }

  @JsonProperty
  public void setVersion(String version) {
    this.version = version;
  }

}
