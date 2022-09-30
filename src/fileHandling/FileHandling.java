package fileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandling {

    public static List<String> readFile(String fileName){
        try{
            File fname = new File(fileName);
            List<String> listaPeopli = new ArrayList<>();
            Scanner input2program = new Scanner(fname);
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

}
