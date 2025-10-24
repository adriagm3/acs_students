package baseWithStates.doorstates;

import baseWithStates.Door;
import baseWithStates.Clock;
import baseWithStates.clock.ClockObserver;

public class UnlockedShortly extends DoorState implements ClockObserver {
    private static final int TIMEOUT_SECONDS = 10; // temps abans de tornar a bloquejar
    private int elapsedSeconds = 0;

    public UnlockedShortly(Door door) {
        super(door);
        this.name = "unlocked_shortly";
        Clock.getInstance().addObserver(this); // ens registrem al rellotge
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void tick() {
        elapsedSeconds++;
        if (elapsedSeconds >= TIMEOUT_SECONDS) {
            Clock.getInstance().removeObserver(this); // deixem de rebre ticks

            if (door.isClosed()) {
                door.setState(new Locked(door)); // si està tancada, bloqueja
                System.out.println("Door " + door.getId() + " auto-locked after timeout.");
            } else {
                door.setState(new Propped(door)); // si està oberta, passa a propped
                System.out.println("Door " + door.getId() + " is propped after timeout.");
            }
        }
    }

    @Override
    public void open() {
        if (door.isClosed()) {
            door.setClosed(false);
            System.out.println("Door " + door.getId() + " opened (unlocked shortly).");
        } else {
            System.out.println("Door " + door.getId() + " is already open.");
        }
    }

    @Override
    public void close() {
        if (!door.isClosed()) {
            door.setClosed(true);
            System.out.println("Door " + door.getId() + " closed (unlocked shortly).");
        } else {
            System.out.println("Door " + door.getId() + " is already closed.");
        }
    }

    @Override
    public void lock() {
        if (door.isClosed()) {
            door.setState(new Locked(door));
            System.out.println("Door " + door.getId() + " locked.");
        } else {
            System.out.println("Cannot lock door " + door.getId() + " while it's open.");
        }
    }

    @Override
    public void unlock() {
        // ja està desbloquejada, podem ignorar o reiniciar el temporitzador
        elapsedSeconds = 0;
        System.out.println("Door " + door.getId() + " already unlocked shortly; timer reset.");
    }

    @Override
    public void unlockShortly() {
        // reiniciem el temporitzador si es torna a desbloquejar temporalment
        elapsedSeconds = 0;
        System.out.println("Door " + door.getId() + " unlocked shortly again; timer reset.");
    }
}
