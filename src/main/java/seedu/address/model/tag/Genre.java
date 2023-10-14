package seedu.address.model.tag;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Represents a Genre Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidGenreName(String)}
 */
public class Genre extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Genre tags names should be a valid genre name";
    public static final HashSet<String> VALID_GENRES = new HashSet<String>(Arrays.asList(
            "pop", "rock", "jazz", "r&b", "classical", "hiphop", "country", "metal"
    ));

    /**
     * Constructs a {@code GenreTag}.
     *
     * @param tagName A valid genre tag name.
     */
    public Genre(String tagName) {
        super(tagName);
        checkArgument(isValidGenreName(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid genre tag name.
     */
    public static boolean isValidGenreName(String test) {
        return VALID_GENRES.contains(test);
    }
}
