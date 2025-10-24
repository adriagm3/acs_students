package baseWithStates;

public class User {
    // Aquesta classe representa un usuari del sistema.
    // Cada usuari té un nom, una credencial única i pertany a un grup d'usuaris.

    private final String name;       // Nom de l'usuari
    private final String credential; // Credencial única per identificar-lo
    private final UserGroup group;   // Grup d'usuaris al qual pertany

    public User(String name, String credential, UserGroup group) {
        this.name = name;
        this.credential = credential;
        this.group = group;
    }

    public String getCredential() {
        return credential; // Retorna la credencial de l'usuari
    }

    public UserGroup getGroup() {
        return group; // Retorna el grup d'usuaris de l'usuari
    }

    @Override
    public String toString() {
        // Representació textual de l'usuari per debugging/logging
        return "User{name=" + name + ", credential=" + credential + ", group=" + group.getName() + "}";
    }
}
