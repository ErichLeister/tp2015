package model;

import java.util.Arrays;

import exceptions.WrongColorException;
import exceptions.WrongNameException;

public class Card {
	protected String name;
	protected String color;
	public static String availableNames[] = {"2","3","4","5","6","7","8","9","10","W","D","K","A",};
	public static String availableColors[] = {"kier", "pik", "karo", "trefl"};
	
	public Card(String name, String color) throws WrongColorException, WrongNameException {
		if (!Arrays.asList(availableColors).contains(color))
			throw new WrongColorException();
		if (!Arrays.asList(availableNames).contains(name))
			throw new WrongNameException();
		this.name = name;
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public String getColor() {
		return color;
	}
	public String toString() {
		return name + " "  + color;
	}
	public boolean equals(Card c) {
		return (this.getName().equals(c.getName()) && (this.getColor().equals(c.getColor())));
	}
}