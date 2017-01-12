package com.github.thomasfischl.loxone.jclient;

import java.io.IOException;
import java.util.List;

public class LoxoneSpsClient {

  private final LoxoneClient client;

  public LoxoneSpsClient(LoxoneClient client) {
    this.client = client;
  }

  /**
   * Return current PLC state
   */
  public SpsState getState() throws IOException {
    LoxoneResponse resp = client.processAsJson("/jdev/sps/state");
    return SpsState.get(resp.getValueAsInt());
  }

  /**
   * Current PLC frequency
   */
  public String getFrequency() throws IOException {
    return client.processAsJson("/jdev/sps/status").getValue();
  }

  /**
   * Restart PLC
   */
  public void restart() throws IOException {
    client.processAsJson("/jdev/sps/restart");
  }

  /**
   * Stop PLC
   */
  public void stop() throws IOException {
    client.processAsJson("/jdev/sps/stop");
  }

  /**
   * Continue PLC
   */
  public void run() throws IOException {
    client.processAsJson("/jdev/sps/continue");
  }

  /**
   * Permit global logging
   */
  public void enableLog() throws IOException {
    client.processAsJson("/jdev/sps/log");
  }

  /**
   * Disable global logging
   */
  public void disableLog() throws IOException {
    client.processAsJson("/jdev/sps/nolog");
  }

  /**
   * List all PLC devices (Miniserver, extensions)
   */
  public List<LoxoneObject> listPlcDevices() throws IOException {
    return client.processAsJson("/jdev/sps/enumdev").getValueAsObjects();
  }

  /**
   * List all PLC inputs
   */
  public List<LoxoneObject> listPlcInputs() throws IOException {
    return client.processAsJson("/jdev/sps/enumin").getValueAsObjects();
  }

  /**
   * List all PLC outputs
   */
  public List<LoxoneObject> listPlcOutputs() throws IOException {
    return client.processAsJson("/jdev/sps/enumout").getValueAsObjects();
  }

  /**
   * Identify PLC
   */
  public String identify() throws IOException {
    return client.processAsJson("/jdev/sps/identify").getValue();
  }

}
