package baseWithStates;

import java.time.*;
import java.util.Set;

public class UserGroup {
    // Aquesta classe representa un grup d'usuaris amb permisos específics.
    // Defineix quines accions poden fer, a quins espais poden accedir i en quin horari.

    private final String name;               // Nom del grup
    private final Set<String> allowedActions; // Accions permeses (LOCK, UNLOCK, etc.)
    private final Set<String> allowedSpaces;  // Espais on poden accedir (ID d'espais)
    private final LocalDate startDate;        // Data d'inici de validesa del grup
    private final LocalDate endDate;          // Data de finalització
    private final Set<DayOfWeek> allowedWeekdays; // Dies de la setmana permesos
    private final LocalTime startTime;        // Hora d'inici permesa
    private final LocalTime endTime;          // Hora de finalització permesa

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
        // Retorna true si el grup pot realitzar aquesta acció
        // També permet qualsevol acció si es té l'entrada "ALL"
        return allowedActions.contains(action) || allowedActions.contains("ALL");
    }

    public boolean canBeInSpace(String spaceId) {
        // Retorna true si el grup pot estar en aquest espai
        // També permet qualsevol espai si es té l'entrada "ALL"
        return allowedSpaces.contains(spaceId) || allowedSpaces.contains("ALL");
    }

    public boolean canSendRequests(LocalDateTime now) {
        // Retorna true si l'usuari pot enviar requests en aquest moment segons data, dia i hora
        LocalDate date = now.toLocalDate();
        LocalTime time = now.toLocalTime();
        DayOfWeek day = now.getDayOfWeek();

        boolean dateOk = !date.isBefore(startDate) && !date.isAfter(endDate);
        boolean dayOk = allowedWeekdays.contains(day);
        boolean timeOk = !time.isBefore(startTime) && !time.isAfter(endTime);

        return dateOk && dayOk && timeOk;
    }
}
