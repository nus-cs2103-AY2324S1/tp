package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.developer.Developer;
import seedu.address.model.person.Person;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;

/**
 * A default model stub that have all of its methods failing.
 */
public class ModelStub implements Model {
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addDeveloper(Developer developer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addClient(Client person) {

    }

    @Override
    public void addProject(Project person) {

    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasDeveloper(Developer developer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasClient(Client client) {
        return false;
    }

    @Override
    public boolean hasProject(Project project) {
        return false;
    }

    @Override
    public String areProjectsValid(Person person) {
        return null;
    }

    @Override
    public void deleteDeveloper(Developer target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteClient(Client target) {

    }

    @Override
    public void deleteProject(Project target) {

    }

    @Override
    public void setDeveloper(Developer target, Developer editedDeveloper) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setClient(Client target, Client editedClient) {

    }

    @Override
    public void setProject(Project target, Project editedProject) {

    }

    @Override
    public ObservableList<Developer> getFilteredDeveloperList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return null;
    }

    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return null;
    }

    @Override
    public void updateFilteredDeveloperList(Predicate<Developer> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {

    }

    @Override
    public void updateFilteredProjectList(Predicate<Project> predicate) {

    }

    @Override
    public void updateFilteredProjectDeadlineList(Predicate<Deadline> predicate) {

    }

    @Override
    public void commitAddressBook(Model model, String message, TabIndex index) {

    }

    @Override
    public void undoAddressBook(Model model) throws CommandException {

    }

    @Override
    public void redoAddressBook(Model model) throws CommandException {

    }

    @Override
    public String getPreviousCommandForRedo() throws CommandException {
        return null;
    }

    @Override
    public String getPreviousCommandForUndo() throws CommandException {
        return null;
    }

    @Override
    public TabIndex getPreviousTabIndex() {
        return null;
    }

    @Override
    public TabIndex getPreviousTabIndexForRedo() {
        return null;
    }
}
