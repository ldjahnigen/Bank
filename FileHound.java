import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class FileHound {
  public String fileRead(String location) {
    String text = "";
    try {
      File file = new File(location);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        text += line;
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Filehound error: " + e.getMessage());
    }
    return text;
  }

  public void fileWrite(String text, String location) {
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(location));
        writer.write(text);
        writer.close();
    } catch (IOException e) {
        System.out.println("Filehound error: " + e.getMessage());
    }
  }

  public void fileAppend(String text, String location) {
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
        writer.write(text);
        writer.close();
    } catch (IOException e) {
        System.out.println("Filehound error: " + e.getMessage());
    }
  }

}
