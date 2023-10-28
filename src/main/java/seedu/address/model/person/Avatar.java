package seedu.address.model.person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

/**
 * Represents displayed photo of a contact in the address book.
 */
public class Avatar {

    private static final String DEFAULT_PATH = "/images/default_photo.png";

    private String path = DEFAULT_PATH;

    public Avatar() {
    }

    /**
     * Initializes an avatar based on a string path given.
     *
     * @param path Path to the photo to be used.
     * @throws FileNotFoundException if the path given is not valid.
     */
    public Avatar(String path) throws FileNotFoundException {
        new Image(new FileInputStream(path));
        this.path = path;
    }

    public Avatar(Avatar avatar) {
        this.path = avatar.getPath();
    }

    public Image getImage() {
        try {
            return new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            return new Image(this.getClass().getResourceAsStream(DEFAULT_PATH));
        }
    }

    public String getPath() {
        return path;
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}


