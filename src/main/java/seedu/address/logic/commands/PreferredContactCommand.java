
package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERRED_CONTACT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PreferredContact;

/**
 * Adds the preferred contact method of the client to the address book.
 */
public class PreferredContactCommand extends Command {

    public static final String COMMAND_WORD = "preferredContact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Show the preferred contact details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_PREFERRED_CONTACT + "PREFERRED_CONTACT\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PREFERRED_CONTACT + "phone";

    public static final String MESSAGE_PREFERREDCONTACT_PERSON_SUCCESS =
            "Preferred contact: %1$s";
    public static final String MESSAGE_PREFERREDCONTACT_NOT_EDITED =
            "At least one field must be provided.";
    public static final String MESSAGE_PREFERREDCONTACT_INVALID =
            "Preferred contact must be either email or phone, else it is blank.";

    private final Index index;
    private final PreferredContact preferredContact;

    /**
     * Represents a constructor of PreferredContactCommand.
     * @param index            of the person in the filtered person list to edit
     * @param preferredContact details to edit the person with
     */
    public PreferredContactCommand(Index index, PreferredContact preferredContact) {
        requireAllNonNull(index, preferredContact);

        this.index = index;
        this.preferredContact = preferredContact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person preferredContactPerson = lastShownList.get(index.getZeroBased());
        Person editedPreferredContactPerson = new Person(preferredContactPerson.getName(),
                preferredContactPerson.getPhone(), preferredContactPerson.getEmail(),
                preferredContactPerson.getAddress(), preferredContactPerson.getTags(),
                preferredContactPerson.getPreferredMeetingRegion(), preferredContact,
                preferredContactPerson.getPolicies());

        model.setPerson(preferredContactPerson, editedPreferredContactPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_PREFERREDCONTACT_PERSON_SUCCESS,
                Messages.format(editedPreferredContactPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PreferredContactCommand)) {
            return false;
        }

        PreferredContactCommand otherPreferredContactCommand = (PreferredContactCommand) other;
        return index.equals(otherPreferredContactCommand.index)
                && preferredContact.equals(otherPreferredContactCommand.preferredContact);
    }
}
