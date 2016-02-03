package model;

import exceptions.InvalidMoveException;
import exceptions.NoPlayersException;
import exceptions.NotEnoughCardsException;
import exceptions.NotPositiveAmountException;
import myClientServer.ClientPlayer;
import myClientServer.Message;
import myClientServer.MessageDecoratorAddPlayer;
import myClientServer.MessageDecoratorAskPlayDecision;
import myClientServer.MessageDecoratorAskPlayerBet;
import myClientServer.MessageDecoratorSetBet;
import myClientServer.MessageDecoratorSetCash;
import myClientServer.MessageDecoratorSetCommonCards;
import myClientServer.MessageInterface;
import playerstate.AllInState;
import playerstate.BigBlindState;
import playerstate.EqualToMaxBetState;
import playerstate.FoldState;
import playerstate.PlayerState;
import playerstate.PlayerStateBehavior;

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
  private int currentPot;
  private int initialChipsPerPlayer;
  private Deck deck;
  private Player dealerButton;
  private int smallBlindAmount;
  private int bigBlindAmount;
  private Map<Player, Integer> playerAllInBet;
  private int currentIndex;
  private int []playersBet;
  private int potInLastRounds;
  private List<Player> currentAllInPlayers;
  
  public Game(List<Player> players, int initialChipsPerPlayer, int smallBlindAmount, int bigBlindAmount) {
    this.commonCards = new ArrayList<Card>();
    this.initialChipsPerPlayer = initialChipsPerPlayer;
    this.deck = new Deck();
    this.dealerButton = null;
    this.pot = 0;
    this.currentPot = 0;
    this.smallBlindAmount = smallBlindAmount;
    this.bigBlindAmount = bigBlindAmount;
    this.playerAllInBet = new HashMap<Player, Integer>();
    this.currentIndex = 0;
    this.players = players;
    this.playersBet = new int[players.size()];
    this.potInLastRounds = 0;
    this.currentAllInPlayers = new ArrayList<Player>();
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
    System.out.println("prepare game");
    for (Player player : players) {
      player.setChips(initialChipsPerPlayer);  
      for (Player otherPlayer : players) {
        System.out.println("player: " + player + ",otherPlayer: " + otherPlayer);
        if (! player.equals(otherPlayer)) {
          player.addObserver(otherPlayer);
        }
      }
      player.setPlayerStateBehavior(PlayerState.INIT.getStateBehavior());
    }
    
    // clearing player's bet array with zeros
    for(int i = 0 ; i < playersBet.length ; i++) {
      playersBet[i] = 0;
    }
  }
  
  private void takeSmallBlind() {
    int index = this.findIndexOfSmallBlind();
    Player player = players.get(index);
    
    for (Player player1 : players) {
      System.out.println(player1.getPlayerStateBehavior());
    }
    
    if (player.getChips() <= this.smallBlindAmount) {
      this.addPlayerAllInChips(player, 2 * player.getChips());
      player.setPlayerStateBehavior(player.getPlayerStateBehavior().allin());
      player.notifyObservers("smallBlind");
      this.currentPot = player.getChips();
      player.setChips(0);
      
    } else {
      try {
        player.setPlayerStateBehavior(player.getPlayerStateBehavior().smallBlind());
      } catch (InvalidMoveException e) {
        System.out.println("blaad");
        e.printStackTrace();
      }
      player.notifyObservers("smallBlind");
      player.withdrawChips(smallBlindAmount);
      this.currentPot = this.smallBlindAmount;
      
      for (Player player1 : players) {
        System.out.println(player1.getPlayerStateBehavior());
      }
      
    }
  }
  
  private void takeBigBlind() {
    int index = this.findIndexOfBigBlind();
    Player player = players.get(index);
    System.out.println("player chips: " + player.getChips() + ", bigblindamount: " + this.bigBlindAmount);
    if (player.getChips() <= this.bigBlindAmount) {
      this.addPlayerAllInChips(player, 2 * player.getChips());
      if (player.getChips() > this.pot) {
        player.notifyObservers("rise");
      }
      this.currentPot = this.currentPot + player.getChips();
      
      MessageInterface msg = new MessageDecoratorSetBet(this.currentPot, player.getBet(), new Message());
      for (Player p : players) {
        p.sendMessage(msg);
      }
      
      player.setChips(0);
      
      MessageInterface msg1 = new MessageDecoratorSetCash(this.currentPot, this.currentPot, new Message());
      for (Player p : players) {
        p.sendMessage(msg1);
      }
      
      player.setPlayerStateBehavior(player.getPlayerStateBehavior().allin());
    } else {
      try {
        System.out.println(player.getPlayerStateBehavior());
        player.setPlayerStateBehavior(player.getPlayerStateBehavior().bigBlind());
      } catch (InvalidMoveException e) {
        e.printStackTrace();
      }
      //System.out.println(player.observers);
      player.notifyObservers("bigBlind");
      player.withdrawChips(bigBlindAmount);
      this.currentPot = this.currentPot + bigBlindAmount;
    }
  }
  
  private void setForAllPlayersInitState() {
    for (Player player : players) {
      player.setPlayerStateBehavior(PlayerState.INIT.getStateBehavior());
    }
  }
  
  // groups all-ins, clear playersBet, currentPot, currentAllInPlayer etc.
  private void summaryOfBettingRound() {
    for (Player allInPlayer : this.currentAllInPlayers) {
      int allInPlayerBet = playersBet[players.indexOf(allInPlayer)];
      int sumBet = 0;
      for (Player player : players) {
        if (! allInPlayer.equals(player)) {
          int playerBet = playersBet[players.indexOf(player)];
          sumBet = (allInPlayerBet < playerBet ? allInPlayerBet : playerBet);
        }
      }
      this.playerAllInBet.put(allInPlayer, sumBet);
    }
    for (int i = 0 ; i < this.playersBet.length ; i++) {
      this.potInLastRounds +=playersBet[i];
      playersBet[i] = 0;
    }
    this.currentPot = 0;
    this.currentAllInPlayers = new ArrayList<Player>();
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
      
//      MessageInterface msg;
//      msg = new MessageDecoratorSetCommonCards(commonCards.get(0), commonCards.get(1), commonCards.get(2), null, null, new Message());
      
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
    
    if (this.isOnlyOnePlayerNonFold())
      return true;
    
    Iterator<Player> iterator = players.iterator();
    
    while (iterator.hasNext() && (! areReady)) {
      Player player = iterator.next();
      if (player.getPlayerStateBehavior().getClass().equals(new FoldState().getClass())
          || player.getPlayerStateBehavior().getClass().equals(new AllInState().getClass())
          || player.getPlayerStateBehavior().getClass().equals(new EqualToMaxBetState().getClass())
          || player.getPlayerStateBehavior().getClass().equals(new BigBlindState().getClass()))
        areReady = false;
    }
    return areReady;
  }
  
  private boolean isOnlyOnePlayerNonFold() {
    int activePlayers = 0;
    
    for(Player player : players) {
      if (! player.getPlayerStateBehavior().getClass().equals(new FoldState().getClass())) {
        activePlayers++;
      }
    }
    return (activePlayers == 1);
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
  
  private boolean raiseMove(Player player) {
    boolean isValidDecision = false;
    
    PlayerStateBehavior previousStateBehavior = player.getPlayerStateBehavior();
    
    try {
      player.setPlayerStateBehavior(previousStateBehavior.raise());
      
      MessageInterface betRequest = new MessageDecoratorAskPlayerBet(new Message());
      player.sendMessage(betRequest);
      
      int playerBet = this.currentPot + player.getBet();

      if (playerBet >= player.getChips()) {
        player.setPlayerStateBehavior(previousStateBehavior);
        isValidDecision = false;
      } else {
        player.withdrawChips(player.getBet());
        this.currentPot = playerBet;
        this.playersBet[players.indexOf(player)] = playerBet; 
        
        isValidDecision = true;
        
        player.notifyObservers("rise");
      }
    } catch (InvalidMoveException e) {
      player.setPlayerStateBehavior(previousStateBehavior);
      isValidDecision = false;
    }
    return isValidDecision;
  }
  
  private boolean callMove(Player player) {
    boolean isValidDecision = false;
    
    PlayerStateBehavior previousStateBehavior = player.getPlayerStateBehavior();
    
    try {
      player.setPlayerStateBehavior(previousStateBehavior.call());
      
      int playerBet = this.currentPot;

      if (playerBet >= player.getChips()) {
        player.setPlayerStateBehavior(previousStateBehavior);
        isValidDecision = false;
      } else {
        player.withdrawChips(playerBet);
        this.currentPot += playerBet;
        this.playersBet[players.indexOf(player)] = playerBet; 
        
        isValidDecision = true;
      }
    } catch (InvalidMoveException e) {
      player.setPlayerStateBehavior(previousStateBehavior);
      isValidDecision = false;
    }
    return isValidDecision;
  }
  
  private void foldMove(Player player) { 
    player.setPlayerStateBehavior(player.getPlayerStateBehavior().fold());
    this.potInLastRounds += this.playersBet[players.indexOf(player)];
    this.playersBet[players.indexOf(player)] = 0;
  }
  
  private boolean checkMove(Player player) {
    boolean isValidDecision = false;

    PlayerStateBehavior previousStateBehavior = player.getPlayerStateBehavior();

    try {
      player.setPlayerStateBehavior(previousStateBehavior.check());
      isValidDecision = true;
      
    } catch (InvalidMoveException e) {
      player.setPlayerStateBehavior(previousStateBehavior);
      isValidDecision = false;
    }
    return isValidDecision;
  }
  
  private void allInMove(Player player) {
    player.setPlayerStateBehavior(player.getPlayerStateBehavior().allin());

    int playerBet = player.getChips();

    player.setChips(0);

    this.playersBet[players.indexOf(player)] = playerBet; 

    if (playerBet > this.currentPot) {
      this.currentPot = playerBet;
      player.notifyObservers("rise");
    }
    this.currentAllInPlayers.add(player);
  }

  private void playerMove(Player player) {
    boolean isValidDecision = false;
    while (! isValidDecision) {
      MessageInterface moveRequest = new MessageDecoratorAskPlayDecision(new Message());
      player.sendMessage(moveRequest);
      String decision = player.getPlayDecision();

      switch(decision) {
      case "rise":
        isValidDecision = raiseMove(player);
        break;
      case "call":
        isValidDecision = callMove(player);
        break;
      case "fold":
        foldMove(player);
        isValidDecision = true;
        break;
      case "check":
        isValidDecision = checkMove(player);
        break;
      case "allin":
        allInMove(player);
        isValidDecision = true;
        break;
        default:
          isValidDecision = false;
      }
    }
  }

  public void startGame() {
    this.prepareGame();

    int roundNumber = 0;

    MessageInterface message = new Message();

    for (Player player : players) {
      ClientPlayer clientPlayer = new ClientPlayer(player.getName(),player.getChips());
      message = new MessageDecoratorAddPlayer(clientPlayer, message);

      while (true) {
        roundNumber = 0;
        while ((! this.isOnlyOnePlayerNonFold()) && (roundNumber < 4)) {

          this.currentIndex = this.prepareRoundOfBetting(roundNumber);
          
          // ewentualnie trzeba zmienic waruenk petli na true. Ponizszy watunek moze wymagac poprawy
          while ((! this.arePlayersReadyToNextRound()) && (! this.isOnlyOnePlayerNonFold())) {
            System.out.println("w licytacji");
            this.playerMove(players.get(this.currentIndex));

            this.moveCurrentIndexToActivePlayer();
          }
          //this.summaryOfBettingRound();
          //roundNumber++;
        }
        try {
          this.summaryOfRound();
        } catch (NoPlayersException e) {
          e.printStackTrace();
        }
        this.moveDealerButton();
      }
    }
  }
}
