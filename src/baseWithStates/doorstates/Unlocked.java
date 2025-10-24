package baseWithStates.doorstates;

import baseWithStates.Actions;
import baseWithStates.Door;

public class Unlocked extends DoorState {
    // Aquesta classe representa l'estat "Unlocked" (desbloquejada) d'una porta.
    // Aquí la porta pot obrir-se i tancar-se, però només es pot bloquejar si està tancada.

    public Unlocked(Door door) {
        super(door);
        this.name = States.UNLOCKED; // Assignem el nom de l'estat.
    }

    @Override
    public void open() {
        // Obre la porta si està tancada.
        if (door.isClosed()) {
            door.setClosed(false); // Actualitzem l'estat de la porta.
            System.out.println("Door " + door.getId() + " opened."); // Missatge informatiu.
        } else {
            System.out.println("Door " + door.getId() + " is already open."); // Si ja està oberta, només mostrem missatge.
        }
    }

    @Override
    public void close() {
        // Tanca la porta si està oberta.
        if (!door.isClosed()) {
            door.setClosed(true); // Actualitzem l'estat de la porta.
            System.out.println("Door " + door.getId() + " closed."); // Missatge informatiu.
        } else {
            System.out.println("Door " + door.getId() + " is already closed."); // Si ja està tancada, només missatge.
        }
    }

    @Override
    public void lock() {
        // Bloqueja la porta només si està tancada.
        if (door.isClosed()) {
            door.setState(new Locked(door)); // Canvia l'estat de la porta a Locked.
            System.out.println("Door " + door.getId() + " locked."); // Missatge confirmant bloqueig.
        } else {
            System.out.println("Cannot lock door " + door.getId() + " while it's open.");
            // Si la porta està oberta, no es pot bloquejar i es mostra missatge.
        }
    }
}
