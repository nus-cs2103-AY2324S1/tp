package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;





/**
 * Parses input arguments and creates a new EditItemCommand object
 */
public class EditItemCommandParser implements Parser<EditItemCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the EditItemCommand
     * and returns an EditItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditItemCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STALL, PREFIX_ITEM, PREFIX_NAME, PREFIX_PRICE, PREFIX_RATING,
                        PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_STALL, PREFIX_ITEM)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditItemCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STALL, PREFIX_ITEM, PREFIX_NAME, PREFIX_PRICE, PREFIX_RATING,
                PREFIX_DESCRIPTION);

        Index stallIndex;
        try {
            stallIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STALL).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_STALL_DISPLAYED_INDEX,
                    EditItemCommand.MESSAGE_USAGE), pe);
        }

        Index itemIndex;
        try {
            itemIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ITEM).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX,
                    EditItemCommand.MESSAGE_USAGE), pe);
        }

        EditItemCommand.EditItemDescriptor editItemDescriptor = new EditItemCommand.EditItemDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editItemDescriptor.setItemName(ParserUtil.parseItemName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editItemDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }

        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            editItemDescriptor.setRating(ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get()));
        }

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editItemDescriptor.setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION)
                    .get()));
        }

        if (!editItemDescriptor.isAnyFieldEdited()) {
            logger.warning("Attempted to edit an item without any fields edited.");
            throw new ParseException(EditItemCommand.MESSAGE_NOT_EDITED);
        }

        return new EditItemCommand(stallIndex, itemIndex, editItemDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
