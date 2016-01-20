package model;

import java.util.Comparator;

import exceptions.WrongNameException;

public class CardComparator implements Comparator<Card> {
	
	private int findNamesIndexInArray(String name) throws WrongNameException {
		int i=0;
		boolean isFound = false;
		while(Card.availableNames[i] != name) {
			i++;
			isFound = true;
		}
		i--;
		if(isFound)
			return i;
		else
			throw new WrongNameException();
			
	}
	@Override
	public int compare(Card arg0, Card arg1) {
		int cardValues[] = {2,3,4,5,6,7,8,9,10,11,12,13,14};

		int index0=-1, index1=-1, val0, val1;
		try {
			index0 = findNamesIndexInArray(arg0.getName());
			index1 = findNamesIndexInArray(arg1.getName());
		}
		catch(WrongNameException e) {
			e.getMessage();
			System.exit(-1);
		}
		val0 = cardValues[index0];
		val1 = cardValues[index1];
		
		if(val0 > val1)
			return 1;
		else if(val0 < val1)
			return -1;
		else
			return 0;
	}
	//is a successor of b
	public boolean isSuccessor(Card a, Card b) throws WrongNameException {
		return ((this.findNamesIndexInArray(a.getName()) - this.findNamesIndexInArray(b.getName())) == 1);
	}
	
}
