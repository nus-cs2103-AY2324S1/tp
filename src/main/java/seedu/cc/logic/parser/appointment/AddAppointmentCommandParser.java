package seedu.cc.logic.parser.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_APPT_DATE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_APPT_TIME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.appointmentcommands.AddAppointmentEventCommand;
import seedu.cc.logic.parser.ArgumentMultimap;
import seedu.cc.logic.parser.ArgumentTokenizer;
import seedu.cc.logic.parser.ParserUtil;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.tag.Tag;
import seedu.cc.model.util.Date;
import seedu.cc.model.util.Time;

/**
 * Parses input arguments and creates a new AddAppointmentEventCommand object.
 */
public class AddAppointmentCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPT_DATE, PREFIX_APPT_TIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentEventCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_APPT_DATE, PREFIX_APPT_TIME);

        if (argMultimap.getValue(PREFIX_APPT_DATE).isEmpty() || argMultimap.getValue(PREFIX_APPT_TIME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentEventCommand.MESSAGE_USAGE));
        }

        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_APPT_DATE).get());
        Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_APPT_TIME).get());
        AppointmentEvent appointmentEvent = new AppointmentEvent(date, time);

        return new AddAppointmentEventCommand(index, appointmentEvent);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
