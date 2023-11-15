package seedu.cc.logic.parser.medicalhistory;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.commands.CommandTestUtil.DATE_DESC_CANCER;
import static seedu.cc.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_CANCER;
import static seedu.cc.logic.commands.CommandTestUtil.TREATMENT_DESC_CANCER;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_DATE_CANCER;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_CANCER;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_TREATMENT_CANCER;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.cc.logic.parser.CliSyntax.PREFIX_TREATMENT;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.cc.testutil.TypicalMedicalHistoryEvents.CANCER;

import org.junit.jupiter.api.Test;

import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.medhisteventcommands.AddMedicalHistoryEventCommand;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.testutil.MedicalHistoryEventBuilder;

class AddMedicalHistoryEventCommandParserTest {
    private final AddMedicalHistoryEventCommandParser parser = new AddMedicalHistoryEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        MedicalHistoryEvent expectedMedicalHistoryEvent = new MedicalHistoryEventBuilder(CANCER).build();
        assertParseSuccess(parser, "1" + MEDICAL_CONDITION_DESC_CANCER + TREATMENT_DESC_CANCER
                + DATE_DESC_CANCER,
                        new AddMedicalHistoryEventCommand(expectedMedicalHistoryEvent, INDEX_FIRST_PATIENT));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedMedicalHistoryEventString = MEDICAL_CONDITION_DESC_CANCER + TREATMENT_DESC_CANCER
                + DATE_DESC_CANCER;

        // repeated medical condition
        assertParseFailure(parser, "1" + validExpectedMedicalHistoryEventString
                        + MEDICAL_CONDITION_DESC_CANCER,
                                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MEDICAL_CONDITION));

        // repeated treatment
        assertParseFailure(parser, "1" + validExpectedMedicalHistoryEventString + TREATMENT_DESC_CANCER,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TREATMENT));

        // repeated date
        assertParseFailure(parser, "1" + validExpectedMedicalHistoryEventString + DATE_DESC_CANCER,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddMedicalHistoryEventCommand.MESSAGE_USAGE);

        // missing medical condition prefix
        assertParseFailure(parser, "1" + VALID_MEDICAL_CONDITION_CANCER + TREATMENT_DESC_CANCER
                        + DATE_DESC_CANCER,
                expectedMessage);

        // missing treatment prefix
        assertParseFailure(parser, "1" + MEDICAL_CONDITION_DESC_CANCER + VALID_TREATMENT_CANCER
                        + DATE_DESC_CANCER,
                            expectedMessage);

        // missing date prefix
        assertParseFailure(parser, "1" + MEDICAL_CONDITION_DESC_CANCER + TREATMENT_DESC_CANCER + VALID_DATE_CANCER,
                expectedMessage);

    }

    //TODO: add test for invalid fields
    @Test
    public void parse_invalidValue_failure() {
    }
}
