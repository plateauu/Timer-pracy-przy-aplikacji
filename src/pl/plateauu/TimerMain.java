package pl.plateauu;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TimerMain extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8302590286687710659L;

	public static void main(String[] args)
	{

		new TimerMain();

	}

	public TimerMain()
	{
		this.setSize(400, 400);
		this.setTitle("Timer aplikacji");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font bigFont = new Font("sans", Font.BOLD, 28);

		JPanel panel = new JPanel();
		JLabel timerLabel = new JLabel("Timer");
		
		JTextArea timerArea = new JTextArea(1, 9);
		timerArea.setEditable(false);
		timerArea.setFont(bigFont);
		timerArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		timerArea.setText("timer");
		
		
		panel.add(timerLabel);
		panel.add(timerArea);
		
		
		

		this.getContentPane().add(BorderLayout.CENTER, panel);
		this.setVisible(true);
	}

}
