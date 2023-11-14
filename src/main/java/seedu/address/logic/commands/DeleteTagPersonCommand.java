package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Deletes a tag from a person identified using its displayed index from the address book.
 */
public class DeleteTagPersonCommand extends Command {

    public static final String COMMAND_WORD = "deleteTagPerson";
    public static final String SHORTENED_COMMAND_WORD = "dtagp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " (alias: " + SHORTENED_COMMAND_WORD + ")"
            + ": Deletes one or more tags from a person identified using its displayed index from the address book.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "TAG "
            + "[" + PREFIX_TAG + "MORE TAGS]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "catering "
            + PREFIX_TAG + "budget";

    public static final String MESSAGE_DELETE_TAG_PERSON_SUCCESS = "Deleted tag(s) from person: %1$s\n";
    public static final String MESSAGE_DELETE_TAG_PERSON_MATCHING_TAGS = "Successfully removed these tags: %1$s\n";
    public static final String MESSAGE_DELETE_TAG_PERSON_MISSING_TAGS = "These tags did not seem to exist: %1$s\n";

    private final Index targetIndex;
    private final Set<Tag> tagsToDelete;

    /**
     * @param targetIndex of the person in the filtered person list to edit
     * @param tagsToDelete tags to delete from the person
     */
    public DeleteTagPersonCommand(Index targetIndex, Set<Tag> tagsToDelete) {
        this.targetIndex = targetIndex;
        this.tagsToDelete = tagsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, tagsToDelete);
        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(formatResultMessage(personToEdit, editedPerson, tagsToDelete));
    }

    private static Person createEditedPerson(Person personToEdit, Set<Tag> tagsToDelete) {
        requireNonNull(personToEdit);
        requireNonNull(tagsToDelete);

        Set<Tag> newTags = new HashSet<>(personToEdit.getTags());
        newTags.removeAll(tagsToDelete);
        return new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                newTags);
    }

    /**
     * Determines what the CommandResult string should be.
     * Handles cases of missing tags.
     */
    public static String formatResultMessage(Person personToEdit, Person editedPerson, Set<Tag> tagsToDelete) {
        Set<Tag> matchingTags = new HashSet<>(personToEdit.getTags());
        matchingTags.retainAll(tagsToDelete);

        Set<Tag> missingTags = new HashSet<>(tagsToDelete);
        missingTags.removeAll(matchingTags);

        String res = String.format(MESSAGE_DELETE_TAG_PERSON_SUCCESS, Messages.format(editedPerson));
        if (!matchingTags.isEmpty()) {
            String matchingTagsString = matchingTags.stream().map(Tag::toString).collect(Collectors.joining(", "));
            res += String.format(MESSAGE_DELETE_TAG_PERSON_MATCHING_TAGS, matchingTagsString);
        }

        if (!missingTags.isEmpty()) {
            String missingTagsString = missingTags.stream().map(Tag::toString).collect(Collectors.joining(", "));
            res += String.format(MESSAGE_DELETE_TAG_PERSON_MISSING_TAGS, missingTagsString);
        }
        return res;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagPersonCommand)) {
            return false;
        }

        DeleteTagPersonCommand otherDeleteCommand = (DeleteTagPersonCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex)
                && tagsToDelete.equals(otherDeleteCommand.tagsToDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("tagsToDelete", tagsToDelete)
                .toString();
    }
}
