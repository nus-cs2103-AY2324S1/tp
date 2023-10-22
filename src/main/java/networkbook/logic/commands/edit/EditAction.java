package networkbook.logic.commands.edit;

import networkbook.logic.commands.exceptions.CommandException;

public interface EditAction {
    void edit(EditPersonDescriptor editPersonDescriptor) throws CommandException;
}
