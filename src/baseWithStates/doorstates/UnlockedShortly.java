package baseWithStates.doorstates;

import baseWithStates.Door;
import baseWithStates.Clock;
import baseWithStates.clock.ClockObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnlockedShortly extends DoorState implements ClockObserver {

    private static final Logger logger = LoggerFactory.getLogger(UnlockedShortly.class);

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
                door.setState(new Locked(door));
                logger.info("Door " + door.getId() + " auto-locked after timeout.");
            } else {
                door.setState(new Propped(door));
                logger.info("Door " + door.getId() + " is propped after timeout.");
            }
        }
    }

    @Override
    public void open() {
        if (door.isClosed()) {
            door.setClosed(false);
            logger.info("Door " + door.getId() + " opened (unlocked shortly).");
        } else {
            logger.info("Door " + door.getId() + " is already open.");
        }
    }

    @Override
    public void close() {
        if (!door.isClosed()) {
            door.setClosed(true);
            logger.info("Door " + door.getId() + " closed (unlocked shortly).");
        } else {
            logger.info("Door " + door.getId() + " is already closed.");
        }
    }

    @Override
    public void lock() {
        if (door.isClosed()) {
            door.setState(new Locked(door));
            logger.info("Door " + door.getId() + " locked.");
        } else {
            logger.warn("Cannot lock door " + door.getId() + " while it's open.");
        }
    }

    @Override
    public void unlock() {
        elapsedSeconds = 0;
        logger.info("Door " + door.getId() + " already unlocked shortly; timer reset.");
    }

    @Override
    public void unlockShortly() {
        elapsedSeconds = 0;
        logger.info("Door " + door.getId() + " unlocked shortly again; timer reset.");
    }
}
