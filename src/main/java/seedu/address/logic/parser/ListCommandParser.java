package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.listcommands.ListCommand;
import seedu.address.logic.commands.listcommands.ListCompaniesCommand;
import seedu.address.logic.commands.listcommands.ListPersonsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {
    /**
     * The argument for listing companies.
     */
    public static final String LIST_COMPANIES_ARG_WORD = "c";

    /**
     * The argument for listing persons.
     */
    public static final String LIST_PERSONS_ARG_WORD = "p";

    /**
     * Regex used to confirm if the arguments are either c or p for list command.
     */
    private static final Pattern ARGUMENT_REGEX_PATTERN =
            Pattern.compile("^(" + LIST_COMPANIES_ARG_WORD + "|" + LIST_PERSONS_ARG_WORD + ")$");

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        // Used to check if argument is either c or p.
        Matcher matcher = ARGUMENT_REGEX_PATTERN.matcher(trimmedArgs);

        // Throw an error, if argument is invalid (i.e. not p or c) or empty.
        if (trimmedArgs.isEmpty() || !matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        // Returns the appropriate List Command, based on the argument (p or c).
        if (trimmedArgs.equals(LIST_PERSONS_ARG_WORD)) {
            return new ListPersonsCommand();
        } else {
            return new ListCompaniesCommand();
        }
    }

}
