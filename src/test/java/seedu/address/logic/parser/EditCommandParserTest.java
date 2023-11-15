package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DOSAGE_DESC_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.DOSAGE_DESC_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.END_DATE_DESC_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.FREQUENCY_DESC_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.FREQUENCY_DESC_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DOSAGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FREQUENCY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NOTE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STOCK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.STOCK_DESC_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOSAGE_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOSAGE_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FREQUENCY_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STOCK_ASPIRIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRESCRIPTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PRESCRIPTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PRESCRIPTION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPrescriptionDescriptor;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Stock;
import seedu.address.testutil.EditPrescriptionDescriptorBuilder;

public class EditCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(EditCommand.MESSAGE_USAGE, EditCommand.MESSAGE_USAGE);
    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_ASPIRIN, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE));

        //no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_NAME_ASPIRIN, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0" + VALID_NAME_ASPIRIN, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DOSAGE_DESC, Dosage.MESSAGE_CONSTRAINTS); // invalid dosage
        assertParseFailure(parser, "1" + INVALID_FREQUENCY_DESC, Frequency.MESSAGE_CONSTRAINTS); // invalid frequency
        assertParseFailure(parser, "1" + INVALID_START_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid start date
        assertParseFailure(parser, "1" + INVALID_END_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid end date
        assertParseFailure(parser, "1" + INVALID_EXPIRY_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid expiry date
        assertParseFailure(parser, "1" + INVALID_STOCK_DESC, Stock.MESSAGE_CONSTRAINTS); // invalid stock
        assertParseFailure(parser, "1" + INVALID_NOTE_DESC, Note.MESSAGE_CONSTRAINTS); // invalid note

        //invalid name followed by valid dosage
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + " " + VALID_NAME_ASPIRIN, Name.MESSAGE_CONSTRAINTS);

        //multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DOSAGE_DESC + INVALID_FREQUENCY_DESC
                + INVALID_START_DATE_DESC + INVALID_END_DATE_DESC + INVALID_EXPIRY_DATE_DESC + INVALID_STOCK_DESC
                + INVALID_NOTE_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PRESCRIPTION;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ASPIRIN + DOSAGE_DESC_PROPRANOLOL
                + FREQUENCY_DESC_ASPIRIN + START_DATE_DESC_ASPIRIN + END_DATE_DESC_ASPIRIN
                + EXPIRY_DATE_DESC_ASPIRIN + STOCK_DESC_ASPIRIN
                + NOTE_DESC_ASPIRIN;

        EditPrescriptionDescriptor descriptor = new EditPrescriptionDescriptorBuilder().withName(VALID_NAME_ASPIRIN)
                .withDosage(VALID_DOSAGE_PROPRANOLOL).withFrequency(VALID_FREQUENCY_ASPIRIN)
                .withStartDate(VALID_START_DATE_ASPIRIN).withEndDate(VALID_END_DATE_ASPIRIN)
                .withExpiryDate(VALID_EXPIRY_DATE_ASPIRIN).withTotalStock(VALID_STOCK_ASPIRIN)
                .withNote(VALID_NOTE_ASPIRIN).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldSpecified_success() {
        Index targetIndex = INDEX_FIRST_PRESCRIPTION;
        String userInput = targetIndex.getOneBased() + DOSAGE_DESC_PROPRANOLOL + FREQUENCY_DESC_ASPIRIN;

        EditPrescriptionDescriptor descriptor = new EditPrescriptionDescriptorBuilder()
                .withDosage(VALID_DOSAGE_PROPRANOLOL).withFrequency(VALID_FREQUENCY_ASPIRIN).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PRESCRIPTION;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ASPIRIN;
        EditPrescriptionDescriptor descriptor = new EditPrescriptionDescriptorBuilder()
                .withName(VALID_NAME_ASPIRIN).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // dosage
        userInput = targetIndex.getOneBased() + DOSAGE_DESC_ASPIRIN;
        descriptor = new EditPrescriptionDescriptorBuilder().withDosage(VALID_DOSAGE_ASPIRIN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // frequency
        userInput = targetIndex.getOneBased() + FREQUENCY_DESC_ASPIRIN;
        descriptor = new EditPrescriptionDescriptorBuilder().withFrequency(VALID_FREQUENCY_ASPIRIN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start date
        userInput = targetIndex.getOneBased() + START_DATE_DESC_ASPIRIN;
        descriptor = new EditPrescriptionDescriptorBuilder().withStartDate(VALID_START_DATE_ASPIRIN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end date
        userInput = targetIndex.getOneBased() + END_DATE_DESC_ASPIRIN;
        descriptor = new EditPrescriptionDescriptorBuilder().withEndDate(VALID_END_DATE_ASPIRIN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // expiry date
        userInput = targetIndex.getOneBased() + EXPIRY_DATE_DESC_ASPIRIN;
        descriptor = new EditPrescriptionDescriptorBuilder().withExpiryDate(VALID_EXPIRY_DATE_ASPIRIN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // stock
        userInput = targetIndex.getOneBased() + STOCK_DESC_ASPIRIN;
        descriptor = new EditPrescriptionDescriptorBuilder().withTotalStock(VALID_STOCK_ASPIRIN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // note
        userInput = targetIndex.getOneBased() + NOTE_DESC_ASPIRIN;
        descriptor = new EditPrescriptionDescriptorBuilder().withNote(VALID_NOTE_ASPIRIN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multiRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detection is done in
        // AddCommandParserTest#parse_multiRepeatedFields_failure()

        //valid followed by invalid
        Index targetIndex = INDEX_FIRST_PRESCRIPTION;
        String userInput = targetIndex.getOneBased() + INVALID_DOSAGE_DESC + DOSAGE_DESC_PROPRANOLOL;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOSAGE));

        //invalid follwed by valid
        userInput = targetIndex.getOneBased() + DOSAGE_DESC_PROPRANOLOL + INVALID_DOSAGE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOSAGE));

        //multiple valid fields repeated
        userInput = targetIndex.getOneBased() + FREQUENCY_DESC_ASPIRIN + DOSAGE_DESC_PROPRANOLOL
                + FREQUENCY_DESC_PROPRANOLOL;
        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(
                PREFIX_FREQUENCY));
    }
}
