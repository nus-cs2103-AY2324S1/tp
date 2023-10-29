package seedu.staffsnap.logic.parser;

import seedu.staffsnap.logic.commands.AddCommand;
import seedu.staffsnap.logic.commands.ClearCommand;
import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.applicant.*;
import seedu.staffsnap.model.interview.Interview;

import java.util.ArrayList;
import java.util.List;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.parser.CliSyntax.*;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.staffsnap.logic.parser.ParserUtil.arePrefixesPresent;

public class ClearCommandParser {
    public ClearCommand parse(String args) throws ParseException {

        return new ClearCommand(args);
    }
}
