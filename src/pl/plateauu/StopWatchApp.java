package pl.plateauu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StopWatchApp extends JFrame
{

	StopWatch timer;
	protected JTextArea timerArea;

	private static final long serialVersionUID = -8302590286687710659L;

	public static void main(String[] args)
	{
		StopWatchApp t = new StopWatchApp();
		
	}

	public StopWatchApp()
	{
		this.setSize(350, 150);
		this.setTitle("StopWarch of App");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font bigFont = new Font("sans", Font.BOLD, 18);

		JPanel panel = new JPanel();
		JLabel timerLabel = new JLabel("Timer");

		timerArea = new JTextArea(1, 9);
		timerArea.setEditable(false);
		timerArea.setFont(bigFont);
		timerArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		timerArea.setText("timer");

		JButton startButton = new JButton("START");
		startButton.addActionListener(new StartButtonListener());
		JButton stopButton = new JButton("STOP");
		stopButton.addActionListener(new StoptButtonListener());
		JButton resumeButton = new JButton("WZNÓW");
		resumeButton.addActionListener(new ResumeButtonListener());

		panel.add(timerLabel);
		panel.add(timerArea);
		panel.add(startButton);
		panel.add(stopButton);
		panel.add(resumeButton);

		this.getContentPane().add(BorderLayout.CENTER, panel);
		this.setVisible(true);
	}

	private class StartButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			timer = new StopWatch();
			timer.start();
			javax.swing.Timer time = new javax.swing.Timer(1000, new TimerListerner());
			time.start();

		}

	}

	private class StoptButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			timer.stop();
			//timerArea.setText("0");
		}

	}

	private class TimerListerner implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			String elapsedTime = Long.toString(timer.getElapsedTimeSecs());
			timerArea.setText(elapsedTime);
			
		}
		
	}
	
	private class ResumeButtonListener implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			timer.resume();
			
		}
		
	}

}
