package com.github.thomasfischl.loxone.jclient;

import java.io.IOException;

public class LoxoneBusClient {

  private final LoxoneClient client;

  public LoxoneBusClient(LoxoneClient client) {
    this.client = client;
  }

  /**
   * Retrieve number of Packets sent to CAN-bus
   */
  public int getPacketsSend() throws IOException {
    return client.processAsJson("/jdev/bus/packetssent").getValueAsInt();
  }

  /**
   * Retrieve number of packets received on CAN-bus
   */
  public int getPacketsReceived() throws IOException {
    return client.processAsJson("/jdev/bus/packetsreceived").getValueAsInt();
  }

  /**
   * Retrieve number of receive errors on CAN-bus
   */
  public int getReceiveErrors() throws IOException {
    return client.processAsJson("/jdev/bus/receiveerrors").getValueAsInt();
  }

  /**
   * Retrieve number of frame errors on CAN-bus
   */
  public int getFrameErrors() throws IOException {
    return client.processAsJson("/jdev/bus/frameerrors").getValueAsInt();
  }

  /**
   * Retrieve number of overflow errors on CAN-bus
   */
  public int getOverflowErrors() throws IOException {
    return client.processAsJson("/jdev/bus/overruns").getValueAsInt();
  }

  /**
   * Retrieve number of parity errors on CAN-bus
   */
  public int getParityErrors() throws IOException {
    return client.processAsJson("/jdev/bus/parityerrors").getValueAsInt();
  }

}
