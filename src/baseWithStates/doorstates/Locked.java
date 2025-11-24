package baseWithStates.doorstates;

import baseWithStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Locked extends DoorState {
//estat bloquejat, esten DoorState heretant, sobrescriibim para comportament quan bloq
    private static final Logger logger = LoggerFactory.getLogger(Locked.class);

    public Locked(Door door) {
        super(door);
        this.name = States.LOCKED; // Nom de l'estat
    }

    //funcionas que canvien l¡estat de la porta posant missatge al logger
    @Override
    public void unlock() {
        door.setState(new Unlocked(door));
        logger.info("Door unlocked.");
    }

    @Override
    public void unlockShortly() {
        door.setState(new UnlockedShortly(door));
        logger.info("Door unlocked shortly.");
    }

    @Override
    public void open() {
        logger.warn("Cannot open door because it's locked.");
    }

    @Override
    public void close() {
        if (!door.isClosed()) {
            door.setClosed(true);
            logger.info("Door closed (but remains locked).");
        } else {
            logger.warn("Door is already closed.");
        }
    }
}
