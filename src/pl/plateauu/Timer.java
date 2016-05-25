package pl.plateauu;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Timer
{

	private long startTime;
	private long stopTime; 
	
	void start()
	{
		startTime = System.nanoTime();
	}

	void stop()
	{
		stopTime = System.nanoTime();
		System.out.println(stopTime - startTime * 1e-9 );
	}

}
