import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class LogAnalyzer {
    public static void main(String[] args) {
        try {
            File file = new File("application.log");
            Scanner in = new Scanner(file);
            int count = 0;
            HashSet<String> setAll = new HashSet<String>();
            HashSet<String> setErr = new HashSet<String>();
            String nextLine, substr, substrError;
            while (in.hasNextLine()) {
                nextLine = in.nextLine();
                if (nextLine.contains("] ERROR ")) {
                    count++;
                    substr = nextLine.substring(12, nextLine.length());
                    substrError = nextLine.replaceFirst(".*ERROR ", "");
                    if (!setAll.contains(substr)) setAll.add(substr);
                    if (!setErr.contains(substrError)) setErr.add(substrError);
                }
            }
            in.close();
            System.out.println("Number of all line with 'ERROR':" + count);
            System.out.println("Number of all line with 'ERROR' with unique loggers and ERROR messages: " + setAll.size());
            System.out.println("Number of lines with unique ERROR messages (can be logged by different loggers): " + setErr.size());
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }
}

