package networkbook.logic.commands.delete;

import networkbook.commons.core.index.Index;
import networkbook.logic.commands.exceptions.CommandException;

/**
 * Class that represents an action to delete the {@code graduation} field of a person.
 */
public class DeleteGraduationAction implements DeleteFieldAction {


    /**
     * Deletes the graduation field from the descriptor.
     * @param descriptor
     */
    @Override
    public void delete(DeletePersonDescriptor descriptor, Index indexOfPerson) throws CommandException {
        descriptor.deleteGraduation(indexOfPerson);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof DeleteGraduationAction;
    }
}
