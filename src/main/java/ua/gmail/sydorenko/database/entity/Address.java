package ua.gmail.sydorenko.database.entity;

import java.util.Objects;

/**
 * Entity of address.
 *
 * @author M.Sydorenko
 */
public class Address extends Entity {
    private static final long serialVersionUID = -3526149612865998479L;
    private String country;
    private String city;
    private String street;
    private int house;
    private int flat;

    public Address() {
    }

    public Address(int id) {
        super(id);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return house == address.house &&
                flat == address.flat &&
                Objects.equals(country, address.country) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        int result = 17;
        return 37 * result + country.hashCode() + city.hashCode() + street.hashCode()
                + house + flat;
    }

    /**
     * String representation if address object.
     *
     * @return address information.
     */
    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", flat=" + flat +
                '}';
    }
}
