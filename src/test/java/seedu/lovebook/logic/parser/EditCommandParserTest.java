package seedu.lovebook.logic.parser;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.commands.CommandTestUtil.AGE_DESC_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.AGE_DESC_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.HEIGHT_DESC_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.HEIGHT_DESC_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.INVALID_AGE_DESC;
import static seedu.lovebook.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.lovebook.logic.commands.CommandTestUtil.INVALID_HEIGHT_DESC;
import static seedu.lovebook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.lovebook.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_HEIGHT_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.lovebook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.lovebook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.lovebook.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.lovebook.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.lovebook.commons.core.index.Index;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.EditCommand;
import seedu.lovebook.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Name;
import seedu.lovebook.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_AGE_DESC, Age.MESSAGE_CONSTRAINTS); // invalid age
        assertParseFailure(parser, "1" + INVALID_GENDER_DESC, Gender.MESSAGE_CONSTRAINTS); // invalid gender
        assertParseFailure(parser, "1" + INVALID_HEIGHT_DESC, Height.MESSAGE_CONSTRAINTS); // invalid lovebook

        // invalid age followed by valid gender
        assertParseFailure(parser, "1" + INVALID_AGE_DESC + GENDER_DESC_AMY, Age.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_GENDER_DESC + VALID_HEIGHT_AMY + VALID_AGE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + AGE_DESC_BOB + GENDER_DESC_AMY
                + HEIGHT_DESC_AMY + NAME_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withAge(VALID_AGE_BOB).withGender(VALID_GENDER_AMY).withHeight(VALID_HEIGHT_AMY)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + AGE_DESC_BOB + GENDER_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withAge(VALID_AGE_BOB)
                .withGender(VALID_GENDER_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // age
        userInput = targetIndex.getOneBased() + AGE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAge(VALID_AGE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // gender
        userInput = targetIndex.getOneBased() + GENDER_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withGender(VALID_GENDER_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // lovebook
        userInput = targetIndex.getOneBased() + HEIGHT_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withHeight(VALID_HEIGHT_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_AGE_DESC + AGE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AGE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + AGE_DESC_BOB + INVALID_AGE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AGE));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + AGE_DESC_AMY + HEIGHT_DESC_AMY + GENDER_DESC_AMY
                + AGE_DESC_AMY + HEIGHT_DESC_AMY + GENDER_DESC_AMY
                + AGE_DESC_BOB + HEIGHT_DESC_BOB + GENDER_DESC_BOB;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AGE, PREFIX_GENDER, PREFIX_HEIGHT));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_AGE_DESC + INVALID_HEIGHT_DESC + INVALID_GENDER_DESC
                + INVALID_AGE_DESC + INVALID_HEIGHT_DESC + INVALID_GENDER_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AGE, PREFIX_GENDER, PREFIX_HEIGHT));
    }
}
