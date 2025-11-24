package baseWithStates.doorstates;

import baseWithStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Unlocked extends DoorState {
    //estat desbloquejat de la porta, pot obrir i tancarse pero nome bloquejar quan tancada
    private static final Logger logger = LoggerFactory.getLogger(Unlocked.class);

    public Unlocked(Door door) {
        super(door);
        this.name = States.UNLOCKED;
    }

    @Override
    public void open() {
        if (door.isClosed()) {
            door.setClosed(false);
            logger.info("Door opened.");
        } else {
            logger.warn("Door is already open.");
        }
    }

    @Override
    public void close() {
        if (!door.isClosed()) {
            door.setClosed(true);
            logger.info("Door closed.");
        } else {
            logger.warn("Door is already closed.");
        }
    }

    @Override
    public void lock() {
        if (door.isClosed()) {
            door.setState(new Locked(door));
            logger.info("Door locked.");
        } else {
            logger.warn("Cannot lock door while it's open.");
        }
    }
}
