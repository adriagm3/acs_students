package baseWithStates.doorstates;

import baseWithStates.Door;
import baseWithStates.doorstates.Unlocked;

public abstract class DoorState {
    // Aquesta és una classe abstracta que representa l'estat d'una porta.
    // L'objectiu és utilitzar el patró State: cada porta pot tenir un estat (Locked, Unlocked, etc.)
    // i el comportament de la porta depèn de l'estat actual.

    protected Door door;  // referència a la porta concreta a la qual pertany aquest estat
    protected String name; // nom de l'estat, per exemple "Locked" o "Unlocked"

    public DoorState(Door door) {
        // Constructor que assigna la porta a la qual pertany aquest estat.
        this.door = door;
    }

    public String getName() {
        // Getter per obtenir el nom de l'estat.
        return name;
    }

    // Mètodes que representen accions sobre la porta.
    // Per defecte, cap acció està permesa; si es crida una acció no vàlida per l'estat,
    // es mostra un missatge a la consola.

    public void open() {
        System.out.println("Action OPEN not allowed in state " + name);
    }

    public void close() {
        System.out.println("Action CLOSE not allowed in state " + name);
    }

    public void lock() {
        System.out.println("Action LOCK not allowed in state " + name);
    }

    public void unlock() {
        System.out.println("Action UNLOCK not allowed in state " + name);
    }

    public void unlockShortly() {
        // Potser és un cas especial d'un desbloqueig temporal.
        // Ara mateix fa el mateix que unlock() per defecte.
        System.out.println("Action UNLOCK not allowed in state " + name);
    }
}
