package lapr.project.model.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Read files.
 */
public final class ReadFiles {

    private ReadFiles() {   }

    /**
     * Read file scenario array list.
     *
     * @param filename the filename
     * @return the array list
     * @throws FileNotFoundException the file not found exception
     */
    public static ArrayList<String[]> readFileScenario(String filename) throws FileNotFoundException {
        ArrayList<String[]> matrix = new ArrayList<>();

        try{
            Scanner sc = new Scanner(new File(filename));


            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splited = line.split(";");

                matrix.add(splited);
            }
        } catch(FileNotFoundException e) {
            return null;
        }

        return matrix;
    }

}
