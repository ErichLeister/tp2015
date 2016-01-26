package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import exceptions.NotSameListsLengthException;
import exceptions.WrongNameException;

public class CardComparator implements Comparator<Card> {
	
	private int findNamesIndexInArray(String name) throws WrongNameException {
		int i=0;
		boolean isFound = false;
		
		for(i=0 ; i<Card.availableNames.length ; i++) {
			if(name.equals(Card.availableNames[i])) {
				isFound = true;
				break;
			}
		}
		if(isFound)
			return i;
		else
			throw new WrongNameException();
			
	}
	
	@Override
	public int compare(Card arg0, Card arg1) {
		int index0=-1, index1=-1;
		try {
			index0 = findNamesIndexInArray(arg0.getName());
			index1 = findNamesIndexInArray(arg1.getName());
		}
		catch(WrongNameException e) {
			e.getMessage();
			System.exit(-1);
		}
		if(index0 > index1)
			return 1;
		else if(index0 < index1)
			return -1;
		else
			return 0;
	}
	
	//is a successor of b
	public boolean isSuccessor(Card a, Card b) {
		boolean outcome = false;
		try {
			outcome = ((this.findNamesIndexInArray(a.getName()) - this.findNamesIndexInArray(b.getName())) == 1);
		} catch (WrongNameException e) {
			e.printStackTrace();
		}
		return outcome;
	}
	
	public int compareCardLists(List<Card> l1, List<Card> l2, Comparator<Card> cc) 
			throws NotSameListsLengthException {
		
		if(l1.size() != l2.size())
			throw new NotSameListsLengthException();
		
		Collections.sort(l1, cc);
		Collections.sort(l2, cc);
		int outcome = 0;
		int i=l1.size()-1;
		
		do {
			outcome = cc.compare(l1.get(i), l2.get(i));
			i--;
		}
		while((outcome == 0) && (i > 0));
		
		return outcome;
	}
}
