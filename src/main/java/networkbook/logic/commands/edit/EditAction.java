package networkbook.logic.commands.edit;

import networkbook.commons.core.index.Index;
import networkbook.logic.commands.exceptions.CommandException;

/**
 * Represents an action to edit a person.
 */
public interface EditAction {
    /**
     * Mutates the {@code editPersonDescriptor}, which is then used by `EditCommand` to edit the person the model.
     */
    void edit(EditPersonDescriptor editPersonDescriptor, Index indexOfPerson) throws CommandException;
}
