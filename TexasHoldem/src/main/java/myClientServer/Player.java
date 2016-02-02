package myClientServer;

public class Player {
	RealUser user;
	public String a = "example user message";
	public Player(RealUser user){
		this.user = user;
	}
	public void sendMessage(MessageInterface message){
		user.giveMessage(message);
	}
	public Answer getAnswer(){
		return user.getAnswer(0);
	}
	public int getBet(){
		Answer answer = user.getAnswer(0);
		int bet = -1;
		bet = answer.getMessageInt();
		return bet;
	}
	public int getPlayDecision(){
		Answer answer = user.getAnswer(0);
		int intOption = -1;
		String stringOption = answer.getMessageString();
		stringOption.trim();
		stringOption.toLowerCase();

		if(stringOption.equals("call"))
		{
			intOption = 1;
		}
		else if(stringOption.equals("raise"))
		{
			intOption = 2;
		}
		else if(stringOption.equals("allin"))
		{
			intOption = 3;
		}
		else if(stringOption.equals("all in"))
		{
			intOption = 3;
		}
		else if(stringOption.equals("all-in"))
		{
			intOption = 3;
		}
		else if(stringOption.equals("check"))
		{
			intOption = 4;
		}
		else if(stringOption.equals("bet"))
		{
			intOption = 5;
		}
		else if(stringOption.equals("fold"))
		{
			intOption = 6;
		}
		return intOption;
	}
}
