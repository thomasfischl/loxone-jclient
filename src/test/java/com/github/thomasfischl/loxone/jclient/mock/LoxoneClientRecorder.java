package com.github.thomasfischl.loxone.jclient.mock;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.github.thomasfischl.loxone.jclient.LoxoneClient;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;

public class LoxoneClientRecorder extends LoxoneClient {

  private final File recordFile;

  private final Map<String, LoxoneClientRequest> requests = new HashMap<>();

  public LoxoneClientRecorder(String host, int port, String username, String password, File recordFile) {
    super(host, port, username, password);
    this.recordFile = recordFile;
  }

  @Override
  public String process(String relativeUrl) throws IOException {
    String resp = super.process(relativeUrl);

    requests.put(relativeUrl, new LoxoneClientRequest(relativeUrl, resp));

    return resp;
  }

  @Override
  public void close() throws Exception {
    super.close();

    if (recordFile.exists()) {
      recordFile.delete();
    }

    Files.write(new Gson().toJson(requests.values()), recordFile, Charsets.UTF_8);
  }

}
