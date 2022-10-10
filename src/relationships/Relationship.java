package relationships;

import people.Person;

import java.util.Objects;

public class Relationship {
    private Person friend1;
    private Person friend2;

    /**
     * This method returns a string representation of this object.
     * @return string
     */
    @Override
    public String toString() {
        return friend1.getIdPerson() +
                "," + friend2.getIdPerson();
    }

    /**
     * This method compares this object with object given in parameter.
     * It overrides default method and allows us to use '==' operator.
     * @param o is an object of undefined type
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relationship that = (Relationship) o;
        return getFriend1().equals(that.getFriend1()) && getFriend2().equals(that.getFriend2());// && getFriend1().equals(that.getFriend2()) && getFriend2().equals(that.getFriend1());
    }

    /**
     * This method overrides default method and returns the integer
     * hash code value of the object.
     * @return integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFriend1(), getFriend2());
    }

    /**
     * Constructor for a Relationships class.
     * @param friend1 is parameter of Person type.
     * @param friend2 is parameter of Person type.
     */
    public Relationship(Person friend1, Person friend2){
        this.friend1 = friend1;
        this.friend2 = friend2;
    }

    /**
     * Getter for a friend1 field.
     * @return object of Person type.
     */
    public Person getFriend1() {
        return friend1;
    }

    /**
     * Setter for a friend1 field.
     * @param friend1 is parameter of Person type.
     */
    public void setFriend1(Person friend1) {
        this.friend1 = friend1;
    }
    /**
     * Getter for a friend2 field.
     * @return object of Person type.
     */
    public Person getFriend2() {
        return friend2;
    }

    /**
     * Setter for a friend2 field.
     * @param friend2 is parameter of Person type.
     */
    public void setFriend2(Person friend2) {
        this.friend2 = friend2;
    }
    // Też ja zmieniający komentarze
}
