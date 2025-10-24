package baseWithStates.requests;

import baseWithStates.DirectoryDoors;
import baseWithStates.DirectoryUsers;
import baseWithStates.Door;
import baseWithStates.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestReader implements Request {
    // Aquesta classe implementa la interfície Request i gestiona una petició concreta
    // per realitzar una acció sobre una porta determinada.

    private final String credential; // Identificador de l'usuari que fa la request
    private final String action;     // Acció a realitzar sobre la porta (LOCK, UNLOCK, etc.)
    private final LocalDateTime now; // Moment en què es fa la request
    private final String doorId;     // Identificador de la porta sobre la qual s'actua
    private String userName;         // Nom de l'usuari (per logs)
    private boolean authorized;      // Indica si l'usuari està autoritzat
    private final ArrayList<String> reasons; // Motius pels quals no està autoritzat
    private String doorStateName;    // Estat actual de la porta després de processar la request
    private boolean doorClosed;      // Estat de la porta (tancada/oberta) després de processar

    public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
        this.credential = credential;
        this.action = action;
        this.doorId = doorId;
        this.now = now;
        this.reasons = new ArrayList<>();
    }

    public void setDoorStateName(String name) {
        doorStateName = name;
    }

    public String getAction() {
        return action;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void addReason(String reason) {
        reasons.add(reason);
    }

    @Override
    public String toString() {
        if (userName == null) {
            userName = "unknown";
        }
        // Representació textual de la request per debugging/logging
        return "Request{"
                + "credential=" + credential
                + ", userName=" + userName
                + ", action=" + action
                + ", now=" + now
                + ", doorID=" + doorId
                + ", closed=" + doorClosed
                + ", authorized=" + authorized
                + ", reasons=" + reasons
                + "}";
    }

    @Override
    public JSONObject answerToJson() {
        // Converteix la resposta de la request a format JSON
        JSONObject json = new JSONObject();
        json.put("authorized", authorized);
        json.put("action", action);
        json.put("doorId", doorId);
        json.put("closed", doorClosed);
        json.put("state", doorStateName);
        json.put("reasons", new JSONArray(reasons));
        return json;
    }

    public void process() {
        // Processa la request:
        // 1. Recupera l'usuari i la porta corresponents
        // 2. Autoritza o denega l'acció
        // 3. Processa la request a la porta encara que no estigui autoritzada
        User user = DirectoryUsers.findUserByCredential(credential);
        Door door = DirectoryDoors.findDoorById(doorId);
        assert door != null : "door " + doorId + " not found";

        authorize(user, door); // determina si l'usuari està autoritzat
        door.processRequest(this); // processa la request a nivell de porta
        doorClosed = door.isClosed(); // actualitza l'estat de tancament de la porta
    }

    private void authorize(User user, Door door) {
        // Determina si la request està autoritzada i guarda motius si no ho està
        if (user == null) {
            authorized = false;
            addReason("user doesn't exist");
            return;
        }

        this.userName = user.toString(); // nom de l'usuari per a logs

        var group = user.getGroup(); // grup de l'usuari

        // Comprovacions d'autorització:
        boolean timeOk = group.canSendRequests(now); // dins l'horari permès
        boolean fromOk = group.canBeInSpace(door.getFromSpace()); // pot accedir a l'espai d'origen
        boolean toOk = group.canBeInSpace(door.getToSpace());     // pot accedir a l'espai de destinació
        boolean actionOk = group.canDoAction(action);             // pot fer l'acció

        if (!timeOk) addReason("outside allowed time interval");
        if (!fromOk) addReason("cannot access from-space " + door.getFromSpace());
        if (!toOk) addReason("cannot access to-space " + door.getToSpace());
        if (!actionOk) addReason("action " + action + " not allowed");

        authorized = timeOk && fromOk && toOk && actionOk;
    }
}
