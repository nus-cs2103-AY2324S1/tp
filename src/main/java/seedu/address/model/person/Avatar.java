package seedu.address.model.person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.scene.image.Image;

/**
 * Represents displayed photo of a contact in the address book.
 */
public class Avatar {
    private static final int MAX_SIZE_BYTES = 5242880;
    private static final String DEFAULT_PATH = "/images/default_photo.png";
    private String path = "";
    @JsonIgnore
    private Image image;

    /**
     * Constructs thej default avatar
     */
    public Avatar() {
        this.image = new Image(this.getClass().getResourceAsStream(DEFAULT_PATH));
    }

    /**
     * Constructs avatar that does not copy image.
     * @param path Path of avatar image on filesystem.
     * @throws IOException if error is encountered while reading image.
     */
    public Avatar(String path) throws IOException {
        verifyImage(path);
        this.path = path;
        this.image = new Image(new FileInputStream(this.path));
    }

    /**
     * Constructs avatar that copies image to data directory.
     * @param path Path of avatar image on filesystem.
     * @param dataDirectory Data directory for application.
     * @throws IOException if error is encountered while reading and copying image.
     */
    public Avatar(String path, Path dataDirectory) throws IOException {
        verifyImage(path);
        this.path = copyImage(path, dataDirectory);
        this.image = new Image(new FileInputStream(this.path));;
    }

    private String copyImage(String path, Path dataDirectory) throws IOException {
        Files.createDirectories(dataDirectory);
        Path imagePath = Path.of(path);
        String fileExtension = getFileExtension(imagePath);
        Path outputPath = dataDirectory.resolve(UUID.randomUUID().toString() + fileExtension);
        return Files.copy(imagePath, outputPath, StandardCopyOption.REPLACE_EXISTING).toString();
    }

    private String getFileExtension(Path path) {
        String fileName = path.toString();
        int dotIndex = fileName.lastIndexOf('.');
        return dotIndex > 0 ? fileName.substring(dotIndex) : "";
    }

    private void verifyImage(String path) throws IOException {
        // Check that path points to valid image
        String contentType = Files.probeContentType(Paths.get(path));
        if (contentType == null) {
            throw new FileNotFoundException();
        } else if (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) {
            throw new IOException("path does not refer to valid png or jpeg image!");
        }

        // Check image size
        if (Files.size(Paths.get(path)) > MAX_SIZE_BYTES) {
            throw new IOException("image cannot be larger than 5mb");
        }
    }

    /**
     * Gets JavaFX {@code Image} of the avatar.
     * @return Image of the avatar.
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Gets string path of the avatar image.
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


