package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.AddContactToMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddContactToMeetingCommand object
 */
public class AddContactToMeetingCommandParser implements Parser<AddContactToMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddContactToMeetingCommand}
     * and returns a {@code AddContactToMeetingCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddContactToMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TITLE);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_TITLE);

        String contactName = argMultimap.getValue(PREFIX_NAME).orElse("");
        String meetingTitle = argMultimap.getValue(PREFIX_TITLE).orElse("");
        boolean raiseException = contactName.length() < 1 || meetingTitle.length() < 1;
        if (raiseException) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactToMeetingCommand.MESSAGE_USAGE));
        }
        return new AddContactToMeetingCommand(meetingTitle, contactName);
    }
}
