package swe.context.commons.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import swe.context.annotation.Nullable;



/**
 * Contains utility methods for reading/writing files and working with {@link Path}s.
 */
public class FileUtil {
    private static final String CHARSET = "UTF-8";

    /**
     * Assumes file exists.
     */
    public static String readFromFile(Path file) throws IOException {
        return new String(Files.readAllBytes(file), CHARSET);
    }

    /**
     * Writes the specified string content to the specified file path.
     *
     * Will create the parent folders/file if they do not exist yet.
     */
    public static void writeToFile(Path path, String content) throws IOException {
        File file = path.toFile();
        @Nullable File parentFolder = file.getParentFile();
        if (parentFolder != null) {
            parentFolder.mkdirs();
        }

        Files.write(path, content.getBytes(CHARSET));
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@link Paths#get(String)},
     * otherwise returns false.
     * @param path A string representing the file path. Cannot be null.
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }
}
