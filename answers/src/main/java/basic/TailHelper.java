package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by vidhyaa on 29/11/17.
 */


interface FileProcessor {
    public String readLine() throws IOException;
}

public class TailHelper implements FileProcessor {
    private final File file;
    BufferedReader br;

    public TailHelper(File file) throws FileNotFoundException {
        this.file = file;
        br = new BufferedReader(new FileReader(file));
    }

    @Override
    public String readLine() throws IOException {
        return br.readLine();
    }

    public List<String> tail(int n) throws IOException {
        Queue<String> linesSeen = new LinkedList<String>();

        while (true) {
            final String line = readLine();
            if (line == null) {
                List<String> lastNLines = new ArrayList<String>(linesSeen);
                linesSeen.clear();
                return lastNLines;
            }

            if (linesSeen.size() > n) {
                linesSeen.poll();
                linesSeen.offer(line);
            }
        }
    }
}