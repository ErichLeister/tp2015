package model;

import exceptions.InvalidMoveException;
import exceptions.NoPlayersException;
import exceptions.NotEnoughCardsException;
import exceptions.NotPositiveAmountException;
import playerstate.AllInState;
import playerstate.BigBlindState;
import playerstate.EqualToMaxBetState;
import playerstate.FoldState;
import playerstate.InitState;
import playerstate.LessThanMaxBetState;
import playerstate.PlayerState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game {
 
  private List<Card> commonCards;
  private List<Player> players;
  private int pot;
  private int initialChipsPerPlayer;
  private Deck deck;
  private Player dealerButton;
  private int smallBlindAmount;
  private int bigBlindAmount;
  private Map<Player, Integer> playerAllInBet;
  private int currentIndex;
  
  public Game(List<Player> players, int initialChipsPerPlayer, int smallBlindAmount, int bigBlindAmount) {
    this.commonCards = new ArrayList<Card>();
    this.players = players;
    this.initialChipsPerPlayer = initialChipsPerPlayer;
    this.deck = new Deck();
    this.dealerButton = null;
    this.pot = 0;
    this.smallBlindAmount = smallBlindAmount;
    this.bigBlindAmount = bigBlindAmount;
    playerAllInBet = new HashMap<Player, Integer>();
    this.currentIndex = 0;
  }
  
  private void setDealerButton() {
    Random rand = new Random();
    this.dealerButton = players.get(rand.nextInt(players.size()));
  }
  
  private void moveDealerButton() {
    this.dealerButton = players.get(this.findIndexOfNextActivePlayer(this.getNextIndex(players.indexOf(dealerButton))));
  }
  
  private int findIndexOfDealerButton() {
    int index = 0;
    while ((index < players.size()) && (! (players.get(index).equals(dealerButton)))) {
      index++;
    }
    return index;
  }
  
  private boolean isFoldOrAllin(Player player) {
    return (player.getPlayerStateBehavior().getClass().equals(new FoldState().getClass())
        || player.getPlayerStateBehavior().getClass().equals(new AllInState().getClass()));
  }
  
  private int getNextIndex(int index) {
    return (index + 1) % players.size();
  }
  
  private void moveCurrentIndex() {
    this.currentIndex = (this.currentIndex + 1) % players.size();
  } 
  
  private void moveCurrentIndexToActivePlayer() {
    do {
      this.moveCurrentIndex();
    } while (this.isFoldOrAllin(players.get(this.currentIndex)));
  }
  
  private int findIndexOfSmallBlind() {
    return (findIndexOfDealerButton() + 1) % players.size();
  }
  
  private int findIndexOfBigBlind() {
    return (findIndexOfDealerButton() + 2) % players.size();
  }
  
  // Return index of a Player with a non fold and non all-in state
  private int findIndexOfNextActivePlayer(int index) {
    while (this.isFoldOrAllin(players.get(index))) {
      index = this.getNextIndex(index);
    }
    return index;
  }
  
  private int findIndexOfStartingPlayer() {
    int index = this.findIndexOfSmallBlind();
    
    while (this.isFoldOrAllin(players.get(index))) {
      index = this.getNextIndex(index);
    }
    return index;
  }
  
  private void addPlayerAllInChips(Player player, int chips) {
    this.playerAllInBet.put(player, chips);
  }
  
  public void prepareGame() {
    deck.fillDeckWithAllCards();
    deck.shuffleCards();
    
    this.setDealerButton();
    
    for (Player player : players) {
      player.setChips(initialChipsPerPlayer);  
      for (Player otherPlayer : players) {
        if (! player.equals(otherPlayer)) {
          player.addObserver(otherPlayer);
        }
      }
      player.setPlayerStateBehavior(PlayerState.INIT.getStateBehavior());
    }
  }
  
  private void takeSmallBlind() {
    int index = this.findIndexOfSmallBlind();
    Player player = players.get(index);

    if (player.getChips() <= this.smallBlindAmount) {
      this.addPlayerAllInChips(player, 2 * player.getChips());
      player.setPlayerStateBehavior(player.getPlayerStateBehavior().allin());
      player.notifyObservers("smallBlind");
      this.pot = player.getChips();
      player.setChips(0);
      
    } else {
      try {
        player.setPlayerStateBehavior(player.getPlayerStateBehavior().smallBlind());
      } catch (InvalidMoveException e) {
        e.printStackTrace();
      }
      player.notifyObservers("smallBlind");
      player.setChips(player.getChips() - smallBlindAmount);
      this.pot = this.smallBlindAmount;
      
    }
  }
  
  private void takeBigBlind() {
    int index = this.findIndexOfBigBlind();
    Player player = players.get(index);
    
    if (player.getChips() <= this.bigBlindAmount) {
      this.addPlayerAllInChips(player, 2 * player.getChips());
      if (player.getChips() > this.pot) {
        player.notifyObservers("rise");
      }
      this.pot = this.pot + player.getChips();
      player.setChips(0);
      player.setPlayerStateBehavior(player.getPlayerStateBehavior().allin());
    } else {
      try {
        player.setPlayerStateBehavior(player.getPlayerStateBehavior().bigBlind());
      } catch (InvalidMoveException e) {
        e.printStackTrace();
      }
      player.notifyObservers("bigBlind");
      player.withdrawChips(bigBlindAmount);
      this.pot = this.pot + bigBlindAmount;
    }
  }
  
  private void setForAllPlayersInitState() {
    for (Player player : players) {
      player.setPlayerStateBehavior(PlayerState.INIT.getStateBehavior());
    }
  }
  
  // Returns an index of a next player
  public int prepareRoundOfBetting(int roundNumber) {
    
    this.setForAllPlayersInitState();
    
    if (roundNumber == 0) {
      this.takeSmallBlind();
      this.takeBigBlind();
      
      this.commonCards = new ArrayList<Card>(); 
      
      return this.findIndexOfNextActivePlayer(this.findIndexOfBigBlind());
      
    } else if (roundNumber == 1) {
      try {
        commonCards.addAll(deck.pullCards(3));
      } catch (NotEnoughCardsException e) {
        //Shouldn't be thrown. Stack was recently filled with all available cards.
      } catch (NotPositiveAmountException e) {
        //Shouldn't be thrown. The method was called with an argument 2
      }
      
      return this.findIndexOfStartingPlayer();
      
    } else {
      try {
        commonCards.add(deck.pullCard());
      } catch (NotEnoughCardsException e) {
        e.printStackTrace();
      }
      return this.findIndexOfStartingPlayer();
    }
  }
  
  private boolean arePlayersReadyToNextRound() {
    boolean areReady = true;
    
    Iterator<Player> iterator = players.iterator();
    
    while (iterator.hasNext() && (! areReady)) {
      Player player = iterator.next();
      if (player.getPlayerStateBehavior().equals(new FoldState())
          || player.getPlayerStateBehavior().equals(new AllInState())
          || player.getPlayerStateBehavior().equals(new EqualToMaxBetState()))
        areReady = false;
    }
    return areReady;
  }
  
  private boolean isOnlyOnePlayerNonFold() {
    int activePlayers = 0;
    
    for(Player player : players) {
      if (! player.getPlayerStateBehavior().getClass().equals(new FoldState().getClass())) {
        System.out.println(player.getPlayerStateBehavior());
        activePlayers++;
        System.out.println(activePlayers);
      }
    }
    return (activePlayers == 1);
  }
  
  private boolean areFixedPlayerStates() {
    boolean outcome = true;
    
    for (Player player : players) {
      if (player.getPlayerStateBehavior().equals(new InitState())
          || player.getPlayerStateBehavior().equals(new LessThanMaxBetState())
          || player.getPlayerStateBehavior().equals(new BigBlindState())) {
        outcome = true;
        break;
      }
    }
    return outcome;
  }
  
  private void summaryOfRound() throws NoPlayersException {
    Dealer dealer = new Dealer(commonCards, players);
    
    Map<Integer, List<Player>> chartOfWinners = dealer.getChartOfWinners();
    
    int place = 1;
    List<Player> winners = chartOfWinners.get(place);
      for(Player player : winners) {
        player.addChips((int) Math.floor(pot / chartOfWinners.get(place).size()));
      }
      this.commonCards = new ArrayList<Card>();
      
      for(Player player : players) {
        player.setCards(new ArrayList<Card>());
        player.setChips(this.initialChipsPerPlayer);
        player.setPlayerStateBehavior(PlayerState.INIT.getStateBehavior());
      }
  }
  
  public void startGame() {
    this.prepareGame();
    int index;
    int roundNumber;
    
    while(true) {
      roundNumber = 0;
      while ((! this.isOnlyOnePlayerNonFold()) && (roundNumber < 4)) {
        
        index = this.prepareRoundOfBetting(roundNumber);
        while ((! this.isOnlyOnePlayerNonFold()) && (! areFixedPlayerStates())) {
         players.get(index).sendMessage("bet");          
        }
        roundNumber++;
      }
      
      this.moveDealerButton();
    }
  }
}
