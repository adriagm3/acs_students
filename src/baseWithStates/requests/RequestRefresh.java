package baseWithStates.requests;

import baseWithStates.DirectoryDoors;
import baseWithStates.Door;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestRefresh implements Request {
    // Aquesta classe implementa Request i serveix per obtenir l'estat actual
    // de totes les portes. És útil per refrescar el simulador i mostrar l'estat
    // actual de les portes i dels lectors.

    private final ArrayList<JSONObject> jsonsDoors = new ArrayList<>();
    // Llista de JSONs de cada porta que s'omplirà en process()

    @Override
    public JSONObject answerToJson() {
        // Converteix la llista de portes a un JSON general
        JSONObject json = new JSONObject();
        json.put("doors", new JSONArray(jsonsDoors));
        return json;
    }

    @Override
    public String toString() {
        // Representació textual de la request per debugging/logging
        return "RequestRefresh{" + jsonsDoors + "}";
    }

    public void process() {
        // Omple la llista jsonsDoors amb l'estat actual de totes les portes
        // Recupera totes les portes del Directory i crida el mètode toJson() de cada una
        for (Door door : DirectoryDoors.getAllDoors()) {
            jsonsDoors.add(door.toJson());
        }
        // Això permet refrescar el simulador o l'app Flutter amb l'estat actual de cada porta
    }
}
