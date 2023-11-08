package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.event.EventNameOrGroupContainsKeywordsPredicate;
import seedu.address.model.person.PersonNameOrGroupContainsKeywordsPredicate;

/**
 * Finds and lists all persons and events in address book whose name or group contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindAllCommand extends Command {

    public static final String COMMAND_WORD = "find_all";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all items whose names or groups contain any "
            + "of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final PersonNameOrGroupContainsKeywordsPredicate personPredicate;
    private final EventNameOrGroupContainsKeywordsPredicate eventPredicate;

    /**
     * Constructs the {@code FindAllCommand}.
     * @param personPredicate predicate for person
     * @param eventPredicate predicate for event
     */
    public FindAllCommand(PersonNameOrGroupContainsKeywordsPredicate personPredicate,
                          EventNameOrGroupContainsKeywordsPredicate eventPredicate) {
        this.personPredicate = personPredicate;
        this.eventPredicate = eventPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        eventPredicate.setPersonList(model.getFullPersonList());
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        model.updateFilteredEventList(eventPredicate);
        model.updateFilteredPersonList(personPredicate);
        int personListSize = model.getFilteredPersonList().size();
        int eventListSize = model.getFilteredEventList().size();
        String stringMessage = getResultString(personListSize, eventListSize);
        return new CommandResult(
                String.format(stringMessage, personListSize, eventListSize));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindAllCommand)) {
            return false;
        }

        FindAllCommand otherFindAllCommand = (FindAllCommand) other;
        return personPredicate.equals(otherFindAllCommand.personPredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", personPredicate)
                .toString();
    }

    private String getResultString(int personListSize, int eventListSize) {
        if (personListSize == 1 && eventListSize != 1) {
            return Messages.MESSAGE_PERSON_AND_EVENTS_LISTED_OVERVIEW;
        } else if (personListSize != 1 && eventListSize == 1) {
            return Messages.MESSAGE_PERSONS_AND_EVENT_LISTED_OVERVIEW;
        } else if (personListSize == 1 && eventListSize == 1) {
            return Messages.MESSAGE_PERSON_AND_EVENT_LISTED_OVERVIEW;
        } else {
            return Messages.MESSAGE_PERSONS_AND_EVENTS_LISTED_OVERVIEW;
        }
    }
}
