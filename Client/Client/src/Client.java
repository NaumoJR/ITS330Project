import java.util.Calendar;

public class Client {
	private int ID;
	private int Port;
	private String Name;

	public Client() {
	}

	public Client(int id) {
		this.ID = id;
	}

	public Client(int id, int port) {
		this.ID = id;
		this.Port = port;
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public int getPort() {
		return this.Port;
	}

	public void setPort(int port) {
		this.Port = port;
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public void printDate() {
		Calendar ca = Calendar.getInstance();
		System.out.println(ca.getTime().toString());
	}
}
