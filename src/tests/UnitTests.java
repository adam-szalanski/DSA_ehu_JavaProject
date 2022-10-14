import org.junit.jupiter.api.*;
import people.People;
import people.Person;
import relationships.Relationships;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MAIN CLASS for testing the project")
public class UnitTests {

        @AfterEach
        public void done() {
                System.out.println("done!");
        }

        @Nested
        @DisplayName("PEOPLE. Nested class for testing features of People class")
        class PeopleTest{
                private static People people;
                private static final String TEST_PERSON = "And02,Andrea,Fernandez,07-02-1991,f,Donostia,Donostia,Lizeo;Psicologia,,Sex and the City 2;Eat Pray Love;Percy Jackson & the Olympians: The Lightning Thief,G6101";
                private static final String TEST_PERSON_ID = "And02";

                @BeforeAll
                @DisplayName("Creation of the People for Test")
                public static void initPeople() {
                        people = new People();
                }

                @AfterAll
                @DisplayName("Delete the People for Test")
                public static void clearPeople() {
                        people = null;
                }

                @Test
                @Tag("gettingList")
                @Order(1)
                @DisplayName("Getting a list of people")
                public void testGetPeople() {
                        assertInstanceOf(List.class,people.getPeople());
                }

                @Test
                @Tag("addingPerson")
                @Order(2)
                @DisplayName("Adding a person")
                public void testAddPerson() throws ParseException {
                        people.addPersonFromString(TEST_PERSON);
                        assertEquals(1,people.getPeople().size());
                }

                @Test
                @Tag("addingDuplicatePerson")
                @Order(3)
                @DisplayName("Adding a duplicate of person")
                public void testAddDuplicatePerson() throws ParseException {
                        people.addPersonFromString(TEST_PERSON);
                        assertEquals(1,people.getPeople().size());
                }
                @Test
                @Tag("findingPersonById")
                @Order(4)
                @DisplayName("Finding a person in the list of people by its id")
                public void testFindPerson() {
                        assertInstanceOf(Person.class, people.findPersonById(TEST_PERSON_ID));
                }

                }

        @Nested
        @DisplayName("PERSON. Nested class for testing features of Person class")
        class PersonTest {
                private static Person testPerson;

                @BeforeAll
                @DisplayName("Create person for testing")
                public static void createTestPerson() throws ParseException{
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

                        String[] splited = PeopleTest.TEST_PERSON.split(",");

                        idPerson = splited[0];
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

                        testPerson=new Person(idPerson,name,lastname,birthdate,gender,birthplace,home, studiedAt,workplaces,films,groupCode);
                        assertNotNull(testPerson);
                }

                @AfterAll
                @DisplayName("Delete testing person")
                public static void deleteTestPerson(){
                        testPerson=null;
                }

                @Test
                @Tag("testCompareEqualPerson")
                @Order(2)
                @DisplayName("Test comparing the test person having same data")
                public void compareEqualTestPerson() throws ParseException{
                        Person testPerson2;
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

                        String[] splited = PeopleTest.TEST_PERSON.split(",");

                        idPerson = splited[0];
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

                        testPerson2=new Person(idPerson,name,lastname,birthdate,gender,birthplace,home, studiedAt,workplaces,films,groupCode);
                        assertEquals(testPerson, testPerson2);
                }


                @Test
                @Tag("testCompareUnequalPerson")
                @Order(3)
                @DisplayName("Test comparing the test person with another different person")
                public void compareUnequalTestPerson() throws ParseException{
                        Person testPerson2;
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

                        String[] splited = PeopleTest.TEST_PERSON.split(",");

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

                        testPerson2=new Person("testID",name,lastname,birthdate,gender,birthplace,home, studiedAt,workplaces,films,groupCode);
                        assertNotEquals(testPerson, testPerson2);
                }

                @Test
                @Tag("testToString")
                @Order(4)
                @DisplayName("Test toString if the output is correctly formatted")
                public void testToString(){
                        assertEquals(PeopleTest.TEST_PERSON,testPerson.toString());
                }
        }

        @Nested
        @DisplayName("RELATIONSHIPS. Nested class for testing features of Relationships class")
        class RelationshipsTest{
                public static Relationships testRelationships;
                public static People testPeople;

                private static final String TEST_PERSON = "And02,Andrea,Fernandez,07-02-1991,f,Donostia,Donostia,Lizeo;Psicologia,,Sex and the City 2;Eat Pray Love;Percy Jackson & the Olympians: The Lightning Thief,G6101";
                private static final String TEST_PERSON2 = "And03,Andrea,Fernandez,07-02-1991,f,Donostia,Donostia,Lizeo;Psicologia,,Sex and the City 2;Eat Pray Love;Percy Jackson & the Olympians: The Lightning Thief,G6101";
                private static final String TEST_RELATIONSHIP = "And02,And03";

                @BeforeAll
                @DisplayName("Creating relationships for testing")
                public static void createTestRelationships() throws ParseException{
                        testPeople = new People();
                        testPeople.addPersonFromString(TEST_PERSON);
                        testPeople.addPersonFromString(TEST_PERSON2);
                        testRelationships = new Relationships();
                }
        }
}
        //TODO: nested class Relationships with nested class Relation and their tests

        //TODO: fileHandling tests

        //TODO: Menu tests

