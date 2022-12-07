package relationships;
import java.util.*;

import people.Person;
class Graph {

    private Map<Person, List<Person>> graph = new HashMap<>();

    public Graph() {
        graph = new HashMap<>();
    }

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

    private void addVertex(Person vertex) {
        graph.put(vertex, new LinkedList<Person>());
    }

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

    private LinkedList<Person> copy(LinkedList<Person> people) {
        return new LinkedList<>(people);
    }

}