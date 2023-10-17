package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    public static final ArrayList<String> DISPLAYABLE_FIELDS = new ArrayList<>(
            Arrays.asList("none", "phone", "email", "address", "tags", "subjects")
    );

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] keywords = trimmedArgs.split("\\s+");
        String[] displayParams = Arrays.copyOfRange(keywords, 1, keywords.length);

        // Check if contains "none" keyword inside the displayParam list, and set to empty is true
        for (String p : displayParams) {
            if (p.equals("none")) {
                displayParams = new String[]{"none"};
                break;
            }
            if (!DISPLAYABLE_FIELDS.contains(p)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            }
        }


        switch (keywords[0]) {
        case "":
        case "SCHEDULE":
            return new ListCommand("SCHEDULE");

        case "STUDENTS":
            return new ListCommand("STUDENTS", displayParams);

        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

    }

}
