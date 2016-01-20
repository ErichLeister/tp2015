package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Table {
	List<Player> players;
	Deck commonCards;
	public Table() {
		players = new ArrayList<Player>();
		commonCards = new Deck();
	}
	public static void combinations(int n, int c, Stack<Integer> combs)
	{
	        if(c == 0) {
	            System.out.println(combs);
	            return;
	        }
	        if(n == 0)
	            return;
	        combs.push(n);
	        combinations(n-1, c-1, combs);
	        combs.pop();
	        combinations(n-1, c, combs);
	}
	public static void main(String args[]) {
		combinations(5,3, new Stack<Integer>());
	}
}