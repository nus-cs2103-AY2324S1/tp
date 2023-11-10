package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FIELD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEDICAL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEDICAL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICALHISTORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.EditCommand.COMMAND_WORD;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EMPTY_MEDICAL_HISTORY_TO_EDIT;
import static seedu.address.logic.commands.EditCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, PREFIX_NAME + VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no name or id and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, PREFIX_NAME + VALID_NAME_AMY + VALID_EMAIL_AMY, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, PREFIX_NAME + VALID_NAME_AMY + INVALID_FIELD_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).build();
        EditCommand expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // phone
        String userInput = COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        EditCommand expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = COMMAND_WORD + NAME_DESC_BOB + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = COMMAND_WORD + NAME_DESC_BOB + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);


        // medical
        userInput = COMMAND_WORD + NAME_DESC_BOB + MEDICAL_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withMedicalHistories(VALID_MEDICALHISTORY).build();
        expectedCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // appointment
        userInput = COMMAND_WORD + NAME_DESC_AMY + APPOINTMENT_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withAppointment(VALID_APPOINTMENT).build();
        expectedCommand = new EditCommand(new Name(VALID_NAME_AMY), null, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecifiedUsingID_success() {
        // phone

        String userInput = COMMAND_WORD + ID_DESC_AMY + PHONE_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        EditCommand expectedCommand = new EditCommand(null, new Id(VALID_ID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = COMMAND_WORD + ID_DESC_AMY + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(null, new Id(VALID_ID_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_failure() {

        // valid followed by invalid
        String userInput = COMMAND_WORD + NAME_DESC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + EMAIL_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));

        // multiple invalid values
        userInput = COMMAND_WORD + NAME_DESC_BOB + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC
                + INVALID_EMAIL_DESC + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));
    }

    @Test
    public void parser_noFieldsProvided_failure() {
        String userInput = COMMAND_WORD + NAME_DESC_BOB;

        assertParseFailure(parser, userInput, MESSAGE_NOT_EDITED);
    }
    @Test
    public void parse_invalidInput_throwsParseException() {
        // Missing NAME or ID prefix
        String invalidInput = COMMAND_WORD + " " + INVALID_EMAIL_DESC;

        assertThrows(ParseException.class, () -> parser.parse(invalidInput));
    }

    @Test
    public void parse_validInput_returnsEditCommand() throws ParseException {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().build();
        // Valid input with ID
        String validInputWithId = COMMAND_WORD + ID_DESC_BOB + PHONE_DESC_BOB
                + NAME_DESC_BOB;
        EditCommand expectedCommandWithId = new EditCommand(new Name(VALID_NAME_BOB),
                new Id(VALID_ID_BOB), descriptor);
        assertEquals(parser.parse(validInputWithId), expectedCommandWithId);

        // Valid input with Name
        String validInputWithName = COMMAND_WORD + NAME_DESC_AMY + EMAIL_DESC_AMY;
        EditCommand expectedCommandWithName = new EditCommand(new Name(VALID_NAME_AMY), null, descriptor);
        assertEquals(parser.parse(validInputWithName), expectedCommandWithName);
    }

    @Test
    public void parse_invalidMedicalHistory_failure() {
        //invalid
        String userInput = COMMAND_WORD + ID_DESC_AMY + INVALID_MEDICAL_DESC;

        assertParseFailure(parser, userInput, MESSAGE_EMPTY_MEDICAL_HISTORY_TO_EDIT);

        //valid and invalid
        userInput = COMMAND_WORD + ID_DESC_AMY + INVALID_MEDICAL_DESC + MEDICAL_DESC_BOB;
        assertParseFailure(parser, userInput, MedicalHistory.MESSAGE_CONSTRAINTS);
    }
}
