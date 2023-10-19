package seedu.address.model.person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Avatar {

    private String path;
    private static final String DEFAULT_PATH = "/images/default_photo.png";

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

        }
        return new Image(this.getClass().getResourceAsStream(DEFAULT_PATH));
    }

    public String getPath() {
        return path;
    }
}
