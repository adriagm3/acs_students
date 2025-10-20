
package baseWithStates;

import java.util.ArrayList;

public abstract class Area {

    protected String id;
    protected String name;
    protected Partition parent; //no per el moment

    public Area(String id, String name, Partition parent) {
        this.id = id;
        this.name = name;
        this.parent = parent; //no per el moment
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract ArrayList<Door> getDoorsGivingAccess();
    public abstract ArrayList<Space> getSpaces();
    public abstract Area findAreaById(String id);
}
