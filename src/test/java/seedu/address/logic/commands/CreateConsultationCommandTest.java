package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OBJ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_OBJ;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalConsultations.CONSULTATION_UNKNOWN_PERSON;
import static seedu.address.testutil.TypicalConsultations.getTypicalConsultationListBook;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ConsultationListBook;
import seedu.address.model.GradedTestListBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyConsultationList;
import seedu.address.model.SessionListBook;
import seedu.address.model.TaskListBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.ConsultationBuilder;
import seedu.address.testutil.ModelStub;


public class CreateConsultationCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskListBook(),
            new SessionListBook(), getTypicalConsultationListBook(), new GradedTestListBook());
    @Test
    public void constructor_nullDetails_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateConsultCommand(null, null, null));
    }

    @Test
    public void execute_consultationAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingConsultationAdded modelStub = new ModelStubAcceptingConsultationAdded();
        Consultation validConsultation = new ConsultationBuilder().build();
        CommandResult commandResult = new CreateConsultCommand(
                validConsultation.getDate(),
                validConsultation.getTime(),
                validConsultation.getStudentsNames()).execute(modelStub);

        assertEquals(String.format(CreateConsultCommand.MESSAGE_SUCCESS, Messages.format(validConsultation)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validConsultation), modelStub.consultationAdded);
    }

    @Test
    public void execute_personNotFound_failure() {
        LocalDate validDate = VALID_DATE_OBJ;
        LocalTime validTime = VALID_TIME_OBJ;
        Set<Name> namesNotFound = CONSULTATION_UNKNOWN_PERSON.getStudentsNames();

        CreateConsultCommand command = new CreateConsultCommand(validDate, validTime, namesNotFound);
        assertCommandFailure(command, model, CreateConsultCommand.MESSAGE_PERSON_NOT_FOUND);
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void getCommandType() {
        Consultation validConsultation = new ConsultationBuilder().build();
        CreateConsultCommand command = new CreateConsultCommand(
                validConsultation.getDate(),
                validConsultation.getTime(),
                validConsultation.getStudentsNames());
        assertEquals(command.getCommandType(), CommandType.CREATE_CONSULT);
    }

    @Test
    public void equals() {
        LocalDate date = VALID_DATE_OBJ;
        LocalTime time = VALID_TIME_OBJ;
        Set<Name> students = SampleDataUtil.getNamesSet(VALID_NAME_AMY);
        CreateConsultCommand createConsultCommand = new CreateConsultCommand(date, time, students);

        // same object -> returns true
        assertTrue(createConsultCommand.equals(createConsultCommand));

        // different types -> returns false
        assertFalse(createConsultCommand.equals(1));

        // null -> returns false
        assertFalse(createConsultCommand.equals(null));
    }

    /**
     * A Model stub that always accept the consultation being added.
     */
    private class ModelStubAcceptingConsultationAdded extends ModelStub {
        final ArrayList<Consultation> consultationAdded = new ArrayList<>();
        final AddressBook addressBook = new AddressBookBuilder().withPerson(AMY).build();

        @Override
        public boolean hasConsultation(Consultation consultation) {
            requireNonNull(consultation);
            return consultationAdded.stream().anyMatch(consultation::isSameConsultation);
        }

        @Override
        public void addConsultation(Consultation consultation) {
            requireNonNull(consultation);
            consultationAdded.add(consultation);
        }

        @Override
        public Person getMatchingStudentName(Name name) {
            requireNonNull(name);
            return addressBook.matchName(name);
        }

        @Override
        public ReadOnlyConsultationList getConsultationList() {
            return new ConsultationListBook();
        }
    }
}
