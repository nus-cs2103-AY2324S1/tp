package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.sortcommands.SortCommand;
import seedu.address.logic.commands.sortcommands.SortCompanyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.internship.InternshipInterviewDateTime;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * The argument for sorting companies.
     */
    public static final String SORT_COMPANIES_ARG_WORD = "c";

    /**
     * Regex used to confirm if the arguments is c for sort command.
     */
    private static final Pattern ARGUMENT_REGEX_PATTERN_WITHOUT_TIME =
            Pattern.compile("^(" + "(?i)" + SORT_COMPANIES_ARG_WORD + ")");
    /**
     * REGEX used to confirm if the arguments is c for sort command with time.
     */
    private static final Pattern ARGUMENT_REGEX_PATTERN_WITH_TIME =
            Pattern.compile("^(" + SORT_COMPANIES_ARG_WORD + ")\\s+.*");

    private final Logger logger = LogsCenter.getLogger(SortCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();

        // Used to check if argument matches the ARGUMENT_REGEX_PATTERN_WITHOUT_TIME pattern
        Matcher matcher = ARGUMENT_REGEX_PATTERN_WITHOUT_TIME.matcher(trimmedArgs);

        // Used to check if argument matches the ARGUMENT_REGEX_PATTERN_WITH_TIME pattern
        Matcher matcherWithTime = ARGUMENT_REGEX_PATTERN_WITH_TIME.matcher(trimmedArgs);

        if (matcher.matches()) {
            logger.info("Successfully parsed Sort command without time");
            return new SortCompanyCommand(Optional.empty(), Optional.empty());
        }
        if (matcherWithTime.matches()) {
            Pair<Optional<InternshipInterviewDateTime>, Optional<InternshipInterviewDateTime>> timeRange =
                    ParserUtil.parseSortInterval(trimmedArgs);
            logger.info("Successfully parsed Sort command with time");
            assert timeRange != null;
            return new SortCompanyCommand(timeRange.getKey(), timeRange.getValue());
        }
        logger.info("Failed to parse Sort command");
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
