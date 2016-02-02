package playerstate;

import exceptions.InvalidMoveException;

public class EqualToMaxBetState implements PlayerStateBehavior {

  @Override
  public PlayerState getState() {
    return PlayerState.EQUAL_TO_MAX_BET;
  }

  @Override
  public PlayerStateBehavior call() {
    return this;
  }

  @Override
  public PlayerStateBehavior raise() {
    return this;
  }

  @Override
  public PlayerStateBehavior allin() {
    return this;
  }

  @Override
  public PlayerStateBehavior check() {
    return this;
  }

  @Override
  public PlayerStateBehavior bet() {
    return this;
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
    throw new InvalidMoveException();
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
    return PlayerState.LESS_THAN_MAX_BET.getStateBehavior();
  }
}
