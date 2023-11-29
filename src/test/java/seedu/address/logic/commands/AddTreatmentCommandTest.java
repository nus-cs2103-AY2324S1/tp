package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTreatments.WISDOM;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.treatment.Treatment;
import seedu.address.testutil.TreatmentBuilder;

public class AddTreatmentCommandTest {

    @Test
    public void constructor_nullTreatment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTreatmentCommand(null));
    }

    @Test
    public void execute_treatmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTreatmentAdded modelStub = new ModelStubAcceptingTreatmentAdded();
        Treatment validTreatment = new TreatmentBuilder().build();

        CommandResult commandResult = new AddTreatmentCommand(validTreatment).execute(modelStub);

        assertEquals(
            String.format(AddTreatmentCommand.MESSAGE_SUCCESS, Messages.format(validTreatment)),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTreatment), modelStub.treatmentAdded);
    }

    @Test
    public void execute_duplicateTreatment_throwsCommandException() {
        Treatment validTreatment = new TreatmentBuilder().build();
        AddTreatmentCommand addTreatmentCommand = new AddTreatmentCommand(validTreatment);
        ModelStub modelStub = new ModelStubWithTreatment(validTreatment);

        assertThrows(CommandException.class,
            AddTreatmentCommand.MESSAGE_DUPLICATE_TREATMENT, () -> addTreatmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Treatment alice = new TreatmentBuilder().withName("Alice").build();
        Treatment bob = new TreatmentBuilder().withName("Bob").build();
        AddTreatmentCommand addAliceCommand = new AddTreatmentCommand(alice);
        AddTreatmentCommand addBobCommand = new AddTreatmentCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddTreatmentCommand addAliceCommandCopy = new AddTreatmentCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different Treatment -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddTreatmentCommand addTreatmentCommand = new AddTreatmentCommand(WISDOM);
        String expected =
            AddTreatmentCommand.class.getCanonicalName() + "{toAdd=" + WISDOM + "}";
        assertEquals(expected, addTreatmentCommand.toString());
    }


    /**
     * A Model stub that contains a single Treatment.
     */
    private class ModelStubWithTreatment extends ModelStub {

        private final Treatment treatment;

        ModelStubWithTreatment(Treatment dentist) {
            requireNonNull(dentist);
            this.treatment = dentist;
        }

        @Override
        public boolean hasTreatment(Treatment treatment) {
            requireNonNull(treatment);
            return this.treatment.isSameTreatment(treatment);
        }
    }

    /**
     * A Model stub that always accept the dentist being added.
     */
    private class ModelStubAcceptingTreatmentAdded extends ModelStub {

        final ArrayList<Treatment> treatmentAdded = new ArrayList<>();

        @Override
        public boolean hasTreatment(Treatment treatment) {
            requireNonNull(treatment);
            return treatmentAdded.stream().anyMatch(treatment::isSameTreatment);
        }

        @Override
        public void addTreatment(Treatment treatment) {
            requireNonNull(treatment);
            treatmentAdded.add(treatment);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
