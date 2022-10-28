package people;

import fileHandling.FileHandling;
import relationships.Relationship;
import relationships.Relationships;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public final class People {
    public final static String FIRST_LINE_PEOPLE = "idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode";
    private final static String FILENAME = "people";

    /**
     * Constructor for a People class.
     */
    public People() {
        this.people = new ArrayList<>();
    }

    private List<Person> people;

    /**
     * Getter for a people field.
     * @return list of Person type objects.
     */
    public List<Person> getPeople() {
        return people;
    }

    /**
     * Setter for a people field.
     * @param people is list of Person type objects.
     */
    public void setPeople(List<Person> people) {
        this.people = people;
    }

    /**
     * This method check if the user ID is already in list of people,
     * if not - add person from string to list of people
     * @param data is string containing data for creating a new object of Person class.
     * @throws ParseException Signals that an error has been reached unexpectedly while parsing.
     */
    public void addPersonFromString(String data) throws ParseException {
        String idPerson;
        String name;
        String lastname;
        Date birthdate;
        char gender;
        String birthplace;
        String home;
        List<String> studiedAt;
        List<String> workplaces;
        List<String> films;
        String groupCode;

        String[] splited = data.split(",");

        if(Objects.equals(splited[0], "\n") || Objects.equals(splited[0], "\r"))
            return;

        idPerson = splited[0];
        if(!this.people.isEmpty())
            for(Person p : people)
                if(p.getIdPerson().compareTo(idPerson)==0)
                    return;

        name = splited[1];
        lastname = splited[2];
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        birthdate = formatter.parse(splited[3]);
        gender = splited[4].charAt(0);
        birthplace = splited[5];
        home = splited[6];
        String[] splitedStuddied = splited[7].split(";");
        studiedAt = Arrays.stream(splitedStuddied).toList();
        String[] splitedWorkplaces = splited[8].split(";");
        workplaces = Arrays.stream(splitedWorkplaces).toList();
        String[] splitedFilms = splited[9].split(";");
        films = Arrays.stream(splitedFilms).toList();
        groupCode = splited[10];

        Person newPerson = new Person(idPerson,name,lastname,birthdate,gender,birthplace,home, studiedAt,workplaces,films,groupCode);
        people.add(newPerson);
    }

    /**
     * This method finds Person object with corresponding ID in people list.
     * @param id string
     * @return Person object
     */
    public Person findPersonById(String id){
        if(!this.people.isEmpty())
            for(Person p : people)
                if(p.getIdPerson().compareTo(id)==0)
                    return p;
        return null;
    }

    /**
     * This method finds List of Person objects with corresponding surname in people list.
     * @param surname string
     * @return List<Person> object
     */
    public List<Person> findPersonBySurname(String surname){
        List<Person> found = new ArrayList<>();
        if(!this.people.isEmpty())
            for(Person p : people)
                if(p.getLastname().compareTo(surname)==0)
                    found.add(p);
        return found;
    }

    /**
     * This method prints relationships of people with a given lastname
     * @param lastname String
     * @param relationships Relationships
     */
    public void printRelationshipsByLastname(String lastname, Relationships relationships){
        List<Person> persons = findPersonBySurname(lastname);
        if(!persons.isEmpty()){
            for(Person p : persons){
                List<Relationship> relations = relationships.findRelationshipsById(p.getIdPerson());
                System.out.printf("Relationships of %s %s:%n",p.getLastname(),p.getName());
                for(Relationship r : relations){
                    if (r.getFriend1()==p)
                        System.out.printf("%s %s%n",r.getFriend2().getName(),r.getFriend2().getLastname());
                    else
                        System.out.printf("%s %s%n",r.getFriend1().getName(),r.getFriend1().getLastname());
                }
            }
        }else{
            System.out.println("Person of that lastname wasn't found");
        }
    }
    /**
     * This method writes string to given file name.
     * @param filename string
     * @throws IOException This throws could occur when writing to a file
     */
    public void writeToFile(String filename) throws IOException {
        String output = FIRST_LINE_PEOPLE +"\n";
        for(Person p : people)
            output+=p.toString()+"\n";
        output.substring(0,output.length()-1);
        if(filename!=null)
            FileHandling.writeToFile(output,filename);
        else
            FileHandling.writeToFile(output,FILENAME);
    }

    /**
     * This method removes person with a certain id from the list of people
     * If person with that id does not exist, prints out a message
     * @param id String
     */
    public void removePersonById(String id){
        Person removedPerson = findPersonById(id);
        if(removedPerson==null)
            System.out.println("Person with that id does not exist");
        else {
            people.remove(removedPerson);
            System.out.println("Successfully removed person");
        }
    }
}
