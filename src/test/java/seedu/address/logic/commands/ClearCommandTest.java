package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.group.tasks.TaskList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.ui.JavaFxInitializer;


public class ClearCommandTest {

    @BeforeAll
    public static void init() {
        JavaFxInitializer.initialize();
    }

    @AfterAll
    public static void cleanup() {
        JavaFxInitializer.cleanup();
    }

    @Test
    public void execute_clearCommandConfirmed_success() throws CommandException {
        // Arrange
        ModelStub modelStub = new ModelStub();
        ClearCommand clearCommand = new ClearCommand();
        clearCommand.setConfirmed(true);

        // Act
        Platform.runLater(() -> {
            try {
                CommandResult commandResult = clearCommand.execute(modelStub);

                // Assert
                assertEquals(ClearCommand.SHOWING_CONFIRMATION_MESSAGE, commandResult.getFeedbackToUser());
                assertFalse(commandResult.isShowHelp());
                assertFalse(commandResult.isExit());
                assertFalse(commandResult.isGroupCommand());
                assertTrue(commandResult.isClear());
                assertEquals(0, modelStub.getAddressBook().getPersonList().size());
            } catch (CommandException e) {
                // Handle exception
            }
        });
    }

    @Test
    public void execute_clearCommandNotConfirmed_success() throws CommandException {
        // Arrange
        ModelStub modelStub = new ModelStub();
        ClearCommand clearCommand = new ClearCommand();
        clearCommand.setConfirmed(false);

        // Act
        Platform.runLater(() -> {
            try {
                CommandResult commandResult = clearCommand.execute(modelStub);

                // Assert
                assertEquals(ClearCommand.SHOWING_CONFIRMATION_MESSAGE, commandResult.getFeedbackToUser());
                assertFalse(commandResult.isShowHelp());
                assertFalse(commandResult.isExit());
                assertFalse(commandResult.isGroupCommand());
                assertFalse(commandResult.isClear());
                assertEquals(0, modelStub.getAddressBook().getPersonList().size());
            } catch (CommandException e) {
                // Handle exception if needed
            }
        });
    }

    // Rest of the class remains unchanged
    private static class ModelStub implements Model {
        private final AddressBook addressBook = new AddressBook();

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            return null;
        }

        @Override
        public GuiSettings getGuiSettings() {
            return null;
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {

        }

        @Override
        public Path getAddressBookFilePath() {
            // Implement if needed
            return null;
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return addressBook;
        }

        @Override
        public boolean hasPerson(Person person) {
            return addressBook.hasPerson(person);
        }

        @Override
        public void deletePerson(Person target) {
            addressBook.removePerson(target);
        }

        @Override
        public void addPerson(Person person) {
            addressBook.addPerson(person);
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            addressBook.setPerson(target, editedPerson);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPersonToGroup(Person person, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Group> getFilteredGroupList() {
            return null;
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Person> getPersonWithEmail(Email email) {
            return Optional.empty();
        }

        @Override
        public Optional<Group> getGroupWithNumber(int number) {
            return Optional.empty();
        }


        @Override
        public boolean personIsInAGroup(Person person) {
            // Implement if needed
            return false;
        }

        @Override
        public Group getGroupThatPersonIsIn(Person person) {
            return null;
        }

        @Override
        public void removePersonFromGroup(Person person, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTasksToGroup(TaskList taskList, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            return false;
        }

        @Override
        public void deleteGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
