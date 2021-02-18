package sol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class SuspectFinder {
    int timeOfCrime = 1581881945;

    public List<String> findSuspects(String fileName) {
        List<String> suspectNames = new LinkedList<>();
        try {
            CSVFormat format = CSVFormat.RFC4180.withHeader();
            CSVParser parser = new CSVParser(new BufferedReader(new FileReader(fileName)), format);
            for (CSVRecord csvRecord : parser) {
                int timeIn = Integer.parseInt(csvRecord.get(1));
                int timeOut = Integer.parseInt(csvRecord.get(2));
                if (timeIn < timeOfCrime && timeOut > timeOfCrime) {
                    // SUSPECT!!!!!!
                    suspectNames.add(csvRecord.get(0));
                }
            }
        } catch (IOException c) {
            System.out.println("this didn't work! Lit");
        }
        return suspectNames;
    }

    public static void main(String[] args) {
        SuspectFinder sf = new SuspectFinder();
        List<String> suspects = sf.findSuspects("citSwipes.csv");
        System.out.println(suspects);
    }
}
