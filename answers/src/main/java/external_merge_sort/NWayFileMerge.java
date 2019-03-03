package external_merge_sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by vidhyaa on 24/12/17.
 */

class BinaryFileBufferComparator implements Comparator<BinaryFileBuffer> {

    @Override
    public int compare(BinaryFileBuffer bfb1, BinaryFileBuffer bfb2) {
        return bfb1.peek().compareTo(bfb2.peek());
    }
}

public class NWayFileMerge {
    private final int BINARY_FILE_BUFFER_SIZE;
    private static final int BLOCK_SIZE = 4 * 1000 * 1000; // bytes
    private static final float FREE_MEM_PERCENTAGE_THRESHOLD = 0.7f;

    public NWayFileMerge(int binaryFileBufferSize) {
        this.BINARY_FILE_BUFFER_SIZE = binaryFileBufferSize;
    }

    public void mergeSortedFiles(List<File> files, Writer outputFileWriter, int N_WAY_MERGE) throws IOException {
        PriorityQueue<BinaryFileBuffer> pq = new PriorityQueue<>(new BinaryFileBufferComparator());

        ArrayDeque<File> pendingFiles = new ArrayDeque(); // pending files for merge
        pendingFiles.addAll(files);

        // batch size = N_WAY_MERGE is needed for efficient merging of files. It is not optimal to open and read / stream
        // from too many files at the same time (e.g. files.size() = 50000 and N_WAY_MERGE = 32).
        // A max of N_WAY_MERGE binary file buffers (of temp files) can be added to the priority queue

        // To get a binary file buffer that needs to be added to the priority queue, do the following steps:
        // Poll the file to be processed from the array deque. Get its binary file buffer and add the binary file buffer to the pq.

        // To write lines to the output, we need a buffered writer and an output file writer
        // For each pending file, create temp files and add them to the priority queue

        while (!pendingFiles.isEmpty()) {
            while (!pendingFiles.isEmpty() && pq.size() < N_WAY_MERGE) {
                // retrieve and remove the first element of pendingFiles deque
                // Get the BinaryFileBuffer of the first element
                // Add the BinaryFileBuffer to the priority queue

                File currFile = pendingFiles.pollFirst();
                pq.add(new BinaryFileBuffer(currFile, BINARY_FILE_BUFFER_SIZE));
            }

            BufferedWriter fbw;

            if (pendingFiles.isEmpty()) { // no more pending files to process, so write to the final output file
                fbw = new BufferedWriter(outputFileWriter);
            } else {
                // create a temp file and set output file writer to write to the temp file
                // Initially there is nothing in the temp file. Add the temp file to pending files.
                // All the logic is single threaded.
                // We would be writing to the temp file as long as there up to N_WAY_MERGE files to process in the heap.

                // Temp files will be created as long as there are pending files to process.
                File tmpFile = File.createTempFile("sortedTmp", ".txt");
                tmpFile.deleteOnExit();
                fbw = new BufferedWriter(new FileWriter(tmpFile));
                pendingFiles.addLast(tmpFile);
            }

          try {
              while (!pq.isEmpty()) {
                  BinaryFileBuffer currBfb = pq.poll();
                  String currLine = currBfb.pop();
                  fbw.write(currLine);
                  fbw.newLine();

                  if (currBfb.isEmpty()) {
                      currBfb.close();
                      currBfb.originalFile.delete();
                  } else {
                      pq.add(currBfb);
                  }
              }
          } finally {
              fbw.close();
              for(BinaryFileBuffer bfb : pq ) { bfb.close(); }
          }
        }
    }

    // how much memory we want to use
    public static long estimateBestSizeOfBlocks() {
        Runtime.getRuntime().gc();
        long freeMem = Runtime.getRuntime().freeMemory(); // freeMemory() returns an approximation to the total amount of memory
        // currently available for future allocated objects, measured in bytes.

        return Math.min(BLOCK_SIZE, (long) (freeMem * FREE_MEM_PERCENTAGE_THRESHOLD));
    }

    public static void sortLinesAndAddToWriter(List<String> lines, Writer outputFileWriter) throws IOException {
        Collections.sort(lines);
        BufferedWriter fbw = new BufferedWriter(outputFileWriter);
        try {
            for (String line : lines) {
                fbw.write(line);
                fbw.newLine();
            }
        } finally {
            fbw.close();
        }
    }

    /**
     * This will simply load the file by blocks of x rows, then
     * sort them in-memory, and write the result to a bunch of
     * temporary files that have to be merged later.
     * @param inputFileReader some flat  file
     * @return a list of temporary flat files
     */
    public static List<File> sortInBatch(Reader inputFileReader, Writer outputFileWriter) throws IOException {
        List<File> files = new ArrayList<File>();
        BufferedReader fbr = new BufferedReader(inputFileReader);
        long blocksize = estimateBestSizeOfBlocks(); // in bytes
        System.out.println( "Using " + blocksize + " bytes of memory");

        try{
            List<String> tmplist =  new ArrayList<String>();
            String line = "";
            try {
                while(line != null) {
                    long currentblocksize = 0; // in bytes
                    while ((currentblocksize < blocksize) && ((line = fbr.readLine()) != null)) {
                        tmplist.add(line);
                        currentblocksize += line.length() * 2; // java uses 2 bytes per character
                        // and that's why we multiply number of characters by 2.
                        currentblocksize += 16; // plus some object overhead in the list -
                        // reference to character array (64 bits - 8 bytes), hash code (4 bytes) & misc stuff
                    }

                    if ((line == null) && files.isEmpty()) {
                        sortLinesAndAddToWriter(tmplist, outputFileWriter);
                    } else {
                        File tmpFile = File.createTempFile("sortInBatch", "txt");
                        tmpFile.deleteOnExit();
                        Writer tmpWriter = new FileWriter(tmpFile);
                        sortLinesAndAddToWriter(tmplist, tmpWriter);
                        files.add(tmpFile);
                        tmplist.clear();
                    }
                }
            } catch(EOFException oef) {
                if (tmplist.size() > 0) {
                    File tmpFile = File.createTempFile("sortInBatch", "txt");
                    tmpFile.deleteOnExit();
                    Writer tmpWriter = new FileWriter(tmpFile);
                    sortLinesAndAddToWriter(tmplist, tmpWriter);
                    files.add(tmpFile);
                    tmplist.clear();
                }
            }
        } finally {
            fbr.close();
        }
        return files;
    }

    public static void main(String[] args) throws IOException {
/*        List<String> filePaths = new ArrayList<>();
        String filePath1 = "input1_copy.txt";
        String filePath2 = "input2_copy.txt";
        String filePath3 = "input3_copy.txt";

        filePaths = Arrays.asList(filePath1, filePath2, filePath3);

*/
        String inputFilePath = "/Users/vidhyaa/words_x10.txt";
        String outputFilePath = "/Users/vidhyaa/words_x10_sorted.txt";
        String prefix = "/Users/vidhyaa/IdeaProjects/programming_interviews/answers/src/main/java/external_merge_sort/";
        List<File> sortedChunkFiles = sortInBatch(new FileReader(inputFilePath), new FileWriter(outputFilePath));

        for (File file : sortedChunkFiles) {
            System.out.println("Sorted chunk file = " + file.toString());
        }
/*        for (String filePath : sortedChunkFiles) {
            File inputFile = new File(prefix + filePath);
            File outputFile = new File(prefix + "sorted_" + filePath);
            sortedFiles.add(outputFile);
            InMemoryFileSort inMemoryFileSort = new InMemoryFileSort(inputFile, outputFile);
            inMemoryFileSort.sort();
        }
*/

        // How to parallelize this ?
        // Approach : 1
        // Let us assume there are 64 files and let us say every thread can perform N_WAY_MERGE with N = 8
        // We can have 8 threads working in parallel , producing 8 tmp files
        // Then a single master thread can merge 8 tmp files into a single final outputFile
        // Overall I/O(without multiple threads ) : 2I (if inputFile is big due to splitting into chunks)
        // Overall I/O : 2I (pass 1 : All data read once and written into 8 tmp files,
        //                   pass2 : All data read once again from tmp files and written into a single output file)
        // Overall runTime : Better because multiple threads are working in parallel for PriorityQueue / String comparison kind of logic on
        //                   blocks of 8 tmpFiles
        // Bottleneck      : If I/O is bottleneck(shared between all threads) and CPU work is very light, then savings will be small and having
        //                   more threads will not help


        // How to distribute work ?
        // These days network IO is faster than file IO in many cases
        // So machine#1 reads data continuously from its local disk and sends it to X other machines (N/X data per machine)
        // Then wait for barrier(all X machines to complete sorting)
        // Now request data from X machines(using peek()/poll() style 1 line or max K lines at a time) and use a PriorityQueue/Comparator
        // to merge data and write outputFile back to disk
        // The key here is network IO speed > local file IO

        // General ideas to improve speedup
        // Improve file IO : Use multiple disk drives instead of 1, Use SSD which is faster
        // Improve parallelism : More threads help
        // Asynchronous I/O : Ensure that I/O is almost always happening(since it would be bottleneck).
        // Asynchronous I/O will help achieve this
        // instead of on demand I/O . Using Buffered reader in Java and
        // a writer thread to do asynchronous writes to help with this.
        NWayFileMerge nWayFileMerge = new NWayFileMerge(128);
//        File outputFile = new File(prefix + "sorted_output.txt");
        nWayFileMerge.mergeSortedFiles(sortedChunkFiles, new FileWriter(outputFilePath), 8);
    }
}
