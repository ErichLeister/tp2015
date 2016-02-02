package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import myClientServer.RealUser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import playerstate.AllInState;
import playerstate.BigBlindState;
import playerstate.EqualToMaxBetState;
import playerstate.InitState;
import playerstate.LessThanMaxBetState;
import playerstate.PlayerState;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameTest {
  Game game;
  
  Player p1;
  Player p2;
  Player p3;
  
  int initialChipsPerPlayer;
  int smallBlindAmount;
  int bigBlindAmount;
  List<Player> players;
  
  @Before
  public void setUp() {
    p1 = new Player("p1", Mockito.mock(RealUser.class));
    p2 = new Player("p2", Mockito.mock(RealUser.class));
    p3 = new Player("p3", Mockito.mock(RealUser.class));
    
    initialChipsPerPlayer = 100;
    smallBlindAmount = 5;
    bigBlindAmount = 10;
    
    players = new ArrayList<Player>();
    players.add(p1);
    players.add(p2);
    players.add(p3);
    
    game = new Game(players, initialChipsPerPlayer, smallBlindAmount, bigBlindAmount);
    game.prepareGame();
  }
  
  @After
  public void tearDown() {
    p1 = null;
    p2 = null;
    p3 = null;
    
    initialChipsPerPlayer = 0;
    smallBlindAmount = 0;
    bigBlindAmount = 0;
    
    players = null;
    game = null;
  }

  @Test
  public void testSetDealerButton() throws NoSuchMethodException, SecurityException,
  IllegalAccessException, IllegalArgumentException, InvocationTargetException,
  NoSuchFieldException {
    
    Method method = (Game.class).getDeclaredMethod("setDealerButton");
    method.setAccessible(true);
    method.invoke(game);
    
    Field field = (Game.class).getDeclaredField("dealerButton");
    field.setAccessible(true);
 
    if (! players.contains(field.get(game))) {
      fail();
    }
  }
  
  @Test
  public void testFindIndexOfDealerButton() throws NoSuchFieldException, SecurityException,
  IllegalArgumentException, IllegalAccessException, NoSuchMethodException,
  InvocationTargetException {
    
    Method method = (Game.class).getDeclaredMethod("findIndexOfDealerButton");
    method.setAccessible(true);
    
    Field field = (Game.class).getDeclaredField("dealerButton");
    field.setAccessible(true);
    field.set(game, p2);
    
    assertEquals(method.invoke(game), 1);
  }
  
  @Test
  public void testFindIndexOfSmallBlind() throws NoSuchMethodException, SecurityException, 
  NoSuchFieldException, IllegalArgumentException, IllegalAccessException,
  InvocationTargetException {
    
    Method method = (Game.class).getDeclaredMethod("findIndexOfSmallBlind");
    method.setAccessible(true);
    
    Field field = (Game.class).getDeclaredField("dealerButton");
    field.setAccessible(true);
    field.set(game, p3);
    
    assertEquals(method.invoke(game), 0);
  }
  
  @Test
  public void testIsFoldOrAllin() throws NoSuchMethodException, SecurityException, 
  IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    
    Method method = (Game.class).getDeclaredMethod("isFoldOrAllin", Player.class);
    method.setAccessible(true);
    
    p1.setPlayerStateBehavior(PlayerState.ALL_IN.getStateBehavior());
    p2.setPlayerStateBehavior(PlayerState.FOLD.getStateBehavior());
    p3.setPlayerStateBehavior(PlayerState.EQUAL_TO_MAX_BET.getStateBehavior());
    
    if (! (method.invoke(game, p1).equals(true) && method.invoke(game, p2).equals(true))) {
      fail();
    }
    
    if ((boolean)method.invoke(game, p3)) {
      fail();
    }
  }
  
  @Test
  public void testFindIndexOfStartingPlayer() throws NoSuchMethodException, SecurityException,
  NoSuchFieldException, IllegalArgumentException, IllegalAccessException,
  InvocationTargetException {
    
    Method method = (Game.class).getDeclaredMethod("findIndexOfStartingPlayer");
    method.setAccessible(true);
    
    Field field = (Game.class).getDeclaredField("dealerButton");
    field.setAccessible(true);
    field.set(game, p1);
    
    p2.setPlayerStateBehavior(PlayerState.FOLD.getStateBehavior());
    p3.setPlayerStateBehavior(PlayerState.FOLD.getStateBehavior());
    
    if (! method.invoke(game).equals(0)) {
      fail();
    }
  }
  
  @Test
  public void testFindIndexOfNextActivePlayer() throws NoSuchMethodException, SecurityException, 
  IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    
    Method method = (Game.class).getDeclaredMethod("findIndexOfNextActivePlayer", int.class);
    method.setAccessible(true);
    
    p1.setPlayerStateBehavior(PlayerState.ALL_IN.getStateBehavior());
    p2.setPlayerStateBehavior(PlayerState.FOLD.getStateBehavior());
    
    if (! method.invoke(game, 0).equals(2)) {
      fail();
    }
  }
  
  @Test
  public void testTakeSmallBlind_withoutAllIn() throws NoSuchMethodException, SecurityException, 
  NoSuchFieldException, IllegalArgumentException, IllegalAccessException,
  InvocationTargetException {
    
    Method method = (Game.class).getDeclaredMethod("takeSmallBlind");
    method.setAccessible(true);
    
    Field field = (Game.class).getDeclaredField("dealerButton");
    field.setAccessible(true);
    
    field.set(game, p2);
    
    method.invoke(game);
    
    if (! ((players.get(2).getChips() == 95) 
        && (players.get(2).getPlayerStateBehavior().getClass().equals(new EqualToMaxBetState().getClass())))) {
      fail();
    }

    if (! ((players.get(0).getChips() == 100) 
        && (players.get(0).getPlayerStateBehavior().getClass().equals(new LessThanMaxBetState().getClass())))) {
      fail();
    }

    if (! ((players.get(1).getChips() == 100) 
        && (players.get(1).getPlayerStateBehavior().getClass().equals(new LessThanMaxBetState().getClass())))) {
      fail();
    }
  }
  
  @Test
  public void testTakeSmallBlind_witAllIn_LessThanPlayerChips() throws NoSuchMethodException, SecurityException, 
  NoSuchFieldException, IllegalArgumentException, IllegalAccessException,
  InvocationTargetException {
    
    Method method = (Game.class).getDeclaredMethod("takeSmallBlind");
    method.setAccessible(true);
    
    Field field = (Game.class).getDeclaredField("dealerButton");
    field.setAccessible(true);
    
    Field f1 = (Game.class).getDeclaredField("playerAllInBet");
    f1.setAccessible(true);
    
    p3.setChips(3);
    field.set(game, p2);
    
    method.invoke(game);
    
    @SuppressWarnings("unchecked")
    Map<Player, Integer> allIns = (Map<Player, Integer>) f1.get(game);

    if (! ((players.get(2).getChips() == 0) 
        && (players.get(2).getPlayerStateBehavior().getClass().equals(new AllInState().getClass()))))
      fail();

    if (! (allIns.get(p3).equals(6)))
      fail();

    if (! ((players.get(0).getChips() == 100) 
        && (players.get(0).getPlayerStateBehavior().getClass().equals(new LessThanMaxBetState().getClass()))))
      fail();

    if (! ((players.get(1).getChips() == 100) 
        && (players.get(1).getPlayerStateBehavior().getClass().equals(new LessThanMaxBetState().getClass()))))
        fail();
  }
  
  @Test
  public void testTakeBigBlind_withoutAllIn() throws NoSuchMethodException, SecurityException,
  NoSuchFieldException, IllegalArgumentException, IllegalAccessException,
  InvocationTargetException {
    
    p1.setChips(95);
    p2.setChips(100);
    p3.setChips(100);

    p1.setPlayerStateBehavior(PlayerState.EQUAL_TO_MAX_BET.getStateBehavior());
    p2.setPlayerStateBehavior(PlayerState.LESS_THAN_MAX_BET.getStateBehavior());
    p3.setPlayerStateBehavior(PlayerState.LESS_THAN_MAX_BET.getStateBehavior());

    Method method = (Game.class).getDeclaredMethod("takeBigBlind");
    method.setAccessible(true);

    Field field = (Game.class).getDeclaredField("dealerButton");
    field.setAccessible(true);

    field.set(game, p3);

    method.invoke(game);

    if (! (p1.getPlayerStateBehavior().getClass().equals(new LessThanMaxBetState().getClass())
        && (p1.getChips() == 95)))
      fail();

    if (! (p2.getPlayerStateBehavior().getClass().equals(new BigBlindState().getClass())
        && (p2.getChips() == 90)))
      fail();

    if (! (p3.getPlayerStateBehavior().getClass().equals(new LessThanMaxBetState().getClass())
        && (p3.getChips() == 100)))
       fail();
  }
  
  @Test
  public void testPrepareRoundOfBetting_firstRound() throws NoSuchMethodException, 
  SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
  NoSuchFieldException {
    
    Method method = (Game.class).getDeclaredMethod("prepareRoundOfBetting", int.class);
    method.setAccessible(true);
    
    Field field = (Game.class).getDeclaredField("dealerButton");
    field.setAccessible(true);
    
    field.set(game, p2);
    
    int index = (int) method.invoke(game, 0);
    
    if (index != 0) {
      fail();
    }

    if (! (players.get(2).getPlayerStateBehavior().getClass().equals(new LessThanMaxBetState().getClass())
        && (players.get(2).getChips() == 95))) {
      fail();
    }


    if (! (players.get(0).getPlayerStateBehavior().getClass().equals(new BigBlindState().getClass()) 
        && (players.get(0).getChips() == 90))) {
      fail();
    }

    if (! (players.get(1).getPlayerStateBehavior().getClass().equals(new LessThanMaxBetState().getClass())
        && (players.get(1).getChips() == 100))) {
      fail();    
    }
  }
  
  @Test
  public void testPrepareRoundOfBetting_SecondRound() throws NoSuchMethodException, 
  SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
  NoSuchFieldException {
    
    Method method = (Game.class).getDeclaredMethod("prepareRoundOfBetting", int.class);
    method.setAccessible(true);
    
    Field field = (Game.class).getDeclaredField("dealerButton");
    field.setAccessible(true);
    
    field.set(game, p2);
    
    int index = (int) method.invoke(game, 1);
    
    if (index != 2) {
      fail();
    }
    
    if (! (players.get(2).getPlayerStateBehavior().getClass().equals(new InitState().getClass())
        && (players.get(2).getChips() == 100))) {
      fail();
    }

    if (! (players.get(0).getPlayerStateBehavior().getClass().equals(new InitState().getClass())
        && (players.get(0).getChips() == 100))) {
      fail();
    }

    if (! (players.get(1).getPlayerStateBehavior().getClass().equals(new InitState().getClass())
        && (players.get(1).getChips() == 100))) {
      fail();    
    }
  }
  
  @Test
  public void testIsActiveOnlyOnePlayer_shouldReturnFalse() throws NoSuchMethodException,
  IllegalAccessException, IllegalArgumentException, InvocationTargetException {

    Method method = (Game.class).getDeclaredMethod("isOnlyOnePlayerNonFold");
    method.setAccessible(true);

    p1.setPlayerStateBehavior(PlayerState.LESS_THAN_MAX_BET.getStateBehavior());
    p2.setPlayerStateBehavior(PlayerState.EQUAL_TO_MAX_BET.getStateBehavior());
    p3.setPlayerStateBehavior(PlayerState.FOLD.getStateBehavior());

    if ((boolean)method.invoke(game)) {
      fail();
    }  
  }
  
  @Test
  public void testIsActiveOnlyOnePlayer_shouldReturnTrue() throws NoSuchMethodException,
  IllegalAccessException, IllegalArgumentException, InvocationTargetException {

    Method method = (Game.class).getDeclaredMethod("isOnlyOnePlayerNonFold");
    method.setAccessible(true);

    p1.setPlayerStateBehavior(PlayerState.FOLD.getStateBehavior());
    p2.setPlayerStateBehavior(PlayerState.ALL_IN.getStateBehavior());
    p3.setPlayerStateBehavior(PlayerState.FOLD.getStateBehavior());

    System.out.println((boolean)method.invoke(game));
    
    if (! (boolean)method.invoke(game)) {
      fail();
    }  
  }
  
}
