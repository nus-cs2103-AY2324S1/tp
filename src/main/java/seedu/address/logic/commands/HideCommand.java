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
 * Hides an applicant from the list of all applicants.
 */
public class HideCommand extends Command {

    public static final String COMMAND_WORD = "hide";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Hides an applicant, identified by the index number "
            + "used in the last list, from all future lists of applicants.\n"
            + "Parameter: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_HIDE_APPLICANT_SUCCESS = "Applicant %1$s hidden from lists.";

    public final Index targetIndex;

    /**
     * Constructor for HideCommand.
     * @param targetIndex
     */
    public HideCommand(Index targetIndex) {
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

        Person personToHide = lastShownList.get(targetIndex.getZeroBased());
        model.setPerson(personToHide, createHiddenPerson(personToHide));
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_UNHIDDEN_PERSONS);
        return new CommandResult(String.format(MESSAGE_HIDE_APPLICANT_SUCCESS, Messages.format(personToHide)));
    }

    /**
     * Creates and returns a hidden {@code Person} with the details of {@code personToHide}
     * @param personToHide {@code Person} to hide
     * @return {@code Person} with {@code IsHidden} set to true
     */
    private static Person createHiddenPerson(Person personToHide) {
        assert personToHide != null;

        return new Person(
                personToHide.getStudentNumber(),
                personToHide.getName(),
                personToHide.getPhone(),
                personToHide.getEmail(),
                personToHide.getGpa(),
                personToHide.getPreviousGrade(),
                personToHide.getInterviewScore(),
                personToHide.getComment(),
                personToHide.getTags(),
                personToHide.getAttachments(),
                new IsHidden(true),
                personToHide.getIsBookmarked());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof HideCommand)) {
            return false;
        }

        HideCommand e = (HideCommand) other;
        return targetIndex.equals(e.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
