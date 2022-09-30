package people;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



public final class People {
    public final static String FIRST_LINE_PEOPLE = "idperson,name,lastname,birthdate,gender,birthplace,home,studiedat,workplaces,films,groupcode";
    public People() {
        this.people = new ArrayList<>();
    }

    private List<Person> people;

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public void addPersonFromString(String data) throws ParseException {
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

        String[] splited = data.split(",");

        idPerson = splited[0];
        if(!this.people.isEmpty())
            for(Person p : people)
                if(p.getIdPerson().compareTo(idPerson)==0)
                    return; //Person ID already in list
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

        Person newPerson = new Person(idPerson,name,lastname,birthdate,gender,birthplace,home, studiedAt,workplaces,films,groupCode);
        people.add(newPerson);
    }

    public Person findPersonById(String id){
        if(!this.people.isEmpty())
            for(Person p : people)
                if(p.getIdPerson().compareTo(id)==0)
                    return p;
        return null;
    }

    public void writeToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename+".txt"));

        String output = FIRST_LINE_PEOPLE +"\n";
        for(Person p : people)
            output+=p.toString()+"\n";
        output.substring(0,output.length()-1);

        writer.write(output);
        writer.close();
    }
}
