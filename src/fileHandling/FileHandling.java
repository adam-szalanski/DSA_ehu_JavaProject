package fileHandling;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandling {

    public static List<String> readFile(String fileName){
        try{
            File fname = new File(fileName);
            List<String> listaPeopli = new ArrayList<>();
            Scanner input2program = new Scanner(fname,"windows-1250");
            String data = input2program.nextLine();
            while (input2program.hasNextLine()){
                data = input2program.nextLine();
                listaPeopli.add(data);
            }
            input2program.close();
            return listaPeopli;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void writeToFile(String output, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+".txt", Charset.forName("windows-1250")));
        writer.write(output);
        writer.close();
    }

}
