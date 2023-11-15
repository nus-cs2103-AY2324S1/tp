package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalBuyers.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.model.displayable.Phone;
import seedu.address.testutil.BuyerBuilder;

public class AddBuyerCommandParserTest {
    static final String BROKEN_INPUT = "ALASDKJDL";
    static final String INVALID_PARTIAL_INPUT = " p/3094 e/email@com ah/homeaddress";
    static final String VALID_PARTIAL_INPUT = " n/adam";
    static final String BAD_FIELDS_INPUT = " n/adam p/badnumber e/email@com ah/homeaddress i/info";
    static final String VALID_INPUT = " n/Alice Pauline p/94351253 e/alice@example.com ah/123, Jurong West Ave 6"
            + ", #08-111 i/Loves views t/friends";
    private final AddBuyerCommandParser parser = new AddBuyerCommandParser();

    @Test
    public void assertFailsParse_brokenInput() {
        assertParseFailure(parser, BROKEN_INPUT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBuyerCommand.MESSAGE_USAGE));
    }
    @Test
    public void assertPassesParse_invalidPartialInput() {
        assertParseFailure(parser, INVALID_PARTIAL_INPUT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBuyerCommand.MESSAGE_USAGE));
    }
    @Test
    public void assertFailsParse_badFieldsInput() {
        assertParseFailure(parser, BAD_FIELDS_INPUT,
            String.format(Phone.MESSAGE_CONSTRAINTS, AddBuyerCommand.MESSAGE_USAGE));
    }
    @Test
    public void assertPassesParse_validPartialInput() {
        assertParseSuccess(parser, VALID_PARTIAL_INPUT,
                new AddBuyerCommand(new BuyerBuilder().withName("adam").build()));
    }
    @Test
    public void assertPassesParse_validInput() {
        assertParseSuccess(parser, VALID_INPUT, new AddBuyerCommand(ALICE));
    }
}
