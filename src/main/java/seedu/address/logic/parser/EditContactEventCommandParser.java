package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditContactEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditContactEventCommand object
 */
public class EditContactEventCommandParser implements Parser<EditContactEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditContactEventCommand
     * and returns an EditContactEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditContactEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_EVENT_DESCRIPTION, PREFIX_EVENT_START_DATE_TIME, PREFIX_EVENT_END_DATE_TIME);

        String preamble;
        ArrayList<Index> indexArrayList;

        try {
            preamble = argMultimap.getPreamble();
            indexArrayList = ParserUtil.parseDualIndexes(preamble);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditContactEventCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT_DESCRIPTION,
                PREFIX_EVENT_START_DATE_TIME, PREFIX_EVENT_END_DATE_TIME);

        EditContactEventCommand.EditEventDescriptor editEventDescriptor =
                new EditContactEventCommand.EditEventDescriptor();

        if (argMultimap.getValue(PREFIX_EVENT_DESCRIPTION).isPresent()) {
            editEventDescriptor.setEventDescription(ParserUtil.parseEventDescription(argMultimap
                    .getValue(PREFIX_EVENT_DESCRIPTION).get()));
        }
        boolean eventStartDateTimePresent = argMultimap.getValue(PREFIX_EVENT_START_DATE_TIME).isPresent();
        boolean eventEndDateTimePresent = argMultimap.getValue(PREFIX_EVENT_END_DATE_TIME).isPresent();
        if (eventStartDateTimePresent && eventEndDateTimePresent) {
            editEventDescriptor.setEventPeriod(
                    ParserUtil.parseEventPeriod(argMultimap.getValue(PREFIX_EVENT_START_DATE_TIME).get(),
                            argMultimap.getValue(PREFIX_EVENT_END_DATE_TIME).get()));
        }
        if ((eventStartDateTimePresent && !eventEndDateTimePresent)
                || (!eventStartDateTimePresent && eventEndDateTimePresent)) {
            throw new ParseException(EditContactEventCommand.MESSAGE_WRONG_TIME);
        }
        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditContactEventCommand.MESSAGE_NOT_EDITED);
        }

        return new EditContactEventCommand(indexArrayList, editEventDescriptor);
    }
}
