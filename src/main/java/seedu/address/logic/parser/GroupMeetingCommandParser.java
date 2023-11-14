package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.GroupMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GroupMeetingContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new GroupMeetingCommand object
 */
public class GroupMeetingCommandParser implements Parser<GroupMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * GroupMeetingCommand
     * and returns a GroupMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupMeetingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupMeetingCommand.MESSAGE_USAGE));
        }

        if (!(trimmedArgs.equals("north") || trimmedArgs.equals("south") || trimmedArgs.equals("east")
                || trimmedArgs.equals("west") || trimmedArgs.equals("central"))) {
            throw new ParseException(GroupMeetingCommand.MESSAGE_REGION_INVALID);
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new GroupMeetingCommand(new GroupMeetingContainsKeywordPredicate(Arrays.asList(nameKeywords)));
    }

}
