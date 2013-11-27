import java.io.Serializable;

public class Operation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int JobID, OPID, index;
	private String JobDescription;
	private boolean isDone = false;

	public Operation(int a, int b, int c) {
		this.JobID = a;
		this.OPID = b;
		this.index = c;
		this.setJobDescription(b);
	}

	public void setJobDescription(int r) {
		if (r == 2) { // maybe computing
			this.JobDescription = "Compute the following: A = JobID x OperationID x Operation Index\n" + 
									"A = " + this.JobID + " x " + this.OPID + " x " + this.index +
										"\nA = " + this.JobID * this.OPID * this.index;			
		} else if (r == 1) { // printing job
			this.JobDescription = "JobID: " + this.JobID
					+ "; OperationID: " + this.OPID + "; Operation Index: "
					+ this.index;
		} else {
		}
	}

	public int getJobID() {
		return this.JobID;
	}

	public void setJobID(int d) {
		this.JobID = d;
	}

	public synchronized boolean isDone() {
		return this.isDone;
	}

	public synchronized void setIsDone(boolean d) {
		this.isDone = d;
	}

	public int getOPID() {
		return this.OPID;
	}

	public void setOPID(int d) {
		this.OPID = d;
	}

	public String getJobDescription() {
		return this.JobDescription;
	}

	public void setJobDescription(String d) {
		this.JobDescription = d;
	}

	public void print(String msg) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + ": " + msg);
	}
}
