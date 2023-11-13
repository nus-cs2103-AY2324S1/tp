package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.predicate.AbsentFromTutorialPredicate;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.week.Week;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAttendanceCommand extends ListCommand {

    public static final String COMMAND_WORD = "attendance";
    public static final String MESSAGE_USAGE = "list " + COMMAND_WORD
            + ": Lists summary of attendance and absent students.\n"
            + "Parameters: "
            + PREFIX_WEEK + "WEEK_NUMBER "
            + "[" + PREFIX_TUTORIAL_GROUP + "TUTORIAL_GROUP_ID]\n"
            + "Example: list " + COMMAND_WORD + " w/3 " + "tg/G02";

    public static final String MESSAGE_SUCCESS = "Listed all absent students:";
    public static final String MESSAGE_INCOMPLETE_ATTENDANCE = "Unable to show summary: "
            + "Attendance records are incomplete for week %d.";
    public static final String MESSAGE_NO_STUDENTS = "There are no students in %s Tutorial Group %s!";
    public static final String MESSAGE_ATTENDANCE_SUMMARY_WITH_TAG =
            "%1$d of %2$d students present for Week %3$d from %4$s %5$s!\n";
    public static final String MESSAGE_ATTENDANCE_SUMMARY_NO_TAG =
            "%1$d of %2$d students present for Week %3$d from %4$s!\n";


    private final Week week;
    private final AbsentFromTutorialPredicate absencePredicate;
    private final Optional<Tag> tag;
    private final ContainsTagPredicate tutorialPredicate;

    /**
     * @param tag               Tutorial group to list
     * @param week              Tutorial number to list
     * @param tutorialPredicate Predicate used to filter for students in the tutorial group
     * @param absencePredicate  Predicate used to filter for students absent
     */
    public ListAttendanceCommand(Optional<Tag> tag, Week week,
                                 ContainsTagPredicate tutorialPredicate,
                                 AbsentFromTutorialPredicate absencePredicate) {
        requireNonNull(week);
        this.week = week;
        this.absencePredicate = absencePredicate;
        this.tag = tag;
        this.tutorialPredicate = tutorialPredicate;
    }

    /**
     * Returns string containing summary of attendance.
     *
     * @param isWithTag If there is a tag entered
     * @param numOfPresentees Number of students who were present
     * @param numOfStudents Number of students in total or in tutorial group
     */
    public String summaryBuilder(boolean isWithTag, int numOfPresentees, int numOfStudents, String courseCode) {
        assert numOfPresentees <= numOfStudents;
        return isWithTag
                ? String.format(MESSAGE_ATTENDANCE_SUMMARY_WITH_TAG, numOfPresentees, numOfStudents,
                        week.getWeekNumber(), courseCode, tag.get().getTagName())
                : String.format(MESSAGE_ATTENDANCE_SUMMARY_NO_TAG, numOfPresentees, numOfStudents,
                        week.getWeekNumber(), courseCode);
    }

    /**
     * Returns an ArrayList containing persons with unmarked attendance.
     *
     * @param personList List of persons to check if attendance was marked for
     */
    public ArrayList<Person> unmarkedPersonsListBuilder(List<Person> personList) {
        ArrayList<Person> unmarkedPersons = new ArrayList<>();
        for (Person p : personList) {
            if (!p.getAttendanceRecords().stream().anyMatch(atd -> atd.getWeek().equals(week))) {
                // Since all persons in personList are unique, p should never be in unmarkedPersons already
                assert !unmarkedPersons.contains(p);
                unmarkedPersons.add(p);
            }
        }

        return unmarkedPersons;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListAttendanceCommand)) {
            return false;
        }

        ListAttendanceCommand otherListAttendanceCommand = (ListAttendanceCommand) other;
        return tag.equals(otherListAttendanceCommand.tag) && week.equals(otherListAttendanceCommand.week);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearFilters();

        boolean isWithTag = false;
        int numberOfStudents = model.getFilteredPersonList().size();
        String courseCode = model.getAddressBook().getCourseCode();

        if (tag.isPresent()) {
            model.addFilter(tutorialPredicate);

            isWithTag = true;
            numberOfStudents = model.getFilteredPersonList().size();

            if (numberOfStudents == 0) {
                return new CommandResult(String.format(MESSAGE_NO_STUDENTS, courseCode, tag.get().getTagName()));
            }
        }

        ArrayList<Person> unmarkedPersons = unmarkedPersonsListBuilder(model.getFilteredPersonList());
        if (!unmarkedPersons.isEmpty()) {
            String nameList = unmarkedPersons.stream().map(person -> person.getName().toString())
                    .collect(Collectors.joining(", "));
            return new CommandResult(String.format(MESSAGE_INCOMPLETE_ATTENDANCE, week.getWeekNumber())
                    + "\n" + "Students with unmarked attendance: " + nameList);
        }

        model.addFilter(absencePredicate);

        int numberOfAbsentees = model.getFilteredPersonList().size();
        int numberOfPresentees = numberOfStudents - numberOfAbsentees;
        String attendanceSummary = summaryBuilder(isWithTag, numberOfPresentees, numberOfStudents, courseCode);

        return new CommandResult(attendanceSummary + MESSAGE_SUCCESS);
    }
}
