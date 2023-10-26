package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Deletes a person identified using its displayed index or tags from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number (or tags) used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) or " + PREFIX_TAG + "TAG [MORE_TAGS]...\n"
            + "Example (Delete by index): " + COMMAND_WORD + " 1\n"
            + "Example (Delete by tags): " + COMMAND_WORD + " " + PREFIX_TAG + "friends " + PREFIX_TAG + "colleague";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_PERSONS_NOT_FOUND = "No persons with the specified tags found.";
    public static final String MESSAGE_NO_INDEX_OR_TAGS = "You must specify either an index or tags to delete.";

    private Index targetIndex = Index.getDefaultIndex();
    private Set<Tag> targetTags = new HashSet<>();

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public DeleteCommand(Set<Tag> tagsIndex) {
        this.targetTags = tagsIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // Delete by index
        if (targetIndex.isPresent()) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
        }

        // Delete by tags
        if (!targetTags.isEmpty()) {
            List<Person> personsToDelete = new ArrayList<>();

            for (Person person : lastShownList) {
                for (Tag tag : targetTags) {
                    if (person.getTags().contains(tag) && !personsToDelete.contains(person)) {
                        personsToDelete.add(person);
                    }
                }
            }

            if (personsToDelete.isEmpty()) {
                throw new CommandException(MESSAGE_PERSONS_NOT_FOUND);
            }

            for (Person person : personsToDelete) {
                model.deletePerson(person);
            }

            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personsToDelete.size() == 1
                    ? Messages.format(personsToDelete.get(0)) : personsToDelete.size() + " persons"));

        }

        throw new CommandException(MESSAGE_NO_INDEX_OR_TAGS);
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
        return targetIndex.equals(otherDeleteCommand.targetIndex) && targetTags.equals(otherDeleteCommand.targetTags);
    }

    @Override
    public String toString() {
        ToStringBuilder stringBuilder = new ToStringBuilder(this);
        if (targetIndex.isPresent()) {
            return stringBuilder.add("targetIndex", targetIndex).toString();
        }

        if (!targetTags.isEmpty()) {
            return stringBuilder.add("targetTags", targetTags).toString();
        }

        return stringBuilder.add("invalid", "No valid target specified").toString();
    }
}
