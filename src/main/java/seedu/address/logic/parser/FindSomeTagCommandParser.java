package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindAllTagCommand;
import seedu.address.logic.commands.FindSomeTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagsContainSomeTagsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindSomeTagCommand object
 */
public class FindSomeTagCommandParser implements Parser<FindSomeTagCommand> {
    public static final String MESSAGE_FORMAT_REMINDER =
            String.format("\nFormat reminder: %1$s", FindAllTagCommand.MESSAGE_USAGE);
    public static final String MESSAGE_INVALID_TAG =
            "Tags names only allows alphanumeric characters, spaces, and dashes.";
    /**
     * Parses the given {@code String} of arguments in the context of the FindSomeTagCommand
     * and returns a FindSomeTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindSomeTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PERSON_TAG);
        if (args.isEmpty() || !isPrefixPresent(argMultimap, PREFIX_PERSON_TAG)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindSomeTagCommand.MESSAGE_USAGE));
        }

        List<String> tagArguments = argMultimap.getAllValues(PREFIX_PERSON_TAG);
        List<Tag> tagList = new ArrayList<>();
        for (String keyword : tagArguments) {
            try {
                Tag tag = new Tag(keyword);
                tagList.add(tag);
            } catch (IllegalArgumentException ie) {
                throw new ParseException(MESSAGE_INVALID_TAG + MESSAGE_FORMAT_REMINDER, ie);
            }
        }

        return new FindSomeTagCommand(new TagsContainSomeTagsPredicate(tagList));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
