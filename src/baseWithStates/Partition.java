package baseWithStates;

import java.util.ArrayList;
import java.util.List;

public class Partition extends Area {
    // Aquesta classe representa una partició dins del sistema, que pot contenir subàrees
    // Les particions són àrees que agrupen altres àrees o espais

    private final List<Area> subAreas; // Llista de subàrees dins d'aquesta partició

    public Partition(String id, String name, Partition parent) {
        super(id, name, parent);
        this.subAreas = new ArrayList<>();
    }

    public void addSubArea(Area area) {
        // Afegeix una subàrea a la partició
        subAreas.add(area);
    }

    @Override
    public ArrayList<Door> getDoorsGivingAccess() {
        // Retorna totes les portes de les subàrees
        ArrayList<Door> doors = new ArrayList<>();
        for (Area area : subAreas) {
            doors.addAll(area.getDoorsGivingAccess());
        }
        return doors; // Llista amb totes les portes dins la partició
    }

    @Override
    public ArrayList<Space> getSpaces() {
        // Retorna tots els espais de les subàrees
        ArrayList<Space> spaces = new ArrayList<>();
        for (Area area : subAreas) {
            spaces.addAll(area.getSpaces());
        }
        return spaces; // Llista amb tots els espais dins la partició
    }

    @Override
    public Area findAreaById(String id) {
        // Busca una àrea amb l'ID especificat dins de la partició o subàrees
        if (this.id.equals(id)) {
            return this;
        }
        for (Area area : subAreas) {
            Area found = area.findAreaById(id);
            if (found != null) {
                return found; // Retorna la primera àrea trobada amb l'ID
            }
        }
        return null; // Retorna null si no trobem cap àrea amb l'ID especificat
    }
}
