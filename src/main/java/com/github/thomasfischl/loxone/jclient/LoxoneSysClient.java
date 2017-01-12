package com.github.thomasfischl.loxone.jclient;

import java.io.IOException;

public class LoxoneSysClient {

  private final LoxoneClient client;

  public LoxoneSysClient(LoxoneClient client) {
    this.client = client;
  }

  /**
   * Retrieve number of tasks
   */
  public int getNumberOfTasks() throws IOException {
    return client.processAsJson("/jdev/sys/numtasks").getValueAsInt();
  }

  /**
   * Retrieve CPU load
   */
  public String getCpuUsage() throws IOException {
    return client.processAsJson("/jdev/sys/cpu").getValue();
  }

  /**
   * Retrieve number of switchings between tasks
   */
  public int getContextSwitches() throws IOException {
    return client.processAsJson("/jdev/sys/contextswitches").getValueAsInt();
  }

  /**
   * Retrieve number of switchings between tasks that were triggered interrupts
   */
  public int getInterruptTriggeredContextSwitches() throws IOException {
    return client.processAsJson("/jdev/sys/contextswitchesi").getValueAsInt();
  }

  /**
   * Retrieve memory size
   */
  public String getMemoryUsage() throws IOException {
    return client.processAsJson("/jdev/sys/heap").getValue();
  }

  /**
   * Retrieve number of system interrupts
   */
  public int getSysInterrupts() throws IOException {
    return client.processAsJson("/jdev/sys/ints").getValueAsInt();
  }

  /**
   * Retrieve number of communication interrupts
   */
  public int getCommunicationInterrupts() throws IOException {
    return client.processAsJson("/jdev/sys/comints").getValueAsInt();
  }

  /**
   * Retrieve number of LAN interrupts
   */
  public int getLanInterrupts() throws IOException {
    return client.processAsJson("/jdev/sys/lanints").getValueAsInt();
  }

  /**
   * Returns the local date
   */
  public String getDate() throws IOException {
    return client.processAsJson("/jdev/sys/date").getValue();
  }

  /**
   * Returns the local time
   */
  public String getTime() throws IOException {
    return client.processAsJson("/jdev/sys/time").getValue();
  }

  /**
   * Retrieve number of PLC cycles
   */
  public int getCycles() throws IOException {
    return client.processAsJson("/jdev/sys/spscycle").getValueAsInt();
  }

  /**
   * Boot Miniserver
   */
  public void reboot() throws IOException {
    client.processAsJson("/jdev/sys/reboot").getValueAsInt();
  }

  /**
   * Displays active connections in Loxone Config
   */
  public String getActiveConnections() throws IOException {
    return client.processAsJson("/jdev/sys/check").getValue();
  }

  /**
   * Ends any existing connections in Loxone Config
   */
  public int logoff() throws IOException {
    return client.processAsJson("/jdev/sys/logoff").getValueAsInt();
  }

  /**
   * Tests the SD card
   */
  public String testSdCard() throws IOException {
    return client.processAsJson("/jdev/sys/sdtest").getValue();
  }

  /**
   * Retrieve task X name
   */
  public String getTaskName(int taskId) throws IOException {
    return client.processAsJson("/jdev/task" + taskId + "/name").getValue();
  }

  /**
   * Retrieve task X priority
   */
  public String getTaskPriority(int taskId) throws IOException {
    return client.processAsJson("/jdev/task" + taskId + "/priority").getValue();
  }

  /**
   * Retrieve task X stack
   */
  public String getTaskStack(int taskId) throws IOException {
    return client.processAsJson("/jdev/task" + taskId + "/stack").getValue();
  }

  /**
   * Retrieve number of task X switchings
   */
  public int getTaskContextSwitches(int taskId) throws IOException {
    return client.processAsJson("/jdev/task" + taskId + "/contextswitches").getValueAsInt();
  }

  /**
   * Retrieve task X wait time in ms
   */
  public int getTaskWaitings(int taskId) throws IOException {
    return client.processAsJson("/jdev/task" + taskId + "/waittimeout").getValueAsInt();
  }

  /**
   * Retrieve task X status
   */
  public String getTaskState(int taskId) throws IOException {
    return client.processAsJson("/jdev/task" + taskId + "/state").getValue();
  }

}
