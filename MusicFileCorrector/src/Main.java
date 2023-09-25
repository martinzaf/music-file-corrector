import java.io.File;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        File filepath = new File(args[0]);
        File[] list = filepath.listFiles();

        if (list == null) {
            System.out.println("ERROR: The folder does not contain any files.");
            return;
        }

        BufferedWriter output = new BufferedWriter(new PrintWriter(("Fixed Names.txt")));

        for (File path: list) {
            String[] result = FileNameFixer.fixFileName(path);

            if (result != null) {

                //System.out.println(result[1] + result[0]);

                if (Arrays.stream(args).anyMatch("-w" :: contains)) {

                    String newName = result[1] + result[0];

                    if (path.renameTo(new File(newName))) System.out.println(path + " has been renamed to: " + newName);
                }

                output.write(result[0] + "\n");
            }
        }

        output.close();
    }

}
