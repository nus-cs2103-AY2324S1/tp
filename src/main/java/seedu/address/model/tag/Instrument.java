package seedu.address.model.tag;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Represents an Instrument Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidInstrumentName(String)}
 */
public class Instrument extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Instrument tags names should be a valid instrument name.\n"
            + "For a list of valid instruments, please use the command 'tags'";

    public static final HashSet<String> VALID_INSTRUMENTS = new LinkedHashSet<>(Arrays.asList(
            "bass", "cello", "clarinet", "drums", "flute", "guitar", "piano", "saxophone",
            "trumpet", "violin", "voice", "other"
    ));

    /**
     * Constructs a {@code InstrumentTag}.
     *
     * @param tagName A valid instrument tag name.
     */
    public Instrument(String tagName) {
        super(tagName);
        checkArgument(isValidInstrumentName(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid instrument tag name.
     */
    public static boolean isValidInstrumentName(String test) {
        return VALID_INSTRUMENTS.contains(test);
    }
}
