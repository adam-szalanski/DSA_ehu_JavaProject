package fileHandling;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class responsible for handling operations related to files
 * It has classes which writes and rides text from/to files
 */
public class FileHandling {
    /**
     * This method read lines from the whole file.
     * @param fileName is String parameter with a file name
     * @return list of Strings
     */
    public static List<String> readFile(String fileName) throws FileNotFoundException {
        try{
            File fname = new File(fileName);
            List<String> linesFromFile = new ArrayList<>();
            Scanner input2program = new Scanner(fname,"windows-1250");
            String data = input2program.nextLine();//skip first line containing labels
            while (input2program.hasNextLine()){
                data = input2program.nextLine();
                linesFromFile.add(data);
            }
            input2program.close();
            return linesFromFile;
        }
        catch(FileNotFoundException e){
            throw new FileNotFoundException("Such file does not exist");
        }
    }

    /**
     * This method write String to the file.
     * @param output String which will be written to file
     * @param fileName String contains name of file where output will be written
     * @throws IOException This throws could occur when an I/O exception of some sort has occurred.
     */
    public static void writeToFile(String output, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, Charset.forName("windows-1250")));
        writer.write(output);
        writer.close();
    }

}
