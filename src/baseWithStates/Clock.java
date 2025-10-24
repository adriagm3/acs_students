package baseWithStates;

import baseWithStates.clock.ClockObserver;
import java.time.LocalDateTime;
import java.util.*;

public class Clock {
    // Singleton que representa un rellotge del sistema que envia "ticks" cada segon a tots els observadors.

    private static final Clock instance = new Clock(1); // instància única del Clock
    private LocalDateTime date;                           // data i hora actual del rellotge
    private final Timer timer;                            // Timer de Java per programar tasques periòdiques
    private final int period;                             // període de cada tick en segons
    private final List<ClockObserver> observers = new ArrayList<>(); // llista d'observadors registrats

    private Clock(int period) {
        this.period = period;
        this.timer = new Timer();
        start(); // comença a enviar ticks
    }

    public static Clock getInstance() {
        return instance; // retorna la instància singleton
    }

    private void start() {
        // Crea una tasca que s'executa periòdicament segons el period definit
        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                date = LocalDateTime.now(); // actualitza la data
                notifyObservers();          // notifica tots els observadors
            }
        };
        timer.scheduleAtFixedRate(repeatedTask, 0, 1000 * period); // executa cada "period" segons
    }

    private void notifyObservers() {
        // Copia la llista d'observadors per evitar ConcurrentModificationException
        for (ClockObserver obs : new ArrayList<>(observers)) {
            obs.tick(); // crida el mètode tick() de cada observador
        }
    }

    public void addObserver(ClockObserver obs) {
        observers.add(obs); // afegeix un observador
    }

    public void removeObserver(ClockObserver obs) {
        observers.remove(obs); // elimina un observador
    }

    public LocalDateTime getDate() {
        return date; // retorna la data/hora actual
    }

    public int getPeriod() {
        return period; // retorna el període en segons
    }
}
