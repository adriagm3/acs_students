package baseWithStates;

import java.util.ArrayList;

public class Space extends Area {
    // Aquesta classe representa un espai físic dins del sistema.
    // Cada espai pot tenir un conjunt de portes que donen accés a altres espais.

    private final ArrayList<Door> doors; // Llista de portes dins d'aquest espai

    public Space(String id, String name, Partition parent) {
        super(id, name, parent);
        this.doors = new ArrayList<>(); // Inicialitza la llista de portes
    }

    public void addDoor(Door door) {
        // Afegeix una porta a aquest espai
        doors.add(door);
    }

    @Override
    public ArrayList<Door> getDoorsGivingAccess() {
        // Retorna una nova llista amb les portes de l'espai
        // Això evita que es pugui modificar la llista original des de fora
        return new ArrayList<>(doors);
    }

    @Override
    public ArrayList<Space> getSpaces() {
        // Retorna una llista amb aquest espai (ja que és un espai concret)
        ArrayList<Space> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public Area findAreaById(String id) {
        // Retorna aquest espai si l'ID coincideix, si no, retorna null
        return this.id.equals(id) ? this : null;
    }
}
