
public class ServerTest {
	
	
	public void TestThreadedServerAdvNet(){
		int port =8088;
		ThreadedPrintServerAdvNet tpsan = new ThreadedPrintServerAdvNet(8088, 5000);
		tpsan.listen();// ask a server to listen for connections from clients
		System.out.println("server is terminated");	
	}
	
	public static void main (String args[]){
		
		ServerTest st = new ServerTest();
		st.TestThreadedServerAdvNet();
		
	}

}