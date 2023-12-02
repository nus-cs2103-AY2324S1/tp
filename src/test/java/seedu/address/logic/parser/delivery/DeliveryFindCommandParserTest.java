package seedu.address.logic.parser.delivery;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.delivery.DeliveryFindCommand;
import seedu.address.model.delivery.DeliveryNameContainsKeywordsPredicate;

public class DeliveryFindCommandParserTest {

    private DeliveryFindCommandParser parser = new DeliveryFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // white space
        assertParseFailure(parser, "         ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryFindCommand.MESSAGE_USAGE));

        // empty
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryFindCommand.MESSAGE_USAGE));

        // escape sequence
        assertParseFailure(parser, "\n\r\t",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsDeliveryFindCommand() {
        DeliveryFindCommand expectedFindCommand =
            new DeliveryFindCommand(new DeliveryNameContainsKeywordsPredicate(List.of("Gabriel")));
        assertParseSuccess(parser, "Gabriel", expectedFindCommand);
    }

    @Test
    public void parse_multipleValidArgs_returnsDeliveryFindCommand() {
        // no leading and trailing whitespaces
        DeliveryFindCommand expectedFindCommand =
            new DeliveryFindCommand(new DeliveryNameContainsKeywordsPredicate(Arrays.asList("Gabriel", "Gambe")));
        assertParseSuccess(parser, "Gabriel Gambe", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Gabriel \n \t Gambe  \t", expectedFindCommand);
    }


}
