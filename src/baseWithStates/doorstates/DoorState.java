package baseWithStates.doorstates;

import baseWithStates.Door;
import baseWithStates.doorstates.Unlocked;

public abstract class DoorState {
  protected Door door;  // referència a la porta
  protected String name;

  public DoorState(Door door) {
    this.door = door;
  }

    public String getName() {
    return name;
  }

  public void open() {
    System.out.println("Action OPEN not allowed in state " + name);
  }

  public void close() {
    System.out.println("Action CLOSE not allowed in state " + name);
  }

  public void lock() {
    System.out.println("Action LOCK not allowed in state " + name);
  }

  public void unlock() {
    System.out.println("Action UNLOCK not allowed in state " + name);
  }
}
