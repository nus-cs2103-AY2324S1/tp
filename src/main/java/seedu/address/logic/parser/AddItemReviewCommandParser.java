package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddItemReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.review.ItemReview;
import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;



/**
 * Parses input arguments and creates a new AddItemReviewCommand object
 */
public class AddItemReviewCommandParser implements Parser<AddItemReviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddItemReviewCommand
     * and returns an AddItemReviewCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddItemReviewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STALL, PREFIX_ITEM, PREFIX_RATING, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_STALL, PREFIX_ITEM, PREFIX_RATING, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemReviewCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STALL, PREFIX_ITEM, PREFIX_RATING, PREFIX_DESCRIPTION);
        Index stallIndex = ParserUtil.parseStallIndex(argMultimap.getValue(PREFIX_STALL).get());
        Index itemIndex = ParserUtil.parseItemIndex(argMultimap.getValue(PREFIX_ITEM).get());
        Rating rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        ItemReview itemReview = new ItemReview(rating, description);

        return new AddItemReviewCommand(stallIndex, itemIndex, itemReview);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
