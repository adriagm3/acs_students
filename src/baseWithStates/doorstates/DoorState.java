package baseWithStates.doorstates;

import baseWithStates.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DoorState {
//Repreenta l'estat d'una porta, utilitza el patro state cada portat pot tenir un estat i el comportament de la porta depen de l'estat actual
    protected static final Logger logger = LoggerFactory.getLogger(DoorState.class);

    protected final Door door;
    protected String name;

    public DoorState(Door door) {
      //constructor copia assigna a la qual pertany aquest estat
        this.door = door;
    }

    public String getName() {
        return name;
    }

    //metodes on per defecte cap accio esta permesa, si no valida per l'estat missatge al logger
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
