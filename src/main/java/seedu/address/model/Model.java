package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.plan.Plan;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Plan> PREDICATE_SHOW_ALL_PLANS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if another person with the same identity as {@code person} exists in the address book.
     */
    boolean hasOtherPerson(Person person, Person originalPerson);

    /**
     * Returns true if a plan with the same identity as {@code plan} exists in the address book.
     */
    boolean hasPlan(Plan plan);

    /**
     * Gets the Person that has the same {@code name} from the list.
     * A Person with the same name must exist in the list, else Exception is thrown.
     */
    Person getPersonByName(Name name);

    /**
     * Gets the Person that has the same {@code name} (case-insensitive) from the list.
     * A Person with the same name must exist in the list, else Exception is thrown.
     */
    Person getPersonByName(String name);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given plan.
     * The plan must exist in the address book.
     */
    void deletePlan(Plan plan);

    /**
     * Marks the given plan as completed.
     * The plan must exist in the address book.
     */
    void completePlan(Plan target);

    /**
     * Marks the given plan as uncomplete.
     * The plan must exist in the address book.
     */
    void uncompletePlan(Plan target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given plan.
     * {@code plan} must not already exist in the address book.
     */
    void addPlan(Plan plan);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given plan {@code target} with {@code editedPlan}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPlan} must not be the same as another existing plan in the address book.
     */
    void setPlan(Plan target, Plan editedPlan);

    /**
     * Replaces the given person (@code target} with {@code editedPerson} in plans which involved {@code target}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void updatePlansWithPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();
    ObservableList<Plan> getFilteredPlanList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered plan list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPlanList(Predicate<Plan> predicate);
}
