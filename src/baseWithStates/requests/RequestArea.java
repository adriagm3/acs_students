package baseWithStates.requests;

import baseWithStates.Actions;
import baseWithStates.DirectoryAreas;
import baseWithStates.Door;
import org.json.JSONArray;
import org.json.JSONObject;
import baseWithStates.Area;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RequestArea implements Request {
    // Aquesta classe implementa la interfície Request per gestionar accions sobre àrees.
    // Una "àrea" és un conjunt de portes, i aquesta request crea requests individuals
    // per cada porta dins l'àrea per executar l'acció corresponent.

    private final String credential; // credencial de l'usuari que fa la request
    private final String action;     // acció a realitzar: LOCK o UNLOCK
    private final String areaId;     // identificador de l'àrea objectiu
    private final LocalDateTime now; // moment en què es fa la request
    private final ArrayList<RequestReader> requests = new ArrayList<>();
    // llista de requests per a cada porta dins l'àrea

    public RequestArea(String credential, String action, LocalDateTime now, String areaId) {
        this.credential = credential;
        this.areaId = areaId;

        // Comprovem que l'acció sigui vàlida per una àrea (només LOCK o UNLOCK)
        assert action.equals(Actions.LOCK) || action.equals(Actions.UNLOCK)
                : "invalid action " + action + " for an area request";
        this.action = action;
        this.now = now;
    }

    public String getAction() {
        return action;
    }

    @Override
    public JSONObject answerToJson() {
        // Retorna la resposta de la request en format JSON
        JSONObject json = new JSONObject();
        json.put("action", action);
        json.put("areaId", areaId);

        JSONArray jsonRequests = new JSONArray();
        for (RequestReader rd : requests) {
            // Afegim a l'array JSON les respostes de cada porta
            jsonRequests.put(rd.answerToJson());
        }
        json.put("requestsDoors", jsonRequests);
        json.put("todo", "request areas not yet implemented");
        // Nota: encara hi ha funcionalitat pendent
        return json;
    }

    @Override
    public String toString() {
        // Representació textual de la request per debugging/logging
        String requestsDoorsStr;
        if (requests.isEmpty()) {
            requestsDoorsStr = "";
        } else {
            requestsDoorsStr = requests.toString();
        }
        return "Request{"
                + "credential=" + credential
                + ", action=" + action
                + ", now=" + now
                + ", areaId=" + areaId
                + ", requestsDoors=" + requestsDoorsStr
                + "}";
    }

    // Mètode principal que processa la request sobre l'àrea
    public void process() {
        // Busquem l'àrea per ID
        Area area = DirectoryAreas.findAreaById(areaId);
        // pot ser null si no s'ha seleccionat cap àrea des de l'app

        if (area != null) {
            // Per cada porta dins l'àrea que dóna accés a l'espai:
            for (Door door : area.getDoorsGivingAccess()) {
                // Creem una request individual per cada porta
                RequestReader requestReader = new RequestReader(credential, action, now, door.getId());
                requestReader.process(); // processem la request de la porta
                // afegim la resposta de la porta a la llista de respostes de l'àrea
                requests.add(requestReader);
            }
        }
    }
}
