package seedu.address.logic.parser;

import seedu.address.logic.commands.MeowCommand;

/**
 * Parses input arguments and creates a new MeowCommand object
 */
public class MeowCommandParser implements Parser<MeowCommand> {

    /**
    * Parses the given {@code String} of arguments in the context of the MeowCommand
    * and returns a MeowCommand object for execution.
    */
    public MeowCommand parse(String args) {
        return new MeowCommand();
    }
}
