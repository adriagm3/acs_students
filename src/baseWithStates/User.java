package baseWithStates;

public class User {
    private final String name;
    private final String credential;
    private final UserGroup group;

    public User(String name, String credential, UserGroup group) {
        this.name = name;
        this.credential = credential;
        this.group = group;
    }

    public String getCredential() {
        return credential;
    }

    public UserGroup getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "User{name=" + name + ", credential=" + credential + ", group=" + group.getName() + "}";
    }
}
