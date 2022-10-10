import org.junit.jupiter.api.*;
import people.People;
import people.Person;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MAIN CLASS for testing the project")
public class unitTests {

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
                public void testgetPeople() throws ParseException {
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
                //TODO: Test for writeToFile()

                //TODO: nested class Person and its tests
        }
        //TODO: nested class Relationships with nested class Relation and their tests

        //TODO: fileHandling tests

        //TODO: Menu tests
}
