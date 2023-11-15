package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalSellers.SALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.model.displayable.Phone;
import seedu.address.testutil.SellerBuilder;

public class AddSellerCommandParserTest {
    static final String BROKEN_INPUT = "ALASDKJDL";
    static final String INVALID_PARTIAL_INPUT = " p/3094 e/email@com ah/homeaddress";
    static final String VALID_PARTIAL_INPUT = " n/adam";
    static final String BAD_FIELDS_INPUT = " n/adam p/badnumber e/email@com ah/homeaddress as/selladdress i/info";
    static final String VALID_INPUT = " n/SAlice Pauline p/94351253 e/alice@example.com "
            + "ah/123, Jurong West Ave 6, #08-111 as/Selling address example i/Has Good Views t/friends";
    private final AddSellerCommandParser parser = new AddSellerCommandParser();

    @Test
    public void assertFailsParse_brokenInput() {
        assertParseFailure(parser, BROKEN_INPUT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE));
    }

    @Test
    public void assertPassesParse_invalidPartialInput() {
        assertParseFailure(parser, INVALID_PARTIAL_INPUT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE));
    }

    @Test
    public void assertFailsParse_badFieldsInput() {
        assertParseFailure(parser, BAD_FIELDS_INPUT,
                String.format(Phone.MESSAGE_CONSTRAINTS, AddSellerCommand.MESSAGE_USAGE));
    }

    @Test
    public void assertPassesParse_validPartialInput() {
        assertParseSuccess(parser, VALID_PARTIAL_INPUT,
                new AddSellerCommand(new SellerBuilder().withName("adam").build()));
    }

    @Test
    public void assertPassesParse_validInput() {
        assertParseSuccess(parser, VALID_INPUT, new AddSellerCommand(SALICE));
    }
}
