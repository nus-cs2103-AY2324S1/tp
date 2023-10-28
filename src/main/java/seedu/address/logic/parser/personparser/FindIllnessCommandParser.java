package seedu.address.logic.parser.personparser;

import seedu.address.logic.commands.personcommands.FindIllnessCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.IllnessContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FindIllnessCommandParser implements Parser<FindIllnessCommand> {

    public FindIllnessCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindIllnessCommand.MESSAGE_USAGE));
        }

        String[] illnessKeywords = trimmedArgs.split("\\s+");

        return new FindIllnessCommand(new IllnessContainsKeywordsPredicate(Arrays.asList(illnessKeywords)));
    }
}
