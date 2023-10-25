package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;

import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventID;
import seedu.address.model.person.ContactID;

/**
 * Parses input arguments and creates a new DeleteEventCommand object
 */
public class DeleteEventCommandParser implements Parser<DeleteEventCommand> {
    @Override
    public DeleteEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PERSON_ID, PREFIX_EVENT_ID);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_PERSON_ID, PREFIX_EVENT_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PERSON_ID, PREFIX_EVENT_ID);
        ContactID contactId = ParserUtil.parseContactID(argMultimap.getValue(PREFIX_PERSON_ID).get());
        EventID eventId = ParserUtil.parseEventID(argMultimap.getValue(PREFIX_EVENT_ID).get());

        return new DeleteEventCommand(contactId, eventId);
    }
}
