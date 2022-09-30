package relationships;

import people.People;
import people.Person;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Relationships {
    public final static String FIRST_LINE_RELATIONSHIPS = "friend1,friend2\n";

    public Relationships() {
        this.relations = new ArrayList<>();
    }

    private List<Relationship> relations;

    public List<Relationship> getRelations() { return relations; }

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

    @Override
    public String toString() {
        String output = FIRST_LINE_RELATIONSHIPS;
        for (Relationship r : relations)
            output+=r.toString()+"\n";
        output=output.substring(0,output.length()-1);
        return output;
    }

    public void writeToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename+".txt"));

        String output = this.toString();

        writer.write(output);
        writer.close();
    }
}
