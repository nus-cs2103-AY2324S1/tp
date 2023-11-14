package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteCommand.DeletePersonDescriptor;
import seedu.address.model.person.Id;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.testutil.PersonBuilder;

/**
 * As we are only doing white-box testing, our test cases do not cover path
 * variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take
 * the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    private DeletePersonDescriptor defaultDescriptor = new DeletePersonDescriptor();

    private Name defaultName = new Name(PersonBuilder.DEFAULT_NAME);

    private Id defaultId = new Id(PersonBuilder.DEFAULT_ID);

    @Test
    public void test_parse_validName() {
        String userString = " n/" + PersonBuilder.DEFAULT_NAME;
        DeleteCommand deleteCommand = new DeleteCommand(null, defaultName, defaultDescriptor);
        assertParseSuccess(parser, userString, deleteCommand);
    }

    @Test
    public void test_parse_validId() {
        String userString = " id/" + PersonBuilder.DEFAULT_ID;
        DeleteCommand deleteCommand = new DeleteCommand(defaultId, null, defaultDescriptor);
        assertParseSuccess(parser, userString, deleteCommand);
    }

    @Test
    public void test_parse_descriptorAppointment() {
        String userString = " id/" + PersonBuilder.DEFAULT_ID + " ap/";
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        descriptor.setDeleteAppointment();
        DeleteCommand deleteCommand = new DeleteCommand(defaultId, null, descriptor);
        assertParseSuccess(parser, userString, deleteCommand);
    }

    @Test
    public void test_parse_descriptorMedicalHistoryDeleteAll() {
        String userString = " id/" + PersonBuilder.DEFAULT_ID + " m/";
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        descriptor.setDeleteMedicalHistory();
        descriptor.setMedicalHistory(new HashSet<>());
        DeleteCommand deleteCommand = new DeleteCommand(defaultId, null, descriptor);
        assertParseSuccess(parser, userString, deleteCommand);
    }

    @Test
    public void test_parse_descriptorMedicalHistoryDeleteOne() {
        String userString = " id/" + PersonBuilder.DEFAULT_ID + " m/tachycardia";
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        MedicalHistory medicalHistory = new MedicalHistory("tachycardia");
        HashSet<MedicalHistory> testSet = new HashSet<>();
        testSet.add(medicalHistory);
        descriptor.setDeleteMedicalHistory();
        descriptor.setMedicalHistory(testSet);
        DeleteCommand deleteCommand = new DeleteCommand(defaultId, null, descriptor);
        assertParseSuccess(parser, userString, deleteCommand);
    }

    @Test
    public void test_parse_descriptorInvalidField() {
        String userString = "id/ " + PersonBuilder.DEFAULT_ID + " p/";
        assertParseFailure(parser, userString,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void test_parse_missingNameAndId() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
