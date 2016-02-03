package myClientServer;

public class MessageDecoratorSetPlayersCards extends MessageDecorator{
  
  String cardA;
  String cardB;
  
  
  public MessageDecoratorSetPlayersCards(String cardA, String cardB, MessageInterface message){
    super(message);
    this.cardA = cardA;
    this.cardB = cardB;
  }
  public void affectClient(){
    super.affectClient();
    situation.setCard(5, cardA);
    situation.setCard(6, cardB);
  }

}