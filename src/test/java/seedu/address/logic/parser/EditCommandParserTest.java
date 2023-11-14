package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESCRIPTION_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_EMAIL_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_EMAIL_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_PHONE_DESC_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TECH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_DESCRIPTION_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_EMAIL_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_PHONE_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON_INTERNSHIP_OR_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.editcommands.EditCommand;
import seedu.address.logic.commands.editcommands.EditCompanyCommand;
import seedu.address.logic.commands.editcommands.EditPersonCommand;
import seedu.address.logic.commands.editcommands.editdescriptors.EditCompanyDescriptor;
import seedu.address.logic.commands.editcommands.editdescriptors.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditCompanyDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();


    @Test
    public void parsePerson_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, " p " + VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, " p 1", EditPersonCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, " p ", MESSAGE_INVALID_FORMAT);
    }
    @Test
    public void parseCompany_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, " c " + VALID_COMPANY_NAME_APPLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, " c 1", EditCompanyCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, " c ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parsePerson_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "p -5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "p 0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "p 1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "p 1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parseCompany_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "c -5" + COMPANY_NAME_DESC_APPLE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "c 0" + COMPANY_NAME_DESC_APPLE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "c 1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "c 1 i/ string", MESSAGE_INVALID_FORMAT);
    }


    @Test
    public void parsePerson_invalidValue_failure() {
        assertParseFailure(parser, " p 1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, " p 1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, " p 1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, " p 1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, " p 1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, " p 1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, " p 1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " p 1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " p 1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                " p 1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parsePerson_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY;
        String userInput = " p " + targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parseCompany_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY;
        String userInput = " c " + targetIndex.getOneBased() + COMPANY_PHONE_DESC_ORACLE + TAG_DESC_TECH
                + COMPANY_EMAIL_DESC_GOOGLE + COMPANY_DESCRIPTION_DESC_GOOGLE
                + COMPANY_NAME_DESC_GOOGLE;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_GOOGLE).withCompanyPhone(VALID_COMPANY_PHONE_ORACLE)
                .withCompanyEmail(VALID_COMPANY_EMAIL_GOOGLE).withDescription(VALID_COMPANY_DESCRIPTION_GOOGLE)
                .withTags(VALID_TAG_TECH).build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parsePerson_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;
        String userInput = " p " + targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parseCompany_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;
        String userInput = " c " + targetIndex.getOneBased()
                + COMPANY_PHONE_DESC_ORACLE + COMPANY_EMAIL_DESC_APPLE;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withCompanyPhone(VALID_COMPANY_PHONE_ORACLE)
                .withCompanyEmail(VALID_COMPANY_EMAIL_APPLE).build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parsePerson_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON_INTERNSHIP_OR_COMPANY;
        String userInput = " p " + targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = " p " + targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = " p " + targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = " p " + targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = " p " + targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditPersonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parseCompany_oneFieldSpecified_success() {
        // companyName
        Index targetIndex = INDEX_THIRD_PERSON_INTERNSHIP_OR_COMPANY;
        String userInput = " c " + targetIndex.getOneBased() + COMPANY_NAME_DESC_GOOGLE;
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_GOOGLE).build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // companyPhone
        userInput = " c " + targetIndex.getOneBased() + COMPANY_PHONE_DESC_ORACLE;
        descriptor = new EditCompanyDescriptorBuilder()
                .withCompanyPhone(VALID_COMPANY_PHONE_ORACLE).build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // companyEmail
        userInput = " c " + targetIndex.getOneBased() + COMPANY_EMAIL_DESC_GOOGLE;
        descriptor = new EditCompanyDescriptorBuilder()
                .withCompanyEmail(VALID_COMPANY_EMAIL_GOOGLE).build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = " c " + targetIndex.getOneBased() + COMPANY_DESCRIPTION_DESC_GOOGLE;
        descriptor = new EditCompanyDescriptorBuilder().withDescription(VALID_COMPANY_DESCRIPTION_GOOGLE).build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = " c " + targetIndex.getOneBased() + TAG_DESC_TECH;
        descriptor = new EditCompanyDescriptorBuilder().withTags(VALID_TAG_TECH).build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()
        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;
        String userInput = " p " + targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = " p " + targetIndex.getOneBased() + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple valid fields repeated
        userInput = " p " + targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));

        // multiple invalid values
        userInput = " p " + targetIndex.getOneBased() + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));
    }


    @Test
    public void parsePerson_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON_INTERNSHIP_OR_COMPANY;
        String userInput = " p " + targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parseCompany_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON_INTERNSHIP_OR_COMPANY;
        String userInput = " c " + targetIndex.getOneBased() + TAG_EMPTY;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withTags().build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
