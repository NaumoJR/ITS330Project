import java.net.*;
import java.io.*;
import java.util.*;

public class ThreadedPrintServerAdvNet implements Runnable {

	private int port, MaxConnections;
	private Vector<Job> Jobque;

	// private ObjectOutputStream objOut;
	// private ObjectInputStream objIn;

	public ThreadedPrintServerAdvNet() {

	}

	public ThreadedPrintServerAdvNet(int a, int b) {
		this.port = a;
		this.MaxConnections = b;
		this.Jobque = new Vector<Job>();

		// start your printer here
		PrintThread pt = new PrintThread(this.Jobque);
		pt.start();
		// start computing thread here
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int a) {
		this.port = a;
	}

	public int getMax() {
		return this.MaxConnections;
	}

	public void setMax(int a) {
		this.MaxConnections = a;
	}

	public void listen() {
		System.out.println("The server is running.");
		int i = 0;

		try {
			ServerSocket listener = new ServerSocket(this.port);

			Socket serverSideSocket;

			while (true) {
				// instead of simply call a method, we need to create a thread
				// to handle the job
				serverSideSocket = listener.accept();

				// handleConnectionObj(serverSideSocket);
				Connection cn = new Connection(this, serverSideSocket, i);
				cn.start();
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Connection currentThread = (Connection) Thread.currentThread();
		try {
			handleConnectionObj(currentThread.getSocket());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleConnectionObj(Socket server) throws IOException {
		ObjectOutputStream objOut = new ObjectOutputStream(
				new BufferedOutputStream(server.getOutputStream(), 2048));
		objOut.flush();// this statement is crucial

		ObjectInputStream objIn = new ObjectInputStream(
				new BufferedInputStream(server.getInputStream()));

		try {
			Job job = (Job) objIn.readObject();
			this.Jobque.add(job);

			System.out.println("the job ID is " + job.getJobID()
					+ " the number of ops is " + job.getOPNumber());

			/*
			 * if (job == null) { System.out.println("a null job got"); }
			 * 
			 * else { int opn = job.getOPNumber(); Vector opv = job.getOPs();
			 * 
			 * for (int i = 0; i < opn; i++) { Operation op = (Operation)
			 * opv.elementAt(i);
			 * 
			 * if (op.getOPID() == 1) {
			 * System.out.println("the operation type is 1"); }
			 * 
			 * else if (op.getOPID() == 2) {
			 * System.out.println("the operation type is 2"); }
			 * 
			 * else if (op.getOPID() == 0) {
			 * System.out.println("the operation type is 0"); } } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		objOut.close();
		objIn.close();
		server.close();
	}
}

class Connection extends Thread {
	private Socket serverSideSocket;

	public Connection(Runnable obj, Socket s, int id) {
		super(obj, " server thread " + id);
		serverSideSocket = s;
	}

	public Socket getSocket() {
		return this.serverSideSocket;
	}
}

class PrintThread extends Thread {
	private Vector<Job> jobQ;
	private boolean stop = false;

	public PrintThread(Vector<Job> v) {
		super("The Printing Thread");
		this.jobQ = v;
	}

	public void run() {
		while (!stop) {
			while ((!stop) && (!jobQ.isEmpty())) {
				int i = 0;

				for (i = 0; i < jobQ.size(); i++) {

					if (stop){
						break;
					}

					Job job = jobQ.elementAt(i);
					//boolean JobTurn = true;
					int opn = job.getOPNumber();
					Vector opv = job.getOPs();
					boolean removable = true;
					boolean doneOne = false;

					for (int j = 0; j < opn; j++) {
						
						Operation op = (Operation) opv.elementAt(j);

						if ((op.getOPID() == 1) && (!op.isDone())) {
							
							if (doneOne){
							System.out.println("Job ID is " + job.getJobID()
									+ " and the OP type is 1");
							op.print(op.getJobDescription());
							op.setIsDone(true);
							doneOne= false;
							}
						} 
							
							else if (op.getOPID() == 0) {
							System.out
									.println("The operation type is 0 and the print thread is to be terminated.");
							this.stop = true;
						} else {
							if (!op.isDone())
								removable = false;
						}
						if (removable) {
							jobQ.remove(job);
							i--;
						}
					}
				} // end of the if, if job queue is empty
				try {
					this.sleep(1000);
				} catch (Exception e) {
				}
			}
		} // end of else, if no job, thread block itself to yield CPU
	} // end of while
} // end of run
