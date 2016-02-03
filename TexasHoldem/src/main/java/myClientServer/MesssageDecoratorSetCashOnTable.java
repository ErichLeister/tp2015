package myClientServer;

public class MesssageDecoratorSetCashOnTable extends MessageDecorator{
  
  int cash;
  
  public MesssageDecoratorSetCashOnTable(int cash, MessageInterface message){
    super(message);
    this.cash = cash;
  }
  public void affectClient(){
    super.affectClient();
    situation.setChipsOnTable(cash);
  }

}
