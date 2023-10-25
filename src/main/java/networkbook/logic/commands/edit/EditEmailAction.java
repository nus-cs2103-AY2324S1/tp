package networkbook.logic.commands.edit;

import static java.util.Objects.requireNonNull;

import networkbook.commons.core.index.Index;
import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Email;

/**
 * Represents an action to edit an email of a person.
 */
public class EditEmailAction implements EditAction {
    private final Email email;
    private final Index index;

    /**
     * Constructs a new action to edit email.
     * @param index The index of the email in the email list of the person.
     * @param email The new value of the email.
     */
    public EditEmailAction(Index index, Email email) {
        this.index = index;
        this.email = email;
    }

    @Override
    public void edit(EditPersonDescriptor editPersonDescriptor) throws CommandException {
        requireNonNull(editPersonDescriptor);
        editPersonDescriptor.setEmail(index, email);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof EditEmailAction)) {
            return false;
        }

        EditEmailAction otherAction = (EditEmailAction) object;
        return this.email.equals(otherAction.email)
                && this.index.equals(otherAction.index);
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        toStringBuilder.add("email", this.email);
        toStringBuilder.add("index", this.index.getOneBased());
        return toStringBuilder.toString();
    }
}
