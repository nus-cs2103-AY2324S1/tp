package seedu.address.logic.parser.appointmentparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointmentcommands.RescheduleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentTime;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class RescheduleCommandParser implements Parser<RescheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RescheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_START, PREFIX_END);

        Index index;

        if (argMultimap.getValue(PREFIX_ID).isEmpty() || argMultimap.getValue(PREFIX_ID).isEmpty()
                || argMultimap.getValue(PREFIX_ID).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RescheduleCommand.MESSAGE_USAGE));
        }
        index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ID).get());

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_START, PREFIX_END);


        LocalDateTime startTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START).get());
        LocalDateTime endTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END).get());
        AppointmentTime appointmentTime = new AppointmentTime(startTime, endTime);

        return new RescheduleCommand(index, appointmentTime);
    }

}
