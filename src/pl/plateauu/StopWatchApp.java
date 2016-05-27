package pl.plateauu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StopWatchApp extends JFrame
{

	StopWatch timer;
	JLabel timerField;
	private String elapsedTimeSecs = "0";
	private String elapsedTimeMinutes = "0";
	private String elapsedTimeHours = "0";
	private String elapsedTimeDays = "0";

	private static final long serialVersionUID = -8302590286687710659L;

	public static void main(String[] args)
	{
		StopWatchApp t = new StopWatchApp();

	}

	public StopWatchApp()
	{
		this.setSize(300, 120);
		this.setTitle("StopWarch of App");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font bigFont = new Font("sans", Font.BOLD, 14);

		JPanel panel = new JPanel();
		JLabel timerLabel = new JLabel("StopWatch: ");

		timerField = new JLabel();
		timerField.setFont(bigFont);
		timerField.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		timerField.setText(elapsedTimeDays + " dni " + elapsedTimeHours + " godzin " + elapsedTimeMinutes + " min. "
				+ elapsedTimeSecs + " s.");

		JButton startButton = new JButton("START");
		startButton.addActionListener(new StartButtonListener());
		JButton stopButton = new JButton("STOP");
		stopButton.addActionListener(new StoptButtonListener());
		JButton resumeButton = new JButton("WZNÓW");
		resumeButton.addActionListener(new ResumeButtonListener());

		// tutaj trzeba dorobiæ
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem loadItem = new JMenuItem("Load");
		loadItem.setMnemonic(KeyEvent.VK_L);
		loadItem.addActionListener(new LoadItemListener());

		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.setMnemonic(KeyEvent.VK_S);
		saveItem.addActionListener(new SaveItemListener());

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_E);
		exitItem.addActionListener(new ExitItemListener());

		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);

		panel.add(timerLabel);
		panel.add(timerField);
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
			// timerArea.setText("0");
		}

	}

	private class TimerListerner implements ActionListener
	{

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			stringTime(timer);

		}

	}

	private class ResumeButtonListener implements ActionListener
	{

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			timer.resume();

		}

	}

	private class SaveItemListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			timer.stop();
				
			JFileChooser winChooser = new JFileChooser();
			winChooser.setDialogTitle("Gdzie zapisaæ projekt?");
			winChooser.showSaveDialog(getParent());

			try
			{
				FileOutputStream outputStr = new FileOutputStream(winChooser.getSelectedFile());
				ObjectOutputStream os = new ObjectOutputStream(outputStr);
				os.writeObject(timer);
				os.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}

	}

	private class LoadItemListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			{
				
				
				timer.stop();
				
				StopWatch loadTimer = null;
				JFileChooser fileCh = new JFileChooser();
				fileCh.setDialogTitle("Który projekt odczytaæ?");
				int veryfyCh = fileCh.showOpenDialog(getParent());

				try
				{
					FileInputStream inputStr = new FileInputStream(fileCh.getSelectedFile());
					ObjectInputStream objInputStr = new ObjectInputStream(inputStr);
					loadTimer = (StopWatch) objInputStr.readObject();

				} catch (Exception e)
				{
					e.printStackTrace();
				} 
				
				timer.setStartTime(loadTimer.getStartTime());
				timer.setStopTime(loadTimer.getStopTime());
				timer.setResumeTime(loadTimer.getResumeTime());
				timer.setRunning(loadTimer.isRunning());
				stringTime(loadTimer);

			}

		}

	}

	private class ExitItemListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			System.exit(0);

		}

	}

	private void stringTime(StopWatch timer)
	{
		String elapsedTime = Long.toString(timer.getElapsedTimeSecs());
		elapsedTimeSecs = Long.toString(timer.getElapsedTimeSecs() % 60);
		elapsedTimeMinutes = Long.toString(timer.getElapsedTimeSecs() / 60);
		elapsedTimeHours = Long.toString(timer.getElapsedTimeSecs() / 3600);
		elapsedTimeDays = Long.toString(timer.getElapsedTimeSecs() / 3600 / 24);
		timerField.setText(elapsedTimeDays + " dni " + elapsedTimeHours + " godzin " + elapsedTimeMinutes + " min. "
				+ elapsedTimeSecs + " s.");
	}

}
