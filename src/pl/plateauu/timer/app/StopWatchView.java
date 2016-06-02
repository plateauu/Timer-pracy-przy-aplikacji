package pl.plateauu.timer.app;

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

public class StopWatchView {

    public StopWatchModel timer;
    public JLabel timerField;
    public JTextArea titleArea;
    public JFrame frame;
    private StopWatchModel allProjectsTimer;

    public void setTitleAreaEditable() {
	this.titleArea.setEditable(true);
    }

    public void setTitleAreaTitle(String title) {
	this.titleArea.setText(title);
	this.titleArea.setEditable(false);
    }

    public void setTitleAreaTitle() {
	this.titleArea.setEditable(false);
    }

    public void initGui() {
	frame = new JFrame();
	frame.setSize(270, 160);
	frame.setTitle("StopWatch of App Coding");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Font bigFont = new Font("sans", Font.BOLD, 14);

	JPanel panel = new JPanel();
	JLabel timerLabel = new JLabel("StopWatch: ");

	timerField = new JLabel();
	timerField.setFont(bigFont);
	timerField.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
	timerField.setText("[Run the StopWatch]");

	JButton startButton = new JButton("START");
	startButton.addActionListener(new StartButtonListener());
	JButton stopButton = new JButton("STOP");
	stopButton.addActionListener(new StoptButtonListener());
	JButton resumeButton = new JButton("RESUME");
	resumeButton.addActionListener(new ResumeButtonListener());

	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	fileMenu.setMnemonic(KeyEvent.VK_F);

	JMenuItem loadItem = new JMenuItem("Load project");
	loadItem.setMnemonic(KeyEvent.VK_L);
	loadItem.addActionListener(new LoadItemListener());

	JMenuItem saveItem = new JMenuItem("Save project");
	saveItem.setMnemonic(KeyEvent.VK_S);
	saveItem.addActionListener(new SaveItemListener());

	JMenuItem exitItem = new JMenuItem("Exit program");
	exitItem.setMnemonic(KeyEvent.VK_E);
	exitItem.addActionListener(new ExitItemListener());
	
	JMenuItem newItem = new JMenuItem("New project");
	newItem.setMnemonic(KeyEvent.VK_N);
	newItem.addActionListener(new NewItemListener());

	fileMenu.add(newItem);
	fileMenu.addSeparator();
	fileMenu.add(saveItem);
	fileMenu.add(loadItem);
	fileMenu.addSeparator();
	fileMenu.add(exitItem);

	menuBar.add(fileMenu);
	frame.setJMenuBar(menuBar);

	titleArea = new JTextArea(1, 20);
	titleArea.setText("Type the project title");
	titleArea.selectAll();

	StatusBar statusBar = new StatusBar();
	statusBar.setMessage("Total time: Tutaj bêdzie licznik");

	panel.add(titleArea);
	panel.add(timerLabel);
	panel.add(timerField);
	panel.add(startButton);
	panel.add(stopButton);
	panel.add(resumeButton);

	frame.getContentPane().add(BorderLayout.CENTER, panel);
	frame.getContentPane().add(BorderLayout.SOUTH, statusBar);
	frame.setVisible(true);
    }

    
    private void saveTheProject() {
	JFileChooser winChooser = new JFileChooser();
	winChooser.setDialogTitle("Gdzie zapisaæ projekt?");
	winChooser.showSaveDialog(frame);
	try {
	FileOutputStream outputStr = new FileOutputStream(winChooser.getSelectedFile());
	ObjectOutputStream os = new ObjectOutputStream(outputStr);
	os.writeObject(timer);
	os.close();
	} catch (IOException e) {
	e.printStackTrace();
	}
    }


    private class NewItemListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
	    timer = new StopWatchModel();
	    setTitleAreaTitle("Type a new project");
	    setTitleAreaEditable();
	}
	
    }
    
    private class StartButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    timer = new StopWatchModel();
	    timer.start();
	    javax.swing.Timer time = new javax.swing.Timer(1000, new TimerListerner());
	    time.start();
	    setTitleAreaTitle();
	}
    }

    private class StoptButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
	    timer.stop();
	}
    }

    private class TimerListerner implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
	    timerField.setText(timer.stringTime());
	}
    }

    private class ResumeButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
	    timer.resume();
	}
    }

    private class SaveItemListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
	    timer.stop();
	    saveTheProject();
	}
    }

    private class LoadItemListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
	    {
		timer.stop();

		StopWatchModel loadTimer = null;
		JFileChooser fileCh = new JFileChooser();
		fileCh.setDialogTitle("Który projekt odczytaæ?");
		int veryfyCh = fileCh.showOpenDialog(frame);

		try {
		    FileInputStream inputStr = new FileInputStream(fileCh.getSelectedFile());
		    ObjectInputStream objInputStr = new ObjectInputStream(inputStr);
		    loadTimer = (StopWatchModel) objInputStr.readObject();
		} catch (Exception e) {
		    e.printStackTrace();
		}

		timer.setStartTime(loadTimer.getStartTime());
		timer.setStopTime(loadTimer.getStopTime());
		timer.setResumeTime(loadTimer.getResumeTime());
		timer.setRunning(loadTimer.isRunning());
		timerField.setText(timer.stringTime());
		setTitleAreaTitle(timer.getTitle());

	    }
	}
    }

    private class ExitItemListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
	    System.exit(0);
	}
    }
}
