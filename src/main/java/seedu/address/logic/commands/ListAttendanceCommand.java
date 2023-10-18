package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSETUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.AbsentFromTutorialNumPredicate;
import seedu.address.model.person.ContainsCourseTutorialPredicate;
import seedu.address.model.tag.Tag;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAttendanceCommand extends ListCommand {

    public static final String COMMAND_WORD = "attendance";
    public static final String MESSAGE_USAGE = "list " + COMMAND_WORD
            + ": Lists summary of attendance and absent students.\n"
            + "Parameters: "
            + PREFIX_COURSETUTORIAL + "TAG "
            + PREFIX_TUTORIALNUMBER + "TUTORIALNUMBER (must be a positive integer)\n"
            + "Example: list " + COMMAND_WORD + " coursetg/CS2103 " + "tn/1";
    public static final String MESSAGE_SUCCESS = "Listed all absent students:";

    private final Index tn;
    private final AbsentFromTutorialNumPredicate absencePredicate;
    private final Tag tag;
    private final ContainsCourseTutorialPredicate courseTutorialPredicate;

    /**
     * @param tag Tutorial group to list
     * @param tn Tutorial number to list
     * @param courseTutorialPredicate Predicate used to filter for students in the tutorial group
     * @param absencePredicate Predicate used to filter for students absent
     */
    public ListAttendanceCommand(Tag tag, Index tn,
                                 ContainsCourseTutorialPredicate courseTutorialPredicate,
                                 AbsentFromTutorialNumPredicate absencePredicate) {
        requireNonNull(tn);
        this.tn = tn;
        this.absencePredicate = absencePredicate;
        this.tag = tag;
        this.courseTutorialPredicate = courseTutorialPredicate;
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
        return tag.equals(otherListAttendanceCommand.tag) && tn.equals(otherListAttendanceCommand.tn);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Tag placeholder = new Tag("PLACEHOLDER");

        String attendanceSummary;
        int numberOfStudents = model.getFilteredPersonList().size();


        if (!tag.equals(placeholder)) {
            model.updateFilteredPersonList(courseTutorialPredicate);
            numberOfStudents = model.getFilteredPersonList().size();
            model.updateFilteredPersonList(absencePredicate);
            int numberOfAbsentees = model.getFilteredPersonList().size();
            int numberOfPresentees = numberOfStudents - numberOfAbsentees;

            attendanceSummary = String.format(Messages.MESSAGE_ATTENDANCE_SUMMARY_WITH_TAG, numberOfPresentees,
                    numberOfStudents, tn.getOneBased(), tag.getTagName());
            return new CommandResult(attendanceSummary + MESSAGE_SUCCESS);
        }


        model.updateFilteredPersonList(absencePredicate);
        int numberOfAbsentees = model.getFilteredPersonList().size();
        int numberOfPresentees = numberOfStudents - numberOfAbsentees;

        attendanceSummary = String.format(Messages.MESSAGE_ATTENDANCE_SUMMARY_NO_TAG, numberOfPresentees,
                numberOfStudents, tn.getOneBased());

        return new CommandResult(attendanceSummary + MESSAGE_SUCCESS);
    }
}
