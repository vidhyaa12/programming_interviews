package external_merge_sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by vidhyaa on 24/12/17.
 */
public class BinaryFileBuffer {
    BufferedReader fbr;
    String cache;
    boolean isEndOfFile;
    File originalFile;

    public BinaryFileBuffer(File inputFile, int bufferSize) throws IOException {
        originalFile = inputFile;
        fbr = new BufferedReader(new FileReader(inputFile), bufferSize);
        advance();
    }

    public boolean isEmpty() {
        return isEndOfFile;
    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return cache.toString();
    }

    public String pop() {
        String nextLine = peek();
        advance();
        return nextLine;
    }

    private void advance() {
        try {
            if ((this.cache = fbr.readLine()) == null) {
                isEndOfFile = true;
                cache = null;
            } else {
                isEndOfFile = false;
            }
        } catch (IOException e) {
            cache = null;
            isEndOfFile = true;
        }
    }

    public void close() throws IOException {
        fbr.close();
    }
}
