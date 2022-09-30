package people;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Person {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getIdPerson().equals(person.getIdPerson());
    }

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

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public List<String> getStudiedAt() {
        return studiedAt;
    }

    public void setStudiedAt(List<String> studiedAt) {
        this.studiedAt = studiedAt;
    }

    public List<String> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(List<String> workplaces) {
        this.workplaces = workplaces;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Override
    public String toString() {
//        return "Person{" +
//                "idPerson=" + idPerson +
//                ", name='" + name + '\'' +
//                ", lastname='" + lastname + '\'' +
//                ", birthdate=" + birthdate +
//                ", gender=" + gender +
//                ", birthplace='" + birthplace + '\'' +
//                ", home='" + home + '\'' +
//                ", studiedAt=" + studiedAt +
//                ", workplaces=" + workplaces +
//                ", films=" + films +
//                ", groupCode=" + groupCode +
//                '}';
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
