package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new DeleteCommand object.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    private final Logger logger = LogsCenter.getLogger(DeleteCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public DeleteCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL_GROUP);
        String trimmedArgs = args.trim();

        if (!argMultimap.getPreamble().equals("all")) {
            logger.fine("Parsing delete: " + trimmedArgs);
            try {
                Index index = ParserUtil.parseIndex(args);
                return new DeleteCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
            }
        }

        Optional<Tag> tag = Optional.empty();

        if (trimmedArgs.equals("all")) {
            logger.fine("Parsing delete all: no Tutorial Group");
            return new DeleteCommand(tag, new ContainsTagPredicate(tag));
        }

        tag = Optional.of(ParserUtil.parseTag(argMultimap.getValue(PREFIX_TUTORIAL_GROUP).get()));
        logger.fine("Parsing delete all: " + tag.get().getTagName());
        return new DeleteCommand(tag, new ContainsTagPredicate(tag));
    }
}
