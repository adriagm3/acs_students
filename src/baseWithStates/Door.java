package baseWithStates;

import baseWithStates.doorstates.DoorState;
import baseWithStates.requests.RequestReader;
import org.json.JSONObject;
import baseWithStates.doorstates.Unlocked;


public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorState state;

  public Door(String id) {
    this.id = id;
    closed = true;//estat inicial
    this.state = new Unlocked(this);
  }
  public void setClosed(boolean tancar){
    closed = tancar;
  }

  public void setState(DoorState newState) {
    this.state = newState;
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        state.open();
        break;
      case Actions.CLOSE:
        state.close();
        break;
      case Actions.LOCK:
        state.lock();
        break;
      case Actions.UNLOCK:
        state.unlock();
        break;
      default:
        System.out.println("Unknown action: " + action);
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    return state.getName();
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}
