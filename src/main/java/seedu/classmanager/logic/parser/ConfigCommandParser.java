package seedu.classmanager.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.classmanager.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classmanager.logic.parser.ArgumentMultimap.areAdditionalPrefixesPresent;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_COUNT;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_TUTORIAL_COUNT;

import java.math.BigInteger;

import seedu.classmanager.logic.commands.ConfigCommand;
import seedu.classmanager.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ConfigCommand object
 */
public class ConfigCommandParser implements Parser<ConfigCommand> {
    public static final String MESSAGE_INVALID_COUNT_VALUE_TOO_SMALL = "Invalid count values! "
            + "The count value of %1$s cannot be less than 1.";
    public static final String MESSAGE_INVALID_COUNT_VALUE_TOO_LARGE = "Invalid count values! "
            + "The count value of %1$s cannot be more than 40.";
    public static final String MESSAGE_INVALID_CONFIG_COMMAND_FORMAT = "Invalid count values! Please input an "
            + "integer between 1 to 40 inclusive for both tutorial count and assignment count."
            + "\n%1$s";

    /**
     * Parses the given {@code String} of arguments in the context of the ConfigCommand
     * @param args Arguments to be parsed
     * @return {@code ConfigCommand} object for execution
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ConfigCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL_COUNT, PREFIX_ASSIGNMENT_COUNT);
        if (!argMultimap.arePrefixesPresent(PREFIX_TUTORIAL_COUNT, PREFIX_ASSIGNMENT_COUNT)
                || !argMultimap.getPreamble().isEmpty()
                || areAdditionalPrefixesPresent(args, PREFIX_TUTORIAL_COUNT, PREFIX_ASSIGNMENT_COUNT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConfigCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TUTORIAL_COUNT, PREFIX_ASSIGNMENT_COUNT);

        BigInteger tutorialCount;
        BigInteger assignmentCount;
        try {
            tutorialCount = new BigInteger(argMultimap.getValue(PREFIX_TUTORIAL_COUNT).get());
            assignmentCount = new BigInteger(argMultimap.getValue(PREFIX_ASSIGNMENT_COUNT).get());
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_CONFIG_COMMAND_FORMAT, ConfigCommand.MESSAGE_USAGE));
        }
        validCountParser(tutorialCount, "tutorials");
        validCountParser(assignmentCount, "assignments");
        return new ConfigCommand(tutorialCount.intValue(), assignmentCount.intValue());
    }

    /**
     * Checks if the count value is valid.
     * Count value is valid when it is 0 or larger.
     * @param count Count value of tutorials or assignments.
     * @throws ParseException if the count value is less than 0.
     */
    private void validCountParser(BigInteger count, String attribute) throws ParseException {
        if (count.compareTo(BigInteger.ONE) < 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COUNT_VALUE_TOO_SMALL, attribute));
        } else if (count.compareTo(new BigInteger("40")) > 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COUNT_VALUE_TOO_LARGE, attribute));
        }
    }
}
