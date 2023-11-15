package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class AvatarTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "AvatarTest");
    private static final String DEFAULT_PATH = "/images/default_photo.png";
    private final String safePath = Paths.get("").toAbsolutePath()
            + "/src/main/resources/images/test_photo.png";

    @Test
    public void constructor_default() {
        Avatar avatar = new Avatar();
        assertEquals(avatar.getPath(), "");
    }

    @Test
    public void constructor_invalidPath_exceptionThrown() throws Exception {
        assertThrows(FileNotFoundException.class, () -> new Avatar("D"));
    }

    @Test
    public void constructor_validPath() {
        try {
            Avatar avatar = new Avatar(safePath);
            assertEquals(avatar.getPath(), safePath);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void constructor_largeImage_exceptionThrown() {
        assertThrows(IOException.class, () -> new Avatar(TEST_DATA_FOLDER.resolve("toolarge.png").toString()));
    }

    @Test
    public void testGetImage() {
        try {
            Avatar avatar = new Avatar(safePath);
            ImageComparator ic = new ImageComparator();
            assertTrue(ic.areImagesEqual(avatar.getImage(), new Image(new FileInputStream(safePath))));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testHashCode() {
        try {
            Avatar avatar = new Avatar(safePath);
            Avatar avatar1 = new Avatar(safePath);
            assertEquals(avatar.hashCode(), avatar1.hashCode());
        } catch (IOException e) {
            fail();
        }
    }

    public class ImageComparator {

        private Image loadImage(String imagePath) {
            // Load an image from a file or create it as needed
            // For example:
            return new Image("file:" + imagePath);
        }

        private boolean areImagesEqual(Image image1, Image image2) {
            if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
                return false; // Images have different dimensions, so they are not equal.
            }

            PixelReader reader1 = image1.getPixelReader();
            PixelReader reader2 = image2.getPixelReader();

            int width = (int) image1.getWidth();
            int height = (int) image1.getHeight();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Color color1 = reader1.getColor(x, y);
                    Color color2 = reader2.getColor(x, y);

                    if (!color1.equals(color2)) {
                        return false; // Images are not equal if any pixel differs.
                    }
                }
            }

            return true; // Images are equal if all pixels are the same.
        }
    }
}




