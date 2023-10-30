package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class AvatarTest {

    private static final String DEFAULT_PATH = "/images/default_photo.png";
    private final String safePath = Paths.get("").toAbsolutePath()
            + "\\src\\main\\resources\\images\\test_photo.png";

    @Test
    public void constructor_default() {
        Avatar avatar = new Avatar();
        assertEquals(avatar.getPath(), DEFAULT_PATH);
    }

    @Test
    public void constructor_invalidPath_exceptionThrown() {
        try {
            Avatar avatar = new Avatar("D");
            fail();
        } catch (FileNotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    public void constructor_validPath() {
        try {
            Avatar avatar = new Avatar(safePath);
            assertEquals(avatar.getPath(), safePath);

        } catch (FileNotFoundException e) {
            fail();
        }
    }

    @Test
    public void testGetImage() {
        try {
            Avatar avatar = new Avatar(safePath);
            ImageComparator ic = new ImageComparator();
            assertTrue(ic.areImagesEqual(avatar.getImage(), new Image(new FileInputStream(safePath))));
        } catch (FileNotFoundException e) {
            fail();
        }
    }

    @Test
    public void testHashCode() {
        try {
            Avatar avatar = new Avatar(safePath);
            Avatar avatar1 = new Avatar(safePath);
            assertEquals(avatar.hashCode(), avatar1.hashCode());
        } catch (FileNotFoundException e) {
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




