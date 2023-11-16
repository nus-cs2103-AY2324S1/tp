package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDevelopers.ALICE;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.developer.Developer;
import seedu.address.testutil.DeveloperBuilder;

public class AddDeveloperCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDeveloperCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingDeveloperAdded modelStub = new ModelStubAcceptingDeveloperAdded();
        Developer validDeveloper = new DeveloperBuilder().build();

        CommandResult commandResult = new AddDeveloperCommand(validDeveloper).execute(modelStub);

        assertEquals(String.format(AddDeveloperCommand.MESSAGE_SUCCESS, Messages.format(validDeveloper)),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validDeveloper), modelStub.developersAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Developer validDeveloper = new DeveloperBuilder().build();
        AddDeveloperCommand addDeveloperCommand = new AddDeveloperCommand(validDeveloper);
        ModelStub modelStub = new ModelStubWithDeveloper(validDeveloper);

        assertThrows(CommandException.class, AddDeveloperCommand.MESSAGE_DUPLICATE_DEVELOPER, ()
                -> addDeveloperCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Developer alice = new DeveloperBuilder().withName("Alice").build();
        Developer bob = new DeveloperBuilder().withName("Bob").build();
        AddDeveloperCommand addAliceCommand = new AddDeveloperCommand(alice);
        AddDeveloperCommand addBobCommand = new AddDeveloperCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddDeveloperCommand addAliceCommandCopy = new AddDeveloperCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different developer -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);
    }

    @Test
    public void toStringMethod() {
        AddDeveloperCommand addDeveloperCommand = new AddDeveloperCommand(ALICE);
        String expected = AddDeveloperCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addDeveloperCommand.toString());
    }

    /**
     * A Model stub that contains a single developer.
     */
    private class ModelStubWithDeveloper extends ModelStub {
        private final Developer developer;

        ModelStubWithDeveloper(Developer developer) {
            requireNonNull(developer);
            this.developer = developer;
        }

        @Override
        public boolean hasDeveloper(Developer developer) {
            requireNonNull(developer);
            return this.developer.isSamePerson(developer);
        }
    }

    /**
     * A Model stub that always accept the developer being added.
     */
    private class ModelStubAcceptingDeveloperAdded extends ModelStub {
        final ArrayList<Developer> developersAdded = new ArrayList<>();

        @Override
        public boolean hasDeveloper(Developer developer) {
            requireNonNull(developer);
            return developersAdded.stream().anyMatch(developer::isSamePerson);
        }

        @Override
        public void addDeveloper(Developer developer) {
            requireNonNull(developer);
            developersAdded.add(developer);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
