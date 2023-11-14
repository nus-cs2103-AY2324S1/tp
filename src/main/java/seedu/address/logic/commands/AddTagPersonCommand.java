package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds tags to an existing person in the address book.
 */
public class AddTagPersonCommand extends Command {

    public static final String COMMAND_WORD = "addTagPerson";
    public static final String SHORTENED_COMMAND_WORD = "atagp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " (alias: " + SHORTENED_COMMAND_WORD + ")"
            + ": Adds new tags to the person identified "
            + "by the index number used in the displayed contact list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "catering " + PREFIX_TAG + "smallBusiness";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s \n" + "Successfully added these "
            + "tags: %2$s \n" + "These tags were not added as they already exists: %3$s";

    // newTags stores those from tagsToAdd that are not already in personToEdit's tags
    private static Set<Tag> newTags;
    // oldTags stores those from tagsToAdd that are already in personToEdit's tags
    private static Set<Tag> oldTags;
    private final Set<Tag> tagsToAdd;
    private final Index index;

    /**
     * @param index of the person in the filtered contact list to edit
     * @param tagsToAdd tags to add to the person
     */
    public AddTagPersonCommand(Index index, Set<Tag> tagsToAdd) {
        requireNonNull(index);
        requireNonNull(tagsToAdd);

        this.index = index;
        this.tagsToAdd = tagsToAdd;
        newTags = new HashSet<>();
        oldTags = new HashSet<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownPersonList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, tagsToAdd);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson),
                setToString(newTags), setToString(oldTags)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}.
     */
    private static Person createEditedPerson(Person personToEdit, Set<Tag> tagsToAdd) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<Tag> existingTags = personToEdit.getTags();

        Set<Tag> commonTags = new HashSet<>(existingTags);
        commonTags.retainAll(tagsToAdd);

        oldTags.addAll(commonTags);

        newTags.addAll(tagsToAdd);
        newTags.removeAll(existingTags);

        Set<Tag> updatedTags = new HashSet<>(existingTags);
        updatedTags.addAll(tagsToAdd);

        return new Person(name, phone, email, address, updatedTags);
    }

    public static String setToString(Set<Tag> tags) {

        if (tags.isEmpty()) {
            return "-";
        } else {
            return tags.stream()
                    .map(Tag::toString)
                    .collect(Collectors.joining(", "));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagPersonCommand)) {
            return false;
        }

        AddTagPersonCommand otherAddTagPersonCommand = (AddTagPersonCommand) other;
        return index.equals(otherAddTagPersonCommand.index)
                && tagsToAdd.equals(otherAddTagPersonCommand.tagsToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("tagsToAdd", tagsToAdd)
                .toString();
    }
}
