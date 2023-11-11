package seedu.application.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.StringUtil;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.Company;
import seedu.application.model.job.Deadline;
import seedu.application.model.job.Industry;
import seedu.application.model.job.JobType;
import seedu.application.model.job.Role;
import seedu.application.model.job.Status;
import seedu.application.model.job.interview.InterviewAddress;
import seedu.application.model.job.interview.InterviewDateTime;
import seedu.application.model.job.interview.InterviewType;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String role} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (!Role.isValidRole(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses a {@code String Company} into a {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code company} is invalid.
     */
    public static Company parseCompany(String company) throws ParseException {
        requireNonNull(company);
        String trimmedCompany = company.trim();
        if (!Company.isValidCompany(trimmedCompany)) {
            throw new ParseException(Company.MESSAGE_CONSTRAINTS);
        }
        return new Company(trimmedCompany);
    }

    /**
     * Parses a {@code String Company} into a {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code company} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        String formattedDeadline = formatDeadline(trimmedDeadline);
        if (!Deadline.isValidDeadline(formattedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(formattedDeadline);
    }

    /**
     * Format the input format of {@code Deadline} field to match the default LocalDateTime format.
     *
     * @param deadline String deadline to be formatted.
     * @return The formatted deadline in String.
     */
    private static String formatDeadline(String deadline) {
        String[] stringSplit = deadline.split(" ");
        if (stringSplit.length >= 3) {
            String month = stringSplit[0];
            month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
            stringSplit[0] = month;
            String time = stringSplit[3];
            String formattedTime = formatTime(time);
            stringSplit[3] = formattedTime;
        }
        return String.join(" ", stringSplit);
    }

    private static String formatTime(String time) {
        if (time.equals("2400")) {
            return "0000";
        }
        return time;
    }

    /**
     * Parses a {@code String Status} into a {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(trimmedStatus);
    }

    /**
     * Parses a {@code String JobType} into a {@code JobType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code jobType} is invalid.
     */
    public static JobType parseJobType(String jobType) throws ParseException {
        requireNonNull(jobType);
        String trimmedJobType = jobType.trim();
        if (!JobType.isValidJobType(trimmedJobType)) {
            throw new ParseException(JobType.MESSAGE_CONSTRAINTS);
        }
        return new JobType(trimmedJobType);
    }

    /**
     * Parses a {@code String Industry} into a {@code Industry}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code industry} is invalid.
     */
    public static Industry parseIndustry(String industry) throws ParseException {
        requireNonNull(industry);
        String trimmedIndustry = industry.trim();
        if (!Industry.isValidIndustry(trimmedIndustry)) {
            throw new ParseException(Industry.MESSAGE_CONSTRAINTS);
        }
        return new Industry(trimmedIndustry);
    }

    /**
     * Parses a {@code String InterviewType} into a {@code InterviewType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interviewType} is invalid.
     */
    public static InterviewType parseInterviewType(String interviewType) throws ParseException {
        requireNonNull(interviewType);
        String trimmedInterviewType = interviewType.trim();
        if (!InterviewType.isValidInterviewType(trimmedInterviewType)) {
            throw new ParseException(InterviewType.MESSAGE_CONSTRAINTS);
        }
        return new InterviewType(trimmedInterviewType);
    }

    /**
     * Parses a {@code String InterviewDateTime} into a {@code InterviewDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interviewDateTime} is invalid.
     */
    public static InterviewDateTime parseInterviewDateTime(String interviewDateTime) throws ParseException {
        requireNonNull(interviewDateTime);
        String trimmedInterviewDateTime = interviewDateTime.trim();
        String formattedDateTime = formatDeadline(trimmedInterviewDateTime);
        if (!InterviewDateTime.isValidInterviewDateTime(formattedDateTime)) {
            throw new ParseException(InterviewDateTime.MESSAGE_CONSTRAINTS);
        }
        return new InterviewDateTime(formattedDateTime);
    }

    /**
     * Parses a {@code String InterviewAddress} into a {@code InterviewAddress}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interviewAddress} is invalid.
     */
    public static InterviewAddress parseInterviewAddress(String interviewAddress) throws ParseException {
        requireNonNull(interviewAddress);
        String trimmedInterviewAddress = interviewAddress.trim();
        if (!InterviewAddress.isValidInterviewAddress(trimmedInterviewAddress)) {
            throw new ParseException(InterviewAddress.MESSAGE_CONSTRAINTS);
        }
        return new InterviewAddress(trimmedInterviewAddress);
    }
}
