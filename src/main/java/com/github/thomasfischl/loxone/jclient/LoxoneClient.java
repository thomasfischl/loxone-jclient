package com.github.thomasfischl.loxone.jclient;

import java.io.IOException;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.common.net.UrlEscapers;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoxoneClient implements AutoCloseable {

  private final String username;
  private final String password;
  private final String host;
  private final int port;
  private final Gson gson = new Gson();

  private CloseableHttpClient client;

  public LoxoneClient(String host, int port, String username, String password) {

    this.host = host;
    this.port = port;
    this.username = username;
    this.password = password;

    client = HttpClientBuilder.create().build();
  }

  @Override
  public void close() throws Exception {
    if (client != null) {
      client.close();
      client = null;
    }
  }

  protected String generateUrl(String relativeUrl) {
    return "http://" + username + ":" + password + "@" + host + ":" + port + relativeUrl;
  }

  public String process(String relativeUrl) throws IOException {
    String url = UrlEscapers.urlFragmentEscaper().escape(generateUrl(relativeUrl));
    log.debug("Call Loxone Url: " + url);

    HttpGet req = new HttpGet(url);
    CloseableHttpResponse resp = client.execute(req);
    String response = EntityUtils.toString(resp.getEntity());
    if (resp.getStatusLine().getStatusCode() != 200) {
      throw new HttpResponseException(resp.getStatusLine().getStatusCode(), response);
    }

    log.debug("Loxone Response: " + response);
    return response;
  }

  public LoxoneResponse processAsJson(String relativeUrl) throws IOException {
    String rawResp = process(relativeUrl);
    LoxoneResponse resp = gson.fromJson(new JsonParser().parse(rawResp).getAsJsonObject().get("LL"), LoxoneResponse.class);
    if (resp.getCodeAsInt() != 200) {
      throw new HttpResponseException(resp.getCodeAsInt(), rawResp);
    }
    return resp;
  }

}
