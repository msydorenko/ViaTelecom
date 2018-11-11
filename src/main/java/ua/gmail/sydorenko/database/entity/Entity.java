package ua.gmail.sydorenko.database.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 9378269L;
    private int id;

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
