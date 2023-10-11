package seedu.address.logic.parser;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.MainWindow;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class DeleteEventCommandParser implements Parser<DeleteEventCommand> {
    @Override
    public DeleteEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EVENT_NAME);

        if (!AddressBookParser.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EVENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_EVENT_NAME);

        String contactName = argMultimap.getValue(PREFIX_NAME).get();
        String eventName = argMultimap.getValue(PREFIX_EVENT_NAME).get();

        return new DeleteEventCommand(contactName, eventName);
    }
}
