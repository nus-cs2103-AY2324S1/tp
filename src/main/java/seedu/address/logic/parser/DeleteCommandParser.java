package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALGROUP;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public DeleteCommand parse(String args) throws ParseException {

        if (!args.trim().startsWith("all")) {
            try {
                Index index = ParserUtil.parseIndex(args);
                return new DeleteCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
            }
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIALGROUP);

        if (!argMultimap.getValue(PREFIX_TUTORIALGROUP).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        String tagName = argMultimap.getValue(PREFIX_TUTORIALGROUP).get();
        Tag tag = ParserUtil.parseTag(tagName);

        return new DeleteCommand(tag, new ContainsTagPredicate(tag));
    }
}
