package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICALHISTORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.EditCommand.COMMAND_WORD;
import static seedu.address.logic.commands.EditCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, PREFIX_NAME + VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no name or nric and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, PREFIX_NAME + VALID_NAME_AMY + " some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, PREFIX_NAME + VALID_NAME_AMY + " i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = COMMAND_WORD + " " + PREFIX_NAME + VALID_NAME_BOB + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        EditCommand expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = COMMAND_WORD + " " + PREFIX_NAME + VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // phone
        String userInput = COMMAND_WORD + " " +  PREFIX_NAME + VALID_NAME_BOB + PHONE_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        EditCommand expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = COMMAND_WORD + " " +  PREFIX_NAME + VALID_NAME_BOB + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = COMMAND_WORD + " " +  PREFIX_NAME + VALID_NAME_BOB + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = COMMAND_WORD + " " +  PREFIX_NAME + VALID_NAME_BOB + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // medical
        userInput = COMMAND_WORD + " " +  PREFIX_NAME + VALID_NAME_BOB + " "
                + PREFIX_MEDICAL + VALID_MEDICALHISTORY;
        descriptor = new EditPersonDescriptorBuilder().withMedicalHistories(VALID_MEDICALHISTORY).build();
        expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // appointment
        userInput = COMMAND_WORD + " " +  PREFIX_NAME + VALID_NAME_AMY + " "
                + PREFIX_APPOINTMENT + VALID_APPOINTMENT;
        descriptor = new EditPersonDescriptorBuilder().withAppointment(VALID_APPOINTMENT).build();
        expectedCommand = new EditCommand(new Name(VALID_NAME_AMY), null, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecifiedUsingID_success() {
        // phone
        String userInput = COMMAND_WORD + " " + PREFIX_NRIC + VALID_NRIC + PHONE_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        EditCommand expectedCommand = new EditCommand(null, new Nric(VALID_NRIC), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = COMMAND_WORD + " " + PREFIX_NRIC + VALID_NRIC + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(null, new Nric(VALID_NRIC), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_failure() {

        // valid followed by invalid
        String userInput = COMMAND_WORD + " " + PREFIX_NAME + VALID_NAME_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = COMMAND_WORD + " " + PREFIX_NAME + VALID_NAME_BOB + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = COMMAND_WORD + " " + PREFIX_NAME + VALID_NAME_BOB + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));

        // multiple invalid values
        userInput = COMMAND_WORD + " " + PREFIX_NAME + VALID_NAME_BOB + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = COMMAND_WORD + " " + PREFIX_NAME + VALID_NAME_BOB + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parser_noFieldsProvided_failure() {
        String userInput = COMMAND_WORD + " " + PREFIX_NAME + VALID_NAME_BOB;

        assertParseFailure(parser, userInput, MESSAGE_NOT_EDITED);
    }
    @Test
    public void parse_invalidInput_throwsParseException() {
        // Missing NAME or NRIC prefix
        String invalidInput = COMMAND_WORD + " " + INVALID_EMAIL_DESC;
        assertThrows(ParseException.class, () -> parser.parse(invalidInput));
    }

    @Test
    public void parse_validInput_returnsEditCommand() throws ParseException {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        // Valid input with NRIC
        String validInputWithNric = COMMAND_WORD + " " + PREFIX_NRIC + VALID_NRIC_BOB + " " +
                PREFIX_PHONE + VALID_PHONE_BOB + " " + PREFIX_NAME + VALID_NAME_BOB;
        EditCommand expectedCommandWithNric = new EditCommand(new Name(VALID_NAME_BOB), new Nric(VALID_NRIC_BOB), descriptor);
        assertEquals(parser.parse(validInputWithNric), expectedCommandWithNric);

        // Valid input with Name
        String validInputWithName = COMMAND_WORD + " " + PREFIX_NAME  + VALID_NAME_AMY + " "
                + PREFIX_EMAIL + VALID_EMAIL_AMY;
        EditCommand expectedCommandWithName = new EditCommand(new Name(VALID_NAME_AMY), null, descriptor);
        assertEquals(parser.parse(validInputWithName), expectedCommandWithName);
    }
}
