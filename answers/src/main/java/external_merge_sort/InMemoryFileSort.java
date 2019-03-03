package external_merge_sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vidhyaa on 24/12/17.
 */
public class InMemoryFileSort {
    Reader inputFileReader;
    Writer outputFileWriter;

    public InMemoryFileSort(File inputFile, File outputFile) throws IOException {
        inputFileReader = new FileReader(inputFile);
        outputFileWriter = new FileWriter(outputFile);
    }

    public void sort() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(inputFileReader);
        String currLine;
        List<String> linesInInputFile = new ArrayList<>();

        while ((currLine = bufferedReader.readLine()) != null) {
            linesInInputFile.add(currLine);
        }

        // System.out.println(Arrays.toString(linesInInputFile.toArray()));

        inputFileReader.close();

        Collections.sort(linesInInputFile);

        PrintWriter printWriter = new PrintWriter(outputFileWriter);

        for (String line : linesInInputFile) {
            System.out.println(line);
            printWriter.write(line);
            printWriter.println();
        }

        printWriter.flush();
        printWriter.close();
        outputFileWriter.close();
    }

    public static void main(String[] args) throws IOException {
        // Add comments (Javadoc style)
        // Also give a commandLine utility
        // inMemorySort inputFile outputFile
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        InMemoryFileSort inMemoryFileSort = new InMemoryFileSort(new File(inputFilePath), new File(outputFilePath));
        inMemoryFileSort.sort();
    }
}
