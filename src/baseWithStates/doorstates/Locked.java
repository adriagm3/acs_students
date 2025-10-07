package baseWithStates.doorstates;

import baseWithStates.Door;

public class Locked extends DoorState {

  public Locked(Door door) {
    super(door);
    this.name = States.LOCKED;
  }

  @Override
  public void unlock() {
    door.setState(new Unlocked(door));
    System.out.println("Door " + door.getId() + " unlocked.");
  }

  @Override
  public void open() {
    System.out.println("Cannot open door " + door.getId() + " because it's locked.");
  }

  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      System.out.println("Door " + door.getId() + " closed (but remains locked).");
    } else {
      System.out.println("Door " + door.getId() + " is already closed.");
    }
  }
}
