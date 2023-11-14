package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.ParserUtil.parseStartDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.ApplicationStatusContainsKeywordsPredicate;
import seedu.address.model.internship.CompanyNameContainsKeywordsPredicate;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.DeadlineWithinRangePredicate;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.DurationWithinRangePredicate;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.RequirementContainsKeywordsPredicate;
import seedu.address.model.internship.RoleContainsKeywordsPredicate;
import seedu.address.model.internship.StartDate;
import seedu.address.model.internship.StartDateWithinRangePredicate;

/**
 * Parses the provided arguments to create a {@link FilterCommand} object.
 * This parser handles the parsing of filter parameters for different attributes of an Internship,
 * such as company name, role, application status, duration, start date, requirement, and deadline.
 */
public class FilterCommandParser implements InternshipParser<FilterCommand> {
    /**
     * Filters the internship list based on the argument of the filter command
     * and returns a FilterCommand object for execution.
     */
    public static final String MESSAGE_ONE_PARAMETER = "filter command should accept only one parameter at a time.";
    public static final String MESSAGE_STARTDATE_RANGE_FORMAT = "start date range should be in the format "
        + "DD/MM/YYYY-DD/MM/YYYY where start is earlier/equal to end.";
    public static final String MESSAGE_DURATION_RANGE_FORMAT = "duration range should be in the format START-END "
            + "where start is smaller/equal to end.";
    public static final String MESSAGE_DEADLINE_RANGE_FORMAT = "deadline range should be in the format "
        + "DD/MM/YYYY-DD/MM/YYYY where start is earlier/equal to end.";
    public static final String MESSAGE_NON_EMPTY = "filter command's parameter cannot be empty.";

    private String filterParameter;
    private String filterValue;

    /**
     * Parses the arguments and returns FilterCommand object.
     */
    public FilterCommand parse(String args) throws ParseException {
        if (args.trim().length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NON_EMPTY));
        }

        if (args.equalsIgnoreCase(" default")) {
            return new FilterCommand("default", "default", InternshipModel.PREDICATE_SHOW_ALL_INTERNSHIPS);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_ROLE, PREFIX_APPLICATION_STATUS,
                        PREFIX_DURATION, PREFIX_START_DATE, PREFIX_REQUIREMENT, PREFIX_DEADLINE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.isOnlyOnePrefixPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_ONE_PARAMETER));
        }

        Predicate<Internship> predicate;

        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            predicate = parseCompanyName(argMultimap);
        } else if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            predicate = parseRole(argMultimap);
        } else if (argMultimap.getValue(PREFIX_APPLICATION_STATUS).isPresent()) {
            predicate = parseApplicationStatus(argMultimap);
        } else if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            predicate = parseStartDateRange(argMultimap);
        } else if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            predicate = parseDuration(argMultimap);
        } else if (argMultimap.getAllValues(PREFIX_REQUIREMENT).size() > 0) {
            predicate = parseRequirement(argMultimap);
        } else if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            predicate = parseDeadlineRange(argMultimap);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterCommandParser.MESSAGE_NON_EMPTY));
        }
        return new FilterCommand(filterParameter, filterValue, predicate);
    }

    private Predicate<Internship> parseCompanyName(ArgumentMultimap argMultimap) throws ParseException {
        String companyName = argMultimap.getValue(PREFIX_COMPANY_NAME).orElse("").trim();

        if (companyName.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NON_EMPTY));
        }

        // Split the companyName by spaces and create a list of keywords
        List<String> keywords = Arrays.asList(companyName.split("\\s+"));
        filterParameter = PREFIX_COMPANY_NAME.getPrefix();
        filterValue = companyName;

        return new CompanyNameContainsKeywordsPredicate(keywords);
    }

    private Predicate<Internship> parseRole(ArgumentMultimap argMultimap) throws ParseException {
        String role = argMultimap.getValue(PREFIX_ROLE).orElse("").trim();

        if (role.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NON_EMPTY));
        }
        // Split the companyName by spaces and create a list of keywords
        List<String> keywords = Arrays.asList(role.split("\\s+"));
        filterParameter = PREFIX_ROLE.getPrefix();
        filterValue = role;
        return new RoleContainsKeywordsPredicate(keywords);
    }

    private Predicate<Internship> parseApplicationStatus(ArgumentMultimap argMultimap) throws ParseException {
        String applicationStatus = argMultimap.getValue(PREFIX_APPLICATION_STATUS).orElse("").trim();
        if (applicationStatus.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NON_EMPTY));
        }
        // Split the companyName by spaces and create a list of keywords
        List<String> keywords = Arrays.asList(applicationStatus.split("\\s+"));
        filterParameter = PREFIX_APPLICATION_STATUS.getPrefix();
        filterValue = applicationStatus;
        return new ApplicationStatusContainsKeywordsPredicate(keywords);
    }

    private Predicate<Internship> parseStartDateRange(ArgumentMultimap argMultimap) throws ParseException {
        String dates = argMultimap.getValue(PREFIX_START_DATE).orElse("").trim();
        if (dates.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NON_EMPTY));
        }

        String[] dateRange = dates.split("-");
        if (dateRange.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_STARTDATE_RANGE_FORMAT));
        }

        StartDate startDateLower;
        StartDate startDateUpper;

        try {
            startDateLower = parseStartDate(dateRange[0].trim());
            startDateUpper = parseStartDate(dateRange[1].trim());

            if (startDateLower.compareTo(startDateUpper) > 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        MESSAGE_STARTDATE_RANGE_FORMAT));
            }

        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_STARTDATE_RANGE_FORMAT));
        }

        filterParameter = PREFIX_START_DATE.getPrefix();
        filterValue = String.format("%s to %s", startDateLower, startDateUpper);
        return new StartDateWithinRangePredicate(List.of(startDateLower, startDateUpper));
    }

    private Predicate<Internship> parseDeadlineRange(ArgumentMultimap argMultimap) throws ParseException {
        String deadlines = argMultimap.getValue(PREFIX_DEADLINE).orElse("").trim();
        if (deadlines.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NON_EMPTY));
        }

        String[] deadlineRange = deadlines.split("-");
        if (deadlineRange.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_DEADLINE_RANGE_FORMAT));
        }

        Deadline deadlineLower;
        Deadline deadlineUpper;

        try {
            // Placeholder for start date such that it is always later than deadline
            LocalDate startDatePlaceholder = LocalDate.MAX;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String startDatePlaceholderString = startDatePlaceholder.format(dateFormatter);
            deadlineLower = ParserUtil.parseDeadline(deadlineRange[0].trim(), startDatePlaceholderString);
            deadlineUpper = ParserUtil.parseDeadline(deadlineRange[1].trim(), startDatePlaceholderString);

            if (deadlineLower.compareTo(deadlineUpper) > 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        MESSAGE_DEADLINE_RANGE_FORMAT));
            }
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_DEADLINE_RANGE_FORMAT));
        }

        filterParameter = PREFIX_DEADLINE.getPrefix();
        filterValue = String.format("%s to %s", deadlineLower, deadlineUpper);
        return new DeadlineWithinRangePredicate(List.of(deadlineLower, deadlineUpper));
    }

    private Predicate<Internship> parseDuration(ArgumentMultimap argMultimap) throws ParseException {
        String durations = argMultimap.getValue(PREFIX_DURATION).orElse("").trim();
        if (durations.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NON_EMPTY));
        }


        String[] durationArr = durations.split("-");
        if (durationArr.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_DURATION_RANGE_FORMAT));
        }

        try {
            Duration lowerDuration = new Duration(durationArr[0]);
            Duration upperDuration = new Duration(durationArr[1]);

            if (lowerDuration.compareTo(upperDuration) > 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_DURATION_RANGE_FORMAT));
            }

            filterParameter = PREFIX_DURATION.getPrefix();
            filterValue = String.format("%s to %s months", durationArr[0], durationArr[1]);
            return new DurationWithinRangePredicate(List.of(lowerDuration, upperDuration));
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_DURATION_RANGE_FORMAT));
        }
    }

    private Predicate<Internship> parseRequirement(ArgumentMultimap argMultimap) throws ParseException {
        List<String> requirements = argMultimap.getAllValues(PREFIX_REQUIREMENT);

        // Create a list to store individual keywords
        List<String> keywords = new ArrayList<>();

        for (String requirement : requirements) {
            // Split each requirement by spaces and add the resulting words to the keywords list
            keywords.addAll(Arrays.asList(requirement.split("\\s+")));
        }

        // Check if all keywords are empty
        if (keywords.stream().allMatch(String::isEmpty) || keywords.stream().allMatch(String::isBlank)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NON_EMPTY));
        }

        filterParameter = PREFIX_REQUIREMENT.getPrefix();
        filterValue = requirements.toString();
        return new RequirementContainsKeywordsPredicate(keywords);
    }
}
