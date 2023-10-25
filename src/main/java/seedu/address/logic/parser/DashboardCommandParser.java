package seedu.address.logic.parser;

import seedu.address.logic.commands.DashboardCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DashboardCommand object
 */
public class DashboardCommandParser implements Parser<DashboardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DashboardCommand
     * and returns an DashboardCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DashboardCommand parse(String args) throws ParseException {
        return new DashboardCommand();
    }

}
