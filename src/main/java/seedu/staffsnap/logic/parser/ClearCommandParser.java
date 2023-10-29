package seedu.staffsnap.logic.parser;

import seedu.staffsnap.logic.commands.ClearCommand;
import seedu.staffsnap.logic.parser.exceptions.ParseException;

public class ClearCommandParser {
    public ClearCommand parse(String args) throws ParseException {

        return new ClearCommand(args);
    }
}
