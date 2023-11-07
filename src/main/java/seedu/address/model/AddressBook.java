package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.UniqueApplicantList;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameApplicant and .isNotValidOrNewInterview comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueApplicantList applicants;
    private final UniqueInterviewList interviews;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        applicants = new UniqueApplicantList();
        interviews = new UniqueInterviewList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Applicants in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the applicant list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     */
    public void setApplicants(List<Applicant> applicants) {
        this.applicants.setApplicants(applicants);
    }

    /**
     * Replaces the contents of the interview list with {@code interviews}.
     * {@code interviews} must not contain duplicate applicants.
     */
    public void setInterviews(List<Interview> interviews) {
        this.interviews.setInterviews(interviews);
    }
    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setApplicants(newData.getApplicantList());
        setInterviews(newData.getInterviewList());
    }

    //// applicant-level operations

    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in the address book.
     */
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.contains(applicant);
    }

    /**
     * Returns true if an applicant that is equal to {@code applicant} exists in the address book
     * as checked by the equals method.
     * Uses a stricter notion of equality than hasApplicant.
     */
    public boolean hasExactApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.containsExact(applicant);
    }

    /**
     * Adds an applicant to the address book.
     * The applicant must not already exist in the address book.
     */
    public void addApplicant(Applicant p) {
        applicants.add(p);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the address book.
     * The applicant identity of {@code editedApplicant} must not
     * be the same as another existing applicant in the address book.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);

        applicants.setApplicant(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeApplicant(Applicant key) {
        applicants.remove(key);
    }

    //// interview-level operations
    /**
     * Returns true if an interview with the same identity as {@code interview} exists in the address book.
     */
    public boolean hasInterview(Interview interview) {
        requireNonNull(interview);
        return interviews.contains(interview);
    }

    /**
     * Returns true if an interview with a timing that clashes with {@code interview}
     * exists in the address book.
     */
    public boolean hasInterviewClash(Interview interview) {
        requireNonNull(interview);
        return interviews.anyClash(interview);
    }

    /**
     * Adds an interview to the address book.
     * The interview must not already exist in the address book.
     */
    public void addInterview(Interview i) {
        interviews.add(i);
    }

    /**
     * Replaces the given interview {@code target} in the list with {@code editedInterview}.
     * {@code target} must exist in the address book.
     * The interview identity of {@code editedInterview} must not be
     * the same as another existing applicant in the address book.
     */
    public void setInterview(Interview target, Interview editedInterview) {
        requireNonNull(editedInterview);

        interviews.setInterview(target, editedInterview);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeInterview(Interview key) {
        interviews.remove(key);
    }

    /**
     * Finds the interview that contains the applicant and returns it.
     * Returns null if the applicant does not have an interview.
     * {@code applicant} must exist in the address book.
     */
    public Interview findInterviewWithApplicant(Applicant applicant) {
        requireNonNull(applicant);

        for (Interview interview : getInterviewList()) {
            if (interview.getInterviewApplicant().isSameApplicant(applicant)) {
                return interview;
            }
        }
        return null;
    }

    /**
     * Sorts the interview list of the address book.
     * The interviews list will be sorted based on the comparator
     */
    public void sortInterview(Comparator<Interview> comparator) {
        requireNonNull(comparator);

        interviews.sort(comparator);
    }

    /**
     * Lists the pockets of time on a given day.
     *
     * @author Tan Kerway
     * @param day the day to list pockets of time on
     * @return a list of pockets of time available on the given day
     */
    List<Pair<Time, Time>> listPocketsOfTimeOnGivenDay(Time day) {
        Time startOfWorkDay = new Time(LocalDateTime.of(
                day.getDate().getYear(),
                day.getDate().getMonth(),
                day.getDate().getDayOfMonth(),
                Time.WORK_START.getHour(),
                0
                ));
        Time endOfWorkDay = new Time(LocalDateTime.of(
                day.getDate().getYear(),
                day.getDate().getMonth(),
                day.getDate().getDayOfMonth(),
                Time.WORK_END.getHour(),
                0
        ));
        // get the list of interviews on the given day
        List<Interview> temp = new ArrayList<>();
        for (Interview value : interviews) {
            temp.add(value);
        }

        // sort the interviews in ascending order of start time
        temp.sort(Comparator.comparing(Interview::getInterviewStartTime));
        // track the previous end time, initialized to the start of the workday
        Time prevEnd = startOfWorkDay;
        List<Pair<Time, Time>> res = new ArrayList<>();
        // loop over the sorted interviews list
        for (Interview interview : temp) {
            // get the start and end time of the current interview
            Time currentInterviewStart = interview.getInterviewStartTime();
            Time currentInterviewEnd = interview.getInterviewEndTime();
            // case 1: the workday is completely overlapped by the interview
            if (currentInterviewStart.isBefore(startOfWorkDay)
                    && currentInterviewEnd.isAfter(endOfWorkDay)) {
                prevEnd = currentInterviewEnd;
                break;
            }
            // case 2: the workday is outside and before the workday
            if (currentInterviewEnd.isBefore(startOfWorkDay)) {
                continue;
            }
            // case 3: the workday is outside and after the workday
            if (currentInterviewStart.isAfter(endOfWorkDay)) {
                break;
            }
            // case 4: the interview's start point is before the start of workday
            // and the interview's end point is after the start of the workday
            if (currentInterviewStart.isBefore(startOfWorkDay)
                    && currentInterviewEnd.isAfter(startOfWorkDay)) {
                prevEnd = currentInterviewEnd;
                continue;
            }
            // get the current block of free time by taking the end of the last interval
            // and the start of the current interval
            if (!prevEnd.equals(currentInterviewStart)) {
                res.add(new Pair<>(prevEnd, currentInterviewStart));
            }
            prevEnd = currentInterviewEnd;
        }

        // add stray free time, if any
        if (prevEnd.isBefore(endOfWorkDay)) {
            res.add(new Pair<>(prevEnd, endOfWorkDay));
        }
        return res;
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("applicants", applicants)
                .toString();
    }

    @Override
    public ObservableList<Applicant> getApplicantList() {
        return applicants.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Interview> getInterviewList() {
        return interviews.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return applicants.equals(otherAddressBook.applicants)
                && interviews.equals(otherAddressBook.interviews);
    }

    @Override
    public int hashCode() {
        return applicants.hashCode();
    }

}
