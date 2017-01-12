package com.github.thomasfischl.loxone.jclient;

import java.io.IOException;

public class LoxoneConfigClient {

  private final LoxoneClient client;

  public LoxoneConfigClient(LoxoneClient client) {
    this.client = client;
  }

  /**
   * Retrieve MAC address
   */
  public String getMacAddress() throws IOException {
    return client.processAsJson("/jdev/cfg/mac").getValue();
  }

  /**
   * Retrieve firmware version
   */
  public String getFirmwareVersion() throws IOException {
    return client.processAsJson("/jdev/cfg/version").getValue();
  }

  /**
   * Retrieve firmware production date
   */
  public String getFirmwareProductionDate() throws IOException {
    return client.processAsJson("/jdev/cfg/versiondate").getValue();
  }

  /**
   * Retrieve DHCP configuration
   */
  public String getDhcpConfiguration() throws IOException {
    return client.processAsJson("/jdev/cfg/dhcp").getValue();
  }

  /**
   * Retrieve IP address
   */
  public String getIpAddress() throws IOException {
    return client.processAsJson("/jdev/cfg/ip").getValue();
  }

  /**
   * Retrieve IP subnet mask
   */
  public String getSubnetMask() throws IOException {
    return client.processAsJson("/jdev/cfg/mask").getValue();
  }

  /**
   * Retrieve IP Gateway address
   */
  public String getGatewayAddress() throws IOException {
    return client.processAsJson("/jdev/cfg/gateway").getValue();
  }

  /**
   * Retrieve Miniserver device name
   */
  public String getDeviceName() throws IOException {
    return client.processAsJson("/jdev/cfg/device").getValue();
  }

  /**
   * Retrieve DNS address 1
   */
  public String getDnsAddress1() throws IOException {
    return client.processAsJson("/jdev/cfg/dns1").getValue();
  }

  /**
   * Retrieve DNS address 2
   */
  public String getDnsAddress2() throws IOException {
    return client.processAsJson("/jdev/cfg/dns2").getValue();
  }

  /**
   * Retrieve NTP address
   */
  public String getNtpAddress() throws IOException {
    return client.processAsJson("/jdev/cfg/ntp").getValue();
  }

  /**
   * Retrieve time zone offset
   */
  public String getTimezoneOffset() throws IOException {
    return client.processAsJson("/jdev/cfg/timezoneoffset").getValue();
  }

  /**
   * Retrieve HTTP port
   */
  public int getHttpPort() throws IOException {
    return client.processAsJson("/jdev/cfg/http").getValueAsInt();
  }

  /**
   * Retrieve FTP port
   */
  public int getFtpPort() throws IOException {
    return client.processAsJson("/jdev/cfg/ftp").getValueAsInt();
  }

  /**
   * Retrieve configuration software port
   */
  public String getConfigurationPort() throws IOException {
    return client.processAsJson("/jdev/cfg/LoxPLAN").getValue();
  }

  /**
   * Retrieve ‘FTP, Telnet, local software access only’
   */
  public String getFtpLocalPort() throws IOException {
    return client.processAsJson("/jdev/cfg/ftllocalonly").getValue();
  }

}
