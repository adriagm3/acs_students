package baseWithStates.doorstates;

import baseWithStates.Door;

public class Propped extends DoorState {
    // Aquesta classe representa l'estat "Propped" d'una porta.
    // "Propped" vol dir que la porta està oberta, però subjecta o sostenida (no completament desbloquejada ni tancada).

    public Propped(Door door) {
        super(door);
        // Aquí cridem el constructor de la classe pare (DoorState) per guardar la referència a la porta.
        // No assignem directament 'name', ja que ho fem amb el mètode getName().
    }

    @Override
    public String getName() {
        // Tornem el nom de l'estat. Això és útil per mostrar missatges o fer debugging.
        return "propped";
    }
}
