package pokerhand;

import model.Player;

public abstract class PokerHand {
	private Player player;
	public abstract int getRank();
	public abstract int subCompare(PokerHand o1, PokerHand o2);
	
	public PokerHand(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
}
