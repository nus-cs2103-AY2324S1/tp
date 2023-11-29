package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDentists.DENTIST_ALICE;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.testutil.DentistBuilder;

public class AddDentistCommandTest {

    @Test
    public void constructor_nullDentist_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDentistCommand(null));
    }

    @Test
    public void execute_dentistAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingDentistAdded modelStub = new ModelStubAcceptingDentistAdded();
        Dentist validDentist = new DentistBuilder().build();

        CommandResult commandResult = new AddDentistCommand(validDentist).execute(modelStub);

        assertEquals(
            String.format(AddDentistCommand.MESSAGE_SUCCESS, Messages.format(validDentist)),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDentist), modelStub.dentistAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Dentist validDentist = new DentistBuilder().build();
        AddDentistCommand addDentistCommand = new AddDentistCommand(validDentist);
        ModelStub modelStub = new ModelStubWithDentist(validDentist);

        assertThrows(CommandException.class,
            AddDentistCommand.MESSAGE_DUPLICATE_DENTIST, () -> addDentistCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Dentist alice = new DentistBuilder().withName("Alice").build();
        Dentist bob = new DentistBuilder().withName("Bob").build();
        AddDentistCommand addAliceCommand = new AddDentistCommand(alice);
        AddDentistCommand addBobCommand = new AddDentistCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddDentistCommand addAliceCommandCopy = new AddDentistCommand(alice);
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
        AddDentistCommand addDentistCommand = new AddDentistCommand(DENTIST_ALICE);
        String expected =
            AddDentistCommand.class.getCanonicalName() + "{toAdd=" + DENTIST_ALICE + "}";
        assertEquals(expected, addDentistCommand.toString());
    }


    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithDentist extends ModelStub {

        private final Dentist dentist;

        ModelStubWithDentist(Dentist dentist) {
            requireNonNull(dentist);
            this.dentist = dentist;
        }

        @Override
        public boolean hasDentist(Dentist dentist) {
            requireNonNull(dentist);
            return this.dentist.isSamePerson(dentist);
        }
    }

    /**
     * A Model stub that always accept the dentist being added.
     */
    private class ModelStubAcceptingDentistAdded extends ModelStub {

        final ArrayList<Dentist> dentistAdded = new ArrayList<>();

        @Override
        public boolean hasDentist(Dentist dentist) {
            requireNonNull(dentist);
            return dentistAdded.stream().anyMatch(dentist::isSameDentist);
        }

        @Override
        public void addDentist(Dentist dentist) {
            requireNonNull(dentist);
            dentistAdded.add(dentist);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
