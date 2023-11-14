package seedu.address.logic.parser.appointmentparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_PRIORITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointmentcommands.TriageCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.PriorityTag;

/**
 * Parses input arguments and creates a new TriageCommand object
 */
public class TriageCommandParser implements Parser<TriageCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TriageCommand
     * and returns a TriageCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TriageCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_PRIORITY);

        Index index;

        if (argMultimap.getValue(PREFIX_APPOINTMENT_PRIORITY).isEmpty()
                || argMultimap.getPreamble().isEmpty() || argMultimap.checkPreambleIsNotNumber()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TriageCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_APPOINTMENT_PRIORITY);

        PriorityTag priorityTag = ParserUtil.parsePriorityTag(argMultimap.getValue(PREFIX_APPOINTMENT_PRIORITY).get());

        return new TriageCommand(index, priorityTag);
    }

}
