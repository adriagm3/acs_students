package baseWithStates.doorstates;

import baseWithStates.Actions;
import baseWithStates.Door;

public class Unlocked extends DoorState {

  public Unlocked(Door door) {
    super(door);
    this.name = States.UNLOCKED;
  }

  @Override
  public void open() {
    if (door.isClosed()) {
      door.setClosed(false);
      System.out.println("Door " + door.getId() + " opened.");
    } else {
      System.out.println("Door " + door.getId() + " is already open.");
    }
  }

  @Override
  public void close() {
    if (!door.isClosed()) {
      door.setClosed(true);
      System.out.println("Door " + door.getId() + " closed.");
    } else {
      System.out.println("Door " + door.getId() + " is already closed.");
    }
  }

  @Override
  public void lock() {
    if (door.isClosed()) {
      door.setState(new Locked(door));
      System.out.println("Door " + door.getId() + " locked.");
    } else {
      System.out.println("Cannot lock door " + door.getId() + " while it's open.");
    }
  }
}
