package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.availability.TimeInterval;
import seedu.address.model.course.Course;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AvailableTimePredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagPredicate;
import seedu.address.model.person.predicates.TeachingCoursePredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COURSE, PREFIX_TAG,
                        PREFIX_DAY, PREFIX_FROM, PREFIX_TO);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_COURSE, PREFIX_TAG,
                PREFIX_DAY, PREFIX_FROM, PREFIX_TO);
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywords = argMultimap.getValue(PREFIX_NAME).get().trim().split("\\s+");
            predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        if (argMultimap.getValue(PREFIX_COURSE).isPresent()) {
            Set<Course> courseList = ParserUtil.parseCourses(argMultimap.getAllValues(PREFIX_COURSE));
            predicates.add(new TeachingCoursePredicate(new ArrayList<>(courseList)));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            predicates.add(new TagPredicate(new ArrayList<>(tagList)));
        }
        if (argMultimap.getValue(PREFIX_DAY).isPresent()
                && argMultimap.getValue(PREFIX_FROM).isPresent()
                && argMultimap.getValue(PREFIX_TO).isPresent()) {
            try {
                int dayOfWeek = Integer.parseInt(argMultimap.getValue(PREFIX_DAY).get());
                if (dayOfWeek < 1 || dayOfWeek > 5) {
                    throw new ParseException(Messages.MESSAGE_INVALID_DAY);
                }
                TimeInterval interval = ParserUtil.parseTimeInterval(argMultimap.getValue(PREFIX_FROM).orElse(null),
                        argMultimap.getValue((PREFIX_TO)).orElse(null));
                predicates.add(new AvailableTimePredicate(dayOfWeek, interval));
            } catch (NumberFormatException exception) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }
        if (predicates.size() == 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand(predicates);
    }

}
