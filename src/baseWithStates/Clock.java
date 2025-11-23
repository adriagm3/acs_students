package baseWithStates;

import baseWithStates.clock.ClockObserver;
import java.time.LocalDateTime;
import java.util.*;

public class Clock {

    // ───────────────────────────────
    // Singleton
    // ───────────────────────────────
    private static Clock instance;

    public static synchronized Clock getInstance() {
        if (instance == null) {
            instance = new Clock(); // període per defecte: 1s
        }
        return instance;
    }

    // ───────────────────────────────
    // Atributs
    // ───────────────────────────────
    private LocalDateTime date;
    private final Timer timer;
    private final int period;
    private final List<ClockObserver> observers = new ArrayList<>();

    // Constructor privat → obligatori per Singleton
    private Clock() {
        this.period = 1;
        this.timer = new Timer(true); // true = daemon thread
        this.date = LocalDateTime.now();
        start();
    }

    // ───────────────────────────────
    // Rellotge i observadors
    // ───────────────────────────────
    private void start() {
        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                date = LocalDateTime.now();
                notifyObservers();
            }
        };

        timer.scheduleAtFixedRate(repeatedTask, 0, period * 1000L);
    }

    // Opcional: per tests/unitats
    public void stop() {
        timer.cancel();
    }

    private void notifyObservers() {
        for (ClockObserver obs : new ArrayList<>(observers)) {
            obs.tick();
        }
    }

    public void addObserver(ClockObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(ClockObserver obs) {
        observers.remove(obs);
    }

    // ───────────────────────────────
    // Getters
    // ───────────────────────────────
    public LocalDateTime getDate() {
        return date;
    }

    public int getPeriod() {
        return period;
    }
}
