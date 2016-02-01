package playerstate;

import exceptions.InvalidMoveException;

public interface PlayerStateBehavior {
  public PlayerState getState();
  public PlayerStateBehavior call() throws InvalidMoveException;
  public PlayerStateBehavior raise() throws InvalidMoveException;
  public PlayerStateBehavior allin();
  public PlayerStateBehavior check() throws InvalidMoveException;
  public PlayerStateBehavior bet() throws InvalidMoveException;
  public PlayerStateBehavior fold();
  public PlayerStateBehavior smallBlind() throws InvalidMoveException;
  public PlayerStateBehavior bigBlind() throws InvalidMoveException;
  
  public PlayerStateBehavior somebodySmallBlind();
  public PlayerStateBehavior somebodyBigBlind();
  public PlayerStateBehavior somebodyRaise();
}