package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;

import java.util.ArrayList;
import java.util.List;

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
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DEPARTMENT);

        List<String> departmentNames = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_DEPARTMENT).isPresent()) {
            departmentNames = argMultimap.getAllValues(PREFIX_DEPARTMENT);

            if (departmentNames.size() > 1) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
            if (departmentNames.get(0).isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
        }

        return new FilterCommand(new ContainsDepartmentPredicate(departmentNames.get(0)));
    }
}
