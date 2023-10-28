package seedu.address.model.person;

import javafx.scene.image.Image;

/**
 * Represents displayed photo of a contact in the address book.
 */
public class Avatar {

    private static final String DEFAULT_PATH = "/images/default_photo.png";
    private Image photo = new Image(this.getClass().getResourceAsStream(DEFAULT_PATH));

    public Avatar() {
    }

    public Avatar(Image photo) {
        this.photo = photo;
    }

    public Avatar(Avatar avatar) {
        this.photo = avatar.photo;
    }

    public Image getImage() {
        return this.photo;
    }
}


