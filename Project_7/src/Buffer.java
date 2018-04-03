
public interface Buffer {
	//place int value into buffer
	public void blockingPut(int value) throws InterruptedException;
	
	//return int value into buffer
	public int blockingGet() throws InterruptedException;
}
