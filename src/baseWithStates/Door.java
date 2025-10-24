package baseWithStates;

import baseWithStates.doorstates.DoorState;
import baseWithStates.requests.RequestReader;
import org.json.JSONObject;
import baseWithStates.doorstates.Unlocked;

public class Door {
    // Aquesta classe representa una porta del sistema.
    // Conté l'estat actual, si està tancada o oberta, i les àrees d'origen i destinació.

    private final String id;       // Identificador únic de la porta
    private boolean closed;        // Estat físic de la porta (tancada/oberta)
    private DoorState state;       // Estat de la porta (Locked, Unlocked, etc.)
    private Area from;             // Espai d'origen per accedir a la porta
    private Area to;               // Espai de destinació al travessar la porta

    // Constructor bàsic sense àrees
    public Door(String id) {
        this.id = id;
        closed = true;             // Inicialment tancada
        this.state = new Unlocked(this); // Inicialment desbloquejada
    }

    // Constructor amb àrees
    public Door(String id, Area from, Area to) {
        this.id = id;
        closed = true;
        this.state = new Unlocked(this);
        this.from = from;
        this.to = to;
    }

    public void setClosed(boolean tancar) {
        closed = tancar; // Canvia l'estat físic de la porta
    }

    public void setState(DoorState newState) {
        state = newState; // Canvia l'estat de la porta
    }

    public void processRequest(RequestReader request) {
        // La porta processa la request perquè coneix el seu estat i si està tancada/oberta
        if (request.isAuthorized()) {
            String action = request.getAction();
            doAction(action); // Executa l'acció
        } else {
            System.out.println("not authorized"); // Usuari no autoritzat
        }
        request.setDoorStateName(getStateName()); // Guarda l'estat actual de la porta a la request
    }

    private void doAction(String action) {
        // Executa l'acció segons l'estat de la porta
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
                System.out.println("Unknown action: " + action);
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
        return "Door{"
                + "id='" + id + '\''
                + ", closed=" + closed
                + ", state=" + getStateName()
                + "}";
    }

    public JSONObject toJson() {
        // Converteix l'estat de la porta a JSON
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
