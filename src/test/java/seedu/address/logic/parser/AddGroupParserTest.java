package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddGroupParserTest {
    private AddGroupParser parser = new AddGroupParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Group expectedGroup = new Group("CS2103T");

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "CS2103T", new AddGroupCommand(expectedGroup));
    }

//    @Test
//    public void parse_repeatedNonTagValue_failure() {
//        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB;
//
//        // multiple names
//        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // multiple phones
//        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//        // multiple emails
//        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//
//        // multiple fields repeated
//        assertParseFailure(parser,
//                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE));
//
//        // invalid value followed by valid value
//
//        // invalid name
//        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // invalid email
//        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//        // invalid phone
//        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//
//        // valid value followed by invalid value
//
//        // invalid name
//        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // invalid email
//        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//        // invalid phone
//        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//    }
//
//    @Test
//    public void parse_optionalFieldsMissing_success() {
//
//    }
//
//    @Test
//    public void parse_compulsoryFieldMissing_failure() {
//
//    }
//
//    @Test
//    public void parse_invalidValue_failure() {
//        // invalid name
//    }
}
