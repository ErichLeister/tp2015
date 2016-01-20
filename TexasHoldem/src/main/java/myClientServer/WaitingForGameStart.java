package myClientServer;

public class WaitingForGameStart {
	public synchronized void waitForGameStart(){
		try{
			wait();
		}
		catch(InterruptedException ex){
		}
	}
	public synchronized void startGame(){
		notifyAll();
	}
}
