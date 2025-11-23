package baseWithStates.doorstates;

import baseWithStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Locked extends DoorState {

    private static final Logger logger = LoggerFactory.getLogger(Locked.class);

    public Locked(Door door) {
        super(door);
        this.name = States.LOCKED; // Nom de l'estat
    }

    @Override
    public void unlock() {
        door.setState(new Unlocked(door));
        logger.info("Door {} " + door.getId() + " unlocked.");
    }

    @Override
    public void unlockShortly() {
        door.setState(new UnlockedShortly(door));
        logger.info("Door " + door.getId() + " unlocked shortly.");
    }

    @Override
    public void open() {
        logger.warn("Cannot open door " + door.getId() + " because it's locked.");
    }

    @Override
    public void close() {
        if (!door.isClosed()) {
            door.setClosed(true);
            logger.info("Door " + door.getId() + " closed (but remains locked).");
        } else {
            logger.warn("Door " + door.getId() + " is already closed.");
        }
    }
}
