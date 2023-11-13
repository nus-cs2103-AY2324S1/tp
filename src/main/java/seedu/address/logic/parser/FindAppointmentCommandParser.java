package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentIcPredicate;

/**
 * Parses input arguments and creates a new FindAppointmentCommand object
 */
public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {
    private static final Logger logger = LogsCenter.getLogger(FindAppointmentCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointmentCommand
     * and returns a FindAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        Pattern nricPattern = Pattern.compile("^[ST]\\d{7}[A-Z]$");
        Matcher nricMatcher = nricPattern.matcher(trimmedArgs);
        if (trimmedArgs.isEmpty() || !nricMatcher.matches()) {
            logger.warning("Can't parse find command - invalid input");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }
        logger.info("Successfully parsed Find Command");
        AppointmentIcPredicate icPredicate = new AppointmentIcPredicate(trimmedArgs);
        return new FindAppointmentCommand(icPredicate);
    }

}
