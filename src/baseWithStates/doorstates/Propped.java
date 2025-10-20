package baseWithStates.doorstates;

import baseWithStates.Door;

public class Propped extends DoorState {

    public Propped(Door door) {
        super(door);
    }

    @Override
    public String getName() {
        return "propped";
    }
}
