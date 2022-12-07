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
            if (lists.size() > 3) {
                System.out.println("--------------------");
                for (Person p : lists) {
                    System.out.println(p.getIdPerson());
                }
            }
        }
    }

    public boolean isFriendWithEveryone(Person person, LinkedList<Person> lists) {
        for (Person p: lists) {
            if(!graph.get(person).contains(p)) return false;
        }
        return true;
    }

}