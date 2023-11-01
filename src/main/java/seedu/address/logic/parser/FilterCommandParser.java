package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.ContainsDepartmentPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object.
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} argument in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @param args FilterCommand argument.
     * @return FilterCommand object for execution.
     * @throws ParseException if the user inputs does not conform to the expected format.
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        String departmentKeyword = trimmedArgs;

        return new FilterCommand(new ContainsDepartmentPredicate(departmentKeyword));
    }
}
