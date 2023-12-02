//@@author B-enguin
package seedu.address.logic.parser.delivery;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.logging.Logger;

import seedu.address.logic.commands.delivery.DeliveryFindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.delivery.DeliveryNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new DeliveryFindCommand object
 */
public class DeliveryFindCommandParser implements Parser<DeliveryFindCommand> {

    private static final Logger logger = Logger.getLogger(DeliveryFindCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments in the context of the DeliveryFindCommand
     * and returns a DeliveryFindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeliveryFindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        if (argMultimap.isEmptyPreamble()) {
            logger.warning("DeliveryFindCommand: empty arguments given");
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = argMultimap.getPreamble().split("\\s+");

        assert Arrays.stream(nameKeywords).anyMatch(s -> !s.isEmpty()) : "Keywords cannot be empty";

        return new DeliveryFindCommand(new DeliveryNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
//@@author B-enguin
