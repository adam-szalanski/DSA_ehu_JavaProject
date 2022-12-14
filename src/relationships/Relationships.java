package relationships;

import fileHandling.FileHandling;
import people.People;
import people.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a relationships in the network
 * Consists of list of relations and graph representing the network of relations
 * @field  relations is a List of objects of type Relationship
 * @field  graph is an object of type Graph, it consists of nodes of people, and edges represents relationships
 */

public final class Relationships {
    public final static String FIRST_LINE_RELATIONSHIPS = "friend1,friend2\n";
    private final static String FILENAME = "friends";

    /**
     * Constructor for a Relationships class.
     */
    public Relationships() {
        this.relations = new ArrayList<>();
    }

    private List<Relationship> relations;

    public Graph graph;
    /**
     * Getter for a relations field.
     * @return list of Relationship objects
     */
    public List<Relationship> getRelations() { return relations; }

    /**
     * This method check if relationship for two people exists.
     * If not - create relationship between two people and add this
     * relationship to list of relationships.
     * @param list This parameter is string list which consists two user IDs.
     * @param ppl This parameter is People type object which have list of all users
     */
    public void addRelationship(String list, People ppl) {
        String[] splited = list.split(",");
        Person gosc1 = ppl.findPersonById(splited[0]);
        Person gosc2 = ppl.findPersonById(splited[1]);
        Relationship rel1 = new Relationship(gosc1, gosc2);
        if(gosc1 != null && gosc2!=null){
            if(this.relations.isEmpty()){
                this.relations.add(rel1);
                return;
            }
            for (Relationship r : this.relations) {
                if ( r.getFriend1().getIdPerson().equals(rel1.getFriend1().getIdPerson()) &&  r.getFriend2().getIdPerson().equals(rel1.getFriend2().getIdPerson())
                    || r.getFriend1().getIdPerson().equals(rel1.getFriend2().getIdPerson()) &&  r.getFriend2().getIdPerson().equals(rel1.getFriend1().getIdPerson())) {
                    return;
                }
            }
            this.relations.add(rel1);
        }

    }

    /**
     * This method overrides default method and returns a string representation of this object.
     * @return string
     */
    @Override
    public String toString() {
        String output = FIRST_LINE_RELATIONSHIPS;
        for (Relationship r : relations)
            output+=r.toString()+"\n";
        output=output.substring(0,output.length()-1);
        return output;
    }

    /**
     * This method writes string to given file name.
     * @param filename string
     * @throws IOException This throws could occur when writing to a file
     */
    public void writeToFile(String filename) throws IOException {
        String output = this.toString();
        if(filename!=null)
            FileHandling.writeToFile(output,filename);
        else
            FileHandling.writeToFile(output,FILENAME);
    }

    /**
     * This method finds every relationship containing passed id
     * @param id String
     * @return List<Relationship> List of relationships containing that id
     */
    public List<Relationship> findRelationshipsById(String id){
        List<Relationship> found = new ArrayList<>();
        for(Relationship r : this.relations){
            if (r.getFriend1().getIdPerson().equals(id)||r.getFriend2().getIdPerson().equals(id))
                found.add(r);
        }
        return found;
    }

    /**
     * Removes passed relationship from the list of relationships
     * @param r Relation
     */
    public void deleteRelationship(Relationship r){
        relations.remove(r);
    }

    /**
     * This method finds relationship containing passed IDs
     * @param id1 String
     * @param id2 String
     * @return Relationship containing passed IDs
     */
    public Relationship findRelationshipsByIDs(String id1, String id2) {
        Relationship found = null;
        for(Relationship r : this.relations){
            if (r.getFriend1().getIdPerson().equals(id1)&&r.getFriend2().getIdPerson().equals(id2)||r.getFriend1().getIdPerson().equals(id2)&&r.getFriend2().getIdPerson().equals(id1))
                found=r;
        }
        return found;

    }
    /**
     * This method prints out every relationship stored in the program
     */
    public void printAllRelationships() {
        String line = String.format("%n%-30.30s %-30.30s%n","Friend 1 ID","Friend 2 ID");
        System.out.printf("%s%s",line,People.dashLineFormatter(line));
        for (Relationship r : relations){
            System.out.printf("%-30.30s %-30.30s%n",r.getFriend1().getIdPerson(),r.getFriend2().getIdPerson());
        }
    }

    /**
     * Method that for every relationship calls for function that adds edges and nodes to graph
     */
    public void createGraph() {
        graph = new Graph();
        for (Relationship rel: relations) {
            graph.addEdge(rel.getFriend1(), rel.getFriend2());
        }
    }

    /**
     * Calls for function that prints graph
     */
    public void printGraph() {
        String result = graph.printGraph();
        System.out.println(result);
    }

    /**
     * Calls for creating the graph and finding shortest path in graph between two people under 6 graph nodes
     * @param id1 String id of first person
     * @param id2 String id of second person
     */
    public void shortestPath(String id1, String id2){
        createGraph();
        String result = graph.shortestDistance(6, id1, id2);
        System.out.println(result);
    }


    /**
     * Calls for creating the graph and finding longest path in graph between two people
     * @param id1 String id of first person
     * @param id2 String id of second person
     */
    public void longestPath(String id1, String id2){
        createGraph();
        String result = graph.longestDistance(id1, id2);
        System.out.println(result);
    }


    /**
     * Calls for creating the graph and printing cliques of people
     * clique of people is a group of least 5 people where everyone has relationship with everyone
     */
    public void cliqueOfPeople() {
        createGraph();
        String result = graph.cliquesOfFriends();
        System.out.println(result);
    }
}
