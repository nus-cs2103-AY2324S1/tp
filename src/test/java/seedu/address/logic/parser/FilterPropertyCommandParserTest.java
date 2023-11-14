package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterPropertyCommand;
import seedu.address.model.property.Price;
import seedu.address.model.property.PriceAndTagsInRangePredicate;
import seedu.address.model.tag.Tag;

public class FilterPropertyCommandParserTest {
    private FilterPropertyCommandParser parser = new FilterPropertyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterPropertyCommand() {
        String priceString = "10000";
        Price price = new Price(priceString);

        String tagString = "pink";
        Tag tag = new Tag(tagString);
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        String userInput = " " + PREFIX_PRICE + priceString + " " + PREFIX_TAG + tagString;

        FilterPropertyCommand expectedFilterPropertyCommand =
                new FilterPropertyCommand(new PriceAndTagsInRangePredicate(price, tags));
        assertParseSuccess(parser, userInput, expectedFilterPropertyCommand);
    }
}
