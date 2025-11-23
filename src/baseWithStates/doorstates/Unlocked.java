package baseWithStates.doorstates;

import baseWithStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.valueOf;

public class Unlocked extends DoorState {

    private static final Logger logger = LoggerFactory.getLogger(Unlocked.class);

    public Unlocked(Door door) {
        super(door);
        this.name = States.UNLOCKED;
    }

    @Override
    public void open() {
        if (door.isClosed()) {
            door.setClosed(false);
            logger.info("Door " + door.getId() + " opened.");
        } else {
            logger.warn("Door " + door.getId() + " is already open.");
        }
    }

    @Override
    public void close() {
        if (!door.isClosed()) {
            door.setClosed(true);
            logger.info("Door " + door.getId() + " closed.");
        } else {
            logger.warn("Door " + door.getId() + " is already closed.");
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
}
