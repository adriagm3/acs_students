package baseWithStates.doorstates;

import baseWithStates.Door;
import java.util.Timer;
import java.util.TimerTask;

public class UnlockedShortly extends DoorState {
    private static final int DELAY_MS = 10000; // 10 segons

    public UnlockedShortly(Door door) {
        super(door);
        scheduleReturnToLockedOrPropped();
    }

    @Override
    public String getName() {
        return "unlocked_shortly"; // tal com diu la slide 15
    }

    private void scheduleReturnToLockedOrPropped() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (door.isClosed()) {
                    door.setState(new Locked(door));
                } else {
                    door.setState(new Propped(door));
                }
            }
        }, DELAY_MS);
    }
}
