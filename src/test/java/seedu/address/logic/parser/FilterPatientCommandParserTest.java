package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.patients.Patient;
import seedu.address.testutil.PatientBuilder;

public class FilterPatientCommandParserTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final FilterPatientCommandParser parser = new FilterPatientCommandParser();

    @Test
    public void parse_validArguments_returnsFilterPatientCommand() throws ParseException {
        String args = "a/phone k/91110000";
        FilterPatientCommand expectedCommand = new FilterPatientCommand("phone", "91110000");

        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_validArgumentsWithWhitespace_returnsFilterPatientCommand() throws ParseException {
        String args = "a/  phone k/  98765432";
        FilterPatientCommand expectedCommand = new FilterPatientCommand("phone", "98765432");

        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_emptyKeywords_throwsParseException() {
        String args = "a/attribute k/";
        String expectedMessage = "Please follow the valid filter command format, filter-patient a/(attribute) "
            + "k/(keywords).";
        assertParseFailure(parser, args, String.format(expectedMessage));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String args = "";
        assertParseFailure(parser, args, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FilterPatientCommand.MESSAGE_USAGE));
    }
}
