package relationships;

import people.Person;

import java.util.Objects;

public class Relationship {
    private Person friend1;
    private Person friend2;

    @Override
    public String toString() {
        return friend1.getIdPerson() +
                "," + friend2.getIdPerson();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relationship that = (Relationship) o;
        return getFriend1().equals(that.getFriend1()) && getFriend2().equals(that.getFriend2());// && getFriend1().equals(that.getFriend2()) && getFriend2().equals(that.getFriend1());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFriend1(), getFriend2());
    }

    public Relationship(Person friend1, Person friend2){
        this.friend1 = friend1;
        this.friend2 = friend2;
    }

    public Person getFriend1() {
        return friend1;
    }

    public void setFriend1(Person friend1) {
        this.friend1 = friend1;
    }

    public Person getFriend2() {
        return friend2;
    }

    public void setFriend2(Person friend2) {
        this.friend2 = friend2;
    }
}
