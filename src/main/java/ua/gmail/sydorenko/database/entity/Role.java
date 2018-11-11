package ua.gmail.sydorenko.database.entity;

public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return values()[--roleId];
    }

    public String getName(){
        return name().toLowerCase();
    }
}
