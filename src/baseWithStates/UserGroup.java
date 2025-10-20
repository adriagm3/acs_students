package baseWithStates;

import java.time.*;
import java.util.Set;

public class UserGroup {
    private final String name;
    private final Set<String> allowedActions;
    private final Set<String> allowedSpaces;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Set<DayOfWeek> allowedWeekdays;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public UserGroup(String name,
                     Set<String> allowedActions,
                     Set<String> allowedSpaces,
                     LocalDate startDate,
                     LocalDate endDate,
                     Set<DayOfWeek> allowedWeekdays,
                     LocalTime startTime,
                     LocalTime endTime) {
        this.name = name;
        this.allowedActions = allowedActions;
        this.allowedSpaces = allowedSpaces;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allowedWeekdays = allowedWeekdays;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public boolean canDoAction(String action) {
        return allowedActions.contains(action) || allowedActions.contains("ALL");
    }

    public boolean canBeInSpace(String spaceId) {
        return allowedSpaces.contains(spaceId) || allowedSpaces.contains("ALL");
    }

    public boolean canSendRequests(LocalDateTime now) {
        LocalDate date = now.toLocalDate();
        LocalTime time = now.toLocalTime();
        DayOfWeek day = now.getDayOfWeek();

        boolean dateOk = !date.isBefore(startDate) && !date.isAfter(endDate);
        boolean dayOk = allowedWeekdays.contains(day);
        boolean timeOk = !time.isBefore(startTime) && !time.isAfter(endTime);

        return dateOk && dayOk && timeOk;
    }
}
