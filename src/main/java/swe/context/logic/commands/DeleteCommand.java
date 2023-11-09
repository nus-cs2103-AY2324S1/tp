package swe.context.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import swe.context.commons.core.index.Index;
import swe.context.commons.util.ToStringBuilder;
import swe.context.logic.Messages;
import swe.context.logic.commands.exceptions.CommandException;
import swe.context.model.Model;
import swe.context.model.contact.Contact;



/**
 * Deletes one or more {@link Contact}s based on their displayed indices in the UI list.
 * Duplicate indices are considered only once.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = String.format(
        "%s: Deletes contact(s)."
                + "%nParameters: INDEX..."
                + "%nExample: %s 1 3 5",
        DeleteCommand.COMMAND_WORD,
        DeleteCommand.COMMAND_WORD
    );

    private final List<Index> targetIndices;

    public DeleteCommand(List<Index> targetIndices) {
        this.targetIndices = targetIndices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Contact> currentContactList = model.getFilteredContactList();
        validateIndices(currentContactList);

        List<Contact> contactsToDelete = collectContactsToDelete(currentContactList);
        contactsToDelete.forEach(model::removeContact);

        String formattedContacts = formatContactsForMessage(contactsToDelete);
        return new CommandResult(Messages.deleteCommandSuccess(formattedContacts));
    }

    private void validateIndices(List<Contact> contactList) throws CommandException {
        for (Index index : targetIndices) {
            if (index.getZeroBased() >= contactList.size()) {
                throw new CommandException(Messages.INVALID_DELETE_INDEX);
            }
        }
    }

    private List<Contact> collectContactsToDelete(List<Contact> contactList) {
        return targetIndices.stream()
                .distinct()
                .map(index -> contactList.get(index.getZeroBased()))
                .collect(Collectors.toList());
    }

    private String formatContactsForMessage(List<Contact> contacts) {
        return contacts.stream()
                .map(Contact::format)
                .collect(Collectors.joining(",\n"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndices.equals(otherDeleteCommand.targetIndices);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndices", targetIndices)
                .toString();
    }
}
