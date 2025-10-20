package baseWithStates;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    private static final Clock instance = new Clock(1); // període d’1 segon
    private LocalDateTime date;
    private final Timer timer;
    private final int period; // segons

    private Clock(int period) {
        this.period = period;
        this.timer = new Timer();
        start();
    }

    public static Clock getInstance() {
        return instance;
    }

    private void start() {
        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                date = LocalDateTime.now();
                // System.out.println("Clock tick: " + date);
            }
        };
        timer.scheduleAtFixedRate(repeatedTask, 0, 1000 * period);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getPeriod() {
        return period;
    }
}
