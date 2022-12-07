package relationships;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
}