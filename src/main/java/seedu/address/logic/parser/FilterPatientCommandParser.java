package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_ATTRIBUTE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_NO_KEYWORDS_PROVIDED;
import static seedu.address.logic.Messages.MESSAGE_USAGE_FILTER_PATIENT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.FilterPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FilterPatientCommand object
 */
public class FilterPatientCommandParser implements Parser<FilterPatientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterPatientCommand
     * and returns a FilterPatientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterPatientCommand parse(String args) throws ParseException {
        // Solution adapted and inspired from https://chat.openai.com/share/c95e431a-4406-4a1e-b88f-cbff729f1cca

        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPatientCommand.MESSAGE_USAGE));
        }

        String regexPattern = "a/\\s*(\\S+)\\s+k/\\s*([^k]+)";

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(trimmedArgs);

        if (!matcher.matches()) {
            throw new ParseException(MESSAGE_USAGE_FILTER_PATIENT);
        }

        String attribute = matcher.group(1);
        String keywords = matcher.group(2);

        String attributeLowerCase = attribute.toLowerCase();

        if (!FilterPatientCommand.getAllowedAttributes().contains(attributeLowerCase)) {
            throw new ParseException(String.format(MESSAGE_INVALID_ATTRIBUTE, attribute,
                String.join(", ", FilterPatientCommand.getAllowedAttributes())));
        }

        if (keywords.trim().isEmpty()) {
            throw new ParseException(MESSAGE_NO_KEYWORDS_PROVIDED);
        }

        return new FilterPatientCommand(attributeLowerCase, keywords);
    }
}
