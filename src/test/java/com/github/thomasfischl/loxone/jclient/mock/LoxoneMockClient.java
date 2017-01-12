package com.github.thomasfischl.loxone.jclient.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.thomasfischl.loxone.jclient.LoxoneClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoxoneMockClient extends LoxoneClient {

  private final Map<String, LoxoneClientRequest> requests = new HashMap<>();

  public LoxoneMockClient(InputStream is) throws IOException {
    super(null, -1, null, null);

    List<LoxoneClientRequest> reqList = new Gson()
        .fromJson(
            new InputStreamReader(is),
            new TypeToken<ArrayList<LoxoneClientRequest>>() {
            }.getType());

    for (LoxoneClientRequest req : reqList) {
      requests.put(req.getUrl(), req);
    }
  }

  @Override
  public String process(String relativeUrl) throws IOException {
    if (requests.containsKey(relativeUrl)) {
      return requests.get(relativeUrl).getResponse();
    }
    throw new RuntimeException("No response available for url '" + relativeUrl + "'");
  }

}
