package seedu.address.logic;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.Name;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_MUSICIAN_DISPLAYED_INDEX = "The musician index provided is invalid";
    public static final String MESSAGE_MUSICIANS_LISTED_OVERVIEW = "%1$d musicians listed!";
    public static final String MESSAGE_UNKNOWN_BAND = "Band does not exist!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_BAND_DISPLAYED_INDEX = "The band index provided is invalid";

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
                .append("; Tags: ");
        musician.getTags().forEach(builder::append);
        builder.append("; Instruments: ");
        musician.getInstruments().forEach(builder::append);
        builder.append("; Genres: ");
        musician.getGenres().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code band} for display to the user.
     */
    public static String format(Band band) {
        final StringBuilder builder = new StringBuilder();
        builder.append(band.getName())
                .append("; Genres: ");
        band.getGenres().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code band} for display to the user.
     */
    public static String format(Band band, Musician musician) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Band Name: ")
                .append(band.getName())
                .append("; Musician Name: ")
                .append(musician.getName());
        return builder.toString();
    }

    /**
     * Formats the {@code band} for display to the user.
     */
    public static String format(Band band, List<Musician> musicians) {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n")
                .append("Band name: ")
                .append(band.getName())
                .append("\n")
                .append("Musician names: ")
                .append(musicians.stream().map(Musician::getName)
                        .map(Name::toString).collect(Collectors.joining(", ")));
        return builder.toString();
    }

}
