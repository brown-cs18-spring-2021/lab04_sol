package sol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A class that writes user input to a Document separated by spaces
 */
class Doc {

  /**
   * Reads from console until EOF, then writes all lines to writer.
   * 
   * @param writer - a BufferedWriter writing to a file.
   */
  public static void toDocument(BufferedWriter writer) {
    try (BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in))) {
      String line = reader.readLine();
      while (line != null) {
        writer.write(line + " ");
        line = reader.readLine();
      }

      writer.flush();
    } catch (IOException e) {
      System.out.println("Error with reading from terminal/writing to file");
    }
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: <output file>");
      System.exit(0);
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[0]))) {
      toDocument(writer);
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
