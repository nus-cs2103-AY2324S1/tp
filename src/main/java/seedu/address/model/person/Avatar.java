package seedu.address.model.person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.image.Image;

/**
 * Represents displayed photo of a contact in the address book.
 */
public class Avatar {
    private static final String DEFAULT_PATH = "/images/default_photo.png";
    private String path = null;
    @JsonIgnore
    private Image image;

    /**
     * Constructor for default avatar
     */
    public Avatar() {
        this.image = new Image(this.getClass().getResourceAsStream(DEFAULT_PATH));
    }

    /**
     * Initializes an avatar based on a string path given.
     *
     * @param path Path to the photo to be used.
     * @throws FileNotFoundException if the path given is not valid.
     */
    public Avatar(String path) throws IOException {
        // Check that path points to valid image
        String contentType = Files.probeContentType(Paths.get(path));
        if (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) {
            throw new IOException("path does not refer to valid png or jpeg image!");
        }

        Image image = new Image(new FileInputStream(path));
        this.path = path;
        this.image = image;
    }

    /**
     * Get JavaFX {@code Image} of the avatar.
     * @return Image of the avatar.
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Get string path of the avatar image.
     * @return String path of the avatar image.
     */
    public String getPath() {
        return path;
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}


