package seedu.address.testutil;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyFullTaskList;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;
import seedu.address.model.person.Person;
import seedu.address.model.state.State;
import seedu.address.ui.Ui;

/**
 * A default model stub that have all of the methods failing.
 */

public class ModelStub implements Model {
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
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getScheduleListFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setScheduleListFilePath(Path scheduleListFilePath) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void addLesson(Lesson lesson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setScheduleList(ReadOnlySchedule newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlySchedule getScheduleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasLesson(Lesson lesson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteLesson(Lesson target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setLesson(Lesson target, Lesson editedLesson) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public ObservableList<Lesson> getFilteredScheduleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredScheduleList(Predicate<Lesson> predicate) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void showLesson(Lesson lessonToShow) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void showPerson(Person personToShow) {
    }
    @Override
    public void showTask(Task taskToShow) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void linkUi(Ui ui) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public State getState() {
        return State.STUDENT;
    }

    @Override
    public void setState(State state) {
        //throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean sameState(State state) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCurrentShownEntry() {
        return false;
    }

    @Override
    public ReadOnlyFullTaskList getFullTaskListObject() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Task> getFullTaskList() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void updateFullTaskList() {
        throw new AssertionError("This method should not be called.");
    };

    @Override
    public Boolean hasPersonClashWith(Person person) {
        return null;
    }

    @Override
    public Person getPersonClashWith(Person person) {
        return null;
    }

    @Override
    public Set<Person> getPersonsFulfill(Predicate<Person> predicate) {
        return null;
    }

    public void linkWith(Person person, Lesson lesson) {

    }

    @Override
    public void unLinkWith(Person person, Lesson lesson) {

    }

    @Override
    public String getLinkedPersonNameStr(Lesson lesson) {

        return null;
    }

    public String getLinkedLessonNameStr(Person person) {
        return null;
    }

    @Override
    public boolean hasLessonClashWith(Lesson lesson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Set<Lesson> getLessonsFulfill(Predicate<Lesson> predicate) {
        return null;
    }

    @Override
    public Lesson getLessonClashWith(Lesson lesson) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void addTask(Task task, int index) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public int getTaskClashWith(Task task, int index) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public boolean hasTaskClashWith(Task task, int index) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public String deleteTask(Lesson lesson, int index) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void resetAllShowFields() {
    }
}
