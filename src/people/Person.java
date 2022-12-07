package people;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Person {

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
        Person person = (Person) o;
        return getIdPerson().equals(person.getIdPerson());
    }

    /**
     * This method overrides default method and returns the integer
     * hash code value of the object.
     * @return integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(getIdPerson());
    }

    private String idPerson;
    private String name;
    private String lastname;
    private Date birthdate;
    private char gender;
    private String birthplace;
    private String home;
    private List<String> studiedAt;
    private List<String> workplaces;
    private List<String> films;
    private String groupCode;

    /**
     * All-argument constructor for a Person class.
     * @param idPerson String contains user ID
     * @param name String contains username
     * @param lastname String contains lastname
     * @param birthdate Date contains birthdate
     * @param gender char contains 'M' as 'male' / 'F' as 'female'
     * @param birthplace String contains birthplace
     * @param home String contains name of hometown
     * @param studiedAt list of Strings contains name of university
     * @param workplaces list of Strings contains workplaces
     * @param films list of Strings contains favourites movie titles
     * @param groupCode String contains group code
     */
    public Person(String idPerson, String name, String lastname, Date birthdate, char gender, String birthplace, String home, List<String> studiedAt, List<String> workplaces, List<String> films, String groupCode) {
        this.idPerson = idPerson;
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.birthplace = birthplace;
        this.home = home;
        this.studiedAt = studiedAt;
        this.workplaces = workplaces;
        this.films = films;
        this.groupCode = groupCode;
    }

    /**
     * Getter for a idPerson field.
     * @return String type object.
     */
    public String getIdPerson() {
        return this.idPerson;
    }

    /**
     * Setter for a idPerson field.
     * @param idPerson is a String type object.
     */
    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    /**
     * Getter for a name field.
     * @return String type object.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for a name field.
     * @param name is a String type object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for a lastname field.
     * @return String type object.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Setter for a lastname field.
     * @param lastname is a String type object.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Getter for a birthdate field.
     * @return Date type object.
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * Setter for a birthdate field.
     * @param birthdate is a Date type object.
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Getter for a gender field.
     * @return char type object.
     */
    public char getGender() {
        return gender;
    }

    /**
     * Setter for a gender field.
     * @param gender is a char type object.
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Getter for a birthplace field.
     * @return String type object.
     */
    public String getBirthplace() {
        return birthplace;
    }

    /**
     * Setter for a birthplace field.
     * @param birthplace is a String type object.
     */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    /**
     * Getter for a getHome field.
     * @return String type object.
     */
    public String getHome() {
        return home;
    }

    /**
     * Setter for a home field.
     * @param home is a String type object.
     */
    public void setHome(String home) {
        this.home = home;
    }

    /**
     * Getter for a studiedAt field.
     * @return List of String type objects.
     */
    public List<String> getStudiedAt() {
        return studiedAt;
    }

    /**
     * Setter for a studiedAt field.
     * @param studiedAt is a list of String type object.
     */
    public void setStudiedAt(List<String> studiedAt) {
        this.studiedAt = studiedAt;
    }

    /**
     * Getter for a workplaces field.
     * @return List of String type objects.
     */
    public List<String> getWorkplaces() {
        return workplaces;
    }

    /**
     * Setter for a workplaces field.
     * @param workplaces is a list of String type object.
     */
    public void setWorkplaces(List<String> workplaces) {
        this.workplaces = workplaces;
    }

    /**
     * Getter for a films field.
     * @return List of String type objects.
     */
    public List<String> getFilms() {
        return films;
    }

    /**
     * Setter for a films field.
     * @param films is a list of String type object.
     */
    public void setFilms(List<String> films) {
        this.films = films;
    }

    /**
     * Getter for a groupCode field.
     * @return String type object.
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * Setter for a groupCode field.
     * @param groupCode is a String type object.
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * This method overrides default method and returns a string representation of this object.
     * @return string
     */
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String output = idPerson+','
                +name+","
                +lastname+","
                +formatter.format(birthdate)+","
                +gender+","
                +birthplace+","
                +home+",";

        String studied="";
        for(String str : studiedAt)
            studied+=str+";";
        studied = studied.substring(0,studied.length()-1);
        output+=studied+",";

        String work="";
        for(String str : workplaces)
            work+=str+";";
        work = work.substring(0,work.length()-1);
        output+=work+",";

        String filmy="";
        for(String str : films)
            filmy+=str+";";
        filmy = filmy.substring(0,filmy.length()-1);
        output+=filmy+",";

        output+=groupCode;
        return output;
    }


}
