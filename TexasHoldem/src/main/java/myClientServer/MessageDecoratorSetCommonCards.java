package myClientServer;

public class MessageDecoratorSetCommonCards extends MessageDecorator{
  
  String cardA;
  String cardB;
  String cardC;
  String cardD;
  String cardE;
  
  
  public MessageDecoratorSetCommonCards(String cardA, String cardB, String cardC,
      String cardD, String cardE, MessageInterface message){
    super(message);
    this.cardA = cardA;
    this.cardB = cardB;
    this.cardC = cardC;
    this.cardD = cardD;
    this.cardE = cardE;
  }
  public void affectClient(){
    super.affectClient();
    situation.setCard(0, cardA);
    situation.setCard(1, cardB);
    situation.setCard(2, cardC);
    situation.setCard(3, cardD);
    situation.setCard(4, cardE);
  }

}
