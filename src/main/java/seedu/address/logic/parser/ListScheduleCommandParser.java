package seedu.address.logic.parser;

import java.util.Arrays;

import seedu.address.logic.commands.ListScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.TutorNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ListScheduleCommandParser implements Parser<ListScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListScheduleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new ListScheduleCommand(new TutorNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
