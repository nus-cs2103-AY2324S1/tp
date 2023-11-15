package seedu.cc.logic.parser.medicalhistory;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.commands.CommandTestUtil.DATE_DESC_CANCER;
import static seedu.cc.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_CANCER;
import static seedu.cc.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.cc.logic.commands.CommandTestUtil.PATIENT_INDEX_DESC;
import static seedu.cc.logic.commands.CommandTestUtil.TREATMENT_DESC_CANCER;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_DATE_CANCER;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_CANCER;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_TREATMENT_CANCER;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_MEDHISTEVENT;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.medhisteventcommands.EditMedicalHistoryEventCommand;
import seedu.cc.testutil.EditMedicalHistoryEventDescriptorBuilder;

class EditMedicalHistoryEventCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMedicalHistoryEventCommand.MESSAGE_USAGE);
    private final EditMedicalHistoryEventCommandParser parser = new EditMedicalHistoryEventCommandParser();

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
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MEDHISTEVENT;
        Index patientIndex = INDEX_FIRST_PATIENT;
        String userInput = targetIndex.getOneBased() + PATIENT_INDEX_DESC + MEDICAL_CONDITION_DESC_CANCER
                + TREATMENT_DESC_CANCER + DATE_DESC_CANCER;

        EditMedicalHistoryEventCommand.EditMedicalHistoryEventDescriptor descriptor =
                new EditMedicalHistoryEventDescriptorBuilder()
                        .withMedicalCondition(VALID_MEDICAL_CONDITION_CANCER).withTreatment(VALID_TREATMENT_CANCER)
                        .withDate(VALID_DATE_CANCER).build();
        EditMedicalHistoryEventCommand expectedCommand = new EditMedicalHistoryEventCommand(targetIndex,
                patientIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
