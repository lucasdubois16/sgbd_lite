import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {

    FileHandler(){};

    public void readFile(String path){

        File myObj = new File(path);

        // try-with-resources: Scanner will be closed automatically
        try (Scanner myReader = new Scanner(myObj)) {
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }
    public  void clearScreen() {
        //Fake clear screen for compatibility reasons
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }
}



