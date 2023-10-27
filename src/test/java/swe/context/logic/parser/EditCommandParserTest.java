package swe.context.logic.parser;

import static swe.context.logic.parser.CliSyntax.PREFIX_EMAIL;
import static swe.context.logic.parser.CliSyntax.PREFIX_NOTE;
import static swe.context.logic.parser.CliSyntax.PREFIX_PHONE;
import static swe.context.logic.parser.CommandParserTestUtil.assertParseFailure;
import static swe.context.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import swe.context.commons.core.index.Index;
import swe.context.logic.Messages;
import swe.context.logic.commands.EditCommand;
import swe.context.logic.commands.EditCommand.EditContactDescriptor;
import swe.context.testutil.EditContactDescriptorBuilder;
import swe.context.testutil.TestData;

public class EditCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            Messages.commandInvalidFormat(EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, TestData.Valid.NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", Messages.EDIT_COMMAND_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TestData.Valid.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TestData.Valid.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + TestData.Invalid.NAME_DESC, Messages.NAME_CONSTRAINTS);
        // invalid phone
        assertParseFailure(parser, "1" + TestData.Invalid.PHONE_DESC, Messages.PHONE_CONSTRAINTS);
        // invalid email
        assertParseFailure(parser, "1" + TestData.Invalid.EMAIL_DESC, Messages.EMAIL_INVALID);
        // Invalid tag
        assertParseFailure(
            parser,
            "1" + TestData.Invalid.Tag.FLAG_HASHTAG,
            Messages.tagInvalid(TestData.Invalid.Tag.HASHTAG)
        );

        // invalid phone followed by valid email
        assertParseFailure(parser,
                "1"
                        + TestData.Invalid.PHONE_DESC
                        + TestData.Valid.EMAIL_DESC_AMY,
                Messages.PHONE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Contact} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(
            parser,
            "1"
                    + TestData.Valid.Tag.FLAG
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC_SPACES,
            Messages.tagInvalid("")
        );
        assertParseFailure(
            parser,
            "1"
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC
                    + TestData.Valid.Tag.FLAG
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC_SPACES,
            Messages.tagInvalid("")
        );
        assertParseFailure(
            parser,
            "1"
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC
                    + TestData.Valid.Tag.FLAG_ALPHANUMERIC_SPACES
                    + TestData.Valid.Tag.FLAG,
            Messages.tagInvalid("")
        );

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1"
                        + TestData.Invalid.NAME_DESC
                        + TestData.Invalid.EMAIL_DESC
                        + TestData.Valid.NOTE_AMY
                        + TestData.Valid.PHONE_AMY,
                Messages.NAME_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TestData.IndexContact.SECOND_CONTACT;
        String userInput = targetIndex.getOneBased()
                + TestData.Valid.PHONE_DESC_BOB
                + TestData.Valid.Tag.FLAG_ALPHANUMERIC
                + TestData.Valid.EMAIL_DESC_AMY
                + TestData.Valid.NOTE_DESC_AMY
                + TestData.Valid.NAME_DESC_AMY
                + TestData.Valid.Tag.FLAG_ALPHANUMERIC_SPACES;

        EditContactDescriptor descriptor =
                new EditContactDescriptorBuilder()
                        .withName(TestData.Valid.NAME_AMY)
                        .withPhone(TestData.Valid.PHONE_BOB)
                        .withEmail(TestData.Valid.EMAIL_AMY)
                        .withNote(TestData.Valid.NOTE_AMY)
                        .withTags(TestData.Valid.Tag.ALPHANUMERIC, TestData.Valid.Tag.ALPHANUMERIC_SPACES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = TestData.IndexContact.FIRST_CONTACT;
        String userInput = targetIndex.getOneBased() + TestData.Valid.PHONE_DESC_BOB + TestData.Valid.EMAIL_DESC_AMY;

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withPhone(TestData.Valid.PHONE_BOB)
                .withEmail(TestData.Valid.EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = TestData.IndexContact.THIRD_CONTACT;
        String userInput = targetIndex.getOneBased() + TestData.Valid.NAME_DESC_AMY;
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(TestData.Valid.NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + TestData.Valid.PHONE_DESC_AMY;
        descriptor = new EditContactDescriptorBuilder().withPhone(TestData.Valid.PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + TestData.Valid.EMAIL_DESC_AMY;
        descriptor = new EditContactDescriptorBuilder().withEmail(TestData.Valid.EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // note
        userInput = targetIndex.getOneBased() + TestData.Valid.NOTE_DESC_AMY;
        descriptor = new EditContactDescriptorBuilder().withNote(TestData.Valid.NOTE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TestData.Valid.Tag.FLAG_ALPHANUMERIC;
        descriptor = new EditContactDescriptorBuilder().withTags(TestData.Valid.Tag.ALPHANUMERIC).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = TestData.IndexContact.FIRST_CONTACT;
        String userInput = targetIndex.getOneBased() + TestData.Invalid.PHONE_DESC + TestData.Valid.PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + TestData.Valid.PHONE_DESC_BOB + TestData.Invalid.PHONE_DESC;

        assertParseFailure(parser, userInput, ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // Duplicated valid tag
        userInput = targetIndex.getOneBased()
                + TestData.Valid.PHONE_DESC_AMY
                + TestData.Valid.NOTE_DESC_AMY
                + TestData.Valid.EMAIL_DESC_AMY
                + TestData.Valid.Tag.FLAG_ALPHANUMERIC
                + TestData.Valid.PHONE_DESC_AMY
                + TestData.Valid.NOTE_DESC_AMY
                + TestData.Valid.EMAIL_DESC_AMY
                + TestData.Valid.Tag.FLAG_ALPHANUMERIC
                + TestData.Valid.PHONE_DESC_BOB
                + TestData.Valid.NOTE_DESC_BOB
                + TestData.Valid.EMAIL_DESC_BOB
                + TestData.Valid.Tag.FLAG_ALPHANUMERIC_SPACES;

        assertParseFailure(parser, userInput,
            ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NOTE));

        // multiple invalid values
        userInput = targetIndex.getOneBased()
                + TestData.Invalid.PHONE_DESC
                + TestData.Valid.NOTE_DESC_AMY
                + TestData.Invalid.EMAIL_DESC
                + TestData.Invalid.PHONE_DESC
                + TestData.Valid.NOTE_DESC_AMY
                + TestData.Invalid.EMAIL_DESC;

        assertParseFailure(parser, userInput,
            ArgumentMultimap.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NOTE));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = TestData.IndexContact.THIRD_CONTACT;
        String userInput = targetIndex.getOneBased() + TestData.Valid.Tag.FLAG;

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
