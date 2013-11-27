import java.util.Vector;
import java.util.Random;
import java.io.Serializable;

public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int JobID, OPNumber;
	private Vector<Operation> OPs;
	private Random randGen;
	private boolean isDone = false;

	public Job(int a, int b) {
		this.JobID = a;
		this.OPNumber = b;
		this.OPs = new Vector<Operation>();
		this.randGen = new Random();

		/* if (JobID < 0) {
		Operation op = new Operation(this.JobID, 0, 1);
		this.OPs.add(op);
		this.OPNumber = 1;

	} else {
		for (int i = 0; i < b; i++) {
			int r = randGen.nextInt(2) + 1;
			Operation op = new Operation(this.JobID, r, i + 1);
			this.OPs.add(op);
		}
	} */
	
	if (JobID >= 0){
		for(int i = 0; i < b; i++){
			int r = randGen.nextInt(2) + 1;
			Operation op = new Operation(this.JobID, r, i + 1);
			this.OPs.add(op);
		}
	}else{
		Operation op = new Operation(this.JobID, 0, 1);
		this.OPs.add(op);
		this.OPNumber = 1;
	}
	try{
		Thread.sleep(2000);
	}catch (Exception e){}
	}

	public synchronized int getJobID() {
		return this.JobID;
	}

	public synchronized void setJobID(int d) {
		this.JobID = d;
	}

	public int getOPNumber() {
		return this.OPNumber;
	}

	public void setOPNumber(int d) {
		this.OPNumber = d;
	}

	public Vector<Operation> getOPs() {
		return this.OPs;
	}

	public void setOPs(Vector<Operation> d) {
		this.OPs = d;
	}

	public synchronized boolean isDone() {
		return this.isDone;
	}

	public synchronized void setIsDone(boolean d) {
		this.isDone = d;
	}

	public void print(String msg) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + ": " + msg);
	}
}
