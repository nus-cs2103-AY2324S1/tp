package seedu.address.model.person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

/**
 * Represents displayed photo of a contact in the address book.
 */
public class Avatar {

    private static final String DEFAULT_PATH = "/images/default_photo.png";
    private String path;

    public Avatar() {
        this.path = DEFAULT_PATH;
    }

    public Avatar(String path) {
        this.path = path;
    }

    public Avatar(Avatar avatar) {
        this.path = avatar.getPath();
    }

    public void setAvatar(String path) {
        this.path = path;
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
}


