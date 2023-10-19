package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AvatarTest {

    private static final String DEFAULT_PATH = "/images/default_photo.png";

    @Test
    public void testDefaultConstructor() {
        Avatar avatar = new Avatar();
        assertEquals(avatar.getPath(), DEFAULT_PATH);
    }
}




