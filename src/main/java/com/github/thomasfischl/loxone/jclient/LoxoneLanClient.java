package com.github.thomasfischl.loxone.jclient;

import java.io.IOException;

public class LoxoneLanClient {

  private final LoxoneClient client;

  public LoxoneLanClient(LoxoneClient client) {
    this.client = client;
  }

  /**
   * Retrieve number of LAN packets sent
   */
  public int getPacketsSend() throws IOException {
    return client.processAsJson("/jdev/lan/txp").getValueAsInt();
  }

  /**
   * Retrieve number of LAN packets sent with errors
   */
  public int getPacketsSendErrors() throws IOException {
    return client.processAsJson("/jdev/lan/txe").getValueAsInt();
  }

  /**
   * Retrieve number of LAN packets sent with collisions
   */
  public int getPacketsSendCollisions() throws IOException {
    return client.processAsJson("/jdev/lan/txc").getValueAsInt();
  }

  /**
   * Retrieve number of LAN buffer errors
   */
  public int getBufferErrors() throws IOException {
    return client.processAsJson("/jdev/lan/exh").getValueAsInt();
  }

  /**
   * Retrieve number of LAN under-run errors
   */
  public int getUnderrunErrors() throws IOException {
    return client.processAsJson("/jdev/lan/txu").getValueAsInt();
  }

  /**
   * Retrieve number of LAN packets recieved
   */
  public int getPacketsRecieved() throws IOException {
    return client.processAsJson("/jdev/lan/rxp").getValueAsInt();
  }

  /**
   * Retrieve number of LAN EOF errors
   */
  public int getEofErrors() throws IOException {
    return client.processAsJson("/jdev/lan/eof").getValueAsInt();
  }

  /**
   * Retrieve number of LAN receive overflow errors
   */
  public int getOverflowErrors() throws IOException {
    return client.processAsJson("/jdev/lan/rxo").getValueAsInt();
  }

  /**
   * Retrieve number of LAN ‘No receive buffer’ errors
   */
  public int getNoBufferErrors() throws IOException {
    return client.processAsJson("/jdev/lan/nob").getValueAsInt();
  }

}
