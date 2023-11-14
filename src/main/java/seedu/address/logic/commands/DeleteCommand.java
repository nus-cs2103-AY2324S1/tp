package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            + " all students in the course  identified by the tutorial group ID entered "
            + "or all students in the course.\n"
            + "Parameters: INDEX (must be a positive integer) || "
            + "all [" + PREFIX_TUTORIAL_GROUP + "TUTORIAL_GROUP_ID]\n"
            + "Examples: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " all, "
            + COMMAND_WORD + " all " + PREFIX_TUTORIAL_GROUP + "G01";

    public static final String MESSAGE_NO_STUDENTS = "No students to delete from %1$s!";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted student: %1$s";
    public static final String MESSAGE_DELETE_TAGGED_SUCCESS = "Deleted all students from %1$s Tutorial Group %2$s!\n"
            + "Deleted student(s):\n%3$s";
    public static final String MESSAGE_DELETE_NO_TAG_SUCCESS = "Deleted all students from %1$s!\n"
            + "Deleted student(s):\n%2$s";

    private final Index targetIndex;
    private final Optional<Tag> tag;
    private final ContainsTagPredicate tagPredicate;

    /**
     * @param targetIndex Index number used in the displayed person list of the target
     */
    public DeleteCommand(Index targetIndex) {
        assert targetIndex != null;
        this.targetIndex = targetIndex;
        this.tag = null;
        this.tagPredicate = null;
    }

    /**
     * @param tag Course and tutorial groups to delete all students from
     * @param tagPredicate Predicate used to filter for students in the course and tutorial group
     */
    public DeleteCommand(Optional<Tag> tag, ContainsTagPredicate tagPredicate) {
        assert tag != null;
        this.targetIndex = null;
        this.tag = tag;
        this.tagPredicate = tagPredicate;
    }

    private CommandResult executeDeleteOne(Model model) throws CommandException {
        assert targetIndex.getOneBased() > 0;
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    private CommandResult executeDeleteAll(Model model) {
        String courseCode = model.getAddressBook().getCourseCode();

        if (tag.isPresent()) {
            model.addFilter(tagPredicate);
        }

        List<Person> toDeleteList = model.getFilteredPersonList();
        List<Person> copyDeleteList = new ArrayList<>(toDeleteList);

        if (toDeleteList.isEmpty()) {
            String toDeleteListDesc = tag.isPresent()
                    ? String.format("%s Tutorial Group %s", courseCode, tag.get().getTagName()) : courseCode;
            model.clearFilters();
            return new CommandResult(String.format(MESSAGE_NO_STUDENTS, toDeleteListDesc));
        }

        for (Person p : copyDeleteList) {
            model.deletePerson(p);
        }

        String nameList = copyDeleteList.stream().map(person -> Messages.format(person))
                .collect(Collectors.joining(",\n"));

        model.clearFilters();
        return tag.isPresent()
                ? new CommandResult(String.format(MESSAGE_DELETE_TAGGED_SUCCESS,
                        courseCode, tag.get().getTagName(), nameList))
                : new CommandResult(String.format(MESSAGE_DELETE_NO_TAG_SUCCESS, courseCode, nameList));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!(targetIndex == null)) {
            return executeDeleteOne(model);
        }

        model.clearFilters();
        return executeDeleteAll(model);
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
