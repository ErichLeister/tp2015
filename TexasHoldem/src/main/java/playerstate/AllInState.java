package playerstate;

import exceptions.InvalidMoveException;

public class AllInState implements PlayerStateBehavior {
  
  @Override
  public PlayerState getState() {
    return PlayerState.ALL_IN;
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
    return this;
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
