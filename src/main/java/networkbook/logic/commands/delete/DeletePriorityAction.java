package networkbook.logic.commands.delete;

import networkbook.commons.core.index.Index;
import networkbook.logic.commands.exceptions.CommandException;

/**
 * Class that represents an action to delete the {@code priority} field of a person.
 */
public class DeletePriorityAction implements DeleteFieldAction {


    /**
     * Deletes the priority field from the descriptor.
     * @param descriptor
     */
    @Override
    public void delete(DeletePersonDescriptor descriptor, Index indexOfPerson) throws CommandException {
        descriptor.deletePriority(indexOfPerson);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof DeletePriorityAction;
    }
}
