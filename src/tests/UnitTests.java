import fileHandling.FileHandling;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import people.People;
import people.Person;
import relationships.Relationship;
import relationships.Relationships;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MAIN CLASS for testing the project")
public class UnitTests {

        @AfterEach
        public void done() {
                System.out.println("done!");
        }

        @Nested
        @DisplayName("MENU")
        class MenuTests {

                @Test
                @DisplayName("Testing menu() method with valid and not valid data")
                public void MenuTest() {
                        String input = "1";
                        InputStream in = new ByteArrayInputStream(input.getBytes());
                        System.setIn(in);
                        assertEquals(1, Menu.menu());

                        input = "dasdas";
                        in = new ByteArrayInputStream(input.getBytes());
                        System.setIn(in);
                        assertEquals(0, Menu.menu());
                }

                @Test
                @DisplayName("Testing if main() method works well, menu in it is tested in other tests")
                public void MainTest( ){
                        String input = "0";
                        InputStream in = new ByteArrayInputStream(input.getBytes());
                        System.setIn(in);
                        Menu.main(new String[0]);
                }
        }
        @Nested
        @DisplayName("MENU")
        class FileHandlingTest {
                @Test
                @DisplayName("Testing reading from file handling")
                public void ReadTest() throws FileNotFoundException {

                        File file = new File("aaa.txt");
                        List<String> linesFromFile = new ArrayList<>();
                        Scanner input2program = new Scanner(file,"windows-1250");
                        String data = input2program.nextLine();

                        while (input2program.hasNextLine()){
                                data = input2program.nextLine();
                                linesFromFile.add(data);
                        }
                        input2program.close();

                        assertEquals(linesFromFile, FileHandling.readFile("aaa.txt"));
                }

                @Test
                @DisplayName("Testing not existing file handling")
                public void File() {
                        Throwable exception = assertThrows(FileNotFoundException.class, () -> FileHandling.readFile("bbb.txt"));
                        assertEquals("Such file does not exist", exception.getMessage());
                }

                
                @Test
                @DisplayName("Testing write to file handling for different files")
                public void WriteTest() throws IOException {

                        File file = new File("df_friends_55L136.txt");
                        File file2 = new File("testfile.txt");

                        Scanner input2program = new Scanner(file,"windows-1250");
                        String data;
                        String longer = "";

                        while (input2program.hasNextLine()){
                                data = input2program.nextLine();
                                longer = longer + data + "\n";
                        }
                        input2program.close();

                        FileHandling.writeToFile(longer, "testfile.txt");

                        assertEquals(FileHandling.readFile("testfile.txt"), FileHandling.readFile("df_friends_55L136.txt"));

                        file2.delete();
                }
                
                
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
                @Test
                @Tag("deleteExistingPersonById")
                @Order(5)
                @DisplayName("Find and delete person from the list of people")
                public void testDeleteExistingPerson() throws ParseException {
                        people.addPersonFromString(TEST_PERSON);
                        people.removePersonById(TEST_PERSON_ID);
                        assertEquals(0,people.getPeople().size());
                }
                @Test
                @Tag("deleteNonExistingPersonById")
                @Order(6)
                @DisplayName("Find and delete person from the list of people")
                public void testDeleteNonexistingPerson() throws ParseException {
                        people.addPersonFromString(TEST_PERSON);
                        people.removePersonById("just a random unexisting id");
                        assertEquals(1,people.getPeople().size());
                }
        }

        @Nested
        @DisplayName("PERSON. Nested class for testing features of Person class")
        class PersonTest {
                private static Person testPerson;

                @BeforeAll
                @DisplayName("Create person for testing")
                public static void initTestPerson() throws ParseException{
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
                public static void clearTestPerson(){
                        testPerson=null;
                }

                @Test
                @Tag("testCompareEqualPerson")
                @Order(2)
                @DisplayName("Test comparing the test person having same data")
                public void testCompareEqualTestPerson() throws ParseException{
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
                public void testCompareUnequalTestPerson() throws ParseException{
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
                public static void initTestRelationships() throws ParseException{
                        testPeople = new People();
                        testPeople.addPersonFromString(TEST_PERSON);
                        testPeople.addPersonFromString(TEST_PERSON2);
                        testRelationships = new Relationships();
                }

                @AfterAll
                @DisplayName("Deleting relationships used for testing")
                public static void clearTestRelationships(){
                        testPeople = null;
                        testRelationships = null;
                }

                @Test
                @Tag("addRelationship")
                @Order(1)
                @DisplayName("Adding a relationship")
                public void testAddRelationship(){
                        testRelationships.addRelationship(TEST_RELATIONSHIP,testPeople);
                        assertFalse(testRelationships.getRelations().isEmpty());
                }

                @Test
                @Tag("writeRelationships")
                @Order(2)
                @DisplayName("Writing relationships as string")
                public void testToString(){
                        testRelationships.addRelationship(TEST_RELATIONSHIP,testPeople);
                        assertEquals((Relationships.FIRST_LINE_RELATIONSHIPS + TEST_RELATIONSHIP), testRelationships.toString());
                }


                @Nested
                @Order(5)
                @Description("RELATION. Nested class for testing Relation features")
                class RelationTest{
                        public static Relationship testRelationship;

                        @BeforeAll
                        @Description("Create test relationship")
                        public static void initTestRelationship(){
                                testRelationship = RelationshipsTest.testRelationships.getRelations().get(0);
                        }

                        @AfterAll
                        @Description("Remove test relationship")
                        public static void clearTestRelationship(){
                                testRelationship = null;
                        }

                        @Test
                        @Tag("toString")
                        @Order(1)
                        @DisplayName("Check if toString is formatting correctly")
                        public void testToString(){
                                assertEquals(RelationshipsTest.TEST_RELATIONSHIP,testRelationship.toString());
                        }

                        @Test
                        @Tag("equals")
                        @Order(2)
                        @DisplayName("Check if equals is comparing properly")
                        public void testEquals(){
                                String[] ids = TEST_RELATIONSHIP.split(",");
                                assertEquals(testRelationship, new Relationship(testPeople.findPersonById(ids[0]), testPeople.findPersonById(ids[1])));
                        }
                }
        }
}