package com.github.thomasfischl.loxone.jclient;

import java.io.IOException;

import com.google.common.base.Strings;

/**
 * Base class to start communication with the loxone mini server
 *
 */
public class LoxoneMiniServer implements AutoCloseable {

  private final LoxoneClient client;

  // -----------------------------------------------------------------------------------

  public LoxoneMiniServer(String host, String username, String password) {
    this(host, 80, username, password);
  }

  public LoxoneMiniServer(String host, int port, String username, String password) {
    client = new LoxoneClient(host, port, username, password);
  }

  public LoxoneMiniServer(LoxoneClient client) {
    this.client = client;
  }

  @Override
  public void close() throws Exception {
    client.close();
  }

  // -----------------------------------------------------------------------------------

  public LoxoneSpsClient sps() {
    return new LoxoneSpsClient(client);
  }

  public LoxoneConfigClient cfg() {
    return new LoxoneConfigClient(client);
  }

  public LoxoneBusClient bus() {
    return new LoxoneBusClient(client);
  }

  public LoxoneLanClient lan() {
    return new LoxoneLanClient(client);
  }

  public LoxoneSysClient sys() {
    return new LoxoneSysClient(client);
  }

  // -----------------------------------------------------------------------------------

  /**
   * Shows the status of the Miniserver and all connected extensions
   * 
   * @return the whole xml file
   */
  public String getStatus() throws IOException {
    return client.process("/data/status");
  }

  /**
   * fetch system logs
   * 
   */
  public String getSystemLogs() throws IOException {
    return client.process("/dev/fsget/log/def.log");
  }

  /**
   * read sensor value
   */
  public LoxoneResponse readSensorValue(String id) throws IOException {
    return client.processAsJson("/jdev/sps/io/" + id);
  }

  /**
   * read sensor value
   */
  public LoxoneResponse readSensorValue(LoxoneObject obj) throws IOException {
    if (Strings.isNullOrEmpty(obj.getId())) {
      return client.processAsJson("/jdev/sps/io/" + obj.getName());
    }
    return client.processAsJson("/jdev/sps/io/" + obj.getId());
  }
}
