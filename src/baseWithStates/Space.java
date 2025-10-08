package baseWithStates;

import java.util.ArrayList;

public class Space extends Area {

  private ArrayList<Door> doors;


  public Space(String id, String name, Partition parent) {
        super(id, name, parent);
        this.doors = new ArrayList<>(); //creem l'array de portes de l'espai respectiu
    }



    public void addDoor(Door door) {
        doors.add(door);
    }
    @Override
    public ArrayList<Door> getDoorsGivingAccess() {
        return new ArrayList<>(doors); //retornem una nova llista per evitar modificacions externes
    }

    @Override
    public ArrayList<Space> getSpaces() {
        ArrayList<Space> list = new ArrayList<>();
        list.add(this);
        return list; //retornem una llista amb aquest espai
    }

    @Override
    public Area findAreaById(String id) {
        return this.id.equals(id) ? this : null; //retornem l'espai si l'id coincideix, sino null
    }
}
