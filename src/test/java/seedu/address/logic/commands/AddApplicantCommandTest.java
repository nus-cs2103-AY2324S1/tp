package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE_APPLICANT;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Applicant;
import seedu.address.testutil.ApplicantBuilder;

public class AddApplicantCommandTest {
    @Test
    public void constructor_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApplicantCommand(null));
    }

    @Test
    public void execute_applicantAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingApplicantAdded modelStub = new ModelStubAcceptingApplicantAdded();
        Applicant validApplicant = new ApplicantBuilder().build();

        CommandResult commandResult = new AddApplicantCommand(validApplicant).execute(modelStub);

        assertEquals(String.format(AddApplicantCommand.MESSAGE_SUCCESS, Messages.format(validApplicant)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validApplicant), modelStub.applicantsAdded);
    }

    @Test
    public void execute_duplicateApplicant_throwsCommandException() {
        Applicant validApplicant = new ApplicantBuilder().build();
        AddApplicantCommand addApplicantCommand = new AddApplicantCommand(validApplicant);
        ModelStub modelStub = new ModelStubWithApplicant(validApplicant);

        assertThrows(CommandException.class, AddApplicantCommand.MESSAGE_DUPLICATE_APPLICANT, () ->
                addApplicantCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        Applicant alice = new ApplicantBuilder().withName("Alice").build();
        Applicant bob = new ApplicantBuilder().withName("Bob").build();
        AddApplicantCommand addAliceCommand = new AddApplicantCommand(alice);
        AddApplicantCommand addBobCommand = new AddApplicantCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddApplicantCommand addAliceCommandCopy = new AddApplicantCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddApplicantCommand addApplicantCommand = new AddApplicantCommand(ALICE_APPLICANT);
        String expected = AddApplicantCommand.class.getCanonicalName() + "{toAdd=" + ALICE_APPLICANT + "}";
        assertEquals(expected, addApplicantCommand.toString());
    }



    /**
     * A Model stub that contains a single applicant.
     */
    private class ModelStubWithApplicant extends ModelStub {
        private final Applicant applicant;

        ModelStubWithApplicant(Applicant applicant) {
            requireNonNull(applicant);
            this.applicant = applicant;
        }

        @Override
        public boolean hasApplicant(Applicant applicant) {
            requireNonNull(applicant);
            return this.applicant.isSamePerson(applicant);
        }
    }

    private class ModelStubAcceptingApplicantAdded extends ModelStub {
        final ArrayList<Applicant> applicantsAdded = new ArrayList<>();

        @Override
        public boolean hasApplicant(Applicant applicant) {
            requireNonNull(applicant);
            return applicantsAdded.stream().anyMatch(applicant::isSamePerson);
        }

        @Override
        public void addApplicant(Applicant applicant) {
            requireNonNull(applicant);
            applicantsAdded.add(applicant);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
