package urfu.model;

import javax.swing.*;
import java.util.TimerTask;
public class GameTimer extends TimerTask {
    private JLabel label;
    private int timeLeft;

    public GameTimer(JLabel label, int time) {
        this.label = label;
        this.timeLeft = time;
    }

    @Override
    public void run() {
        label.setText("Time left: " + timeLeft + " Score: " + Robot.countValue());
        timeLeft--;
        if (timeLeft < 0) {
            cancel();
            label.setText("Time's over. Final Score: " + Robot.countValue());
        }
    }
    public void increaseTimeLeft(int increment) {
        timeLeft += increment;
    }
}

