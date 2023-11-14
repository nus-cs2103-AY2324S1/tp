package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.SortCommand;
import seedu.address.model.internship.Internship;

/**
 * The API of the Model component.
 */
public interface InternshipModel {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Internship> PREDICATE_SHOW_ALL_INTERNSHIPS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyInternshipUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyInternshipUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' internship book file path.
     */
    Path getInternshipBookFilePath();

    /**
     * Sets the user prefs' internship book file path.
     */
    void setInternshipBookFilePath(Path internshipBookFilePath);

    /**
     * Replaces internship book data with the data in {@code internshipBook}.
     */
    void setInternshipBook(ReadOnlyInternshipBook internshipBook);

    /**
     * Returns the internshipBook
     */
    ReadOnlyInternshipBook getInternshipBook();

    /**
     * Returns true if an internship with the same identity as {@code internship} exists in the internship book.
     */
    boolean hasInternship(Internship internship);

    /**
     * Deletes the given internship.
     * The internship must exist in the internship book.
     */
    void deleteInternship(Internship target);


    void createInternship(Internship internship);

    /**
     * Replaces the given internship {@code target} with {@code editedInternship}.
     * {@code target} must exist in the internship book.
     * The person identity of {@code editedInternship} must not be the same as another existing internship in the
     * internship book.
     */
    void setInternship(Internship target, Internship editedInternship);

    /**
     * Sorts the internship book using the {@code comparator}.
     */
    void sortInternships(Comparator<Internship> comparator);

    /**
     * Updates the current sort comparator of internship book using the {@code comparator}.
     */
    void updateSortComparator(Comparator<Internship> comparator);

    /**
     * Gets the current comparator order.
     */
    SortCommand.Order getComparatorOrder();

    /**
     * Sets the comparator order.
     */
    void setComparatorOrder(SortCommand.Order order);

    /**
     * Gets the current comparator prefix.
     */
    String getComparatorPrefix();

    /**
     * Sets the comparator prefix.
     */
    void setComparatorPrefix(String prefix);

    /**
     * Returns an unmodifiable view of the filtered internship list
     */
    ObservableList<Internship> getFilteredInternshipList();


    /**
     * Updates the filter of the filtered internship list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<Internship> predicate);

    void updatePredicate(Predicate<Internship> predicate);

    void setFilterParameter(String filterParameter);

    void setFilterValue(String filterValue);

    String getFilterParameter();

    String getFilterValue();
}
