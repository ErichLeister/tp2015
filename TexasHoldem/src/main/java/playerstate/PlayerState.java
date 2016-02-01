package playerstate;

public enum PlayerState {
  FOLD {
    public PlayerStateBehavior getStateBehavior() {
      return new FoldState();
    }
  },
  INIT {
    public PlayerStateBehavior getStateBehavior() {
      return new InitState();
    }

  },
  EQUAL_TO_MAX_BET {
    public PlayerStateBehavior getStateBehavior() {
      return new EqualToMaxBetState();
    }
  },
  LESS_THAN_MAX_BET {
    public PlayerStateBehavior getStateBehavior() {
      return new LessThanMaxBetState();
    }
  },
  ALL_IN {
    public PlayerStateBehavior getStateBehavior() {
      return new AllInState();
    }
  },
  BIG_BLIND {
    public PlayerStateBehavior getStateBehavior() {
      return new BigBlindState();
    }
  };

  public PlayerStateBehavior getStateBehavior() {
    return null;
  }

}
