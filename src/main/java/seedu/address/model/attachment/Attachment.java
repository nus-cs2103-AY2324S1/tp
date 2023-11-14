package seedu.address.model.attachment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * Represents an attachment in the address book.
 * Guarantees: immutable; name is valid as declared in
 * {@link #isValidAttachment(String)}
 */
public class Attachment {

    public static final String MESSAGE_CONSTRAINTS = "Attachments should be valid paths to files";

    public final File file;

    /**
     * Constructs an {@code Attachment}.
     *
     * @param tagName A valid file path to the attachment contents.
     */
    public Attachment(String path) {
        requireNonNull(path);
        checkArgument(isValidAttachment(path), MESSAGE_CONSTRAINTS);
        File file = new File(path);
        this.file = file;
    }

    /**
     * Returns true if a given string is a valid path for an attachment.
     */
    public static boolean isValidAttachment(String test) {
        try {
            Paths.get(test);
            return true;
        } catch (InvalidPathException e) {
            return false;
        } catch (SecurityException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attachment)) {
            return false;
        }

        Attachment otherFile = (Attachment) other;
        return file.equals(otherFile.file);
    }

    @Override
    public int hashCode() {
        return file.getAbsolutePath().hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + file.getAbsolutePath() + ']';
    }

}
