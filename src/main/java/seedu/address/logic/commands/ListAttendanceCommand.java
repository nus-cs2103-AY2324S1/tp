package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALGROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.person.AbsentFromTutorialNumPredicate;
import seedu.address.model.person.ContainsTutorialGroupPredicate;
import seedu.address.model.person.TutorialGroup;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAttendanceCommand extends ListCommand {

    public static final String COMMAND_WORD = "attendance";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists summary of attendance and absent students.\n"
            + "Parameters: "
            + PREFIX_TUTORIALGROUP + " TUTORIALGROUPID "
            + PREFIX_TUTORIALNUMBER + " TUTORIALNUMBER (must be a positive integer) "
            + "Example: list " + COMMAND_WORD + " tg/ G01 " + "tn/1";
    public static final String MESSAGE_SUCCESS = "Listed all absent students";

    private final Index tn;
    private final AbsentFromTutorialNumPredicate absencePredicate;

    /**
     * @param tg Tutorial group to list
     * @param tn Tutorial number to list
     * @param tutorialGroupPredicate Predicate used to filter for students in the tutorial group
     * @param absencePredicate Predicate used to filter for students absent
     */
    public ListAttendanceCommand(TutorialGroup tg, Index tn,
                                 ContainsTutorialGroupPredicate tutorialGroupPredicate,
                                 AbsentFromTutorialNumPredicate absencePredicate) {
        super(tg, tutorialGroupPredicate);
        requireNonNull(tn);
        this.tn = tn;
        this.absencePredicate = absencePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        TutorialGroup placeholder = new TutorialGroup("PLACEHOLDER");
        String tutorialStr = String.format(" for Tutorial #%d", tn.getOneBased());

        if (!tg.equals(placeholder)) {
            model.updateFilteredPersonList(tutorialGroupPredicate);
            String tutorialGroupStr = String.format(" from Tutorial Group %s.", tg);
            tutorialStr += tutorialGroupStr;
        }

        int numberOfStudents = model.getFilteredPersonList().size();

        model.updateFilteredPersonList(absencePredicate);
        int numberOfAbsentees = model.getFilteredPersonList().size();
        int numberOfPresentees = numberOfStudents - numberOfAbsentees;

        String attendanceNumberStr = String.format("%d of %d students present.\n",
                numberOfPresentees, numberOfStudents);

        return new CommandResult(attendanceNumberStr + MESSAGE_SUCCESS + tutorialStr);
    }
}
