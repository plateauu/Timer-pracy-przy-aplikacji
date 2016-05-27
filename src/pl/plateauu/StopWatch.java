package pl.plateauu;

import java.io.Serializable;

class StopWatch implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7224616195898281026L;
	private long startTime = 0;
	private long stopTime = 0;
	private long resumeTime = 0;
	private boolean running = false;
	
	

	/**
	 * @return the startTime
	 */
	public long getStartTime()
	{
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
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
	 * @param stopTime the stopTime to set
	 */
	public void setStopTime(long stopTime)
	{
		this.stopTime = stopTime;
	}

	/**
	 * @return the resumeTime
	 */
	public long getResumeTime()
	{
		return resumeTime;
	}

	/**
	 * @param resumeTime the resumeTime to set
	 */
	public void setResumeTime(long resumeTime)
	{
		this.resumeTime = resumeTime;
	}

	/**
	 * @return the running
	 */
	public boolean isRunning()
	{
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running)
	{
		this.running = running;
	}

	public void start()
	{
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}

	public void stop()
	{
		if (running)
		{
			this.stopTime = System.currentTimeMillis();
			this.running = false;
		}
	}

	public void resume()
	{
		// this.stopTime = 0;
		this.resumeTime = System.currentTimeMillis() - this.stopTime;
		this.running = true;
	}

	// elaspsed time in milliseconds
	public long getElapsedTime()
	{
		long elapsed;
		if (running)
		{
			elapsed = (System.currentTimeMillis() - startTime - resumeTime);
		} else
		{
			elapsed = (stopTime - startTime - resumeTime);
		}
		return elapsed;
	}

	// elaspsed time in seconds
	public long getElapsedTimeSecs()
	{
		long elapsed;
		if (running)
		{
			elapsed = ((System.currentTimeMillis() - startTime - resumeTime) / 1000);
		} else
		{
			elapsed = ((stopTime - startTime - resumeTime) / 1000);
		}
		return elapsed;
	}
}
