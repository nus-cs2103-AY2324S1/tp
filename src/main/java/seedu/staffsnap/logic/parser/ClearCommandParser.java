package seedu.staffsnap.logic.parser;

import seedu.staffsnap.logic.commands.ClearCommand;
import seedu.staffsnap.logic.parser.exceptions.ParseException;

/**
 * Parser for clearCommand
 */
public class ClearCommandParser {

    /**
     * Returns the clearcommand with the user input processed
     * @param args user input
     * @return ClearCommand
     * @throws ParseException
     */
    public ClearCommand parse(String args) throws ParseException {

        return new ClearCommand(args);
    }
}
