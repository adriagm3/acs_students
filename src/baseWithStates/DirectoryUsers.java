package baseWithStates;

import java.time.*;
import java.util.*;

public final class DirectoryUsers {
    private static final ArrayList<User> users = new ArrayList<>();

    public static void makeUsers() {
        int currentYear = LocalDate.now().getYear();

        // ─── GRUPS ─────────────────────────────────────────────

        UserGroup noPrivileges = new UserGroup(
                "NoPrivileges",
                Set.of(),                // cap acció
                Set.of(),                // cap espai
                LocalDate.of(currentYear, 1, 1),
                LocalDate.of(currentYear, 12, 31),
                Set.of(DayOfWeek.values()),
                LocalTime.MIN,
                LocalTime.MAX
        );

        UserGroup employees = new UserGroup(
                "Employees",
                Set.of("UNLOCK_SHORTLY"),
                Set.of("ground", "floor1", "exterior", "stairs"),
                LocalDate.of(currentYear, 9, 1),
                LocalDate.of(currentYear + 1, 3, 1),
                Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY),
                LocalTime.of(9, 0),
                LocalTime.of(17, 0)
        );

        UserGroup managers = new UserGroup(
                "Managers",
                Set.of("ALL"),
                Set.of("ALL"),
                LocalDate.of(currentYear, 9, 1),
                LocalDate.of(currentYear + 1, 3, 1),
                Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY),
                LocalTime.of(8, 0),
                LocalTime.of(20, 0)
        );

        UserGroup admin = new UserGroup(
                "Admin",
                Set.of("ALL"),
                Set.of("ALL"),
                LocalDate.of(currentYear, 1, 1),
                LocalDate.of(2100, 1, 1),
                Set.of(DayOfWeek.values()),
                LocalTime.MIN,
                LocalTime.MAX
        );

        // ─── USUARIS ───────────────────────────────────────────

        users.add(new User("Bernat", "12345", noPrivileges));
        users.add(new User("Blai", "77532", noPrivileges));

        users.add(new User("Ernest", "74984", employees));
        users.add(new User("Eulalia", "43295", employees));

        users.add(new User("Manel", "95783", managers));
        users.add(new User("Marta", "05827", managers));

        users.add(new User("Ana", "11343", admin));
    }

    public static User findUserByCredential(String credential) {
        for (User user : users) {
            if (user.getCredential().equals(credential)) {
                return user;
            }
        }
        System.out.println("user with credential " + credential + " not found");
        return null;
    }
}
