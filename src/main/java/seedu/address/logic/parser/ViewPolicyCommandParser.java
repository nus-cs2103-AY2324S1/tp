package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewPolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewPolicyCommand object.
 */
public class ViewPolicyCommandParser implements Parser<ViewPolicyCommand> {

    @Override
    public ViewPolicyCommand parse(String args) throws ParseException {
        String[] indexes = args.trim().split("\\s+");
        if (indexes.length != 2) {
            throw new ParseException("Invalid command format. Two indexes are required.");
        }
        try {
            Index targetIndex = ParserUtil.parseIndex(indexes[0]);
            Index policyIndex = ParserUtil.parseIndex(indexes[1]);
            return new ViewPolicyCommand(targetIndex, policyIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format("Invalid command format. %s", ViewPolicyCommand.MESSAGE_USAGE), pe);
        }
    }
}

