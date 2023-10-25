package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALGROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
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
            + "[" + PREFIX_TUTORIALGROUP + "TUTORIAL_GROUP_ID]\n"
            + "Example: list " + COMMAND_WORD + " w/3 " + "tg/G02";
    public static final String MESSAGE_SUCCESS = "Listed all absent students:";
    public static final String MESSAGE_INCOMPLETE_ATTENDANCE = "Unable to show summary: "
            + "Attendance records are incomplete for week %d.";
    public static final String MESSAGE_NO_STUDENTS = "There are no students in tutorial group %s!";

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
     * @param numberOfPresentees Number of students who were present
     * @param numberOfStudents Number of students in total or in tutorial group
     */
    public String getAttendanceSummary(boolean isWithTag, int numberOfPresentees, int numberOfStudents) {
        return isWithTag
                ? String.format(Messages.MESSAGE_ATTENDANCE_SUMMARY_WITH_TAG, numberOfPresentees, numberOfStudents,
                        week.getWeekNumber(), tag.get().getTagName())
                : String.format(Messages.MESSAGE_ATTENDANCE_SUMMARY_NO_TAG, numberOfPresentees, numberOfStudents,
                        week.getWeekNumber());
    }

    public ArrayList<Person> getUnmarkedPersons(List<Person> personList) {
        ArrayList<Person> unmarkedPersons = new ArrayList<>();
        for (Person p : personList) {
            if (!p.getAttendanceRecords().stream().anyMatch(atd -> atd.getWeek().equals(week))) {
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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isWithTag = false;
        int numberOfStudents = model.getFilteredPersonList().size();

        if (tag.isPresent()) {
            model.addFilter(tutorialPredicate);
            isWithTag = true;
            numberOfStudents = model.getFilteredPersonList().size();

            if (numberOfStudents == 0) {
                return new CommandResult(String.format(MESSAGE_NO_STUDENTS, tag.get().getTagName()));
            }
        }

        ArrayList<Person> unmarkedPersons = getUnmarkedPersons(model.getFilteredPersonList());
        if (!unmarkedPersons.isEmpty()) {
            String nameList = unmarkedPersons.stream().map(person -> person.getName().toString())
                    .collect(Collectors.joining(", "));
            return new CommandResult(String.format(MESSAGE_INCOMPLETE_ATTENDANCE, week.getWeekNumber())
                    + "\n" + "Students with unmarked attendance: " + nameList);
        }

        model.addFilter(absencePredicate);

        int numberOfAbsentees = model.getFilteredPersonList().size();
        int numberOfPresentees = numberOfStudents - numberOfAbsentees;
        String attendanceSummary = getAttendanceSummary(isWithTag, numberOfPresentees, numberOfStudents);

        return new CommandResult(attendanceSummary + MESSAGE_SUCCESS);
    }
}
