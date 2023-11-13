package seedu.letsgethired.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Stack;

import javafx.collections.ObservableList;
import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.UniqueApplicationList;

/**
 * Wraps all data at the intern-tracker level
 * Duplicates are not allowed (by .isSameApplication comparison)\
 */
public class InternTracker implements ReadOnlyInternTracker {

    private UniqueApplicationList internApplications;

    private InternApplication selectedApplication;

    private Stack<UniqueApplicationList> savedStates;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        internApplications = new UniqueApplicationList();
    }

    public InternTracker() {
        this.savedStates = new Stack<>();
    }

    /**
     * Creates an InternTracker using the InternApplications in the {@code toBeCopied}
     */
    public InternTracker(ReadOnlyInternTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the application list with a list of InternApplications{@code
     * internApplications}.
     * {@code internApplications} must not contain duplicate applications.
     */
    public void setInternApplications(List<InternApplication> internApplications) {
        this.internApplications.setApplications(internApplications);
    }

    /**
     * Replaces the contents of the application list with a UniqueApplicationList{@code internApplications}.
     * {@code internApplications} must not contain duplicate applications.
     */
    public void setInternApplications(UniqueApplicationList internApplications) {
        this.internApplications.setApplications(internApplications);
    }

    public UniqueApplicationList getInternApplications() {
        return internApplications;
    }

    /**
     * Resets the existing data of this {@code InternTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyInternTracker newData) {
        requireNonNull(newData);
        setInternApplications(newData.getApplicationList());
    }

    //// intern application-level operations

    /**
     * Returns true if an application with the same identity as {@code internApplication} exists in the
     * intern tracker.
     */
    public boolean hasApplication(InternApplication internApplication) {
        requireNonNull(internApplication);
        return internApplications.contains(internApplication);
    }

    /**
     * Adds an application to the intern tracker.
     * The application must not already exist in the intern tracker.
     */
    public void addApplication(InternApplication a) {
        internApplications.add(a);
    }
    /**
     * Replaces the given application {@code target} in the list with {@code editedInternApplication}.
     * {@code target} must exist in the intern tracker.
     * The application identity of {@code editedInternApplication} must not be the same as another existing
     * application in the intern tracker.
     */
    public void setApplication(InternApplication target, InternApplication editedInternApplication) {
        requireNonNull(editedInternApplication);
        internApplications.setApplication(target, editedInternApplication);
    }

    /**
     * Removes {@code key} from this {@code InternTracker}.
     * {@code key} must exist in the intern tracker.
     */
    public void removeApplication(InternApplication key) {
        internApplications.remove(key);
        if (selectedApplication != null && selectedApplication.equals(key)) {
            selectedApplication = null;
        }
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("applications", internApplications)
                .toString();
    }

    @Override
    public ObservableList<InternApplication> getApplicationList() {
        return internApplications.asUnmodifiableObservableList();
    }

    public void setSelectedApplication(InternApplication selectedApplication) {
        if (hasApplication(selectedApplication)) {
            this.selectedApplication = selectedApplication;
        }
    }

    public InternApplication getSelectedApplication() {
        return this.selectedApplication;
    }

    /**
     * Deletes all existing intern applications being tracked
     */
    public void clear() {
        internApplications = new UniqueApplicationList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternTracker)) {
            return false;
        }

        InternTracker otherInternTracker = (InternTracker) other;
        return internApplications.equals(otherInternTracker.internApplications);
    }

    @Override
    public int hashCode() {
        return internApplications.hashCode();
    }
}
