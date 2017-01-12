package com.github.thomasfischl.loxone.jclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.github.thomasfischl.loxone.jclient.mock.LoxoneClientRecorder;

@Ignore("Is used to test against real lonxone mini searver or to record new mock files")
public class LoxoneMiniServerTest {

  private static final String HOST = "90.146.128.29";
  private static final String USERNAME = "admin";
  private static final String PASSWORD = "Faba8888";
  private static final int PORT = 80;

  @Test
  public void testSps() throws Exception {
    File recordFile = new File("./record-testSps.json");
    try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneClientRecorder(HOST, PORT, USERNAME, PASSWORD, recordFile))) {
      assertEquals(SpsState.RUNNING, server.sps().getState());
      assertEquals("Running 100/sec", server.sps().getFrequency());

      List<LoxoneObject> devices = server.sps().listPlcDevices();
      assertEquals(3, devices.size());
      assertEquals("Loxone Miniserver", devices.get(0).getName());
      assertEquals("Dimmer Extension 1", devices.get(1).getName());
      assertEquals("Tree Extension", devices.get(2).getName());

      List<LoxoneObject> inputs = server.sps().listPlcInputs();
      assertEquals(21, inputs.size());
      assertEquals("Eingang I1", inputs.get(0).getName());
      assertEquals("Spannung AI1", inputs.get(8).getName());
      assertEquals("Input 8", inputs.get(20).getName());

      List<LoxoneObject> outputs = server.sps().listPlcOutputs();
      assertEquals(17, outputs.size());
      assertEquals("Licht Schwarz", outputs.get(0).getName());
      assertEquals("Dimmer 2", outputs.get(14).getName());

      assertEquals("", server.sps().identify());
    }
  }

  @Test
  public void testCfg() throws Exception {
    File recordFile = new File("./record-testCfg.json");
    try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneClientRecorder(HOST, PORT, USERNAME, PASSWORD, recordFile))) {
      assertEquals("", server.cfg().getConfigurationPort());
      assertEquals("lxlB033", server.cfg().getDeviceName());
      assertEquals("Yes", server.cfg().getDhcpConfiguration());
      assertEquals("192.168.1.1", server.cfg().getDnsAddress1());
      assertEquals("0.0.0.0", server.cfg().getDnsAddress2());
      assertEquals("Nov 11 2016 12:26:21", server.cfg().getFirmwareProductionDate());
      assertEquals("8.1.11.11", server.cfg().getFirmwareVersion());
      assertEquals("No", server.cfg().getFtpLocalPort());
      assertEquals(21, server.cfg().getFtpPort());
      assertEquals("192.168.1.1", server.cfg().getGatewayAddress());
      assertEquals(80, server.cfg().getHttpPort());
      assertEquals("192.168.1.8", server.cfg().getIpAddress());
      assertEquals("50:4F:94:10:B0:33", server.cfg().getMacAddress());
      assertEquals("0.pool.ntp.org", server.cfg().getNtpAddress());
      assertEquals("255.255.255.0", server.cfg().getSubnetMask());
      assertEquals("+01:00:00", server.cfg().getTimezoneOffset());
    }
  }

  @Test
  public void testLan() throws Exception {
    File recordFile = new File("./record-testLan.json");
    try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneClientRecorder(HOST, PORT, USERNAME, PASSWORD, recordFile))) {
      assertEquals(0, server.lan().getBufferErrors());
      assertEquals(0, server.lan().getEofErrors());
      assertEquals(0, server.lan().getNoBufferErrors());
      assertEquals(0, server.lan().getOverflowErrors());
      assertTrue(server.lan().getPacketsRecieved() > 0);
      assertTrue(server.lan().getPacketsSend() > 0);
      assertEquals(0, server.lan().getPacketsSendCollisions());
      assertEquals(0, server.lan().getPacketsSendErrors());
      assertEquals(0, server.lan().getUnderrunErrors());
    }
  }

  @Test
  public void testBus() throws Exception {
    File recordFile = new File("./record-testBus.json");
    try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneClientRecorder(HOST, PORT, USERNAME, PASSWORD, recordFile))) {
      assertEquals(0, server.bus().getFrameErrors());
      assertEquals(0, server.bus().getOverflowErrors());
      assertTrue(server.bus().getPacketsReceived() > 0);
      assertTrue(server.bus().getPacketsSend() > 0);
      assertEquals(0, server.bus().getParityErrors());
      assertEquals(0, server.bus().getReceiveErrors());
    }
  }

  @Test
  public void testSys() throws Exception {
    File recordFile = new File("./record-testSys.json");
    try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneClientRecorder(HOST, PORT, USERNAME, PASSWORD, recordFile))) {
      assertTrue(server.sys().getActiveConnections().length() > 0);
      assertEquals(0, server.sys().getCommunicationInterrupts());
      assertTrue(server.sys().getContextSwitches() > 0);
      assertTrue(server.sys().getCpuUsage().length() > 0);
      assertTrue(server.sys().getCycles() > 0);
      assertTrue(server.sys().getDate().length() > 0);
      assertTrue(server.sys().getInterruptTriggeredContextSwitches() > 0);
      assertTrue(server.sys().getLanInterrupts() > 0);
      assertTrue(server.sys().getMemoryUsage().length() > 0);
      assertTrue(server.sys().getNumberOfTasks() > 0);
      assertTrue(server.sys().getSysInterrupts() > 0);
      assertTrue(server.sys().getTime().length() > 0);

      assertTrue(server.sys().getTaskContextSwitches(0) > 0);
      assertEquals("Idle", server.sys().getTaskName(0));
      assertEquals("", server.sys().getTaskPriority(0));
      assertTrue(server.sys().getTaskStack(0).endsWith("/8192"));
      assertEquals("Ready", server.sys().getTaskState(0));
      assertEquals(0, server.sys().getTaskWaitings(0));

      assertTrue(server.sys().testSdCard().length() > 0);
    }
  }

  @Test
  public void testMiniServer() throws Exception {
    File recordFile = new File("./record-testMiniServer.json");
    try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneClientRecorder(HOST, PORT, USERNAME, PASSWORD, recordFile))) {
      assertTrue(server.getStatus().length() > 0);
      assertTrue(server.getSystemLogs().length() > 0);

      assertEquals(0, server.readSensorValue("Q2").getValueAsInt());
      assertEquals(20.8, server.readSensorValue("Temperatur Bewegungsmelder Gang1").getValueAsDouble(), 0);
    }
  }

}
