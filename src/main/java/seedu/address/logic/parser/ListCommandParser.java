package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALGROUP;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContainsTutorialGroupPredicate;
import seedu.address.model.person.TutorialGroup;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String type;
        TutorialGroup tg = new TutorialGroup("PLACEHOLDER");

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIALGROUP);

        String trimmedArgs = args.trim();
        if (trimmedArgs.startsWith("students")) {
            type = "students";
        } else if (trimmedArgs.startsWith("attendance")) {
            type = "attendance";
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_TUTORIALGROUP).isPresent()) {
            String tgValue = argMultimap.getValue(PREFIX_TUTORIALGROUP).get();
            tg = ParserUtil.parseTutorialGroup(tgValue);
        }

        return new ListCommand(type, tg, new ContainsTutorialGroupPredicate(tg));
    }

}
