package ua.gmail.sydorenko.database.entity;

import java.util.Objects;

/**
 * @author M.Sydorenko
 */
public class Tariff extends Entity {
    private static final long serialVersionUID = 482045L;
    private int spr_service_id;
    private String name;
    private int price;
    private String description;

    public int getSpr_service_id() {
        return spr_service_id;
    }

    public void setSpr_service_id(int spr_service_id) {
        this.spr_service_id = spr_service_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Tariff)) {
            return false;
        }
        Tariff tariff = (Tariff) o;
        return Objects.equals(name, tariff.name) &&
                Objects.equals(price, tariff.price);
    }

    @Override
    public int hashCode() {
        int result = 17;
        return 37 * result + name.hashCode() + price;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
