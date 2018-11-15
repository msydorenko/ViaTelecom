package ua.gmail.sydorenko.database.entity;

import java.util.Objects;

/**
 * @author M.Sydorenko
 */
public class Bill extends Entity {
    private static final long serialVersionUID = 1909333L;
    private String number;
    private int value;

    public Bill() {
    }

    public Bill(int id) {
        super(id);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Bill)) return false;
        Bill bill = (Bill) o;
        return Objects.equals(number, bill.number);
    }

    @Override
    public int hashCode() {
        int result = 17;
        return 37 * result + number.hashCode();
    }

    @Override
    public String toString() {
        return "Bill{" +
                "number='" + number + '\'' +
                ", value=" + value +
                '}';
    }
}
