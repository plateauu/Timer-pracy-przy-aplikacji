package pl.plateauu;

public class Timer
{

	private long startTime;
	private long stopTime;

	/**
	 * @return the startTime
	 */
	public long getStartTime()
	{
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(long startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * @return the stopTime
	 */
	public long getStopTime()
	{
		return stopTime;
	}

	/**
	 * @param stopTime
	 *            the stopTime to set
	 */
	public void setStopTime(long stopTime)
	{
		this.stopTime = stopTime;
	}

	void start()
	{
		startTime = System.nanoTime();
	}

	void stop()
	{
		stopTime = System.nanoTime();
		System.out.println((int) ((stopTime - startTime) * 1e-9) + " S.");
		
	}

	String elapsed()
	{
		long currentTime = System.nanoTime();
		long start = startTime;
		long value = currentTime -  start;
		return Long.toString(value/100);
	}

}
