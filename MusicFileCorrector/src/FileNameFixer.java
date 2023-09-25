import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FileNameFixer {

    private static final String[] smallWords = {"the", "to", "in", "a", "of", "as"};
    private static final MusicFileFilter filter = new MusicFileFilter();

    /**
     * Extracts a filename from a file path, and re-formats the name.
     * @param path a pith leading to the file that we wish to modify
     * @return a String array containing the new filename and the path leading to the file's original location
     */
    public static String[] fixFileName(File path) {

        String stringPath = path.getAbsoluteFile().toString();
        String fileName = stringPath.substring(stringPath.lastIndexOf("\\")+1);

        // Skip if the file is not an audio file
        String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);

        if (!filter.accept(path, fileExt)) {
            System.out.println("SKIPPED: " + fileName + " is not an audio file.");
            return null;
        }

        ArrayList<String> wordList = new ArrayList<>();
        String[] splitName = fileName.split("[ -.]");

        // Ensure the word w isn't empty before adding it
        for (String w : splitName) { if (w.compareTo("") != 0) wordList.add(w); }

        String[] fixedWordList = new String[wordList.size()];

        // If the first word is a number, we want it to have a set number of digits depending on the nb. of tracks
        String num = StringUtils.stripStart((String) wordList.toArray()[0], "0");
        fixedWordList[0] = addRequiredZeroes(num, 2);

        String ext = (String) wordList.toArray()[wordList.size()-1];
        fixedWordList[wordList.size() - 1] = ext;

        for (int i = 1; i < wordList.size() - 1; i++) {
            String word = (String) wordList.toArray()[i];
            word = word.toLowerCase();

            /* Capitalize a word if:
            1. It's not one of the small words
            2. It's the first or last word in the title
            */

            if (!Arrays.asList(smallWords).contains(word) || (i == 1) || (i == wordList.size() - 2)) {
                word = Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            }

            fixedWordList[i] = word;

        }

        // Constructing the fixed filename
        String newFilename = fixedWordList[0];

        for (int i = 1; i < wordList.size() - 1; i++) {
            newFilename = newFilename + " " + fixedWordList[i];
        }

        newFilename = newFilename + "." + fixedWordList[fixedWordList.length-1];

        return(new String[]{newFilename, fileName.substring(0, fileName.lastIndexOf("\\")+1)});

    }

    /**
     * Formats a number (inputted as a String) so that it has a desired amount of total digits.
     * @param input A string, which we verify is a number
     * @param numberOfZeroes The number of zeroes wanted in the number
     * @return The number, with at least two zeroes
     */
    // TODO: find a way to determine how many tracks there are, and therefore how many zeroes are required
    // (ex: 100+ tracks = 3 digits to start with)
    private static String addRequiredZeroes(String input, int numberOfZeroes) {
        String out = input;

        if (out.matches("-?\\d+(\\.\\d+)?")) {
            while (out.length() < numberOfZeroes) { out = "0" + out; }
            return out + ".";
        }

        return out;
    }
}
