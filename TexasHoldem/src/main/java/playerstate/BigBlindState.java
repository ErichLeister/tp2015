package playerstate;

import exceptions.InvalidMoveException;

public class BigBlindState implements PlayerStateBehavior {

  @Override
  public PlayerState getState() {
    return PlayerState.BIG_BLIND;
  }

  @Override
  public PlayerStateBehavior call() throws InvalidMoveException {
    throw new InvalidMoveException();
  }

  @Override
  public PlayerStateBehavior raise() throws InvalidMoveException {
    return PlayerState.EQUAL_TO_MAX_BET.getStateBehavior();
  }

  @Override
  public PlayerStateBehavior allin() {
    return PlayerState.ALL_IN.getStateBehavior();
  }

  @Override
  public PlayerStateBehavior check() throws InvalidMoveException {
    return PlayerState.EQUAL_TO_MAX_BET.getStateBehavior();
  }

  @Override
  public PlayerStateBehavior bet() throws InvalidMoveException {
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
    return this;
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
