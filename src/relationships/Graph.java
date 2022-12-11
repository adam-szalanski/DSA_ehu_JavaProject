package relationships;
import java.util.*;

import people.Person;

/**
 * This class implements a graph which represents the network. The nodes are the people in network, and edges are relationships between them
 * Consists of list of relations and graph representing the network of relations
 * @field graph is a Map with nodes and corresponding to them edges
 */
class Graph {

    private Map<Person, List<Person>> graph = new HashMap<>();

    /**
     * Constructor for a Graph class.
     */
    public Graph() {
        graph = new HashMap<>();
    }

    /**
     * This function adds an edge between two given nodes and calls for creating nodes if they doesn't exist
     * @param source String id of first person
     * @param destination String id of second person
     */
    public void addEdge(Person source, Person destination) {
        if (!graph.containsKey(source)) {
            addVertex(source);
        }

        if (!graph.containsKey(destination)) {
            addVertex(destination);
        }

        graph.get(source).add(destination);
        graph.get(destination).add(source);
    }

    /**
     * This function adds a Node for given person
     */
    private void addVertex(Person vertex) {
        graph.put(vertex, new LinkedList<Person>());
    }

    /**
     * Prints a graph as people and friends connected with them
     */
    public void printGraph() {
        StringBuilder builder = new StringBuilder();

        for(Person person : graph.keySet()) {
            builder.append(person.getIdPerson() + ": ");
            for(Person node: graph.get(person)) {
                builder.append(node.getIdPerson() + " ");
            }
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }

    /**
     * This function finds the shortest path in graph between two people under "s" graph nodes
     * @param s int, number of max nodes between searched people
     * @param origin String id of first person
     * @param dest String id of second person
     *
     * It creates a linked list to which it adds linked list with first person,
     * the next step is to add in a loop a new linked list with added new node, where node is the friend of last person in first linked list,
     * this step is repeated for every friend of the node and then the first linked list is removed - every linked list represents checked path.
     * The algorithm stops if the destination node is found under "s" steps and writes the path, and if the path is not found under 6 steps it informs about it.
     */
    public void shortestDistance(int s, String origin, String dest)
    {
        LinkedList<LinkedList<Person>> list = new LinkedList<>();

        for (Person person : graph.keySet()) {
            if(person.getIdPerson().equals(origin))  {
                LinkedList<Person> newlist = new LinkedList<>();
                newlist.add(person);
                list.add(newlist);
                break;
            }
        }

        while(!list.isEmpty()){
            s--;
            int size = list.get(0).size()-1;
            Person last = list.get(0).get(size);
            for (Person friend: graph.get(last)) {
                if(friend.getIdPerson().equals(dest)){
                    System.out.println("path from " + origin + " to " + dest + " under " + s + " steps:");
                    for (Person p: list.get(0)) {
                        System.out.println(p.getIdPerson());
                    }
                    System.out.println(friend.getIdPerson());
                    return;
                }

                if(!list.get(0).contains(friend))   {
                    LinkedList<Person> newlist = copy(list.get(0));
                    newlist.add(friend);
                    list.add(newlist);
                }
            }
            list.remove(0);
            if(s==0){
                System.out.println("there is no path under 6 steps");
                return;
            }
        }

    }

    /**
     * This function finds the longest path in graph between two people
     * @param origin String id of first person
     * @param dest String id of second person
     *
     * It creates a linked list to which it adds linked list with first person, and the second linked list that holds the longest path to destination
     * the next step is to - in a loop - check if the last person in first linked list is designated person, and if so update the longest path linked list
     * algorithm also adds a new linked list with added new node, where node is the friend of last person in first linked list,
     * this step is repeated for every friend of the node and then the first linked list is removed - every linked list represents checked path.
     */
    public void longestDistance(String origin, String dest)
    {
        LinkedList<LinkedList<Person>> list = new LinkedList<>();

        LinkedList<Person> longest = new LinkedList<>();

        for (Person person : graph.keySet()) {
            if(person.getIdPerson().equals(origin))  {
                LinkedList<Person> newlist = new LinkedList<>();
                newlist.add(person);
                list.add(newlist);
                break;
            }
        }

        while(!list.isEmpty()){
            int size = list.get(0).size()-1;
            Person last = list.get(0).get(size);

            if(last.getIdPerson().equals(dest)){
                longest = copy(list.get(0));
                list.remove(0);
                continue;
            }

            for (Person friend: graph.get(last)) {
                if(!list.get(0).contains(friend))   {
                    LinkedList<Person> newlist = copy(list.get(0));
                    newlist.add(friend);
                    list.add(newlist);
                }
            }
            list.remove(0);
        }

        System.out.println("longest path from " + origin + " to " + dest + ":");
        for (Person p: longest) {
            System.out.println(p.getIdPerson());
        }
    }

    /**
     * Creates the graph and prints cliques of people, where clique of people is a group of least 5 people where everyone has relationship with everyone
     *
     * For every person it adds a linked list only with them into linked list of cliques of friends.
     * Then for all linked list of clique it checks if the person is a friend with everyone, and if so it adds a new clique with this person added.
     * At last, it prints every clique of friends with more than 4 participants.
     */
    public void cliquesOfFriends() {
        LinkedList<LinkedList<Person>> list = new LinkedList<>();


        for (Person person : graph.keySet()) {
            LinkedList<Person> newlist2 = new LinkedList<>();
            newlist2.add(person);
            list.add(newlist2);

            LinkedList<LinkedList<Person>> list2 = new LinkedList<>();

            for (LinkedList<Person> lists : list) {
                if (isFriendWithEveryone(person, lists)) {
                    LinkedList<Person> newlist = copy(lists);
                    newlist.add(person);
                    list2.add(newlist);
                }
            }

            for (LinkedList<Person> lists : list2){
                list.add(lists);
            }

        }

        for (LinkedList<Person> lists : list) {
            if (lists.size() > 4) {
                System.out.println("--------------------");
                for (Person p : lists) {
                    System.out.println(p.getIdPerson());
                }
            }
        }
    }

    /**
     * This function copies content of a linked list to another linked list
     * @param people int, number of max nodes between searched people
     * @return copied linked list is returned
     */
    private LinkedList<Person> copy(LinkedList<Person> people) {
        return new LinkedList<>(people);
    }

    /**
     * This method checks if a given person is a friend with everyone in given list
     */
    public boolean isFriendWithEveryone(Person person, LinkedList<Person> lists) {
        for (Person p: lists) {
            if(!graph.get(person).contains(p)) return false;
        }
        return true;
    }

}