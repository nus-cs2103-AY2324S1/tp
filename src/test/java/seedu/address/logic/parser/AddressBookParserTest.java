package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.FIRST_APPOINTMENT_ID;
import static seedu.address.testutil.TypicalIndexes.FIRST_DENTIST_ID;
import static seedu.address.testutil.TypicalIndexes.FIRST_PATIENT_ID;
import static seedu.address.testutil.TypicalIndexes.FIRST_TREATMENT_NAME;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddDentistCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.AddTreatmentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteDentistCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.DeleteTreatmentCommand;
import seedu.address.logic.commands.EditDentistCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterAppointmentCommand;
import seedu.address.logic.commands.FilterDentistCommand;
import seedu.address.logic.commands.FilterPatientCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.commands.ListDentistCommand;
import seedu.address.logic.commands.ListPatientCommand;
import seedu.address.logic.commands.ListTreatmentCommand;
import seedu.address.logic.commands.SearchDentistCommand;
import seedu.address.logic.commands.SearchPatientCommand;
import seedu.address.logic.commands.ViewCalendarCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.treatment.Treatment;
import seedu.address.testutil.DentistBuilder;
import seedu.address.testutil.EditDentistDescriptorBuilder;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TreatmentBuilder;
import seedu.address.ui.CalendarWindow;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @BeforeEach
    public void setUp() {
        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        CalendarWindow.setInstance(mockCalendarWindow);
    }

    @Test
    public void parseCommand_addDentist() throws Exception {
        Dentist dentist = new DentistBuilder().build();
        AddDentistCommand command = (AddDentistCommand) parser.parseCommand(
            PersonUtil.getAddDentistCommand(dentist));
        assertEquals(new AddDentistCommand(dentist), command);
    }

    @Test
    public void parseCommand_addPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddPatientCommand command = (AddPatientCommand) parser.parseCommand(
            PersonUtil.getAddPatientCommand(patient));
        assertEquals(new AddPatientCommand(patient), command);
    }

    @Test
    public void parseCommand_addTreatment() throws Exception {
        Treatment treatment = new TreatmentBuilder().build();
        AddTreatmentCommand command =
            (AddTreatmentCommand) parser.parseCommand(PersonUtil.getAddTreatmentCommand(treatment));
        assertEquals(new AddTreatmentCommand(treatment), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deletePatient() throws Exception {
        DeletePatientCommand command = (DeletePatientCommand) parser.parseCommand(
            DeletePatientCommand.COMMAND_WORD + " " + FIRST_PATIENT_ID);
        assertEquals(new DeletePatientCommand(FIRST_PATIENT_ID), command);
    }

    @Test
    public void parseCommand_deleteDentist() throws Exception {
        DeleteDentistCommand command = (DeleteDentistCommand) parser.parseCommand(
            DeleteDentistCommand.COMMAND_WORD + " " + FIRST_DENTIST_ID);
        assertEquals(new DeleteDentistCommand(FIRST_DENTIST_ID), command);
    }

    @Test
    public void parseCommand_deleteAppointment() throws Exception {
        DeleteAppointmentCommand command = (DeleteAppointmentCommand) parser.parseCommand(
            DeleteAppointmentCommand.COMMAND_WORD + " " + FIRST_APPOINTMENT_ID);
        assertEquals(new DeleteAppointmentCommand(FIRST_APPOINTMENT_ID), command);
    }

    @Test
    public void parseCommand_deleteTreatment() throws Exception {
        DeleteTreatmentCommand command = (DeleteTreatmentCommand) parser.parseCommand(
            DeleteTreatmentCommand.COMMAND_WORD + " " + FIRST_TREATMENT_NAME);
        assertEquals(new DeleteTreatmentCommand(FIRST_TREATMENT_NAME), command);
    }

    @Test
    public void parseCommand_editPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(
            patient).build();
        EditPatientCommand command =
            (EditPatientCommand) parser.parseCommand(EditPatientCommand.COMMAND_WORD + " "
                + FIRST_PATIENT_ID + " " + PersonUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditPatientCommand(FIRST_PATIENT_ID, descriptor), command);
    }


    @Test
    public void parseCommand_editDentist() throws Exception {
        Dentist dentist = new DentistBuilder().build();
        EditDentistCommand.EditDentistDescriptor descriptor = new EditDentistDescriptorBuilder(
            dentist).build();
        descriptor.setDentistId(0);
        EditDentistCommand command =
            (EditDentistCommand) parser.parseCommand(EditDentistCommand.COMMAND_WORD + " "
                + FIRST_DENTIST_ID + " " + PersonUtil.getEditDentistDescriptorDetails(descriptor));
        assertEquals(new EditDentistCommand(FIRST_DENTIST_ID, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listPatient() throws Exception {
        assertTrue(
            parser.parseCommand(ListPatientCommand.COMMAND_WORD) instanceof ListPatientCommand);
        assertTrue(parser.parseCommand(
            ListPatientCommand.COMMAND_WORD + " 3") instanceof ListPatientCommand);
    }

    @Test
    public void parseCommand_listDentist() throws Exception {
        assertTrue(
            parser.parseCommand(ListDentistCommand.COMMAND_WORD) instanceof ListDentistCommand);
        assertTrue(parser.parseCommand(
            ListDentistCommand.COMMAND_WORD + " 3") instanceof ListDentistCommand);
    }

    @Test
    public void parseCommand_listAppointment() throws Exception {
        assertTrue(parser.parseCommand(
            ListAppointmentCommand.COMMAND_WORD) instanceof ListAppointmentCommand);
        assertTrue(parser.parseCommand(
            ListAppointmentCommand.COMMAND_WORD + " 3") instanceof ListAppointmentCommand);
    }

    @Test
    public void parseCommand_listTreatment() throws Exception {
        assertTrue(
            parser.parseCommand(ListTreatmentCommand.COMMAND_WORD) instanceof ListTreatmentCommand);
    }

    @Test
    public void parseCommand_filterPatient() throws Exception {
        String attribute = "phone";
        String keywords = "91234567";
        String userInput = FilterPatientCommand.COMMAND_WORD + " a/" + attribute + " k/" + keywords;

        FilterPatientCommand command = (FilterPatientCommand) parser.parseCommand(userInput);
        FilterPatientCommand expectedCommand = new FilterPatientCommand(attribute, keywords);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_filterDentist() throws Exception {
        String attribute = "specialization";
        String keywords = "orthodontics";
        String userInput = FilterDentistCommand.COMMAND_WORD + " a/" + attribute + " k/" + keywords;

        FilterDentistCommand command = (FilterDentistCommand) parser.parseCommand(userInput);
        FilterDentistCommand expectedCommand = new FilterDentistCommand(attribute, keywords);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_filterAppointment() throws Exception {
        String attribute = "dentist";
        long keywords = 0;
        String userInput = FilterAppointmentCommand.COMMAND_WORD + " " + attribute + " " + keywords;
        FilterAppointmentCommand command = (FilterAppointmentCommand) parser.parseCommand(
            userInput);
        FilterAppointmentCommand expectedCommand = new FilterAppointmentCommand(attribute,
            keywords);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_searchPatientByName() throws Exception {
        String keywords = "John Tan";
        NameContainsKeywordsPredicate predicate =
            new NameContainsKeywordsPredicate(Arrays.asList(keywords.split("\\s+")));
        String userInput = SearchPatientCommand.COMMAND_WORD + " " + keywords;
        SearchPatientCommand command = (SearchPatientCommand) parser.parseCommand(userInput);
        SearchPatientCommand expectedCommand = new SearchPatientCommand(predicate);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_searchPatientById() throws Exception {
        long keywords = 1;
        String userInput = SearchPatientCommand.COMMAND_WORD + " " + keywords;
        SearchPatientCommand command = (SearchPatientCommand) parser.parseCommand(userInput);
        SearchPatientCommand expectedCommand = new SearchPatientCommand(keywords);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_searchDentistByName() throws Exception {
        String keywords = "John Tan";
        NameContainsKeywordsPredicate predicate =
            new NameContainsKeywordsPredicate(Arrays.asList(keywords.split("\\s+")));
        String userInput = SearchDentistCommand.COMMAND_WORD + " " + keywords;
        SearchDentistCommand command = (SearchDentistCommand) parser.parseCommand(userInput);
        SearchDentistCommand expectedCommand = new SearchDentistCommand(predicate);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_searchDentistById() throws Exception {
        long keywords = 1;
        String userInput = SearchDentistCommand.COMMAND_WORD + " " + keywords;
        SearchDentistCommand command = (SearchDentistCommand) parser.parseCommand(userInput);
        SearchDentistCommand expectedCommand = new SearchDentistCommand(keywords);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_viewCalendar() throws Exception {
        assertTrue(
            parser.parseCommand(ViewCalendarCommand.COMMAND_WORD) instanceof ViewCalendarCommand);
        assertTrue(parser.parseCommand(
            ViewCalendarCommand.COMMAND_WORD + " 3") instanceof ViewCalendarCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
