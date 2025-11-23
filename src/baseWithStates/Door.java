package baseWithStates;

import baseWithStates.doorstates.DoorState;
import baseWithStates.requests.RequestReader;
import baseWithStates.doorstates.Unlocked;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Door {

    private static final Logger logger = LoggerFactory.getLogger(Door.class);

    private final String id;       // Identificador únic de la porta
    private boolean closed;        // Estat físic de la porta
    private DoorState state;       // Estat lògic (Locked, Unlocked, Propped...)
    private Area from;             // Àrea d'origen
    private Area to;               // Àrea de destinació

    // Constructor bàsic
    public Door(String id) {
        this.id = id;
        this.closed = true;
        this.state = new Unlocked(this);
        logger.info("Door " + id + " created (default areas).");
    }

    // Constructor amb àrees
    public Door(String id, Area from, Area to) {
        this.id = id;
        this.closed = true;
        this.state = new Unlocked(this);
        this.from = from;
        this.to = to;
        logger.info("Door " + id + " created between " + from.getName() + "none" + "and " + to.getName() + "none" + ".");
    }

    public void setClosed(boolean tancar) {
        this.closed = tancar;
        logger.debug("Door " + id + " setClosed -> " + tancar);
    }

    public void setState(DoorState newState) {
        this.state = newState;
        logger.info("Door " + id + " changed state -> " + newState.getName());
    }

    public void processRequest(RequestReader request) {

        if (!request.isAuthorized()) {
            logger.warn("Unauthorized request on door " + id + ".");
            request.setDoorStateName(getStateName());
            return;
        }

        String action = request.getAction();
        logger.info("Door " + id + " processing action " + action +".");

        doAction(action);

        request.setDoorStateName(getStateName());
    }

    private void doAction(String action) {
        switch (action) {
            case Actions.OPEN:
                state.open();
                break;

            case Actions.CLOSE:
                state.close();
                break;

            case Actions.LOCK:
                state.lock();
                break;

            case Actions.UNLOCK:
                state.unlock();
                break;

            case Actions.UNLOCK_SHORTLY:
                state.unlockShortly();
                break;

            default:
                logger.error("Door " + id + " received unknown action " + action +".");
        }
    }

    public boolean isClosed() {
        return closed;
    }

    public String getId() {
        return id;
    }

    public String getStateName() {
        return state.getName();
    }

    @Override
    public String toString() {
        return "Door{" +
                "id='" + id + '\'' +
                ", closed=" + closed +
                ", state=" + getStateName() +
                '}';
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("state", getStateName());
        json.put("closed", closed);
        return json;
    }

    public String getFromSpace() {
        return from != null ? from.getName() : "";
    }

    public String getToSpace() {
        return to != null ? to.getName() : "";
    }

    public Area getFrom() {
        return from;
    }

    public Area getTo() {
        return to;
    }
}
