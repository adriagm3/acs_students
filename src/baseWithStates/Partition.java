package baseWithStates;

import java.util.ArrayList;
import java.util.List;

public class Partition extends Area {


  private List<Area> subAreas;

  public Partition(String id, String name, Partition parent) {
    super(id, name, parent);
    this.subAreas = new ArrayList<>();

  }

  public void addSubArea(Area area) {
    subAreas.add(area);
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();
    for (Area area : subAreas) {
      doors.addAll(area.getDoorsGivingAccess());
    }
    return doors; //retornem una llista amb totes les portes dins la particio
  }

  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : subAreas) {
      spaces.addAll(area.getSpaces());
    }
    return spaces; //retornem una llista amb tots els espais dins la particio
  }


  @Override
  public Area findAreaById(String id) {
    if (this.id.equals(id)) {
      return this;
    }
    for (Area area : subAreas) {
      Area found = area.findAreaById(id);
      if (found != null) {
        return found;
      }
      }
    return null; //retornem null si no trobem l'area amb l'id especificat
  }

}