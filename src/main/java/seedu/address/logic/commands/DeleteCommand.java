package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALGROUP;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed person list,\n"
            + " all students identified by the tutorial group ID entered or all students.\n"
            + "Parameters: INDEX (must be a positive integer) || "
            + "all [" + PREFIX_TUTORIALGROUP + "TUTORIAL_GROUP_ID]\n"
            + "Examples: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " all, "
            + COMMAND_WORD + " all " + PREFIX_TUTORIALGROUP + "G01";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_TAGGED_SUCCESS = "Deleted all contacts from Tutorial Group %1$s";
    public static final String MESSAGE_DELETE_NO_TAG_SUCCESS = "Deleted all contacts";

    private final Index targetIndex;
    private final Optional<Tag> tag;
    private final ContainsTagPredicate tagPredicate;

    /**
     * @param targetIndex Index number used in the displayed person list of the target
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.tag = null;
        this.tagPredicate = null;
    }

    /**
     * @param tag Course and tutorial groups to delete all students from
     * @param tagPredicate Predicate used to filter for students in the course and tutorial group
     */
    public DeleteCommand(Optional<Tag> tag, ContainsTagPredicate tagPredicate) {
        this.targetIndex = null;
        this.tag = tag;
        this.tagPredicate = tagPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (!(targetIndex == null)) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
        }

        if (tag.isPresent()) {
            model.addFilter(tagPredicate);
        }

        List<Person> toDeleteList = model.getFilteredPersonList();
        List<Person> copyDeleteList = new ArrayList<>(toDeleteList);

        for (Person p : copyDeleteList) {
            model.deletePerson(p);
        }

        model.clearFilters();

        return tag.isPresent()
                ? new CommandResult(String.format(MESSAGE_DELETE_TAGGED_SUCCESS, tag.get().getTagName()))
                : new CommandResult(String.format(MESSAGE_DELETE_NO_TAG_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;

        if (tag == null && otherDeleteCommand.tag == null) {
            return targetIndex.equals(otherDeleteCommand.targetIndex);
        }

        return tag.equals(otherDeleteCommand.tag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
