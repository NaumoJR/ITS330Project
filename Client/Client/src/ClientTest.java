public class ClientTest {

	public void testThreadedClient() {
		String host = "localhost";
		int port = 8088;

		for (int i = 0; i < 5; i++) { // number of JobID's per thread
			ThreadedPrintClientAdvNet pc = new ThreadedPrintClientAdvNet(i, host, port);
			Thread t = new Thread(pc);
			t.start();
		}
		try {
			Thread.sleep(2000);

		} catch (Exception e) {
		}
			/*ThreadedPrintClientAdvNet pc0 = new ThreadedPrintClientAdvNet(-1, host,
				port);
			Thread t1 = new Thread(pc0);
			t1.start();
		
		
		try {
			Thread.sleep(2000);

		} catch (Exception e) {
		}

			ThreadedPrintClientAdvNet pc1 = new ThreadedPrintClientAdvNet(-2, host,
				port);
			Thread t2 = new Thread(pc1);
			t2.start(); */
	} 

	public static void main(String args[]) {

		ClientTest ct = new ClientTest();
		ct.testThreadedClient();
	}
}