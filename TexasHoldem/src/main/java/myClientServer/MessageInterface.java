package myClientServer;

import java.io.Serializable;

public interface MessageInterface extends Serializable {
	public void affectClient();
	public void setClient(Client client);
	public String getMessage();
	public Client getClient();
	
}
