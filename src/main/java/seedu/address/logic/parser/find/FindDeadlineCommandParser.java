package seedu.address.logic.parser.find;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEJOINED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.commands.find.FindDeadlineCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Date;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Priority;

/**
 * Parser to handle user input and create a {@link FindDeadlineCommand}.
 * This command is used to search and filter deadlines within projects based on specific criteria.
 * The supported criteria include filtering by date joined and priority level.
 */
public class FindDeadlineCommandParser implements Parser<FindDeadlineCommand> {
    /**
     * Parses user input and creates a {@link FindDeadlineCommand} based on the provided criteria.
     *
     * @param args User input arguments, which may include criteria for filtering deadlines.
     * @return A {@link FindDeadlineCommand} for searching and filtering deadlines.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public FindDeadlineCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeadlineCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATEJOINED, PREFIX_PRIORITY);

        for (Prefix prefix : Arrays.asList(PREFIX_DATEJOINED, PREFIX_PRIORITY)) {
            if (argMultimap.getValue(prefix).isPresent() && argMultimap.getValue(prefix).get().isEmpty()) {
                throw new ParseException("Please input a value after the prefix.");
            }
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeadlineCommand.MESSAGE_USAGE));
        }

        Predicate<Deadline> finalPredicate = buildPredicate(argMultimap);

        return new FindDeadlineCommand(finalPredicate);
    }

    /**
     * Builds a predicate for filtering deadlines based on the criteria provided in the user input.
     *
     * @param argMultimap Argument multimap containing user input arguments.
     * @return A predicate for filtering deadlines.
     */
    private Predicate<Deadline> buildPredicate(ArgumentMultimap argMultimap) throws ParseException {
        Predicate<Deadline> finalPredicate = deadline -> true;

        if (argMultimap.getValue(PREFIX_DATEJOINED).isPresent()) {
            String dateKeywords = argMultimap.getValue(PREFIX_DATEJOINED).get();
            Date input = ParserUtil.parseDateDeadline(dateKeywords);
            finalPredicate =
                    finalPredicate.and(d -> !d.getDate().value.after(input.value));
            // Replace with your DateJoinedPredicate
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            String priorityKeywords = argMultimap.getValue(PREFIX_PRIORITY).get();
            Priority input = ParserUtil.parsePriority(priorityKeywords);
            finalPredicate = finalPredicate.and(d -> d.getPriority().equals(input));
            // Replace with your PriorityPredicate
        }

        return finalPredicate;
    }
}
