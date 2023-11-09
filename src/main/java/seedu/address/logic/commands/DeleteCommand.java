package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using its displayed index or tags from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person(s) identified by the index number, tags, or status "
            + "used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) or " + PREFIX_TAG + "TAG [MORE_TAGS]... or "
            + PREFIX_STATUS + "STATUS\n"
            + "Example (Delete by index): " + COMMAND_WORD + " 1\n"
            + "Example (Delete by tags): " + COMMAND_WORD + " " + PREFIX_TAG + "manager " + PREFIX_TAG + "intern\n"
            + "Example (Delete by status): " + COMMAND_WORD + " " + PREFIX_STATUS + "rejected\n"
            + "Example (Delete by tags and status): " + COMMAND_WORD + " " + PREFIX_TAG + "softwareEngineer "
            + PREFIX_STATUS + "interviewed";


    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_PERSONS_NOT_FOUND = "No persons with the specified tags or status found.";
    public static final String MESSAGE_NO_TARGET_SPECIFIED = "You must specify either an index, tags, "
            + "or status to delete.";

    private Index targetIndex = Index.getDefaultIndex();
    private List<Predicate<Person>> predicatesList = new ArrayList<>();

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public DeleteCommand(List<Predicate<Person>> predicatesList) {
        this.predicatesList = predicatesList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Event> lastShownEventList = model.getFilteredEventList();

        // Delete by index
        if (targetIndex.isPresent()) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            List<Event> eventsToDelete = lastShownEventList.stream()
                    .filter(event -> event.getPerson().isSamePerson(personToDelete))
                    .collect(Collectors.toList());
            for (Event event : eventsToDelete) {
                model.deleteEvent(event);
            }
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
        }

        // Use predicates to filter the list of persons
        List<Person> personsToDelete = lastShownList.stream()
                .filter(person -> predicatesList.stream().allMatch(predicate -> predicate.test(person)))
                .collect(Collectors.toList());

        if (personsToDelete.isEmpty()) {
            throw new CommandException(MESSAGE_PERSONS_NOT_FOUND);
        }

        for (Person person : personsToDelete) {
            model.deletePerson(person);
        }

        List<Event> eventsToDelete = lastShownEventList.stream()
                .filter(event -> personsToDelete.stream()
                        .anyMatch(person -> event.getPerson().isSamePerson(person)))
                .collect(Collectors.toList());

        for (Event event : eventsToDelete) {
            model.deleteEvent(event);
        }


        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS,
                personsToDelete.size() == 1 ? Messages.format(personsToDelete.get(0))
                        : personsToDelete.size() + " persons"));
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
        return targetIndex.equals(otherDeleteCommand.targetIndex)
                && predicatesList.equals(otherDeleteCommand.predicatesList);
    }

    @Override
    public String toString() {
        ToStringBuilder stringBuilder = new ToStringBuilder(this);
        if (targetIndex.isPresent()) {
            return stringBuilder.add("targetIndex", targetIndex).toString();
        }

        if (!predicatesList.isEmpty()) {
            return stringBuilder.add("predicateList", predicatesList).toString();
        }

        return stringBuilder.add("invalid", "No valid target specified").toString();
    }
}
