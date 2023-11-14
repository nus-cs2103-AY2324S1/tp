package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IsBookmarked;
import seedu.address.model.person.Person;


/**
 * Unbookmarks an applicant from the list of all applicants.
 */
public class UnbookmarkCommand extends Command {

    public static final String COMMAND_WORD = "unbookmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unbookmarks an applicant, identified by the index number "
            + "used in the last list, from all future lists of applicants.\n"
            + "Parameter: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_UNBOOKMARK_APPLICANT_SUCCESS = "Applicant at index %1$s has been "
            + "successfully unbookmarked.";

    public final Index targetIndex;

    /**
     * Constructor for UnbookmarkCommand.
     * @param targetIndex
     */
    public UnbookmarkCommand(Index targetIndex) {
        requireAllNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (this.targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnbookmark = lastShownList.get(targetIndex.getZeroBased());
        Person unbookmarkedPerson = new Person(
                personToUnbookmark.getStudentNumber(),
                personToUnbookmark.getName(),
                personToUnbookmark.getPhone(),
                personToUnbookmark.getEmail(),
                personToUnbookmark.getGpa(),
                personToUnbookmark.getPreviousGrade(),
                personToUnbookmark.getInterviewScore(),
                personToUnbookmark.getComment(),
                personToUnbookmark.getTags(),
                personToUnbookmark.getAttachments(),
                personToUnbookmark.getIsHidden(),
                new IsBookmarked(false));
        model.setPerson(personToUnbookmark, unbookmarkedPerson);
        return new CommandResult(String.format(MESSAGE_UNBOOKMARK_APPLICANT_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UnbookmarkCommand)) {
            return false;
        }

        UnbookmarkCommand e = (UnbookmarkCommand) other;
        return targetIndex.equals(e.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
