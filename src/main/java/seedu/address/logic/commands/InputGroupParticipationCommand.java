package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION_POINTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Group;
import seedu.address.model.person.Person;

/**
 * Class to input participation points to an existing group of students for a specified week
 */
public class InputGroupParticipationCommand extends Command {
    public static final String COMMAND_WORD = "inputGroupPP";
    public static final String SUCCESS_MSG = "Participation points input successfully!\n"
            + "For students with unmarked attendance, they will not get any participation points.";
    public static final String ATTENDANCE_NOT_MARKED = "Before inputting participation points, "
            + "mark the attendance of the student first!";
    public static final String ATTENDANCE_ABSENT = "A student was absent for this tutorial!";
    public static final int MAXIMUM_PARTICIPATION_POINTS = 1000;

    public static final String PARTICIPATION_POINTS_OUT_OF_RANGE = "Participation points "
            + "must be between 0 and " + MAXIMUM_PARTICIPATION_POINTS + ".";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Inserts participation points to the group of students identified\n"
            + "by the group name used in the displayed student list.\n"
            + "Parameters: GROUP "
            + PREFIX_TUTORIAL + "TutorialToMark \n"
            + PREFIX_PARTICIPATION_POINTS + "ParticipationPoints \n"
            + "Example: " + COMMAND_WORD + " tut33 t/1 pp/200";

    private final Group group;
    private final Index tut;
    private final int points;

    /**
     * Constructor for inputGroupParticipationCommand
     * @param group group name of students to update
     * @param tut tutorial to update
     */
    public InputGroupParticipationCommand(Group group, Index tut, int points) {
        this.group = group;
        this.tut = tut;
        this.points = points;
    }

    /**
     * Executes the InputGroupParticipationCommand to input participation for a student on a specific tutorial.
     *
     * @param model The model that the command operates on.
     * @return A CommandResult indicating the outcome of the execution.
     * @throws CommandException If there is an error in executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList().filtered(x -> x.getGroup().equals(this.group));
        if (lastShownList.isEmpty()) {
            throw new CommandException(Messages.INVALID_GROUP);
        }

        if (this.points < 0 || this.points > MAXIMUM_PARTICIPATION_POINTS) {
            throw new CommandException(PARTICIPATION_POINTS_OUT_OF_RANGE);
        }

        for (Person studentToEdit : lastShownList) {
            Attendance studentAtd = studentToEdit.getAttendance();
            if (studentAtd.isPresent(this.tut.getZeroBased())) {
                studentAtd.inputParticipationPoints(this.tut.getZeroBased(), this.points);
                model.updatePerson(studentToEdit);
            }
        }
        return new CommandResult(SUCCESS_MSG);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InputGroupParticipationCommand)) {
            return false;
        }

        InputGroupParticipationCommand o = (InputGroupParticipationCommand) other;
        return group.equals(o.group) && tut.equals(o.tut)
                && points == o.points;
    }
}
