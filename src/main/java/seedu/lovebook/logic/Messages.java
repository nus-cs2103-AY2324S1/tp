package seedu.lovebook.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.lovebook.logic.commands.SetPrefCommand;
import seedu.lovebook.logic.parser.Prefix;
import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.person.Date;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The date index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d dates listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String WELCOME_MESSAGE = "Hey there, fabulous single!" + "\n"
            + "Get ready to embark on an exciting journey with LoveBook to find your perfect match ❤︎₊ ⊹";

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
     * Formats the {@code date} for display to the user.
     */
    public static String format(Date date) {
        final StringBuilder builder = new StringBuilder();
        builder.append(date.getName())
                .append("; Age: ")
                .append(date.getAge())
                .append("; Gender: ")
                .append(date.getGender())
                .append("; Height: ")
                .append(date.getHeight())
                .append("; Income: ")
                .append(date.getIncome())
                .append("; Horoscope: ")
                .append(date.getHoroscope());
        return builder.toString();
    }

    /**
     * Formats the {@code datePrefs} for display to the user.
     */
    public static String format(DatePrefs datePrefs) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Age: ")
                .append(datePrefs.getAge())
                .append("; Gender: ")
                .append(datePrefs.getGender())
                .append("; Height: ")
                .append(datePrefs.getHeight())
                .append("; Income: ")
                .append(datePrefs.getIncome())
                .append("; Horoscope: ")
                .append(datePrefs.getHoroscope());
        return builder.toString();
    }

    /**
     * Formats the {@code datePrefs} for display to the user.
     */
    public static String format(SetPrefCommand.SetPreferenceDescriptor descriptor) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Age: ")
                .append(descriptor.getAge().get())
                .append("; Gender: ")
                .append(descriptor.getGender().get())
                .append("; Height: ")
                .append(descriptor.getHeight().get())
                .append("; Income: ")
                .append(descriptor.getIncome().get())
                .append("; Horoscope: ")
                .append(descriptor.getHoroscope().get());
        return builder.toString();
    }

}
