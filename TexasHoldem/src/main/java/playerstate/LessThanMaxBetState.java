package playerstate;

import exceptions.InvalidMoveException;

public class LessThanMaxBetState implements PlayerStateBehavior {
  @Override
  public PlayerState getState() {
    return PlayerState.LESS_THAN_MAX_BET;
  }
  
  @Override
  public PlayerStateBehavior call() {
    return PlayerState.EQUAL_TO_MAX_BET.getStateBehavior();
  }
  
  @Override
  public PlayerStateBehavior raise() {
    return PlayerState.EQUAL_TO_MAX_BET.getStateBehavior();
  }
  
  @Override
  public PlayerStateBehavior allin() {
    return PlayerState.ALL_IN.getStateBehavior();
  }
  
  @Override
  public PlayerStateBehavior check() throws InvalidMoveException {
    throw new InvalidMoveException();
  }
  
  @Override
  public PlayerStateBehavior bet() throws InvalidMoveException{
    throw new InvalidMoveException();
  }

  @Override
  public PlayerStateBehavior fold() {
    return PlayerState.FOLD.getStateBehavior();
  }

  @Override
  public PlayerStateBehavior somebodyRaise() {
    return this;
  }

  @Override
  public PlayerStateBehavior smallBlind() throws InvalidMoveException {
    return this;
  }

  @Override
  public PlayerStateBehavior bigBlind() throws InvalidMoveException {
    throw new InvalidMoveException();
  }

  @Override
  public PlayerStateBehavior somebodySmallBlind() {
    return this;
  }

  @Override
  public PlayerStateBehavior somebodyBigBlind() {
    return this;
  }
}
