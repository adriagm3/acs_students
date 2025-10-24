package baseWithStates.doorstates;

import baseWithStates.Door;

public class Locked extends DoorState {
    // Aquesta classe representa l'estat "Locked" (bloquejat) d'una porta.
    // Estén DoorState, així que hereta totes les propietats i mètodes per defecte.
    // Aquí sobreescrivim els mètodes per definir el comportament concret quan la porta està bloquejada.

    public Locked(Door door) {
        super(door);
        this.name = States.LOCKED; // Assignem el nom de l'estat per poder-lo mostrar.
    }

    @Override
    public void unlock() {
        // Quan desbloquegem una porta completament:
        // 1. Canviem l'estat de la porta a "Unlocked"
        // 2. Mostrem un missatge indicant que la porta s'ha desbloquejat.
        door.setState(new Unlocked(door));
        System.out.println("Door " + door.getId() + " unlocked.");
    }

    @Override
    public void unlockShortly() {
        // Quan fem un desbloqueig temporal (per exemple, s'obrirà sola després d'un temps):
        // 1. Canviem l'estat a "UnlockedShortly"
        // 2. Mostrem un missatge indicatiu.
        door.setState(new UnlockedShortly(door));
        System.out.println("Door " + door.getId() + " unlocked shortly.");
    }

    @Override
    public void open() {
        // No podem obrir una porta que està bloquejada, així que només informem per consola.
        System.out.println("Cannot open door " + door.getId() + " because it's locked.");
    }

    @Override
    public void close() {
        // Comprovem si la porta ja està tancada.
        // Si no ho està, la tanquem però segueix bloquejada.
        if (!door.isClosed()) {
            door.setClosed(true);
            System.out.println("Door " + door.getId() + " closed (but remains locked).");
        } else {
            System.out.println("Door " + door.getId() + " is already closed.");
        }
    }
}
