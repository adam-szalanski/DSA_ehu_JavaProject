import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import people.People;
import people.Person;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.io.FileInputStream;
import java.lang.System.*;
import java.util.Objects;


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
                private static String TEST_PERSON = "And02,Andrea,Fernandez,07-02-1991,female,Donostia,Donostia,Lizeo;Psicologia,,Sex and the City 2;Eat Pray Love;Percy Jackson & the Olympians: The Lightning Thief,G6101";
                private static String TEST_PERSON_ID = "And02";

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
                public void testGetPeople() throws ParseException {
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
                //TODO: Person tests
                @Nested
                @DisplayName("PEOPLE. Nested class for testing features of Person class")
                class PersonTest {
                        Person testPerson;

                        @Test
                        @Tag("createPerson")
                        @Order(1)
                        @DisplayName("Create a person for testing")
                        public void createTestPerson() throws ParseException{
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

                        @Test
                        @Tag("testCompareEqualPerson")
                        @Order(2)
                        @DisplayName("Test comparing the test person with itself")
                        public void compareEqualTestPerson() {
                                
                        }
                }
        }
        //TODO: nested class Relationships with nested class Relation and their tests

        //TODO: fileHandling tests

        //TODO: Menu tests

        @Nested
        @DisplayName("TEST. Nested class for testing features of Main Menu")
        class MenuTest{
                                     InputStream stdin = System.in;
                InputOutput inputOutput= new InputOutput();

                String input = "add 5";
                InputStream in = new ByteArrayInputStream(input.getBytes());
                System.setIn(in);

                assertEquals("add 5", inputOutput.getInput());

        }
}
