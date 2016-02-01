package model;

public interface PlayerObservable {
  public void addObserver(Player observer);
  public void deleteObserver(Player observer);
  public void notifyObservers(String betType);
}
