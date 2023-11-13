package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Member;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Tag> PREDICATE_SHOW_ALL_TAGS = unused -> true;

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

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a member with the same identity as {@code member} exists in the address book.
     *
     * @param member The member to check.
     * @return True if the member exists, false otherwise.
     */
    boolean hasMember(Member member);

    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in the address book.
     *
     * @param applicant The applicant to check.
     * @return True if the applicant exists, false otherwise.
     */
    boolean hasApplicant(Applicant applicant);

    /**
     * Deletes the given member.
     * The member must exist in the address book.
     *
     * @param target The member to delete.
     */
    void deleteMember(Member target);

    /**
     * Deletes the given applicant.
     * The applicant must exist in the address book.
     *
     * @param target The applicant to delete.
     */
    void deleteApplicant(Applicant target);

    /**
     * Adds the given member.
     * {@code member} must not already exist in the address book.
     *
     * @param toAdd The member to add.
     */
    void addMember(Member toAdd);

    /**
     * Adds the given applicant.
     * {@code applicant} must not already exist in the address book.
     *
     * @param toAdd The applicant to add.
     */
    void addApplicant(Applicant toAdd);

    /**
     * Replaces the given member {@code target} with {@code editedMember}.
     * {@code target} must exist in the address book.
     * The member identity of {@code editedMember} must not be the same as another existing member in the
     * address book.
     *
     * @param target       The member to replace.
     * @param editedMember The member to replace with.
     */
    void setMember(Member target, Member editedMember);

    /**
     * Replaces the given applicant {@code target} with {@code editedApplicant}.
     * {@code target} must exist in the address book.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant in the
     * address book.
     *
     * @param target          The applicant to replace.
     * @param editedApplicant The applicant to replace with.
     */
    void setApplicant(Applicant target, Applicant editedApplicant);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Member> getFilteredMemberList();

    /**
     * Returns an unmodifiable view of the filtered applicant list
     */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Returns an unmodifiable view of the filtered tag list
     */
    ObservableList<Tag> getFilteredTagList();

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Sets the task list for the member.
     */
    void setTaskListForMember(Member member);

    /**
     * Clears the task list.
     */
    void clearTaskList();

    /**
     * Updates the filter of the filtered member list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate to filter by.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMemberList(Predicate<? super Member> predicate);

    /**
     * Updates the filter of the filtered applicant list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate to filter by.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicantList(Predicate<? super Applicant> predicate);

    /**
     * Updates the filter of the filtered tag list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate to filter by.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTagList(Predicate<? super Tag> predicate);
}
