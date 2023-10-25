package seedu.address.logic.parser;

import seedu.address.logic.commands.RemindCommand;

/**
 * Parses input arguments and creates a new RemindCommand object
 */
public class RemindCommandParser implements Parser<RemindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     */
    public RemindCommand parse(String args) {
        return new RemindCommand();
    }
}
