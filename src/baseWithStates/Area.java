package baseWithStates;

import java.util.ArrayList;

public abstract class Area {
    // Classe abstracta que representa una "àrea" dins del sistema.
    // Una àrea pot ser un conjunt d'espais o particions, i conté portes que en donen accés.

    protected String id;        // Identificador únic de l'àrea
    protected String name;      // Nom descriptiu de l'àrea
    protected Partition parent; // Referència a la partició pare (no implementada encara)

    public Area(String id, String name, Partition parent) {
        this.id = id;
        this.name = name;
        this.parent = parent; // Inicialitza la referència al pare
    }

    public String getId() {
        return id; // Retorna l'identificador de l'àrea
    }

    public String getName() {
        return name; // Retorna el nom de l'àrea
    }

    // Retorna les portes que donen accés a aquesta àrea
    public abstract ArrayList<Door> getDoorsGivingAccess();

    // Retorna els espais continguts dins aquesta àrea
    public abstract ArrayList<Space> getSpaces();

    // Busca una àrea dins de la jerarquia amb un id concret
    public abstract Area findAreaById(String id);
}
