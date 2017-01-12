package com.github.thomasfischl.loxone.jclient;

public enum SpsState {
  NO_STATUS, BOOTING, PROGRAM_LOADED, STARTED, LINK_STARTED, RUNNING, CHANGE, ERROR, UPDATING;

  public static SpsState get(int val) {
    switch (val) {
    case 0:
      return SpsState.NO_STATUS;
    case 1:
      return SpsState.BOOTING;
    case 2:
      return SpsState.PROGRAM_LOADED;
    case 3:
      return SpsState.STARTED;
    case 4:
      return SpsState.LINK_STARTED;
    case 5:
      return SpsState.RUNNING;
    case 6:
      return SpsState.CHANGE;
    case 7:
      return SpsState.ERROR;
    case 8:
      return SpsState.UPDATING;
    default:
      throw new IllegalArgumentException("No state for value '" + val + "' defined!");
    }
  }
}
