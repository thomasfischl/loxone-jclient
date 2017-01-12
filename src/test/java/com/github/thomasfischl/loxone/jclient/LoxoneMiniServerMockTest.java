package com.github.thomasfischl.loxone.jclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.github.thomasfischl.loxone.jclient.mock.LoxoneMockClient;

public class LoxoneMiniServerMockTest {

  @Test
  public void testSps() throws Exception {

    try (InputStream is = getClass().getResourceAsStream("/record-testSps.json")) {

      try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneMockClient(is))) {
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
  }

  @Test
  public void testCfg() throws Exception {
    try (InputStream is = getClass().getResourceAsStream("/record-testCfg.json")) {

      try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneMockClient(is))) {
        assertEquals("", server.cfg().getConfigurationPort());
        assertEquals("DemoServer", server.cfg().getDeviceName());
        assertEquals("Yes", server.cfg().getDhcpConfiguration());
        assertEquals("192.168.1.1", server.cfg().getDnsAddress1());
        assertEquals("0.0.0.0", server.cfg().getDnsAddress2());
        assertEquals("Nov 11 2016 12:26:21", server.cfg().getFirmwareProductionDate());
        assertEquals("8.1.11.7", server.cfg().getFirmwareVersion());
        assertEquals("No", server.cfg().getFtpLocalPort());
        assertEquals(21, server.cfg().getFtpPort());
        assertEquals("192.168.1.1", server.cfg().getGatewayAddress());
        assertEquals(80, server.cfg().getHttpPort());
        assertEquals("192.168.1.100", server.cfg().getIpAddress());
        assertEquals("50:4F:94:15:B4:27", server.cfg().getMacAddress());
        assertEquals("0.pool.ntp.org", server.cfg().getNtpAddress());
        assertEquals("255.255.255.0", server.cfg().getSubnetMask());
        assertEquals("+01:00:00", server.cfg().getTimezoneOffset());
      }
    }
  }

  @Test
  public void testLan() throws Exception {
    try (InputStream is = getClass().getResourceAsStream("/record-testLan.json")) {

      try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneMockClient(is))) {
        assertEquals(0, server.lan().getBufferErrors());
        assertEquals(0, server.lan().getEofErrors());
        assertEquals(0, server.lan().getNoBufferErrors());
        assertEquals(0, server.lan().getOverflowErrors());
        assertEquals(33055, server.lan().getPacketsRecieved());
        assertEquals(29325, server.lan().getPacketsSend());
        assertEquals(0, server.lan().getPacketsSendCollisions());
        assertEquals(0, server.lan().getPacketsSendErrors());
        assertEquals(0, server.lan().getUnderrunErrors());
      }
    }
  }

  @Test
  public void testBus() throws Exception {
    try (InputStream is = getClass().getResourceAsStream("/record-testBus.json")) {

      try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneMockClient(is))) {
        assertEquals(0, server.bus().getFrameErrors());
        assertEquals(0, server.bus().getOverflowErrors());
        assertEquals(916, server.bus().getPacketsReceived());
        assertEquals(2157, server.bus().getPacketsSend());
        assertEquals(0, server.bus().getParityErrors());
        assertEquals(0, server.bus().getReceiveErrors());
      }
    }
  }

  @Test
  public void testSys() throws Exception {
    try (InputStream is = getClass().getResourceAsStream("/record-testSys.json")) {

      try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneMockClient(is))) {
        assertEquals("1:User admin IP: 195.50.84.65", server.sys().getActiveConnections());
        assertEquals(0, server.sys().getCommunicationInterrupts());
        assertEquals(174037184, server.sys().getContextSwitches());
        assertEquals("26%", server.sys().getCpuUsage());
        assertEquals(100, server.sys().getCycles());
        assertEquals("2017-01-03", server.sys().getDate());
        assertEquals(25306571, server.sys().getInterruptTriggeredContextSwitches());
        assertEquals(62386, server.sys().getLanInterrupts());
        assertEquals("15437/52688kB", server.sys().getMemoryUsage());
        assertEquals(51, server.sys().getNumberOfTasks());
        assertEquals(24651853, server.sys().getSysInterrupts());
        assertEquals(68161773, server.sys().getTaskContextSwitches(0));
        assertEquals("Idle", server.sys().getTaskName(0));
        assertEquals("", server.sys().getTaskPriority(0));
        assertEquals("208/8192", server.sys().getTaskStack(0));
        assertEquals("Ready", server.sys().getTaskState(0));
        assertEquals(0, server.sys().getTaskWaitings(0));

        assertEquals(
            "SD performance read=851kB/s write=586kB/s no error (0 0), ManufactorerID 2 Date 2016/3 CardType 2 Blocksize 512 Erase 0 MaxtransferRate 25000000 RWfactor 2 ReadSpeed 22222222Hz WriteSpeed 22222222Hz MaxReadCurrentVDDmin 3 MaxReadCurrentVDDmax 5 MaxWriteCurrentVDDmin 5 MaxWriteCurrentVDDmax 1",
            server.sys().testSdCard());
      }
    }
  }

  @Test
  public void testMiniServer() throws Exception {
    try (InputStream is = getClass().getResourceAsStream("/record-testMiniServer.json")) {

      try (LoxoneMiniServer server = new LoxoneMiniServer(new LoxoneMockClient(is))) {
        assertTrue(server.getStatus().contains("<Miniserver Type=\"0\" Name=\"Loxone Miniserver\" IP=\"192.168.1.100\""));
        assertTrue(server.getSystemLogs().length() > 0);

        assertEquals(0, server.readSensorValue("Q2").getValueAsInt());
        assertEquals(20.8, server.readSensorValue("Temperatur Bewegungsmelder Gang1").getValueAsDouble(), 0);
      }
    }
  }

}
