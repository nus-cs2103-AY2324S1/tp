package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.BatchDeleteCommand.MESSAGE_COMPANY_DESCRIPTION;
import static seedu.address.logic.commands.BatchDeleteCommand.MESSAGE_MONTH_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELETE_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELETE_MONTH_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.month.DeleteMonth;
import seedu.address.model.person.Person;
import seedu.address.model.policy.Company;
import seedu.address.testutil.PersonBuilder;

public class BatchDeleteCommandTest {

    private DeleteMonth month = new DeleteMonth(VALID_DELETE_MONTH);

    // Company: InsureMe
    private Company company = new Company(VALID_COMPANY_BOB);

    private Person alice = new PersonBuilder(ALICE).build();
    private Person benson = new PersonBuilder(BENSON).build();
    private Person carl = new PersonBuilder(CARL).build();

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BatchDeleteCommand(month, null)
                .execute(null));
    }

    @Test
    public void execute_batchDeleteWithMonth_batchDeleteSuccessful() throws CommandException {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        modelStub.addPerson(alice);
        modelStub.addPerson(benson); // only benson policy expiry date not in that month
        modelStub.addPerson(carl);

        CommandResult commandResult = new BatchDeleteCommand(month, null).execute(modelStub);

        assertEquals(String.format(BatchDeleteCommand.MESSAGE_DELETE_PEOPLE_SUCCESS,
                MESSAGE_MONTH_DESCRIPTION + month.toString()), commandResult.getFeedbackToUser());

        assertEquals(Arrays.asList(benson), modelStub.personsAdded);
    }

    @Test
    public void execute_batchDeleteWithCompany_batchDeleteSuccessful() throws CommandException {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        modelStub.addPerson(alice); // only alice policy company not insureMe
        modelStub.addPerson(benson);
        modelStub.addPerson(carl);

        CommandResult commandResult = new BatchDeleteCommand(null, company).execute(modelStub);

        assertEquals(String.format(BatchDeleteCommand.MESSAGE_DELETE_PEOPLE_SUCCESS,
                MESSAGE_COMPANY_DESCRIPTION + company.toString()), commandResult.getFeedbackToUser());

        assertEquals(Arrays.asList(alice), modelStub.personsAdded);
    }

    @Test
    public void execute_invalidBatchDeleteCommands_throwsCommandException() throws CommandException {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();

        BatchDeleteCommand batchDeleteCommand1 = new BatchDeleteCommand(month, company);

        assertThrows(CommandException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        BatchDeleteCommand.MESSAGE_USAGE), () -> batchDeleteCommand1.execute(modelStub));

        BatchDeleteCommand batchDeleteCommand2 = new BatchDeleteCommand(null, null);

        assertThrows(CommandException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        BatchDeleteCommand.MESSAGE_USAGE), () -> batchDeleteCommand2.execute(modelStub));
    }

    @Test
    public void equals_sameBatchDeleteCommand_returnsTrue() {
        assertTrue(new BatchDeleteCommand(month, null)
                .equals(new BatchDeleteCommand(month, null)));

        assertTrue(new BatchDeleteCommand(null, company)
                .equals(new BatchDeleteCommand(null, company)));
    }

    @Test
    public void equals_differentBatchDeleteCommand_returnsFalse() {
        DeleteMonth month2 = new DeleteMonth(VALID_DELETE_MONTH_2);
        Company company2 = new Company(VALID_COMPANY_AMY);

        assertFalse(new BatchDeleteCommand(month, null).equals(null));

        assertFalse(new BatchDeleteCommand(month, null)
                .equals(new BatchDeleteCommand(month2, null)));

        assertFalse(new BatchDeleteCommand(null, company)
                .equals(new BatchDeleteCommand(null, company2)));
    }

    @Test
    public void toStringMethod() {
        BatchDeleteCommand command1 = new BatchDeleteCommand(month, null);
        String expected1 = BatchDeleteCommand.class.getCanonicalName() + "{toBatchDeleteMonth="
                + month + ", toBatchDeleteCompany=[not applicable]}";

        assertEquals(expected1, command1.toString());

        BatchDeleteCommand command2 = new BatchDeleteCommand(null, company);
        String expected2 = BatchDeleteCommand.class.getCanonicalName()
                + "{toBatchDeleteMonth=[not applicable], toBatchDeleteCompany=" + company + "}";

        assertEquals(expected2, command2.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSamePolicyNumber(Person person) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void batchDeleteWithPredicate(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the person being added and allows batchDelete.
     */
    private class ModelStubAcceptingPersonAdded extends BatchDeleteCommandTest.ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public void batchDeleteWithPredicate(Predicate<Person> predicate) {
            requireNonNull(predicate);

            Iterator<Person> iterator = personsAdded.iterator();
            while (iterator.hasNext()) {
                Person person = iterator.next();
                if (predicate.test(person)) {
                    iterator.remove(); // Safely remove the current element
                }
            }
        }
    }
}
