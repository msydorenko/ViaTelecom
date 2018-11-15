package ua.gmail.sydorenko.database.entity;

import java.util.Objects;

/**
 * @author M.Sydorenko
 */
public class Service extends Entity {
    private static final long serialVersionUID = 1295689L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service contact = (Service) o;
        return Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        return 37 * result + (name.hashCode());
    }
}
