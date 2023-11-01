package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    public static final String STUDENT_CATEGORY = "students";
    public static final String APPOINTMENT_CATEGORY = "appointments";
    public static final String ALL_CATEGORY = "all";

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns an ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CATEGORY);

        String category = argMultimap.getValue(PREFIX_CATEGORY).orElse("");
        if (!isValidCategory(category)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        return new ViewCommand(category);
    }

    /**
     * Check whether the category is valid
     */
    public boolean isValidCategory(String category) {
        return category.equals(STUDENT_CATEGORY) || category.equals(APPOINTMENT_CATEGORY)
                || category.equals(ALL_CATEGORY);
    }
}
