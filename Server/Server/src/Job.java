import java.util.Vector;
import java.util.Random;
import java.io.Serializable;

public class Job implements Serializable {

	private int JobID, OPNumber;
	private Vector OPs;
	private Random randGen;
	private boolean isDone = false;

	public Job() {
	}

	public Job(int a, int b) {
		this.JobID = a;
		this.OPNumber = b;
		this.OPs = new Vector();

		this.randGen = new Random();
		// System.out.println("the job id is " + a +
		// " and the job number is "+b);
		
		if (JobID <= 5) {
			for (int i = 0; i < b; i++) { // job index
				int r = randGen.nextInt(2)+1; // opnumber, you only have 1 and
												// 2
				Operation op = new Operation(this.JobID, r, i + 1);
				this.OPs.add(op);
			}
		} else {
			Operation op = new Operation(this.JobID, 0, 1);
			this.OPs.add(op);
		}
		try {
			Thread.sleep(2000);

		} catch (Exception e) {
		}

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

	public Vector getOPs() {
		return this.OPs;
	}

	public void setOPs(Vector d) {
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
