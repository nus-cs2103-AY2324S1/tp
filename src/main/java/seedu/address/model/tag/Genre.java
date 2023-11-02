package seedu.address.model.tag;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Represents a Genre Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidGenreName(String)}
 */
public class Genre extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Genre tags names should be a valid genre name.\n"
            + "For a list of valid genres, please use the command 'tags'";
    public static final HashSet<String> VALID_GENRES = new LinkedHashSet<>(Arrays.asList(
            "blues", "classical", "country", "electronic", "folk", "hiphop", "jazz",
            "latin", "metal", "pop", "rock", "soul", "other"
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
