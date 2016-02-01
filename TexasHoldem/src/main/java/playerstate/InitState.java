package playerstate;

import exceptions.InvalidMoveException;

public class InitState implements PlayerStateBehavior {
  
  @Override
  public PlayerState getState() {
    return PlayerState.INIT;
  }
  
  @Override
  public PlayerStateBehavior call() throws InvalidMoveException {
    throw new InvalidMoveException();
  }
  
  @Override
  public PlayerStateBehavior raise() throws InvalidMoveException {
    throw new InvalidMoveException();
  }
  
  @Override
  public PlayerStateBehavior allin() {
    return PlayerState.ALL_IN.getStateBehavior();
  }
  
  @Override
  public PlayerStateBehavior check() {
    return PlayerState.EQUAL_TO_MAX_BET.getStateBehavior();
  }

  @Override
  public PlayerStateBehavior bet() {
    return PlayerState.EQUAL_TO_MAX_BET.getStateBehavior();
  }

  @Override
  public PlayerStateBehavior fold() {
    return PlayerState.FOLD.getStateBehavior();
  }

  @Override
  public PlayerStateBehavior somebodyRaise() {
    return PlayerState.LESS_THAN_MAX_BET.getStateBehavior();
  }

  @Override
  public PlayerStateBehavior smallBlind() throws InvalidMoveException {
    return PlayerState.EQUAL_TO_MAX_BET.getStateBehavior();
  }

  @Override
  public PlayerStateBehavior bigBlind() throws InvalidMoveException {
    throw new InvalidMoveException();
  }

  @Override
  public PlayerStateBehavior somebodySmallBlind() {
    return PlayerState.LESS_THAN_MAX_BET.getStateBehavior();
  }

  @Override
  public PlayerStateBehavior somebodyBigBlind() {
    return this;
  }
}
