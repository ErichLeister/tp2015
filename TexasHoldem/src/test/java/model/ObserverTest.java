package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import exceptions.InvalidMoveException;
import myClientServer.RealUser;
import playerstate.EqualToMaxBetState;
import playerstate.LessThanMaxBetState;
import playerstate.PlayerState;

public class ObserverTest {
  Player p1;
  Player p2;

  @Before
  public void setUp() {
    p1 = new Player("p1", Mockito.mock(RealUser.class));
    p2 = new Player("p1", Mockito.mock(RealUser.class));
    
    p1.addObserver(p2);
    p2.addObserver(p1);
  }
  
  @Test
  public void testInit_somebodySmallBlind() throws InvalidMoveException {
    p1.setPlayerStateBehavior(PlayerState.INIT.getStateBehavior());
    p2.setPlayerStateBehavior(PlayerState.INIT.getStateBehavior());
    
    p1.setPlayerStateBehavior(p1.getPlayerStateBehavior().smallBlind());
    p1.notifyObservers("smallBlind");
    
    System.out.println(p1.getPlayerStateBehavior());
    System.out.println(p2.getPlayerStateBehavior());
    
    if (! (p1.getPlayerStateBehavior().getClass().equals(new EqualToMaxBetState().getClass())
        && p2.getPlayerStateBehavior().getClass().equals(new LessThanMaxBetState().getClass())))
      fail();
  }

}
