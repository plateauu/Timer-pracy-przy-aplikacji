package pl.plateauu.timer.app;

public class Main {
    StopWatchView theView;
    StopWatchModel theModel;
    StopWatchModel theAllTimeModel;

    public static void main(String[] args) {
	Main mainImpl = new Main();
    }

    public Main() {
	this.theView = new StopWatchView();
	this.theModel = new StopWatchModel();
	theView.initGui();
    }

}
