package model;

import myClientServer.Message;

public interface PlayerObserver {
  public void updateStateBehavior(String betType);
  public void sendUpdate(Message msg);
}
