package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IsHidden;
import seedu.address.model.person.Person;


/**
 * Unhides an applicant from the list of all applicants.
 */
public class UnhideCommand extends Command {

    public static final String COMMAND_WORD = "unhide";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unhides an applicant, identified by the index number "
            + "used in the last list, in all future lists of applicants.\n"
            + "Parameter: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_UNHIDE_APPLICANT_SUCCESS = "Applicant %1$s unhidden from lists.";

    public final Index targetIndex;

    /**
     * Constructor for UnhideCommand.
     * @param targetIndex
     */
    public UnhideCommand(Index targetIndex) {
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

        Person personToUnhide = lastShownList.get(targetIndex.getZeroBased());
        model.setPerson(personToUnhide, createUnhiddenPerson(personToUnhide));
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_UNHIDDEN_PERSONS);
        return new CommandResult(String.format(MESSAGE_UNHIDE_APPLICANT_SUCCESS, Messages.format(personToUnhide)));
    }

    /**
     * Creates and returns an unhidden {@code Person} with the details of {@code personToUnhide}
     * @param personToUnhide {@code Person} to unhide
     * @return {@code Person} with {@code IsHidden} set to false
     */
    private static Person createUnhiddenPerson(Person personToUnhide) {
        assert personToUnhide != null;

        return new Person(
                personToUnhide.getStudentNumber(),
                personToUnhide.getName(),
                personToUnhide.getPhone(),
                personToUnhide.getEmail(),
                personToUnhide.getGpa(),
                personToUnhide.getPreviousGrade(),
                personToUnhide.getInterviewScore(),
                personToUnhide.getComment(),
                personToUnhide.getTags(),
                personToUnhide.getAttachments(),
                new IsHidden(false),
                personToUnhide.getIsBookmarked());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UnhideCommand)) {
            return false;
        }

        UnhideCommand e = (UnhideCommand) other;
        return targetIndex.equals(e.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
