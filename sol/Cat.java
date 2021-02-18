package sol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class that writes files to standard output.
 */
class Cat {

  /**
   * Writes the contents of one buffered reader followed by another to a
   * buffered writer.
   *
   * @param reader - a buffered reader for the file
   * @param writer - a buffered writer to write to
   */
  public static void cat(BufferedReader reader, BufferedWriter writer) {
    String line;

    try {
      line = reader.readLine();
      while (line != null) {
        writer.write(line);
        writer.newLine();
        line = reader.readLine();
      }
    } catch (IOException e) {
      System.out.println("Encountered an error on reader or writer: "
              + e.getMessage());
    }
  }

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Usage: <input file> <output file>");
      System.exit(0);
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {

      cat(reader, writer);
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
