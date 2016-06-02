package pl.plateauu.timer.app;

import java.io.Serializable;

import pl.plateauu.timer.app.*;

public class StopWatchModel implements Serializable {

    private static final long serialVersionUID = 7224616195898281026L;

    private long startTime = 0;
    private long stopTime = 0;
    private long resumeTime = 0;
    private boolean running = false;
    private String title = null;
    private StopWatchView theView;

    public StopWatchModel(StopWatchView theView) {
	super();
	this.theView = theView;
    }

    public StopWatchModel() {
	super();
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public long getStartTime() {
	return startTime;
    }

    public void setStartTime(long startTime) {
	this.startTime = startTime;
    }

    public long getStopTime() {
	return stopTime;
    }

    public void setStopTime(long stopTime) {
	this.stopTime = stopTime;
    }

    public long getResumeTime() {
	return resumeTime;
    }

    public void setResumeTime(long resumeTime) {
	this.resumeTime = resumeTime;
    }

    public boolean isRunning() {
	return running;
    }

    public void setRunning(boolean running) {
	this.running = running;
    }

    public void start() {
	this.startTime = System.currentTimeMillis() - stopTime - resumeTime;
	this.running = true;

    }

    public void stop() {
	if (running) {
	    this.stopTime = System.currentTimeMillis();
	    this.running = false;
	}
    }

    public void resume() {
	if (!running) {
	    this.resumeTime = System.currentTimeMillis() - stopTime;
	    this.running = true;
	}
    }

    public long getElapsedTime() {
	long elapsed;
	if (running) {
	    elapsed = (System.currentTimeMillis() - startTime - resumeTime);
	} else {
	    elapsed = (stopTime - startTime - resumeTime);
	}
	return elapsed;
    }

    public long getElapsedTimeSecs() {
	long elapsed;
	if (running) {
	    elapsed = ((System.currentTimeMillis() - startTime - resumeTime) / 1000);
	} else {
	    elapsed = ((stopTime - startTime - resumeTime) / 1000);
	}
	return elapsed;
    }

    public String stringTime() {
	StringBuffer sb = new StringBuffer();
	long elapsed = getElapsedTimeSecs();

	sb.append("[");
	sb.append(elapsed / 3600 / 24);
	sb.append(" D ");
	sb.append(elapsed / 3600);
	sb.append(" H ");
	sb.append((elapsed / 60));
	sb.append(" M ");
	sb.append((elapsed % 60));
	sb.append(" s]");
	return sb.toString();
    }
}
