import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class MusicFileFilter implements FilenameFilter {

    private static final String[] musicExtensions = {
            "3gp", "aa", "aac", "aax", "act", "aiff", "alac",
            "amr", "ape", "au", "awb", "dss", "dvf", "flac",
            "gsm", "iklax", "ivs", "m4a", "m4b", "m4p", "mmf",
            "movpkg", "mp3", "mpc", "msv", "nmf", "ogg", "oga",
            "mogg", "opus", "ra", "rm", "raw", "rf64", "sln", "tta",
            "voc", "vox", "wav", "wma", "wv", "webm", "8svx", "cda"};

    @Override
    public boolean accept(File dir, String name) {
        return Arrays.stream(musicExtensions).anyMatch(name::contains);
    }

}
