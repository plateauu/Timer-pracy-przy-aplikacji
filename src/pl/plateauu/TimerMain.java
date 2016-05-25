package pl.plateauu;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TimerMain extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8302590286687710659L;

	public static void main(String[] args)
	{
		
		JFrame frame = new JFrame("Timer aplikacji");
		
	}
	
	public TimerMain(String title) 
	{
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		
		this.getContentPane().add(BorderLayout.CENTER, panel);
		
		this.setSize(400, 400);
		this.setVisible(true);
	}

}
