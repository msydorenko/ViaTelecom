package ua.gmail.sydorenko.database.entity;

/**
 * @author M.Sydorenko
 */
public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return values()[--roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
