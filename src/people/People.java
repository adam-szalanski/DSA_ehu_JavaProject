package people;

import fileHandling.FileHandling;
import relationships.Relationship;
import relationships.Relationships;

import java.io.IOException;
import java.lang.invoke.CallSite;
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
     *
     * @return list of Person type objects.
     */
    public List<Person> getPeople() {
        return people;
    }

    /**
     * Setter for a people field.
     *
     * @param people is list of Person type objects.
     */
    public void setPeople(List<Person> people) {
        this.people = people;
    }

    /**
     * This method prints out the list of all the people loaded to the program.
     */
    public void printAllPeople() {
        String line = String.format("%n%-30.30s %-30.30s %-30.30s %-30.30s %-30.30s %-30.30s %-30.30s %-60.60s %-60.60s %-150.150s %-30.30s%n"
                , "Id", "Name", "Lastname", "Birthdate", "Gender", "Birth Place", "Home", "Studied At", "Workplaces", "Favourite Films", "Group Code");
        System.out.printf("%s%s", line, dashLineFormatter(line));
        for (Person p : people) {
            String studied = "";
            for (String str : p.getStudiedAt())
                studied += str + ", ";
            studied = studied.substring(0, studied.length() - 2);

            String work = "";
            for (String str : p.getWorkplaces())
                work += str + ", ";
            work = work.substring(0, work.length() - 2);

            String film = "";
            for (String str : p.getFilms())
                film += str + ", ";
            film = film.substring(0, film.length() - 2);

            String date = String.format("%1$td %1$tB %1$tY", p.getBirthdate());

            System.out.printf("%-30.30s %-30.30s %-30.30s %-30.30s %-30.30s %-30.30s %-30.30s %-60.60s %-60.60s %-150.150s %-30.30s%n",
                    p.getIdPerson(),
                    p.getName(),
                    p.getLastname(),
                    date,
                    p.getGender(),
                    p.getBirthplace(),
                    p.getHome(),
                    studied,
                    work,
                    film,
                    p.getGroupCode());
        }
    }

    /**
     * This method check if the user ID is already in list of people,
     * if not - add person from string to list of people
     *
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

        if (Objects.equals(splited[0], "\n") || Objects.equals(splited[0], "\r"))
            return;

        idPerson = splited[0];
        if (!this.people.isEmpty())
            for (Person p : people)
                if (p.getIdPerson().compareTo(idPerson) == 0)
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

        Person newPerson = new Person(idPerson, name, lastname, birthdate, gender, birthplace, home, studiedAt, workplaces, films, groupCode);
        people.add(newPerson);
    }

    /**
     * This method finds Person object with corresponding ID in people list.
     *
     * @param id string
     * @return Person object
     */
    public Person findPersonById(String id) {
        if (!this.people.isEmpty())
            for (Person p : people)
                if (p.getIdPerson().compareTo(id) == 0)
                    return p;
        return null;
    }

    /**
     * This method finds List of Person objects with corresponding surname in people list.
     *
     * @param surname string
     * @return List<Person> object
     */
    public List<Person> findPersonBySurname(String surname) {
        List<Person> found = new ArrayList<>();
        if (!this.people.isEmpty())
            for (Person p : people)
                if (p.getLastname().compareTo(surname) == 0)
                    found.add(p);
        return found;
    }

    /**
     * This method prints relationships of people with a given lastname
     *
     * @param lastname      String
     * @param relationships Relationships
     */
    public void printRelationshipsByLastname(String lastname, Relationships relationships) {
        List<Person> persons = findPersonBySurname(lastname);
        if (!persons.isEmpty()) {
            for (Person p : persons) {
                List<Relationship> relations = relationships.findRelationshipsById(p.getIdPerson());
                System.out.printf("Relationships of %s %s:%n", p.getLastname(), p.getName());
                String line = String.format("%-30.30s %-30.30s", "surname", "name");
                System.out.printf("%s%s", line, dashLineFormatter(line));
                for (Relationship r : relations) {
                    if (r.getFriend1() == p)
                        System.out.printf("%-30.30s %-30.30s%n", r.getFriend2().getName(), r.getFriend2().getLastname());
                    else
                        System.out.printf("%-30.30s %-30.30s%n", r.getFriend1().getName(), r.getFriend1().getLastname());
                }
            }
        } else {
            System.out.println("Person of that lastname wasn't found");
        }
    }

    /**
     * This method finds a list of people with the passed hometown
     *
     * @param home String
     * @return List<Person> list of found people
     */
    public List<Person> findPeopleByHome(String home) {
        List<Person> found = new ArrayList<>();
        for (Person p : this.people) {
            if (p.getHome().equals(home))
                found.add(p);
        }
        return found;
    }

    /**
     * This method prints people with a given hometown
     *
     * @param home String
     */
    public void printPeopleByHome(String home) {
        List<Person> found = findPeopleByHome(home);
        if (!found.isEmpty()) {
            System.out.printf("Found following people from %s:%n", home);
            String line = String.format("%-30.30s %-30.30s %-30.30s %-30.30s%n", "name", "surname", "birthplace", "studied at");
            System.out.printf("%s%s", line, dashLineFormatter(line));
            for (Person p : found) {
                String studied = "";
                for (String str : p.getStudiedAt())
                    studied += str + ", ";
                studied = studied.substring(0, studied.length() - 2);
                System.out.printf("%-30.30s %-30.30s %-30.30s %-30.30s%n", p.getName(), p.getLastname(), p.getBirthplace(), studied);
            }
        } else {
            System.out.printf("Found no person from %s%n", home);
        }
    }

    /**
     * This method finds a list of people with the passed birthplace
     *
     * @param birthplace String
     * @return List<Person> list of found people
     */
    public List<Person> findPeopleByBirthplace(String birthplace) {
        List<Person> found = new ArrayList<>();
        for (Person p : this.people) {
            if (p.getBirthplace().equals(birthplace))
                found.add(p);
        }
        return found;
    }

    /**
     * This method prints people with a given birthplace
     *
     * @param birthplace String
     */
    public void printPeopleByBirthplace(String birthplace) {
        List<Person> found = findPeopleByBirthplace(birthplace);
        if (!found.isEmpty()) {
            System.out.printf("Found following people born in %s:%n", birthplace);
            String line = String.format("%-30.30s %-30.30s%n", "ID", "surname");
            System.out.printf("%s%s", line, dashLineFormatter(line));
            for (Person p : found) {
                System.out.printf("%-30.30s %-30.30s%n", p.getIdPerson(), p.getLastname());
            }
        } else {
            System.out.printf("Found no person born in %s%n", birthplace);
        }
    }

    /**
     * This method finds a list of people born between passed years
     *
     * @param yearMin int
     * @param yearMax int
     * @return List<Person> list of found people
     */
    public List<Person> findPeopleBornBetween(int yearMin, int yearMax) {
        List<Person> found = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        try {
            Date startRange = sdf.parse("01-01-" + yearMin);
            Date endRange = sdf.parse("01-01-" + yearMax);
            for (Person p : this.people) {
                if (!(p.getBirthdate().before(startRange) || p.getBirthdate().after(endRange)))
                    found.add(p);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return found;
    }

    /**
     * This method prints people born between passed years
     *
     * @param yearMin int
     * @param yearMax int
     */
    public void printPeopleBornBetween(int yearMin, int yearMax) {
        List<Person> found = findPeopleBornBetween(yearMin, yearMax);
        if (!found.isEmpty()) {
            System.out.printf("Found following people born between years %d and %d:%n%n", yearMin, yearMax);
            String line = String.format("%-30.30s %-30.30s %-30.30s %-30.30s%n", "id", "surname", "name", "birthplace");
            System.out.printf("%s%s", line, dashLineFormatter(line));
            found.sort(Comparator.comparing(Person::getBirthplace)
                    .thenComparing(Person::getLastname)
                    .thenComparing(Person::getName));
            for (Person p : found) {
                System.out.printf("%-30.30s %-30.30s %-30.30s %-30.30s%n", p.getIdPerson(), p.getLastname(), p.getName(), p.getBirthplace());
            }
        } else {
            System.out.printf("Found no person born between years %d and %d:%n", yearMin, yearMax);
        }
    }

    /**
     * This method writes string to given file name.
     *
     * @param filename string
     * @throws IOException This throws could occur when writing to a file
     */
    public void writeToFile(String filename) throws IOException {
        String output = FIRST_LINE_PEOPLE + "\n";
        for (Person p : people)
            output += p.toString() + "\n";
        output.substring(0, output.length() - 1);
        if (filename != null)
            FileHandling.writeToFile(output, filename);
        else
            FileHandling.writeToFile(output, FILENAME);
    }

    /**
     * This method removes person with a certain id from the list of people
     * If person with that id does not exist, prints out a message
     *
     * @param id String
     */
    public void removePersonById(String id) {
        Person removedPerson = findPersonById(id);
        if (removedPerson == null)
            System.out.println("Person with that id does not exist");
        else {
            people.remove(removedPerson);
            System.out.println("Successfully removed person");
        }
    }

    /**
     * A little function that takes a line containing column names and returns
     * a line of dashes adjusted to the length of passed line
     * @param line String with column names
     * @return String of dashes
     */
    public static String dashLineFormatter(String line) {
        return "-".repeat(line.length())+"\n";
    }

    /**
     * A little function that takes a line containing column names and returns
     * a line of dashes adjusted to the length of passed line
     * @param line String with column names
     * @param symbol String to be used as a delimiter line
     * @return String of passed symbol
     */
    public static String anySymbolLineFormatter(String line, String symbol){
        return symbol.trim().repeat(line.length())+"\n";
    }

    /**
     * The function takes the name of the .txt file, reads the contents
     * of the file being the ID's of people and prints the lists of
     * people whose birthplace is equal to hometown of people from file
     * @param filename String - name of the file without extension
     * @throws IOException in case the file causes issues
     */
    public void findMatchesByFile(String filename) throws IOException {
        List<Person> listOfPeopleFromFile = new ArrayList<>();
        List<Person> listOfFoundPeopleFromFile;

        List<String> ids = FileHandling.readFile(filename);
        for (String id : ids) {
            listOfPeopleFromFile.add(findPersonById(id));
        }
        for (Person one : listOfPeopleFromFile) {
            listOfFoundPeopleFromFile = findPeopleByBirthplace(one.getHome());
            printMatchesByFile(listOfFoundPeopleFromFile, one.getIdPerson());
        }
    }

    /**
     * This function takes a list of people and id of a person and prints the formatted output
     * @param personList List<Person> list containing people whose data should be printed
     * @param id String ID of the person for whom the list was created
     */
    private void printMatchesByFile(List<Person> personList, String id) {
        if (!personList.isEmpty()) {
            System.out.printf("Found following people for person whose id is %s:%n", id);
            String line = String.format("%-30.30s %-30.30s %-30.30s %-30.30s%n", "name", "surname", "home town", "studied at");
            System.out.printf("%s%s", line, dashLineFormatter(line));
            for (Person p : personList) {
                String studied = "";
                for (String str : p.getStudiedAt())
                    studied += str + ", ";
                studied = studied.substring(0, studied.length() - 2);
                System.out.printf("%-30.30s %-30.30s %-30.30s %-30.30s%n", p.getName(), p.getLastname(), p.getHome(), studied);
            }
        } else {
            System.out.printf("Found no people for person whose id is %s%n", id);
        }
    }


}
