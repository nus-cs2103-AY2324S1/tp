package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_MUSICIAN_DISPLAYED_INDEX = "The musician index provided is invalid";
    public static final String MESSAGE_MUSICIANS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code musician} for display to the user.
     */
    public static String format(Musician musician) {
        final StringBuilder builder = new StringBuilder();
        builder.append(musician.getName())
                .append("; Phone: ")
                .append(musician.getPhone())
                .append("; Email: ")
                .append(musician.getEmail())
                .append("; Address: ")
                .append("; Tags: ");
        musician.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code musician} for display to the user.
     */
    public static String format(Band band) {
        final StringBuilder builder = new StringBuilder();
        builder.append(band.getName());
        return builder.toString();
    }

}
