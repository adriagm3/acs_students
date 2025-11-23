package baseWithStates.doorstates;

import baseWithStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DoorState {

    protected static final Logger logger = LoggerFactory.getLogger(DoorState.class);

    protected final Door door;
    protected String name;

    public DoorState(Door door) {
        this.door = door;
    }

    public String getName() {
        return name;
    }

    public void open() {
        logger.warn("Action OPEN not allowed in state");
    }

    public void close() {
        logger.warn("Action CLOSE not allowed in state");
    }

    public void lock() {
        logger.warn("Action LOCK not allowed in state");
    }

    public void unlock() {
        logger.warn("Action UNLOCK not allowed in state");
    }

    public void unlockShortly() {
        logger.warn("Action UNLOCK not allowed in state ");
    }
}
