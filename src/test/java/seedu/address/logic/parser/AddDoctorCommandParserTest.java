package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DEREK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;


public class AddDoctorCommandParserTest {
    private AddDoctorCommandParser parser = new AddDoctorCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_DEREK + PHONE_DESC_DEREK + EMAIL_DESC_DEREK + ADDRESS_DESC_DEREK,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_DEREK + VALID_PHONE_DEREK + EMAIL_DESC_DEREK + ADDRESS_DESC_DEREK,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_DEREK + PHONE_DESC_DEREK + VALID_EMAIL_DEREK + ADDRESS_DESC_DEREK,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_DEREK + PHONE_DESC_DEREK + EMAIL_DESC_DEREK + VALID_ADDRESS_DEREK,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_DEREK + VALID_PHONE_DEREK + VALID_EMAIL_DEREK + VALID_ADDRESS_DEREK,
                expectedMessage);
    }
}
