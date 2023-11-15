package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX;

import seedu.address.logic.commands.RecipeViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.RecipeUuidMatchesPredicate;
import seedu.address.model.recipe.UniqueId;

/**
 * Parser for the RecipeViewCommand, returns a RecipeViewCommand
 */
public class RecipeViewCommandParser implements Parser<RecipeViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StockCommand
     * and returns a StockCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RecipeViewCommand parse(String args) throws ParseException {
        int id;
        String trimmedArgs = args.trim();
        try {
            id = Integer.parseInt(trimmedArgs);
        } catch (NumberFormatException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecipeViewCommand.MESSAGE_USAGE), pe);
        }

        if (id <= 0) {
            throw new ParseException(MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        return new RecipeViewCommand(new RecipeUuidMatchesPredicate(new UniqueId(id)));
    }
}
