package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_EMPTY_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_NON_POSITIVE_INTEGER_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_TIKTOK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.Priority;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, NAME_DESC_GOOGLE, MESSAGE_EMPTY_INDEX);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_EMPTY_INDEX);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_GOOGLE, MESSAGE_NON_POSITIVE_INTEGER_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_GOOGLE, MESSAGE_NON_POSITIVE_INTEGER_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_remarkField_failure() {
        // only remark field
        assertParseFailure(parser, "1" + REMARK_DESC_GOOGLE, MESSAGE_INVALID_FORMAT);


        // remark field parsed with other fields
        assertParseFailure(parser, "1" + ROLE_DESC_GOOGLE + REMARK_DESC_GOOGLE, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + REMARK_DESC_GOOGLE + PHONE_DESC_GOOGLE + STATUS_DESC_GOOGLE,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS_INVALID_REGEX);
        // invalid phone
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS_VALID_REGEX);
        // invalid email
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS_VALID_REGEX);
        // invalid deadline
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS_WRONG_FORMAT);
        // invalid status
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC,
                ApplicationStatus.MESSAGE_CONSTRAINTS_VALID_STATUS);
        //invalid priority
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS_VALID_REGEX);


        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_GOOGLE, Phone.MESSAGE_CONSTRAINTS_VALID_REGEX);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_GOOGLE,
                Name.MESSAGE_CONSTRAINTS_INVALID_REGEX);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_COMPANY;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_TIKTOK
                + EMAIL_DESC_GOOGLE + NAME_DESC_GOOGLE + PRIORITY_DESC_TIKTOK;

        EditCommand.EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withName(VALID_NAME_GOOGLE)
                .withPhone(VALID_PHONE_TIKTOK).withEmail(VALID_EMAIL_GOOGLE).withPriority(VALID_PRIORITY_TIKTOK)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_COMPANY;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_TIKTOK + EMAIL_DESC_GOOGLE;

        EditCommand.EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_TIKTOK)
                .withEmail(VALID_EMAIL_GOOGLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_COMPANY;
        String userInput = targetIndex.getOneBased() + NAME_DESC_GOOGLE;
        EditCommand.EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withName(VALID_NAME_GOOGLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_GOOGLE;
        descriptor = new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_GOOGLE;
        descriptor = new EditCompanyDescriptorBuilder().withEmail(VALID_EMAIL_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_GOOGLE;
        descriptor = new EditCompanyDescriptorBuilder().withPriority(VALID_PRIORITY_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_COMPANY;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_TIKTOK;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + PHONE_DESC_TIKTOK + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + PHONE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + PHONE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL));
    }
}
