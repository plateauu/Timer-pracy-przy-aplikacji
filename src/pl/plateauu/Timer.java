package pl.plateauu;

public class Timer
{

	private long startTime;
	private long stopTime; 
	
	
	public static void main(String[] args)
	{
		Timer gui = new Timer();
		gui.start();
		System.out.println(gui.startTime);
		gui.stop();
		System.out.println(gui.stopTime);

	}

	private void initGui()
	{

	}

	private void start()
	{
		startTime = System.nanoTime();
	}

	private void stop()
	{
		stopTime = System.nanoTime();
		System.out.println(stopTime - startTime * 1e-9 );
	}

}
