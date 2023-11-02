package networkbook.logic.commands.delete;

import networkbook.commons.core.index.Index;
import networkbook.logic.commands.exceptions.CommandException;

/**
 * Interface that represents an abstract action to delete a field of a contact.
 */
public interface DeleteFieldAction {
    void delete(DeletePersonDescriptor descriptor, Index indexOfPerson) throws CommandException;
}
