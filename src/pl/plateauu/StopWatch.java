package pl.plateauu;

class StopWatch
{

	private long startTime = 0;
	private long stopTime = 0;
	private long resumeTime = 0;
	private boolean running = false;

	public void start()
	{
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}

	public void stop()
	{
		this.stopTime = System.currentTimeMillis();
		this.running = false;
	}

	public void resume()
	{
		//this.stopTime = 0;
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
