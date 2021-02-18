package sol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tester.Tester;

public class CatTest {

  public static void testCat(Tester t) {
    BufferedReader r2 = null;
    BufferedReader r3 = null;

    try (BufferedReader r1 =
            new BufferedReader(new FileReader("PATH TO SOME FILE A"));
            BufferedWriter w1 =
                    new BufferedWriter(new FileWriter(
                            "PATH TO A DIFFERENT, EMPTY FILE B"))) {

      Cat.cat(r1, w1);

      // set up readers for the two files
      r2 = new BufferedReader(new FileReader("SAME PATH TO FILE A"));
      r3 = new BufferedReader(new FileReader("SAME PATH TO FILE B"));

      String line1 = r2.readLine();
      String line2 = r3.readLine();

      // ensure every line is the same
      while (line1 != null && line2 != null) {
        t.checkExpect(line1, line2);

        line1 = r2.readLine();
        line2 = r3.readLine();
      }

      // ensure neither file had more lines than the other
      t.checkExpect(line1 == null);
      t.checkExpect(line2 == null);
    } catch (FileNotFoundException e) {
      System.out.println("File was not found!");
    } catch (IOException e) {
      System.out.println("There was an IO issue!");
    }
  }

  public static void main(String[] args) {
    Tester.run(new CatTest());
  }

}
