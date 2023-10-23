package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSETUTORIAL;

import java.util.ArrayList;
import java.util.List;

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
            + ": Deletes the student identified by the index number used in the displayed person list\n"
            + "or all students identified by the tag entered.\n"
            + "Parameters: INDEX (must be a positive integer) || "
            + "all " + PREFIX_COURSETUTORIAL + "TAG\n"
            + "Example:\n"
            + COMMAND_WORD + " 1\n"
            + COMMAND_WORD + " all " + PREFIX_COURSETUTORIAL + " CS2103T G01";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_TAGGED_NO_GROUP_SUCCESS = "Deleted all contacts from Course %1$s";
    public static final String MESSAGE_DELETE_TAGGED_WITH_GROUP_SUCCESS = MESSAGE_DELETE_TAGGED_NO_GROUP_SUCCESS
            + ", Tutorial Group %2$s";


    private final Index targetIndex;
    private final Tag tag;
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
    public DeleteCommand(Tag tag, ContainsTagPredicate tagPredicate) {
        this.targetIndex = null;
        this.tag = tag;
        this.tagPredicate = tagPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (tag == null) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
        }

        model.addFilter(tagPredicate);
        List<Person> studentsTaggedList = model.getFilteredPersonList();
        List<Person> copyList = new ArrayList<>(studentsTaggedList);

        for (Person p : copyList) {
            // TODO: add ability to delete the specific tag only when there are multiple tags
            model.deletePerson(p);
        }

        model.clearFilters();

        boolean isCourseOnly = tag.getTutorialGroup() == null;

        return isCourseOnly
                ? new CommandResult(String.format(MESSAGE_DELETE_TAGGED_NO_GROUP_SUCCESS, tag.getCourseCode()))
                : new CommandResult(String.format(
                        MESSAGE_DELETE_TAGGED_WITH_GROUP_SUCCESS, tag.getCourseCode(), tag.getTutorialGroup()));
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
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
